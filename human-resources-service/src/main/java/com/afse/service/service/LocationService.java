package com.afse.service.service;

import java.io.Serializable;
import java.util.List;

public interface LocationService extends Serializable {

    List<String> findAll();

    List<String> findCities(String country);
}
