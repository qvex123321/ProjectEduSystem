<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<jsp:include page="/fragment/headResource.jsp"/>
<!-- styles for calendar -->
<link href='scr/css/fullcalendar.css' rel='stylesheet' />
<link href='scr/css/fullcalendar.print.css' rel='stylesheet' media='print' />

<link href="scr/css/cal.css" rel="stylesheet">

<title>MyModel</title>

</head>
<body id=page-top>
	<div id="wrapper">
		<jsp:include page="/fragment/sidebarS.jsp" />
		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">

			<!-- Main Content -->
			<div id="content">
				<jsp:include page="/fragment/topbar.jsp"></jsp:include>
				<div class="container-fluid">
					<!-- 要放東西的內容都放在這裡面 -->
					<div id='calendar'></div>
				</div>
				<jsp:include page="/fragment/footer.jsp"></jsp:include>
			</div>
		</div>
	</div>

	<jsp:include page="/fragment/scroll-to-top.html"></jsp:include>
	<jsp:include page="/fragment/logoutModel.jsp"></jsp:include>
	<jsp:include page="/fragment/jsResource.jsp"></jsp:include>
	
	<script src='scr/js/moment.min.js'></script>
	<script src='scr/js/fullcalendar.js'></script>
	<!-- <script src="scr/js/cal.js"></script> -->
	<script>
		var today = moment().format('YYYY-MM-DD');
		//$(document).ready(function() {
		document.addEventListener('DOMContentLoaded', function() {
			$('#calendar').fullCalendar({
				header : {
					left : 'prev,next /*today*/',
					center : 'title',
					right : 'month,basicWeek,/*basicDay*/'
				},
				defaultDate : today,
				navLinks : false, // can click day/week names to navigate views
				editable : false,
				eventLimit : false, // allow "more" link when too many events
				eventSources : $.ajax({
					url : "../Schedule?classPeriodId=${LoginO2K.classPeriodId}",
					type : "GET",
					//error : alert("ERROR"),
					success : function(data) {
						console.log(typeof(JSON.parse(data)));
						$('#calendar').fullCalendar({
							events : JSON.parse(data) ,
						});
					}
				})
			});
//			$.ajax({
//				url : "../Schedule?classPeriodId=${LoginO2K.classPeriodId}",
//				type : "GET",
				//error : alert("ERROR"),
//				success : function(data) {
//					console.log(typeof(JSON.parse(data)));
//					$('#calendar').fullCalendar({
//						events : JSON.parse(data) ,
//					});
//				}
//			});
			
			
		});
		
	</script>
	<script>
		//按鈕點擊隱藏另外一個(更動js檔的14792)
		function dpButton(Button) {
			if (Button === "prev") {
				document.getElementById("prev").style.visibility = "hidden";
				document.getElementById("next").style.visibility = "visible";
			} else if (Button === "next") {
				document.getElementById("next").style.visibility = "hidden";
				document.getElementById("prev").style.visibility = "visible";
			}
		}
		//這個function是要塞ajax的地方
    function check(id) {
      console.log(id);

      if(document.getElementById('popBox') !== null){
        document.getElementById('popBox').remove();
      }

      popUp();
      setJson(id);
    }

    function popUp() {
      if (document.getElementById('popBox') === null) {

        var pDiv = document.createElement("div"); //生成popUp之div
        pDiv.setAttribute('id', 'popBox'); //賦予上面生成的div標籤一個id      
        var xButton = document.createElement("button"); //生成button
        xButton.innerText = 'X'; //把按鈕的value設為X，以看起來有叉叉的效果
        xButton.setAttribute('onmouseup', 'removePdiv()'); //定義點擊要觸發的function
        pDiv.append(xButton);//把按鈕塞進xDiv內
        xButton.setAttribute('id', 'xButton');//賦予按鈕顯示的X
        document.getElementById('calendar').append(pDiv);//把 pDiv 塞進 calendar div下
        positionHandler();// 讓彈出視窗的位置在滑鼠點擊位置      
      }else{
        removePdiv();
      }
    }

    function removePdiv() {
      document.getElementById('popBox').remove();
    }

    function positionHandler() {/*設定置中的函式*/
      var myTop = window.event.y; /*取得垂直中央位置*/
      var myLeft = window.event.x; /*取得水平中央位置*/
      $("#popBox").offset({ top: myTop, left: myLeft }); /*設定區塊於水平與垂直置中*/
    }

    function setJson(id){

      var jsonDiv_1 = document.createElement("div");
      var jsonDiv_1_P = document.createElement("p");
      jsonDiv_1.setAttribute('id','json1');
      jsonDiv_1_P.setAttribute('id','jDT1');
      jsonDiv_1_P.setAttribute('src','snoopy.jpg');
      // document.getElementById('json1').append(jsonDiv_1_P);           
      document.getElementById('popBox').append(jsonDiv_1);
      document.getElementById('json1').append(jsonDiv_1_P); 

      var jsonDiv_2 = document.createElement("div");
      jsonDiv_2.setAttribute('id','json2');  
      document.getElementById('popBox').append(jsonDiv_2); 
                                                                
      var jsonDiv_3 = document.createElement("div");
      jsonDiv_3.setAttribute('id','json3');  
      document.getElementById('popBox').append(jsonDiv_3);

      var x = document.getElementById('json1');
      var y = document.getElementById('json2');
      var z = document.getElementById('json3');      


      var xhttp = new XMLHttpRequest();

      xhttp.onload = function() { 
         rText = this.responseText;   
         console.log(rText);
         console.log(typeof(JSON.parse(rText)));
         x.innerText = '課程名稱：'+　JSON.parse(rText).courseName;
         y.innerText = '授課老師：'+　JSON.parse(rText).teacher;
         z.innerText = '課程簡介：'+　JSON.parse(rText).briefInfo;
      }
      xhttp.open("GET", "CourseInfo?courseId="+id, true); //中間的參數名稱表示要讀入的json字串
      xhttp.send();
    }
	</script>
</body>
</html>