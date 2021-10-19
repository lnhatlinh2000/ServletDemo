package com.example.dao;

import com.example.util.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.Serializable;
import java.util.List;

public class AbstractDAO<T, K> {

    Session session = null;
    Transaction transaction = null;

    /*
     * Load all data in database
     * @param table name entity
     * @return list<T>
     */
    public List<T> getAll(String entity) {
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            Query<T> query = session.createQuery(" FROM " + entity);
            return query.list();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    /*
     * Save data
     * @param entity
     * @return entity or null
     */
    public boolean save(T entity) {
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            Serializable result = session.save(entity);
            transaction.commit();
            return (result != null);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    /*
     * Update data
     * @param entity
     * @return boolean
     */
    public boolean update(T entity) {
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.getMessage();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return false;
    }

    /*
     * Delete by entity
     * @param entity
     * @return boolean
     */
    public boolean delete(T entity) {
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.delete(entity);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.getMessage();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return false;
    }

    /*
     * search by entity id (primary key)
     * @param class entity
     * @param key
     * @return entity
     */
    public T findById(Class<T> entity, Object key) {
        try {
            session = HibernateUtils.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            return session.find(entity, key);
        } catch (NullPointerException e) {
            e.getMessage();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }
}
