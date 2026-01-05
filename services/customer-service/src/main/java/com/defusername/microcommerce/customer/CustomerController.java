package com.defusername.microcommerce.customer;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

	private final CustomerService service;

	@PostMapping
	public ResponseEntity<Void> createCustomer(@RequestBody @Valid CustomerRequest request) {
		service.createNewCustomer(request);
		return ResponseEntity.status(HttpStatus.OK)
							 .build();
	}

	@PutMapping
	public ResponseEntity<Void> updateCustomer(@RequestBody @Valid CustomerRequest request) {
		service.updateExistingCustomer(request);
		return ResponseEntity.status(HttpStatus.ACCEPTED)
							 .build();
	}

	@GetMapping(path = "/all")
	public ResponseEntity<List<CustomerResponse>> getAllCustomers() {
		final List<CustomerResponse> customersList = service.getAllCustomers();
		return ResponseEntity.status(HttpStatus.OK)
							 .body(customersList);
	}

	@GetMapping(path = "$/{id}")
	public ResponseEntity<CustomerResponse> getCustomerById(@PathVariable String id) {
		final CustomerResponse customer = service.getCustomerById(id);
		return ResponseEntity.status(HttpStatus.OK)
							 .body(customer);
	}

	@DeleteMapping(path = "$/{id}")
	public ResponseEntity<Void> deleteCustomerById(@PathVariable String id) {
		service.deleteCustomerById(id);
		return ResponseEntity.status(HttpStatus.ACCEPTED)
							 .build();
	}

}
