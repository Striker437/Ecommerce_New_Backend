package com.Ecommerce.Service;

import java.util.*;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Ecommerce.DTO.Purchase;
import com.Ecommerce.Entity.Cart_Product;
import com.Ecommerce.Entity.Order;
import com.Ecommerce.Entity.OrderProduct;
import com.Ecommerce.Entity.User;
import com.Ecommerce.Repository.OrderRepository;

@Service
public class OrderService {
	
	
	
      @Autowired
      OrderRepository orderRepository;

	public List<String> saveOrder(Purchase purchase, User user) {
		
		Order order=purchase.getOrder();   //get the order
		System.out.println("order total price"+order.getTotalprice());
		List<OrderProduct> orderProducts=purchase.getOrderProducts();	
		List<String> TrackingnoList=new ArrayList<>();
		for(OrderProduct tempOrderProduct:orderProducts)
		{
			String orderTrackingNumber=generateOrderTrackingNumber();    //generate tracking no
			tempOrderProduct.setOrderTrackingNumber(orderTrackingNumber);    //generate trackingid for each database
			TrackingnoList.add(orderTrackingNumber);
		}
		order.setOrderProductList(orderProducts);
		 for(OrderProduct temporderProduct:orderProducts) {   //set order_id for each order product in database
			  temporderProduct.setOrder(order); }
		 
		 
		 order.setUser(user); //set user id
		 
		 orderRepository.save(order);
		 
		
		
		
		
		return TrackingnoList;
		
		
	}
	
	
	private String generateOrderTrackingNumber() {
		
		//generate a random UUID Number(unique)
		
		return UUID.randomUUID().toString();
	}

}
