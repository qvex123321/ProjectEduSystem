<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="zh-TW">

<head>
  <title>Login</title>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1" />
  <link rel="icon" type="image/png" href="images/icons/favicon.ico" />
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/general/vendor/bootstrap/css/bootstrap.min.css" />
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/general/fonts/font-awesome-4.7.0/css/font-awesome.min.css" />
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/general/fonts/Linearicons-Free-v1.0.0/icon-font.min.css" />
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/general/vendor/animate/animate.css" />
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/general/vendor/css-hamburgers/hamburgers.min.css" />
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/general/vendor/animsition/css/animsition.min.css" />
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/general/vendor/select2/select2.min.css" />
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/general/vendor/daterangepicker/daterangepicker.css" />
  <!--===============================================================================================-->
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/general/css/util.css" />
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/general/css/main.css" />
  <!--===============================================================================================-->
</head>

<body>
  <div class="limiter">
    <div class="container-login100">
      <div class="wrap-login100 p-l-85 p-r-85 p-t-55 p-b-55">
        <form class="login100-form validate-form flex-sb flex-w" method="POST" action="${pageContext.request.contextPath}/login.do">
          <span class="login100-form-title p-b-32"> 數位學習網ﾚ(ﾟ∀ﾟ;)ﾍ=З=З=З </span>
          <span class="txt1 p-b-11"> Username </span>
          <div class="wrap-input100 validate-input m-b-36" data-validate="Username is required">
            <input class="input100" type="text" name="username" value="${requestScope.user}${param.username}"/>
            <span class="focus-input100"></span>
          </div>

          <span class="txt1 p-b-11"> Password </span>
          <div class="wrap-input100 validate-input m-b-12" data-validate="Password is required">
            <span class="btn-show-pass"> <i class="fa fa-eye"></i> </span>
            <input class="input100" type="password" name="password" value="${requestScope.password}${param.password}"/>
            <span class="focus-input100"></span>
          </div>

          <div class="flex-sb-m w-full p-b-32">
            <div class="contact100-form-checkbox">
              <input class="input-checkbox100" id="ckb1" type="checkbox" name="rememberMe" <c:if test='${requestScope.rememberMe==true}'>checked='checked'</c:if> value="true">
              <label class="label-checkbox100" for="ckb1">
                 記住我
              </label>
            </div>
            <div>
              <!-- <a href="./forgetPassword.jsp" class="txt3"> 忘記密碼 </a> -->
              <div  class="txt3">(=´ω`=)</div>
            </div>
          </div>
          <div class="flex-sb-m w-full p-b-40 errorMsg">
          	<p class = "text-danger">${ErrorMsgKey.LoginError}</p>
    	  </div>

          <div class="container-login100-form-btn">
            <button class="login100-form-btn">Login</button>
          </div>
        </form>
      </div>
    </div>
  </div>

  <div id="dropDownSelect1">
  </div>

  <!--===============================================================================================-->
  <script src="${pageContext.request.contextPath}/general/vendor/jquery/jquery.min.js"></script>
  <!--===============================================================================================-->
  <script src="${pageContext.request.contextPath}/general/vendor/animsition/js/animsition.min.js"></script>
  <!--===============================================================================================-->
  <script src="${pageContext.request.contextPath}/general/vendor/bootstrap/js/popper.js"></script>
  <script src="${pageContext.request.contextPath}/general/vendor/bootstrap/js/bootstrap.min.js"></script>
  <!--===============================================================================================-->
  <script src="${pageContext.request.contextPath}/general/vendor/select2/select2.min.js"></script>
  <!--===============================================================================================-->
  <script src="${pageContext.request.contextPath}/general/vendor/daterangepicker/moment.min.js"></script>
  <script src="${pageContext.request.contextPath}/general/vendor/daterangepicker/daterangepicker.js"></script>
  <!--===============================================================================================-->
  <script src="${pageContext.request.contextPath}/general/vendor/countdowntime/countdowntime.js"></script>
  <!--===============================================================================================-->
  <script src="${pageContext.request.contextPath}/general/js/main.js"></script>
</body>

</html>