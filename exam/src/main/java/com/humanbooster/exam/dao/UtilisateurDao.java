package com.humanbooster.exam.dao;

import com.humanbooster.exam.model.Utilisateur;
import org.hibernate.SessionFactory;

public class UtilisateurDao extends ImplementationDao<Utilisateur, Long>{
    public UtilisateurDao(SessionFactory sessionFactory) {
        super(sessionFactory, Utilisateur.class );
    }
}
