package com.ericsson.bookmanager.dao.impl;

import java.util.ArrayList;

import com.ericsson.bookmanager.dao.BookManageDao;
import com.ericsson.util.SqlHelper;

public class BookManageDaoImpl implements BookManageDao {
	
	public ArrayList executeQuery1(String[] parameters){
		String sql = "select * from book_manage where name=? and password=?";
		return SqlHelper.executeQuery1(sql,parameters);
	}
	
	public boolean executeUpdate(String[] parameters) {
		String sql = "update book_manage set password=? where password=? and name=?";
		return SqlHelper.executeUpdate3(sql, parameters);
	}
}
