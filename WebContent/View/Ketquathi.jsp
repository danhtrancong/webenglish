<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Kết quả thi</title>
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
			<br/>
			
		
		  <!-- /. PAGE TITLE-->
		  
			<div class="row">	
						<div class="span9">	
							<h4>
								Đáp án đúng
							</h4>	
						</div>
						<div class="span3">	
							<h4>
								Đáp án người dùng
							</h4>	
						</div>
						<div class="span9">			
								<div class="thumbnail">
									<div class="reading_description" style="overflow: auto; height: 400px" >
										<c:forEach items= "${listcorrectanswer}" var= "listcorrectanswer">
										
											<c:if test="${(listcorrectanswer.imagequestion!='')&&(listcorrectanswer.audiogg!='')&&(listcorrectanswer.audiomp3!='')}">
													<img src= "Imageaudiodethi/${listcorrectanswer.imagequestion}" alt="" style="width:250px;height:150px;"/>
													<br/>
													<br/>
													<p>
														<audio controls>
																<source src="Imageaudiodethi/${listcorrectanswer.audiogg}" type="audio/ogg">
																<source src="Imageaudiodethi/${listcorrectanswer.audiomp3}" type="audio/mpeg">
														</audio>
													</p>
													
													<c:if test="${listcorrectanswer.correctanswer == 'A'}">
													
														 <p style="color:red">Câu ${listcorrectanswer.num}. Đáp án A đúng</p>
													</c:if>
													
													<c:if test="${listcorrectanswer.correctanswer == 'B'}">
														
															
														<p style="color:red">Câu ${listcorrectanswer.num}. Đáp án B đúng</p>
															
															
													</c:if>
													<c:if test="${listcorrectanswer.correctanswer == 'C'}">
														
															
														<p style="color:red">Câu ${listcorrectanswer.num}. Đáp án C đúng</p>	
															
													
													</c:if>
													<c:if test="${listcorrectanswer.correctanswer == 'D'}">
														
															
														<p style="color:red">Câu ${listcorrectanswer.num}. Đáp án D đúng</p>
													
													</c:if>
															
											</c:if>
											
											<c:if test="${(listcorrectanswer.imagequestion=='')&&(listcorrectanswer.audiogg!='')&&(listcorrectanswer.audiomp3!='')}">
													
													<br/>
													<br/>
													<p>
														<audio controls>
																<source src="Imageaudiodethi/${listcorrectanswer.audiogg}" type="audio/ogg">
																<source src="Imageaudiodethi/${listcorrectanswer.audiomp3}" type="audio/mpeg">
														</audio>
													</p>
													
													<c:if test="${listcorrectanswer.correctanswer == 'A'}">
													
														 <p style="color:red">Câu ${listcorrectanswer.num}. Đáp án A đúng</p>
													</c:if>
													
													<c:if test="${listcorrectanswer.correctanswer == 'B'}">
														
															
														<p style="color:red">Câu ${listcorrectanswer.num}. Đáp án B đúng</p>
															
															
													</c:if>
													<c:if test="${listcorrectanswer.correctanswer == 'C'}">
														
															
														<p style="color:red">Câu ${listcorrectanswer.num}. Đáp án C đúng</p>	
															
													
													</c:if>
													<c:if test="${listcorrectanswer.correctanswer == 'D'}">
														
															
														<p style="color:red">Câu ${listcorrectanswer.num}. Đáp án D đúng</p>
													
													</c:if>
															
											</c:if>
											
											<c:if test="${(listcorrectanswer.imagequestion=='')&&(listcorrectanswer.audiogg=='')&&(listcorrectanswer.audiomp3=='')}">
													
													<c:if test="${listcorrectanswer.correctanswer == 'A'}">
														
														<p>${listcorrectanswer.num}. 
																
																<c:set var ="kq" value="${fn:replace(listcorrectanswer.paragraph,kitutrongdatabase,kitutronghtml)}" />
																<c:out value= "${kq}" escapeXml="false"/>		
														</p>
														<p>${listcorrectanswer.question}</p>
														  <img alt="" src="Image/correct.png"> ${listcorrectanswer.option1}
														<br>
														<br>
														  ${listcorrectanswer.option2}
														<br>
														<br>
														  ${listcorrectanswer.option3}
														<br>
														<br>
														  ${listcorrectanswer.option4}
														<br>
														<br>
												
												
										
													</c:if>
										
													<c:if test="${listcorrectanswer.correctanswer == 'B'}">
														
															
																
																<p>${listcorrectanswer.num}. 
																	<c:set var ="kq" value="${fn:replace(listcorrectanswer.paragraph,kitutrongdatabase,kitutronghtml)}" />
																	<c:out value= "${kq}" escapeXml="false"/>
																</p>
																<p>${listcorrectanswer.question}</p>
																  ${listcorrectanswer.option1}
																<br>
																<br>
																  <img alt="" src="Image/correct.png">${listcorrectanswer.option2}
																<br>
																<br>
																  ${listcorrectanswer.option3}
																<br>
																<br>
																  ${listcorrectanswer.option4}
																<br>
																<br>
															
															
													</c:if>
													<c:if test="${listcorrectanswer.correctanswer == 'C'}">
														
															
															
																<p>${listcorrectanswer.num}.
																	<c:set var ="kq" value="${fn:replace(listcorrectanswer.paragraph,kitutrongdatabase,kitutronghtml)}" />
																	<c:out value= "${kq}" escapeXml="false"/>
																</p>
																<p>${listcorrectanswer.question}</p>
																  ${listcorrectanswer.option1}
																<br>
																<br>
																  ${listcorrectanswer.option2}
																<br>
																<br>
																  <img alt="" src="Image/correct.png">${listcorrectanswer.option3}
																<br>
																<br>
																  ${listcorrectanswer.option4}
																<br>
																<br>
															
															
													
													</c:if>
													<c:if test="${listcorrectanswer.correctanswer == 'D'}">
														
													
															
															
																<p>${listcorrectanswer.num}.
																	<c:set var ="kq" value="${fn:replace(listcorrectanswer.paragraph,kitutrongdatabase,kitutronghtml)}" />
																	<c:out value= "${kq}" escapeXml="false"/>
																</p>
																<p>${listcorrectanswer.question}</p>
																  ${listcorrectanswer.option1}
																<br>
																<br>
																  ${listcorrectanswer.option2}
																<br>
																<br>
																  ${listcorrectanswer.option3}
																<br>
																<br>
																  <img alt="" src="Image/correct.png">${listcorrectanswer.option4}
																<br>
																<br>
															
															
													
													</c:if>
															
											</c:if>
										
								
									   </c:forEach>
									
									</div>
								</div>						
						</div>	
						
						
						<div class="span3">			
								<div class="thumbnail">
									<div class="reading_description" style="overflow: auto; height: 400px" >
											<c:forEach items="${listansweruser}" var="list">
													<div class="span1">
														${list.num}. 
													</div>
													
																
													${list.answer}
													
													<br/>
											</c:forEach>
									</div>
								</div>
								<br/>
								<input type="button" class="btn btn-primary" value="Xem kết quả thi" data-toggle="modal" data-target="#myModal"/>
								<a href="Hienthidsdethi?pageid=1" role="button" class="btn btn-primary" >Làm lại</a>
								
								
						</div>
						
						
									
			</div>
			
			
			
			
		</div>
	 
	 <div>
	 	<jsp:include page="Footer.jsp"></jsp:include>
	 </div>
	 
	   <!-- start Modal -->
		  <div class="modal fade" id="myModal" role="dialog">
		    <div class="modal-dialog">
		    
		      <!-- Modal content-->
		      <div class="modal-content">
		        <div class="modal-header">
		          <button type="button" class="close" data-dismiss="modal">&times;</button>
		          <h4 class="modal-title">Kết quả thi của người dùng</h4>
		        </div>
		        <div class="modal-body">
	         			
	         		<c:forEach items="${ketquathi}" var = "list">
						<div class="media">
							
							<div class="media-body">
								<h4>
									Số câu đúng: ${list.correctanswernum}
									<br/>
										- Số câu đúng phần nghe: ${list.correctanswerlisten}
									<br/>
										- Số câu đúng phần đọc: ${list.correctanswerread}
								</h4> 					
							</div>
						</div>
					
			          	<div class="media">
							
							<div class="media-body">
								<h4>
									Số câu sai: ${list.incorrectanswernum}
								</h4> 					
							</div>
						</div>
						
					
					</c:forEach>
		        </div>
		        <div class="modal-footer">
		          <button type="button" class="btn btn-default" data-dismiss="modal">Thoát</button>
		        </div>
		      </div>
		      
		    </div>
		  </div>
		  <!-- end modal -->
	 
	 
	  	 
</body>
</html>