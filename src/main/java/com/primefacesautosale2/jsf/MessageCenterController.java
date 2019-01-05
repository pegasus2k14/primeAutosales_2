package com.primefacesautosale2.jsf;

import com.promefacesautosales2.bean.MenuBean;
import java.awt.Menu;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;

@ManagedBean(name = "messageCenterController")
@SessionScoped
public class MessageCenterController  implements Serializable{
    //Variables
    private String tipoMensaje;
    private String broadcastMessage;
    static final String CHANNEL ="/message"; //variable con el nombre del canal
    
    //Constante
    private static final String MESSAGE_WIKI ="W";
    private static final String MESSAGE_MAIL ="M";
    
    //Instancia de MenuBean
    private MenuBean menuBean = new MenuBean();
    
//Constructor vacio
    public void MessageCenterController(){
        
    }
    
    //Metodo de edicion
    //en este caso conectaremos todos los elementos de nuestra vista 'messageCenter.xhtml'
    //para que se muestre la opcion de menu que fue seleccionada
    public void edit(String accion){
        String mensaje = "Accion invocada: "+accion;
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje, mensaje);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

//Metodo de cierre de Session
    public void logout(){
        String mensaje = "Session Finalizada";
        //Creamos y lanzamos el mensaje
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, mensaje, mensaje);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    
     //Metodo que lanza la notificacion PUSH, este añade el mensaje recibido
    //al EventBus
      public void broadcastMessageAction(){
          //Creamos el bjeto 'EventBus'
          //con este creamos el canal de comunicacion de nuestra red
          EventBus eventBus = EventBusFactory.getDefault().eventBus();
          eventBus.publish(CHANNEL, new FacesMessage(broadcastMessage,broadcastMessage));
          broadcastMessage = new String();
      }
    
    
    //Getters y Setters

    public String getTipoMensaje() {
        return tipoMensaje;
    }

    public void setTipoMensaje(String tipoMensaje) {
        this.tipoMensaje = tipoMensaje;
    }

    public MenuBean getMenuBean() {
        return menuBean;
    }

    public void setMenuBean(MenuBean menuBean) {
        this.menuBean = menuBean;
    }

    public String getBroadcastMessage() {
        return broadcastMessage;
    }

    public void setBroadcastMessage(String broadcastMessage) {
        this.broadcastMessage = broadcastMessage;
    }
    
          
}
