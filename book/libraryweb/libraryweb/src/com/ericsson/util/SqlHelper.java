package com.ericsson.util;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import java.sql.*;

/**
 * jdbc封装工具类
 * @author cheng
 *
 */
public final class SqlHelper
{
    //定义变量
    private static Connection ct = null;
    private static PreparedStatement ps = null;
    private static ResultSet rs = null;
   
    //连接数据库的参数
    private static String url = "";
    private static String username = "";
    private static String driver = "";
    private static String password = "";
    private static Properties  pp = null;
    private static InputStream fis = null;
    //加载驱动，只需要一次，用静态代码块
    static
    {
        try
        {
            //从mysql.properties
            pp = new Properties();
            fis=SqlHelper.class.getClassLoader().getResourceAsStream("mysql.properties");
            //fis = new FileInputStream();
            pp.load(fis);
            url = pp.getProperty("url");
            username = pp.getProperty("username");
            driver = pp.getProperty("driver");
            password = pp.getProperty("password");
           
            Class.forName(driver);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            { fis.close();}
            catch(IOException e) {e.printStackTrace();}
            fis = null;
        }
       
    }
    //得到连接
    public static Connection getConnection()
        {
            try
            {ct = DriverManager.getConnection(url,username,password);}
            catch(Exception e) {e.printStackTrace();}
            return ct;
        }
   
   
    public static ResultSet executeQuery(String sql,String[] parameters)
    {
        try
        {
            ct=getConnection();
            ps=ct.prepareStatement(sql);
            if(parameters!=null)
            {
                for(int i=0;i<parameters.length;i++)
                {
                    ps.setString(i+1,parameters[i]);
                }
            }
            rs = ps.executeQuery();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return rs;
    }
    
    //统一的select语句，为了能够访问结果集，将结果集放入ArrayList，这样可以直接关闭资源
    public static ArrayList executeQuery1(String sql, String[] parameters) {
        ArrayList results = new ArrayList();
         
        try {
        	 ct=getConnection();
             ps=ct.prepareStatement(sql);
             
            if (parameters != null) {
                for (int i=0; i<parameters.length; i++) {
                    ps.setString(i+1, parameters[i]);
                }
            }
             
            rs = ps.executeQuery();
             
            ResultSetMetaData rsmd = rs.getMetaData();
            int column = rsmd.getColumnCount();
             
            while (rs.next()) {
                Object[] objects = new Object[column];
                 
                for (int i=1; i<=column; i++) {
                    objects[i-1] = rs.getObject(i);
                }
                 
                results.add(objects);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        } finally {
        	close(rs,ps,ct);
        }
        return results; 
    }
     
    //可以执行多个update、delete、insert语句（考虑事务）
    public static void executeUpdate2(String[] sql,String[][] parameters)
    {
        try
        {
            ct = getConnection();
            ct.setAutoCommit(false);
           
            for(int i=0;i<sql.length;i++)
            {
               
                if(null!=parameters[i])
                {
                    ps = ct.prepareStatement(sql[i]);
                    for(int j=0;j<parameters[i].length;j++)
                    {
                        ps.setString(j+1,parameters[i][j]);
                    }
                    ps.executeUpdate();
                }
               
            }
            
            ct.commit();
            
        }catch (Exception e)
        {
            e.printStackTrace();
            try
            {
                ct.rollback();
            }
            catch (SQLException e1)
            {
                e1.printStackTrace();
            }
            throw  new RuntimeException(e.getMessage());
        }finally
        {
            close(rs,ps,ct);
        }
       
    }
   
    //该方法执行一个update/delete/insert语句
    //sql语句是带问号的格式，如：update table_name set column_name = ? where ...
    //parameters = {"...", "..."...}；
    public static void executeUpdate(String sql,String[] parameters)
    {
        try
        {
            ct=getConnection();
            ps = ct.prepareStatement(sql);
            if(parameters!=null)
            {
                for(int i=0;i<parameters.length;i++)
                {
                    ps.setString(i+1,parameters[i]);
                }
                           
            }
            ps.executeUpdate();
        }
        catch(Exception e)
        {
            e.printStackTrace();//开发阶段
            //抛出异常
            //可以处理，也可以不处理
            throw new RuntimeException(e.getMessage());
        }
        finally
        {
            close(rs,ps,ct);
        }
    }
    
  //该方法执行一个update/delete/insert语句
    //sql语句是带问号的格式，如：update table_name set column_name = ? where ...
    //parameters = {"...", "..."...}；
    public static boolean executeUpdate3(String sql,String[] parameters)
    {
    	Boolean b=false;
    	try
        {
            ct=getConnection();
            ps = ct.prepareStatement(sql);
            if(parameters!=null)
            {
                for(int i=0;i<parameters.length;i++)
                {
                    ps.setString(i+1,parameters[i]);
                }
                           
            }
            int i=ps.executeUpdate();
            if(i>0){
            	b=true;
            }else {
				b=false;
			}
        }
        catch(Exception e)
        {
            e.printStackTrace();//开发阶段
            //抛出异常
            //可以处理，也可以不处理
            throw new RuntimeException(e.getMessage());
        }
        finally
        {
            close(rs,ps,ct);
        }
        return b;
    }
   
    public static void close(ResultSet rs,PreparedStatement ps,Connection ct)
    {
        //关闭资源(先开后关)
        if(rs!=null)
        {
            try
            {
                rs.close();
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }
            rs=null;
        }
        if(ps!=null)
        {
            try
            {
                ps.close();
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }
            ps=null;
        }
        if(null!=ct)
        {
            try
            {
                ct.close();
            }
            catch(SQLException e)
            {
                e.printStackTrace();
            }
            ct=null;
        }
    }
}