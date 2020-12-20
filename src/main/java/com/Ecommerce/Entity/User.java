package com.Ecommerce.Entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	int id;
	String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	String password;
	String address;
	int phoneno;
	String role;
	
	
	  @JsonManagedReference
	  @JsonProperty(access = Access.WRITE_ONLY)   //used to ignore the cart field in json response                                         //@JsonProperty will ignore the field when it gets parsed to JSON as a response object. (E.g. for some API call)
	  @OneToOne
	  Cart cart;
	 
	/*
	 * @OneToMany(cascade = CascadeType.ALL,mappedBy = "user")
	 * List<Order>Orderlist=new ArrayList<Order>();
	 */
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getPhoneno() {
		return phoneno;
	}
	public void setPhoneno(int phoneno) {
		this.phoneno = phoneno;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
	
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
	}
	
	
	

}
