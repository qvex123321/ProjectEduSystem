<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="/fragment/headResource.jsp"/>

<c:if test="${LoginOK.privilegeId!=3}">
	<c:redirect url="/member/login.jsp" />
</c:if>

<title>新增會員</title>
</head>

<body id=page-top>
<!-- 測試用 -->
	
	
	<div id="wrapper">
		<!-- 引入側邊欄 -->
			<c:if test="${LoginOK.privilegeId==3}">
				<jsp:include page="/fragment/sidebarA.jsp" />
			</c:if>
	

		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">

			<!-- Main Content -->
			<div id="content">
				<jsp:include page="/fragment/topbar.jsp"></jsp:include>


				<div class="container-fluid">
					<div id="showMsg" class="text-danger px-auto">
					${MsgOK.InsertOK }${MsgMap.errorIdDup }		
					
					
					</div>
				
				
					<div class="accordion mt-3" id="accordionExample">
						<div class="card">
							<div class="card-header" id="headingSingle">
								<h2 class="mb-0">
									<button class="btn btn-muted collapsed btn-lg btn-block" type="button"
										data-toggle="collapse" data-target="#collapseSingle"
										aria-expanded="false" aria-controls="collapseSingle">
										單筆方式新增</button> 
								</h2>
								
							</div>
							<div id="collapseSingle" class="collapse"
								aria-labelledby="headingSingle" data-parent="#accordionExample">
								<div class="card-body">
								<button type="button" id="show" class="btn btn-outline-danger btn-sm mx-auto">按一下</button>
									<!-- 表單內容 -->
									<form class="col-md-8 mx-auto needs-validation" novalidate
										method="POST"
										action="${pageContext.request.contextPath}/register.do"
										enctype="multipart/form-data">
										<div class="form-group">
											<select name="privilegeId" id="privilegeId"
												onchange="selectPrivilege()">
												<option selected>請選擇註冊身份</option>
												<option value="student">學生</option>
												<option value="teacher">老師</option>
												<option value="admin">行政人員</option>
											</select>
										</div>
										<div id="container"><input type="hidden" id="seatNo" name="seatNo"><input type="hidden" id="classPeriodId" name="classPeriodId"></div>
										<br>

										<div class="form-row ">
											<div class="form-group col-md-6">
												<label for="lastName">姓氏</label> <input type="text"
													class="form-control" id="lastName" name="lastName"
													placeholder="請輸入姓氏" required>
												<div class="invalid-feedback">姓氏為必填欄位</div>
											</div>
											<div class="form-group col-md-6">
												<label for="firstName">名字</label> <input type="text"
													class="form-control" id="firstName" name="firstName"
													placeholder="請輸入名字" required>
												<div class="invalid-feedback">名字為必填欄位</div>
											</div>
										</div>
										<div class="form-group">
											<label for="accountName">帳號</label> <input type="text"
												class="form-control" id="accountName" name="accountName"
												placeholder="請輸入帳號" required>
												<div class="invalid-feedback">帳號為必填欄位</div>
										</div>
										<div class="form-group">
											<label for="password">密碼</label> <input type="password"
												class="form-control" id="password" name="password"
												placeholder="請輸入密碼" required> 
												<div class="invalid-feedback">密碼為必填欄位</div>
												<!-- <div class="invalid-feedback">密碼格式錯誤</div> -->
												<small id="passwordHelpBlock"
												class="form-text text-muted">
												密碼至少含有一個大寫字母、小寫字母、數字與!@#$%!^'\"等四組資料組合而成，且長度不能小於八個字元 </small>
										</div>
										<div class="form-group">
											<label for="email">E-MAIL</label> <input type="email"
												class="form-control" id="email" name="email"
												placeholder="請輸入E-MAIL" required>
												<div class="invalid-feedback">E-MAIL為必填欄位</div>
										</div>

										<div class="form-group ">
											<legend class="col-form-label pt-0">性別</legend>
											<div class="form-check-inline">
												<input class="form-check-input" type="radio" name="gender"
													value="male" id="male" checked> <label
													class="form-check-label" for="male"> 男 </label>
											</div>
											<div class="form-check-inline">
												<input class="form-check-input" type="radio" name="gender"
													value="female" id="female"> <label
													class="form-check-label" for="female"> 女 </label>
											</div>
										</div>

										<div class="form-group">
											<label for="telePhone">電話號碼</label> <input type="text"
												class="form-control" id="telePhone" name="telePhone"
												placeholder="請輸入電話號碼" required>
												<div class="invalid-feedback">電話號碼為必填欄位</div>
										</div>

										<div class="form-group">
											<label for="cellPhone">手機號碼</label> <input type="text"
												class="form-control" id="cellPhone" name="cellPhone"
												placeholder="請輸入手機號碼" required>
												<div class="invalid-feedback">手機號碼為必填欄位</div>
										</div>

										<div class="form-row">
											<legend class="col-form-label pt-0">出生日期</legend>
											<div class="form-group col-md-4">
												<label for="ddl_year">年份</label> <select
													class="form-control" id="ddl_year" name="birthDate_year" required>
												</select>
											</div>
											<div class="form-group col-md-4">
												<label for="ddl_month">月</label> <select
													class="form-control" id="ddl_month" name="birthDate_month" required>
												</select>
											</div>
											<div class="form-group col-md-4">
												<label for="ddl_day">日</label> <select class="form-control"
													id="ddl_day" name="birthDate_day" required>
												</select>
												<div class="invalid-feedback">請選擇</div>
											</div>
										</div>

										<!-- 有空再改成下拉選單 -->
										<div class="form-group">
											<label for="address">地址</label> <input type="text"
												class="form-control" id="address" name="address"
												placeholder="請輸入地址">
										</div>

										<div class="form-group">
											<label for="memberImage">個人資料照片</label> <input type="file"
												class="form-control-file" id="memberImage"
												name='memberImage'>
										</div>

										<button type="submit" class="btn btn-dark col-md-3 mt-3">確定送出</button>
									</form>

								</div>
							</div>
						</div>
						<div class="card mx-auto">
							<div class="card-header" id="headingOne">
								<h2 class="mb-0">
									<button class="btn-lg btn-muted btn btn-block" type="button"
										data-toggle="collapse" data-target="#collapseOne"
										aria-expanded="true" aria-controls="collapseOne">
										檔案方式新增</button>
								</h2>
							</div>

							<div id="collapseOne" class="collapse"
								aria-labelledby="headingOne" data-parent="#accordionExample">
								<div class="card-body">
									<form method="POST"
										action="${pageContext.request.contextPath}/CsvProcessServlet"
										enctype="multipart/form-data">
										<input type="file" name="csvFile">
										<button type="submit" class="btn btn-light">送出</button>
									</form>
								</div>
							</div>
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
	
	
	//1911為開始年份
	set_ddl_date(1911);

	function set_ddl_date(year_start) {
		var now = new Date();

		//年(year_start~今年)
		for (var i = now.getFullYear(); i >= year_start; i--) {
			$('#ddl_year').append(
					$("<option></option>").attr("value", i).text(i));
		}

		//月
		for (var i = 1; i <= 12; i++) {
			$('#ddl_month').append(
					$("<option></option>").attr("value", i).text(i));
		}

		$('#ddl_year').change(onChang_date);
		$('#ddl_month').change(onChang_date);

		//年、月選單改變時
		function onChang_date() {
			if ($('#ddl_year').val() != -1 && $('#ddl_month').val() != -1) {

				var date_temp = new Date($('#ddl_year').val(), $(
						'#ddl_month').val(), 0);

				//移除超過此月份的天數
				$("#ddl_day option").each(
						function() {
							if ($(this).val() != -1
									&& $(this).val() > date_temp.getDate())
								$(this).remove();
						});

				//加入此月份的天數
				for (var i = 1; i <= date_temp.getDate(); i++) {
					if (!$("#ddl_day option[value='" + i + "']").length) {
						$('#ddl_day').append(
								$("<option></option>").attr("value", i)
										.text(i));
					}
				}
			} else {
				$("#ddl_day option:selected").removeAttr("selected");
			}
		}
	}
	
	//選擇學生老師或是行政人員的onclick
	function selectPrivilege(){
		if(document.getElementById("privilegeId").value!="student"){
			document.getElementById("classTypeId").remove();
		}
		if(document.getElementById("privilegeId").value=="student"){
			var elm=document.createElement("select");
			elm.setAttribute("id","classTypeId");
			elm.setAttribute("onchange","sendClassTypeAndPeriodNo()");
			
			//要做活的請塞下面AJAX
			// var opt0=document.createElement("option");
			// opt0.innerText="";
			// opt0.setAttribute=("value",0);
			// var opt1=document.createElement("option");
			// opt1.innerText="EEIT";
			// opt1.setAttribute=("value",1);
			// var opt2=document.createElement("option");
			// opt2.innerText="JJIT";
			// opt2.setAttribute=("value",2);
			// var opt3=document.createElement("option");
			// opt3.innerText="CCIT";
			// opt3.setAttribute=("value",3);
			
			// elm.appendChild(opt0);
			// elm.appendChild(opt1);
			// elm.appendChild(opt2);
			// elm.appendChild(opt3);
			
			//AJAX
			var xhttp = new XMLHttpRequest();
			var opt = document.createElement("option");
			
			xhttp.onload=function(){
				var dataO = JSON.parse(xhttp.responseText);
				elm.append(document.createElement("option"));
				for ( var i in dataO) {
            	var opt = document.createElement("option");
            	opt.value=dataO[i].eduProgramTypeName;
            	opt.innerText=dataO[i].ClasPeriodId;
            	elm.append(opt);
        		}
			}
			xhttp.open("GET", "../../GetClass ", true);
			xhttp.send();

			document.getElementById("container").append(elm);
		}
	}



	function sendClassTypeId() {
		
		var classTypeValue = document.getElementById("classTypeId").value;
		var xhttp = new XMLHttpRequest();
		//			alert(classTypeValue);
		xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				var sel = document.createElement("div");
				//sel.setAttribute("id","classIdList");
				sel.innerHTML=this.responseText;
				document.getElementById("container").append(sel);
			}
		}
		xhttp.open("GET", "../SelectPeriodNumberServlet?id="
				+ classTypeValue, true);
		xhttp.send();
	}

	function sendClassTypeAndPeriodNo() {
		
		var xhttp = new XMLHttpRequest();
		// 取得selcted 班級下 的value
		var classTypeSelect = document.getElementById("classTypeId");
		var index = classTypeSelect.selectedIndex;
		var classTypeValue = classTypeSelect.options[index].text;
		
		console.log(classTypeValue)
		var PeriodNo = document.getElementById("classTypeId").value;
		var gatai = classTypeValue + PeriodNo;
		//var gatai = PeriodNo;
		xhttp.onreadystatechange = function() {
			if (this.readyState == 4 && this.status == 200) {
				console.log(this.responseText);/*  */
				$("#seatNo").val(this.responseText);
				$("#classPeriodId").val(classTypeValue);
				//seatNo.innerHTML=this.responseText;
				//console.log(seatNo);
				
				document.getElementById("container").append(seatNo);
				console.log(xhttp.responseText);
			}
		}
		xhttp.open("GET", "../../getSeatNoServlet?id=" + classTypeValue, true);
		xhttp.send();
	}

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
		
	
	// 一鍵
		$("#show").click(function(){
					$("#firstName").val("Amy");
					$("#lastName").val("Chen");
					$("#accountName").val("user"+Math.ceil(Math.random()*1000));
					$("#password").val("Do!ng123");
					$("#email").val("abcd@gmail.com");
					$("#telePhone").val("02-12345678");
					$("#cellPhone").val("0912-3145678");
					$("#address").val("Taipei");
				})
		
	</script>
</body>

</html>