package com.ws101.Alastoy_Espano.EcommerceApi.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode

public class Product {
    private Integer id;
    private String name;
    private String description;
    private double price;
    private String category;
    private Integer stockQuantity;
    private String imageUrl;
}
