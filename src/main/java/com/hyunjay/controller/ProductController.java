package com.hyunjay.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hyunjay.detail.MyUserDetails;
import com.hyunjay.model.Product;
import com.hyunjay.repo.ProductRepository;
import com.hyunjay.service.ProductService;

@Controller
//@RequestMapping("api")
public class ProductController {

	@Autowired
	private ProductService ps;
	
	@GetMapping("/")
	public ModelAndView frontPage(HttpServletRequest http) {
		ModelAndView mav = new ModelAndView("front_page");
		Principal principal = http.getUserPrincipal();
		String username = principal.getName();
		mav.addObject(username);
		return mav;
	}
	
	@GetMapping("/products")
	public String getAllProducts(Model model) {
		List<Product> products = ps.getAllProducts();
		int sum = 0;
		for(Product p : products) {
			sum += p.getQuantity() * p.getPrice();
		}
		model.addAttribute("allProducts", products);
		model.addAttribute("totalPrice", sum);
		return "products";
	}

	@GetMapping("/new")
	public String newProductForm(Model model) {
		Product product = new Product();
		model.addAttribute("product",product);
		return "new_product_form";
	}
	
	@PostMapping("/save")
	public String saveProduct(@ModelAttribute("product") Product prd) {
		ps.create(prd);
		return "redirect:/products";
	}

	@GetMapping("/edit_user/{id}")
	public ModelAndView editProductFormUser(@PathVariable(name="id") Long id) throws Exception {
		ModelAndView mav = new ModelAndView("edit_form_user");
		Product product = ps.getById(id);
		mav.addObject("product",product);
		return mav;
	}

	@GetMapping("/edit_admin/{id}")
	public ModelAndView editProductFormAdmin(@PathVariable(name="id") Long id) throws Exception {
		ModelAndView mav = new ModelAndView("edit_form_admin");
		Product product = ps.getById(id);
		mav.addObject("product",product);
		return mav;
	}
	
//	@PostMapping("/edit/{id}")
//	public String updateProduct(@PathVariable(name="id") Long id, Product prd) {
//		ps.update(id, prd);
//		return "update_success";
//	}

	@GetMapping("/delete/{id}")
	public String deleteProduct(@PathVariable(name="id") Long id) {
		ps.deleteById(id);
		return "redirect:/products";
	}
}
