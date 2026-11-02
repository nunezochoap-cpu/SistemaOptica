  /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemaoptica.service;

import com.mycompany.sistemaoptica.dao.ClienteDAO;
import com.mycompany.sistemaoptica.dao.impl.ClienteDAOImpl;
import com.mycompany.sistemaoptica.model.Cliente;
import java.util.List;

/**
 *
 * @author Dell
 */
public class ClienteService {
    
    private ClienteDAO clienteDAO = new ClienteDAOImpl();
    
    public boolean registrar(Cliente c){
        
        if (c.getUsuarioID() <= 0) {
        throw new IllegalArgumentException(
        "El ID del Usuario debe ser Valido.");
       
       }
    
        if (c.getDni() == null || c.getDni().trim().isEmpty()){
        throw new IllegalArgumentException(
        "Ingrese el DNI del Cliente.");
        
        }
        
        if (c.getTelefono() == null || c.getTelefono().trim().isEmpty()){
             throw new IllegalArgumentException(
             "Ingrese el telefono del cliente.");     
             
        }    
        
        return clienteDAO.insertar(c);
           
    }    
    
    public List<Cliente> listar(){
        return clienteDAO.listar();
        
    }
    
    public Cliente buscarPorID(int id){
        if (id <= 0){
            throw new IllegalArgumentException(
            "Seleccion un Cliente Valido.");
        }
        return clienteDAO.buscarPorID(id);
    }
    
    public boolean actualizar(Cliente c){
        
        if (c.getClienteID() <= 0) {
            throw new IllegalArgumentException(
            "Seleccion un cliente de la tabla.");
        }
        
        if (c.getDni()== null || c.getDni().trim().isEmpty()){
            throw new IllegalArgumentException(
            "El DNI es el obligatorio.");
        }
        
         if (c.getTelefono() == null
                || c.getTelefono().trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "El teléfono es obligatorio.");
        }
         
         return clienteDAO.actualizar(c);
        
    }
    
    public boolean elimanar(int id){
        if(id <= 0){
            throw new IllegalArgumentException(
            "Seleccione un cliente valido.");
        }
        
        return clienteDAO.eliminar(id);
    }
}
