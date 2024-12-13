package com.christmasbaytwozerotwo.dao;

import java.util.List;

import com.christmasbaytwozerotwo.dao.GenericDAO;
import com.christmasbaytwozerotwo.domain.WishlistItem;





public interface WishlistItemDAO extends GenericDAO<WishlistItem, Integer> {
  
	List<WishlistItem> findAll();
	






}


