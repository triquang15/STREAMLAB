package com.aptech.common.entity.movie;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.aptech.common.entity.Category;
import com.aptech.common.entity.IdBasedEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "movies")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Movie extends IdBasedEntity{
	@Column(unique = true, length = 256, nullable = false)
	private String name;
	
	@Column(unique = true, length = 256, nullable = false)
	private String alias;
	
	@Column(length = 4096, nullable = false, name = "description")
	private String description;
	
	@Column(name = "created_time")
	private Date createdTime;
	
	@Column(name = "updated_time")
	private Date updatedTime;
	
	private boolean enabled;
	
	@Column(name = "main_video", nullable = false)
	private String mainVideo;
		
	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;
	
	@OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
	private List<MovieDetail> details = new ArrayList<>();
	
	@OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<MovieVideo> videos = new HashSet<>();
	
	private int reviewCount;
	private float averageRating;

	@Transient
	private boolean customerCanReview;
	@Transient
	private boolean reviewedByCustomer;
	
	public Movie(String name) {
		this.name = name;
	}

	public void addExtraVideo(String videoName) {
		this.videos.add(new MovieVideo(videoName, this));
	}
	
	
	@Transient
	public String getMainVideoPath() {
		if (id == null || mainVideo == null) return "/images/image-thumbnail.png";
		
		return "/video-movies/" + this.id + "/" + this.mainVideo;
	}

//	@Transient
//	public String getMainVideoPath() {
//		if (id == null || mainVideo == null)
//			return "/videos/video.mp4"";
//
//		return Constants.S3_BASE_URI + "/product-images/" + this.id + "/" + this.mainImage;
//	}

	public void addDetail(String name, String value) {
		this.details.add(new MovieDetail(name, value, this));
	}

	public void addDetail(Integer id, String name, String value) {
		this.details.add(new MovieDetail(id, name, value, this));
	}

	public boolean containsVideoName(String videoName) {
		Iterator<MovieVideo> iterator = videos.iterator();

		while (iterator.hasNext()) {
			MovieVideo video = iterator.next();
			if (video.getName().equals(videoName)) {
				return true;
			}
		}

		return false;
	}

	@Transient
	public String getShortName() {
		if (name.length() > 70) {
			return name.substring(0, 70).concat("...");
		}
		return name;
	}

	

	@Transient
	public String getURI() {
		return "/p/" + this.alias + "/";
	}
}
