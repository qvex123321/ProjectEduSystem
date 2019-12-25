<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="zh-TW">

<head>
<title>Login</title>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<link rel="icon" type="image/png" href="images/icons/favicon.ico" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/general//vendor/bootstrap/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/general//fonts/font-awesome-4.7.0/css/font-awesome.min.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/general//fonts/Linearicons-Free-v1.0.0/icon-font.min.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/general//vendor/animate/animate.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/general//vendor/css-hamburgers/hamburgers.min.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/general//vendor/animsition/css/animsition.min.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/general//vendor/select2/select2.min.css" />
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/general//vendor/daterangepicker/daterangepicker.css" />
<!--===============================================================================================-->
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/general/css/util.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/general/css/main.css" />
<!--===============================================================================================-->
</head>

<body>
	<c:set var="memberName" value="${ LoginOK.accountName }" />
	<c:remove var="LoginOK" scope="session" />
	<c:set var="status" value="OUT" scope="session" />

	<%
		if (session != null) {
			session.invalidate();
		}
	%>
	<%-- <c:set target='${logoutBean}' property='session'  value='${pageContext.session}'/> --%>

	<c:redirect url="login.jsp" />

	<!--===============================================================================================-->
	<script
		src="${pageContext.request.contextPath}/general/vendor/jquery/jquery.min.js"></script>
	<!--===============================================================================================-->
	<script
		src="${pageContext.request.contextPath}/general/vendor/animsition/js/animsition.min.js"></script>
	<!--===============================================================================================-->
	<script
		src="${pageContext.request.contextPath}/general/vendor/bootstrap/js/popper.js"></script>
	<script
		src="${pageContext.request.contextPath}/general/vendor/bootstrap/js/bootstrap.min.js"></script>
	<!--===============================================================================================-->
	<script
		src="${pageContext.request.contextPath}/general/vendor/select2/select2.min.js"></script>
	<!--===============================================================================================-->
	<script
		src="${pageContext.request.contextPath}/general/vendor/daterangepicker/moment.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/general/vendor/daterangepicker/daterangepicker.js"></script>
	<!--===============================================================================================-->
	<script
		src="${pageContext.request.contextPath}/general/vendor/countdowntime/countdowntime.js"></script>
	<!--===============================================================================================-->
	<script src="${pageContext.request.contextPath}/general/js/main.js"></script>
</body>

</html>