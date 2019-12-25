<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>

<head>
	<jsp:include page="/fragment/headResource.jsp"/>
<title>會員資料</title>
</head>

<body id=page-top>

${errorMsg.errorUpdate}
${msgOK.updateOK }

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
							<h5 class="mb-0">會員資料檢視</h5>
						</div>
						<div class="card-body">
							<!-- 表單內容 -->
							<form class="col-md-8 mx-auto needs-validation" novalidate
								method="POST" action="${pageContext.request.contextPath}/info"
								enctype="multipart/form-data">
								<div class="form-group">
									<label for="privilegeId">會員身份</label> 
									<c:choose>
										<c:when test="${LoginOK.privilegeId ==3 }">
											<c:set var="identity" value="行政人員"></c:set>
										</c:when>
										<c:when test="${LoginOK.privilegeId ==2 }">
											<c:set var="identity" value="教職員"></c:set>
										</c:when>
										<c:otherwise>
											<c:set var="identity" value="學生"></c:set>
										</c:otherwise>
									</c:choose>
									
									
									<input type="text"
										class="form-control" id="privilegeId" name="privilegeId"
										value="${identity }" disabled>
								</div>

								<div class="form-row ">
									<div class="form-group col-md-6">
										<label for="lastName">姓名</label> <input type="text"
											class="form-control" id="lastName" name="lastName"
											placeholder="請輸入姓氏" value="${LoginOK.lastName}" required>
										<div class="invalid-feedback">姓氏為必填欄位</div>
									</div>
									<div class="form-group col-md-6">
										<label for="firstName">名字</label> <input type="text"
											class="form-control" id="firstName" name="firstName"
											placeholder="請輸入名字" value="${LoginOK.firstName}" required>
										<div class="invalid-feedback">名字為必填欄位</div>
									</div>
								</div>

								<div class="form-group">
									<label for="email">E-MAIL</label> <input type="email"
										class="form-control" id="email" name="email"
										placeholder="請輸入E-MAIL" value="${LoginOK.email }" >
									<div class="invalid-feedback">E-MAIL為必填欄位</div>
								</div>

								<div class="form-group">
									<label for="telePhone">電話號碼</label> <input type="text"
										class="form-control" id="telePhone" name="telePhone"
										placeholder="請輸入電話號碼" value="${LoginOK.telePhone}" required>
									<div class="invalid-feedback">電話號碼為必填欄位</div>
								</div>

								<div class="form-group">
									<label for="cellPhone">手機號碼</label> <input type="text"
										class="form-control" id="cellPhone" name="cellPhone"
										placeholder="請輸入手機號碼" value="${LoginOK.cellPhone}" required>
									<div class="invalid-feedback">手機號碼為必填欄位</div>
								</div>

								<!-- 有空再改成下拉選單 -->
								<div class="form-group">
									<label for="address">地址</label> <input type="text"
										class="form-control" id="address" name="address"
										placeholder="請輸入地址" value="${LoginOK.address}">
								</div>

								<div class="form-group">
									<label for="memberImage">上傳照片</label> <input type="file"
										class="form-control-file" id="memberImage" name='memberImage'>
								</div>
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
	<script>
	

		(function() {
			'use strict';
			window.addEventListener('load',
					function() {
						// Fetch all the forms we want to apply custom Bootstrap validation styles to
						var forms = document
								.getElementsByClassName('needs-validation');
						// Loop over them and prevent submission
						var validation = Array.prototype.filter.call(forms,
								function(form) {
									form.addEventListener('submit', function(
											event) {
										if (form.checkValidity() === false) {
											event.preventDefault();
											event.stopPropagation();
										}
										form.classList.add('was-validated');
									}, false);
								});
					}, false);
		})();
	</script>
</body>

</html>