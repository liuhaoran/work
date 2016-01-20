package com.ericsson.book.service.impl;

import java.sql.ResultSet;
import java.util.List;

import com.ericsson.book.dao.BookSmallTypeDao;
import com.ericsson.book.dao.impl.BookSmallTypeDaoImpl;
import com.ericsson.book.service.BookSmallTypeService;

public class BookSmallTypeServiceImpl implements BookSmallTypeService {
	
	BookSmallTypeDao bookSmallTypeDao=new BookSmallTypeDaoImpl();
	public List executeQueryByPage(String[] parameters, int pageSize,
			int pageNow) {
		
		return bookSmallTypeDao.executeQueryByPage(parameters, pageSize, pageNow);
	}

	public ResultSet getCount(String[] parameters) {
		
		return bookSmallTypeDao.getCount(parameters);
	}
	
	public  List executeFindById(String [] parameters){
		return bookSmallTypeDao.executeFindById(parameters);
	}
	
	public void executeUpdateBookSmallType(String[] parameters){
		bookSmallTypeDao.executeUpdateBookSmallType(parameters);
	}
}
