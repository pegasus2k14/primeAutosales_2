package com.primefacesautosale2.dao;

import com.primefacesautosale2.entity.PrimeAutoQuote;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;

public class AutoQuoteDao extends GenericDao{
    public List<PrimeAutoQuote> getAllPrimeAutoQuote(){
        //Cadena con la Consulta
        String hql = "select a from PrimeAutoQuote a";
        List<PrimeAutoQuote> listPrimeAutoQuote = null;
        try{
            //obtenemos una instancia de EntityManager
            em = getEntityManager();
            //Creamos la consulta
            Query q = em.createQuery(hql);
            //ejecutamos la consulta
              listPrimeAutoQuote = q.getResultList();
        }catch(Exception e){
            e.printStackTrace();
        }
        return listPrimeAutoQuote;
    }
    
    //Metodo que retorna una instancia de 'PrimeAutoQuote' en base a su Id
    public PrimeAutoQuote findById(Integer id){
        PrimeAutoQuote auto = new PrimeAutoQuote();
        try{
            em=getEntityManager();
            auto = em.find(PrimeAutoQuote.class, id);
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return auto;
    }
    
    //Metodo que inserta una instancia de 'PrimeAutoQuote.java' en la BD
    public void insertPrimeAutoQuote(PrimeAutoQuote primeAutoQuote){
        try{
            //obtenemos una instancia de EntityManager
            em = getEntityManager();
            //iniciamos una transaccion
            em.getTransaction().begin();
            em.persist(primeAutoQuote);
            //confirmamos la transaccion
            em.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    //Metodo que modifica una instancia de 'PrimeAutoQuote.java' en la BD
    public void updatePrimeAutoQuote(PrimeAutoQuote primeAutoQuote){
        try{
            //obtenemos una instancia de EntityManager
            em = getEntityManager();
            //iniciamos una transaccion
            em.getTransaction().begin();
            em.merge(primeAutoQuote);
            //confirmamos la transaccion
            em.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
     //Metodo que elimina una instancia de 'PrimeAutoQuote.java' en la BD
    public void removetPrimeAutoQuote(PrimeAutoQuote primeAutoQuote){
        try{
            //obtenemos una instancia de EntityManager
            em = getEntityManager();
            //iniciamos una transaccion
            em.getTransaction().begin();
            em.remove(em.merge(primeAutoQuote));
            //confirmamos la transaccion
            em.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    //Metodo que devuelve una lista de Strings con las marcas de Automobil
    //Que coinciden de forma completa o en parte con el String que el metodo
    //recibe como parametro
    public List autoCompleteMake(String texto){
        //Sub cadena a pasar como parametro a la consulta JPQL
        //equivake a "%TEXTO%"
        System.out.println("texto: "+texto);
        String queryString = "%"+texto.toUpperCase()+"%";
        
        //Lista de Strings para almacenar los resultados
        List<String>  matches= new ArrayList<>();
        
        try{
            //Creamos la consulta 
            Query q = em.createQuery("select DISTINCT(p.make) from PrimeAutomobile p where UPPER(p.make) LIKE :make");
            //Seteamos parametros 
            q.setParameter("make", queryString);
            //Ejecutando la consulta
            matches = q.getResultList();
        }catch(Exception e){
            e.printStackTrace();
        }
        return matches;
    }
    
}
