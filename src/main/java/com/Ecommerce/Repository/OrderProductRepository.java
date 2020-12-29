package com.Ecommerce.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.Ecommerce.Entity.OrderProduct;

public interface OrderProductRepository extends JpaRepository<OrderProduct, Integer> {

	
	@Query("select u from OrderProduct u where u.user.id=:user_Id")
	 List<OrderProduct> findOrderByUser(@Param("user_Id") int user_Id);

}
