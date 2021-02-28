package com.Ecommerce.Entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Category implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	String type;
	
	
	@OneToMany(cascade = CascadeType.ALL,  mappedBy = "category" )
	List<Product>productlist=new ArrayList<Product>();
	@Override
	public String toString() {
		return "Category [id=" + id + ", type=" + type + ", productlist=" + productlist + "]";
	}
	public int getId() {
		return id;
	}
	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}
	public void setId(int id) {
		this.id = id;
	}
	public Category(int id, String type) {
		super();
		this.id = id;
		this.type = type;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	/*
	 * public List<Product> getProductlist() { return productlist; } public void
	 * setProductlist(List<Product> productlist) { this.productlist = productlist; }
	 */
	

}
