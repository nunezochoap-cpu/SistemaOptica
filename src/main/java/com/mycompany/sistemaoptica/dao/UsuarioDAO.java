/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemaoptica.dao;

import com.mycompany.sistemaoptica.model.Usuario;

/**
 *
 * @author Dell
 */
public interface UsuarioDAO {
    
    
    Usuario buscarPorCorreo(String correo);
    
    boolean insertar(Usuario usuario);
    
    
    
}
