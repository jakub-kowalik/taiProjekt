package com.example.taiprojekt.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "lineitem")
public class LineItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    int quantity;

    @ManyToOne
    OrderEntity order;

    @ManyToOne
    ProductEntity product;
}
