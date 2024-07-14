package com.example.ecommerce.payLoads;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateForm {
    @NotNull
    private String name;
    @NotNull
    private int price;
    @NotNull
    private int quantity;
}
