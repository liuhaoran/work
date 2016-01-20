<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!-- 
<%@ page import="com.ericsson.bean.*"%>
-->
<%@ page import="com.ericsson.bookmanager.bean.*"%>
<!DOCTYPE>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>图书管理系统</title>
		<link type="text/css" rel="stylesheet" href="css/style.css" />
		<link type="text/css" rel="stylesheet" href="css/style1.css" />
		<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
		<script type="text/javascript" src="js/menu.js"></script>
		<%
			BookManager bookManager= (BookManager)session.getAttribute("bookManager"); //得到当前登陆的学生学号
		 			String name="";
		 			if(bookManager!=null){
		 				name=bookManager.getName();
		 			}else{
		 				name="未登录!";
		 			}
		%>
	</head>

	<body>
		<div class="top"></div>
		<div id="header">
		 <span ><img src="images/logo.png" width="80"></span>
			<div class="logo">
				图书管理系统
			</div>
			<div class="navigation">
				<ul>
					<li>
						欢迎您！
					</li>
					<li>
						<a href="#"><%=name %></a>
					</li>
					<li>
						<a href="changepassword.jsp" target="openhere">修改密码</a>
					</li>
					<li>
						<a href="#">设置</a>
					</li>
					<li>
						<a href="/libraryweb/BookManageServlet?action=logout">退出</a>
					</li>
				</ul>
			</div>
			<!--  
			<div id="menu">
				<ul>
					<li>
						<a href="#">首页</a>
					</li>
					<li>
						<a href="#">读者管理</a>
					</li>
					<li>
						<a href="#">借阅管理</a>
					</li>
					<li>
						<a href="#">图书管理</a>
					</li>
					<li>
						<a href="#">书籍查询</a>
					</li>
					<li>
						<a href="#">书架管理</a>
					</li>
					<li>
						<a href="#">系统维护</a>
					</li>
				</ul>
			</div>
			-->
		</div>
		<div id="content">
			<div class="left_menu">
				<ul class="ce">
					<li>
						<a class="M1" href="#">读者管理<img class="more"
								src="images/more.png" /> </a>
						<ul class="er">
							<li>
								<a href="addreader.jsp" target="openhere">读者添加</a>
							</li>
							<li>
								<a href="/libraryweb/ReaderInfoServlet?action=queryreader" target="openhere">读者查询</a>
							</li>
						</ul>
					</li>
					<li>
						<a class="M2" href="#">借阅管理<img class="more"
								src="images/more.png" /> </a>
						<ul class="er">
							<li>
								<a href="##">图书借出</a>
							</li>
							<li class="e_li">
								<a href="##">图书归还</a>
								<ul class="thr">
									<li>
										<a href="##">图书归还 <img class="more1"
												src="images/more1.png" /> </a>
										<div class="thr_nr">
											<ul>
												<li>
													<a href=''>归还登记</a>
												</li>
											</ul>
										</div>
									</li>
									
									<li>
										<a href="##">图书丢失 <img class="more1"
												src="images/more1.png" /> </a>
										<div class="thr_nr">
											<ul>
												<li>
													<a href=''>丢失登记</a>
												</li>
											</ul>
										</div>
									</li>
									<li>
										<a href="##">图书损坏 <img class="more1"
												src="images/more1.png" /> </a>
										<div class="thr_nr">
											<ul>
												<li>
													<a href=''>损坏登记</a>
												</li>
											</ul>
										</div>
									</li>
									<div class="clear"></div>
								</ul>
							</li>
							<li class="e_li">
								<a href="##">罚款管理</a>
								<ul class="thr">
									<li>
										<a href="##">罚款查询</a>
									</li>
									<li>
										<a href="##">结算罚款</a>
									</li>
									<div class="clear"></div>
								</ul>
							</li>
						</ul>
					</li>
					<li>
						<a class="M3" href="#">图书管理<img class="more"
								src="images/more.png" /> </a>
						<ul class="er">
							<li class="e_li">
								<a href="##">类别管理</a>
								<ul class="thr">
									<li>
										<a href="##">类别添加 <img class="more1"
											src="images/more1.png" /></a>
										<div class="thr_nr">
											<ul>
												<li>
													<a href="addbooktype.jsp" target="openhere">大类添加</a>
												</li>
												<li>
												</li>
											</ul>
										</div>	
												
									</li>
									<li>
										<a href="/libraryweb/BookSmallTypeServlet?action=querybooksmalltype" target="openhere">类别查询</a>
									</li>
								</ul>
							</li>
							<li class="e_li">
								<a href="##">信息管理</a>
								<ul class="thr">
									<li>
										<a href="##">图书添加</a>
									</li>
									<li>
										<a href="##">图书修改</a>
									</li>
								</ul>
							</li>
						</ul>
					</li>
					<li>
						<a class="M4" href="#">书籍查询<img class="more"
								src="images/more.png" /> </a>
						<ul class="er">
							<li>
								<a href="##">基本信息查询</a>
							</li>
							<li>
								<a href="##">当前借阅查询</a>
							</li>
							<li>
								<a href="##">历史借阅查询</a>
							</li>
						</ul>
					</li>
					<li>
						<a class="M5" href="#">书架管理<img class="more" src="images/more.png" /> </a>
						<ul class="er">
							<li>
								<a href="##">书架添加</a>
							</li>
							<li>
								<a href="##">书架修改</a>
							</li>
							<li>
								<a href="##">书架删除</a>
							</li>
						</ul>
					</li>
					<li>
						<a class="M6" href="#">系统维护<img class="more" src="images/more.png" /> </a>
						<ul class="er">
							<li class="e_li">
								<a href="##">用户管理</a>
								<ul class="thr">
									<li>
										<a href="##">调整角色<img class="more1" src="images/more1.png" />
										</a>
										<div class="thr_nr">
											<ul>
												<li>
													<a href=''>分配角色</a>
												</li>
												<li>
													<a href=''>调整角色</a>
												</li>
												<li>
													<a href=''>松绑角色</a>
												</li>
											</ul>
										</div>
									</li>
									<li>
										<a href="##">更改密码</a>
									</li>
									<li>
										<a href="##">用户添加</a>
									</li>
									<li>
										<a href="##">用户删除</a>
									</li>
									<li>
										<a href="##">用户修改</a>
									</li>
								</ul>
							</li>
							<li class="e_li">
								<a href="##">角色管理</a>
								<ul class="thr">
									<li>
										<a href="##">创建角色<img class="more1" src="images/more1.png" />
										</a>
										<div class="thr_nr">
											<ul>
												<li>
													<a href=''>超级管理员</a>
												</li>
												<li>
													<a href=''>图书采购员</a>
												</li>
												<li>
													<a href=''>读者管理员</a>
												</li>
												<li>
													<a href=''>借阅管理员</a>
												</li>
											</ul>
										</div>
									</li>
									<li>
										<a href="##">注销角色<img class="more1" src="images/more1.png" />
										</a>
										<div class="thr_nr">
											<ul>
												<li>
													<a href=''>松绑用户</a>
												</li>
												<li>
													<a href=''>角色删除</a>
												</li>
											</ul>
										</div>
									</li>
								</ul>
							</li>
						</ul>
					</li>
					<div class="clear"></div>
				</ul>

			</div>
			<div class="m-right">
				<div class="right-nav">
					<ul>
						<li>
							<img src="images/home.png">
						</li>
						<li style="margin-left: 25px;">
							您当前的位置：
						</li>
						<li>
							<span>读者管理</span>
						</li>
						<li>
							>
						</li>
						<li>
							<span>读者添加</span>
						</li>
					</ul>
				</div>
				<div class="main">
					<iframe  frameborder="0" width="1200" height="480" name="openhere"></iframe>
				</div>
			</div>
		</div>
		<div class="bottom"></div>
		<div id="footer"></div>
		<script>navList(12);</script>
	</body>
</html>
