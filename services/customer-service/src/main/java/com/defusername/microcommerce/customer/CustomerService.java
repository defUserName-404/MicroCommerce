package com.defusername.microcommerce.customer;

import com.defusername.microcommerce.exception.CustomerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

	private final CustomerRepository repository;
	private final CustomerMapper mapper;

	public void createNewCustomer(CustomerRequest request) {
		repository.save(mapper.toCustomer(request));
	}

	public void updateExistingCustomer(CustomerRequest request) {
		final Customer customer = repository.findById(request.id())
											.orElseThrow(() -> new CustomerNotFoundException(String.format(
													"Cannot update customer:: No customer found with provided ID:: %s",
													request.id()
											)));
		final Customer updatedCustomer = mergerCustomer(customer, request);
		repository.save(updatedCustomer);
	}

	private Customer mergerCustomer(Customer customer, CustomerRequest request) {
		if (StringUtils.isNotBlank(request.firstName())) {
			customer.setFirstName(request.firstName());
		}
		if (StringUtils.isNotBlank(request.lastName())) {
			customer.setFirstName(request.lastName());
		}
		if (StringUtils.isNotBlank(request.email())) {
			customer.setFirstName(request.email());
		}
		if (request.address() != null) {
			customer.setAddress(request.address());
		}
		return customer;
	}

	public List<CustomerResponse> getAllCustomers() {
		return repository.findAll()
						 .stream()
						 .map(mapper::fromCustomer)
						 .collect(Collectors.toList());
	}

	public CustomerResponse getCustomerById(String id) {
		return repository.findById(id)
						 .map(mapper::fromCustomer)
						 .orElseThrow(() -> new CustomerNotFoundException(String.format(
								 "Cannot update customer:: No customer found with provided ID:: %s",
								 id
						 )));
	}

	public void deleteCustomerById(String id) {
		repository.deleteById(id);
	}

}
