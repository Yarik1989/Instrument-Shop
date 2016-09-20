<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/taglib/captchaTag.tld" prefix="ex"%>
<%@ attribute name="session" required="false" type="java.lang.String"%>
<div>
	<c:choose>
		<c:when test="${not empty bean}">

			<form class="form-inline" role="form" method="GET">
				<img src=photoLoader class="img-circle" alt="Cinque Terre"
					width="50" height="38">
				<div class="form-group">
					<ul>
						<li>${bean.getEmail()}</li>
					</ul>
				</div>
				<div class="form-group">
					<ul>
						<li><i class="glyphicon glyphicon-log-in"></i><a
							href="logout">Logout</a></li>
					</ul>
				</div>
			</form>
		</c:when>

		<c:otherwise>
			<form class="form-inline" action="login" role="form"
				id="registerForm" method="POST">
				<div class="form-group">

					<input id="email" name="email" type="email"
						placeholder="Email Address" class="form-control"
						value="${bean.email}">
				</div>
				<div class="form-group">
					<input id="password" name="password" type="password"
						placeholder="Password" class="form-control"
						value="${bean.password}">
				</div>
				<button type="submit" class="btn btn-default">Login</button>
				<button type="submit" class="btn btn-default">
					<a href="register">Register</a>
				</button>

			</form>
			<c:if test="${not empty errors}">
				<div class="row">
					<span class="label label-warning">${errors.email}</span> <span
						class="label label-warning">${errors.password}</span>
				</div>
			</c:if>

		</c:otherwise>
	</c:choose>
</div>