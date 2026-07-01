package com.tailorsin.repository;

import com.tailorsin.model.Customer;
import com.tailorsin.model.Order;
import com.tailorsin.model.User;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class DataStore {
    private static final List<String> STAGES = List.of("Cutting", "Stitching", "Stitching Complete", "Ironing", "Packing");

    private final List<Order> orders = new ArrayList<>();
    private final List<Customer> customers = new ArrayList<>();
    private final List<User> users = new ArrayList<>();

    @PostConstruct
    public void init() {
        orders.add(new Order("TLR-1024", "Rohan Sharma", "Suit", 2999, "Stitching", "Out for delivery", "14 Rosewood Lane, Mumbai", "Akash"));
        orders.add(new Order("TLR-1008", "Priya Joshi", "Kurta", 1099, "Ironing", "Packing", "82 Green Avenue, Pune", "Riya"));
        orders.add(new Order("TLR-1031", "Amit Patel", "Shirt", 799, "Packing", "Ready for pickup", "23 Lake View, Bangalore", "Meera"));

        customers.add(new Customer("Rohan Sharma", "rohan@example.com", "9876543210", "TLR-1024"));
        customers.add(new Customer("Priya Joshi", "priya@example.com", "9123456780", "TLR-1008"));
        customers.add(new Customer("Amit Patel", "amit@example.com", "9988776655", "TLR-1031"));

        users.add(new User("admin@tailors.in", "9000000000", "admin123", "ADMIN"));
        users.add(new User("customer@tailors.in", "9000000001", "customer123", "CUSTOMER"));
    }

    public List<Order> getOrders() {
        return Collections.unmodifiableList(orders);
    }

    public Optional<Order> findOrder(String id) {
        return orders.stream().filter(order -> order.getId().equalsIgnoreCase(id)).findFirst();
    }

    public boolean advanceOrder(String orderId) {
        return findOrder(orderId).map(order -> {
            int currentIndex = STAGES.indexOf(order.getStatus());
            int nextIndex = Math.min(currentIndex + 1, STAGES.size() - 1);
            order.setStatus(STAGES.get(nextIndex));
            if (order.getStatus().equals("Packing")) {
                order.setDeliveryStatus("Ready for pickup");
            }
            return true;
        }).orElse(false);
    }

    public List<Customer> getCustomers() {
        return Collections.unmodifiableList(customers);
    }

    public Optional<User> authenticate(String identity, String password, String role) {
        return users.stream()
                .filter(user -> user.matchesIdentity(identity))
                .filter(user -> user.getPassword().equals(password))
                .filter(user -> user.getRole().equalsIgnoreCase(role))
                .findFirst();
    }

    public List<String> getStages() {
        return STAGES;
    }
}
