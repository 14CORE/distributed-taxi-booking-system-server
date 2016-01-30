package com.robertnorthard.dtbs.server.layer.service;

/**
 * An interface for defining and enforcing operations needed for 
 * the Taxi Service class. It provides the scope of possible 
 * database requests made through the TaxiDAO.
 * @author robertnorthard
 */
public interface LocationTrackingFacade{
    
    /**
     * Update taxi location by id.
     * @param id the id of the taxi.
     * @param latitude Taxi's current latitude.
     * @param longitude Taxi's current longitude.
     * @param timestamp timestamp of event.
     */
    public void updateLocation(Long id, double latitude, double longitude, long timestamp);
    
}
