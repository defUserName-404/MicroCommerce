package com.defusername.microcommerce.product;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record ProductPurchaseResponse(Long productId, String name, String description, BigDecimal price, int quantity) {

}
