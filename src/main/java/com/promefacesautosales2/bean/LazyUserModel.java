package com.promefacesautosales2.bean;

import com.primefacesautosale2.entity.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;


public class LazyUserModel extends LazyDataModel<User>{
    private List<User> datasource;
     
    public LazyUserModel(List<User> datasource) {
        this.datasource = datasource;
    }
     
    @Override
    public User getRowData(String rowKey) {
        for(User user : datasource) {
            if(user.getId().equals(rowKey)){ 
                return user;
            }
        }
 
        return null;
    }
 
    @Override
    public Object getRowKey(User user) {
        return user.getId();
    }
 
    @Override
    public List<User> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,Object> filters) {
        List<User> data = new ArrayList<User>();
 
        //filter
        for(User user : datasource) {
                data.add(user);
        }
 
 
        //rowCount
        int dataSize = data.size();
        this.setRowCount(dataSize);
        return data;
        
    }
    
   
}
