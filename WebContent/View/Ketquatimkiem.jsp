<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Kết quả tìm kiếm</title>
</head>
<body>

		
		
		<% 
		
			if (request.getAttribute("ketqua")!=null)
			{
		%>
			<div class="row">
				<div class="span6">			
								<div class="media">
									<%=request.getAttribute("ketqua") %>
								</div>
				</div>
			</div>
		<%
		}
		else
		{
		%>
		
		
		<div class="row">
					
					<c:forEach items="${listsearch}" var="list">
						<div class="span6">			
								<div class="media">
									<a href="#" class="pull-left"><img src="Imageupload/${list.grammarimage}" class="media-object" alt='' width="128px" height="128px"/></a>
									<div class="media-body">
										 
										<p>
											${list.grammarname}
										</p>
										<a href="Chitietbaihdnguphapforward?mabaihdnguphap=${list.grammarguidelineid}" class="btn" type="button">Xem bài hướng dẫn</a>
					
					
									</div>
								</div>						
						</div>	
					</c:forEach>
		</div>	
		
		<%
			}
		%>
</body>
</html>