package com.robertnorthard.dtbs.server.layer.model.booking;

import com.robertnorthard.dtbs.server.layer.model.Taxi;
import java.util.Date;

/**
 * Represents passenger picked up state.
 * 
 * @author robertnorthard
 */
public class PassengerPickedUpBookingState extends BookingState {

    @Override
    public void cancelBooking(Booking booking) {
        throw new IllegalStateException("Cannot cancel booking.");
    }

    @Override
    public void cancelTaxi(Booking booking) {
        throw new IllegalStateException("Cannot cancel taxi");
    }

    @Override
    public void dispatchTaxi(Booking booking, Taxi taxi) {
        throw new IllegalStateException("Taxi already dispatched");
    }

    @Override
    public void dropOffPassenger(Booking booking, Date time) {
        
        if(time.after(booking.getStartTime())){
            booking.setEndTime(time);
            
            booking.setState(
                    Booking.getCompletedTaxiBookingState());
        }else{
            throw new IllegalArgumentException("Time must be after initial booking creation time.");
        }
        
    }

    @Override
    public void pickupPassenger(Booking booking, Date time) {
        throw new IllegalStateException("Passenger already picked up.");
    }
    
    @Override
    public String toString(){
        return "Passenger picked up state.";
    }
}
