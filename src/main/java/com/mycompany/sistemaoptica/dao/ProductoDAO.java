/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemaoptica.dao;

import com.mycompany.sistemaoptica.model.Producto;
import java.util.List;

/**
 *
 * @author Dell
 */
public interface ProductoDAO {
    
    boolean insertar(Producto producto);
    
    List<Producto> listar();
    
    Producto buscarPorID(int productoID);
    
    boolean actualizar(Producto producto);
    
    boolean eliminar(int productoID);
    
    List<Producto> listarStockBajo();
}
