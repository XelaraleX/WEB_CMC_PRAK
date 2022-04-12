package ru.msu.cmc.webprak;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.msu.cmc.webprak.model.DAOFactory;
import ru.msu.cmc.webprak.model.dao.AircraftDAO;
import ru.msu.cmc.webprak.model.dao.AirlinesDAO;
import ru.msu.cmc.webprak.model.dao.AirportsDAO;
import ru.msu.cmc.webprak.model.dao.BonusProgramDAO;
import ru.msu.cmc.webprak.model.dao.FlightsDAO;
import ru.msu.cmc.webprak.model.dao.TicketsDAO;
import ru.msu.cmc.webprak.model.dao.UsersDAO;
import ru.msu.cmc.webprak.model.entity.Aircraft;
import ru.msu.cmc.webprak.model.entity.Airlines;
import ru.msu.cmc.webprak.model.entity.Airports;
import ru.msu.cmc.webprak.model.entity.BonusProgram;
import ru.msu.cmc.webprak.model.entity.Flights;
import ru.msu.cmc.webprak.model.entity.Tickets;
import ru.msu.cmc.webprak.model.entity.Users;

@SpringBootApplication
public class WebprakApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebprakApplication.class, args);
		SpringApplication.run(WebprakApplication.class, args);
		AircraftDAO dao = DAOFactory.getInstance().getAircraftDAO();
		System.out.println(dao.getAircraftByFilter(AircraftDAO.getFilterBuilder().build()));
	}

}
