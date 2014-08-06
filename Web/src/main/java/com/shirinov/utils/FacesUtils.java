package com.shirinov.utils;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 * User: Rustam Shirinov
 * Date: 21/07/14
 * Time: 11:03 PM
 */
public class FacesUtils {


    public FacesUtils() {

    }

    public UIComponent findComponentByID(String id) {
        return getContext().getViewRoot().findComponent(id);
    }

    /**
     * returns current context.
     *
     * @return FacesContext
     */
    public  FacesContext getContext() {
        return FacesContext.getCurrentInstance();
    }

    public ExternalContext getExternalContext() {
        FacesContext ctx = getContext();
        ExternalContext r = ctx.getExternalContext();
        return r;
    }

    @SuppressWarnings("unchecked")
    public  Map<String, Object> getRequestMap() {
        ExternalContext context = getExternalContext();
        return context.getRequestMap();
    }

    @SuppressWarnings("unchecked")
    public  Map<String, Object> getSessionMap() {
        ExternalContext context = getExternalContext();
        return context.getSessionMap();
    }

    @SuppressWarnings("unchecked")
    public  Map<String, Object> getApplicationMap() {
        ExternalContext context = getExternalContext();
        return context.getApplicationMap();
    }

    public  Object getRequestParameter(final String key) {
        return getRequestMap().get(key);
    }

    public  void setRequestParameter(final String key, final String value) {
        getRequestMap().put(key, value);
    }

    public  void throwFacesMessage(final String id, final String msg,
                                         final FacesMessage.Severity severity) {
        FacesMessage mess = new FacesMessage(msg);
        mess.setSeverity(severity);
        getContext().addMessage(id, mess);
    }

    public  void throwFacesError(final String id, final String msg) {
        throwFacesMessage(id, msg, FacesMessage.SEVERITY_ERROR);
    }

    public  HttpSession getSession() {
        ExternalContext context = getExternalContext();
        Object sessionObject = context.getSession(true);
        return (HttpSession) sessionObject;
    }


    public Object getSessionAttribute(final String attributeName) {
        return getSession().getAttribute(attributeName);
    }


    public void removeSessionAttribute(final String attributeName) {
        getSession().removeAttribute(attributeName);
    }


    public void setSessionAttribute(final String attributeName,
                                           final Object attribute) {
        getSession().setAttribute(attributeName, attribute);
    }


    public void redirect(final String url) throws IOException {
        getExternalContext().redirect(url);
    }

    public void redirectTo(final String url) throws IOException {
        String contextPath = getExternalContext()
                .getRequestContextPath();
        getExternalContext().redirect(contextPath + url);
    }

    public void forwardTo(final String url) throws IOException {
        ExternalContext externalContext = getExternalContext();
        String contextPath = externalContext.getRequestContextPath();
        externalContext.dispatch(contextPath + url);
    }

    public void updatePage() {
        String url = getRequestURL();
        try {
            getContext().getExternalContext().redirect(url);
        } catch (IOException e) {
        }
    }

    public String getRequestURL() {
        HttpServletRequest request = (HttpServletRequest) getContext().getExternalContext().getRequest();
        return request.getRequestURL().toString();
    }

    public String getContextRoot(){
        return FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
    }

    public <T> T resolveExpression(String expression) {
        Object value = null;
        if ((expression.indexOf("#{") != -1)
                && (expression.indexOf("#{") < expression.indexOf('}'))) {
            value =
                    getContext().getApplication().createValueBinding(
                            expression).getValue(
                            getContext());
        } else {
            value = expression;
        }
        @SuppressWarnings("unchecked")
        T res = (T) value;
        return res;
    }

    public  <T> T getManagedBean(String beanName) {
        String expression = "#{" + beanName + "}";
        @SuppressWarnings("unchecked")
        T res = (T) resolveExpression(expression);
        return res;
    }

    public <T> T getSessionBean(final String name) {
        @SuppressWarnings("unchecked")
        Map<String, Object> sessionMap = getExternalContext().getSessionMap();
        Object obj = sessionMap.get(name);
        if (obj == null) {
            obj = getManagedBean(name);
        }
        @SuppressWarnings("unchecked")
        T res = (T) obj;
        return res;
    }

    public void setSessionBean(final String name, final Object bean) {
        Map<String, Object> sessionMap = getExternalContext().getSessionMap();
        if (sessionMap.containsKey(name)) {
            sessionMap.remove(name);
        }
        sessionMap.put(name, bean);
    }

}
