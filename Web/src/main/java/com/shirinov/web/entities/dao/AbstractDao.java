package com.shirinov.web.entities.dao;

import com.shirinov.utils.HibernateUtil;
import com.shirinov.web.entities.AbstractEntity;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;

/**
 * User: Rustam Shirinov
 * Date: 19/06/14
 * Time: 10:42 PM
 */
public class AbstractDao <T extends AbstractEntity>{

    public void add(T entity){
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.save(entity);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
            throw e;
        } finally {
            session.flush();
            session.close();
        }
    }

    public void update(T entity){
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.update(entity);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
            throw e;
        } finally {
            session.flush();
            session.close();
        }
    }

    public void delete(T entity){
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            session.delete(entity);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            if (trns != null) {
                trns.rollback();
            }
            e.printStackTrace();
            throw e;
        } finally {
            session.flush();
            session.close();
        }
    }

    public List<T> getBy(Map<String,Object> params, String queryName) {
        List<T> entities = new ArrayList<>();
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            Query query = session.getNamedQuery(queryName);
            for(String key : params.keySet()){
                Object value =params.get(key);
                if (value instanceof Integer) query.setInteger(key, (Integer) value);
                else
                if (value instanceof String) query.setString(key, (String) value);
                else
                if (value instanceof Date) query.setDate(key,(Date)value);
                else
                if (value instanceof java.util.Date) query.setDate(key,(java.util.Date)value);
                else
                if (value instanceof Boolean) query.setBoolean(key,(Boolean)value);
                else throw new RuntimeException("Unknown Parameter");
            }
            entities = query.list();
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw e;
        } finally {
            session.flush();
            session.close();
        }
        return (entities==null || entities.size()==0) ? null : entities;
    }

    public T getSingleBy(Map<String,Object> params, String queryName) {
        List<T> entities = this.getBy(params,queryName);
        return (entities==null || entities.size()==0) ? null : entities.get(0);
    }

//    public Integer count(){
//        Transaction trns = null;
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        try{
//            trns = session.beginTransaction();
//            Query query = session.createQuery("COUNT(id) FROM "+ T.class.getSimpleName());
//        } catch (RuntimeException e) {
//            e.printStackTrace();
//            throw e;
//        } finally {
//            session.flush();
//            session.close();
//        }
//        return null;
//    }

    public List<T> getBy(String key, Object value, String queryName) {
        List<T> entities = new ArrayList<>();
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            Query query = session.getNamedQuery(queryName);
                if (value instanceof Integer) query.setInteger(key, (Integer) value);
                else if (value instanceof String) query.setString(key, (String) value);
                else if (value instanceof Date) query.setDate(key,(Date)value);
                else if (value instanceof java.util.Date) query.setDate(key,(java.util.Date)value);
                else if (value instanceof Boolean) query.setBoolean(key,(Boolean)value);
                else throw new RuntimeException("Unknown Parameter");
            entities = query.list();
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw e;
        } finally {
            session.flush();
            session.close();
        }
        return (entities==null || entities.size()==0) ? null : entities;
    }

    public T getSingleBy(String key, Object value, String queryName) {
        List<T> entities = this.getBy(key,value,queryName);
        return (entities==null || entities.size()==0) ? null : entities.get(0);
    }

    public List<T> get(String queryName){
        List<T> entities = new ArrayList<>();
        Transaction trns = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            entities = session.getNamedQuery(queryName).list();
        } catch (RuntimeException e) {
            e.printStackTrace();
            throw e;
        } finally {
            session.flush();
            session.close();
        }
        return (entities==null || entities.size()==0) ? null : entities;
    }

}
