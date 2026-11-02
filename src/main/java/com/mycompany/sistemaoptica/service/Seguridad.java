/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemaoptica.service;

import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
        

/**
 *
 * @author Dell
 */
public class Seguridad {
    
    public static String generaHash(String contraseña ){
     try {
      byte[]sal = new byte[16];
        SecureRandom random = new SecureRandom();
        random.nextBytes(sal);
        
        KeySpec spec = new PBEKeySpec(
        contraseña.toCharArray(),
        sal,
        120000,
        256
        );
        
        SecretKeyFactory fabrica = 
                SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        
        byte[] hash = fabrica.generateSecret(spec).getEncoded();
        
        return Base64.getEncoder().encodeToString(sal)
                + ":" + Base64.getEncoder().encodeToString(hash);
        
         }catch(Exception e ){
          throw new IllegalStateException(
             "No se pudo proteger la contraseña. ",e
          );
       }
    }
    
    public static boolean verificarContraseña(
        String contraseña, String datoGuardado){
        try{
            String[] partes = datoGuardado.split(":");
            
            if (partes.length != 2){
            return false;
            
           
            }
            byte[]sal = Base64.getDecoder().decode(partes[0]);
            byte[]hashGuardado = Base64.getDecoder().decode(partes[1]);
            
            KeySpec spec = new PBEKeySpec(
                    contraseña.toCharArray(),
                    sal,
                    120000,
                    256
            );
            
            SecretKeyFactory fabrica = 
                    SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            
            byte[]hashCalculado = fabrica.generateSecret(spec).getEncoded();
            
            if(hashGuardado.length != hashCalculado.length){
                return false;
            }
            
            int diferencia = 0;
            
            for (int i = 0; i < hashGuardado.length; i++){
                diferencia |= hashGuardado[i] ^ hashCalculado[i];
            }
            
            return diferencia == 0;
           
        }catch (Exception e){
            return false;
        }
    }
}
