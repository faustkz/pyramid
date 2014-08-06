package com.shirinov.web.entities.dao;

import com.shirinov.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.shirinov.web.entities.Member;

import java.util.List;

/**
 * User: Rustam Shirinov
 * Date: 25/07/14
 * Time: 10:17 AM
 */
public class MemberDao extends AbstractDao<Member> {



    public int countMembers(int devisionId){
        Transaction trns = null;
        int count = 0;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            trns = session.beginTransaction();
            count = ((Long)session.createQuery("SELECT COUNT(*) FROM Member WHERE devision_id = :id").uniqueResult()).intValue();
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
        return count;
    }

//    public List<Member> getFreeMember(){
//        Transaction trns = null;
//        List<Member> members;
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        try {
//            trns = session.beginTransaction();
//            members= session.getNamedQuery(Member.QUERY_GET_FREE_MEMBERS).list();
//            session.getTransaction().commit();
//        } catch (RuntimeException e) {
//            if (trns != null) {
//                trns.rollback();
//            }
//            e.printStackTrace();
//            throw e;
//        } finally {
//            session.flush();
//            session.close();
//        }
//        return (members!=null && members.size()!=0) ? members : null;
//
//    }

}
