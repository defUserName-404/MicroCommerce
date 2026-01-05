package com.defusername.microcommerce.product;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {

	private final ProductService service;

	@PostMapping
	public ResponseEntity<Void> createProduct(@RequestBody @Valid ProductRequest request) {
		service.createProduct(request);
		return ResponseEntity.status(HttpStatus.OK)
							 .build();
	}

	@PostMapping(path = "/purchase")
	public ResponseEntity<List<ProductPurchaseResponse>> purchaseProducts(@RequestBody List<ProductPurchaseRequest> request) {
		final List<ProductPurchaseResponse> response = service.purchaseProducts(request);
		return ResponseEntity.status(HttpStatus.OK)
							 .body(response);
	}

	@GetMapping(path = "$/{id}")
	public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id) {
		final ProductResponse product = service.getProductById(id);
		return ResponseEntity.status(HttpStatus.OK)
							 .body(product);
	}

	@GetMapping(path = "/all")
	public ResponseEntity<List<ProductResponse>> getAllProducts() {
		final List<ProductResponse> productsList = service.getAllProducts();
		return ResponseEntity.status(HttpStatus.OK)
							 .body(productsList);
	}

}
