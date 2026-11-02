/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemaoptica.model;

import java.sql.Date;

/**
 *
 * @author Dell
 */
public class Medicion {
    private int medicionID;
    private int clienteID;
    private Date fecha;
    private double esferaOD;
    private double cilindroOD;
    private int ejeOD;
    private double esferaOI;
    private double cilindroOI;
    private int ejeOI;
    private double adicion;
    private String observaciones;
    
    public int getMedicionID(){
        return medicionID;
    }
    
    public void setMedicionID(int medicionID){
        this.medicionID = medicionID;
    }
    
    public int getClienteID(){
        return clienteID;
    }
    
    public void setClienteID(int clienteID){
        this.clienteID = clienteID;
    }
    
    public Date getFecha(){
        return fecha;
    }
    
    public void setFecha(Date fecha){
        this.fecha = fecha;
    }
    
    public double getEsferaOD(){
        return esferaOD;
    }
    
    public void setEsferaOD(double esferaOD){
        this.esferaOD = esferaOD;
    }
    
    public double getCilindroOD() {
    return cilindroOD;
    }
   
    public void setCilindroOD(double cilindroOD) {
    this.cilindroOD = cilindroOD;
    }
    
    
    public int getEjeOD(){
        return ejeOD;
    }
    
    public void setEjeOD(int ejeOD){
        this.ejeOD = ejeOD;
    }
    
     public double getEsferaOI(){
        return esferaOI;
    }
    
    public void setEsferaOI(double esferaOI){
        this.esferaOI = esferaOI;
    }
    
     public double getCilindroOI(){
        return cilindroOI;
    }
    
    public void setCilindroOI(double cilindroOI){
        this.cilindroOI = cilindroOI;
    }
    
     public int getEjeOI(){
        return ejeOI;
    }
    
    public void setEjeOI(int ejeOI){
        this.ejeOI = ejeOI;
    }
    
    public double getAdicion(){
        return adicion;
    }
    
    public void setAdicion(double adicion){
        this.adicion = adicion;
    }
    
    public String getObservaciones(){
        return observaciones;
    }
    
    public void setObservaciones(String observaciones){
        this.observaciones = observaciones;
    }
    
}
