package com.lakehouse.app.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "greentaxis")
public class TaxiTrip {
    @Id
    @Column(name = "VendorID")
    private Integer vendorId;

    @Column(name = "lpep_pickup_datetime")
    private LocalDateTime pickupDateTime;

    @Column(name = "lpep_dropoff_datetime")
    private LocalDateTime dropoffDateTime;

    @Column(name = "store_and_fwd_flag")
    private String storeAndFwdFlag;

    @Column(name = "RatecodeID")
    private Long ratecodeId;

    @Column(name = "PULocationID")
    private Integer puLocationId;

    @Column(name = "DOLocationID")
    private Integer doLocationId;

    @Column(name = "passenger_count")
    private Long passengerCount;

    @Column(name = "trip_distance")
    private Double tripDistance;

    @Column(name = "fare_amount")
    private Double fareAmount;

    @Column(name = "total_amount")
    private Double totalAmount;
}