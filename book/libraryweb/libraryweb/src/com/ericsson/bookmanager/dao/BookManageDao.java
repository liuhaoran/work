package com.ericsson.bookmanager.dao;

import java.util.ArrayList;

import com.ericsson.dao.BaseDao;

public interface BookManageDao extends BaseDao{
	public ArrayList executeQuery1(String[] parameters);
	
}
