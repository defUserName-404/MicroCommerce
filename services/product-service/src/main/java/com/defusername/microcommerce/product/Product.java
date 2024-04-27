package com.defusername.microcommerce.product;

import com.defusername.microcommerce.category.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "name", nullable = false)
	private String name;
	@Column(name = "description")
	private String description;
	@Column(name = "available_quantity", nullable = false)
	private int availableQuantity;
	@Column(name = "price", nullable = false)
	private BigDecimal price;
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

}
