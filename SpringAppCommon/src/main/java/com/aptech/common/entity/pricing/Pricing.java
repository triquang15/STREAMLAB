package com.aptech.common.entity.pricing;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.aptech.common.entity.IdBasedEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pricings")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Pricing extends IdBasedEntity {
	@Column(length = 64, nullable = false, unique = true)
	private String name;
	
	private float price;
	
	@Column(length = 256, nullable = false)
	private String description;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pricing other = (Pricing) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return this.name;
	}

}
