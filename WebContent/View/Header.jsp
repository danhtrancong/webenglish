<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<% 
		if (session.getAttribute("sessionuser")==null)
		{
	%>
	<div id="header-row">
	    <div class="container">
	      <div class="row">
	              <!--LOGO-->
	              <div class="span3"><a class="brand" href="Homeforward"><img src="Template/Frontend/img/logo.png"/></a></div>
	              <!-- /LOGO -->
	
	            <!-- MAIN NAVIGATION -->  
	              <div class="span9">
	                <div class="navbar  pull-right">
	                  <div class="navbar-inner">
	                    <a data-target=".navbar-responsive-collapse" data-toggle="collapse" class="btn btn-navbar"><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></a>
	                    <div class="nav-collapse collapse navbar-responsive-collapse">
	                    <ul class="nav">
	                                             
	                   
	                 		<li><a href="Logincontroller">Đăng nhập</a></li>
	                 		
	                        <li><a href="Registercontroller">Đăng Ký</a></li>
	                    </ul>
	                  </div>
	
	                  </div>
	                </div>
	              </div>
	            <!-- MAIN NAVIGATION -->  
	      </div>
	    </div>
	 </div>
	 <%
		}
		else
		{
	 %>
	 <div id="header-row">
	    <div class="container">
	      <div class="row">
	              <!--LOGO-->
	              <div class="span3"><a class="brand" href="Homeforward"><img src="Template/Frontend/img/logo.png"/></a></div>
	              <!-- /LOGO -->
	
	            <!-- MAIN NAVIGATION -->  
	              <div class="span9">
	                <div class="navbar  pull-right">
	                  <div class="navbar-inner">
	                    <a data-target=".navbar-responsive-collapse" data-toggle="collapse" class="btn btn-navbar"><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></a>
	                    <div class="nav-collapse collapse navbar-responsive-collapse">
	                    <ul class="nav">
	                                             
	                       
	                        <li><a>Wellcome: ${sessionuser}</a></li>
	                        
	                        <li><a href="Logoutcontroller">Thoát</a></li>
	                 
	                    </ul>
	                  </div>
	
	                  </div>
	                </div>
	              </div>
	            <!-- MAIN NAVIGATION -->  
	      </div>
	    </div>
	 </div>	
	 <%
		}
	 %>
	 
</body>
</html>