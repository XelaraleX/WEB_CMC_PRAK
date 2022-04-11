package ru.msu.cmc.webprak.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.msu.cmc.webprak.model.dao.AircraftDAO;
import ru.msu.cmc.webprak.model.entity.Aircraft;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AircraftDAOTest {
    AircraftDAO dao;
    Aircraft aircraftTest1;
    Aircraft aircraftTest2;
    String runId;

    @BeforeEach
    public void setUp() {
        this.dao = DAOFactory.getInstance().getAircraftDAO();
        this.runId = UUID.randomUUID().toString();

        this.aircraftTest1 = new Aircraft();
        this.aircraftTest1.setModelName("TestName1" + this.runId);

        this.aircraftTest2 = new Aircraft();
        this.aircraftTest2.setModelName("TestName2" + this.runId);

        this.dao.add(aircraftTest1);
        this.dao.add(aircraftTest2);
    }

    @AfterEach
    public void cleanUp() {
        this.dao.delete(this.aircraftTest1);
        this.dao.delete(this.aircraftTest2);

        this.dao = null;
        this.runId = null;
        this.aircraftTest1 = null;
        this.aircraftTest2 = null;
    }

    @Test
    public void testGetByName() {
        Collection<Aircraft> allTestNames = this.dao.getAircraftByFilter(
                AircraftDAO.getFilterBuilder()
                        .modelName(this.runId)
                        .build()
        );
        Set<Aircraft> expected = new HashSet<>();
        expected.add(this.aircraftTest1);
        expected.add(this.aircraftTest2);

        Set<Aircraft> got = new HashSet<>(allTestNames);

        assertEquals(expected, got);

        Collection<Aircraft> onlyTest1 = this.dao.getAircraftByFilter(
                AircraftDAO.getFilterBuilder()
                        .modelName("TestName1" + this.runId)
                        .build()
        );

        expected = new HashSet<>();
        expected.add(this.aircraftTest1);

        got = new HashSet<>(onlyTest1);

        assertEquals(expected, got);
    }
}
