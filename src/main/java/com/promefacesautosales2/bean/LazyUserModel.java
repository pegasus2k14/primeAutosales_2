package com.promefacesautosales2.bean;

import com.primefacesautosale2.dao.UserDao;
import com.primefacesautosale2.entity.User;
import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;


public class LazyUserModel extends LazyDataModel<User>{
    UserDao userDao = new UserDao();
    
    public LazyUserModel(){
        this.setRowCount(userDao.getUserTotalCount());
    }
    
    @Override
    public List<User> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        List<User> listUser = userDao.listarUsuarios(first, pageSize);
        
        return listUser;
    }

}
