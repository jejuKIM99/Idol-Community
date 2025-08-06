package com.weverse.sb.product.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.weverse.sb.user.controller.UserController;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequestMapping("/api/shop")
// @RequiredArgsConstructor
public class ProductController {
	
	@GetMapping("/main")
	public void mainPage(Model model) {
		
	}
	
	@GetMapping("/artists/{artistId}/products")
	public void artistProduct(Model model) {
		
	}

}
