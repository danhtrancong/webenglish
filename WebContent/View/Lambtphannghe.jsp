<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Câu hỏi bài tập theo mã</title>
	<link href="Template/Frontend/css/bootstrap.css" rel="stylesheet">
    <link href="Template/Frontend/css/bootstrap-responsive.css" rel="stylesheet">
    <link href="Template/Frontend/css/style.css" rel="stylesheet"> 
    <!-- font -->
    <link rel="stylesheet" href="Template/Frontend/font/font-awesome.min.css" />
    
    <script src="Template/Frontend/js/code-jquery.js"></script>
    <script src="Template/Frontend/js/bootstrap.min.js"></script>
    
    	<script type="text/javascript">
			
			
					function Xuatketqua()
					{
						var xhttp;
						var kq = document.myform.radio.value;
						
						var url = "Lambtnghe?kq="+kq+"&num="+<%=request.getAttribute("numberpage")%>+"&listenexerciseid="+<%=request.getAttribute("listenexerciseid")%>;
						
						if (window.XMLHttpRequest) 
						{        
						    xhttp = new XMLHttpRequest(); 
						} 
						
						xhttp.onreadystatechange= function()
						{
							if (xhttp.readyState == 4)
							{
								var data = xhttp.responseText;
								document.getElementById("ketqualambtnghe").innerHTML=data;
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
					<h4>
						Lựa chọn câu trả lời đúng nhất
					</h4>
				</div>
				</div>
			</div>
			
		  	<!-- /. PAGE TITLE-->
		  	<br/>
		  	
			<div class="row">
				<div class="span12">
				<ul class="thumbnails">
					<li class="span12">
						<div class="thumbnail" >
							<form name="myform" id="ketqualambtnghe">
							<c:forEach items="${danhsachcauhoibtnghe}" var ="list">
								<p>
									Câu ${list.num}. ${list.question}
									
								</p>
								<p>
									<img src= "Filebtphannghe/${list.imagename}" alt="" style="width:250px;height:150px;"/>
									
								</p>
								<p>
									
									<audio controls>
										<source src="Filebtphannghe/${list.audiogg}" type="audio/ogg">
										<source src="Filebtphannghe/${list.audiomp3}" type="audio/mpeg">
									</audio>
									
								</p>
								<p>
						  			<input type="radio" name="radio" value="A"/>
						  			${list.option1}
						  		</p>
						  		<p>
						  			<input type="radio" name="radio" value="B"/>
						  			${list.option2}
						  		</p>
						  		<p>
						  			<input type="radio" name="radio" value="C"/>
						  			${list.option3}
						  		</p>
						  		<p>
						  			<input type="radio" name="radio" value="D"/>
						  			${list.option4}
						  		</p>
					  		</c:forEach>
					  		</form>
					   </div>
					</li>
				  </ul>
				</div>			
			</div>
			
			<!--Pagination-->
			<div class="row">
				<div class="span12">
					
					<div>
						
							<c:if test="${numberpage == 1}">
							   <a href = "#" class="btn btn-info disabled">Prev</a>
							   <input type="button" value="Đáp án" class="btn btn-info" onclick="Xuatketqua()"/>
							   <a href = "Lambtnghe?pageid=${numberpage}&listenexerciseid=<%=request.getAttribute("listenexerciseid")%>" class="btn btn-info">Làm lại</a>
							   <a href = "Lambtnghe?pageid=${numberpage+1}&listenexerciseid=<%=request.getAttribute("listenexerciseid")%>" class="btn btn-info">Next</a>
							   
						   </c:if>
						   <c:if test="${numberpage == maxpageid}">
							   <a href = "Lambtnghe?pageid=${numberpage-1}&listenexerciseid=<%=request.getAttribute("listenexerciseid")%>" class="btn btn-info">Prev</a>
							   <input type="button" value="Đáp án" class="btn btn-info" onclick="Xuatketqua()"/>
							   <a href = "Lambtnghe?pageid=${numberpage}&listenexerciseid=<%=request.getAttribute("listenexerciseid")%>" class="btn btn-info">Làm lại</a>
							   
							   <a href ="#" class="btn btn-info disabled">Next</a>
						   </c:if>
						   
						   <c:if test="${numberpage > 1 && numberpage < maxpageid}">
							   <a href = "Lambtnghe?pageid=${numberpage-1}&listenexerciseid=<%=request.getAttribute("listenexerciseid")%>" class="btn btn-info">Prev</a>
							   <input type="button" value="Đáp án" class="btn btn-info" onclick="Xuatketqua()"/>
							   <a href = "Lambtnghe?pageid=${numberpage}&listenexerciseid=<%=request.getAttribute("listenexerciseid")%>" class="btn btn-info">Làm lại</a>
							   
							   <a href = "Lambtnghe?pageid=${numberpage+1}&listenexerciseid=<%=request.getAttribute("listenexerciseid")%>" class="btn btn-info">Next</a>
						   </c:if>
					
						   
						
					</div>	
					
			 	</div>
			</div>
			<!--/.Pagination-->
			
				
		</div>
		<br/>
		<br/>
	 <jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>