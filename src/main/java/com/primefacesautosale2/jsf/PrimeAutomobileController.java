package com.primefacesautosale2.jsf;

import com.primefacesautosale2.dao.PrimeAutomobileDao;
import com.primefacesautosale2.entity.PrimeAutomobile;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.primefaces.model.DualListModel;

@ManagedBean(name = "primeAutomobileController")
@SessionScoped
public class PrimeAutomobileController {
    //Atributos
    private List<PrimeAutomobile> listPrimeAutomobile;
    private PrimeAutomobileDao primeAutomobileDao;
    
    //Modelo de Lista en la cual se depositaran las dos listas que usa el componente
    //picklist
    private DualListModel<String> listAutomobiles;
    
    
    //Lista para los nombres de las imagenes
    private List<String> sedanImages;
    
     
        //Variables Booleanas
        private boolean renderMake = true;
        private boolean renderModel = true;
        private boolean renderYear = true;
        private boolean renderDescription = true;
        
   
    //Metodo que obtiene todos los Automobiles registrados
    public void loadAutomobiles(){
        primeAutomobileDao = new PrimeAutomobileDao();
        listPrimeAutomobile = primeAutomobileDao.getAllPrimeAutomobile();
        
        //instanciamos la lista de nombres de Imagenes
        sedanImages = new ArrayList<>();
        sedanImages.add("sedans/sedan1.jpg");
        sedanImages.add("sedans/sedan2.jpeg");
        sedanImages.add("sedans/sedan3.png");
        System.out.println("Yegaste Aqui");
    }
    
    //Metodo para poblar la Lista de origen
    public void populateAutomobileFieldList(){
        System.out.println("Entraste aqui");
        //Lista de origen
        List<String> automobileFieldSource = new ArrayList<>();
        //Lista de destino
        List<String> automobileFieldTarget = new ArrayList<>();
        
        //Agregando items a la lsta de origen
        automobileFieldSource.add("Marca");
        automobileFieldSource.add("Modelo");
        automobileFieldSource.add("Año");
        automobileFieldSource.add("Descripcion");
       
        
        //Steamos a la variable 'DualistModel' un objeto del mismo tipo al
        //cual se le pasan como parametros a su constructor las listade de 
        // 'origen' y 'destino'
        setListAutomobiles(new DualListModel<String>(automobileFieldSource,automobileFieldTarget));
    }
    
    
    //Metodo que en base a los items seleccionados en el PickList cambiara el
    //valor de las variable Booleanas
    public void submitSelecion(){
        //Recuperamos la lista destino de la lista Dual
        List<String> selectionList = listAutomobiles.getTarget();
        
        if(selectionList.contains("Marca")){
            renderMake=false;
        }else{
            renderMake = true;
        }
        if(selectionList.contains("Modelo")){
            renderModel=false;
        }else{
            renderModel = true;
        }
        if(selectionList.contains("Año")){
            renderYear=false;
        }else{
            renderYear = true;
        }
        if(selectionList.contains("Descripcion")){
            renderDescription=false;
        }else{
            renderDescription = true;
        }
        loadAutomobiles();
    }
    
    
    
   
    
    //Getters and Setters

    public List<PrimeAutomobile> getListPrimeAutomobile() {
        return listPrimeAutomobile;
    }

    public void setListPrimeAutomobile(List<PrimeAutomobile> listPrimeAutomobile) {
        this.listPrimeAutomobile = listPrimeAutomobile;
    }

    public DualListModel<String> getListAutomobiles() {
        return listAutomobiles;
    }

    public void setListAutomobiles(DualListModel<String> listAutomobiles) {
        this.listAutomobiles = listAutomobiles;
    }

    public boolean isRenderMake() {
        return renderMake;
    }

    public boolean isRenderModel() {
        return renderModel;
    }

    public boolean isRenderYear() {
        return renderYear;
    }

    public boolean isRenderDescription() {
        return renderDescription;
    }

    public List<String> getSedanImages() {
        return sedanImages;
    }
}
