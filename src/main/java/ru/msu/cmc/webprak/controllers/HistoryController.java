package ru.msu.cmc.webprak.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.msu.cmc.webprak.model.entity.Users;
import ru.msu.cmc.webprak.model.entity.Tickets;
import ru.msu.cmc.webprak.model.dao.impl.UsersDAOImpl;
import ru.msu.cmc.webprak.model.dao.impl.TicketsDAOImpl;
import ru.msu.cmc.webprak.model.dao.TicketsDAO;
import ru.msu.cmc.webprak.model.entity.Flights;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
public class HistoryController {

    @GetMapping("/history/")
    public String history(Model model) {
        model.addAttribute("result", false);
        return "history";
    }

    @PostMapping("/history/")
    public String history_result(
            @RequestParam(name = "user_id") Integer user_id,
            Model model
    ) {
        Users client = new UsersDAOImpl().getById(user_id);
        Collection<Tickets> tickets = new TicketsDAOImpl().getTicketsByFilter(TicketsDAO.getFilterBuilder().userId(user_id).build());
        List<Flights> flights = new ArrayList<>();
        for (Tickets ticket : tickets) {
            flights.add(ticket.getFlightId());
        }
        model.addAttribute("result", true);
        model.addAttribute("client", client);
        model.addAttribute("flights", flights);
        return "history";
    }
}