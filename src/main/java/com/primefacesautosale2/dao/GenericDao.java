package com.primefacesautosale2.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class GenericDao {
    protected static EntityManager em;
    private static EntityManagerFactory emf = null;
    
    //Constructor
    public GenericDao(){
        if(emf == null){
            emf = Persistence.createEntityManagerFactory("primeAutosales2PU");
        }
    }
    
    //Metodo que devuelve una instancia de EntityMamanger
    protected EntityManager getEntityManager(){
        if(em == null){
            em = emf.createEntityManager();
        }
        return em;
    }
}
