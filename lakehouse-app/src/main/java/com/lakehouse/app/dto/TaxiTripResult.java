package com.lakehouse.app.dto;

import lombok.*;


public class TaxiTripResult {
    private Long trips;
    private Integer hourOfDay;

    public TaxiTripResult(Long trips, Integer hourOfDay) {
        this.trips = trips;
        this.hourOfDay = hourOfDay;
    }

    public Long getTrips() {
        return trips;
    }

    public Integer getHourOfDay() {
        return hourOfDay;
    }
}
