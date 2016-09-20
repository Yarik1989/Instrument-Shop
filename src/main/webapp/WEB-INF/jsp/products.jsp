<!--
Author: W3layouts
Author URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
-->
<%@ page language="java" contentType="text/html; charset=cp1251"
	pageEncoding="cp1251"%>
<%@ taglib prefix="mytags" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="mylib" uri="/WEB-INF/taglib/mylib.tld"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
			<h2 class="animated wow fadeInLeft" data-wow-delay=".5s">Products</h2>
			<h3 class="animated wow fadeInRight" data-wow-delay=".5s">
				<a href="index.html">Home</a><label>/</label>Products<label>/</label>Women's
			</h3>
			<div class="clearfix"></div>
		</div>
	</div> -->
	<div class="banner-top">
		<div class="container">
			<h2 class="animated wow fadeInLeft" data-wow-delay=".5s">Products</h2>
			<div class="clearfix"></div>
		</div>
	</div>

	<div class=" container">
		<div class="row">
			<div class="col-lg-3 product-bottom">

				<div id="maincontent">

					<c:url var="link_products" value="/products" />

					<form method="get" action="${link_products}">
						<!--prodConfig  -->
						<div id="prodConfig">

							<div class="price animated wow fadeInLeft animated"
								data-wow-delay=".5s">
								<!-- 	<p>Elements on page</p> -->
								<h3>Elements on page</h3>
								<select id="elementsOnPage" name="elementsOnPage">
									<c:forEach items="${elementsOnPageModes}" var="em">
										<c:choose>
											<c:when test="${em eq filterBean.elementsOnPage}">
												<option value="${em}" selected="selected">${em.count}</option>
											</c:when>
											<c:otherwise>
												<option value="${em}">${em.count}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</select>
							</div>
							<div class="price animated wow fadeInLeft animated"
								data-wow-delay=".5s">
								<!-- <p class="label">Sort by</p> -->
								<h3>Sort by</h3>
								<select id="sortBy" name="sortBy">
									<c:forEach items="${sortModes}" var="sm">
										<c:choose>
											<c:when test="${sm eq filterBean.sortMode}">
												<option value="${sm}" selected="selected">${sm.name}</option>
											</c:when>
											<c:otherwise>
												<option value="${sm}">${sm.name}</option>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</select>
							</div>
							<!-- <div style="clear: both;"></div> -->
						</div>

						<div id="filter">
							<div class="price animated wow fadeInLeft animated"
								data-wow-delay=".5s">
								<h3>Price Range</h3>

								<div class="price animated wow fadeInLeft animated f"
									id="priceFilter" data-wow-delay=".5s">
									<!-- <div class="title">
										<span class="text">Price</span> <span class="btn"
											onclick="resetPrice();">(reset)</span>
									</div> -->
									<input class="price" type="text" maxlength="10" name="priceLow"
										value="${filterBean.priceLow}" /> low <input class="price "
										type="text" maxlength="10" name="priceHigh"
										value="${filterBean.priceHigh}" /> high
								</div>
							</div>
							<!--categories-->
							<div class="categories animated wow fadeInUp animated"
								data-wow-delay=".5s">
								<h3>Categories</h3>
								<div class="categories animated wow fadeInUp animated f"
									id="categoriesFilter">
									<!-- <div class="title">
										<span class="text">Categories</span> <span class="btn"
											onclick="resetCategories();">(reset)</span>
									</div> -->
									<ul>
										<c:forEach items="${categories}" var="cat">
											<c:choose>
												<c:when
													test="${not empty filterBean.categoryIds && mylib:contains(filterBean.categoryIds,cat.id)}">
													<li><input type="checkbox" checked="checked"
														name="cat" value="${cat.id}" />${cat.name}</li>
												</c:when>
												<c:otherwise>
													<li><input type="checkbox" name="cat"
														value="${cat.id}" />${cat.name}</li>
												</c:otherwise>
											</c:choose>
										</c:forEach>
									</ul>
								</div>
							</div>
							<div class="categories animated wow fadeInUp animated f"
								id="manufacturersFilter" data-wow-delay=".5s">
								<h3>Manufacturers</h3>


								<!-- <div class="title">
									<span class="text">Manufacturers</span> <span class="btn"
										onclick="resetManufacturers();">(reset)</span>
								</div> -->
								<ul>
									<c:forEach items="${manufacturers}" var="man">
										<c:choose>
											<c:when
												test="${not empty filterBean.manufacturerIds && mylib:contains(filterBean.manufacturerIds,man.id)}">
												<li><input type="checkbox" checked="checked"
													name="manuf" value="${man.id}" />${man.name}</li>
											</c:when>
											<c:otherwise>
												<li><input type="checkbox" name="manuf"
													value="${man.id}" />${man.name}</li>
											</c:otherwise>
										</c:choose>
									</c:forEach>
								</ul>
							</div>

							<div class="categories animated wow fadeInUp animated f">
								<input type="submit" value="Filter" class="btn btn-success"/>
							</div>

						</div>
					</form>


				</div>
			</div>
			<div class="col-lg-9 product-bottom">
				<div id="leftside" class="productside">
					<mytags:pagination filterBean="${filterBean}" />
					<div class="products_holder">
						<c:choose>
							<c:when test="${not empty products}">
								<ul>
									<c:forEach var="p" items="${products}">
										<mytags:productPrinter prod="${p}" />
									</c:forEach>
								</ul>
							</c:when>
							<c:otherwise>
								<p>nothing found</p>
							</c:otherwise>
						</c:choose>
					</div>
					<div style="clear: both;"></div>
					<mytags:pagination filterBean="${filterBean}" />
				</div>
			</div>
		</div>

	</div>
	<%@include file="/WEB-INF/jsp/parts/footer.jsp"%>


</body>
</html>