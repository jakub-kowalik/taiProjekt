package com.example.taiprojekt.product;

import com.example.taiprojekt.product.dtos.ProductRequest;
import com.example.taiprojekt.product.dtos.ProductResponse;
import com.example.taiprojekt.product.exceptions.ProductNotFoundException;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<ProductResponse> getRegionsPage() {
        return productService.getAllProducts();
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductResponse> getRegion(@PathVariable Long id) {
        try {
            ProductResponse productResponse = productService.getProduct(id);
            return new ResponseEntity<>(productResponse, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (ProductNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<ProductResponse> saveProduct(@RequestBody ProductRequest productRequest) {
        try {
            ProductResponse productResponse = productService.saveProduct(productRequest);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(productResponse.getId())
                    .toUri();
            return ResponseEntity.created(location).body(productResponse);
        } catch (ProductNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
