package com.christmasbaytwozerotwo.dao;

import java.util.List;

import com.christmasbaytwozerotwo.dao.GenericDAO;
import com.christmasbaytwozerotwo.domain.Wishlist;





public interface WishlistDAO extends GenericDAO<Wishlist, Integer> {
  
	List<Wishlist> findAll();
	






}


