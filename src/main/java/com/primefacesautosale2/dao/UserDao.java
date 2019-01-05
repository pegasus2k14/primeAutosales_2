package com.primefacesautosale2.dao;

import com.primefacesautosale2.entity.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.primefaces.model.SortOrder;

public class UserDao extends GenericDao implements Serializable{
    
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
    
    //Metodo para obtener la lista de usuarios registrados en Demanda con paginacion
    //Donde los parametros son (primerRegistro, tamañoDePagina)
    public List<User> listarUsuarios(int first, int pageSize){
        //obtenemos una instancia de EntityManager
        em = getEntityManager();
        Query q = em.createQuery("from User");
        q.setFirstResult(first);
        q.setMaxResults(pageSize);
                
        //Ejecutamos la consulta
        return q.getResultList();
    }
    
    
    //Metodo que Devuelve el numero de registros que existen en la tabla Usuarios
    public int getUserTotalCount(){
        //obtenemos una instancia de EntityMannager
        em = getEntityManager();
        
        //creamos la consulta
        Query q = em.createQuery("select count(u.id) from User u");
        //Ejecutamos la consulta
        return ((Long) q.getSingleResult()).intValue();
    }
    
    
    
   //Metodo para obtener la lista de usuarios registrados en Demanda, con Paginacion
    //y ordenacion los parametros son (primerRegistro, tamañoDePagina,CampoPorElQueSeOrdena, TipoOrdenacion )
    public List<User> listarUsuariosPaginacionOrdenacion(int first,int pageSize,String sortField,SortOrder sortOrder){
        //Se obtiene una instancia de EntityManager
        em = getEntityManager();
        
        //Creamos la consulta usando el API Criteria
          //Obtenemos una instancia de CriteriaBuilder
          CriteriaBuilder cb = em.getCriteriaBuilder();
          //Creamos un objeto CriteriaQuery, indicando el tipo de resultados que este tendra
          CriteriaQuery<User> q = cb.createQuery(User.class);
          //Creamos el Root de la consulta
          Root<User> r = q.from(User.class);
          
          //Añadimos la ordenacion 
          //Si el parametro 'sortField' es distinto de nulo
           if(sortField != null){
               if(sortOrder == SortOrder.ASCENDING){
                   q.orderBy(cb.asc(r.get(sortField)));
               }else{
                   q.orderBy(cb.desc(r.get(sortField)));
               }
           }
           
           //A partir del objeto CriteriaQuery 'q' creamos un objeto Typedquery
           TypedQuery<User> query = em.createQuery(q);
           //Asignado paginacion al objeto TypedQuery
           query.setFirstResult(first);
           query.setMaxResults(pageSize);
           //Ejecutando la consulta
           List<User> listUsers = query.getResultList();
           //retornamos el resultado
          return listUsers;     
    }
    
    
     //Metodo que devuelve el numero de filas o registros que se obtienen de la
    //tabla Usuarios tras realizar la consulta con filtrado
    //Recibe como parametro una coleccion Clave-Valor Map(NombreCampoFiltro,ValorCampoFiltro)
    //con los filtros usados por la consulra
    public int getFilterdRowCount(Map<String, Object> filters) {
        //obtenemos una instancia de EntityMannager
        em = getEntityManager();
        //Creamos una instancia de CriteriaBbuilder
        CriteriaBuilder cb = em.getCriteriaBuilder();

        //Creamos un objeto CriteriaQuery cuyo Resultado debera ser del Tipo 'Long'
        CriteriaQuery<Long> criteriaQuery = cb.createQuery(Long.class);
        //Creamos el Root de la Consulta
        //El 'from' de la consulta sera sobre la Entidad 'User'
        Root<User> root = criteriaQuery.from(User.class);

        //Agregamos una funcion de Conteo al Select de la consulta
        //Equivale a 'select count(u) from User u'
        criteriaQuery.select(cb.count(root));

        //Si la coleccion que almacena los filtros es distinta de Null
        //y su longitud es > 0
        if (filters != null && filters.size() > 0) {
            //Creamos una Lista de Objetos Predicate
            //Estos nos sirven para almacenar los elementos que formaran parte del
            //Where de la Consulta
            List<Predicate> predicados = new ArrayList<>();

            //Recorremos la Coleccion Map que contiene los Filtros
            for (Map.Entry<String, Object> entry : filters.entrySet()) {
                //Recuperramos la Clave y el valor de Cada Item y los depositamos 
                //en variables
                String field = entry.getKey();
                Object value = entry.getValue();

                //Comprobamos si el Value es Nulo, de ser asi aboramos la iteracion
                //y pasamos a la siguiente
                if (value == null) {
                    continue;
                }

                //Recuperamos el valor del field (Nombre del atributo en la Entidad User usado como filtro)
                //y lo almacenamos en un Objeto Expression que almacena un valor String
                Expression<String> expresion = root.get(field).as(String.class);

                //Creamos un objeto Predicate  que almacenara uno de los elementos
                //que compondran luego el WHERE de la consulta
                //lo siguiente es equivalente a 'nombreCampo LIKE %Valor%' en una consulta SQL
                Predicate p = cb.like(expresion, "%" + value.toString().toLowerCase() + "%");
                //Agregamos el Predicado a la Lista de Predicados
                predicados.add(p);
            }

            //Si la Lista de Predicados su longitud es > 0
            if (predicados.size() > 0) {
                //Agregamos la Lista de predicados al WHERE de la consulta
                //Esto es equivalente a 'WHERE nombreCampo LIKE %valor% and nombreCampo2 LIKE %valor% ...'
                criteriaQuery.where(cb.and(predicados.toArray(new Predicate[predicados.size()])));
            }

        }
        //Realizamos la Consulta y obtenemos el numero de registros que cumplieron
        //con los filtros o condiciones de la consulta
        Long count = em.createQuery(criteriaQuery).getSingleResult();

        //Retornamos el numero de filas de resultado
        return count.intValue();
    }
    
    
    
