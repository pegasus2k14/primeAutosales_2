package com.primefacesautosale2.dao;

import com.primefacesautosale2.entity.PrimeAutoQuote;
import com.primefacesautosale2.entity.PrimeAutomobile;
import java.util.ArrayList;
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
    
    //Metodo que retorna una instancia de 'PrimeAutomobile' en base a su Id
    public PrimeAutomobile findById(Integer id){
        PrimeAutomobile auto = new PrimeAutomobile();
        try{
            em=getEntityManager();
            auto = em.find(PrimeAutomobile.class, id);
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return auto;
    }
    
    //Metodo que inserta una instancia de 'PrimeAutomobile.java' en la BD
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
    
    
    //Metodo que modifica una instancia de 'PrimeAutomobile.java' en la BD
    public void updatePrimeAutomobile(PrimeAutomobile primeAutomobile){
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
    
    
     //Metodo que elimina una instancia de 'PrimeAutomobile.java' en la BD
    public void removetPrimeAutomobile(PrimeAutomobile primeAutomobile){
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
    
    
//Metodo que devuelve todos Las Entity cuyo valor de su atributo 'modelo'
//coincide completamente o en parte con la cadena digitada en el 
//componente <p:autocomplete/> de la vista
public List<PrimeAutomobile> autoCompleteModel(String texto) {
        //Creamos cadenna que servira como parametro de la consulta
        String queryString = "%" + texto.toUpperCase() + "%";
        
        List<PrimeAutomobile> matches = new ArrayList<>();

        try {
            //Creamos la consulta 
            Query q = em.createQuery("select DISTINCT(P) from PrimeAutomobile p WHERE UPPER(p.modelo) LIKE :model");
            //Seteamos el parametro
            q.setParameter("model", queryString);
            //Ejcutamos la consulta
            matches = q.getResultList();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return matches;
    }


   
//Metodo que retorna una lista de Strings con los modelos de automobile
//en base a la marca recibida como parametro
public List<String> obtaineAutomobileModel(String marca) {
        List<String> listModelos = new ArrayList<>();

        try {
            if (marca != null && marca.length() > 1) {
                //String con la consulta
                String hql = "select p.modelo from PrimeAutomobile p where p.make =:marca";
                //Se crea la consulta
                Query q = em.createQuery(hql);
                //pasando parametros a la consulta
                q.setParameter("marca", marca);
                //ejecutando la consulta
                listModelos = q.getResultList();
            } else {
                String hql = "select p.modelo from PrimeAutomobile p";
                //Se crea la consulta
                Query q = em.createQuery(hql);
                //ejecutando la consulta
                listModelos = q.getResultList();
            }

            //Creando la consulta 
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listModelos;
    }

//Metodo que retorna una lista de de objetos PrimeAutomobile 
//cuyo valor del atributo 'marca' se corresponda con el parametro recibido 
public List<PrimeAutomobile> obtainePrimeAutomobileModel(PrimeAutoQuote primeAuto) {
        List<PrimeAutomobile> listModelos = new ArrayList<>();

        try {
            if (primeAuto != null && primeAuto.getMake() != null) {
                    //String con la consulta
                    String hql = "select p from PrimeAutomobile p where p.make =:marca";
                    //Se crea la consulta
                    Query q = em.createQuery(hql);
                    //pasando parametros a la consulta
                    q.setParameter("marca", primeAuto.getMake());
                    //ejecutando la consulta
                    listModelos = q.getResultList();
            } else {
                String hql = "select p from PrimeAutomobile p";
                //Se crea la consulta
                Query q = em.createQuery(hql);
                //ejecutando la consulta
                listModelos = q.getResultList();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listModelos;
    }
}


