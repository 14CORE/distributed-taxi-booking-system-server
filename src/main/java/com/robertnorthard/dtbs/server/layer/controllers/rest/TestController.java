package com.robertnorthard.dtbs.server.layer.controllers.rest;

import com.robertnorthard.dtbs.server.common.exceptions.InvalidGoogleApiResponseException;
import com.robertnorthard.dtbs.server.layer.service.entities.Account;
import com.robertnorthard.dtbs.server.layer.service.entities.AccountRole;
import com.robertnorthard.dtbs.server.layer.service.entities.Location;
import com.robertnorthard.dtbs.server.layer.service.entities.Route;
import com.robertnorthard.dtbs.server.layer.service.entities.taxi.Taxi;
import com.robertnorthard.dtbs.server.layer.service.entities.Vehicle;
import com.robertnorthard.dtbs.server.layer.service.entities.VehicleType;
import com.robertnorthard.dtbs.server.layer.service.entities.booking.Booking;
import com.robertnorthard.dtbs.server.layer.persistence.AccountDao;
import com.robertnorthard.dtbs.server.layer.persistence.BookingDao;
import com.robertnorthard.dtbs.server.layer.persistence.TaxiDao;
import com.robertnorthard.dtbs.server.layer.persistence.VehicleDao;
import com.robertnorthard.dtbs.server.layer.service.GoogleDistanceMatrixService;
import com.robertnorthard.dtbs.server.layer.utils.AuthenticationUtils;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * Test controller to initialise the database. 
 * 
 * @author robertnorthard
 */
@Path("/v1/test")
public class TestController {

    @GET
    @Path("/setup")
    public String setUp() throws InvalidGoogleApiResponseException {

        String password = AuthenticationUtils.hashPassword("password");

        Account passenger = new Account("john.doe", "John", "Doe", password, "07645346541", "john.doe@example.com");
        passenger.setRole(AccountRole.PASSENGER);

        Account passenger2 = new Account("timsmith", "Tim", "Smith", password, "07526888826", "tim_smith@example.com");
        passenger2.setRole(AccountRole.PASSENGER);

        Account driver = new Account("john.smith", "John", "Smith", password, "02345346548", "john.smith@example.com");
        driver.setRole(AccountRole.DRIVER);

        VehicleType type = new VehicleType("Cruiser", "Hyundai", "Matrix", 0.3);
        Vehicle vehicle = new Vehicle("RN12 NGE", 5, type);

        Taxi taxi = new Taxi(vehicle, driver);

        Location startLocation = new Location(51.763366, -0.22309);

        Location endLocation = new Location(51.7535889, -0.2446257);

        GoogleDistanceMatrixService service = new GoogleDistanceMatrixService();

        Route route = service.getRouteInfo(startLocation, endLocation);

        Booking booking = new Booking(passenger, route, 2);

        /*
         * Persist entities.
         */
        AccountDao accountDao = new AccountDao();
        accountDao.persistEntity(passenger);
        accountDao.persistEntity(passenger2);
        accountDao.persistEntity(driver);

        VehicleDao vehicleDao = new VehicleDao();
        vehicleDao.persistEntity(vehicle);

        TaxiDao taxiDao = new TaxiDao();
        taxiDao.persistEntity(taxi);

        BookingDao bookingDao = new BookingDao();
        bookingDao.persistEntity(booking);

        return "Database initialised.";
    }

}
