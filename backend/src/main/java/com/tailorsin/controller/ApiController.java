package com.tailorsin.controller;

import com.tailorsin.model.Customer;
import com.tailorsin.model.LoginRequest;
import com.tailorsin.model.Order;
import com.tailorsin.repository.DataStore;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class ApiController {
    private final DataStore dataStore;

    public ApiController(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    @GetMapping("/orders")
    public List<Order> getOrders() {
        return dataStore.getOrders();
    }

    @GetMapping("/customers")
    public List<Customer> getCustomers() {
        return dataStore.getCustomers();
    }

    @PostMapping("/orders/{id}/advance")
    public ResponseEntity<Map<String, String>> advanceOrder(@PathVariable String id) {
        boolean updated = dataStore.advanceOrder(id);
        if (!updated) {
            return ResponseEntity.badRequest().body(Map.of("message", "Order not found"));
        }
        return ResponseEntity.ok(Map.of("message", "Order advanced"));
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest request) {
        boolean authenticated = dataStore.authenticate(request.getIdentity(), request.getPassword(), request.getRole()).isPresent();
        if (!authenticated) {
            return ResponseEntity.status(401).body(Map.of("message", "Invalid credentials"));
        }
        return ResponseEntity.ok(Map.of("message", "authenticated", "role", request.getRole()));
    }
}
