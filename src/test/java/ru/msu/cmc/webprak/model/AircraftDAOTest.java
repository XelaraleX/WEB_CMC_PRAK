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

    }
}
