/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemaoptica.dao;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Dell
 */
public interface HistorialVentaDAO {
    
    DefaultTableModel listarVentas();
    
    DefaultTableModel buscarPorCliente(String nombre);
    
    DefaultTableModel buscarPorFiltros(
        String nombre, String desde, String hasta);
    
    DefaultTableModel buscarDetalle(int ventaID);
    
    
}
