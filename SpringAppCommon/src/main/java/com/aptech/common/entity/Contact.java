package com.aptech.common.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "contacts")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Contact extends IdBasedEntity {
	
	@Column(nullable = false, length = 45, unique = true)
	private String name;
	
	@Column(nullable = false, length = 45, unique = true)
	private String email;
	
	@Column(nullable = false, length = 15, unique = true)
	private String phone;
	
	@Column(nullable = false, length = 256, unique = true)
	private String venue;
	
	@Column(nullable = false, length = 256, unique = true)
	private String message;
}
