package com.example.taiprojekt.order.dtos;

import com.example.taiprojekt.lineitem.dtos.LineItemResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    Long id;
    String name;
    Instant date;
    List<LineItemResponse> products;
}
