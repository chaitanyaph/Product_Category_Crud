package com.testing.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.testing.entity.Category;
import com.testing.repository.CategoryRepository;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepo;
	
	public Page<Category> getAllCategories(int page, int size){
		
		return categoryRepo.findAll(PageRequest.of(page, size));
	}
	
	public Category createCategory(Category category) {
		
		return categoryRepo.save(category);
	}
	
	public Category getCategoryById(Long categoryId) {
		
		return categoryRepo.findById(categoryId)
				.orElseThrow(() -> new RuntimeException("Category not Found"));
	}
	
	public Category updateCategory(Long categoryId, Category categoryDetails) {
		
		Category category  = getCategoryById(categoryId);
		category.setName(categoryDetails.getName());
		return categoryRepo.save(category);
	}
	
	public String deleteCategory(Long categoryId) {
		
		Optional<Category> findById = categoryRepo.findById(categoryId);
		if(findById.isPresent()) {
			categoryRepo.deleteById(categoryId);
			return "Category Deleted Successfully Donee.....";
		}
		return "Category Not found with this CategoryId : "+categoryId;
	}
	
}
