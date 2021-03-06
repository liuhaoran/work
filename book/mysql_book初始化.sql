读者类型表
insert into reader_type(gender,borrowNumber,borrowTime,xujiecishu,reserverBook) values(1,15,30,1,5);
insert into reader_type(gender,borrowNumber,borrowTime,xujiecishu,reserverBook) values(2,10,30,1,5);
insert into reader_type(gender,borrowNumber,borrowTime,xujiecishu,reserverBook) values(32,20,30,2,10);
读者基本信息表
insert into reader_info(name,password,sex,tel,createTime,endTime,idCard,readertypeid) values('xiaoming','123456','男','15866668888','2016-1-1','2020-12-31','410611198510306339',1);
insert into reader_info(name,password,sex,tel,createTime,endTime,idCard,readertypeid) values('laowang','123456','男','15866668666','2016-1-1','2020-12-31','410611198510306355',2);
insert into reader_info(name,password,sex,tel,createTime,endTime,idCard,readertypeid) values('xiaohong','123456','女','15866668222','2016-1-1','2020-12-31','410611198510306339',3);
insert into reader_info(name,password,sex,tel,createTime,endTime,idCard,readertypeid) values('test','123456','女','15866668222',curdate(),date_add(curdate(), interval 36 month),'410611198510306339',2);

图书基本信息表
insert into book_info(bookIsbn,bookName,bookAuthor,bookPublish,bookPrice,bookSmallid) values('7121016834','使用Ant进行Java开发','[美]Erik Hatcher','电子工业出版社',60,18);
insert into book_info(bookIsbn,bookName,bookAuthor,bookPublish,bookPrice,bookSmallid) values('5121016835','自我激励的100种方法','史蒂夫.钱德勒','人民邮电出版社',30,6);
insert into book_info(bookIsbn,bookName,bookAuthor,bookPublish,bookPrice,bookSmallid) values('5121016832','从来没有太晚的开始','【韩】韩京姬','人民邮电出版社',30,1);
insert into book_info(bookIsbn,bookName,bookAuthor,bookPublish,bookPrice,bookSmallid) values('2121016835','在难搞的日子笑出声来','大鹏','人民邮电出版社',30,2);
insert into book_info(bookIsbn,bookName,bookAuthor,bookPublish,bookPrice,bookSmallid) values('4121016832','天龙八部','金庸','人民邮电出版社',50,4);
insert into book_info(bookIsbn,bookName,bookAuthor,bookPublish,bookPrice,bookSmallid) values('3121016835','唐诗三百首','中国','人民邮电出版社',30,8);
insert into book_info(bookIsbn,bookName,bookAuthor,bookPublish,bookPrice,bookSmallid) values('7121416832','人体素描','小明','电子工业出版社',50,10);
insert into book_info(bookIsbn,bookName,bookAuthor,bookPublish,bookPrice,bookSmallid) values('6121416832','Linux入门到精通','小牛','电子工业出版社',50,17);
insert into book_info(bookIsbn,bookName,bookAuthor,bookPublish,bookPrice,bookSmallid) values('6221416832','java入门到精通','小羊','电子工业出版社','60',18);
insert into book_info(bookIsbn,bookName,bookAuthor,bookPublish,bookPrice,bookSmallid) values('6221416832','C语言入门到精通','马云','电子工业出版社','60',18);
insert into book_info(bookIsbn,bookName,bookAuthor,bookPublish,bookPrice,bookSmallid) values('6231416832','C++入门到精通','马化腾','电子工业出版社','50',18);

书籍类型表
insert into book_type(bookMajorType) values('青春');
insert into book_type(bookMajorType) values('小说');
insert into book_type(bookMajorType) values('文学');
insert into book_type(bookMajorType) values('艺术');
insert into book_type(bookMajorType) values('娱乐时尚');
insert into book_type(bookMajorType) values('生活');
insert into book_type(bookMajorType) values('计算机');

