package com.example.taiprojekt.order.dtos;

import com.example.taiprojekt.lineitem.LineItemRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
    @NotNull
    String name;

    List<LineItemRequest> product;
}
