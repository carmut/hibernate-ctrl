package com.humanbooster.exam.dao;

import com.humanbooster.exam.model.LieuRecharge;
import org.hibernate.SessionFactory;

public class LieuRechargeDao extends ImplementationDao<LieuRecharge, Long>{
    public LieuRechargeDao(SessionFactory sessionFactory) {
        super(sessionFactory, LieuRecharge.class);
    }
}
