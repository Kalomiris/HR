package com.afse.persistence.dao;

import com.afse.persistence.entity.EmailMassage;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class EmailDaoImpl implements EmailDao {

    private static final long serialVersionUID = 67375053087436772L;

    @PersistenceContext(unitName = "HumanResourcePX")
    private EntityManager entityManager;

    @Override
    public void save(EmailMassage message) {
        if (message != null) {
            entityManager.persist(message);
            entityManager.flush();
        }
    }
}
