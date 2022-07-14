package com.aptech.common.entity.movie;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.aptech.common.entity.IdBasedEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "movie_videos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MovieVideo extends IdBasedEntity {
	@Column(nullable = false)
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "movie_id")
	private Movie movie;
	
	@Transient
	public String getImagePath() {
		return "/video-movies/" + movie.getId() + "/extras/" + this.name;
	}
}
