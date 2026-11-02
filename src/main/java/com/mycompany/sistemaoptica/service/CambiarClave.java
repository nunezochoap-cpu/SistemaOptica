/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemaoptica.service;

/**
 *
 * @author Dell
 */
public class CambiarClave {
     public static void main(String[] args) {

        String hash = Seguridad.generaHash("Optica2026*");

        System.out.println("Nuevo hash:");
        System.out.println(hash);
    }
}
