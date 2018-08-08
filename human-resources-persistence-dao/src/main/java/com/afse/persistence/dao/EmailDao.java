package com.afse.persistence.dao;

import com.afse.persistence.entity.EmailMassage;

import java.io.Serializable;

public interface EmailDao extends Serializable {

    void save(EmailMassage emailMassage);
}
