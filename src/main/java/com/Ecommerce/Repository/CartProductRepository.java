package com.Ecommerce.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Ecommerce.Entity.Cart_Product;

public interface CartProductRepository extends JpaRepository<Cart_Product, Integer> {

	
	
	@Query("select u from Cart_Product u where u.cart.id=:cart_id")
	List<Cart_Product> findCartProductByCartId(@Param("cart_id") int cart_id);


}
