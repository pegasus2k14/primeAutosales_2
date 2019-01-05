package com.primefacesautosale2.jsf;

import javax.faces.application.FacesMessage;
import org.primefaces.push.annotation.OnMessage;
import org.primefaces.push.annotation.PushEndpoint;
import org.primefaces.push.impl.JSONEncoder;

//Recurso al que se suscriben los clientes
//indicamos el nombre del Canal
@PushEndpoint("/message")
public class MessageResource {
  
    
    //Metodo que recibe un objeto Mensaje y lo retorna
    //indicamos que la codificacion sera JSON
    @OnMessage(encoders = {JSONEncoder.class})
    public FacesMessage onMessage(FacesMessage message){
        return message;
    }
}
