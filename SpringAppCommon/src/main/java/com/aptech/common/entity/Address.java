package com.aptech.common.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "addresses")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Address extends AbstractAddressWithCountry {

	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;

	@Column(name = "default_address")
	private boolean defaultForShipping;

	
	

}
