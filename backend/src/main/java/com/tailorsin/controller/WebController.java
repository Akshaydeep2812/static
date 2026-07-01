package com.tailorsin.controller;

import com.tailorsin.model.Customer;
import com.tailorsin.model.Order;
import com.tailorsin.model.User;
import com.tailorsin.repository.DataStore;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class WebController {
    private final DataStore dataStore;

    public WebController(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("orders", dataStore.getOrders());
        model.addAttribute("customers", dataStore.getCustomers());
        model.addAttribute("orderStages", dataStore.getStages());
        return "index";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String role,
                        @RequestParam String identity,
                        @RequestParam String password,
                        Model model) {
        boolean authenticated = dataStore.authenticate(identity, password, role).isPresent();
        if (!authenticated) {
            model.addAttribute("errorMessage", "Invalid credentials. Try admin/admin123 or customer/customer123.");
            return "login";
        }
        if (role.equalsIgnoreCase("ADMIN")) {
            return "redirect:/admin";
        }
        return "redirect:/";
    }

    @GetMapping("/admin")
    public String adminDashboard(Model model) {
        List<Order> orders = dataStore.getOrders();
        List<Customer> customers = dataStore.getCustomers();
        model.addAttribute("orders", orders);
        model.addAttribute("customers", customers);
        return "dashboard";
    }
}
