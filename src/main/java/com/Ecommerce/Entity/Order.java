package com.Ecommerce.Entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity(name = "orders")
public class Order{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String address;
	@CreationTimestamp
	Date order_placed_date;
	@UpdateTimestamp
	Date shipped_date;
	int Totalprice;
	int TotalQuantity;
	
	@ManyToOne 
	User user;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "order")
	List<OrderProduct>OrderProductList=new ArrayList<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getOrder_placed_date() {
		return order_placed_date;
	}

	public void setOrder_placed_date(Date order_placed_date) {
		this.order_placed_date = order_placed_date;
	}

	public Date getShipped_date() {
		return shipped_date;
	}

	public void setShipped_date(Date shipped_date) {
		this.shipped_date = shipped_date;
	}

	public int getTotalprice() {
		return Totalprice;
	}

	public void setTotalprice(int totalprice) {
		Totalprice = totalprice;
	}

	public int getTotalQuantity() {
		return TotalQuantity;
	}

	public void setTotalQuantity(int totalQuantity) {
		TotalQuantity = totalQuantity;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<OrderProduct> getOrderProductList() {
		return OrderProductList;
	}

	public void setOrderProductList(List<OrderProduct> orderProductList) {
		OrderProductList = orderProductList;
	}
	
	
	
	
	
	

}
