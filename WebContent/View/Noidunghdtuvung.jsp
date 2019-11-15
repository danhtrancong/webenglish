<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Danh sách đề thi toeic</title>
	<link href="Template/Frontend/css/bootstrap.css" rel="stylesheet">
    <link href="Template/Frontend/css/bootstrap-responsive.css" rel="stylesheet">
    <link href="Template/Frontend/css/style.css" rel="stylesheet"> 
    
   
   	<!-- font -->
	<link rel="stylesheet" href="Template/Frontend/font/font-awesome.min.css" />
    
    <script src="Template/Frontend/js/jquery-1.js"></script>
    <script src="Template/Frontend/js/bootstrap.min.js"></script>
    
   
    
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
								Nội dung chủ đề từ vựng
							</h3>
							
							
						</div>
						
				</div>
				
			</div>
		
		  <!-- /. PAGE TITLE-->
		  	
		  	<%
		  		if (request.getAttribute("msgndchudetuvung")!=null)
		  		{	  		
		  	%>
		  		<div class="row">	
					
						<div class="span6">			
								<div class="media">
									 <p style="color:red">${msgndchudetuvung}</p>
								</div>						
						</div>	
							
				</div>
		  	<%
		  		}
		  		else
		  		{
		  	%>
			<div class="row">	
					<c:forEach items="${noidungtuvung}" var="list">
						<div class="span6">			
								<div class="media">
									 <a href="#" class="pull-left"><img src="Imageaudiohdtuvung/${list.image}" class="media-object" alt='' width="128px" height="128px"/></a>
									<div class="media-body">
										 
										<p>
											Từ: ${list.vocabularycontentname}
										</p>
										<p>
											Phiên âm: ${list.transcribe}
										</p>
										<p>
											Nghĩa và từ loại: ${list.mean}
										</p>
										
										<p>
												<audio controls>
														<source src="Imageaudiohdtuvung/${list.audiogg}" type="audio/ogg">
														<source src="Imageaudiohdtuvung/${list.audiomp3}" type="audio/mpeg">
												</audio>
										</p>
										
									</div>
								</div>						
						</div>	
					</c:forEach>				
			</div>
			<%
		  		}
			%>
			
			
			
			
		</div>
	 
	 <div>
	 	<jsp:include page="Footer.jsp"></jsp:include>
	 </div>
	 
</body>
</html>