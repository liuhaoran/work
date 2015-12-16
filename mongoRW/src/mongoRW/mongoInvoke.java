package mongoRW;

import java.net.UnknownHostException;

import com.mongodb.MongoClient;

public class mongoInvoke extends Thread{
	private String operation;
	private long rows;
	private String host;
	private String dbname;
	private long batchnum;
	private int tnum;
	
	public mongoInvoke(String operation,long rows,String host,String dbname ,long batchnum,int tnum) throws UnknownHostException{
		this.operation = operation;
		this.rows = rows;
		this.host = host;
		this.dbname = dbname;
		this.batchnum = batchnum;
		this.tnum = tnum;
	}
	
	public void run(){
		long start = System.currentTimeMillis();
		
		mongodb[] mongothread = new mongodb[this.tnum];
        for ( int k = 0; k < this.tnum; k++ ) {
            try {
				mongothread[k] = new mongodb(this.operation,this.rows,this.host,this.dbname,this.batchnum,k);
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
            mongothread[k].start();
        }
        
//        确保所有进程都已经完成后 进入下面的 sysout输出模块
        for ( int k = 0; k < this.tnum; k++ ) {
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
    long tresult = rows*tnum/endtime;

    System.out.print("Total thread:" + this.tnum + "\n");
    System.out.print("Total run time:" + endtime + " sec\n");
    System.out.print("Per-thread rows:" + this.rows + "\n");
    System.out.print("Per-thread " + "mongo" + " " + this.operation + " Result:" + result + "row/sec\n");
    System.out.print("Total-thread rows:" + this.rows * this.tnum + "\n");
    System.out.print("Total-thread " + "mongo" + " " + this.operation + " Result:" + tresult + "row/sec\n");
	}
}
