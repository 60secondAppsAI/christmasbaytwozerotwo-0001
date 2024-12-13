package com.christmasbaytwozerotwo.dao;

import java.util.List;

import com.christmasbaytwozerotwo.dao.GenericDAO;
import com.christmasbaytwozerotwo.domain.Invoice;





public interface InvoiceDAO extends GenericDAO<Invoice, Integer> {
  
	List<Invoice> findAll();
	






}


