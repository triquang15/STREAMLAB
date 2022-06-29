package com.aptech.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aptech.common.entity.IdBasedEntity;

@Controller
public class MainController {

	@GetMapping("")
	public String viewHomePage() {

		return "login";
	}
	
	@RequestMapping("/home")
	public String loginSubmit() {
		return "pages/landing_page";
	}
	
}
