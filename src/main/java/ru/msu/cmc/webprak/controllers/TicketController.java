package ru.msu.cmc.webprak.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.msu.cmc.webprak.model.dao.impl.FlightsDAOImpl;
import ru.msu.cmc.webprak.model.dao.impl.TicketsDAOImpl;
import ru.msu.cmc.webprak.model.dao.impl.UsersDAOImpl;
import ru.msu.cmc.webprak.model.entity.Flights;
import ru.msu.cmc.webprak.model.entity.Tickets;
import ru.msu.cmc.webprak.model.entity.Users;

@Controller
public class TicketController {

    @GetMapping("/tickets/add/")
    public String tickets_page() {
        return "tickets";
    }

    @GetMapping("/tickets/success/")
    public String tickets_success(Model model) {
        model.addAttribute("success", true);
        model.addAttribute("error", false);
        return "tickets_result";
    }

    @GetMapping("/tickets/error/")
    public String tickets_error(Model model) {
        model.addAttribute("error", true);
        model.addAttribute("success", false);
        return "tickets_result";
    }

    @PostMapping("/tickets/add/")
    public String tickets_add(
            @RequestParam(name = "flight_id") Integer flight_id,
            @RequestParam(name = "user_id") Integer user_id,
            @RequestParam(name = "status") String status
    ) {
        try {
            Flights flight = new FlightsDAOImpl().getById(flight_id);
            Users client = new UsersDAOImpl().getById(user_id);
            Tickets ticket = new Tickets();
            ticket.setFlightId(flight);
            ticket.setStatus(status);
            ticket.setUserId(client);
            new TicketsDAOImpl().add(ticket);
            flight.setCntAvailableSeats(flight.getCntAvailableSeats() - 1);
            new FlightsDAOImpl().update(flight);
            return "redirect:/tickets/success/";
        } catch (Exception e) {
            return "redirect:/tickets/error/";
        }
    }
}