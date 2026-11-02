/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemaoptica.dao.impl;

import com.mycompany.sistemaoptica.conexion.ConexionDB;
import com.mycompany.sistemaoptica.dao.UsuarioDAO;
import com.mycompany.sistemaoptica.model.Usuario;
import com.mycompany.sistemaoptica.service.Seguridad;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author Dell
 */
public class UsuarioDAOImpl implements UsuarioDAO {
    
    @Override 
    public Usuario buscarPorCorreo(String correo){
        String sql = "SELECT * FROM Usuarios WHERE Correo = ? AND Activo = 1";
        
        try (Connection cn = ConexionDB.getConexion();
                PreparedStatement ps = cn.prepareStatement(sql)){
            
            ps.setString(1, correo);
            
            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    Usuario u = new Usuario();
                    u.setUsuarioID(rs.getInt("UsuarioID"));
                    u.setNombreUsuario(rs.getString("NombreUsuario"));
                    u.setCorreo(rs.getString("Correo"));
                    u.setContraseña(rs.getString("ContrasenaHash"));
                    u.setRol(rs.getString("Rol"));
                    return u;
                      
                }
            }
        }catch (SQLException e){
            System.out.println("Error al buscar Usuario: "
            + e.getMessage());
        }
        
        return null;
    }
    
    @Override
    public boolean insertar(Usuario u ){
        
        String sql = "INSERT INTO Usuarios "
                + "(NombreUsuario, Correo, ContrasenaHash, Rol)"
                + " VALUES (?, ?, ?, ?)";
        
        try (Connection cn = ConexionDB.getConexion();
                PreparedStatement ps = cn.prepareStatement(sql)){
            
            ps.setString(1, u.getNombreUsuario());
            ps.setString(2, u. getCorreo());
            ps.setString(3, Seguridad.generaHash(u.getContraseña()));
            ps.setString(4, u.getRol());
            
            return ps.executeUpdate() > 0;
            
            
        }catch (SQLException e) {
            System.out.println("Error al registrar Usuario:"
            + e.getMessage());
            return false;
        }
    }
    
}
