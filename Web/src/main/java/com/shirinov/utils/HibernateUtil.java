package com.shirinov.utils;

import com.shirinov.web.entities.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: Rustam Shirinov
 * Date: 19/06/14
 * Time: 11:47 AM
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory=buildSessionFactory();

    public static SessionFactory buildSessionFactory(){
        try {
            Properties prop= new Properties();
            prop.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/pyramid");
            prop.setProperty("hibernate.connection.username", "rustam");
            prop.setProperty("hibernate.connection.password", "mega1234$");
            prop.setProperty("hibernate.show_sql","true");
            prop.setProperty("hibernate.use_sql_comments","true");
            prop.setProperty("dialect", "org.hibernate.dialect.MySQLDialect");
            prop.setProperty("hibernate.hbm2ddl.auto", "update");
            prop.setProperty("hibernate.c3p0.min_size","5");
            prop.setProperty("hibernate.c3p0.max_size","20");
            prop.setProperty("hibernate.c3p0.timeout","300");
            prop.setProperty("hibernate.c3p0.max_statements","50");
            prop.setProperty("hibernate.c3p0.idle_test_period","3000");
            return new Configuration().
                    addProperties(prop).
                    addAnnotatedClass(User.class).
                    addAnnotatedClass(Country.class).
                    addAnnotatedClass(City.class).
                    addAnnotatedClass(Devision.class).
                    addAnnotatedClass(Contact.class).
                    addAnnotatedClass(Comment.class).
                    addAnnotatedClass(Member.class).
                    addAnnotatedClass(Setting.class).
                    addAnnotatedClass(Balance.class).
                    addAnnotatedClass(Payment.class).
                    addAnnotatedClass(News.class).
                    buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
