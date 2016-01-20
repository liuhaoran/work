package com.ericsson.book.service;

import java.util.ArrayList;

import com.ericsson.service.BaseService;

public interface BookTypeService extends BaseService {
	public ArrayList executeQuery1(String[] parameters);
	public boolean executeInsert(String[] parameters);
}
