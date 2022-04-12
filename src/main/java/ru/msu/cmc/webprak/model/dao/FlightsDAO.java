package ru.msu.cmc.webprak.model.dao;

import lombok.Builder;
import lombok.Getter;
import ru.msu.cmc.webprak.model.entity.Flights;

import java.time.LocalDateTime;
import java.util.Collection;

public interface FlightsDAO extends BaseDAO<Flights> {
    @Builder
    @Getter
    class Filter {
        private String timeDep;
        private String timeArr;
        private Integer flightCost;
        private Integer cntSeats;
        private Integer cntAvailableSeats;
    }

    static Filter.FilterBuilder getFilterBuilder() {
        return Filter.builder();
    }

    Collection<Flights> getFlightsByFilter(Filter filter);
}
