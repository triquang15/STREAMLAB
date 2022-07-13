package com.aptech.admin.movie;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.aptech.common.entity.movie.Movie;

public interface MovieRepository extends PagingAndSortingRepository<Movie, Integer>{
public Movie findByName(String name);
	
	@Query("UPDATE Movie p SET p.enabled = ?2 WHERE p.id = ?1")
	@Modifying
	public void updateEnabledStatus(Integer id, boolean enabled);	
	
	public Long countById(Integer id);
}
