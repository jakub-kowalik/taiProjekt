package com.example.taiprojekt.order.dtos;

import com.example.taiprojekt.product.dtos.ProductResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    Long id;
    String name;
    List<ProductResponse> products;
}
