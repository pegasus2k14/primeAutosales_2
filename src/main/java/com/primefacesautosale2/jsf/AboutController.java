package com.primefacesautosale2.jsf;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.event.TabChangeEvent;

@ManagedBean(name = "aboutController")
@SessionScoped
public class AboutController implements Serializable {
    
   //Constructor vacio
    public AboutController() {
    }
    
    public void tabChange(TabChangeEvent tce){
        //Creamos un Mensaje
        FacesMessage fm = new FacesMessage("Tab Cambiada","Tab Activa: " + tce.getTab().getTitle());
        //Lanzamos el mensaje
        FacesContext.getCurrentInstance().addMessage(null, fm);
    }
    
}
