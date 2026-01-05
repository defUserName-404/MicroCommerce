package com.defusername.microcommerce.product;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ProductRequest(Long id, @NotNull(message = "Product name is required") String name, String description,
							 @Positive(message = "Product available quantity should be in positive number") @NotNull(message = "Product available quantity is required") int availableQuantity,
							 @Positive(message = "Product price should be in positive number") @NotNull(message = "Product price is required") BigDecimal price,
							 @NotNull(message = "Product categoryName is required") Long categoryId) {

}
