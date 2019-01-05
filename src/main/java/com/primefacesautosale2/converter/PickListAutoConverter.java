package com.primefacesautosale2.converter;

import com.primefacesautosale2.dao.PrimeAutomobileDao;
import com.primefacesautosale2.entity.PrimeAutomobile;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import org.primefaces.component.picklist.PickList;
import org.primefaces.model.DualListModel;


@FacesConverter(value = "automobilConverter")
public class PickListAutoConverter implements Converter{
    PrimeAutomobileDao primeAutomobileDao = new PrimeAutomobileDao();

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (component instanceof PickList) {
            if (value != null && value.trim().length() > 0 && !value.equalsIgnoreCase("null")) {
                return this.primeAutomobileDao.findById(Integer.valueOf(value));
            }else{
                return null;
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null || (value instanceof String && ((String) value).length() == 0)) {
            return null;
        }

        if(value instanceof PrimeAutomobile){
            PrimeAutomobile p = (PrimeAutomobile) value;
            return p.getId().toString();
        }else{
            return null;
        }
    }
    
}
