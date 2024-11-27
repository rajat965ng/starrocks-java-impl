package com.lakehouse.app.service;


import com.lakehouse.app.dto.TaxiTripResult;
import com.lakehouse.app.repository.TaxiTripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaxiTripService {
    @Autowired
    private TaxiTripRepository taxiTripRepository;

    public List<TaxiTripResult> getTripsPerHour() {
        return taxiTripRepository.findTripsByHourOfDay().stream()
                .map(result -> new TaxiTripResult(
                        Long.parseLong(result[1].toString()),
                        Integer.parseInt(result[0].toString())
                ))
                .collect(Collectors.toList());
    }

    public List<TaxiTripResult> getTripsPerHourFiltered(Long minTrips) {
        return taxiTripRepository.findTripsByHourOfDayFiltered(minTrips).stream()
                .map(result -> new TaxiTripResult(
                        Long.parseLong(result[1].toString()),
                        Integer.parseInt(result[0].toString())
                ))
                .collect(Collectors.toList());
    }

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