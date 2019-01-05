package com.promefacesautosales2.bean;

import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.MenuModel;

public class MenuBean {
    //Variable de tipo MenuModel
    private MenuModel menuModel;
    
    //CONSTRUCTOR
    public MenuBean(){
        //invocamos al metodo que construye el MenuModel
        build();
    }
    
    
   //Metodo que configura el MenuModel
    public void build(){
        //Instanciamos el Menu Model
        menuModel = new DefaultMenuModel();
        //Creamos objeto MenuItem
        DefaultMenuItem boldItem = new DefaultMenuItem("Bold");
        
        //Configuramos el MenuItem
        boldItem.setCommand("#{messageCenterController.tipoMensaje eq 'W'}"); //Visibilidad
        boldItem.setUpdate("messageCenterBody");  //Actualiza al componente con id 'messageCenterBody'
        
        DefaultMenuItem italicItem = new DefaultMenuItem("Italic");
        italicItem.setCommand("#{messageCenterController.tipoMensaje eq 'W'}");
        italicItem.setUpdate("messageCenterBody");
        
        DefaultMenuItem underlineItem = new DefaultMenuItem("Underline");
        underlineItem.setCommand("#{messageCenterController.tipoMensaje eq 'W'}");
        underlineItem.setUpdate("messageCenterBody");
        
        //Agregamos los Items al Menu Model
        menuModel.addElement(boldItem);
        menuModel.addElement(italicItem);
        menuModel.addElement(underlineItem);
    }
    
    
    
    
    //Getters y Setters

    public MenuModel getMenuModel() {
        return menuModel;
    }

    public void setMenuModel(MenuModel menuModel) {
        this.menuModel = menuModel;
    }
    
}
