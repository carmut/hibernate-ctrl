package com.humanbooster.exam.dao;

import com.humanbooster.exam.model.Reservation;
import com.humanbooster.exam.model.Utilisateur;
import org.hibernate.SessionFactory;

public class ReservationDao extends ImplementationDao<Reservation, Long>{
    public ReservationDao(SessionFactory sessionFactory) {
        super(sessionFactory, Reservation.class);
    }
}
