package com.aptech.common.entity;

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
@Table(name = "states")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class State extends IdBasedEntity {

	@Column(nullable = false, length = 45)
	private String name;

	@ManyToOne
	@JoinColumn(name = "country_id")
	private Country country;

}
