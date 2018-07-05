package com.primefacesautosale2.jsf;

public class Vehiculo {
  private String marca;
  private String modelo;
  private String descripcion;
  
   public Vehiculo(){
       
   }
   
   //Getters y Setters

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
   
}
