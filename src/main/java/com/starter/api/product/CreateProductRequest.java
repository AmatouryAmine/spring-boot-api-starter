package com.starter.api.product;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class CreateProductRequest {
    @NotBlank
    private String name;
    private String description;
    @NotNull @DecimalMin("0.01")
    private BigDecimal price;
}
