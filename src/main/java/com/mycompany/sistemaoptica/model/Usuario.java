/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemaoptica.model;

/**
 *
 * @author Dell
 */
public class Usuario {
    
    private int usuarioID;
    private String nombreUsuario;
    private String correo;
    private String contraseña;
    private String rol;
    
    public Usuario(){
    }
    
    public Usuario(int usuarioID, String nombreUsuario, String correo, String contraseña, String rol){
        this.usuarioID = usuarioID;
        this.nombreUsuario = nombreUsuario;
        this.correo = correo;
        this.contraseña = contraseña;
        this.rol = rol;
    }
    
    public int getUsuarioID(){
        return usuarioID;
    }
    
    public void setUsuarioID(int usuarioID){
        this.usuarioID = usuarioID;
    }
    
    public String getNombreUsuario(){
        return nombreUsuario;
    }
    
    public void setNombreUsuario(String nombreUsuario){
        this.nombreUsuario = nombreUsuario; 
    }
    
    public String getCorreo(){
        return correo;
    }
    
    public void setCorreo(String correo){
        this.correo = correo;
    }
    
    public String getContraseña (){
        return contraseña;
    }
    
    public void setContraseña(String Contraseña){
        this.contraseña = Contraseña;
    }
    
    public String getRol(){
        return rol;
    }
    
    public void setRol(String rol){
        this.rol = rol;
    }
    
}
