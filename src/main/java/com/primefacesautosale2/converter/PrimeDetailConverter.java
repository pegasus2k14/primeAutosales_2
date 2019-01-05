package com.primefacesautosale2.converter;

import com.primefacesautosale2.dao.PrimeAutomobileDao;
import com.primefacesautosale2.entity.PrimeAutomobile;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


@FacesConverter(value ="automobileDetailConverter")
public class PrimeDetailConverter implements Converter{
    PrimeAutomobileDao primeAutomobileDao = new PrimeAutomobileDao();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
      if(value != null && !value.isEmpty()){
          return (PrimeAutomobile) component.getAttributes().get(value);
      }
      return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
      if(value instanceof PrimeAutomobile){
          PrimeAutomobile entity = (PrimeAutomobile) value;
          
          if(entity != null && entity instanceof PrimeAutomobile && entity.getId() != null){
              component.getAttributes().put(entity.getId().toString(), entity);
              return entity.getId().toString();
          }
      }
      return null;
    }
    
    
}
