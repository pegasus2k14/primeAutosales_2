package com.primefacesautosale2.jsf;

import com.primefacesautosale2.dao.AutoQuoteDao;
import com.primefacesautosale2.dao.PrimeAutomobileDao;
import com.primefacesautosale2.entity.PrimeAutoQuote;
import com.primefacesautosale2.entity.PrimeAutomobile;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import org.primefaces.context.RequestContext;


@ManagedBean(name = "primeAutoQuoteController")
@ViewScoped
public class PrimeAutoQuoteController  implements Serializable{
    
    //Atributos
    private AutoQuoteDao autoQuoteDao;
    private PrimeAutomobileDao primeAutomobileDao;
    private PrimeAutoQuote current;
    private PrimeAutomobile primeModelSelected;
    private boolean option;
    private String testMessage;
    
    //Constructor vacio
    public PrimeAutoQuoteController() {
    }
    
    @PostConstruct
    public void init(){
        autoQuoteDao = new AutoQuoteDao();
        primeAutomobileDao = new PrimeAutomobileDao();
        primeModelSelected = new PrimeAutomobile();
    }
    
    
    //Metodo que retorna una lista de Marcas
    public List<String> autoCompleteMake(String text){
        System.out.println("El texto digitado fue: "+text);
        return autoQuoteDao.autoCompleteMake(text);
    }
    
    //Metodo que guarda la Cita en la BD
    public void saveQuote(){
        try{
            
            //Seteamos a 'current' el modelo seleccionado
            current.setModel(primeModelSelected.getModelo());
            //insertamos la cita
            autoQuoteDao.insertPrimeAutoQuote(current);
            
            //Bloque para crear un delay 
            Thread.sleep(5000);
            
            
            //re inicializamos la instancia de  'PrimeAutomobile'
            current = new PrimeAutoQuote();
            //Mensaje de confirmacion
           FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardado", "La cita se ha guardado!");
           FacesContext.getCurrentInstance().addMessage(null, message);
        }catch(Exception e){
            FacesMessage messageError = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Error", "La cita no pudo ser guardada");
            FacesContext.getCurrentInstance().addMessage(null, messageError);
            e.printStackTrace();
        }
    }
    
    
    //Metodo que retornara a la vista una lista de objetos 'SelectItem'
    //Con los cuales se llenara el componente 'SelectOneMenu'
    public List getAllModells(){
        //Lista generica a retornar
        List returnList = new ArrayList();
        try{
            //Recuperramos la lista de modelos
            List<String> listModels = primeAutomobileDao.obtaineAutomobileModel(current.getMake());
            
            //Recorremos la Lista de Strings
            for(String model : listModels){
                //Agregamos SelectedItems a la lista generica 
                returnList.add(new SelectItem(model, model));  //SelectItem(ObjectValue,Label)
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        //retornamos la Lista Generica que contiene los SelectItems
        return returnList;
        
    }
    
    //Metodo que retornara a la vista una lista de objetos 'PrimeAutomobile'
    //Con los cuales se llenara el componente 'SelectOneMenu'
    public List<PrimeAutomobile> getAllPrimeModells(){
        List<PrimeAutomobile> listPrimeModels = new ArrayList<>();
        try{
            //Recuperramos la lista de modelos
            listPrimeModels = primeAutomobileDao.obtainePrimeAutomobileModel(current);
           
        }catch(Exception e){
            e.printStackTrace();
        }
        //retornamos la Lista Generica que contiene los SelectItems
        return listPrimeModels;
        
    }
    
    
    public void reflectText(){
        //imprimimos en consola el valor del atributo 'FirstName' del
        //objeto Current
        System.err.println(this.current.getFirstName());
    }
    
    
    
    public void checkValid() throws InterruptedException{
        //Creamos objeto RequestContext
        RequestContext requestContext = RequestContext.getCurrentInstance();
        //Levantamos el componente Dialog de la vista 'autoQuote.xhtml'
        //mediante la invocacion a una funcion JavaScript
        requestContext.execute("PF('progressDialog').show();");
        
        //Agregamos un parametro a la devolucion de la llamada, este
        //parametro se pasara a la vista
        requestContext.addCallbackParam("newValue", true);
        testMessage = "Usuario Verificado..";
        
        Thread.sleep(3000);
        
         //Actualizando el componente de <h:outPutText> de la vista
        requestContext.update(":autoQuoteForm:testMensaje");
        
        //Ocultando el Dialog
        //requestContext.execute("PF('progressDialog').hide();");
    }
    
    
    //Metodo que devuelve un String con la URL relativa de la Segunda vista Mobile
    public String navigateToSecondMobile(){
        return "pm:second?transition=slideup";
    } 
    
    
    //Metodo para guardar una Cita y direccionar a la pagina con id 'success'
    public String saveQuoteMobile(){
        //invocamos al metodo que guarda la cita
        saveQuote();  
        
        //Retornamos una cadena con la pagina a la que se direccionara
        //y el efecto de transicion
        return "pm:success?transition=slideup";
    }
    
    
    
    
    //Getters y Setters

    public PrimeAutoQuote getCurrent() {
        //Su 'current' es NULL crearemos una nueva instancia
        if(current == null){
            current = new PrimeAutoQuote();
        }
        return current;
    }

    public void setCurrent(PrimeAutoQuote current) {
        this.current = current;
    }

    public PrimeAutomobile getPrimeModelSelected() {
        return primeModelSelected;
    }

    public void setPrimeModelSelected(PrimeAutomobile primeModelSelected) {
        this.primeModelSelected = primeModelSelected;
    }

    public boolean isOption() {
        return option;
    }

    public void setOption(boolean option) {
        this.option = option;
        System.err.println("La opcion ahora es: " + option);
    }

    public String getTestMessage() {
        return testMessage;
    }

    public void setTestMessage(String testMessage) {
        this.testMessage = testMessage;
    }
    
    
}
