package com.aptech.admin.movie;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aptech.common.entity.movie.Movie;


@Service
@Transactional
public class MovieService {
	@Autowired
	private MovieRepository MovieRepository;

	public List<Movie> listAll() {
		return (List<Movie>) MovieRepository.findAll();
	}
	
	public Movie save(Movie Movie) {
		if (Movie.getId() == null) {
			Movie.setCreatedTime(new Date());
		}
		
		if (Movie.getAlias() == null || Movie.getAlias().isEmpty()) {
			String defaultAlias = Movie.getName().replaceAll(" ", "-");
			Movie.setAlias(defaultAlias);
		} else {
			Movie.setAlias(Movie.getAlias().replaceAll(" ", "-"));
		}
		
		Movie.setUpdatedTime(new Date());
		
		return MovieRepository.save(Movie);
	}
	
	public String checkUnique(Integer id, String name) {
		boolean isCreatingNew = (id == null || id == 0);
		Movie MovieByName = MovieRepository.findByName(name);
		
		if (isCreatingNew) {
			if (MovieByName != null) return "Duplicate";
		} else {
			if (MovieByName != null && MovieByName.getId() != id) {
				return "Duplicate";
			}
		}
		
		return "OK";
	}
	
	public void updateMovieEnabledStatus(Integer id, boolean enabled) {
		MovieRepository.updateEnabledStatus(id, enabled);
	}	
	
	public void delete(Integer id) throws MovieNotFoundException {
		Long countById = MovieRepository.countById(id);
		
		if (countById == null || countById == 0) {
			throw new MovieNotFoundException("Could not find any Movie with ID " + id);			
		}
		
		MovieRepository.deleteById(id);
	}	
}
