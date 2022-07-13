package com.aptech.common.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.aptech.common.entity.pricing.Pricing;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "customers")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Customer extends AbstractAddressWithCountry {

	@Column(nullable = false, unique = true, length = 45)
	private String email;

	@Column(nullable = false, length = 64)
	private String password;

	@Column(name = "verification_code", length = 64)
	private String verificationCode;

	private boolean status;

	@Column(name = "created_time")
	private Date createdTime;

	@Enumerated(EnumType.STRING)
	@Column(name = "authentication_type", length = 10)
	private AuthenticationType authenticationType;

	@Column(name = "reset_password_token", length = 30)
	private String resetPasswordToken;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "customer_pricing", joinColumns = @JoinColumn(name = "customer_id"), inverseJoinColumns = @JoinColumn(name = "pricing_id"))
	private Set<Pricing> pricings = new HashSet<>();

	public Customer(Integer id) {
		this.id = id;
	}

	public String getFullName() {
		return firstName + " " + lastName;
	}
	
	public boolean hasRole(String pricingName) {
		Iterator<Pricing> iterator = pricings.iterator();

		while (iterator.hasNext()) {
			Pricing pricing = iterator.next();
			if (pricing.getName().equals(pricingName)) {
				return true;
			}
		}

		return false;
	}

}
