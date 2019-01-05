package com.primefacesautosale2.jsf;

import com.primefacesautosale2.dao.PrimeAutomobileDao;
import com.primefacesautosale2.entity.PrimeAutomobile;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import org.primefaces.model.DualListModel;

@ManagedBean(name = "pickListController")
@RequestScoped
public class PickListController {
    //Atributos
      //Variable Lista Dual
    private DualListModel<PrimeAutomobile> listDualPrimeAutomobile;
    private List<PrimeAutomobile> listPrimeAutomobileSource;
    private List<PrimeAutomobile> listPrimeAutomobileTarget;
    
    private PrimeAutomobileDao primeAutomobileDao;
    private List<PrimeAutomobile>  listPrimeAutomobileSelected;
    
    //Metodo para cargar Los Objetos de la tabla 'prime_automobile'
    //en la Lista Origen
    public void populateDuaListModel(){
        primeAutomobileDao = new PrimeAutomobileDao();
        
        listPrimeAutomobileSource = new ArrayList<>();
        listPrimeAutomobileTarget = new ArrayList<>();
        try{
            //obtenemos todos los registros de la tabla de automobiles
            listPrimeAutomobileSource =  primeAutomobileDao.getAllPrimeAutomobile();
            //Agregando listas origen y destino a la lista dual
            listDualPrimeAutomobile = new DualListModel<>(listPrimeAutomobileSource, listPrimeAutomobileTarget);
        }catch(Exception e){
            e.printStackTrace();
        }
         
    }
    

    //Metodo que recuperra la lista 'listPrimeAutomobileTarget' de la lista Dual y se la 
    //pasa a la lista de automobiles seleccionados
    public void loadSelectedAutomobile(){
        try{
            listPrimeAutomobileSelected = listDualPrimeAutomobile.getTarget();
        }catch(Exception e){
            e.printStackTrace();
        }       
    }
    
    
    //Getter y Setters

    public DualListModel<PrimeAutomobile> getListDualPrimeAutomobile() {
        return listDualPrimeAutomobile;
    }

    public void setListDualPrimeAutomobile(DualListModel<PrimeAutomobile> listDualPrimeAutomobile) {
        this.listDualPrimeAutomobile = listDualPrimeAutomobile;
    }

    public List<PrimeAutomobile> getListPrimeAutomobileSelected() {
        return listPrimeAutomobileSelected;
    }

    public void setListPrimeAutomobileSelected(List<PrimeAutomobile> listPrimeAutomobileSelected) {
        this.listPrimeAutomobileSelected = listPrimeAutomobileSelected;
    } 
    
}     
