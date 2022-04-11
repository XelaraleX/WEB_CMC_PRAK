package ru.msu.cmc.webprak.model.dao;

import lombok.Builder;
import lombok.Getter;
import ru.msu.cmc.webprak.model.entity.Airports;

import java.util.Collection;

public interface AirportsDAO extends BaseDAO<Airports> {
    @Builder
    @Getter
    class Filter {
        private String airportName;
        private Float width;
        private Float longitude;
        private String timezone;
    }

    static Filter.FilterBuilder getFilterBuilder() {
        return Filter.builder();
    }

    Collection<Airports> getAirportsByFilter(Filter filter);
}
