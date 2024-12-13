package com.christmasbaytwozerotwo.dao;

import java.util.List;

import com.christmasbaytwozerotwo.dao.GenericDAO;
import com.christmasbaytwozerotwo.domain.Message;





public interface MessageDAO extends GenericDAO<Message, Integer> {
  
	List<Message> findAll();
	






}


