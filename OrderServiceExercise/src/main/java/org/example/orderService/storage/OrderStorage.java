package org.example.orderService.storage;

import org.example.orderService.model.Order;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class OrderStorage {

    private final Map<String, Order> orders;

    public OrderStorage() {
        this.orders = new HashMap<>();
    }

    public Order save(Order order) {
        orders.put(order.getOrderId(), order);
        return order;
    }

    public Order getByOrderId(String orderId) {
        return orders.get(orderId);
    }

    public List<Order> getAll() {
        return new ArrayList<>(orders.values());
    }
}