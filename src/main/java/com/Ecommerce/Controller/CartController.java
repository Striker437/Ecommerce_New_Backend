package com.Ecommerce.Controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Ecommerce.Entity.Cart_Product;
import com.Ecommerce.Entity.User;
import com.Ecommerce.Repository.UserRepository;
import com.Ecommerce.Service.CartService;

@RestController
@CrossOrigin
@RequestMapping("/carts")
public class CartController {
	
	
	@Autowired
	CartService cartService;
	
	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/addtocart/{product_id}/{user_id}")  // add to cart 
	public List<Cart_Product> AddToCart(@PathVariable("product_id") int product_id , @PathVariable("user_id") int user_id)
	{
		List<Cart_Product>productList=cartService.AddToCart(product_id,user_id);      //for adding products in cart userid and productid is required
		
		
		
		return productList;
	}
	
	
	
	@GetMapping("/cart-details/{user_id}")
	public List<Cart_Product> getCartDetails(@PathVariable("user_id")  int userId,Principal principal)
	{
		String userName=principal.getName();
		User user=userRepository.findByName(userName);
		if(user.getId()!=userId)
		{
			System.out.println("Access Denied");
			return null;
		}
		List<Cart_Product> productList=cartService.getCartDetails(userId);
		return productList;
	}
	
	
	
	
	@DeleteMapping("/{product_id}")
	public List<Cart_Product> DeleteCartProduct(@PathVariable("product_id") int productId ,Principal principal)
	{
		String userName=principal.getName();
		User user=userRepository.findByName(userName);
		
		List<Cart_Product> cartProductList=cartService.DeleteCartProduct(productId,user);
		
		return cartProductList;
		
	}
	
	//

	
	

}
