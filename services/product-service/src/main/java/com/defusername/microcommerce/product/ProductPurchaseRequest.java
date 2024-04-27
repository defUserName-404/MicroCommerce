package com.defusername.microcommerce.product;

import jakarta.validation.constraints.NotNull;

public record ProductPurchaseRequest(@NotNull(message = "Product ID is required") Long productId,
									 @NotNull(message = "Product quantity is required") int quantity) {

}
