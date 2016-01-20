package com.ericsson.book.dao.impl;

import java.util.ArrayList;

import com.ericsson.book.dao.BookTypeDao;
import com.ericsson.util.SqlHelper;

public class BookTypeDaoImpl implements BookTypeDao {

	public boolean executeUpdate(String[] parameters) {
		String sql="insert into book_type(bookMajorType) values(?)";
		return SqlHelper.executeUpdate3(sql, parameters);
	}

	public ArrayList executeQuery1(String[] parameters) {
		String sql="select * from book_type";
		return SqlHelper.executeQuery1(sql,parameters);
	}
	
	public boolean executeInsert(String[] parameters) {
		String sql="insert into book_small_type(booksmalltypename,bookTypeId) values(?,?)";
		return SqlHelper.executeUpdate3(sql, parameters);
	}

}
