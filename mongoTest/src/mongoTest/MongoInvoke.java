package mongoTest;

import java.net.UnknownHostException;

import com.mongodb.MongoClient;

public class MongoInvoke extends Thread{
	
	private String host;
	private int port;
	private String dbname;
	private String colname;
	private String operation;
	private long rows;
	private long batchnum;
	private int threadnum;
	
	public MongoInvoke(String host,int port,String dbname,String colname,String operation,long rows,int thread) throws UnknownHostException
	{
		this.host = host;
		this.port = port;
		this.dbname = dbname;
		this.colname = colname;
		this.operation = operation;
		this.rows = rows;
//		this.batchnum = batchnum;
		this.threadnum = thread;
	}
	
	public void run(){
		long start = System.currentTimeMillis();
		
		MongoDB[] mongothread = new MongoDB[this.threadnum];
        for ( int k = 0; k < this.threadnum; k++ ) {
            try {
//            	String host,int port,String dbname,String colname,String operation,long len,long batchnum,int threadnum
				mongothread[k] = new MongoDB(this.host
											,this.port
											,this.dbname
											,this.colname
											,this.operation
											,this.rows
											,this.threadnum
											);
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
            mongothread[k].start();
        }
        
//        确保所有进程都已经完成后 进入下面的 sysout输出模块
        for ( int k = 0; k < this.threadnum; k++ ) {
//            System.out.println("mongothread["+k+"].isAlive()=" + mongothread[k].isAlive());
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
    long tresult = rows*threadnum/endtime;

    System.out.print("Total thread:" + this.threadnum + "\n");
    System.out.print("Total run time:" + endtime + " sec\n");
    System.out.print("Per-thread rows:" + this.rows + "\n");
    System.out.print("Per-thread " + "mongo" + " " + this.operation + " Result:" + result + "row/sec\n");
    System.out.print("Total-thread rows:" + this.rows * this.threadnum + "\n");
    System.out.print("Total-thread " + "mongo" + " " + this.operation + " Result:" + tresult + "row/sec\n");
	}
}
