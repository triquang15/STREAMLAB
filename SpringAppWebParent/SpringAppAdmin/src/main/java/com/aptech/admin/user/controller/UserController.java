package com.aptech.admin.user.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

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

import com.aptech.admin.AmazonS3Util;
import com.aptech.admin.paging.PagingAndSortingHelper;
import com.aptech.admin.paging.PagingAndSortingParam;
import com.aptech.admin.user.UserNotFoundException;
import com.aptech.admin.user.UserService;
import com.aptech.admin.user.export.UserCsvExporter;
import com.aptech.admin.user.export.UserExcelExporter;
import com.aptech.admin.user.export.UserPdfExporter;
import com.aptech.common.entity.Role;
import com.aptech.common.entity.User;

@Controller
public class UserController {
	@Autowired
	private UserService service;
	
	@GetMapping("/users")
	public String listAll(Model model) {
		List<User> listUsers = service.listAll();
		model.addAttribute("listUsers", listUsers);
		
		return "users/users";
	}
	
	@GetMapping("/users/new")
	public String newUser(Model model) {
		List<Role> listRoles = service.listRoles();
		
		User user = new User();
		user.setEnabled(true);
		
		model.addAttribute("user", user);
		model.addAttribute("listRoles", listRoles);
		
		return "users/user_form";
	}
	
	@PostMapping("/users/save")
	public String saveUser(User user, RedirectAttributes redirectAttributes) {
		System.out.println(user);
		service.save(user);
		
		redirectAttributes.addFlashAttribute("message", "The user has been saved successfully.");
		
		return "redirect:/users";
	}
}
