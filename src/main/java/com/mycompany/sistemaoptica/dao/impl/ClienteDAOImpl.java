/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemaoptica.dao.impl;

import com.mycompany.sistemaoptica.conexion.ConexionDB;
import com.mycompany.sistemaoptica.dao.ClienteDAO;
import com.mycompany.sistemaoptica.model.Cliente;
import com.mycompany.sistemaoptica.model.Usuario;
import com.mycompany.sistemaoptica.service.Seguridad;
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
public class ClienteDAOImpl implements ClienteDAO {
    
    @Override
    public boolean insertar(Cliente c){
        String sql = "INSERT INTO Clientes (UsuarioID, DNI, Telefono)" + "VALUES (?, ?, ?)";
        
        try (Connection cn = ConexionDB.getConexion();
         PreparedStatement ps = cn.prepareStatement(sql)){
            
            ps.setInt(1, c.getUsuarioID());
            ps.setString(2, c.getDni());
            ps.setString(3, c.getTelefono());
            
            return ps.executeUpdate() > 0;
            
        }catch (SQLException e){
                System.out.println("Error al insertar cliente: " + e.getMessage());
                return false;
                
        }
    }
    
    
    @Override
    public Cliente buscarPorID(int id){
        String sql = "SELECT c.* FROM Clientes c "
                + "INNER JOIN Usuarios u ON c.UsuarioID = u.UsuarioID "
                + "WHERE c.ClienteID = ? AND u.Activo = 1";
        
        try (Connection cn = ConexionDB.getConexion();
         PreparedStatement ps =  cn.prepareStatement(sql)){
            
            ps.setInt(1, id);  
            
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()){
                    Cliente c = new Cliente();
                    c.setClienteID(rs.getInt("ClienteID"));
                    c.setUsuarioID(rs.getInt("UsuarioID"));
                    c.setDni(rs.getString("DNI"));
                    c.setTelefono(rs.getString("Telefono"));
                    return c;
                }
            }
        }catch (SQLException e){
            System.out.println("Error al buscar cliente: " + e.getMessage());
        }
        
        return null;
    }
    
      @Override
    public List<Cliente> listar() {
        List<Cliente> lista = new ArrayList<>();

        String sql = "SELECT c.* FROM Clientes c "
                   + "INNER JOIN Usuarios u ON c.UsuarioID = u.UsuarioID "
                   + "WHERE u.Activo = 1 ORDER BY c.ClienteID";

        try (Connection cn = ConexionDB.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Cliente c = new Cliente();
                c.setClienteID(rs.getInt("ClienteID"));
                c.setUsuarioID(rs.getInt("UsuarioID"));
                c.setDni(rs.getString("DNI"));
                c.setTelefono(rs.getString("Telefono"));
                lista.add(c);
            }

        } catch (SQLException e) {
            System.out.println("Error al listar clientes: "
                    + e.getMessage());
        }

        return lista;
    }

    @Override
    public boolean actualizar(Cliente c) {
        String sql = "UPDATE Clientes SET DNI = ?, Telefono = ? "
                   + "WHERE ClienteID = ?";

        try (Connection cn = ConexionDB.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, c.getDni());
            ps.setString(2, c.getTelefono());
            ps.setInt(3, c.getClienteID());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizar cliente: "
                    + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminar(int id) {
        String sql = "UPDATE Usuarios SET Activo = 0 "
                   + "WHERE UsuarioID = "
                   + "(SELECT UsuarioID FROM Clientes "
                   + "WHERE ClienteID = ?)";

        try (Connection cn = ConexionDB.getConexion();
             PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error al desactivar cliente: "
                    + e.getMessage());
            return false;
        }
    }
    
    public boolean registrarConUsuario(Usuario u, Cliente c){
        Connection con = null;
        
        try{
            con = ConexionDB.getConexion();
            con.setAutoCommit(false);
            
            String sqlUsuario = "INSERT INTO Usuarios"
                    +"(NombreUsuario, Correo, ContrasenaHash, Rol)"
                    +"VALUES (?, ?, ?, ?)";
           
            
            int usuarioID;
            
            try (PreparedStatement ps = con.prepareStatement(
            sqlUsuario, PreparedStatement.RETURN_GENERATED_KEYS)){
                
                ps.setString(1, u.getNombreUsuario());
                ps.setString(2, u.getCorreo());
                ps.setString(3, Seguridad.generaHash(u.getContraseña()));
                ps.setString(4, "Cliente");
                
                
                ps.executeUpdate();
                
                try (ResultSet rs = ps.getGeneratedKeys()){
                    if(!rs.next()){
                        throw new SQLException("No se genero el ID del Usuario");
                    }
                    
                    usuarioID = rs.getInt(1);
                }
                
                
            }
            
            String sqlCliente = "INSERT INTO Clientes"
                    + "(UsuarioID, DNI, Telefono) VALUES (?, ?, ? )";
            
            try(PreparedStatement ps = con.prepareStatement(sqlCliente)){
                ps.setInt(1, usuarioID);
                ps.setString(2, c.getDni());
                ps.setString(3, c.getTelefono());
                ps.executeUpdate();
                
            }
            
            con.commit();
            return true;
            
        }catch (SQLException e){
            if (con != null){
            try {
                con.rollback();
            }catch (SQLException ex){
                System.out.println("Error al revertir : "+ ex.getMessage());
               }
            }
            System.out.println("Error al registrar: " + e.getMessage());
            e.printStackTrace();
            return false;
            
          }finally{
            if (con !=null){
                try{
                    con.setAutoCommit(true);
                    con.close();
                }catch (SQLException e) {
                    System.out.println("Error al cerrar conexion: " + e.getMessage());
                }
            }
        }   
            
    }
    
    public String buscarNombrePorID(int id) {

    String sql = "SELECT u.NombreUsuario "
               + "FROM Clientes c "
               + "INNER JOIN Usuarios u ON c.UsuarioID = u.UsuarioID "
               + "WHERE c.ClienteID = ? AND u.Activo = 1";
    
    try (Connection cn = ConexionDB.getConexion();
         PreparedStatement ps = cn.prepareStatement(sql)) {

        ps.setInt(1, id);

        try (ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return rs.getString("NombreUsuario");
            }
        }

    } catch (SQLException e) {
        System.out.println("Error al buscar nombre: " + e.getMessage());
    }

    return null;
    
}
    
    public Cliente buscarPorUsuarioID(int usuarioID) {

    String sql = "SELECT * FROM Clientes WHERE UsuarioID = ?";

    try (Connection cn = ConexionDB.getConexion();
         PreparedStatement ps = cn.prepareStatement(sql)) {

        ps.setInt(1, usuarioID);

        try (ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                Cliente c = new Cliente();

                c.setClienteID(rs.getInt("ClienteID"));
                c.setUsuarioID(rs.getInt("UsuarioID"));
                c.setDni(rs.getString("DNI"));
                c.setTelefono(rs.getString("Telefono"));

                return c;
            }
        }

    } catch (SQLException e) {
        System.out.println("Error al buscar cliente por usuario: "
                + e.getMessage());
    }

    return null;
    
    }
    
    
    
    
    
}
    

