package com.defusername.microcommerce.category;

import com.defusername.microcommerce.product.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "category")
public class Category {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "name", nullable = false)
	private String name;
	@Column(name = "description")
	private String description;
	@OneToMany(mappedBy = "category", cascade = {CascadeType.REMOVE, CascadeType.MERGE})
	private Set<Product> products;

}
