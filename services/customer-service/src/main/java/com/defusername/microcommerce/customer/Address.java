package com.defusername.microcommerce.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Validated
public class Address {

	private String street;
	private String houseNo;
	private String zipCode;
	private String city;
	private String country;

}
