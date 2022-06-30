package com.aptech.common.entity.product;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.aptech.common.entity.Customer;
import com.aptech.common.entity.IdBasedEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "product_images")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductImage extends IdBasedEntity {

	@Column(nullable = false)
	private String name;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	@Transient
	public String getImagePath() {
		return "/product-images/" + product.getId() + "/extras/" + this.name;
	}

}
