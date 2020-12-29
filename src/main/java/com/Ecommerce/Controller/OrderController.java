package com.Ecommerce.Controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Ecommerce.DTO.Purchase;
import com.Ecommerce.Entity.Cart_Product;
import com.Ecommerce.Entity.Order;
import com.Ecommerce.Entity.OrderProduct;
import com.Ecommerce.Entity.User;
import com.Ecommerce.Repository.UserRepository;
import com.Ecommerce.Service.OrderService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/orders/")
public class OrderController {
	
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	UserRepository userRepository;
	
	
	@PostMapping
	public List<String> SaveOrder(@RequestBody Purchase purchase ,Principal principal)
	{
		System.out.println(purchase.getOrder().getTotalprice());
		String userName=principal.getName();
		User user=userRepository.findByName(userName);
		List<String> orderTrackingNumber=orderService.saveOrder(purchase,user);
		System.out.println(orderTrackingNumber);
		return orderTrackingNumber;
	}
	
	
	@GetMapping("/getall")
	public List<OrderProduct> getOrderDetails(Principal principal)
	{
		String userName=principal.getName();
		User user=userRepository.findByName(userName);
		List<OrderProduct>OrderProductList=orderService.getOrderDeatails(user);
		return OrderProductList;
	}
	
	

}
