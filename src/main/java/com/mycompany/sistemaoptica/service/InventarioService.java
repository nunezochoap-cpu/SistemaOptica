/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemaoptica.service;

import com.mycompany.sistemaoptica.dao.ProductoDAO;
import com.mycompany.sistemaoptica.dao.impl.ProductoDAOImpl;
import com.mycompany.sistemaoptica.model.Producto;
import java.util.List;
/**
 *
 * @author Dell
 */
public class InventarioService {
    
    private ProductoDAO productoDAO;
    
    public InventarioService(){
        productoDAO = new ProductoDAOImpl();
        
    }
    
    public boolean registrar(Producto p) {
        
        if (p.getNombre() == null || p.getNombre().trim().isEmpty()){
            throw new IllegalArgumentException(
            "Ingrese el Nombre del Producto.");
        }
        
        if (p.getTipo() == null
                || !(p.getTipo().equals("ARO")
                || p.getTipo().equals("LUNA")
                || p.getTipo().equals("ACCESORIO"))){
            throw new IllegalArgumentException(
            "Seleccione un tipo de Producto valido.");
        }
        
        if (p.getPrecio() < 0 || p.getStock() < 0){
            throw new IllegalArgumentException("El precio y el Stock no pueden ser negativos.");
        }
        
        return productoDAO.insertar(p);
        
        
    }
    
       public List<Producto> listar(){
           return productoDAO.listar();
       }
       
       public boolean actualizar(Producto p){
           
           if(p.getProductoID() <= 0 ){
               throw new IllegalArgumentException(
               "Seleccione un producto valido.");
           }
           
           if (p.getNombre() == null || p.getNombre(). trim().isEmpty()){
               throw new IllegalArgumentException(
               "El Nombre es obligatorio.");
           }
           
           if(p.getPrecio() < 0 || p.getStock() < 0) {
               throw new IllegalArgumentException(
               "Precio o stock Invalido.");
           }
           
           return productoDAO.actualizar(p);
       }
       
       public boolean eliminar(int id){
           
           if (id <= 0){
               throw new IllegalArgumentException(
               "Seleccionar un Producto.");
               
           }
           
           return productoDAO.eliminar(id);
       }
         
       public List<Producto> stockBajo(){
           return productoDAO.listarStockBajo();
       }
}
