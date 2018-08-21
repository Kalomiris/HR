package com.afse.service.service;

import java.util.List;

public interface LocationService {

    List<String> findAll();

    List<String> findCities(String country);
}
