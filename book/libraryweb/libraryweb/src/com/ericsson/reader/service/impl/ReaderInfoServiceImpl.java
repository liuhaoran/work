package com.ericsson.reader.service.impl;

import java.sql.ResultSet;
import java.util.List;

import com.ericsson.reader.dao.ReaderInfoDao;
import com.ericsson.reader.dao.impl.ReaderInfoDaoImpl;
import com.ericsson.reader.service.ReaderInfoService;

public class ReaderInfoServiceImpl implements ReaderInfoService {
	
	ReaderInfoDao readerInfoDao=new ReaderInfoDaoImpl();

	public boolean executeUpdate(String[] parameters) {
		
		return readerInfoDao.executeUpdate(parameters);
	}

	public List executeQueryByPage(String[] parameters, int pageSize,
			int pageNow) {
		return readerInfoDao.executeQueryByPage(parameters, pageSize, pageNow);
	}

	public ResultSet getCount(String[] parameters) {
		return readerInfoDao.getCount(parameters);
	}
	
	
	public void executeDelete(String[] parameters){
		readerInfoDao.executeDelete(parameters);
	}
	
	public void executeUpdateReader(String[] parameters){
		readerInfoDao.executeUpdateReader(parameters);
	}
	
	public  List executeFindById(String [] parameters){
		return readerInfoDao.executeFindById(parameters);
	}
}
