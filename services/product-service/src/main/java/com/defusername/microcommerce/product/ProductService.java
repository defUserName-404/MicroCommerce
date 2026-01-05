package com.defusername.microcommerce.product;

import com.defusername.microcommerce.exception.ProductPurchaseException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

	private final ProductRepository repository;
	private final ProductMapper mapper;

	public ProductResponse getProductById(Long id) {
		return repository.findById(id)
						 .map(mapper::fromEntityToResponse)
						 .orElseThrow(() -> new EntityNotFoundException("Product with ID " + id + " not found"));
	}

	public List<ProductResponse> getAllProducts() {
		return repository.findAll()
						 .stream()
						 .map(mapper::fromEntityToResponse)
						 .collect(Collectors.toList());
	}

	public void createProduct(ProductRequest request) {
		final Product product = mapper.fromResponseToEntity(request);
		repository.save(product);
	}

	@Transactional(rollbackFor = ProductPurchaseException.class)
	public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> request) {
		final List<Long> productIds = request.stream()
											 .map(ProductPurchaseRequest::productId)
											 .toList();
		var storedProducts = repository.findAllByIdInOrderById(productIds);
		if (productIds.size() != storedProducts.size()) {
			throw new ProductPurchaseException("One or more products does not exist");
		}
		var sortedRequest = request.stream()
								   .sorted(Comparator.comparing(ProductPurchaseRequest::productId))
								   .toList();
		var purchasedProducts = new ArrayList<ProductPurchaseResponse>();
		for (int i = 0; i < storedProducts.size(); i++) {
			var product = storedProducts.get(i);
			var productRequest = sortedRequest.get(i);
			if (product.getAvailableQuantity() < productRequest.quantity()) {
				throw new ProductPurchaseException(
						"Insufficient stock quantity for product with ID:: " + productRequest.productId());
			}
			var newAvailableQuantity = product.getAvailableQuantity() - productRequest.quantity();
			product.setAvailableQuantity(newAvailableQuantity);
			repository.save(product);
			purchasedProducts.add(mapper.fromEntityToPurchaseResponse(product));
		}
		return purchasedProducts;
	}

}
