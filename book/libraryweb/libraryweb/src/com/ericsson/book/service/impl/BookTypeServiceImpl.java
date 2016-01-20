package com.ericsson.book.service.impl;

import java.util.ArrayList;

import com.ericsson.book.dao.BookTypeDao;
import com.ericsson.book.dao.impl.BookTypeDaoImpl;
import com.ericsson.book.service.BookTypeService;

public class BookTypeServiceImpl implements BookTypeService {
	
	BookTypeDao bookTypeDao=new BookTypeDaoImpl();

	public boolean executeUpdate(String[] parameters) {
		return bookTypeDao.executeUpdate(parameters);
	}

	public ArrayList executeQuery1(String[] parameters) {
		return bookTypeDao.executeQuery1(parameters);
	}

	public boolean executeInsert(String[] parameters) {
		return bookTypeDao.executeInsert(parameters);
	}
	
}
