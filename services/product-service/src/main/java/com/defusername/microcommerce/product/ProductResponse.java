package com.defusername.microcommerce.product;

import com.defusername.microcommerce.category.CategoryResponse;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ProductResponse(Long id, String name, String description, int availableQuantity, BigDecimal price,
							  CategoryResponse categoryResponse) {

}
