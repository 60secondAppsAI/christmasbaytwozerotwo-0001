package com.christmasbaytwozerotwo.dao;

import java.util.List;

import com.christmasbaytwozerotwo.dao.GenericDAO;
import com.christmasbaytwozerotwo.domain.CartItem;





public interface CartItemDAO extends GenericDAO<CartItem, Integer> {
  
	List<CartItem> findAll();
	






}


