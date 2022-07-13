package com.aptech.admin.movie;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.aptech.admin.FileUploadUtil;
import com.aptech.admin.category.CategoryService;
import com.aptech.common.entity.Category;
import com.aptech.common.entity.movie.Movie;

@Controller
public class MovieController {
	@Autowired
	private MovieService MovieService;
	
	@Autowired
	private CategoryService categoryService;


	@GetMapping("/movies")
	public String listAll(Model model) {
		List<Movie> listMovies = MovieService.listAll();
		
		model.addAttribute("listMovies", listMovies);
		return "movies/movie";
	}
	
	@GetMapping("/movies/new")
	public String newMovie(Model model) {
	
		List<Category> listCategories = categoryService.listAll();
		Movie movie = new Movie();
		movie.setEnabled(true);
				
		model.addAttribute("movie", movie);
		model.addAttribute("listCategories", listCategories);
		model.addAttribute("pageTitle", "Create New Movie");
		
		return "movies/movie_form";
	}
	
	@PostMapping("/movies/save")
	public String saveMovie(Movie Movie, RedirectAttributes ra,
			@RequestParam("fileVideo") MultipartFile mainVideoMultipart,			
			@RequestParam("extraVideo") MultipartFile[] extraVideoMultiparts,
			@RequestParam(name = "detailNames", required = false) String[] detailNames,
			@RequestParam(name = "detailValues", required = false) String[] detailValues
			) 
					throws IOException {
		setMainVideoName(mainVideoMultipart, Movie);
		setExtraVideoNames(extraVideoMultiparts, Movie);
		setMovieDetails(detailNames, detailValues, Movie);
			
		Movie savedMovie = MovieService.save(Movie);
		
		saveUploadedVideos(mainVideoMultipart, extraVideoMultiparts, savedMovie);
		
		ra.addFlashAttribute("message", "The Movie has been saved successfully.");
		
		return "redirect:/movies";
	}
	
	private void setMovieDetails(String[] detailNames, String[] detailValues, Movie Movie) {
		if (detailNames == null || detailNames.length == 0) return;
		
		for (int count = 0; count < detailNames.length; count++) {
			String name = detailNames[count];
			String value = detailValues[count];
			
			if (!name.isEmpty() && !value.isEmpty()) {
				Movie.addDetail(name, value);
			}
		}
	}

	private void saveUploadedVideos(MultipartFile mainVideoMultipart, 
			MultipartFile[] extraVideoMultiparts, Movie savedMovie) throws IOException {
		if (!mainVideoMultipart.isEmpty()) {
			String fileName = StringUtils.cleanPath(mainVideoMultipart.getOriginalFilename());
			String uploadDir = "../video-movies/" + savedMovie.getId();
			
			FileUploadUtil.cleanDir(uploadDir);
			FileUploadUtil.saveFile(uploadDir, fileName, mainVideoMultipart);		
		}
		
		if (extraVideoMultiparts.length > 0) {
			String uploadDir = "../video-movies/" + savedMovie.getId() + "/extras";
			
			for (MultipartFile multipartFile : extraVideoMultiparts) {
				if (multipartFile.isEmpty()) continue;
				
				String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
				FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
			}
		}
		
	}

	private void setExtraVideoNames(MultipartFile[] extraVideoMultiparts, Movie Movie) {
		if (extraVideoMultiparts.length > 0) {
			for (MultipartFile multipartFile : extraVideoMultiparts) {
				if (!multipartFile.isEmpty()) {
					String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
					Movie.addExtraVideo(fileName);
				}
			}
		}
	}

	private void setMainVideoName(MultipartFile mainVideoMultipart, Movie Movie) {
		if (!mainVideoMultipart.isEmpty()) {
			String fileName = StringUtils.cleanPath(mainVideoMultipart.getOriginalFilename());
			Movie.setMainVideo(fileName);
		}
	}
	
	@GetMapping("/movies/{id}/enabled/{status}")
	public String updateCategoryEnabledStatus(@PathVariable("id") Integer id,
			@PathVariable("status") boolean enabled, RedirectAttributes redirectAttributes) {
		MovieService.updateMovieEnabledStatus(id, enabled);
		String status = enabled ? "enabled" : "disabled";
		String message = "The Movie ID " + id + " has been " + status;
		redirectAttributes.addFlashAttribute("message", message);
		
		return "redirect:/movies";
	}	
	
	@GetMapping("/movies/delete/{id}")
	public String deleteMovie(@PathVariable(name = "id") Integer id, 
			Model model,
			RedirectAttributes redirectAttributes) {
		try {
			MovieService.delete(id);
			
			redirectAttributes.addFlashAttribute("message", 
					"The Movie ID " + id + " has been deleted successfully");
		} catch (MovieNotFoundException ex) {
			redirectAttributes.addFlashAttribute("message", ex.getMessage());
		}
		
		return "redirect:/movies";
	}	
}
