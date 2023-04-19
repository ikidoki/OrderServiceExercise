package org.example.orderService.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemOutput {
    private ProductType productType;
    private int quantity;
    private double price;
}
