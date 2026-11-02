/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemaoptica.dao.impl;

import com.mycompany.sistemaoptica.conexion.ConexionDB;
import com.mycompany.sistemaoptica.dao.ProductoDAO;
import com.mycompany.sistemaoptica.model.Producto;
import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Dell
 */
public class ProductoDAOImpl implements ProductoDAO {
    
    @Override
    public boolean insertar(Producto p){
        
        String sql = "INSERT INTO Productos "
                + "(Nombre, Tipo, Marca, Material, Forma, Precio, Stock)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
  
    
     try(Connection cn = ConexionDB.getConexion();
            PreparedStatement ps = cn.prepareStatement(sql)){
    
       ps.setString(1, p.getNombre());
       ps.setString(2, p.getTipo());
       ps.setString(3, p.getMarca());
       ps.setString(4, p.getMaterial());
       ps.setString(5, p.getForma());
       ps.setDouble(6, p.getPrecio());
       ps.setInt(7, p.getStock());
    
       return ps.executeUpdate() >0;
    
      }catch (SQLException e){
       System.out.println("Error al Insertar: " + e.getMessage());
       return false;
    
      }
    } 
    
    @Override
    public List<Producto> listar(){
        List<Producto> lista = new ArrayList<>();
        
        String sql = "SELECT * FROM Productos "
                
                + "WHERE Activo = 1 ORDER BY ProductoID";
        
        try (Connection cn = ConexionDB.getConexion();
                PreparedStatement ps = cn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()){
            
            while (rs.next()){
                Producto p = new Producto();
                
                p.setProductoID(rs.getInt("ProductoID"));
                p.setNombre(rs.getString("Nombre"));
                p.setTipo(rs.getString("Tipo"));
                p.setMarca(rs.getString("Marca"));
                p.setMaterial(rs.getString("Material"));
                p.setForma(rs.getString("Forma"));
                p.setPrecio(rs.getDouble("Precio"));
                p.setStock(rs.getInt("Stock"));
                p.setActivo(rs.getBoolean("Activo"));
                
                lista.add(p);
            }
            
        }catch (SQLException e){
            System.out.println("Error al listar:" + e.getMessage());
        }
        
        return lista;
        
    }
    @Override
    public Producto buscarPorID(int id) {
        String sql = "SELECT * FROM Productos " 
                + "WHERE ProductoID = ? AND Activo = 1";

        try (Connection cn = ConexionDB.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Producto p = new Producto();

                    p.setProductoID(rs.getInt("ProductoID"));
                    p.setNombre(rs.getString("Nombre"));
                    p.setTipo(rs.getString("Tipo"));
                    p.setMarca(rs.getString("Marca"));
                    p.setMaterial(rs.getString("Material"));
                    p.setForma(rs.getString("Forma"));
                    p.setPrecio(rs.getDouble("Precio"));
                    p.setStock(rs.getInt("Stock"));
                    p.setActivo(rs.getBoolean("Activo"));

                    return p;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar: " + e.getMessage());
        }

        return null;
    }
    
     @Override
    public boolean actualizar(Producto p) {
        String sql = "UPDATE Productos SET Nombre=?, Tipo=?, "
                + "Marca=?, Material=?, Forma=?, Precio=?, Stock=? "
                + "WHERE ProductoID=? AND Activo=1";

        try (Connection cn = ConexionDB.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, p.getNombre());
            ps.setString(2, p.getTipo());
            ps.setString(3, p.getMarca());
            ps.setString(4, p.getMaterial());
            ps.setString(5, p.getForma());
            ps.setDouble(6, p.getPrecio());
            ps.setInt(7, p.getStock());
            ps.setInt(8, p.getProductoID());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar: " + e.getMessage());
            return false;
        }
    
        
    }
    
    
    @Override
    public boolean eliminar(int id) {
        String sql = "UPDATE Productos SET Activo=0 "
                + "WHERE ProductoID=? AND Activo=1";

        try (Connection cn = ConexionDB.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminar: " + e.getMessage());
            return false;
        }
    }
    
     @Override
    public List<Producto> listarStockBajo() {
        List<Producto> lista = new ArrayList<>();

        String sql = "SELECT * FROM Productos "
                + "WHERE Tipo='ARO' AND Stock < 5 AND Activo=1";

        try (Connection cn = ConexionDB.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Producto p = new Producto();

                p.setProductoID(rs.getInt("ProductoID"));
                p.setNombre(rs.getString("Nombre"));
                p.setMarca(rs.getString("Marca"));
                p.setPrecio(rs.getDouble("Precio"));
                p.setStock(rs.getInt("Stock"));
                p.setActivo(rs.getBoolean("Activo"));

                lista.add(p);
            }

        } catch (SQLException e) {
            System.out.println("Error al consultar stock: "
                    + e.getMessage());
        }

        return lista;
    }
    
    public List<Producto> buscarAros(String marca, String material,
        String forma, Double precioMin, Double precioMax) {

    List<Producto> lista = new ArrayList<>();

    String sql = "SELECT * FROM Productos "
            + "WHERE Tipo = 'ARO' AND Activo = 1 AND Stock > 0";

    if (marca != null && !marca.equals("Todos")) {
        sql += " AND Marca = ?";
    }

    if (material != null && !material.equals("Todos")) {
        sql += " AND Material = ?";
    }

    if (forma != null && !forma.equals("Todos")) {
        sql += " AND Forma = ?";
    }

    if (precioMin != null) {
        sql += " AND Precio >= ?";
    }

    if (precioMax != null) {
        sql += " AND Precio <= ?";
    }

    sql += " ORDER BY ProductoID";

    try (Connection cn = ConexionDB.getConexion();
         PreparedStatement ps = cn.prepareStatement(sql)) {

        int posicion = 1;

        if (marca != null && !marca.equals("Todos")) {
            ps.setString(posicion++, marca);
        }

        if (material != null && !material.equals("Todos")) {
            ps.setString(posicion++, material);
        }

        if (forma != null && !forma.equals("Todos")) {
            ps.setString(posicion++, forma);
        }

        if (precioMin != null) {
            ps.setDouble(posicion++, precioMin);
        }

        if (precioMax != null) {
            ps.setDouble(posicion++, precioMax);
        }

        try (ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Producto p = new Producto();

                p.setProductoID(rs.getInt("ProductoID"));
                p.setNombre(rs.getString("Nombre"));
                p.setTipo(rs.getString("Tipo"));
                p.setMarca(rs.getString("Marca"));
                p.setMaterial(rs.getString("Material"));
                p.setForma(rs.getString("Forma"));
                p.setPrecio(rs.getDouble("Precio"));
                p.setStock(rs.getInt("Stock"));
                p.setActivo(rs.getBoolean("Activo"));

                lista.add(p);
            }
        }

    } catch (SQLException e) {
        System.out.println("Error al buscar aros: " + e.getMessage());
    }

    return lista;
}
    
    
}
