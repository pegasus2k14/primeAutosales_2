package com.promefacesautosales2.bean;

import com.primefacesautosale2.dao.UserDao;
import com.primefacesautosale2.entity.User;
import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

public class LazyUserModelPagSort extends  LazyDataModel<User>{
     UserDao userDao = new UserDao();
    
    //Constructor
    public LazyUserModelPagSort() {
        setRowCount(userDao.getUserTotalCount());
    }
    
    @Override
    public List<User> load(int first, int pageSize,String sortField,SortOrder sortOrder,Map<String,Object> filters){
      //Invocamos al metodo que retorna la lista de usuarios paginados y ordenados
      List<User> lista = userDao.listarUsuariosPaginacionOrdenacion(first, pageSize, sortField, sortOrder);
      //retornamos la lista
      return lista;
    }
    
    
}
