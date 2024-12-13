package com.christmasbaytwozerotwo.dao;

import java.util.List;

import com.christmasbaytwozerotwo.dao.GenericDAO;
import com.christmasbaytwozerotwo.domain.Seller;





public interface SellerDAO extends GenericDAO<Seller, Integer> {
  
	List<Seller> findAll();
	






}


