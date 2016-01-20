package com.ericsson.book.dao.impl;

import java.sql.ResultSet;
import java.util.List;

import com.ericsson.book.dao.BookSmallTypeDao;
import com.ericsson.util.SqlHelper;

public class BookSmallTypeDaoImpl implements BookSmallTypeDao {

	public boolean executeUpdate(String[] parameters) {
		
		return false;
	}

	public List executeQueryByPage(String[] parameters, int pageSize,
			int pageNow) {
		String sql="";
		if(parameters!=null){
			sql="select s.bookSmallid,s.booksmalltypename,b.bookMajorType from book_small_type s,book_type b " +
			"where s.booksmalltypename like ? and s.booktypeid=b.booktypeid limit "+pageNow+","+pageSize+"";
		}else{
			sql="select s.bookSmallid,s.booksmalltypename,b.bookMajorType from book_small_type s,book_type b " +
			"where s.booktypeid=b.booktypeid order by s.bookSmallid limit "+pageNow+","+pageSize+"";
		}
		
		return SqlHelper.executeQuery1(sql, parameters);
	}

	public ResultSet getCount(String[] parameters) {
		String sql="";
		if(parameters!=null){
			sql="select count(*) from book_small_type s,book_type b " +
			"where s.booksmalltypename like ? and s.booktypeid=b.booktypeid";
		}else{
			sql="select count(*) from book_small_type s,book_type b " +
			"where s.booktypeid=b.booktypeid";
		}
		return SqlHelper.executeQuery(sql, parameters);
	}
	
	public  List executeFindById(String [] parameters){
		String sql="select s.bookSmallid,s.booksmalltypename,b.bookMajorType from book_small_type s,book_type b " +
				"where s.booktypeid=b.booktypeid and s.bookSmallid=?";
		return SqlHelper.executeQuery1(sql, parameters);
	}
	
	public void executeUpdateBookSmallType(String[] parameters){
		String sql="update book_small_type set booksmalltypename=? where bookSmallid=?";
		SqlHelper.executeUpdate(sql, parameters);
	}

}
