package com.example.ecommerce.payLoads;

import com.example.ecommerce.entities.Categories;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductForm {
    @NotNull
    private String name;

    @NotNull
    private double price;

    @NotNull
    private int quantity;

    @NotNull
    private Long category;
}
