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

@Entity(name = "orders")
public class Order{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String address;
	Date order_placed_date;
	Date shipped_date;
	int price;
	/*
	 * @OneToMany(cascade = CascadeType.ALL,mappedBy = "order")
	 * List<Product>Productlist=new ArrayList();
	 */
	/*
	 * @ManyToOne User user;
	 */
	
	
	

}
