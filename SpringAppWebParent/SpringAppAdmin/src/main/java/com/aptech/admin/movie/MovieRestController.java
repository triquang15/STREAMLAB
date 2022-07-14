package com.aptech.admin.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieRestController {
	@Autowired
	private MovieService service;

	@PostMapping("/movies/check_unique")
	public String checkUnique(@Param("id") Integer id, @Param("name") String name) {
		return service.checkUnique(id, name);
	}
}
