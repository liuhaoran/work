package com.ericsson.bookmanager.service.impl;

import java.util.ArrayList;

import com.ericsson.bookmanager.dao.BookManageDao;
import com.ericsson.bookmanager.dao.impl.BookManageDaoImpl;
import com.ericsson.bookmanager.service.BookManageService;

public class BookManageServiceImpl implements BookManageService{
	BookManageDao bookManageDao = new BookManageDaoImpl();

	public ArrayList executeQuery1(String[] parameters) {

		return bookManageDao.executeQuery1(parameters);
	}

	public boolean executeUpdate(String[] parameters) {
		
		return bookManageDao.executeUpdate(parameters);
	}
}
