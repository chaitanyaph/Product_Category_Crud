package com.testing.service;

import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.testing.entity.Product;
import com.testing.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	public Page<Product> getAllProducts(int page, int size){
		
		return productRepository.findAll(PageRequest.of(page, size));
	}
	
	public Product createProduct(Product product) {
		
		return productRepository.save(product);
	}
	
	public Product getProductById(Long productId) {
		
		return productRepository.findById(productId)
				.orElseThrow(() -> new RuntimeException("Product not found....."));
	}
	
	public Product updateProduct(Long productId, Product productDetails) {
		
		Product product = getProductById(productId);
		product.setName(productDetails.getName());
		product.setPrice(productDetails.getPrice());
		product.setCategory(productDetails.getCategory());
		return productRepository.save(product);
	}
	
	public String deleteProduct(Long productId) {
		
		Optional<Product> findById = productRepository.findById(productId);
		if(findById.isPresent()) {
			productRepository.deleteById(productId);
			return "Product Deleted Successfully......";
		}
		return "Product not found with this Id : "+productId;
	}
}
