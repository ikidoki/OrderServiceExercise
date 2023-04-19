package org.example.orderService.service;

import org.example.orderService.model.Order;
import org.example.orderService.model.OrderItemInput;
import org.example.orderService.model.OrderItemOutput;
import org.example.orderService.model.ProductType;
import org.example.orderService.storage.OrderStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OrderServiceTests {

    private OrderService orderService;
    private OrderStorage orderStorage;

    @BeforeEach
    public void setUp() {
        orderStorage = mock(OrderStorage.class);
        orderService = new OrderService(orderStorage);
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

    @Test
    public void testGetOrderById() {
        // Create order
        String orderId = UUID.randomUUID().toString();
        List<OrderItemOutput> itemsOutput = Arrays.asList(
                new OrderItemOutput(ProductType.APPLE, 3, Double.valueOf(1.2)),
                new OrderItemOutput(ProductType.ORANGE, 5, Double.valueOf(1.0))
        );
        Order expectedOrder = new Order(orderId, itemsOutput, Double.valueOf(2.2));
        // Assume order Storage retrieval
        when(orderStorage.getByOrderId(orderId)).thenReturn(expectedOrder);
        // Execute get order by ID
        Order actualOrder = orderService.getOrderById(orderId);
        // Validate result
        assertEquals(expectedOrder, actualOrder);
    }

    @Test
    public void testGetAll() {
        // Create items and orders
        List<OrderItemOutput> itemsOutput1 = Arrays.asList(
                new OrderItemOutput(ProductType.APPLE, 0, Double.valueOf(0)),
                new OrderItemOutput(ProductType.ORANGE, 0, Double.valueOf(0))
        );
        List<OrderItemOutput> itemsOutput2 = Arrays.asList(
                new OrderItemOutput(ProductType.APPLE, 0,Double.valueOf(0)),
                new OrderItemOutput(ProductType.ORANGE, 0, Double.valueOf(0))
        );
        Order order1 = new Order(UUID.randomUUID().toString(), itemsOutput1, Double.valueOf(0));
        Order order2 = new Order(UUID.randomUUID().toString(), itemsOutput2, Double.valueOf(0));
        List<Order> expectedOrders = Arrays.asList(order1, order2);
        // Assume order Storage retrieval
        when(orderStorage.getAll()).thenReturn(expectedOrders);
        // Execute get All order
        List<Order> actualOrders = orderService.getAllOrders();
        // Validate result
        assertEquals(expectedOrders, actualOrders);
    }
}
