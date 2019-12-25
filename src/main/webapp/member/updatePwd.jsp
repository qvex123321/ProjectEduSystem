<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
	<jsp:include page="/fragment/headResource.jsp"/>
<title>會員資料</title>
</head>

<body id=page-top>

${msgOK}
${errorMsg}
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
					<!-- 內頁內容起點 -->
						<div class="card-header" id="headingSingle">
							<h5 class="mb-0">會員密碼修改</h5>
						</div>
						<div class="card-body">
							<!-- 表單內容 -->
							<form class="col-md-8 mx-auto needs-validation" novalidate
								method="POST" action="${pageContext.request.contextPath}/UpdatePwd">
								
								<div class="form-group">
									<label for="oldPwd">請輸入舊密碼：</label> <input type="password"
										class="form-control" id="oldPwd" name="oldPwd"
										placeholder="請輸入舊密碼"  required>
									<div class="invalid-feedback">密碼不可為空</div>
								</div>

								<div class="form-group">
									<label for="newPwd">請輸入新密碼：</label> <input type="password"
										class="form-control" id="newPwd" name="newPwd"
										placeholder="請輸入新密碼" required>
									<div class="invalid-feedback">請輸入新密碼</div>
								</div>

								<!-- 有空再改成下拉選單 -->
								<div class="form-group">
									<label for="newPwdCheck">確認新密碼：</label> <input type="password"
										class="form-control" id="newPwdCheck" name="newPwdCheck"
										placeholder="請確認新密碼" required>
									<div class="invalid-feedback">請確認新密碼</div>
								</div>

								<button type="submit" class="btn btn-dark col-md-3 mt-3">確定</button>
								<button type="reset" class="btn btn-dark col-md-3 mt-3">重填</button>
							</form>
						</div>
						<!-- 內頁內容結束點 -->
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