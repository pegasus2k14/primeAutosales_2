package com.primefacesautosale2.dao;

import com.primefacesautosale2.entity.Ventasdemo;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;

public class VentaDemoDao extends GenericDao{
   //Metodo para obtener todas las instancias de 'VentasDemo.java' que hay en la BD
    public List<Ventasdemo> getAllVentasDemo(){
        //Cadena con la Consulta
        String hql = "select v from VentasDemo v";
        List<Ventasdemo> listVentasDemo = null;
        try{
            //obtenemos una instancia de EntityManager
            em = getEntityManager();
            //Creamos la consulta
            Query q = em.createQuery(hql);
            //ejecutamos la consulta
              listVentasDemo = q.getResultList();
        }catch(Exception e){
            e.printStackTrace();
        }
        return listVentasDemo;
    }
    
    //Metodo que inserta una instancia de 'User.java' en la BD
    public void insertUser(Ventasdemo ventasDemo){
        try{
            //obtenemos una instancia de EntityManager
            em = getEntityManager();
            //iniciamos una transaccion
            em.getTransaction().begin();
            em.persist(ventasDemo);
            //confirmamos la transaccion
            em.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    //Metodo que modifica una instancia de 'User.java' en la BD
    public void updatetUser(Ventasdemo ventasDemo){
        try{
            //obtenemos una instancia de EntityManager
            em = getEntityManager();
            //iniciamos una transaccion
            em.getTransaction().begin();
            em.merge(ventasDemo);
            //confirmamos la transaccion
            em.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
     //Metodo que elimina una instancia de 'User.java' en la BD
    public void removetUser(Ventasdemo ventasDemo){
        try{
            //obtenemos una instancia de EntityManager
            em = getEntityManager();
            //iniciamos una transaccion
            em.getTransaction().begin();
            em.remove(em.merge(ventasDemo));
            //confirmamos la transaccion
            em.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    //Metodo para recobrar registros de la tabla 'ventasdemo'
    public Map<Object, Number> getVentasDemo(char clasificacion){
        List<Ventasdemo> listVentasDemo; 
        Map<Object,Number> mapVentas;
        try{
            em = getEntityManager();
            Query q = em.createQuery("select vd from Ventasdemo vd where vd.clasificacion =:clasificacion");
            q.setParameter("clasificacion", clasificacion);
            //Ejecutando la consulta
            listVentasDemo = q.getResultList();
            
            //Pasando los items de la Lista a la Coleccion Map
            mapVentas = new LinkedHashMap();
            for(Ventasdemo vd :listVentasDemo){
                mapVentas.put(vd.getTipoVehiculo(), vd.getVentas());
            }
            
            //Retornamos el Map
            return mapVentas;
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
