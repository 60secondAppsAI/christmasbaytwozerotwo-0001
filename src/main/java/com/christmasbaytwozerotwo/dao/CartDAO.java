package com.christmasbaytwozerotwo.dao;

import java.util.List;

import com.christmasbaytwozerotwo.dao.GenericDAO;
import com.christmasbaytwozerotwo.domain.Cart;





public interface CartDAO extends GenericDAO<Cart, Integer> {
  
	List<Cart> findAll();
	






}


