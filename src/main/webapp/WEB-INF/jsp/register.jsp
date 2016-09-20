<!--
Author: W3layouts
Author URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
-->
<%@ taglib prefix="cap" uri="/WEB-INF/taglib/captchaTag.tld"%>



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
<script type="application/x-javascript"></script>
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
<!-- //animation-effect -->
<link href='//fonts.googleapis.com/css?family=Cabin:400,500,600,700'
	rel='stylesheet' type='text/css'>
<link href='//fonts.googleapis.com/css?family=Lato:400,100,300,700,900'
	rel='stylesheet' type='text/css'>
</head>

<body>
	<%@include file="/WEB-INF/jsp/parts/header.jsp"%>
	<!-- 	<!--banner-->
	<div class="banner-top">
		<div class="container">
			<h2 class="animated wow fadeInLeft" data-wow-delay=".5s">Register</h2>
			<h3 class="animated wow fadeInRight" data-wow-delay=".5s">
				<a href="main">Home</a><label>/</label>Register
			</h3>
			<div class="clearfix"></div>
		</div>
	</div>

	<div class="login">
		<div class="container">
			<form class="animated slideInUp registerForm" action="register"
				role="form" id="registerForm" method="POST"
				enctype="multipart/form-data">
				<div class="col-md-6 login-do1 animated wow fadeInLeft"
					data-wow-delay=".5s">
					<div class="login-mail">
						<input id="firstName" name="firstName" type="text"
							placeholder="First name" class="required"
							value="${bean.firstName}"> <i
							class="glyphicon glyphicon-lock"></i>
					</div>
					<span class="error-message">${errors.firstName}</span>
					<div class="login-mail">
						<input id="lastName" name="lastName" type="text"
							placeholder="Last name" class="required" value="${bean.lastName}">
						<i class="glyphicon glyphicon-lock"></i>
					</div>
					<span class="error-message">${errors.lastName}</span>

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
					<span class="error-message">${errors.repeatPassword}</span>

					<div class="login-mail">
						<input id="repeatPassword" name="repeatPassword" type="password"
							placeholder="Repeated password" class="required"
							value="${bean.repeatPassword}"> <i
							class="glyphicon glyphicon-lock"></i>
					</div>
					<span class="error-message">${errors.repeatPassword}</span> <a
						class="news-letter" href="#"> <label class="checkbox1">
							<input type="checkbox" name="subscribe" value="sub"
							${bean.subscribe == 'true' ? 'checked' : ''}> <i> </i>Receive
							letters?
					</label>
					</a>

				</div>
				<div class="col-md-6 login-do animated wow fadeInRight"
					data-wow-delay=".5s">
					<label class="hvr-sweep-to-top login-sub"> <!-- <input	type="submit" value="Submit"> -->
						<input id="submit" type="submit" name="submit" value="Register"
						class="btn btn-success">
					</label>
					<p>Robot protection</p>
					<cap:captcha strategy="${strategy}" />
					<span class="error-message">${errors.captcha}</span>

				</div>
				<div class="col-md-6 login-do animated wow fadeInRight"
					data-wow-delay=".5s">
					<label class="hvr-sweep-to-top login-sub"> <input id="file"
						type="file" name="file" value="Upload photo">
					</label>

				</div>
			</form>
		</div>
		<div class="clearfix"></div>

	</div>



	<%@include file="/WEB-INF/jsp/parts/footer.jsp"%>

</body>
</html>