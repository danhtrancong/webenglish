<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Làm bài thi toeic</title>
<link href="Template/Frontend/css/bootstrap.css" rel="stylesheet">
<link href="Template/Frontend/css/bootstrap-responsive.css"
	rel="stylesheet">
<link href="Template/Frontend/css/style.css" rel="stylesheet">


<!-- font -->
<link rel="stylesheet" href="Template/Frontend/font/font-awesome.min.css" />

<script src="Template/Frontend/js/jquery-1.js"></script>
<script src="Template/Frontend/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="Jqueryphantrang/css/style.css" media="screen"/>
		<style>
            .demo
            {
                width:580px;
                padding:10px;
                margin:10px auto;
                border: 1px solid #fff;
                background-color:#f7f7f7;
            }        
			.pagedemo{
				border: 1px solid #CCC;
				width:310px;
				margin:2px;
                padding:50px 10px;
                text-align:center;
				background-color:white;	
			}
			.khoangcach{
				padding-top:100px;
			}
        </style>
        
        <script src="Countdown/countdown.js"></script>
        <script type="text/javascript">
        
        		function auto_submit()
        		{
        			document.form.submit();
        		}
        			
        		function auto_submit1()
        		{
        			setTimeout("auto_submit()",10000);
        		}
        
        
        </script>
		
</head>

<body onLoad="auto_submit1();">
	<!--HEADER ROW-->
	<jsp:include page="Header.jsp"></jsp:include>
	<!-- /HEADER ROW -->

	<div class="container">
		<!--PAGE TITLE-->

		<div class="row">
			<div class="span12">
				
					<h3>Làm bài thi toeic hoàn chỉnh</h3>
					
					<script>

						function doneHandler(result) 
						{
							
							
							var year = result.getFullYear();
							var month = result.getMonth() + 1; // bump by 1 for humans
							var day = result.getDate();
							var h = result.getHours();
							var m = result.getMinutes();
							var s = result.getSeconds();
							
							var UTC = result.toString();
							
							var output = UTC + "\n";
							output += "year: " + year + "\n";
							output += "month: " + month + "\n";
							output += "day: " + day + "\n";
							output += "h: " + h + "\n";
							output += "m: " + m + "\n";
							output += "s: " + s + "\n";
							
							
						}				
						var myCountdownTest = new Countdown({
														 	time: 10,
															width	: 300, 
															height	: 50,
															onComplete : doneHandler
															});					
					</script>
					<!-- END COUNTDOWN -->
					
				</div>
			
		</div>
		<br/>
		
		
		<div class="row">
			<div class="span8">
				
				<div id="paginationdemo" class="thumbnail">
					<div class="reading_description" style="overflow: auto; height: 400px" >
							<div id="p1" class=" _current" style="">
								<h1 class="khoangcach">Nhấn page 2 để xem đề thi
								</h1>
							</div>
							
							<c:forEach items="${dscauhoi}" var ="list">
								<c:if test="${(list.imagequestion!='')&&(list.audiogg!='')&&(list.audiomp3!='')}">
									<div id="p${list.num+1}" style="display:none;">
																	
											<img src= "Imageaudiodethi/${list.imagequestion}" alt="" style="width:250px;height:150px;"/>
											<br/>
											<br/>
											<p>
												<audio controls>
														<source src="Imageaudiodethi/${list.audiogg}" type="audio/ogg">
														<source src="Imageaudiodethi/${list.audiomp3}" type="audio/mpeg">
												</audio>
											</p>
											
											<b>Câu ${list.num}. ${list.question}</b>
											<p>${list.option1}</p>
											<p>${list.option2}</p>
											<p>${list.option3}</p>
											<p>${list.option4}</p>							
									</div>
								</c:if>
								
								<c:if test="${(list.imagequestion=='')&&(list.audiogg!='')&&(list.audiomp3!='')}">
									<div id="p${list.num+1}" style="display:none;">
																				
											<br/>
											<br/>
											<p>
												<audio controls>
														<source src="Imageaudiodethi/${list.audiogg}" type="audio/ogg">
														<source src="Imageaudiodethi/${list.audiomp3}" type="audio/mpeg">
												</audio>
											</p>
											<b>Câu ${list.num}. ${list.question}</b>
											<p>${list.option1}</p>
											<p>${list.option2}</p>
											<p>${list.option3}</p>
											<p>${list.option4}</p>							
									</div>
								</c:if>
								
								<c:if test="${(list.imagequestion=='')&&(list.audiogg=='')&&(list.audiomp3=='')}">
									<div id="p${list.num+1}" style="display:none;">
																
											<p>		
												<c:set var ="kq" value="${fn:replace(list.paragraph,kitutrongdatabase,kitutronghtml)}" />
												<c:out value= "${kq}" escapeXml="false"/>		
											</p>
											<b>Câu ${list.num}. ${list.question}</b>
											<p>${list.option1}</p>
											<p>${list.option2}</p>
											<p>${list.option3}</p>
											<p>${list.option4}</p>							
									</div>
								</c:if>
							</c:forEach>
							
							
					</div>
					
				</div>
				<br/>
				<div id="demo5">                   
                </div>
			</div>
			
			<form name="form" action="Lambaithitoeic?examinationid=${examinationid}&memberid=${memberid}" method="POST">
				<div class="span4">
					<div class="thumbnail">
						<div class="reading_description" style="overflow: auto; height: 400px" >
									
								<c:forEach items = "${dscauhoi}" var = "list">		
									&nbsp;
									<div class="span1">
										${list.num}.
									</div>
									
									A. <input type="radio" name="ans[${list.num}]" value="A"> 
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									B. <input type="radio" name="ans[${list.num}]" value="B"> 
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									C. <input type="radio" name="ans[${list.num}]" value="C"> 
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									D. <input type="radio" name="ans[${list.num}]" value="D"> 
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									<br/>
									<br/>
								</c:forEach>		
									
						</div>	
					</div>
					<br/>
					<input type="submit" class="btn btn-primary" value="Nộp bài"/>
					
				</div>
			</form>
			
		</div>

	</div>
	
	<div>
		<jsp:include page="Footer.jsp"></jsp:include>
	</div>
	
		<script type="text/javascript" src="Jqueryphantrang/jquery-1.3.2.js"></script>
		<script src="Jqueryphantrang/jquery.paginate.js" type="text/javascript"></script>
		
		<script type="text/javascript">
		
			$(function() 
			{
				$("#demo5").paginate({
					count 		: 11,
					start 		: 1,
					display     : 5,
					border					: true,
					border_color			: '#fff',
					text_color  			: '#fff',
					background_color    	: 'black',	
					border_hover_color		: '#ccc',
					text_hover_color  		: '#000',
					background_hover_color	: '#fff', 
					images					: false,
					mouse					: 'press',
					onChange     			: function(page){
												$('._current','#paginationdemo').removeClass('_current').hide();
												$('#p'+page).addClass('_current').show();
											  }
				});
			});
		</script>
</body>
</html>