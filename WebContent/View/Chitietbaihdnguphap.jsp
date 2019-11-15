<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hướng dẫn học ngữ pháp</title>
 	<link href="Template/Frontend/css/bootstrap.css" rel="stylesheet">
    <link href="Template/Frontend/css/bootstrap-responsive.css" rel="stylesheet">
    <link href="Template/Frontend/css/style.css" rel="stylesheet"> 
    
   
   	<!-- font -->
	<link rel="stylesheet" href="Template/Frontend/font/font-awesome.min.css" />
    
    <script src="Template/Frontend/js/jquery-1.js"></script>
    <script src="Template/Frontend/js/bootstrap.min.js"></script>
    
    <link rel="stylesheet" type="text/css" href="Template/Pagingjquery/css/style.css" media="screen"/>
    
    <script type="text/javascript">
    
    		function Binhluan()
    		{
    			var xhttp;
				var cmtgrammarcontent = document.formbinhluan.cmtgrammarcontent.value;
				var memberid = "<%=session.getAttribute("sessionmemberid")%>";
				var grammarguidelineid = "<%=request.getAttribute("mabaihdnguphap")%>"
				
				var url = "Cmtgrammarcontroller?cmtgrammarcontent="+cmtgrammarcontent+"&memberid="+memberid+"&grammarguidelineid="+grammarguidelineid;
				
				if (window.XMLHttpRequest) 
				{          
				         xhttp = new XMLHttpRequest();
				} 
				else
				{        
					xhttp = new ActiveXObject("Microsoft.XMLHTTP");
				}
				
				xhttp.onreadystatechange = function()
				{
					if(xhttp.readyState == 4)
					{
						var data = xhttp.responseText;
						document.getElementById("listcomment").innerHTML = data;
					}			
					
				}

				
				xhttp.open("POST",url,true);
				xhttp.send();
    		}
    
    </script>
    
</head>
<body>
	<!--HEADER ROW-->
	  	<jsp:include page="Header.jsp"></jsp:include>
	 <!-- /HEADER ROW -->
	 
		<div class="container">
			  <!--PAGE TITLE-->
		
			<div class="row">
				<div class="span12">
					<div class="page-header">
						 <h3>
							Bài hướng dẫn học ngữ pháp
						</h3>
					</div>
				</div>
			</div>
		  <!-- /. PAGE TITLE-->
		  	
		  	<div class="row">
				<div class="span9">
						<!--Blog Post-->
						<div class="blog-post">
							
							<div class="postmetadata">
								<ul>
										
										<li>
											 <i class="icon-calendar"></i><%= new java.util.Date() %>
										</li>
			
										<li>
											<i class="icon-comment"></i> <a href="#">${countrow} bình luận</a>
										</li>
										
								</ul>
							</div>
							
							<p>				
								<c:set var ="kq" value="${fn:replace(grammarguidelinecontent,kitutrongdatabase,kitutronghtml)}" />
								<c:out value="${kq}" escapeXml="false"/>
								
							</p>	
			
											
						</div>
						<!--===============-->
						
				  		<%
				  			if (session.getAttribute("sessionuser")==null)
				  			{
				  		%>
				  			<div class="blog-post">
							<div>		
								<h3>Bình luận</h3>
											 							
								<!--textarea  class="input-xxlarge" rows="8" name="comment" disabled style="color:red"-->
								<textarea  class="input-xxlarge" rows="4" name="comment" disabled style="color:red">
										Đăng nhập để bình luận
								</textarea>
								
							</div>
							
							<div>
								
								<button type="button" class="btn btn-primary" disabled>Đăng bình luận</button>
							</div>
							</div>
						<%
				  			}
				  			else
				  			{		
						%>
							<form name="formbinhluan">
								<div>		
									<h3>Bình luận</h3>
												 							
									<textarea  class="input-xxlarge"  rows="4" name="cmtgrammarcontent">
										
									</textarea>
									
								</div>
								
								<div>
									<!--textarea  class="input-xxlarge" rows="8" name="comment" disabled style="color:red"-->
									<button type="button" class="btn btn-primary" onclick="Binhluan()">Đăng bình luận</button>
								</div>
							</form>
						<%
				  			}
						%>
						<div class="reading_description" style="overflow: auto; height: 300px; width:550px;" >
							<div id="listcomment">				
									<c:forEach items="${listcommentgrammar}" var="list"> 
											<h4 style="background-color:yellow" class="input-large">${list.name}</h4>
											
													
											<!--textarea  class="input-xxlarge"  rows="4" disabled>
												${list.cmtgrammarcontent}
											</textarea-->
							
											<p style="background-color:powderblue; height:100px" class="input-xxlarge">
												${list.cmtgrammarcontent}
											</p>
									</c:forEach>
										
							</div>
						</div>		
		  		</div>  		
		  		<div class="span3">
					<div class="side-bar">
		
						<h3>Danh mục</h3>
						<ul class="nav nav-list">
							<li><a href="#">Hướng dẫn học ngữ pháp</a></li>
							<li><a href="#">Hướng dẫn học từ vựng</a></li>
							<li><a href="#">Làm bài tập phần đọc</a></li>
							<li><a href="#">Làm bài tập phần nghe</a></li>
							<li><a href="#">Thi thử toeic</a></li>
						</ul>
		
					</div>
				</div>
				
		  	</div>
		  	
		  	
		</div>
			
	 
	 <div>
	 	<jsp:include page="Footer.jsp"></jsp:include>
	 </div>
	 
</body>
</html>