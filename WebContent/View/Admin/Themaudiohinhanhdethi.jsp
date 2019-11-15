<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Thêm audio hinh ảnh đề thi</title>
		<link rel="stylesheet" href="Template/Backend/assets/css/bootstrap.min.css" />
		
		<!-- font -->
		<link rel="stylesheet" href="Template/Backend/font/font-awesome.min.css" />
		<link rel="stylesheet" href="Template/Backend/assets/font-awesome/4.5.0/css/font-awesome.min.css" />

		
		<link rel="stylesheet" href="Template/Backend/assets/css/ace.min.css" />

		<link rel="stylesheet" href="Template/Backend/assets/css/ace-rtl.min.css" />
		<script src="js/jquery-3.1.1.min.js"></script>
		<script>
		    $(document)
		            .ready(
		                    function() {
		                        //add more file components if Add is clicked
		                        $('#addFile')
		                                .click(
		                                        function() {
		                                            var fileIndex = $('#fileTable tr')
		                                                    .children().length - 1;
		                                            $('#fileTable')
		                                                    .append(
		                                                            '<tr><td>'
		                                                                    + '   <input type="file" name="files['+ fileIndex +']" />'
		                                                                    + '</td></tr>');
		                                        });
		 
		                    });
		</script>
		
</head>
<body>
		<body class="login-layout">
		
		<div class="main-container">
			<div class="main-content">
				<div class="row">
					<div class="col-sm-10 col-sm-offset-1">
						<div class="login-container">
							<div class="center">
								<h1>
									<i class="ace-icon fa fa-leaf green"></i>
									<span class="red">JSP</span>
									<span class="white" id="id-text2">SERVLET</span>
								</h1>
								<h4 class="blue" id="id-company-text">&copy; Myclass.vn</h4>
							</div>

							<div class="space-6"></div>

							<div class="position-relative">
								<div id="login-box" class="login-box visible widget-box no-border">
									<div class="widget-body">
										<div class="widget-main">
											<h4 class="header blue lighter bigger">
												<i class="ace-icon fa fa-coffee green"></i>
												Thêm audio hình ảnh đề thi
											</h4>

											<div class="space-6"></div>

											<form action="Themaudiohinhanhdethi" method="POST" enctype="multipart/form-data">
												<fieldset>
													<label class="block clearfix">
														<%=request.getAttribute("msgthemaudiohinhanhdethi")!=null?request.getAttribute("msgthemaudiohinhanhdethi"):" "%>
													</label>
													
													<table id="fileTable">
												 			 <tr>
										                   		 <td><input name="files[0]" type="file" /></td>
										               		 </tr>
										                	<tr>
										                   		 <td><input name="files[1]" type="file" /></td>
										                	</tr>		  	
												 	 </table>
													
													 

													
													<div class="space"></div>

													<div class="clearfix">
														
												
														<input type="submit" value="Thêm audio và hình ảnh" class="btn btn-sm btn-primary">
													 	<input id="addFile" type="button" value="Thêm choose file" class="btn btn-sm btn-primary"/>
														
													</div>

													<div class="space-4"></div>
												</fieldset>
											</form>

											<div class="social-or-login center">
												<span class="bigger-110"></span>
											</div>

											<div class="space-6"></div>

										
										</div><!-- /.widget-main -->

									</div><!-- /.widget-body -->
								</div><!-- /.login-box -->
							</div><!-- /.position-relative -->

							<div class="navbar-fixed-top align-right">
								<br />
								&nbsp;
								<a id="btn-login-dark" href="#">Dark</a>
								&nbsp;
								<span class="blue">/</span>
								&nbsp;
								<a id="btn-login-blur" href="#">Blur</a>
								&nbsp;
								<span class="blue">/</span>
								&nbsp;
								<a id="btn-login-light" href="#">Light</a>
								&nbsp; &nbsp; &nbsp;
							</div>
						</div>
					</div><!-- /.col -->
				</div><!-- /.row -->
			</div><!-- /.main-content -->
		</div><!-- /.main-container -->
		
		
		
		<script src="Template/Backend/assets/js/jquery-2.1.4.min.js"></script>

		
		<script type="text/javascript">
			if('ontouchstart' in document.documentElement) document.write("<script src='Template/Backend/assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
		</script>

		<!-- inline scripts related to this page -->
		<script type="text/javascript">
			jQuery(function($) {
			 $(document).on('click', '.toolbar a[data-target]', function(e) {
				e.preventDefault();
				var target = $(this).data('target');
				$('.widget-box.visible').removeClass('visible');//hide others
				$(target).addClass('visible');//show target
			 });
			});
			
			
			
			//you don't need this, just used for changing background
			jQuery(function($) {
			 $('#btn-login-dark').on('click', function(e) {
				$('body').attr('class', 'login-layout');
				$('#id-text2').attr('class', 'white');
				$('#id-company-text').attr('class', 'blue');
				
				e.preventDefault();
			 });
			 $('#btn-login-light').on('click', function(e) {
				$('body').attr('class', 'login-layout light-login');
				$('#id-text2').attr('class', 'grey');
				$('#id-company-text').attr('class', 'blue');
				
				e.preventDefault();
			 });
			 $('#btn-login-blur').on('click', function(e) {
				$('body').attr('class', 'login-layout blur-login');
				$('#id-text2').attr('class', 'white');
				$('#id-company-text').attr('class', 'light-blue');
				
				e.preventDefault();
			 });
			 
			});
		</script>
</body>
</body>
</html>