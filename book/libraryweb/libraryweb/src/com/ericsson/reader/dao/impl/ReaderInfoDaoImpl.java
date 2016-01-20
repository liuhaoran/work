package com.ericsson.reader.dao.impl;

import java.sql.ResultSet;
import java.util.List;

import com.ericsson.reader.dao.ReaderInfoDao;
import com.ericsson.util.SqlHelper;

public class ReaderInfoDaoImpl implements ReaderInfoDao{

     public boolean executeUpdate(String[] parameters) {
    	 
    	 String sql ="insert into reader_info(readertypeid,name,password,sex,tel,createTime,endTime,idCard) " +
    		"values(?,?,?,?,?,curdate(),date_add(curdate(), interval 36 month),?)";
 		 return SqlHelper.executeUpdate3(sql, parameters);
 	}

	public List executeQueryByPage(String[] parameters, int pageSize,
			int pageNow) {
		String parameters1[]=null;
		String sql="";
		if(parameters[0]!=null){
			sql="select * from reader_info where readertypeid like ? " +
			"limit "+pageNow+","+pageSize+"";
			parameters1=new String[]{"%"+parameters[0]+"%"};
			if(parameters[1]!=null){
				sql="select * from reader_info where readertypeid like ? and name like ? " +
				"limit "+pageNow+","+pageSize+"";
				parameters1=new String[]{"%"+parameters[0]+"%","%"+parameters[1]+"%"};
				if(parameters[2]!=null){
					sql="select * from reader_info where readertypeid like ? and name like ? and idcard like ? " +
					"limit "+pageNow+","+pageSize+"";
					parameters1=new String[]{"%"+parameters[0]+"%","%"+parameters[1]+"%","%"+parameters[2]+"%"};
				}
			}
		}else if(parameters[1]!=null){
			sql="select * from reader_info where name like ? " +
			"limit "+pageNow+","+pageSize+"";
			parameters1=new String[]{"%"+parameters[1]+"%"};
			if(parameters[2]!=null){
				sql="select * from reader_info where name like ? and idcard like ? " +
				"limit "+pageNow+","+pageSize+"";
				parameters1=new String[]{"%"+parameters[1]+"%","%"+parameters[2]+"%"};
			}
		}else if(parameters[2]!=null){
			sql="select * from reader_info where idcard like ? " +
			"limit "+pageNow+","+pageSize+"";
			parameters1=new String[]{"%"+parameters[2]+"%"};
		}else{
			sql="select * from reader_info " +
			"limit "+pageNow+","+pageSize+"";
		}
		return SqlHelper.executeQuery1(sql, parameters1);
	}

	public ResultSet getCount(String[] parameters) {
		String sql=""; 
		String parameters1[]=null;
		if(parameters[0]!=null){
			sql="select count(*) from reader_info where readertypeid like ?";
			parameters1=new String[]{"%"+parameters[0]+"%"};
			if(parameters[1]!=null){
				sql="select count(*) from reader_info where readertypeid like ? and name like ?";
				parameters1=new String[]{"%"+parameters[0]+"%","%"+parameters[1]+"%"};
				if(parameters[2]!=null){
					sql="select count(*) from reader_info where readertypeid like ? and name like ? and idcard like ?";
					parameters1=new String[]{"%"+parameters[0]+"%","%"+parameters[1]+"%","%"+parameters[2]+"%"};
				}
			}
		}else if(parameters[1]!=null){
			sql="select count(*) from reader_info where name like ?";
			parameters1=new String[]{"%"+parameters[1]+"%"};
			if(parameters[2]!=null){
				sql="select count(*) from reader_info where name like ? and idcard like ?";
				parameters1=new String[]{"%"+parameters[1]+"%","%"+parameters[2]+"%"};
			}
		}else if(parameters[2]!=null){
			sql="select count(*) from reader_info where idcard like ?";
			parameters1=new String[]{"%"+parameters[2]+"%"};
		}else{
			sql="select count(*) from reader_info";
		}
		return SqlHelper.executeQuery(sql, parameters1);
	}
	
	public void executeDelete(String[] parameters){
		String sql="delete from reader_info where readerid=?";
		SqlHelper.executeUpdate(sql, parameters);
	}
	
	public void executeUpdateReader(String[] parameters){
		String sql="update reader_info set readertypeid=?,name=?,password=?,sex=?,tel=?,idcard=? where readerid=?";
		SqlHelper.executeUpdate(sql, parameters);
		
	}
	
	public  List executeFindById(String [] parameters){
		String sql="select * from reader_info where readerid=?";
		return SqlHelper.executeQuery1(sql, parameters);
	}
}
