package com.defusername.microcommerce.product;

import com.defusername.microcommerce.category.CategoryResponse;
import org.springframework.stereotype.Service;

@Service
public class ProductMapper {

	public Product fromResponseToEntity(ProductRequest request) {
		if (request == null) {
			return null;
		}
		return Product.builder()
					  .id(request.id())
					  .name(request.name())
					  .description(request.description())
					  .availableQuantity(request.availableQuantity())
					  .price(request.price())
					  .build();
	}

	public ProductResponse fromEntityToResponse(Product product) {
		if (product == null) {
			return null;
		}
		return ProductResponse.builder()
							  .id(product.getId())
							  .name(product.getName())
							  .description(product.getDescription())
							  .availableQuantity(product.getAvailableQuantity())
							  .price(product.getPrice())
							  .categoryResponse(CategoryResponse.builder()
																.categoryName(product.getCategory()
																					 .getName())
																.categoryDescription(product.getCategory()
																							.getDescription())
																.build())
							  .build();
	}

	public ProductPurchaseResponse fromEntityToPurchaseResponse(Product product) {
		if (product == null) {
			return null;
		}
		return ProductPurchaseResponse.builder()
									  .productId(product.getId())
									  .name(product.getName())
									  .description(product.getDescription())
									  .price(product.getPrice())
									  .quantity(product.getAvailableQuantity())
									  .build();

	}

}
