/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemaoptica.dao;

import com.mycompany.sistemaoptica.model.Medicion;
import java.util.List;
/**
 *
 * @author Dell
 */
public interface MedicionDAO {
    
    boolean insertar(Medicion medicion);
    
    List<Medicion> listarPorCliente(int clienteID);
    
}
