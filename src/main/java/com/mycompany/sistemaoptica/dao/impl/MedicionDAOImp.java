/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemaoptica.dao.impl;
import com.mycompany.sistemaoptica.conexion.ConexionDB;
import com.mycompany.sistemaoptica.dao.MedicionDAO;
import com.mycompany.sistemaoptica.model.Medicion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Dell
 */
public class MedicionDAOImp implements MedicionDAO {
    @Override
    public boolean insertar(Medicion m) {

        String sql = "INSERT INTO Mediciones "
                + "(ClienteID, EsferaOD, CilindroOD, EjeOD, "
                + "EsferaOI, CilindroOI, EjeOI, Adicion, Observaciones) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection cn = ConexionDB.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, m.getClienteID());
            ps.setDouble(2, m.getEsferaOD());
            ps.setDouble(3, m.getCilindroOD());
            ps.setInt(4, m.getEjeOD());
            ps.setDouble(5, m.getEsferaOI());
            ps.setDouble(6, m.getCilindroOI());
            ps.setInt(7, m.getEjeOI());
            ps.setDouble(8, m.getAdicion());
            ps.setString(9, m.getObservaciones());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al insertar medición: "
                    + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Medicion> listarPorCliente(int clienteID) {

        List<Medicion> lista = new ArrayList<>();

        String sql = "SELECT * FROM Mediciones "
                + "WHERE ClienteID = ? ORDER BY Fecha DESC";

        try (Connection cn = ConexionDB.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, clienteID);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    Medicion m = new Medicion();

                    m.setMedicionID(rs.getInt("MedicionID"));
                    m.setClienteID(rs.getInt("ClienteID"));
                    m.setFecha(rs.getDate("Fecha"));
                    m.setEsferaOD(rs.getDouble("EsferaOD"));
                    m.setCilindroOD(rs.getDouble("CilindroOD"));
                    m.setEjeOD(rs.getInt("EjeOD"));
                    m.setEsferaOI(rs.getDouble("EsferaOI"));
                    m.setCilindroOI(rs.getDouble("CilindroOI"));
                    m.setEjeOI(rs.getInt("EjeOI"));
                    m.setAdicion(rs.getDouble("Adicion"));
                    m.setObservaciones(rs.getString("Observaciones"));

                    lista.add(m);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al listar mediciones: "
                    + e.getMessage());
        }

        return lista;
    }
}
