package com.afse.service.service;

import com.afse.persistence.dao.LocationDao;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;

/**
 * Stateless Session bean which offer access to LocationDaoImpl
 */
@Stateless
public class LocationServiceImpl implements LocationService {


    @EJB
    private LocationDao locationDao;


    /**
     * This method return all countries or cities
     *
     * @return List of String
     */
    public List<String> findAll() {
        return locationDao.findAll();
    }

    @Override
    public List<String> findCities(String country) {
        return locationDao.findCities(country);
    }
}
