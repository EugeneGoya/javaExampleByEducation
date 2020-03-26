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

    public  FlatUserEntity findByCity(String city) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        FlatUserEntity flatUserEntity = null;
        try {
            tx = session.beginTransaction();
            flatUserEntity = session.createQuery("select f from FlatUserEntity f where f.cityName =:city", FlatUserEntity.class).setParameter("city", city).getSingleResult();
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

    public  void deleteFlatUser(int id) {
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

    public  void updateСountry(FlatUserEntity flatUserEntity) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            FlatUserEntity flatUser = session.get(FlatUserEntity.class, flatUserEntity.getId());
            flatUser.setCountryName(flatUserEntity.getCountryName());
            session.update(flatUser);
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
