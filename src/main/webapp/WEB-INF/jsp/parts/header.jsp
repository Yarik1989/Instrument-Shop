<%@ taglib prefix="log" uri="/WEB-INF/taglib/captchaTag.tld"%>
<!-- header -->
<div class="header">
	<div class="header-grid">
		<div class="container">
			<div class="header-left animated wow fadeInLeft" data-wow-delay=".5s">
				<ul>
					<li><i class="glyphicon glyphicon-headphones"></i><a href="#">24x7
							live support</a></li>
					<li><i class="glyphicon glyphicon-envelope"></i><a
						href="mailto:info@example.com">@example.com</a></li>
					<li><i class="glyphicon glyphicon-earphone"></i>+1234 567 892</li>

				</ul>
			</div>
			<div class="header-right animated wow fadeInRight"
				data-wow-delay=".5s">
						
							<log:login> session="${session}"</log:login>
					<!-- 	<li><i class="glyphicon glyphicon-log-in"></i><a href="login">Login</a></li>
						<li><i class="glyphicon glyphicon-book"></i><a
							href="register">Register</a></li>
		 -->		
				<div class="header-right2">
					<div class="cart box_1">
						<a href="checkout.html">
							<h3>
								<div class="total">
									<span class="simpleCart_total"></span> (<span
										id="simpleCart_quantity" class="simpleCart_quantity"></span>
									items)
								</div>
								<img src="images/cart.png" alt="" />
							</h3>
						</a>
						<p>
							<a href="javascript:;" class="simpleCart_empty">Empty Cart</a>
						</p>
						<div class="clearfix"></div>
					</div>
				</div>
				<div class="clearfix"></div>
			</div>
			<div class="clearfix"></div>
		</div>
	</div>
	<div class="container">
		<div class="logo-nav">

			<nav class="navbar navbar-default">
				<!-- Brand and toggle get grouped for better mobile display -->
				<div class="navbar-header nav_2">
					<button type="button"
						class="navbar-toggle collapsed navbar-toggle1"
						data-toggle="collapse" data-target="#bs-megadropdown-tabs">
						<span class="sr-only">Toggle navigation</span> <span
							class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<div class="navbar-brand logo-nav-left ">
						<h1 class="animated wow pulse" data-wow-delay=".5s">
							<a href="index.html">Classic<span>Style</span></a>
						</h1>
					</div>

				</div>
				<div class="collapse navbar-collapse" id="bs-megadropdown-tabs">
					<ul class="nav navbar-nav">
						<li class="active"><a href="main" class="act">Home</a></li>
						<!-- Mega Menu -->

						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown">Instrument<b class="caret"></b></a>
							<ul class="dropdown-menu multi multi1">
								<div class="row">
									<div class="col-sm-4">
										<ul class="multi-column-dropdown">
											<h6>Electro instrument</h6>
											<li><a href="products">Perforator</a></li>
											<li><a href="products">Grinding machines</a></li>
										</ul>
							</ul></li>

					</ul>
				</div>
			</nav>
		</div>

	</div>
</div>
<!-- //header -->
