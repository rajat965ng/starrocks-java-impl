package com.lakehouse.app.repository;

import com.lakehouse.app.model.TaxiTrip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaxiTripRepository extends JpaRepository<TaxiTrip, Integer> {
    @Query(value = """
        SELECT 
            HOUR(lpep_pickup_datetime) AS hourOfDay, 
            COUNT(*) AS trips 
        FROM greentaxis 
        GROUP BY hourOfDay 
        ORDER BY trips DESC
    """, nativeQuery = true)
    List<Object[]> findTripsByHourOfDay();

    @Query(value = """
        SELECT 
            HOUR(lpep_pickup_datetime) AS hourOfDay, 
            COUNT(*) AS trips 
        FROM greentaxis 
        GROUP BY hourOfDay 
        HAVING trips >= :minTrips
        ORDER BY trips DESC
    """, nativeQuery = true)
    List<Object[]> findTripsByHourOfDayFiltered(Long minTrips);
}