    //Metodo que retorna La lista de usuarios para cargar en el DataTable en base a Demanda
    //Recibe como parametros: (primerRegistro,tamañoDePagina, ColeccionMapConLosFiltros)
    public List<User> listarUsuariosPaginacionFiltro(int first,int pageSize, Map<String, Object> filters) {
        //obtenemos una instancia de EntityMannager
        em = getEntityManager();
        //Creamos una instancia de CriteriaBbuilder
        CriteriaBuilder cb = em.getCriteriaBuilder();

        //Creamos un objeto CriteriaQuery cuyo Resultado debera ser del Tipo 'Long'
        CriteriaQuery<User> criteriaQuery = cb.createQuery(User.class);
        //Creamos el Root de la Consulta
        //El 'from' de la consulta sera sobre la Entidad 'User'
        Root<User> root = criteriaQuery.from(User.class);

        //Si la coleccion que almacena los filtros es distinta de Null
        //y su longitud es > 0
        if (filters != null && filters.size() > 0) {
            //Creamos una Lista de Objetos Predicate
            //Estos nos sirven para almacenar los elementos que formaran parte del
            //Where de la Consulta
            List<Predicate> predicados = new ArrayList<>();

            //Recorremos la Coleccion Map que contiene los Filtros
            for (Map.Entry<String, Object> entry : filters.entrySet()) {
                //Recuperramos la Clave y el valor de Cada Item y los depositamos 
                //en variables
                String field = entry.getKey();
                Object value = entry.getValue();

                //Comprobamos si el Value es Nulo, de ser asi aboramos la iteracion
                //y pasamos a la siguiente
                if (value == null) {
                    continue;
                }

                //Recuperamos el valor del field (Nombre del atributo en la Entidad User usado como filtro)
                //y lo almacenamos en un Objeto Expression que almacena un valor String
                Expression<String> expresion = root.get(field).as(String.class);

                //Creamos un objeto Predicate  que almacenara uno de los elementos
                //que compondran luego el WHERE de la consulta
                //lo siguiente es equivalente a 'nombreCampo LIKE %Valor%' en una consulta SQL
                Predicate p = cb.like(expresion, "%" + value.toString().toLowerCase() + "%");
                //Agregamos el Predicado a la Lista de Predicados
                predicados.add(p);
            }

            //Si la Lista de Predicados su longitud es > 0
            if (predicados.size() > 0) {
                //Agregamos la Lista de predicados al WHERE de la consulta
                //Esto es equivalente a 'WHERE nombreCampo LIKE %valor% and nombreCampo2 LIKE %valor% ...'
                criteriaQuery.where(cb.and(predicados.toArray(new Predicate[predicados.size()])));
            }

        }
        
        //A partir del objeto CriteriaQuery creamos un objeto Typedquery
        TypedQuery<User> query = em.createQuery(criteriaQuery);
        //agregamos la paginacion al TypedQuery
        query.setFirstResult(first);
        query.setMaxResults(pageSize);
        
        //Ejecutamos la consulta
        List<User> lista = query.getResultList();
        
        //Retornamos la lista resultante
        return lista;
    }
    
}
