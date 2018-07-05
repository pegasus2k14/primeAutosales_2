package com.primefacesautosale2.dao;

import com.primefacesautosale2.entity.User;
import java.util.List;
import javax.persistence.Query;

public class UserDao extends GenericDao{
    
    //Metodo para obtener todas las instancias de 'User.java' que hay en la BD
    public List<User> getAllUsers(){
        //Cadena con la Consulta
        String hql = "select u from User u";
        List<User> listUser = null;
        try{
            //obtenemos una instancia de EntityManager
            em = getEntityManager();
            //Creamos la consulta
            Query q = em.createQuery(hql);
            //ejecutamos la consulta
              listUser = q.getResultList();
        }catch(Exception e){
            e.printStackTrace();
        }
        return listUser;
    }
    
    //Metodo que inserta una instancia de 'User.java' en la BD
    public void insertUser(User user){
        try{
            //obtenemos una instancia de EntityManager
            em = getEntityManager();
            //iniciamos una transaccion
            em.getTransaction().begin();
            em.persist(user);
            //confirmamos la transaccion
            em.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
    //Metodo que modifica una instancia de 'User.java' en la BD
    public void updatetUser(User user){
        try{
            //obtenemos una instancia de EntityManager
            em = getEntityManager();
            //iniciamos una transaccion
            em.getTransaction().begin();
            em.merge(user);
            //confirmamos la transaccion
            em.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    
     //Metodo que elimina una instancia de 'User.java' en la BD
    public void removetUser(User user){
        try{
            //obtenemos una instancia de EntityManager
            em = getEntityManager();
            //iniciamos una transaccion
            em.getTransaction().begin();
            em.remove(em.merge(user));
            //confirmamos la transaccion
            em.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
