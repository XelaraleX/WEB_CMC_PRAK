package ru.msu.cmc.webprak.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.msu.cmc.webprak.model.dao.AirportsDAO;
import ru.msu.cmc.webprak.model.entity.Airports;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AirportsDAOTest {
    AirportsDAO dao;
    Airports airportsTest1;
    Airports airportsTest2;
    String runId;

    @BeforeEach
    public void setUp() {
        this.dao = DAOFactory.getInstance().getAirportsDAO();
        this.runId = UUID.randomUUID().toString();

        this.airportsTest1 = new Airports();
        this.airportsTest1.setAirportName("TestName1" + this.runId);
        this.airportsTest1.setAirportTown("TestTown1" + this.runId);

        this.airportsTest2 = new Airports();
        this.airportsTest2.setAirportName("TestName2" + this.runId);
        this.airportsTest2.setAirportTown("TestTown2" + this.runId);

        this.dao.add(airportsTest1);
        this.dao.add(airportsTest2);
    }

    @AfterEach
    public void cleanUp() {
        this.dao.delete(airportsTest1);
        this.dao.delete(airportsTest2);

        this.dao = null;
        this.runId = null;
        this.airportsTest1 = null;
        this.airportsTest2 = null;
    }

    @Test
    public void testGetByName() {
        Collection<Airports> all = this.dao.getAirportsByFilter(
                AirportsDAO.getFilterBuilder()
                        .airportName(this.runId)
                        .build()
        );
        Set<Airports> expected = new HashSet<>();
        expected.add(this.airportsTest1);
        expected.add(this.airportsTest2);

        Set<Airports> got = new HashSet<>(all);

        assertEquals(expected, got);

        Collection<Airports> onlyTest1 = this.dao.getAirportsByFilter(
                AirportsDAO.getFilterBuilder()
                        .airportName("TestName1" + this.runId)
                        .build()
        );

        expected = new HashSet<>();
        expected.add(this.airportsTest1);

        got = new HashSet<>(onlyTest1);

        assertEquals(expected, got);
    }

    @Test
    public void testGetByTimezone() {
        Collection<Airports> all = this.dao.getAirportsByFilter(
                AirportsDAO.getFilterBuilder()
                        .airportTown(this.runId)
                        .build()
        );
        Set<Airports> expected = new HashSet<>();
        expected.add(this.airportsTest1);
        expected.add(this.airportsTest2);

        Set<Airports> got = new HashSet<>(all);

        assertEquals(expected, got);

        Collection<Airports> onlyTest1 = this.dao.getAirportsByFilter(
                AirportsDAO.getFilterBuilder()
                        .airportTown("TestTown1" + this.runId)
                        .build()
        );

        expected = new HashSet<>();
        expected.add(this.airportsTest1);

        got = new HashSet<>(onlyTest1);

        assertEquals(expected, got);
    }
}
