package com.humanbooster.exam.dao;

import com.humanbooster.exam.model.BorneRecharge;
import org.hibernate.SessionFactory;

public class BorneRechargeDao extends ImplementationDao<BorneRecharge, Long> {
    public BorneRechargeDao(SessionFactory sessionFactory) {
        super(sessionFactory, BorneRecharge.class);
    }
}
