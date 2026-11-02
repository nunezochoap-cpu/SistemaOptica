/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemaoptica.model;

/**
 *
 * @author Dell
 */
public class Producto {
    
    private int productoID;
    private String nombre;
    private String tipo;
    private String marca;
    private String material;
    private String forma;
    private double precio;
    private int stock;
    private boolean activo;
    
    
    public Producto(){
     
    }
    
    public int getProductoID(){
        return productoID;
    }
    
    public void setProductoID(int productoID){
        this.productoID = productoID;
        
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    public String getTipo(){
        return tipo;
    }
    
    public void setTipo(String tipo){
        this.tipo = tipo;
    }
    
    public String getMarca(){
        return marca;
    }
    
    public void setMarca(String marca){
        this.marca = marca;
    }
    
    public String getMaterial(){
        return material; 
    }
    public void setMaterial(String material){
        this.material = material;
    }
    
    public String getForma(){
        return forma;
    }
      
    public void setForma(String forma){
        this.forma = forma;
    }
    
    public double getPrecio(){
        return precio;
    }
    
    public void setPrecio(double precio){
        this.precio = precio;
    }
    
    public int getStock(){
        return stock;
    }
    
    public void setStock(int stock){
        this.stock = stock;
    }
    
    public boolean isActivo(){
        return activo;
    }
    
    public void setActivo(boolean activo){
        this.activo = activo;
    }
    
    @Override
    public String toString(){
        return nombre;
    }
}
