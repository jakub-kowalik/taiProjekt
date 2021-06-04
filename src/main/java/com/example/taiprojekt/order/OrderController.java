package com.example.taiprojekt.order;

import com.example.taiprojekt.order.dtos.OrderRequest;
import com.example.taiprojekt.order.dtos.OrderResponse;
import com.example.taiprojekt.order.exceptions.OrderNotFoundException;
import com.example.taiprojekt.product.dtos.ProductRequest;
import com.example.taiprojekt.product.dtos.ProductResponse;
import com.example.taiprojekt.product.exceptions.ProductNotFoundException;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public List<OrderResponse> getOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("{id}")
    public ResponseEntity<OrderResponse> getOrder(@PathVariable Long id) {
        try {
            OrderResponse orderResponse = orderService.getOrder(id);
            return new ResponseEntity<>(orderResponse, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (ProductNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<OrderResponse> saveOrder(@RequestBody OrderRequest orderRequest) {
        try {
            OrderResponse orderResponse = orderService.saveOrder(orderRequest);
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(orderResponse.getId())
                    .toUri();
            return ResponseEntity.created(location).body(orderResponse);
        } catch (ProductNotFoundException | OrderNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<OrderResponse> modifyOrder(@PathVariable Long id, @Valid @RequestBody OrderRequest orderRequest) {
        try {
            OrderResponse productResponse = orderService.modifyOrder(id, orderRequest);
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
