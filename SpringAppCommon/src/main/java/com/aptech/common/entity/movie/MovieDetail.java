package com.aptech.common.entity.movie;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.aptech.common.entity.IdBasedEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "movie_details")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MovieDetail extends IdBasedEntity{
	
	@Column(nullable = false, length = 255)
	private String name;
	
	@Column(nullable = false, length = 255)
	private String value;
	
	@ManyToOne
	@JoinColumn(name = "movie_id")
	private Movie movie;
	
	public MovieDetail(Integer id, String name, String value, Movie movie) {
		super();
		this.id = id;
		this.name = name;
		this.value = value;
		this.movie = movie;
	}
}
