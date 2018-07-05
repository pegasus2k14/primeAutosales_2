package com.primefacesautosale2.jsf;

import com.primefacesautosale2.dao.UserDao;
import com.primefacesautosale2.entity.User;
import com.promefacesautosales2.bean.LazyUserModel;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.FlowEvent;
import org.primefaces.event.RowEditEvent;


@ManagedBean(name = "userRegistrationController")
@SessionScoped
public class UserRegistrationController {
    
   //Variables
    private User current = new User();
    private UserDao userDao;
    //Variable tipo Lista de Usuarios
    private List<User> listUser;
    
    //Variable de tipo LazyUserModel
    private LazyUserModel lazyUserModel;
    
    //Constructor vacio
    public UserRegistrationController() {
    }
    
    
    //Metodo que permite controlar el Flujo de las pestañas el cual se saltara a la
    //tercera pestaña si NO  se habilito el 'checkbox' de notificaciones en la primera
    //pestaña
    //En caso contrario se pasara a la segunda pestaña
    public String flowHandler(FlowEvent event){
        //Si existe la instancia de 'User'
        if(current != null){
            //obtenemos el valor de su propiedad 'enableNotifications'
            //y este NO es TRUE (NO Se selecciono el Checkbox)
            if(!current.getEnableNotifications()){
                //Indicamos que se pase a la tercera pestaña (con id 'confirm')
                return "confirm";
            }else{ //en caso contrario se pasa a la siguiente pestaña (la segunda en este caso)
             return event.getNewStep();
            }
        }else{
            return event.getNewStep();
        }
    }
    
    
//Metodo para agregar un nuevo Usuario a la BD
 public void createUser(){
     userDao = new UserDao();
     try{
         userDao.insertUser(current);
         this.current = new User();
         //Creamos y agregamos un mensaje al Contexto
         FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO
                 , "Usuario guardado con exito!", "Usuario guardado con Exito!"));
     }catch(Exception e){
         e.printStackTrace();
     }
 }    
 
 //Metodo que carga los registros de la tabla  de Usuarios en la lista
 public String populateUser() {
        userDao = new UserDao();
        try {
            System.out.println("Aqui...");
           this.listUser = new ArrayList<>();
           this.listUser = userDao.getAllUsers();
            //lazyUserModel = new LazyUserModel(this.listUser);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
     
     
     //retornamos una cadena con el nombre de la vista donde se desplegaran los usuarios
     return "/admin/currentUsers";
 }
 
 
 
 //metodo para persistir los cambios realizados en el DataTable
 public void rowEditAction(RowEditEvent event){
     //a partir del evento obtenemos la instancia del objeto que se modifico en
     //El DataTable
     User user = (User) event.getObject();
     //Persistimos los cambios en la BD
     userDao.updatetUser(user);
     //Mensaje de confirmacion
        FacesMessage message = new FacesMessage("Registro modificado con Exito!!");
        FacesContext.getCurrentInstance().addMessage(null, message);
 }
 
 
 
 
 //metodo que persistira los eventos de edicion de celda de un registro en el DataTable
 //Sobre la BD
 public void cellEditAction(CellEditEvent event){
     //obtenemos el valor nuevo y anterior de la celda
     Object oldValue = event.getOldValue();
     Object newValue = event.getNewValue();
     
     //si el nuevo valor no es nulo y es distinto al anterior
     if(newValue != null && (!newValue.equals(oldValue))){
         //obtenemos el nombre de la columna de DataTable que fue modificada
         String columName =event.getColumn().getHeaderText();
         //obteniendo la instancia de la Entidad que va a ser modificada
         FacesContext context = FacesContext.getCurrentInstance();
         User user = context.getApplication().evaluateExpressionGet(context, "#{userItem}", User.class);
         
         if("First Name".equals(columName)){
             user.setFirstname(newValue.toString());
         }else if("Last Name".equals(columName)){
             user.setLastname(newValue.toString());
         }else if("Age".equals(columName)){
             user.setAge(Integer.parseInt(newValue.toString()));
         }else if("Email".equals(columName)){
             user.setEmail(newValue.toString());
         }
         
         try {
             //Persistimos los cambios en la BD
             userDao.updatetUser(user);
             //Mensaje de confirmacion
             FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, 
                     "Registro modificado", "Registro modificado con exito!!");
             FacesContext.getCurrentInstance().addMessage(null, message);
         } catch (Exception e) {
             FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
                     "Registro no modificado","Ningun registro ha sido modificado");
             FacesContext.getCurrentInstance().addMessage(null, message);
         }
     }
       
 }
 
//Getter y Setters
    
    //Metodo que devuelve una instancia de 'User'

    public User getCurrent() {
        return current;
    }

    public void setCurrent(User current) {
        this.current = current;
    }

    public List<User> getListUser() {
        return listUser;
    }

    public void setListUser(List<User> listUser) {
        this.listUser = listUser;
    }

    public LazyUserModel getLazyUserModel() {
        return lazyUserModel;
    }

    public void setLazyUserModel(LazyUserModel lazyUserModel) {
        this.lazyUserModel = lazyUserModel;
    }

    
            
}



