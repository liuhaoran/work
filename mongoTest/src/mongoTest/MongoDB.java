package mongoTest;

import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.bson.Document;
//import org.bson.BSON;

import com.mongodb.BasicDBObject;
import com.mongodb.InsertOptions;
import com.mongodb.MongoClient;
import com.mongodb.WriteConcern;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.InsertManyOptions;

public class MongoDB extends Thread {
	private MongoClient mongoClient;
	private MongoDatabase db;   //数据库名   
	private String operation;   //操作类型
	private String colname;		//集合名字
	private long len;			//操作记录数
//	private long batchnum;		//批量操作数
	private int threadnum;		//线程数
//	collection 已日期来命名
	
	public MongoDB(String host,int port,String dbname,String colname,String operation,long len,int threadnum) throws UnknownHostException{
		this.mongoClient = new MongoClient(host, port);
		this.db = mongoClient.getDatabase(dbname);
		this.colname = colname;
		this.len = len;
		this.operation = operation;
		this.threadnum = threadnum;
	}
	
	@Override
	public void run(){
		
		if(this.operation.equals("insertOne")){
    	this.insertOne();
//		}else if(this.operation.equals("insertMany")){
//		this.insertMany();	
		}else if(this.operation.equals("select")){
			int find = this.findBasic();
			System.out.println("thread:"+this.threadnum+"==总共能够找到:"+find+"条记录");
		}
    }
	
	public static Document sendData(String mobile, String chnlId, String campId ,int campTimeType){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = formatter.format(new Date()); 
        Document sendData = new Document("mobile",mobile)
        .append("chnlid", chnlId)
        .append("campid", campId)
        .append("campTimeType", campTimeType)
        .append("sendtime",date);
      
        return sendData;
    }
	// 定义一系列方法 来操作 mongodb	
    public void insertOne(){
        MongoCollection<Document> coll = this.db.getCollection(colname);
        Document sendData;
        
        InsertOptions option = new InsertOptions();
//        option.writeConcern(WriteConcern.ACKNOWLEDGED);
        for(long i=0L; i<this.len; i++){
        	long mobile = 10000000000L;
            Random random = new Random();
            mobile = mobile + (long) random.nextInt(10000000);       	
        	sendData = sendData(String.valueOf(mobile),"chnl00001","vc000001",1);
            coll.insertOne(sendData);
        }
    }
    /*
    public void insertMany(){
    	long i =0;
    	
    	while(i<this.len){
    		
        List<Document> documents = new ArrayList<Document>();
        // 批量插入1W 记录
        for(long j=0L; j<this.batchnum; j++){
        	long mobile = 10000000000L;
            Random random = new Random();
            mobile = mobile + (long) random.nextInt(10000000);
            documents.add(sendData(String.valueOf(mobile),"chnl00001","vc000001",1));
        }
        
        MongoCollection<Document> coll = db.getCollection(colname);
        InsertManyOptions option = new InsertManyOptions();
        option.ordered(true);
        coll.insertMany(documents,option);
    	
        i = i+this.batchnum;
    	}

    }
    */

    // 只能查找一个值
    public int findBasic(){
        MongoCollection<Document> coll = db.getCollection(colname);
        int find = 0;
        
        for(long i=0L;i<this.len;i++){
        int records = 0;	
        long mobile = 10000000000L;
        Random random = new Random();
        mobile = mobile + (long) random.nextInt(10000000);
//          mobile = 10006163648L;     
//        Document myDoc = coll.find(new BasicDBObject("mobile",String.valueOf(mobile))).first();
         FindIterable<Document> myDoc1 = coll.find(new BasicDBObject("mobile",String.valueOf(mobile)));
         MongoCursor<Document> cursor = myDoc1.iterator();
         
         while(cursor.hasNext()){
        	 records++;
//        	 System.out.println(doc.toString());
        	         	 
         }
         System.out.println(mobile+" has find "+records+" records");
         find = find +records;   
        }
        
        return find;
    }
    
    // 可以查找多个值
    /*
    public void findFilter(){
        MongoCollection<Document> coll = db.getCollection(collection);
        //import static com.mongodb.client.model.Filters.*; manully
        Document myDoc = coll.find(and(eq("mobile","15902028416"),eq("type",0))).first();
        System.out.println(myDoc);
    }

    public void findWithCursor(){
        MongoCollection<Document> coll = db.getCollection(collection);
        MongoCursor<Document> cursor = coll.find(eq("mobile","15902028416")).iterator();
        try {
            while (cursor.hasNext()) {
                System.out.println(cursor.next());
            }
        } finally {
            cursor.close();
        }
    }
    /*
    public void updateWhole(){
        MongoCollection<Document> coll = db.getCollection("meters");

//      UpdateOptions options = new UpdateOptions();
//      options.upsert(true);//update or add
        coll.findOneAndUpdate(and(eq("uid",1234567),eq("type",0)), new Document("content.value","updateWhole"));
    }

    public void updateModifier(){
        MongoCollection<Document> coll = db.getCollection("meters");
        Document modifiedObject = new Document();
        modifiedObject.put("$set", new Document().append("comment", "updateModifier"));
        coll.updateOne(and(eq("uid",1234567),eq("type",0)),modifiedObject);
    }

    public void createIndex(){
        MongoCollection<Document> meters = db.getCollection("meters");
        meters.createIndex(new BasicDBObject("uid", 1).append("type", 1));
        meters.createIndex(new BasicDBObject("type", -1));
    }

    public void dropIndex(){
        MongoCollection<Document> meters = db.getCollection("meters");
        meters.dropIndex("type_-1");
//      meters.dropIndexes();
    }

    public void drop(){
        MongoCollection<Document> meters = db.getCollection("meters");
        meters.dropCollection();
    }
    
    */
	
}
