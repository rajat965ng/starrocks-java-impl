package com.lakehouse.app.repository;

import com.lakehouse.app.model.TaxiTripResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TaxiTripRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<TaxiTripResult> getTripsByHourOfDay() {
        String query = """
            SELECT COUNT(*) AS trips,
                   HOUR(lpep_pickup_datetime) AS hour_of_day
            FROM greentaxis
            GROUP BY hour_of_day
            ORDER BY trips DESC
        """;

        return jdbcTemplate.query(query, (rs, rowNum) ->
                new TaxiTripResult(
                        rs.getLong("trips"),
                        rs.getInt("hour_of_day")
                )
        );
    }

    // Additional method with optional filtering
    public List<TaxiTripResult> getTripsByHourOfDayWithFilter(Integer minTrips) {
        String query = """
            SELECT COUNT(*) AS trips,
                   HOUR(lpep_pickup_datetime) AS hour_of_day
            FROM greentaxis
            GROUP BY hour_of_day
            HAVING trips >= ?
            ORDER BY trips DESC
        """;

        return jdbcTemplate.query(query,
                new Object[]{minTrips != null ? minTrips : 0},
                (rs, rowNum) ->
                        new TaxiTripResult(
                                rs.getLong("trips"),
                                rs.getInt("hour_of_day")
                        )
        );
    }
}