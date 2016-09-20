<!--
Author: W3layouts
Author URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
-->
<!DOCTYPE html>
<html>
<head>
<title>Shop</title>
<!-- for-mobile-apps -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords"
	content="Classic Style Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
<script type="application/x-javascript">
	
	
	
	
	
	 addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false);
		function hideURLbar(){ window.scrollTo(0,1); } 





</script>
<!-- //for-mobile-apps -->
<link href="css/bootstrap.css" rel="stylesheet" type="text/css"
	media="all" />
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
<!-- js -->
<script src="js/jquery.min.js"></script>
<!-- //js -->
<!-- cart -->
<script src="js/simpleCart.min.js"></script>
<!-- cart -->
<!-- for bootstrap working -->
<script type="text/javascript" src="js/bootstrap-3.1.1.min.js"></script>
<!-- //for bootstrap working -->
<!-- animation-effect -->
<link href="css/animate.min.css" rel="stylesheet">
<script src="js/wow.min.js"></script>
<script>
	new WOW().init();
</script>
<script src="js/ValidateLoginForm.js"></script>
<!-- //animation-effect -->
<link href='//fonts.googleapis.com/css?family=Cabin:400,500,600,700'
	rel='stylesheet' type='text/css'>
<link href='//fonts.googleapis.com/css?family=Lato:400,100,300,700,900'
	rel='stylesheet' type='text/css'>
</head>

<body>
	<%@include file="/WEB-INF/jsp/parts/header.jsp"%>

	<!--banner-->
	<!-- 	<div class="banner-top">
		<div class="container">
			<h2 class="animated wow fadeInLeft" data-wow-delay=".5s">Login</h2>
			<h3 class="animated wow fadeInRight" data-wow-delay=".5s">
				<a href="main">Home</a><label>/</label>Login
			</h3>
			<div class="clearfix"></div>
		</div>
	</div> -->
	<!-- contact -->
	<div class="login">
		<div class="container">
			<form id="loginForm" method="POST">
				<div class="col-md-6 login-do1 animated wow fadeInLeft"
					data-wow-delay=".5s">
					<div class="login-mail">
						<input id="email" name="email" type="email"
							placeholder="Email Address" class="required"
							value="${bean.email}"> <i
							class="glyphicon glyphicon-envelope"></i>
					</div>
					<span class="error-message">${errors.email}</span>
					<div class="login-mail">
						<input id="password" name="password" type="password"
							placeholder="Password" class="required" value="${bean.password}">
						<i class="glyphicon glyphicon-lock"></i>
					</div>
					<span class="error-message">${errors.password}</span> <a
						class="news-letter " href="#"> <label class="checkbox1"><input
							type="checkbox" name="checkbox"><i> </i>Forgot Password</label>
					</a>


				</div>
				<div class="col-md-6 login-do animated wow fadeInRight"
					data-wow-delay=".5s">
					<label class="hvr-sweep-to-top login-sub"> <input
						id="submit" type="submit" name="submit" value="Login">
					</label>
					<p>Do not have an account?</p>
					<a href="register" class="hvr-sweep-to-top">Signup</a>
				</div>

			</form>


		</div>
		<div class="clearfix"></div>
	</div>

	<%@include file="/WEB-INF/jsp/parts/footer.jsp"%>
</body>
</html>