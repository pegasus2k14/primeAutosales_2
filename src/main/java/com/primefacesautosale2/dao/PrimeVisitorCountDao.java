package com.primefacesautosale2.dao;

import com.primefacesautosale2.entity.PrimeVisitorCount;
import java.io.Serializable;
import javax.persistence.Query;

public class PrimeVisitorCountDao extends GenericDao {
    
    //Metodo que inserta una instancia de 'PrimeVisitorCount.java' en la BD
    public void insertPrimeVisitorCount(PrimeVisitorCount visitorCount){
        try{
            //obtenemos una instancia de EntityManager
            em = getEntityManager();
            //iniciamos una transaccion
            em.getTransaction().begin();
            em.persist(visitorCount);
            //confirmamos la transaccion
            em.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    //Metodo que modifica una instancia de 'User.java' en la BD
    public void updatePrimeVisitorCount(PrimeVisitorCount visitorCount){
        try{
            //obtenemos una instancia de EntityManager
            em = getEntityManager();
            //iniciamos una transaccion
            em.getTransaction().begin();
            em.merge(visitorCount);
            //confirmamos la transaccion
            em.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
     //Metodo que elimina una instancia de 'User.java' en la BD
    public void removetPrimeVisitorCount(PrimeVisitorCount visitorCount){
        try{
            //obtenemos una instancia de EntityManager
            em = getEntityManager();
            //iniciamos una transaccion
            em.getTransaction().begin();
            em.remove(em.merge(visitorCount));
            //confirmamos la transaccion
            em.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    //Metodo que incrementa el valor del atributo 'count' de PrimeVisitorCount
    public void incrementCounter(){
        String consulta = "update PrimeVisitorCount c set c.count = c.count + 1";
        
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            //Creamos la consulta 
            Query q = em.createQuery(consulta);
            //Ejecutando la consulta
            q.executeUpdate();
           em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    //Medodo que devuelva una instancia de PrimeVisitorCount en vase al id indicado
    public PrimeVisitorCount findById(Integer id){
        PrimeVisitorCount count = new PrimeVisitorCount();
        try{
            em = getEntityManager();
            
            count = em.find(PrimeVisitorCount.class, id);
            em.refresh(count); 
            //em.getEntityManagerFactory().getCache().evictAll();
        }catch(Exception e){
            e.printStackTrace();
        }
        return count;
    }
    
    
}


