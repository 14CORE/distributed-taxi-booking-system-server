package com.robertnorthard.dtbs.server.layer.service;

import com.robertnorthard.dtbs.server.common.exceptions.InvalidGoogleApiResponseException;
import com.robertnorthard.dtbs.server.common.exceptions.RouteNotFoundException;
import com.robertnorthard.dtbs.server.layer.model.Location;
import com.robertnorthard.dtbs.server.layer.model.Route;
import java.util.List;
import org.codehaus.jettison.json.JSONObject;

/**
 * Service class for Google's distance matrix and geocode API.
 * @author robertnorthard
 */
public interface GoogleDistanceMatrixFacade {
    
    /**
     * Return distance between origin and destination.
     * 
     * @param origin route origin address.
     * @param destination route destination address.
     * @return distance between origin and destination.
     * @throws InvalidGoogleApiResponseException
     */
    public double getDistance(String origin, String destination) 
            throws InvalidGoogleApiResponseException, RouteNotFoundException;
    
    /**
     * Return a collection of routes from Google directions API response.
     * 
     * @param json Google directions API response.
     * @return a collection of routes from Google directions API response.
     * @throws InvalidGoogleApiResponseException invalid JSON response.
     */
    public List<List<Location>> getRoutes(JSONObject json) throws InvalidGoogleApiResponseException;
    
    /**
     * Return location from address.
     * 
     * @param address address to lookup.
     * @return the location (latitude and longitude)
     * @throws InvalidGoogleApiResponseException invalid JSON response. 
     */
    public Location getGeocode(String address) throws InvalidGoogleApiResponseException;
    
    /**
     * Return address corresponding to latitude and longitude.
     * 
     * @param latitude latitude of address.
     * @param longitude longitude of address.
     * @return address corresponding to latitude and longitude.
     * @throws InvalidGoogleApiResponseException invalid JSON response. 
     */
    public String getGeocode(double latitude, double longitude) throws InvalidGoogleApiResponseException;
    
   /**
     * Get route info (distance, route, travel time, start and end textual address) using start and end location.
     * 
     * @param startLocation start location
     * @param endLocation end location.
     * @return route calculated route information. 
     * @throws InvalidGoogleApiResponseException unable to parse API response. 
     * @throws IllegalArgumentException invalid location object.
     */
    public Route getRouteInfo(Location startLocation, Location endLocation) throws InvalidGoogleApiResponseException;
       
    
   /**
     * Estimate travel time between start and end location.
     * 
     * @param startLocation start location
     * @param endLocation end location.
     * @return route calculated route information. 
     * @throws InvalidGoogleApiResponseException unable to parse API response. 
     * @throws IllegalArgumentException invalid location object.
     */
    public long estimateTravelTime(Location startLocation, Location endLocation) throws InvalidGoogleApiResponseException;
     
}
