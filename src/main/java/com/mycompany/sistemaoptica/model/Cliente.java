/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemaoptica.model;

/**
 *
 * @author Dell
 */
public class Cliente {
    
    private int clienteID;
    private int usuarioID;
    private String dni;
    private String telefono;
    
    public Cliente(){
        
    }
    
    public Cliente(int clienteID, int usuarioID,
            String dni, String telefono){
        this.clienteID = clienteID;
        this.usuarioID = usuarioID;
        this.dni = dni;
        this.telefono = telefono;
    }
    
    public int getClienteID(){
        return clienteID;
    }
    
    public void setClienteID(int clienteID){
        this.clienteID = clienteID;
    }
    
    public int getUsuarioID(){
        return usuarioID;
    }
    
    public void setUsuarioID(int usuarioID){
        this.usuarioID = usuarioID;
    }
    
    public String getDni(){
        return dni;
    }
    
    public void setDni(String dni){
        this.dni = dni;
    }
    
    public String getTelefono(){
        return telefono;
    }
    
    public void setTelefono(String telefono){
        this.telefono = telefono;
    }
}
