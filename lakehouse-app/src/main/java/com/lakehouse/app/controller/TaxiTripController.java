package com.lakehouse.app.controller;


import com.lakehouse.app.dto.TaxiTripResult;
import com.lakehouse.app.service.TaxiTripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/taxi-trips")
public class TaxiTripController {
    @Autowired
    private TaxiTripService taxiTripService;

    @GetMapping("/hourly-distribution")
    public List<TaxiTripResult> getTripsPerHour() {
        return taxiTripService.getTripsPerHour();
    }

    @GetMapping("/hourly-distribution-filtered")
    public List<TaxiTripResult> getTripsPerHourFiltered(
            @RequestParam(required = false) Integer minTrips
    ) {
        return taxiTripService.getTripsPerHourFiltered(minTrips.longValue());
    }

    @GetMapping("/average-trips")
    public Double getAverageTripsPerHour() {
        return taxiTripService.getAverageTripsPerHour();
    }

    @GetMapping("/peak-hour")
    public TaxiTripResult getPeakHour() {
        return taxiTripService.getPeakHour();
    }
}
