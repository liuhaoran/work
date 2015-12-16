package mongosw;

import java.net.UnknownHostException;

import mongosw.MongoDb;

public class WriteMain {
	
	 public static void main(String[] args) throws UnknownHostException {

	        long rows = 0;
	        long start = System.currentTimeMillis();
	        
	        if ( args.length < 4 ) {
	            usage(1);
	        }
	               
	        	String operation = args[0];
	        	rows = Long.parseLong(args[1]);  //��Ҫ���Բ��������  
	            int tnum = Integer.parseInt(args[2]);
	            long batchnum = Long.parseLong(args[3]);
	        	String host = "172.17.0.9"; //���ڲ���shard,��Ҫֱ������ mongos·�ɷ�������ַ
	        	String dbname = "rtm";  //���ݿ�����
	        	MongoDb[] mongothread = new MongoDb[tnum];
	            for ( int k = 0; k < tnum; k++ ) {
	                mongothread[k] = new MongoDb(operation,rows,host,dbname,batchnum,k);
	                mongothread[k].start();
	            }
	            
//	            ȷ�����н��̶��Ѿ���ɺ� ��������� sysout���ģ��
	            for ( int k = 0; k < tnum; k++ ) {
//	                System.out.println("mongothread["+k+"].isAlive()=" + mongothread[k].isAlive());
	                if ( mongothread[k].isAlive() ) {
	                    try {
	                        mongothread[k].join();
	                    } catch (InterruptedException ex) {
	                        System.out.println(ex.getMessage());
	                        System.out.println(ex.toString());
	                    }
	                }
	            }
	        

	        long stop = System.currentTimeMillis();
	        long endtime = (stop - start)/1000;
	        if ( endtime == 0 ) endtime = 1;
	        long result = rows/endtime;
	        long tresult = rows*tnum/endtime;

	        System.out.print("Total thread:" + tnum + "\n");
	        System.out.print("Total run time:" + endtime + " sec\n");
	        System.out.print("Per-thread rows:" + rows + "\n");
	        System.out.print("Per-thread " + "mongo" + " " + operation + " Result:" + result + "row/sec\n");
	        System.out.print("Total-thread rows:" + rows * tnum + "\n");
	        System.out.print("Total-thread " + "mongo" + " " + operation + " Result:" + tresult + "row/sec\n");
	    }

	    public static void usage(int errorno){
	        System.out.print("Usage:\n");
//	        System.out.print("mysql test:\n");
//	        System.out.print("java -jar mongotest.jar < mysql > < [select | update | insert] > < rows > <concurrent> < host > < username > < password>  <database> \n");
	        System.out.print("mongo Select:\n");
//	        System.out.print("java -jar mongotest.jar < mongo > < [select | update | insert] > < rows > <concurrent> < host > <database> \n");
	        System.out.print("java -jar mongoWrite.jar <insertOne|insertMany> < rows > <threads> <batchrow> \n");
	        System.exit( errorno );
	    }
}
