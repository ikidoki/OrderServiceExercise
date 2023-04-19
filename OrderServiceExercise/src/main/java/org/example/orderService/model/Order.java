package org.example.orderService.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class Order {
    private String orderId;
    private List<OrderItemOutput> items;
    private double cost;
}
