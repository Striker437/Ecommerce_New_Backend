package com.Ecommerce.DTO;

import java.io.Serializable;
import java.util.List;


import com.Ecommerce.Entity.Order;
import com.Ecommerce.Entity.OrderProduct;

public class Purchase implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Order order;
	private List<OrderProduct>orderProducts;
	
	
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	
	public List<OrderProduct> getOrderProducts() {
		return orderProducts;
	}
	public void setOrderProducts(List<OrderProduct> orderProducts) {
		this.orderProducts = orderProducts;
	}
	@Override
	public String toString() {
		return "Purchase [order=" + order + ", cartProducts=" + orderProducts + "]";
	}
	
	
	
	
	
	
	

}
