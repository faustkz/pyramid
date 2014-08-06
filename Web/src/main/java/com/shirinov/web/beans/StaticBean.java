package com.shirinov.web.beans;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.io.Serializable;

/**
 * User: Rustam Shirinov
 * Date: 14/07/14
 * Time: 9:14 PM
 */
@ManagedBean  (name="staticBean", eager=true)
@ApplicationScoped
public class StaticBean implements Serializable {

    private static final long serialVersionUID = 102L;

    public String getContextRoot(){
        return FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
    }
}
