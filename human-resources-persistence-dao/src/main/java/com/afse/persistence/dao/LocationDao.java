package com.afse.persistence.dao;

import java.io.Serializable;
import java.util.List;

public interface LocationDao extends Serializable {

    List<String> findAll();

    List<String> findCities(String country);

    boolean validateCombination(String country, String city);
}
