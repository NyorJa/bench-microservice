package com.bench.practice.order.dto;

import com.bench.practice.order.model.OrderLineItems;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class OrderDto {
    private List<OrderLineItems> orderLineItemsList;
}
