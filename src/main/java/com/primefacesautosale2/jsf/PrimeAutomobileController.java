package com.primefacesautosale2.jsf;

import com.primefacesautosale2.dao.PrimeAutomobileDao;
import com.primefacesautosale2.entity.PrimeAutomobile;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean(name = "primeAutomobileController")
@RequestScoped
public class PrimeAutomobileController {
    //Atributos
    private List<PrimeAutomobile> listPrimeAutomobile;
    private PrimeAutomobileDao primeAutomobileDao;
    
   
    //Metodo que obtiene todos los Automobiles registrados
    public void loadAutomobiles(){
        primeAutomobileDao = new PrimeAutomobileDao();
        listPrimeAutomobile = primeAutomobileDao.getAllPrimeAutomobile();
    }
    
    //Getters and Setters

    public List<PrimeAutomobile> getListPrimeAutomobile() {
        return listPrimeAutomobile;
    }

    public void setListPrimeAutomobile(List<PrimeAutomobile> listPrimeAutomobile) {
        this.listPrimeAutomobile = listPrimeAutomobile;
    }
    
    
}
