package com.defusername.microcommerce.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record CustomerRequest(String id, @NotNull(message = "Customer firstname is required") String firstName,
							  @NotNull(message = "Customer lastname is required") String lastName,
							  @NotNull(message = "Customer email is required") @Email String email,
							  Address address) {

}
