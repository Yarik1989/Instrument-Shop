<%@ tag language="java" pageEncoding="ISO-8859-1" body-content="empty"
	description="Prints single product entity as list item"%>
<%@ attribute name="prod" description="Product entity" required="true"
	type="com.epam.chuikov.entity.Product"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!--content-->

<c:url var="link_prodImageServlet" value="/productImage" />

<li><img src="${link_prodImageServlet}?prodId=${prod.id}" />
	<h3>${prod.name}</h3>
	<p>
		<span class="price">Price : ${prod.price / 100}$</span>
	</p>

	<p>Category : ${prod.category.name}</p>
	<p>Manufacturer : ${prod.manufacturer.name}</p>
	<p>Description : ${prod.description}</p> <!-- <button title="add to cart"></button> -->
	<label > <!-- <input	type="submit" value="Submit"> -->

		<input id="add to cart" type="submit" name="add to cart"
		value="add to cart" class="btn btn-success">
</label></li>
