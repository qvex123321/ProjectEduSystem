<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<jsp:include page="/fragment/headResource.jsp"/>
<c:if test="${LoginOK.privilegeId!=3}">
	<c:redirect url="/member/login.jsp" />
</c:if>

<meta charset="UTF-8">
<title>會員資料</title>
</head>

<body id=page-top>

	<div id="wrapper">
		<!-- 引入側邊欄 -->
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
					<div class="card mb-5">
						<div class="card-header" id="headingSingle">
							<h5 class="mb-0">開新班級</h5>
						</div>

						<div class="card-body">
							<div class="col-md-8 updateMsg mb-3 mx-auto text-danger">
								<p><c:out value="${msgOK.createClassOK} ${errorMsg.errorClass}"/>
								
								</p></div>
							<!-- 表單內容 -->
							<form class="col-md-8 mx-auto needs-validation" novalidate
								method="POST"
								action="${pageContext.request.contextPath}/CreateClass">

								<div class="form-group">
									<label for="program">學程</label> <select id="program"
										class="form-control" required>

									</select>
								</div>
								<div class="form-group">
									<label for="period">期數</label> <input type="text"
										class="form-control" id="period" name="period" disabled
										required>
								</div>
								<div class="form-group">
									<label for="startDate">開課日期</label> <input type="date"
										class="form-control" id="startDate" name="startDate" required>
								</div>

								<div class="form-group">
									<label for="">結業日期</label> <input type="date"
										class="form-control" id="endDate" name="endDate" required>
								</div>
								<div class="form-group">
									<label for="classRoom">使用教室</label> <input type="text"
										class="form-control" id="classRoom" name="classRoom"
										placeholder="請輸入教室" required>
								</div>
								<input type="hidden" name="obj" value="test" id="obj">
								<button type="submit" class="btn btn-dark col-md-3 mt-3">儲存</button>
							</form>
						</div>
					</div>
				</div>
			</div>
			<jsp:include page="/fragment/footer.jsp"></jsp:include>
		</div>
	</div>

	<jsp:include page="/fragment/scroll-to-top.html"></jsp:include>
	<jsp:include page="/fragment/logoutModel.jsp"></jsp:include>
	<jsp:include page="/fragment/jsResource.jsp"></jsp:include>
	<script src="newClass.js" ></script>

</body>

</html>