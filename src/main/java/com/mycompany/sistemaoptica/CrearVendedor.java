/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.sistemaoptica;

import com.mycompany.sistemaoptica.dao.impl.UsuarioDAOImpl;
import com.mycompany.sistemaoptica.model.Usuario;

/**
 *
 * @author Dell
 */
public class CrearVendedor {

    public static void main(String[] args) {
       

        Usuario u = new Usuario();

        u.setNombreUsuario("Vendedor Optica");
        u.setCorreo("vendedor@optica.com");
        u.setContraseña("Vendedor2026*");
        u.setRol("Vendedor");

        UsuarioDAOImpl dao = new UsuarioDAOImpl();

        if (dao.insertar(u)) {
            System.out.println("Vendedor registrado correctamente.");
        } else {
            System.out.println("No se pudo registrar el vendedor.");
        }
    }
}
    

