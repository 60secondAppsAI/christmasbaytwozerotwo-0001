package com.christmasbaytwozerotwo.dao;

import java.util.List;

import com.christmasbaytwozerotwo.dao.GenericDAO;
import com.christmasbaytwozerotwo.domain.Shipping;





public interface ShippingDAO extends GenericDAO<Shipping, Integer> {
  
	List<Shipping> findAll();
	






}


