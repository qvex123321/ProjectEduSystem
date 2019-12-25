<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/fragment/headResource.jsp" />
	<title>學生後台系統</title>
</head>

<body id=page-top>
	<div id="wrapper">
		<c:choose>
			<c:when test="${LoginOK.privilegeId==3}">
				<jsp:include page="/fragment/sidebarA.jsp" />
			</c:when>
			<c:when test="${LoginOK.privilegeId==2}">
				<jsp:include page="/fragment/sidebarT.jsp" />
			</c:when>
			<c:when test="${LoginOK.privilegeId==1}">
				<jsp:include page="/fragment/sidebarS.jsp" />
			</c:when>
		</c:choose>

		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">

			<!-- Main Content -->
			<div id="content">
				<jsp:include page="/fragment/topbar.jsp"></jsp:include>
				<div class="container-fluid">
					<!-- 要放東西的內容都放在這裡面 -->
				</div>
			</div>
			<jsp:include page="/fragment/footer.jsp"></jsp:include>
		</div>
	</div>

	<jsp:include page="/fragment/scroll-to-top.html"></jsp:include>
	<jsp:include page="/fragment/logoutModel.jsp"></jsp:include>


	<jsp:include page="/fragment/jsResource.jsp"></jsp:include>
</body>

</html>