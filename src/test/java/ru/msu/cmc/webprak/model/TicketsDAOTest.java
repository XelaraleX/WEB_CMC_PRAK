package ru.msu.cmc.webprak.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.msu.cmc.webprak.model.dao.*;
import ru.msu.cmc.webprak.model.entity.*;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TicketsDAOTest {
    TicketsDAO dao;
    String runId;
    Airlines airlines1;
    Airlines airlines2;
    Airports airports1;
    Airports airports2;
    Aircraft aircraft1;
    Aircraft aircraft2;
    Flights flights1;
    Flights flights2;
    Users users1;
    Users users2;
    Tickets tickets1;
    Tickets tickets2;

    @BeforeEach
    public void setUp() {
        this.dao = DAOFactory.getInstance().getTicketsDAO();
        this.runId = UUID.randomUUID().toString();

        this.airlines1 = new Airlines();
        this.airlines1.setAirlineName("TestName1" + this.runId);
        this.airlines1.setAirlineEmail("test1" + this.runId + "@mail.com");
        this.airlines1.setPhoneNumber("+1" + this.runId);

        this.airports1 = new Airports();
        this.airports1.setAirportName("TestName1" + this.runId);
        this.airports1.setWidth(0.1);
        this.airports1.setLongitude(1.1);
        this.airports1.setTimezone("TestTimezone1" + this.runId);

        this.aircraft1 = new Aircraft();
        this.aircraft1.setModelName("TestName1" + this.runId);

        this.flights1 = new Flights();
        this.flights1.setAirlineId(airlines1);
        this.flights1.setAirportIdDep(airports1);
        this.flights1.setAirportIdArr(airports1);
        this.flights1.setAircraftId(aircraft1);
        this.flights1.setTimeDep(LocalDateTime.parse("2022-04-12 00:01:01+03"));
        this.flights1.setTimeArr(LocalDateTime.parse("2022-04-12 00:02:01+03"));
        this.flights1.setFlightCost(1);
        this.flights1.setCntSeats(1);
        this.flights1.setCntAvailableSeats(1);

        this.users1 = new Users();
        this.users1.setUserStatus("TestStatus1" + this.runId);
        this.users1.setFullName("TestFullName1" + this.runId);
        this.users1.setAddress("TestAddress1" + this.runId);
        this.users1.setEmail("test1" + this.runId + "@mail.com");
        this.users1.setPhoneNumber("+1" + this.runId);
        this.users1.setUserLogin("TestLogin1" + this.runId);
        this.users1.setUserPassword("TestPassword1" + this.runId);

        this.tickets1 = new Tickets();
        this.tickets1.setFlightId(flights1);
        this.tickets1.setUserId(users1);
        this.tickets1.setStatus("TestStatus1" + this.runId);


        this.airlines2 = new Airlines();
        this.airlines2.setAirlineName("TestName2" + this.runId);
        this.airlines2.setAirlineEmail("test2" + this.runId + "@mail.com");
        this.airlines2.setPhoneNumber("+2" + this.runId);

        this.airports2 = new Airports();
        this.airports2.setAirportName("TestName2" + this.runId);
        this.airports2.setWidth(0.2);
        this.airports2.setLongitude(1.2);
        this.airports2.setTimezone("TestTimezone2" + this.runId);

        this.aircraft2 = new Aircraft();
        this.aircraft2.setModelName("TestName2" + this.runId);

        this.flights2 = new Flights();
        this.flights2.setAirlineId(airlines2);
        this.flights2.setAirportIdDep(airports2);
        this.flights2.setAirportIdArr(airports2);
        this.flights2.setAircraftId(aircraft2);
        this.flights2.setTimeDep(LocalDateTime.parse("2022-04-12 00:01:02+03"));
        this.flights2.setTimeArr(LocalDateTime.parse("2022-04-12 00:02:02+03"));
        this.flights2.setFlightCost(2);
        this.flights2.setCntSeats(2);
        this.flights2.setCntAvailableSeats(2);

        this.users2 = new Users();
        this.users2.setUserStatus("TestStatus2" + this.runId);
        this.users2.setFullName("TestFullName2" + this.runId);
        this.users2.setAddress("TestAddress2" + this.runId);
        this.users2.setEmail("test2" + this.runId + "@mail.com");
        this.users2.setPhoneNumber("+2" + this.runId);
        this.users2.setUserLogin("TestLogin2" + this.runId);
        this.users2.setUserPassword("TestPassword2" + this.runId);

        this.tickets1 = new Tickets();
        this.tickets1.setFlightId(flights2);
        this.tickets1.setUserId(users2);
        this.tickets1.setStatus("TestStatus2" + this.runId);

        AirlinesDAO airlinesDAO = DAOFactory.getInstance().getAirlinesDAO();
        airlinesDAO.add(airlines1);
        airlinesDAO.add(airlines2);

        AirportsDAO airportsDAO = DAOFactory.getInstance().getAirportsDAO();
        airportsDAO.add(airports1);
        airportsDAO.add(airports2);

        AircraftDAO aircraftDAO = DAOFactory.getInstance().getAircraftDAO();
        aircraftDAO.add(aircraft1);
        aircraftDAO.add(aircraft2);

        FlightsDAO flightsDAO = DAOFactory.getInstance().getFlightsDAO();
        flightsDAO.add(flights1);
        flightsDAO.add(flights2);

        UsersDAO usersDAO = DAOFactory.getInstance().getUsersDAO();
        usersDAO.add(users1);
        usersDAO.add(users2);

        this.dao.add(tickets1);
        this.dao.add(tickets2);
    }

    @AfterEach
    public void cleanUp() {
        this.dao.delete(tickets1);
        this.dao.delete(tickets2);

        AirlinesDAO airlinesDAO = DAOFactory.getInstance().getAirlinesDAO();
        airlinesDAO.delete(this.airlines1);
        airlinesDAO.delete(this.airlines2);

        AirportsDAO airportsDAO = DAOFactory.getInstance().getAirportsDAO();
        airportsDAO.delete(this.airports1);
        airportsDAO.delete(this.airports2);

        AircraftDAO aircraftDAO = DAOFactory.getInstance().getAircraftDAO();
        aircraftDAO.delete(this.aircraft1);
        aircraftDAO.delete(this.aircraft2);

        FlightsDAO flightsDAO = DAOFactory.getInstance().getFlightsDAO();
        flightsDAO.delete(this.flights1);
        flightsDAO.delete(this.flights2);

        UsersDAO usersDAO = DAOFactory.getInstance().getUsersDAO();
        usersDAO.delete(this.users1);
        usersDAO.delete(this.users2);

        this.dao = null;
        this.runId = null;
        this.airlines1 = null;
        this.airlines2 = null;
        this.airports1 = null;
        this.airports2 = null;
        this.aircraft1 = null;
        this.aircraft2 = null;
        this.flights1 = null;
        this.flights2 = null;
        this.users1 = null;
        this.users2 = null;
        this.tickets1 = null;
        this.tickets2 = null;
    }

    @Test
    public void testGetByStatus() {
        Collection<Tickets> all = this.dao.getTicketsByFilter(
                TicketsDAO.getFilterBuilder()
                        .status(this.runId)
                        .build()
        );
        Set<Tickets> expected = new HashSet<>();
        expected.add(this.tickets1);
        expected.add(this.tickets2);

        Set<Tickets> got = new HashSet<>(all);

        assertEquals(expected, got);

        Collection<Tickets> onlyTest1 = this.dao.getTicketsByFilter(
                TicketsDAO.getFilterBuilder()
                        .status("TestStatus1" + this.runId)
                        .build()
        );

        expected = new HashSet<>();
        expected.add(this.tickets1);

        got = new HashSet<>(onlyTest1);

        assertEquals(expected, got);
    }
}
