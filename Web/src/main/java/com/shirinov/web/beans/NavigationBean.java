package com.shirinov.web.beans;


import com.shirinov.utils.FacesUtils;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.IOException;
import java.io.Serializable;

/**
 * User: Rustam Shirinov
 * Date: 22/07/14
 * Time: 1:27 AM
 */

@ManagedBean
@RequestScoped
public class NavigationBean implements Serializable {

    private static final long serialVersionUID = 104l;

    private FacesUtils facesUtils = new FacesUtils();

    public void toDevivsion(){
        try {
            facesUtils.redirect(facesUtils.getContextRoot() + "/content/devision.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
            //TODO: Страница с ошибками
        }
    }

    public void toMembers(){
        try {
            facesUtils.redirect(facesUtils.getContextRoot() + "/content/members.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
            //TODO: Страница с ошибками
        }
    }

    public void toMyMembers(){
        try {
            facesUtils.redirect(facesUtils.getContextRoot() + "/content/mymembers.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
            //TODO: Страница с ошибками
        }
    }
    public void toMyBalance(){
        try {
            facesUtils.redirect(facesUtils.getContextRoot() + "/content/mybalance.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
            //TODO: Страница с ошибками
        }
    }
    public void toMyCash(){
        try {
            facesUtils.redirect(facesUtils.getContextRoot() + "/content/mycash.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
            //TODO: Страница с ошибками
        }
    }


}
