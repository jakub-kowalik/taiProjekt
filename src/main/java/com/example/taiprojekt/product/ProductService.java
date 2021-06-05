package com.example.taiprojekt.product;

import com.example.taiprojekt.entities.ProductEntity;
import com.example.taiprojekt.product.dtos.ProductFactory;
import com.example.taiprojekt.product.dtos.ProductRequest;
import com.example.taiprojekt.product.dtos.ProductResponse;
import com.example.taiprojekt.product.exceptions.ProductNotFoundException;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(ProductFactory::productEntityToResponse)
                .collect(Collectors.toList());
    }

    public ProductResponse getProduct(Long id) {
        ProductEntity productEntity = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        return ProductFactory.productEntityToResponse(productEntity);
    }

    public ProductResponse saveProduct(ProductRequest productRequest) {
        return ProductFactory.productEntityToResponse(
                productRepository.save(
                        ProductFactory.productRequestToEntity(productRequest)
                )
        );
    }

    public ProductResponse modifyProduct(Long productId, ProductRequest productRequest) {
        ProductEntity productEntity = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));

        productEntity.setName(productRequest.getName());

        return ProductFactory.productEntityToResponse(productRepository.save(productEntity));
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
