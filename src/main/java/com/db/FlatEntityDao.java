package com.db;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class FlatEntityDao {

    private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    public void insert(FlatUserEntity person) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(person);
            tx.commit();
        } catch (HibernateException he) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }

    public List<FlatUserEntity> printAllFlatUsers() {
        Session session = sessionFactory.openSession();
        List<FlatUserEntity> flatUserEntities = null;
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            flatUserEntities = session.createQuery("select e from FlatUserEntity e", FlatUserEntity.class).list();
            tx.commit();
        } catch (HibernateException he) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
        return flatUserEntities;
    }

    private static FlatUserEntity findByEmail(String email) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        FlatUserEntity flatUserEntity = null;
        try {
            tx = session.beginTransaction();
            flatUserEntity = session.createQuery("select e from FlatUserEntity e where e.email =:email", FlatUserEntity.class).setParameter("email", email).getSingleResult();
            tx.commit();
        } catch (HibernateException he) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
        return flatUserEntity;
    }

    private static void deletePerson(int id) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            FlatUserEntity flatUserEntity = session.get(FlatUserEntity.class, id);
            session.delete(flatUserEntity);
            tx.commit();
        } catch (HibernateException he) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }

    private static void updatePerson(FlatUserEntity flatUserEntity) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            FlatUserEntity p = session.get(FlatUserEntity.class, flatUserEntity.getId());
            p.setCountryName(flatUserEntity.getCountryName());
            session.update(p);
            tx.commit();
        } catch (HibernateException he) {
            if (tx != null) {
                tx.rollback();
            }
        } finally {
            session.close();
        }
    }
}
