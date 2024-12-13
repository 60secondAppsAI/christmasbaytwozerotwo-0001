package com.christmasbaytwozerotwo.dao;

import java.util.List;

import com.christmasbaytwozerotwo.dao.GenericDAO;
import com.christmasbaytwozerotwo.domain.PaymentMethod;





public interface PaymentMethodDAO extends GenericDAO<PaymentMethod, Integer> {
  
	List<PaymentMethod> findAll();
	






}


