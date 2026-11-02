/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemaoptica.dao.impl;

import com.mycompany.sistemaoptica.conexion.ConexionDB;
import com.mycompany.sistemaoptica.dao.VentaDAO;
import com.mycompany.sistemaoptica.model.DetalleVenta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;


/**
 *
 * @author Dell
 */
public class VentaDAOImpl implements VentaDAO {
    
    @Override
    public int registrarVenta(int clienteID, List<DetalleVenta> detalles) {

        if (clienteID <= 0 || detalles == null || detalles.isEmpty()) {
            return -1;
        }

        Connection cn = null;

        try {

            cn = ConexionDB.getConexion();
            cn.setAutoCommit(false);

            // 1. Buscar la última medición del cliente
            int medicionID;

            String sqlMedicion =
                    "SELECT TOP 1 m.MedicionID "
                  + "FROM Mediciones m "
                  + "INNER JOIN Clientes c ON m.ClienteID = c.ClienteID "
                  + "INNER JOIN Usuarios u ON c.UsuarioID = u.UsuarioID "
                  + "WHERE m.ClienteID = ? AND u.Activo = 1 "
                  + "ORDER BY m.Fecha DESC, m.MedicionID DESC";

            try (PreparedStatement ps = cn.prepareStatement(sqlMedicion)) {

                ps.setInt(1, clienteID);

                try (ResultSet rs = ps.executeQuery()) {

                    if (!rs.next()) {
                        throw new SQLException(
                                "El cliente no tiene mediciones registradas.");
                    }

                    medicionID = rs.getInt("MedicionID");
                }
            }

            // 2. Calcular subtotal
            BigDecimal subtotalVenta = BigDecimal.ZERO;

            for (DetalleVenta d : detalles) {

                if (d == null || d.getProductoID() <= 0
                        || d.getCantidad() <= 0) {
                    throw new SQLException("Detalle de venta inválido.");
                }

                BigDecimal precio = obtenerPrecio(
                        cn, d.getProductoID(), d.getCantidad());

                BigDecimal subtotalProducto = precio.multiply(
                        BigDecimal.valueOf(d.getCantidad()));

                subtotalVenta = subtotalVenta.add(subtotalProducto);
            }

            subtotalVenta = subtotalVenta.setScale(
                    2, RoundingMode.HALF_UP);

            // 3. Calcular ISV del 15%
            BigDecimal impuesto = subtotalVenta.multiply(
                    new BigDecimal("0.15"))
                    .setScale(2, RoundingMode.HALF_UP);

            BigDecimal total = subtotalVenta.add(impuesto)
                    .setScale(2, RoundingMode.HALF_UP);

            // 4. Registrar la venta
            String sqlVenta =
                    "INSERT INTO Ventas "
                  + "(ClienteID, MedicionID, Fecha, Subtotal, Impuesto, Total) "
                  + "VALUES (?, ?, SYSDATETIME(), ?, ?, ?)";

            int ventaID;

            try (PreparedStatement ps = cn.prepareStatement(
                    sqlVenta, PreparedStatement.RETURN_GENERATED_KEYS)) {

                ps.setInt(1, clienteID);
                ps.setInt(2, medicionID);
                ps.setBigDecimal(3, subtotalVenta);
                ps.setBigDecimal(4, impuesto);
                ps.setBigDecimal(5, total);

                ps.executeUpdate();

                try (ResultSet rs = ps.getGeneratedKeys()) {

                    if (!rs.next()) {
                        throw new SQLException(
                                "No se pudo obtener el ID de la venta.");
                    }

                    ventaID = rs.getInt(1);
                }
            }

            // 5. Preparar el detalle y descuento de inventario
            String sqlDetalle =
                    "INSERT INTO DetalleVenta "
                  + "(VentaID, ProductoID, Cantidad, PrecioUnitario, Subtotal) "
                  + "VALUES (?, ?, ?, ?, ?)";

            String sqlDescontar =
                    "UPDATE Productos "
                  + "SET Stock = Stock - ? "
                  + "WHERE ProductoID = ? AND Activo = 1 AND Stock >= ?";

            try (PreparedStatement psDetalle =
                         cn.prepareStatement(sqlDetalle);
                 PreparedStatement psStock =
                         cn.prepareStatement(sqlDescontar)) {

                for (DetalleVenta d : detalles) {

                    BigDecimal precio = obtenerPrecio(
                            cn, d.getProductoID(), d.getCantidad());

                    BigDecimal subtotalProducto = precio.multiply(
                            BigDecimal.valueOf(d.getCantidad()))
                            .setScale(2, RoundingMode.HALF_UP);

                    // Descontar existencias
                    psStock.setInt(1, d.getCantidad());
                    psStock.setInt(2, d.getProductoID());
                    psStock.setInt(3, d.getCantidad());

                    if (psStock.executeUpdate() == 0) {
                        throw new SQLException(
                                "Stock insuficiente o producto inactivo: "
                                + d.getProductoID());
                    }

                    // Insertar detalle
                    psDetalle.setInt(1, ventaID);
                    psDetalle.setInt(2, d.getProductoID());
                    psDetalle.setInt(3, d.getCantidad());
                    psDetalle.setBigDecimal(4, precio);
                    psDetalle.setBigDecimal(5, subtotalProducto);

                    psDetalle.executeUpdate();
                }
            }

            // 6. Confirmar transacción
            cn.commit();

            return ventaID;

        } catch (SQLException e) {

            if (cn != null) {
                try {
                    cn.rollback();
                } catch (SQLException ex) {
                    System.out.println(
                            "Error al revertir: " + ex.getMessage());
                }
            }

            System.out.println(
                    "Error al registrar venta: " + e.getMessage());

            return -1;

        } finally {

            if (cn != null) {
                try {
                    cn.setAutoCommit(true);
                    cn.close();
                } catch (SQLException e) {
                    System.out.println(
                            "Error al cerrar conexión: " + e.getMessage());
                }
            }
        }
    }

    // Consultar precio y comprobar existencias
    private BigDecimal obtenerPrecio(
            Connection cn, int productoID, int cantidad)
            throws SQLException {

        String sql =
                "SELECT Precio FROM Productos WITH (UPDLOCK, HOLDLOCK) "
              + "WHERE ProductoID = ? AND Activo = 1 AND Stock >= ?";

        try (PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, productoID);
            ps.setInt(2, cantidad);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    return rs.getBigDecimal("Precio");
                }

                throw new SQLException(
                        "Producto inactivo o stock insuficiente: "
                        + productoID);
            }
        }
    }
}