package mongoWrite;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.naming.directory.BasicAttribute;

import org.bson.Document;

import static com.mongodb.client.model.Filters.*;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.InsertOptions;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.WriteConcern;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.InsertManyOptions;
import com.mongodb.client.model.UpdateOptions;

public class mongodb extends Thread {
	private MongoClient mongoClient;
	private MongoDatabase db;
	private String operation;
	private long len;
	private long batchnum;
	private int tnum;
//	collection 已日期来命名
	private String collection = "test"+new SimpleDateFormat("yyyyMMdd").format(new Date());
	
	public mongodb(String operation,long len,String host,String dbname ,long batchnum,int tnum) throws UnknownHostException{
		int port = 27017;
		this.mongoClient = new MongoClient(host, port);
		this.db = mongoClient.getDatabase(dbname);
		this.len = len;
		this.operation = operation;
		this.batchnum = batchnum;
		this.tnum = tnum;
	}
	
	@Override
	public void run(){
    	
		if(this.operation.equals("insertOne")){
    	this.insertOne();
		}else if(this.operation.equals("insertMany")){
		this.insertMany();	
		}else if(this.operation.equals("select")){
			ArrayList<Document> dlist = this.findBasic();
			int find = dlist.size();
			System.out.println("thread:"+this.tnum+"==总共能够找到:"+find+"条记录");
			System.out.println("thread:"+this.tnum+" "+String.valueOf(dlist.get(find-1)));
//			for(Document dc:dlist){
//				System.out.println("thread:"+this.tnum+"  "+String.valueOf(dc));
//			}
		}
    	/*
        if ( this.operation.equals("insert") ) {
            this.mongodb_insert();
        } else if ( this.operation.equals("update") ) {
            this.mongodb_update();
        } else {
            this.mongodb_select();
        }
		*/
    }
	
	/*
	public static Document newMeter(int uid, int type, String value){
        Document meter = new Document("uid",uid)
        .append("type", type)
        .append("content", new Document("type",type).append("value", value))
        .append("updatetime", new Date());
        return meter;
    }
	*/
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
        //insert one
        MongoCollection<Document> coll = this.db.getCollection(collection);
        Document sendData;
        
        InsertOptions option = new InsertOptions();
        option.writeConcern(WriteConcern.ACKNOWLEDGED);
        for(long i=0L; i<this.len; i++){
        	long mobile = 10000000000L;
            Random random = new Random();
            mobile = mobile + (long) random.nextInt(10000000);       	
        	sendData = sendData(String.valueOf(mobile),"chnl00001","vc000001",1);
            coll.insertOne(sendData);
        }
    }

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
        
        MongoCollection<Document> coll = db.getCollection(collection);
        InsertManyOptions option = new InsertManyOptions();
        option.ordered(true);
        coll.insertMany(documents,option);
    	
        i = i+this.batchnum;
    	}

    }

    // 只能查找一个值
    public ArrayList<Document> findBasic(){
        MongoCollection<Document> coll = db.getCollection(collection);
        int find =0;
        ArrayList<Document> dlist = new ArrayList<Document>();
        
        for(long i=0L;i<this.len;i++){
        long mobile = 10000000000L;
        Random random = new Random();
        mobile = mobile + (long) random.nextInt(10000000);
               
        Document myDoc = coll.find(new BasicDBObject("mobile",String.valueOf(mobile))).first();
//        ArrayList<Document> myDoc = (ArrayList<Document>) coll.find(new BasicDBObject("mobile",String.valueOf(mobile)));
        if(myDoc == null){        	
        }else{
        	dlist.add(myDoc);
        	find++;
        }        
//        System.out.println(myDoc);    
        }
        
        return dlist;
    }
    
    // 可以查找多个值
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


