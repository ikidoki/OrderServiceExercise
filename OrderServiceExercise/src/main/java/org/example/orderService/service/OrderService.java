package org.example.orderService.service;

import org.example.orderService.model.Order;
import org.example.orderService.model.OrderItemInput;
import org.example.orderService.model.OrderItemOutput;
import org.example.orderService.model.ProductType;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderService {

    public OrderService(){
    }

    private double calculateOrderCost(ProductType productType, int quantity) {
        if (!EnumSet.allOf(ProductType.class).contains(productType)) {
            throw new IllegalArgumentException("Invalid product type: " + productType);
        }
        final double price = productType.getPrice() * (double)quantity;
        return price;
    }

    private Integer calculateEffectiveQuantityAfterOffer(ProductType productType, int quantity) {
        Integer effectiveQuantity = 0;
        switch (productType) {
            // effective quantity when buy 1 get 1 free
            case APPLE:
                effectiveQuantity = quantity / 2 + quantity % 2;
                return effectiveQuantity;
            // effective quantity when 3 for price of 2
            case ORANGE:
                effectiveQuantity = (quantity / 3) * 2 + quantity % 3;
                return effectiveQuantity;
            default:
                throw new IllegalArgumentException("Invalid product Type: " + productType);
        }
    }

    public Order createOrder(List<OrderItemInput> items){
        List<OrderItemOutput> itemsOutput = new ArrayList<>();
        double totalCost = 0.0;
        Map<ProductType, Integer> consolidatedQuantities = new HashMap<>();

        // Consolidate duplicate items quantity count
        for (OrderItemInput item : items) {
            consolidatedQuantities.merge(item.getProductType(), item.getQuantity(), Integer::sum);
        }

        for (Map.Entry<ProductType, Integer> entry : consolidatedQuantities.entrySet()) {
            double itemCost = calculateOrderCost(entry.getKey(), calculateEffectiveQuantityAfterOffer(entry.getKey(), entry.getValue()));
            itemsOutput.add(new OrderItemOutput(entry.getKey(), entry.getValue(), itemCost));
            totalCost = totalCost + itemCost;
        }
        final String orderId = UUID.randomUUID().toString();
        final Order order = new Order(orderId, itemsOutput, totalCost);

        return order;
    }
}
