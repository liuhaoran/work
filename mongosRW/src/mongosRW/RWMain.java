package mongosRW;

import java.net.UnknownHostException;

import mongosRW.MongoDb;

public class RWMain {
	public static void main(String[] args) throws UnknownHostException {

        long rows = 0;
        long start = System.currentTimeMillis();
        
        if ( args.length < 4 ) {
            usage(1);
        }
               
        	String operation1 = "select";
        	String operation2 = args[0];
        	long Readrows = Long.parseLong(args[1]);  //��Ҫ���Բ��������  
            int Readtnum = Integer.parseInt(args[2]);
            long WriteRow = Long.parseLong(args[3]);
            int WriteTnum = Integer.parseInt(args[4]);
            long Readbatchnum = Long.parseLong(args[5]);
            long Writebatchnum = Long.parseLong(args[6]);
            
            
        	String host = "172.17.0.9"; //mongos �����ַ
        	String dbname = "rtm";  //���ݿ�����
        	
        	int tnum = 2;
//        	ʵ��invoke �� ʵ���� ���� һ�������� ��һ��д����
            MongoInvoke mongoWthread = new MongoInvoke(operation2, WriteRow, host, dbname, Writebatchnum, WriteTnum);
            MongoInvoke mongoRthread = new MongoInvoke(operation1, Readrows, host, dbname, Readbatchnum, Readtnum);
            mongoWthread.start();
            mongoRthread.start();
                        
    }
	
	public static void usage(int errorno){
        System.out.print("Usage:\n");
//        System.out.print("mysql test:\n");
//        System.out.print("java -jar mongotest.jar < mysql > < [select | update | insert] > < rows > <concurrent> < host > < username > < password>  <database> \n");
        System.out.print("mongo Select:\n");
//        System.out.print("java -jar mongotest.jar < mongo > < [select | update | insert] > < rows > <concurrent> < host > <database> \n");
//        System.out.print("java -jar mongoWrite.jar <insertOne|insertMany> < rows > <threads> <batchrow> \n");
        System.out.print("java -jar mongoRW.jar <WriteOp:insertOne|insertMany> <Rrows> <Rthread> <Wrows> <Wthreads> <Rbatch> <Wbatch> \n");
        System.exit( errorno );
    }
}
