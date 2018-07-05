package com.primefacesautosale2.jsf;

import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import org.primefaces.event.ResizeEvent;


//@Named(value = "indexController")
@ManagedBean(name = "indexController")
@RequestScoped
public class indexController {
    
    //Propiedades
    private String tipoVehiculo;
    List<Vehiculo> listVehiculo;  

   
    public indexController() {
    }
    
    //Metodo que vigila el evento de cambio de tamaño de una region de la vista
    public void layoutResizeEvent(ResizeEvent event){
        //Escribimos en consola
        System.out.println("El tamaño del layout ha sido cambiado "+ event.getComponent());
    }
    
    //Metodos que modifican el valor de la variable 'tipoVehiculo'
    //y retornan un Strin con el nombre de la pagina a la cual se direccionara
    public String navegarAuto(){
        tipoVehiculo ="auto";
        return "automovil";
    }
    
    public String navegarPickup(){
        tipoVehiculo = "pickup";
        return "automovil";
    }
    
    public String navegarSuv(){
        tipoVehiculo = "suv";
        return "automovil";
    }
    
    //Metodo que crea un conjunto de objetos 'Vehiculo' y los agrega a la Lista
    public List<Vehiculo> llenarListaVehiculos(){
        Vehiculo v1= new Vehiculo();
        v1.setMarca("Toyota");
        v1.setModelo("Turbo");
        Vehiculo v2 = new Vehiculo();
        v2.setMarca("Toyota");
        v2.setModelo("Rocket");
        Vehiculo v3 = new Vehiculo();
        v3.setMarca("Nissan");
        v3.setModelo("Jet");
        
        //Llennando la lista de Vehiculos
        listVehiculo = new ArrayList();
        listVehiculo.add(v1);
        listVehiculo.add(v2);
        listVehiculo.add(v3);
        
        return listVehiculo;
    }
    
    //Getter y Setters

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public List<Vehiculo> getListVehiculo() {
        return llenarListaVehiculos();
    }

    public void setListVehiculo(List<Vehiculo> listVehiculo) {
        this.listVehiculo = listVehiculo;
    }
    
    
    
}
