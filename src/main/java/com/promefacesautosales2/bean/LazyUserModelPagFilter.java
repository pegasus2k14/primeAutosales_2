package com.promefacesautosales2.bean;

import com.primefacesautosale2.dao.UserDao;
import com.primefacesautosale2.entity.User;
import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

public class LazyUserModelPagFilter extends LazyDataModel<User>{
    UserDao userDao = new UserDao();
    //Constructor
    public LazyUserModelPagFilter(){
        this.setRowCount(userDao.getUserTotalCount());
    }

    @Override
    public List<User> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        //Ejecutamos el metodo que devuelve la Lista de Usuarios en Demanda Paginados y Filtrados
        List<User> listUser =userDao.listarUsuariosPaginacionFiltro(first, pageSize, filters);
        
        //Si la coleccion Clave Valor que almacena los Filtros tiene elementos
        if(filters != null && filters.size() >0){
            this.setRowCount(userDao.getFilterdRowCount(filters));
        }
        //retornamos la lista de Usuarios
        return listUser;
    }
    
    
}
