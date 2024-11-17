package com.testing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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

import com.testing.entity.Product;
import com.testing.service.ProductService;

@RequestMapping("api/products")
@RestController
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping
	public ResponseEntity<Page<Product>> getAllProducts(@RequestParam int page, @RequestParam(defaultValue = "10") int size){
		
		Page<Product> allProducts = productService.getAllProducts(page, size);
		return new ResponseEntity<Page<Product>>(allProducts, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Product> createProdut(@RequestBody Product product){
		
		Product createProduct = productService.createProduct(product);
		return new ResponseEntity<Product>(createProduct, HttpStatus.CREATED);
	}
	
	@GetMapping("/{productId}")
	public ResponseEntity<Product> getProductById(@PathVariable Long productId){
		
		Product productById = productService.getProductById(productId);
		return new ResponseEntity<Product>(productById, HttpStatus.OK);
	}
	
	@PutMapping("/{productId}")
	public ResponseEntity<Product> updateProduct(@PathVariable Long productId, @RequestBody Product product){
		
		Product updateProduct = productService.updateProduct(productId, product);
		return new  ResponseEntity<Product>(updateProduct, HttpStatus.OK);
	}
	
	@DeleteMapping
	public ResponseEntity<String> deleteProduct(@PathVariable Long productId){
		
		String deleteProduct = productService.deleteProduct(productId);
		return new ResponseEntity<String>(deleteProduct, HttpStatus.OK);
	}
}
