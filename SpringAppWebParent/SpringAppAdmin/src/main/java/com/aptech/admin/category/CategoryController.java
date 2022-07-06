package com.aptech.admin.category;

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
import com.aptech.common.entity.Category;
import com.aptech.common.exception.CategoryNotFoundException;

@Controller
public class CategoryController {
	@Autowired
	private CategoryService categoryService;

	@GetMapping("/categories")
	public String listAll(Model model) {
		List<Category> listCategories = categoryService.listAll();

		model.addAttribute("listCategories", listCategories);

		return "categories/categories";
	}

	@GetMapping("/categories/new")
	public String newCategory(Model model) {
		List<Category> listCategories = categoryService.listCategoriesUsedInForm();

		model.addAttribute("category", new Category());
		model.addAttribute("listCategories", listCategories);
		model.addAttribute("pageTitle", "Create New Category");

		return "categories/category_form";
	}

	@PostMapping("/categories/save")
	public String saveCategory(Category category, @RequestParam("fileImage") MultipartFile multipartFile,
			RedirectAttributes ra) throws IOException {
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		category.setImage(fileName);

		Category savedCategory = categoryService.save(category);
		String uploadDir = "../category-images/" + savedCategory.getId();
		FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

		ra.addFlashAttribute("message", "The category has been saved successfully.");
		return "redirect:/categories";
	}

	@GetMapping("/categories/edit/{id}")
	public String editCategory(@PathVariable(name = "id") Integer id, Model model, RedirectAttributes ra) {
		try {
			Category category = categoryService.get(id);
			List<Category> listCategories = categoryService.listCategoriesUsedInForm();

			model.addAttribute("category", category);
			model.addAttribute("listCategories", listCategories);
			model.addAttribute("pageTitle", "Edit Category (ID: " + id + ")");

			return "categories/category_form";
		} catch (CategoryNotFoundException ex) {
			ra.addFlashAttribute("message", ex.getMessage());
			return "redirect:/categories";
		}
	}
}
