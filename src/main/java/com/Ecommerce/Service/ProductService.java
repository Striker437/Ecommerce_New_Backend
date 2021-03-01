package com.Ecommerce.Service;

import java.util.List;
import java.util.Optional;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.Ecommerce.Entity.Product;
import com.Ecommerce.Repository.ProductRepository;

@Service
public class ProductService {
	
	Logger log=LoggerFactory.logger(ProductService.class);
	@Autowired
	ProductRepository productRepository;

	
	@Cacheable(cacheNames = "products")
	public List<Product> getAllProducts() throws InterruptedException {
		Thread.sleep(1000);
		
		System.out.println("fetch from db");
		return productRepository.findAll();
		
		
	}

	@Cacheable(cacheNames = "productsByCategory")
	public List<Product> getProductsByCategory(int category_id) throws InterruptedException {
		Thread.sleep(1000);
		log.info("fetch from db productsbycategory");
	    
		return productRepository.findAllById(category_id);
		
	}

	public void saveProduct(Product product) {
		
		productRepository.save(product);
		
		
	}

	@CacheEvict(cacheNames = "products")
	public void DeleteById(int id) {
		
		log.info("delete product from db");
		
		productRepository.deleteById(id);
		
		
		
		 
		
	}

	@Cacheable(cacheNames = "products", unless = "#result.price<200")   //cache only the products which have price more than 200
	public Optional<Product> getProductDetail(int id) {
		
		log.info("fetch from db rpoduct details");
		
		Optional<Product> optional=productRepository.findById(id);
		
		return optional;
	}

	@Cacheable(cacheNames = "search" , key = "#keyword")
	public List<Product> getProductsByContainingName(String keyword) {
		return productRepository.findByNameContaining(keyword);
	}
	
	
	
	@CachePut(cacheNames = "products")
	public List<Product> save(Product product)
	{
		log.info("fetch from db updated product");
		productRepository.save(product);
		return productRepository.findAll();		
	}
	
	

}
