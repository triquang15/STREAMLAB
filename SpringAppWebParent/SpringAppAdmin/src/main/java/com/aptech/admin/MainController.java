package com.aptech.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.aptech.common.entity.IdBasedEntity;

@Controller
public class MainController {

	@GetMapping("")
	public String viewHomePage() {

		return "index";
	}
}
