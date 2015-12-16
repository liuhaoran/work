package mongoRead;

import java.net.UnknownHostException;

import mongoRead.mongodb;

public class ReadMain {
    /**
     * @param args the command line arguments
     * @throws UnknownHostException 
     */
    public static void main(String[] args) throws UnknownHostException {

        long rows = 0;
        long start = System.currentTimeMillis();
        
        if ( args.length < 3 ) {
            usage(1);
        }
               
        	String operation = "select";
        	rows = Long.parseLong(args[0]);  //需要测试插入的条数  
            int tnum = Integer.parseInt(args[1]);
            long batchnum = Long.parseLong(args[2]);
        	String host = "172.17.0.9"; //mongodb 服务器地址
        	String dbname = "rtm";  //数据库名称
            mongodb[] mongothread = new mongodb[tnum];
            for ( int k = 0; k < tnum; k++ ) {
                mongothread[k] = new mongodb(operation,rows,host,dbname,batchnum,k);
                mongothread[k].start();
            }
            
//            确保所有进程都已经完成后 进入下面的 sysout输出模块
            for ( int k = 0; k < tnum; k++ ) {
//                System.out.println("mongothread["+k+"].isAlive()=" + mongothread[k].isAlive());
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
//        System.out.print("mysql test:\n");
//        System.out.print("java -jar mongotest.jar < mysql > < [select | update | insert] > < rows > <concurrent> < host > < username > < password>  <database> \n");
        System.out.print("mongo Select:\n");
//        System.out.print("java -jar mongotest.jar < mongo > < [select | update | insert] > < rows > <concurrent> < host > <database> \n");
        System.out.print("java -jar mongoRead.jar  < rows > <threads> <batchrow> \n");
        System.exit( errorno );
    }
}
