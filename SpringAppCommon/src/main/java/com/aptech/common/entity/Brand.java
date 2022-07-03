package com.aptech.common.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import com.aptech.common.Constants;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "brands")
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Brand extends IdBasedEntity {

	@Column(nullable = false, length = 45, unique = true)
	private String name;

	@Column(nullable = false, length = 128)
	private String logo;

	@ManyToMany
	@JoinTable(name = "brands_categories", joinColumns = @JoinColumn(name = "brand_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
	private Set<Category> categories = new HashSet<>();

	public Brand(String name) {
		this.name = name;
		this.logo = "brand-logo.png";
	}

	@Transient
	public String getLogoPath() {
		if (this.id == null)
			return "/images/image-thumbnail.png";

		return Constants.S3_BASE_URI + "/brand-logos/" + this.id + "/" + this.logo;
	}
}
