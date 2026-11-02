/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemaoptica.dao.impl;
import com.mycompany.sistemaoptica.dao.HistorialVentaDAO;
import com.mycompany.sistemaoptica.conexion.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
import com.mycompany.sistemaoptica.dao.HistorialVentaDAO;

/**
 *
 * @author Dell
 */
public class HistorialVentaDAOImpl implements HistorialVentaDAO {
    
     @Override
    public DefaultTableModel listarVentas() {

        DefaultTableModel modelo = new DefaultTableModel();

        modelo.addColumn("VentaID");
        modelo.addColumn("Fecha");
        modelo.addColumn("Cliente");
        modelo.addColumn("Subtotal");
        modelo.addColumn("Impuesto");
        modelo.addColumn("Total");

        String sql = "SELECT v.VentaID, v.Fecha, u.NombreUsuario, "
                + "v.Subtotal, v.Impuesto, v.Total "
                + "FROM Ventas v "
                + "INNER JOIN Clientes c ON v.ClienteID = c.ClienteID "
                + "INNER JOIN Usuarios u ON c.UsuarioID = u.UsuarioID "
                + "ORDER BY v.Fecha DESC";

        try (Connection con = ConexionDB.getConexion();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Object[] fila = {
                    rs.getInt("VentaID"),
                    rs.getTimestamp("Fecha"),
                    rs.getString("NombreUsuario"),
                    rs.getDouble("Subtotal"),
                    rs.getDouble("Impuesto"),
                    rs.getDouble("Total")
                };

                modelo.addRow(fila);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return modelo;
    }
    
    
    @Override
  public DefaultTableModel buscarPorCliente(String nombre) {

    DefaultTableModel modelo = new DefaultTableModel();

    modelo.addColumn("VentaID");
    modelo.addColumn("Fecha");
    modelo.addColumn("Cliente");
    modelo.addColumn("Subtotal");
    modelo.addColumn("Impuesto");
    modelo.addColumn("Total");

    String sql = "SELECT v.VentaID, v.Fecha, u.NombreUsuario, "
            + "v.Subtotal, v.Impuesto, v.Total "
            + "FROM Ventas v "
            + "INNER JOIN Clientes c ON v.ClienteID = c.ClienteID "
            + "INNER JOIN Usuarios u ON c.UsuarioID = u.UsuarioID "
            + "WHERE u.NombreUsuario LIKE ? "
            + "ORDER BY v.Fecha DESC";

    try (Connection con = ConexionDB.getConexion();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, "%" + nombre + "%");

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            Object[] fila = {
                rs.getInt("VentaID"),
                rs.getTimestamp("Fecha"),
                rs.getString("NombreUsuario"),
                rs.getDouble("Subtotal"),
                rs.getDouble("Impuesto"),
                rs.getDouble("Total")
            };

            modelo.addRow(fila);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return modelo;
    
   }
  
  @Override
 public DefaultTableModel buscarPorFiltros(
        String nombre, String desde, String hasta) {

      DefaultTableModel modelo = new DefaultTableModel();

        modelo.addColumn("VentaID");
        modelo.addColumn("Fecha");
        modelo.addColumn("Cliente");
        modelo.addColumn("Subtotal");
        modelo.addColumn("Impuesto");
        modelo.addColumn("Total");

        String sql = "SELECT v.VentaID, v.Fecha, u.NombreUsuario, "
            + "v.Subtotal, v.Impuesto, v.Total "
            + "FROM Ventas v "
            + "INNER JOIN Clientes c ON v.ClienteID = c.ClienteID "
            + "INNER JOIN Usuarios u ON c.UsuarioID = u.UsuarioID "
            + "WHERE (? = '' OR u.NombreUsuario LIKE ?) "
            + "AND (? = '' OR v.Fecha >= CAST(? AS DATE)) "
            + "AND (? = '' OR v.Fecha < DATEADD(DAY, 1, CAST(? AS DATE))) "
            + "ORDER BY v.Fecha DESC";

        try (Connection con = ConexionDB.getConexion();
         PreparedStatement ps = con.prepareStatement(sql)) {

          ps.setString(1, nombre);
          ps.setString(2, "%" + nombre + "%");
          ps.setString(3, desde);
          ps.setString(4, desde);
          ps.setString(5, hasta);
          ps.setString(6, hasta);

          ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            Object[] fila = {
                rs.getInt("VentaID"),
                rs.getTimestamp("Fecha"),
                rs.getString("NombreUsuario"),
                rs.getDouble("Subtotal"),
                rs.getDouble("Impuesto"),
                rs.getDouble("Total")
            };

            modelo.addRow(fila);
        }

      } catch (Exception e) {
        e.printStackTrace();
       }

        return modelo;
    

    }
 
 @Override
public DefaultTableModel buscarDetalle(int ventaID) {

    DefaultTableModel modelo = new DefaultTableModel();

    modelo.addColumn("Producto");
    modelo.addColumn("Cantidad");
    modelo.addColumn("Precio Unitario");
    modelo.addColumn("Subtotal");

    String sql = "SELECT p.Nombre, d.Cantidad, "
            + "d.PrecioUnitario, d.Subtotal "
            + "FROM DetalleVenta d "
            + "INNER JOIN Productos p ON d.ProductoID = p.ProductoID "
            + "WHERE d.VentaID = ?";

    try (Connection con = ConexionDB.getConexion();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setInt(1, ventaID);

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {

            Object[] fila = {
                rs.getString("Nombre"),
                rs.getInt("Cantidad"),
                rs.getDouble("PrecioUnitario"),
                rs.getDouble("Subtotal")
            };

            modelo.addRow(fila);
        }

    } catch (Exception e) {
        e.printStackTrace();
    }

    return modelo;
  }
}