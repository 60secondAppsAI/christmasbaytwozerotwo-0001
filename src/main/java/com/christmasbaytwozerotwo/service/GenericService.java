package com.christmasbaytwozerotwo.service;

import com.christmasbaytwozerotwo.dao.GenericDAO;

public interface GenericService<T, ID> {

    abstract GenericDAO<T, ID> getDAO();

    T getById(Integer id) ;

}