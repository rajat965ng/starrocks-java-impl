package com.lakehouse.app.service;


import com.lakehouse.app.model.TaxiTripResult;
import com.lakehouse.app.repository.TaxiTripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaxiTripService {
    @Autowired
    private TaxiTripRepository taxiTripRepository;

    public List<TaxiTripResult> getTripsPerHour() {
        return taxiTripRepository.getTripsByHourOfDay();
    }

    public List<TaxiTripResult> getTripsPerHourFiltered(Integer minTrips) {
        return taxiTripRepository.getTripsByHourOfDayWithFilter(minTrips);
    }

    // Additional analysis methods
    public Double getAverageTripsPerHour() {
        List<TaxiTripResult> trips = getTripsPerHour();
        return trips.stream()
                .mapToLong(TaxiTripResult::getTrips)
                .average()
                .orElse(0.0);
    }

    public TaxiTripResult getPeakHour() {
        return getTripsPerHour().stream()
                .max((a, b) -> a.getTrips().compareTo(b.getTrips()))
                .orElse(null);
    }
}
