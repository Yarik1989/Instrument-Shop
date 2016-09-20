<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="/WEB-INF/taglib/captchaTag.tld" prefix="ex"%>
<%@ attribute name="strategy" required="false" type="java.lang.String"%>
<div>
	<c:if test="${strategy eq 'hidden'}">
		<input type="hidden" name="captchaId" id="captchaId"
			value="${captchaId}" />

	</c:if>
	<img src="cap?captchaId=${captchaId}" />

	<p></p>
	<div class="login-mail">
		<input id="captcha" name="captchaValue" type="text"
			placeholder="Captcha" required=""> <i
			class="glyphicon glyphicon-lock"></i>
	</div>

</div>