书籍小类型表
insert into book_small_type(book_small_type_name,bookTypeId) values('校园',1);
insert into book_small_type(book_small_type_name,bookTypeId) values('爱情',1);
insert into book_small_type(book_small_type_name,bookTypeId) values('叛逆',1);
insert into book_small_type(book_small_type_name,bookTypeId) values('武侠小说',2);
insert into book_small_type(book_small_type_name,bookTypeId) values('言情小说',2);
insert into book_small_type(book_small_type_name,bookTypeId) values('都市小说',2);
insert into book_small_type(book_small_type_name,bookTypeId) values('民间文学',3);
insert into book_small_type(book_small_type_name,bookTypeId) values('中国古诗词',3);
insert into book_small_type(book_small_type_name,bookTypeId) values('外国诗歌',3);
insert into book_small_type(book_small_type_name,bookTypeId) values('艺术理论',4);
insert into book_small_type(book_small_type_name,bookTypeId) values('人体艺术',4);
insert into book_small_type(book_small_type_name,bookTypeId) values('游戏',5);
insert into book_small_type(book_small_type_name,bookTypeId) values('瑜伽',5);
insert into book_small_type(book_small_type_name,bookTypeId) values('家居与风水',6);
insert into book_small_type(book_small_type_name,bookTypeId) values('爱车一族',6);
insert into book_small_type(book_small_type_name,bookTypeId) values('减肥健身',6);
insert into book_small_type(book_small_type_name,bookTypeId) values('操作系统',7);
insert into book_small_type(book_small_type_name,bookTypeId) values('程序设计',7);
insert into book_small_type(book_small_type_name,bookTypeId) values('数据库',7);
insert into book_small_type(book_small_type_name,bookTypeId) values('网络与数据通信',7);
insert into book_small_type(book_small_type_name,bookTypeId) values('信息安全',7);

图书借阅表
insert into borrow_list(bookId,readerId,bookManageId,borrowTime,returnTime,xujiecishu,status) values(3,1,2,'2015-3-2','2015-4-2',0,'1');
insert into borrow_list(bookId,readerId,bookManageId,borrowTime,returnTime,xujiecishu,status) values(4,1,2,'2015-6-2','2015-7-2',0,'2');
insert into borrow_list(bookId,readerId,bookManageId,borrowTime,returnTime,xujiecishu,status) values(5,1,2,'2015-8-2','2015-9-2',0,'2');
insert into borrow_list(bookId,readerId,bookManageId,borrowTime,returnTime,xujiecishu,status) values(6,1,2,'2015-5-2','2015-6-2',0,'2');
insert into borrow_list(bookId,readerId,bookManageId,borrowTime,returnTime,xujiecishu,status) values(7,1,2,'2015-5-2','2015-6-2',0,'3');
insert into borrow_list(bookId,readerId,bookManageId,borrowTime,returnTime,xujiecishu,status) values(8,1,2,'2015-5-2','2015-6-2',0,'4');

select * from borrow_list;

图书管理员表
insert into book_manage(name,password) values('admin','123');


图书归还表
insert into return_list(borrowId,bookManageId,returnTime) values(4,'2','2015-7-29');
insert into return_list(borrowId,bookManageId,returnTime) values(5,'2','2015-3-19');
insert into return_list(borrowId,bookManageId,returnTime) values(6,'2','2015-6-20');
insert into return_list(borrowId,bookManageId,returnTime) values(7,'2','2015-8-22');
insert into return_list(borrowId,bookManageId,returnTime) values(8,'2','2015-5-26');

图书丢失表
insert into book_lost(borrowId,bookManageId,note) values(8,2,'图书丢失，需赔偿');


图书罚款表
insert into book_fine(borrowId,bookManageId,fineReason,fineTime,fineMoney,note) values(8,'2','图书丢失','2015-7-20','30','图书丢失，已赔偿');


select * from reader_info where readetyperid like '%?%' and name like '%?%' " +
				"idcard like '%?%' limit "+pageNow+","+pageSize+""
				
select count(*) from reader_info where readertypeid like '%?%' and name like '%?%' " +
		"idcard like '%?%'				


select * from reader_info " +
				"limit "+pageNow+","+pageSize+"
				
select count(*) from reader_info				
				
	sql="select s.bookSmallid,s.book_small_type_name,b.bookMajorType from book_small_type s,book_type b " +
			"where s.booktypeid=b.booktypeid order by s.bookSmallid limit "+pageNow+","+pageSize+"";
			
			sql="select s.bookSmallid, s.book_small_type_name,s.booktypeid from book_small_type s limit "+pageNow+","+pageSize+"";		
			
	
	 alter table book_small_type change  book_small_type_name booksmalltypename varchar(30) not null;

