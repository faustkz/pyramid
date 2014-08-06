package com.shirinov.web.beans;

import com.shirinov.web.entities.Devision;
import com.shirinov.web.entities.Member;
import com.shirinov.web.entities.Setting;
import com.shirinov.web.entities.User;
import com.shirinov.web.entities.dao.AbstractDao;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: Shirinov
 * Date: 15/06/14
 * Time: 7:53 PM
 */

@ManagedBean  (name="userBean", eager=true)
@SessionScoped
public class UserBean implements Serializable {

    private static final long serialVersionUID = 101L;

    private User user;
    public UserBean() {

    }

    public UserBean(User user) {
        this.user = user;
    }

    public String getFullName(){
        return user.getFirstName() + " " + user.getLastName();
    }

    public boolean isAdminRole(){
        return user.getRole()==0;
    }
}
