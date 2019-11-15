<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<c:forEach items="${listcommentgrammar}" var="list">
		<h4 style="background-color:yellow" class="input-large">${list.name}</h4>
													 							
		<!--textarea  class="input-xxlarge"  rows="4" disabled>
			${list.cmtgrammarcontent}
		</textarea-->
		<p style="background-color:powderblue; height:100px" class="input-xxlarge">
									${list.cmtgrammarcontent}
		</p>
	</c:forEach>
</body>
</html>