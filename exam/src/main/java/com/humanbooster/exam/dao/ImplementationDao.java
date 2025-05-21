package com.humanbooster.exam.dao;

import com.humanbooster.exam.dao.interfaces.GenericDao;
import org.hibernate.SessionFactory;
import org.hibernate.Session;

import java.util.List;

public abstract class ImplementationDao<T, ID> implements GenericDao<T, ID> {
    //attribut
    protected final SessionFactory sessionFactory;
    private final Class<T> entityClass;


    //contructor
    public ImplementationDao(SessionFactory sessionFactory, Class<T> entityClass) {
        this.sessionFactory = sessionFactory;
        this.entityClass = entityClass;
    }


    //override interface fonctions
    @Override
    public void creer(T entity) {
        try( var session = sessionFactory.openSession() ) {
            session.beginTransaction();
            session.persist(entity);
            session.getTransaction().commit();
            //partie ajouter pour les tests
            System.out.println("entitée " + entityClass.getSimpleName() + " creer avec succes");
        }
    }

    @Override
    public T lire(ID id) {
        try (Session session = sessionFactory.openSession()) {
            return session.get(entityClass, id);
        }
    }

    @Override
    public void mettreAJour(T entity) {
        try (var session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(entity);
            session.getTransaction().commit();
        }
    }

    @Override
    public void supprimer(ID id) {
        try (var session = sessionFactory.openSession()) {
            session.beginTransaction();
            T entity = session.get(entityClass, id);
            if (entity != null) {
                session.remove(entity);
            }
            session.getTransaction().commit();
        }
    }

    @Override
    public List<T> tout() {
        try (var session = sessionFactory.openSession()) {
            String hql = "FROM" + entityClass.getName();
            return session.createQuery(hql, entityClass).getResultList();
        }
    }
}
