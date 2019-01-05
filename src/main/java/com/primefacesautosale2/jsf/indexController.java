package com.primefacesautosale2.jsf;

import com.primefacesautosale2.dao.PrimeVisitorCountDao;
import com.primefacesautosale2.entity.PrimeVisitorCount;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.primefaces.event.ResizeEvent;


//@Named(value = "indexController")
@ManagedBean(name = "indexController")
@RequestScoped
public class indexController implements Serializable{
    
    //Propiedades
    private String tipoVehiculo;
    List<Vehiculo> listVehiculo;  
    private int tabIndexSelected; //Almacena el indice del Tab Seleccionado
    
    private PrimeVisitorCountDao primeVisitorCountDao;

   
    
    //Constructor
    public indexController() { 
       primeVisitorCountDao = new PrimeVisitorCountDao();
       setTipoVehiculo("CAR");
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
        //return "automovil";
        return "automovil?faces-redirect=true";
    }
    
    //Metodo que ofrece una segunda alternativa para Direccionar a la vista
    // 'automovil.xhtml'
    public void navegarAuto2() throws IOException{
        tipoVehiculo ="auto";
        FacesContext.getCurrentInstance().getExternalContext().redirect("automovil.xhtml");
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
    
    
    //Metodo para levantar el Dialog Framework
    public void invokePoll(){
        //Coleccion de tipo map para almacenar los atributos del Dialog
        Map<String, Object> options = new HashMap<String, Object>();
        //Agregamos items a la coleccion
        options.put("modal", true);
        options.put("headerElement", "Encuesta");
        options.put("draggable",false);
        options.put("height","100");
        
        RequestContext.getCurrentInstance().openDialog("/dialog/poll.xhtml",options,null);
    }
    
    
    //Metodo que abrira un componente Dialog en tiempo de ejecucion 
    //cuando el usuario selecciona YES en el 'p:confirmDialog'
    public void navigateToContinuar(){
        System.out.println("Aqui");
        RequestContext.getCurrentInstance().openDialog("/dialog/clearence.xhtml");
        
    }
    
    
    //Metodo para incrementar el contador de visitas
    public void incrementarCounter(){
        //invocamos al metodo que incrementa el contador de visitar
        //y almacena el nuevo valor en el registro correspondiente de la BD
        primeVisitorCountDao.incrementCounter();
    }
    
    //Metodo que devuelve el valor actual del atributo 'counter' de
    //PrimeVisitorCount
    public Integer getVisitorCounter(){
        //Devolvera siempre el valor del primer registro de la tabla (con id = 1)
        PrimeVisitorCount count = primeVisitorCountDao.findById(1);
        //Retornamos el valor del atributo Contador de visitas
        return count.getCount();
    }
    
    //Getter y Setters

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }
   
    //Agrgamos un Switch de seleccion a este metodo Setter
    public void setTipoVehiculo(String tipoVehiculo) {
        
        switch(tipoVehiculo){
            case "CAR":
                tabIndexSelected = 0;
            break;
            case "TRUCK":
                tabIndexSelected =  1;
            break;    
            case "SUV":
              tabIndexSelected = 2;
            break; 
            default:
                tabIndexSelected = 0;
            break;    
        }
        System.out.println("la pestaña seleccionada fue la: " + tabIndexSelected);
        
        this.tipoVehiculo = tipoVehiculo; 
    }

    public List<Vehiculo> getListVehiculo() {
        return llenarListaVehiculos();
    }

    public void setListVehiculo(List<Vehiculo> listVehiculo) {
        this.listVehiculo = listVehiculo;
    }

    public int getTabIndexSelected() {
        return tabIndexSelected;
    }

    public void setTabIndexSelected(int tabIndexSelected) {
        this.tabIndexSelected = tabIndexSelected;
    }
    
}
