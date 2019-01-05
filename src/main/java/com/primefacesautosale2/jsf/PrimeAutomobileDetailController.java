package com.primefacesautosale2.jsf;

import com.primefacesautosale2.dao.PrimeAutomobileDao;
import com.primefacesautosale2.entity.PrimeAutomobile;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;


@ManagedBean(name = "primeAutomobileDetailController")
@ViewScoped
public class PrimeAutomobileDetailController implements Serializable{
    //Atributos
    private PrimeAutomobileDao primeAutomobileDao;
    private PrimeAutomobile primeSelected;

   
    public PrimeAutomobileDetailController() {
        primeSelected = new PrimeAutomobile();
    }
    
    @PostConstruct
    public void init(){
        primeAutomobileDao = new PrimeAutomobileDao();
        
    } 
    
    
    //Metodo que retorna una lista de objetos 'PrimeAutomobile' cuyo
    //atributo 'modelo' coincide con el texto digitado en el 
    //componente 'autocomplete'
    public List<PrimeAutomobile> completeDetail(String modelo){
        try{
            return primeAutomobileDao.autoCompleteModel(modelo);
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
    
    //Metodo que lanza un mensaje de confirmacion
    public void sendDetailMessage(){
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, 
                "Detalle modelo "+primeSelected.getModelo(), 
                "Automovil "+primeSelected.getMake()+" "+primeSelected.getYearMade()));
    }
    
    
    //GETTERS Y SETTERS

    public PrimeAutomobile getPrimeSelected() {
        return primeSelected;
    }

    public void setPrimeSelected(PrimeAutomobile primeSelected) {
        this.primeSelected = primeSelected;
    }
    
    
}
