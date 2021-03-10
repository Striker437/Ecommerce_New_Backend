package com.Ecommerce.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.Ecommerce.Entity.Cart;
import com.Ecommerce.Entity.Cart_Product;
import com.Ecommerce.Entity.Product;
import com.Ecommerce.Entity.User;
import com.Ecommerce.Repository.CartProductRepository;
import com.Ecommerce.Repository.CartRepository;
import com.Ecommerce.Repository.ProductRepository;
import com.Ecommerce.Repository.UserRepository;

@Service
public class CartService {
	
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	CartRepository cartRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CartProductRepository cartProductRepository; 

	
	//add to cart
	public List<Cart_Product> AddToCart(int product_id, int user_id)  {
		
		Optional<Product> optional=productRepository.findById(product_id);
		Product product=optional.get();      //get product from product_id
		 User user=userRepository.findById(user_id).get();   //get user  from user id
		
		int cartid=user.getCart().getId();    //get cart id from user 
		
		List<Cart_Product>cartProductList1=getCartDetails(user_id);     //debug purpose
		
		
		for(Cart_Product cartProduct:cartProductList1)
			System.out.println("CartProducts:"+cartProduct.toString());     //debug purpose
		
		
		
		
		cartProductList1.forEach(System.out::println);
		
		System.out.println("Product needs to add in cart"+product);      //debug purpose
		
		
		
		
		
		Cart cart=cartRepository.findById(cartid).get();  //get the cart from user
		
		
		if(cart==null)
		{
			Cart emptyCart=new Cart();
			emptyCart.setTotalprice(product.getPrice());  //if cart is empty then set the cart total price and quantity as
			emptyCart.setQuantity(1);                    //product total price and quantity
			emptyCart.setUser(user);                      //set the initial quantity as 1 for carts
			cartRepository.save(emptyCart);               //save the cart
			
			Cart_Product cartProduct=new Cart_Product();   //if cart is empty that means there is no product in cart i,e
			//cartProduct.setId(product.getId());
			cartProduct.setPrice(product.getPrice());       //we have to set the cart product as product details or description
			cartProduct.setName(product.getName());
			cartProduct.setImageURL(product.getImageURL());
			cartProduct.setDescription(product.getDescription());
			cartProduct.setQuantity(1);         
			cartProduct.setCart(emptyCart);
			cartProductRepository.save(cartProduct);
			
			
			
		}
		else
		{
			
			if(IsProductPresent(cartProductList1,product))
			{
			System.out.println("Product already exists in cart");
			return cartProductList1;
			}
		
		int total_price=cart.getTotalprice()+product.getPrice();
		int quantity=cart.getQuantity()+1;
		cart.setTotalprice(total_price);  //if cart is not empty then set total price as cart price added to product price and cart quantity as cart quantity plus 1
		cart.setQuantity(quantity);
		cart.setUser(user);
		cartRepository.save(cart);
		
		
		
		Cart_Product cartProduct=new Cart_Product();   //if cart is not empty that means there already a  product in cart i,e
		
		
		
		//cartProduct.setId(product.getId());
		cartProduct.setPrice(product.getPrice());       //we have to set the cart product as product details or description
		cartProduct.setName(product.getName());
		cartProduct.setImageURL(product.getImageURL());
		cartProduct.setDescription(product.getDescription());
		cartProduct.setQuantity(1);
		cartProduct.setCart(cart);
		cartProductRepository.save(cartProduct);
		
		System.out.println("Product added to cart , Product Details: "+cartProduct);
		
		
		
		
		}
		List<Cart_Product> cartProductList=cartProductRepository.findCartProductByCartId(cartid);
		
		
		
		return cartProductList;
	}
	
	
	
	
	
	
	
	//get cart products  from userId

	@Cacheable(cacheNames = "cart")
	public List<Cart_Product> getCartDetails(int userId) {
		System.out.println("fetch from db in cartservice-----");
		User user=userRepository.findById(userId).get();
		int cartId=user.getCart().getId();
	    List<Cart_Product> productList=cartProductRepository.findCartProductByCartId(cartId);
	    return productList;
	}
	
	
	
	
	
	//check whether product is already present in cart or not
	public boolean IsProductPresent(List<Cart_Product> cartProductList1, Product product)
	{
		/*
		 * for(Cart_Product tempcartProduct:cartProductList1) {
		 * 
		 * if(tempcartProduct.getName().equalsIgnoreCase(product.getName())) {
		 * 
		 * return true; } }
		 */
		
		Boolean b=cartProductList1.stream()
				                  .anyMatch(x->x.getName().equalsIgnoreCase(product.getName()));    //java stream api
		System.out.println("check product exists in cart or not------"+b);
		
		return b;
	}







	
	
	//delete a cart product
	  @CacheEvict(cacheNames = "cart")
	  public List<Cart_Product> DeleteCartProduct(int productId, User user) {
			
			int cartid=user.getCart().getId();    //get cart id from user 
			
			Cart cart=cartRepository.findById(cartid).get();
			
			Cart_Product cartProduct=cartProductRepository.findById(productId).get();
			
			int subtotal=cartProduct.getPrice()*cartProduct.getQuantity();
			
			int TotalPrice=cart.getTotalprice()-subtotal;
			
			int TotalQuantity=cart.getQuantity()-cartProduct.getQuantity();
			cart.setQuantity(TotalQuantity);
			cart.setTotalprice(TotalPrice);
	  
	  cartProductRepository.deleteById(productId);
	  
	  return getCartDetails(user.getId());
	  
	  
	  
	  
	  }






	  @CachePut(cacheNames = "cart")
	public List<Cart_Product> IncrementQuantity(int productId, int userId) {
		
		Optional<Cart_Product> optional=cartProductRepository.findById(productId);
		Cart_Product product=optional.get();
		int totalQuantity=product.getQuantity()+1;
		//product.setPrice(totalQuantity*product.getPrice());
		product.setQuantity(totalQuantity);
		cartProductRepository.save(product);
		return getCartDetails(userId);
		
		
	}






	@CachePut(cacheNames = "cart")
	public List<Cart_Product> DecrementQuantity(int productId, int userId) {
		
		Optional<Cart_Product> optional=cartProductRepository.findById(productId);
		Cart_Product product=optional.get();
		int totalQuantity=product.getQuantity()-1;
		if(totalQuantity==0)
		cartProductRepository.deleteById(productId);     //if quantity becomes 0 then remove product from cart
		
		else
		{
		//product.setPrice(totalQuantity*product.getPrice());   //else calculate 
		product.setQuantity(totalQuantity);
		cartProductRepository.save(product);
		
		}
		
		return getCartDetails(userId);
		
		
	}
	 

}
