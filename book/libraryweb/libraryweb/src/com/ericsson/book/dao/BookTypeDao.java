package com.ericsson.book.dao;

import java.util.ArrayList;

import com.ericsson.dao.BaseDao;

public interface BookTypeDao extends BaseDao{
	public ArrayList executeQuery1(String[] parameters);
	public boolean executeInsert(String[] parameters);
}
