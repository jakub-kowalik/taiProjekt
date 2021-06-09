package com.example.taiprojekt.lineitem;

import com.example.taiprojekt.product.dtos.ProductResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LineItemResponse {

        private long id;
        private ProductResponse product;
        private int quantity;
}
