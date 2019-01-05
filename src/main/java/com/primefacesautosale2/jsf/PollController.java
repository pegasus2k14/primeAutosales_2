package com.primefacesautosale2.jsf;

import com.primefacesautosale2.pojo.Poll;
import java.util.HashMap;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

@ManagedBean(name = "pollController")
@ViewScoped
public class PollController {
    //variable de tipo 'Poll.java'
    private Poll current;
    
    //Metodo para abrir y configurar los atributos del Dialog
     public void invokePoll(){
        //Coleccion de tipo map para almacenar los atributos del Dialog
        Map<String, Object> options = new HashMap<String, Object>();
        //Agregamos items a la coleccion
        options.put("modal", true);
        options.put("headerElement", "Encuesta");
        options.put("draggable",false);
        
        RequestContext.getCurrentInstance().openDialog("/dialog/poll.xhtml",options,null);
    }
     
     
     //Metodo que Cerrara el Dialog y pondra el objeto de tipo 'Poll.java' a nulo
     public void submitPoll(){
         //Cerramos el Dialog
         //El metodo 'closeDialog()' recibe como parametro el objeto que contiene
         //la data del formulario
         RequestContext.getCurrentInstance().closeDialog(current);
         current = null;
     }
     
     
     //Metodo en que recuperamos el objeto Poll de la vista y procesa los datos de la encuesra
     public void handleReturn(SelectEvent event){
        Poll poll =(Poll) event.getObject();
         System.out.println("Resultado de la encuesta: "+poll.getRespuesta1()+" - "+poll.getRespuesta2()+" - "+poll.getRespuesta3());
     }
     
     //Getter y Setters

    public Poll getCurrent() {
        //Si current es distinto de null lo instanciamos de nuevo
        if(current == null){
            current = new Poll();
        }
        return current;
    }

    public void setCurrent(Poll current) {
        this.current = current;
    }
    
}
