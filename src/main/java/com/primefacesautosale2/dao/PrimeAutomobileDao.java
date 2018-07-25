package com.primefacesautosale2.dao;

import com.primefacesautosale2.entity.PrimeAutomobile;
import java.util.List;
import javax.persistence.Query;

public class PrimeAutomobileDao extends GenericDao{
    public List<PrimeAutomobile> getAllPrimeAutomobile(){
        //Cadena con la Consulta
        String hql = "select a from PrimeAutomobile a";
        List<PrimeAutomobile> listPrimeAutomobile = null;
        try{
            //obtenemos una instancia de EntityManager
            em = getEntityManager();
            //Creamos la consulta
            Query q = em.createQuery(hql);
            //ejecutamos la consulta
              listPrimeAutomobile = q.getResultList();
        }catch(Exception e){
            e.printStackTrace();
        }
        return listPrimeAutomobile;
    }
    
    //Metodo que inserta una instancia de 'User.java' en la BD
    public void insertPrimeAutomobile(PrimeAutomobile primeAutomobile){
        try{
            //obtenemos una instancia de EntityManager
            em = getEntityManager();
            //iniciamos una transaccion
            em.getTransaction().begin();
            em.persist(primeAutomobile);
            //confirmamos la transaccion
            em.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    //Metodo que modifica una instancia de 'User.java' en la BD
    public void updatetUser(PrimeAutomobile primeAutomobile){
        try{
            //obtenemos una instancia de EntityManager
            em = getEntityManager();
            //iniciamos una transaccion
            em.getTransaction().begin();
            em.merge(primeAutomobile);
            //confirmamos la transaccion
            em.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
     //Metodo que elimina una instancia de 'User.java' en la BD
    public void removetUser(PrimeAutomobile primeAutomobile){
        try{
            //obtenemos una instancia de EntityManager
            em = getEntityManager();
            //iniciamos una transaccion
            em.getTransaction().begin();
            em.remove(em.merge(primeAutomobile));
            //confirmamos la transaccion
            em.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}


