package com.ericsson.reader.dao;

import java.sql.ResultSet;
import java.util.List;

import com.ericsson.dao.BaseDao;

public interface ReaderInfoDao extends BaseDao{
	public  List executeQueryByPage(String [] parameters,int pageSize,int pageNow);
	public ResultSet getCount(String[] parameters);
	public void executeDelete(String[] parameters);
	public void executeUpdateReader(String[] parameters);
	public  List executeFindById(String [] parameters);
}
