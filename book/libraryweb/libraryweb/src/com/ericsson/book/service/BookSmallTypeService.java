package com.ericsson.book.service;

import java.sql.ResultSet;
import java.util.List;

public interface BookSmallTypeService {
	public  List executeQueryByPage(String [] parameters,int pageSize,int pageNow);
	public ResultSet getCount(String[] parameters);
	public  List executeFindById(String [] parameters);
	public void executeUpdateBookSmallType(String[] parameters);
}
