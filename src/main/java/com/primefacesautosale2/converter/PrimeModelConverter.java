package com.primefacesautosale2.converter;

import com.primefacesautosale2.entity.PrimeAutomobile;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("primeModelConverter")
public class PrimeModelConverter implements Converter{

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if(value != null && !value.isEmpty()){
            return (PrimeAutomobile) component.getAttributes().get(value);
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object object) {
        if(object instanceof PrimeAutomobile){
            PrimeAutomobile entity = (PrimeAutomobile) object;
            if(entity != null && entity instanceof PrimeAutomobile && entity.getId() != null){
                component.getAttributes().put(entity.getId().toString(), entity);
                return entity.getId().toString();
            }
        }
        return "";
    }
    
}
