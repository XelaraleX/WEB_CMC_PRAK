package ru.msu.cmc.webprak.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import ru.msu.cmc.webprak.model.entity.Users;
import ru.msu.cmc.webprak.model.dao.UsersDAO;
import ru.msu.cmc.webprak.model.dao.impl.UsersDAOImpl;

import java.util.Collection;

@Controller
public class ClientController {

    @GetMapping("/clients/")
    public String clients(Model model) {
        Collection<Users> clients = new UsersDAOImpl().getUsersByFilter(UsersDAO.getFilterBuilder().build());
        model.addAttribute("clients", clients);
        return "clients";
    }

    @GetMapping("/clients/add/")
    public String add_clients_page(Model model) {
        model.addAttribute("add", true);
        model.addAttribute("edit", false);
        model.addAttribute("action", "/clients/add/");
        return "client";
    }

    @PostMapping("/clients/add/")
    public String add_client(
            @RequestParam(name = "clientName") String clientName,
            @RequestParam(name = "email", required = false) String email,
            @RequestParam(name = "phone", required = false) String phone
    ) {
        Users client = new Users();
        client.setFullName(clientName);
        client.setEmail(email);
        client.setPhoneNumber(phone);
        client.setUserLogin(clientName);
        client.setUserPassword(phone);
        client.setUserStatus("new");
        new UsersDAOImpl().add(client);
        return "redirect:/clients/";
    }

    @GetMapping("/clients/edit/{id}")
    public String edit_client_page(@PathVariable(name = "id") Integer clientId, Model model) {
        model.addAttribute("add", false);
        model.addAttribute("edit", true);
        model.addAttribute("action", String.format("/clients/edit/%d", clientId));
        return "client";
    }

    @PostMapping("/clients/edit/{id}")
    public String edit_client(
            @PathVariable(name = "id") Integer clientId,
            @RequestParam(name = "clientName") String clientName,
            @RequestParam(name = "email", required = false) String email,
            @RequestParam(name = "phone", required = false) String phone
    ) {
        Users client = new UsersDAOImpl().getById(clientId);

        if (email != null) {
            client.setEmail(email);
        }
        if (phone != null) {
            client.setPhoneNumber(phone);
        }
        if (clientName != null) {
            client.setFullName(clientName);
        }
        new UsersDAOImpl().update(client);
        return "redirect:/clients/";
    }

    @GetMapping("/clients/delete/{id}")
    public String delete_client(@PathVariable(name = "id") Integer clientId) {
        Users client = new UsersDAOImpl().getById(clientId);
        new UsersDAOImpl().delete(client);
        return "redirect:/clients/";
    }
}