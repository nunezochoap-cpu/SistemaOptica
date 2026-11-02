/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemaoptica.dao;

import com.mycompany.sistemaoptica.model.Cliente;
import java.util.List;

/**
 *
 * @author Dell
 */
public interface ClienteDAO {
    
    boolean insertar(Cliente cliente);
    
    Cliente buscarPorID(int clienteID);
    
    List<Cliente> listar();
    
    boolean actualizar(Cliente cliente);
    
    boolean eliminar(int clienteID); 
}
