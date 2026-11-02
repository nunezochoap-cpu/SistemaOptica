/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemaoptica.dao.impl;
import com.mycompany.sistemaoptica.conexion.ConexionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Dell
 */
public class AroApartadoDAOImpl {
      public boolean registrarApartado(int clienteID, int productoID) {

        String sql = "INSERT INTO ArosApartados "
                + "(ClienteID, ProductoID) "
                + "SELECT ?, ? "
                + "WHERE EXISTS ("
                + "SELECT 1 FROM Productos "
                + "WHERE ProductoID = ? AND Tipo = 'ARO' "
                + "AND Activo = 1 AND Stock > 0)";

        try (Connection cn = ConexionDB.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, clienteID);
            ps.setInt(2, productoID);
            ps.setInt(3, productoID);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al apartar aro: " + e.getMessage());
            return false;
        }
    }
      
      public javax.swing.table.DefaultTableModel listarApartados(int clienteID) {

    javax.swing.table.DefaultTableModel modelo =
            new javax.swing.table.DefaultTableModel();

    modelo.addColumn("ID");
    modelo.addColumn("ARO");
    modelo.addColumn("PRECIO");
    modelo.addColumn("ESTADO");

    String sql = "SELECT A.ApartadoID, P.Nombre, P.Precio, A.Estado "
            + "FROM ArosApartados A "
            + "INNER JOIN Productos P ON A.ProductoID = P.ProductoID "
            + "WHERE A.ClienteID = ? "
            + "ORDER BY A.FechaApartado DESC";

    try (java.sql.Connection cn =
                 com.mycompany.sistemaoptica.conexion.ConexionDB.getConexion();
         java.sql.PreparedStatement ps = cn.prepareStatement(sql)) {

        ps.setInt(1, clienteID);

        try (java.sql.ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                modelo.addRow(new Object[]{
                    rs.getInt("ApartadoID"),
                    rs.getString("Nombre"),
                    rs.getBigDecimal("Precio"),
                    rs.getString("Estado")
                });
            }
        }

    } catch (java.sql.SQLException e) {
        System.out.println("Error al listar apartados: " + e.getMessage());
    }

    return modelo;
}

    
}
