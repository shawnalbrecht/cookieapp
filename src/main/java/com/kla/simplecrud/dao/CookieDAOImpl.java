package com.kla.simplecrud.dao;

import com.kla.simplecrud.model.Cookie;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.util.List;

/**
 * A simple implementation of {@link CookieDAO}.
 */
@Service
public class CookieDAOImpl implements CookieDAO {

    @PersistenceUnit(unitName = "entityManagerFactory")
    EntityManagerFactory entityManagerFactory;

    @Override
    public long createCookie(Cookie cookie) {
        return 0;
    }

    @Override
    public List<Cookie> retrieveCookies() {

        @SuppressWarnings("unchecked")
        List<Cookie> cookieList =  getSession().createQuery("from Cookie").list();

        return cookieList;
    }

    @Override
    public boolean updateCookie(Cookie cookie) {
        return false;
    }

    @Override
    public boolean deleteCookie(long cookieId) {
        return false;
    }

    protected Session getSession() {
        SessionFactory sessionFactory = this.entityManagerFactory.unwrap(SessionFactory.class);
        return sessionFactory.openSession();
    }
}
