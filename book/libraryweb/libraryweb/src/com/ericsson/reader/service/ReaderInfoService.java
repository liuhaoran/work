package com.ericsson.reader.service;

import java.sql.ResultSet;
import java.util.List;

import com.ericsson.service.BaseService;

public interface ReaderInfoService extends BaseService{
	
	public List executeQueryByPage(String[] parameters, int pageSize,
			int pageNow);
	public ResultSet getCount(String[] parameters);
	public void executeDelete(String[] parameters);
	public void executeUpdateReader(String[] parameters);
	public  List executeFindById(String [] parameters);
	
}
