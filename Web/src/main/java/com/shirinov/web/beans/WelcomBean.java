package com.shirinov.web.beans;

import com.shirinov.utils.FacesUtils;
import com.shirinov.utils.PasswordHash;
import com.shirinov.web.entities.Devision;
import com.shirinov.web.entities.Member;
import com.shirinov.web.entities.User;
import com.shirinov.web.entities.dao.AbstractDao;
import sun.security.provider.SHA2;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: Shirinov
 * Date: 15/06/14
 * Time: 7:11 PM
 */
@ManagedBean  (name = "welcomBean", eager = true)
@ViewScoped
public class WelcomBean implements Serializable {

    private static final long serialVersionUID =104L;
    private static final String LOGIN_FORM = "loginForm";
    private static final String EMAIL_FIELD = "emailField";
    private static final String PASSWORD_FIELD = "passwordField";
    private FacesUtils facesUtils = new FacesUtils();

    private String email;
    private String password;

    public boolean isLogin(){
        Object userId =  facesUtils.getSessionAttribute("userId");
        if (userId==null) {
           return false;
        } else try {
            facesUtils.redirect(facesUtils.getContextRoot() + "/content/main.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void doLogin(){
        try {
            AbstractDao<User> userDao = new AbstractDao<>();
            User user = userDao.getBy("email",email, User.QUERY_GET_USER_BY_EMAIL).get(0);
            byte[] passHash = PasswordHash.hash(password);


            if (user!=null && Arrays.equals(user.getPassword(),(passHash))){
                facesUtils.setSessionAttribute("userId",user.getId());
                HttpSession session = (HttpSession)facesUtils.getExternalContext().getSession(true);
                session.setAttribute("userId", user.getId());
                UserBean userBean = new UserBean(user);
                facesUtils.setSessionBean("userBean",userBean);
                facesUtils.redirect(facesUtils.getContextRoot()+"/content/main.xhtml");
            }
        } catch (IOException e){
            e.printStackTrace();
            facesUtils.throwFacesError(LOGIN_FORM + ":" + EMAIL_FIELD, "Внутреньяя ошибка ");
        } catch (Exception e) {
            facesUtils.throwFacesError(LOGIN_FORM + ":" + EMAIL_FIELD, "Не верный пароль ");
        }
        facesUtils.throwFacesError(LOGIN_FORM + ":" + EMAIL_FIELD, "Не верный пароль ");

    }

    public void doLogout(){
        facesUtils.removeSessionAttribute("userId");
        HttpSession session = (HttpSession)facesUtils.getExternalContext().getSession(true);
        session.setAttribute("userId", null);
        facesUtils.updatePage();
    }

//    public String showHash() throws UnsupportedEncodingException, NoSuchAlgorithmException {
//        AbstractDao<User> ud = new AbstractDao<>();
//        AbstractDao<Member> md = new AbstractDao<>();
//        AbstractDao<Devision> dd = new AbstractDao<>();
//
//        User user = new User();
//        Member member = new Member();
//        Devision devision = dd.get(Devision.QUERY_GET_ALL_DEVISIONS).get(0);
//
//        user.setId(1L);
//        user.setFirstName("Rustam");
//        user.setLastName("Shirinov");
//        user.setEmail("test@test.com");
//        user.setRole(0);
//        user.setStatus(1);
//        user.setPassword(PasswordHash.hash("123456"));
//        ud.add(user);
//
//        member.setId(1L);
//        member.setUser(user);
//        member.setDevision(devision);
//
//        md.add(member);
//
//
//        return "Готово!";
//
//    }

}
