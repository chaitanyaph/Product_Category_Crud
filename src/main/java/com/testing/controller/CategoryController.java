package com.testing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.testing.entity.Category;
import com.testing.service.CategoryService;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@GetMapping
	public ResponseEntity<Page<Category>> allCategories(@RequestParam int page, @RequestParam(defaultValue = "10") int size){
		
		Page<Category> allCategories = categoryService.getAllCategories(page, size);
		return new ResponseEntity<Page<Category>>(allCategories, HttpStatus.OK);
	}
	
	@GetMapping("/{categoryId}")
	public ResponseEntity<Category> getCategoryById(@PathVariable Long categoryId){
		
		Category categoryById = categoryService.getCategoryById(categoryId);
		return new ResponseEntity<Category>(HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Category> createCategory(@RequestBody Category category){
		
		Category createCategory = categoryService.createCategory(category);
		return new ResponseEntity<Category>(createCategory, HttpStatus.CREATED);
	}
	
	@PutMapping("/{categoryId}")
	public ResponseEntity<Category> updateCategory(@PathVariable Long categoryId, @RequestBody Category category){
		
		Category updateCategory = categoryService.updateCategory(categoryId, category);
		return new ResponseEntity<Category>(updateCategory, HttpStatus.OK);
	}
	
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId){
		
		String deleteCategory = categoryService.deleteCategory(categoryId);
		return new ResponseEntity<String>(deleteCategory, HttpStatus.OK);
	}
}
