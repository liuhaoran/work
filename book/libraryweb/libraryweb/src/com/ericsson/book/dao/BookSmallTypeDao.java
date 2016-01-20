package com.ericsson.book.dao;

import java.sql.ResultSet;
import java.util.List;

import com.ericsson.dao.BaseDao;

public interface BookSmallTypeDao extends BaseDao{
	
	public  List executeQueryByPage(String [] parameters,int pageSize,int pageNow);
	public ResultSet getCount(String[] parameters);
	public  List executeFindById(String [] parameters);
	public void executeUpdateBookSmallType(String[] parameters);

}
