package org.example.orderService.service;

import org.example.orderService.model.Order;
import org.example.orderService.model.OrderItemInput;
import org.example.orderService.model.ProductType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderServiceTests {

    private OrderService orderService;

    @BeforeEach
    public void setUp() {
        orderService = new OrderService();
    }

    @Test
    public void testCalculateSeparateOrderCost() {
        // Create sample items
        List<OrderItemInput> items = Arrays.asList(
                new OrderItemInput(ProductType.APPLE, 1),
                new OrderItemInput(ProductType.ORANGE, 2),
                new OrderItemInput(ProductType.APPLE, 1)
        );

        // Create actual order
        Order order = orderService.createOrder(items);

        // Validate total price and consolidated item list count
        assertEquals(Double.valueOf(1.1), order.getCost());
        assertEquals(order.getItems().size(), 2);

    }
}
