package mongoTest;

import java.net.UnknownHostException;

import mongoTest.MongoDB;

public class MongoMain {
	public static void main(String[] args) throws UnknownHostException {

        long rows = 0;
        long start = System.currentTimeMillis();
        
        long readThreadRows;
        long writeThreadRows;
        int readThreads;
        int writeThreads;
        
//        mongoDB的部分参数
        
        
        
        
        if ( args.length < 5 ) {
            usage(1);
        }
               
        	String operation = args[0];
        	String host = args[1];
        	String port = args[2];
        	String dbname = args[3];
        	String colname = args[4];
        	
        	
        	if(operation.equals("read")){
        		if(args.length<7){
        			usage(1);
        		}
        		readThreadRows = Long.parseLong(args[5]);
        		readThreads = Integer.parseInt(args[6]);
        		
        		String option = "select";
//        		String host,int port,String dbname,String colname,String operation,long rows,long batchnum,int threadnum
        		MongoInvoke mongoRead = new MongoInvoke(host,Integer.parseInt(port),dbname,colname,option,readThreadRows,readThreads);
        		mongoRead.start();
        	}
        	
        	if(operation.equals("write")){       		
        		if(args.length<7){
        			usage(1);
        		}
        		writeThreadRows = Long.parseLong(args[5]);
        		writeThreads = Integer.parseInt(args[6]);
        		
        		String option = "insertOne";
//        		String host,int port,String dbname,String colname,String operation,long rows,long batchnum,int threadnum
        		MongoInvoke mongoRead = new MongoInvoke(host,Integer.parseInt(port),dbname,colname,option,writeThreadRows,writeThreads);
        		mongoRead.start();      		
        	}
        	
        	if(operation.equals("rw")){       		
        		if(args.length<9){
        			usage(1);
        		}
        		readThreadRows = Long.parseLong(args[5]);
        		readThreads = Integer.parseInt(args[6]);
        		
        		writeThreadRows = Long.parseLong(args[7]);
        		writeThreads = Integer.parseInt(args[8]);
        		
        		String option1 = "select";
        		String option2 = "insertOne";
//        		String host,int port,String dbname,String colname,String operation,long rows,long batchnum,int threadnum
        		MongoInvoke mongoRead1 = new MongoInvoke(host,Integer.parseInt(port),dbname,colname,option1,readThreadRows,readThreads);
        		MongoInvoke mongoRead2 = new MongoInvoke(host,Integer.parseInt(port),dbname,colname,option2,writeThreadRows,writeThreads);
        		mongoRead1.start();
        		mongoRead2.start();
        	}                      
    }
	
	public static void usage(int errorno){
        System.out.print("Usage:\n");
//        System.out.print("mysql test:\n");
//        System.out.print("java -jar mongotest.jar < mysql > < [select | update | insert] > < rows > <concurrent> < host > < username > < password>  <database> \n");
//        System.out.print("mongo Select:\n");
//        System.out.print("java -jar mongotest.jar < mongo > < [select | update | insert] > < rows > <concurrent> < host > <database> \n");
//        System.out.print("java -jar mongoWrite.jar <insertOne|insertMany> < rows > <threads> <batchrow> \n");
//        System.out.print("java -jar mongosRW.jar <WriteOp:insertOne|insertMany> <Rrows> <Rthread> <Wrows> <Wthreads> <Rbatch> <Wbatch> \n");
        System.out.println("java -jar mongoTest.jar read  <host> <port> <dbname> <colname> <1thread-rows> <threads> ");
        System.out.println("java -jar mongoTest.jar write <host> <port> <dbname> <colname> <1thread-rows> <threads> ");
        System.out.println("java -jar mongoTest.jar rw 	  <host> <port> <dbname> <colname> <r-thread-rows> <r-threads> <w-thread-rows> <w-thread>");
        System.exit( errorno );
    }
}
