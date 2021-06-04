package com.example.taiprojekt.product;

import com.example.taiprojekt.product.dtos.ProductFactory;
import com.example.taiprojekt.product.dtos.ProductRequest;
import com.example.taiprojekt.product.dtos.ProductResponse;
import com.example.taiprojekt.product.exceptions.ProductNotFoundException;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductResponse> getAllProducts() {
        List<ProductResponse> products = new ArrayList<>();
        productRepository.findAll().forEach(x -> {
            ProductResponse productResponse = ProductFactory.productEntityToResponse(x);
            products.add(productResponse);
        });
        return products;
    }

    public ProductResponse getProduct(Long id) {
        ProductEntity productEntity = productRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new);

        return ProductFactory.productEntityToResponse(productEntity);
    }

    public ProductResponse saveProduct(ProductRequest productRequest) {
        return ProductFactory.productEntityToResponse(
                productRepository.save(
                        ProductFactory.productRequestToEntity(productRequest)
                )
        );
    }
}
