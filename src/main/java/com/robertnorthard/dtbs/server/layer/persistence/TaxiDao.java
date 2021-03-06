package com.robertnorthard.dtbs.server.layer.persistence;

import com.robertnorthard.dtbs.server.layer.service.entities.taxi.Taxi;
import com.robertnorthard.dtbs.server.layer.service.entities.taxi.TaxiState;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A taxi Data Access Object (DAO) class for handling and managing event related
 * data requested, updated, and processed in the application and maintained in
 * the database.
 *
 * @author robertnorthard
 */
public class TaxiDao extends JpaEntityDaoImpl<Long, Taxi> {

    /**
     * Return a collection of bookings in the specified state.
     * If a taxi is not found for the driver null is returned.
     *
     * @param username username of driver.
     * @return the driver's taxi. If a taxi is not found for the driver null is returned.
     */
    public Taxi findTaxiForDriver(String username) {
        
        Taxi taxi = null;
        EntityManager em = this.getEntityManager();
        
        try{
        Query query = em.createNamedQuery("Taxi.findTaxiForDriver", Taxi.class);
        query.setParameter("username", username);
        taxi = (Taxi)query.getSingleResult();
        }finally{
            if(em.isOpen()){
                em.close();
            }
        }
        
        return taxi;
    }
    
    /**
     * Return collection of taxis on duty but not on a job.
     * 
     * @return a collection of taxis on duty but not on a job.
     *         Collection is empty of no taxis are found.
     */
    public List<Taxi> findAllOnDuty(){
        return this.findTaxiWithState(Taxi.getOnDutyTaxiState());
    }
    
   /**
     * Return collection of taxis off duty.
     * 
     * @return a collection of taxis that are off duty.
     *         Collection is empty of no taxis are found.
     */
    public List<Taxi> findAllOffDuty(){
        return this.findTaxiWithState(Taxi.getOffDutyTaxiState());
    }
    
    /**
     * Find taxi with the specified state.
     * 
     * @param state taxis with state.
     * @return a collection. of taxis with the provided state. 
     *         Collection is empty of no taxis are found.
     */
    private List<Taxi> findTaxiWithState(TaxiState state){
        
        List<Taxi> taxis = null;
        EntityManager em = this.getEntityManager();
        try{
            Query query = em.createNamedQuery("Taxi.findTaxisWithState", Taxi.class);
            query.setParameter("state", state);
            taxis = query.getResultList();
        }finally{
            if(em.isOpen()){
                em.close();
            }
        }
        return taxis;
    }
}
