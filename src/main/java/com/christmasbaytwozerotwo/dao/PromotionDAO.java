package com.christmasbaytwozerotwo.dao;

import java.util.List;

import com.christmasbaytwozerotwo.dao.GenericDAO;
import com.christmasbaytwozerotwo.domain.Promotion;





public interface PromotionDAO extends GenericDAO<Promotion, Integer> {
  
	List<Promotion> findAll();
	






}


