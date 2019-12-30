<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>

<jsp:include page="/fragment/headResource.jsp" />
<meta charset='utf-8' />
<link href='scr/css/core.css' rel='stylesheet' />
<link href='scr/css/daygrid.css' rel='stylesheet' />
<link href='scr/css/timegrid.css' rel='stylesheet' />
<link href='scr/css/list.css' rel='stylesheet' />

<!-- styles for calendar -->
<link href='scr/css/fullcalendar.css' rel='stylesheet' />
<link href='scr/css/fullcalendar.print.css' rel='stylesheet'
	media='print' />
<link href="scr/css/cal.css" rel="stylesheet">

<script src='scr/js/core.js'></script>
<script src='scr/js/interaction.js'></script>
<script src='scr/js/daygrid.js'></script>
<script src='scr/js/timegrid.js'></script>
<script src='scr/js/jquery.min.js'></script>
<script src='scr/js/moment.min.js'></script>

<!-- For the Demo object -->
<script src='scr/js/ClassCourseDemo.js'></script>

<script>
	//使用moment.js拿到現在時間，塞進去
	var today = moment().format('YYYY-MM-DD');
	var flag = false;
	calendar();
	//calender區
	function calendar(){
	document.addEventListener('DOMContentLoaded', function() {
		var calendarEl = document.getElementById('calendar');
		var eventStartTime="";
		var eventEndTime=""; 
		var eventId;

		var calendar = new FullCalendar.Calendar(calendarEl, {
			plugins : [ 'interaction', 'dayGrid', 'timeGrid' ],
			// defaultView: 'dayGridMonth',
			header : {
				left : 'addEventButton mornButton afterButton nightButton',
				center : 'title',
				right : 'prev,next'
			},
			footer : {
				right : 'DEMOButton submitButton',
			},
			defaultDate : today,
			navLinks : false, // can click day/week names to navigate views
			selectable : true,
			selectMirror : true,
			editable : false,
			eventLimit : true, // allow "more" link when too many events

			//在選擇日期時的動作
			select : function(arg) {
				
				//用來判斷是不是要執行addEvent()
				var title = $('#showCourses :selected').text();
				var eventid = $('#showCourses :selected').val();
				var start = arg.startStr + "T" + eventStartTime;
				var end = arg.startStr + "T" + eventEndTime;
				//比對目前events中所有Object的開始時間
				var nowEvents = calendar.getEvents();
				// console.log(nowEvents);

				for ( var i in nowEvents) {
					// flag=true;
					//如果events陣列中有與現在的時間相同時(使用moment.js來幫忙轉時間格式)
					if (moment(start).format("YYYY-MM-DDTHH:mm:ss") == moment(
							nowEvents[i].start).format("YYYY-MM-DDTHH:mm:ss")) {
						if (confirm("是否要覆蓋原課程？") == true) {
							//要在這裡增加回復課程時數的code
							flag = true;
							nowEvents[i].remove();
							break;
						} else {
							//不要執行，讓USER重新選擇
							flag=false;
							calendar.unselect();
							break;
						}
					//增加課程檢查時數功能
					}else if($("#"+eventid).val()==0){
						alert("此課程時數已排滿");
						flag=false;
						calendar.unselect();
						break;
					}else{
						flag=true;
					}
					
				}
				if (flag) {
					calendar.addEvent({
						title : title,
						start : arg.startStr + "T" + eventStartTime,
						end : arg.startStr + "T" + eventEndTime,
						id : eventid,
					});
					flag = false;
					//課程新增成功，扣掉總時數
					var beforeClassHour=$("#"+eventid).val();
					//console.log(beforeCalssHour);
					$("#"+eventid).val(parseInt(beforeClassHour)-3);
					//console.log($("#"+eventid).val());
				}

				calendar.unselect();
			},
			//生一個自訂按鈕
			customButtons : {
/*				addEventButton : {
					text : 'search',
					//click預先啟用，要把function放在callback裡面執行
					click : function() {
						//恩他不吃Array...只吃Object..
						var courseList = getCourseListContent();
						for ( var i in courseList) {
							calendar.addEvent(courseList[i]);
						}
						//顯示早中晚按鈕
						timeButton();
						// console.log(calendar.events);
					},

				},
*/
				// //自訂早中晚按鈕(1214晚)
				mornButton : {
					text : '早',
					click : function() {
						eventStartTime = "09:00:00";
						eventEndTime = "12:00:00";
						flag=true;
						;
					},

				},

				afterButton : {
					text : '中',
					click : function() {
						eventStartTime = "14:00:00";
						eventEndTime = "17:00:00";
						//這個是課程的Id
						flag=true;
						;
					},
				},

				nightButton : {
					text : '晚',
					click : function() {
						eventStartTime = "18:00:00";
						eventEndTime = "21:00:00";
						//這個是課程的Id
						flag=true;
						;
					},
				},
				//送出按鈕(1214晚用calendar.getEvents()拿到記憶體的值(不可以覆蓋，必須我們自己判斷))
				submitButton : {
					text : '送出',
					click : function() {
						
						if (confirm("是否送出課程？") == true) {
							var ALLEvents=calendar.getEvents();
							var OUTPUT=[];
							OUTPUT=convertOutput(ALLEvents);
							/*for(var i in ALLEvents){
								var OPObject={
									title:ALLEvents[i].title,
									start:moment(ALLEvents[i].start).format("YYYY-MM-DDTHH:mm:ss"),
									end:moment(ALLEvents[i].end).format("YYYY-MM-DDTHH:mm:ss"),
									id:ALLEvents[i].id,
									class:$("#showClass :selected").text()
								}
								OUTPUT.push(OPObject);
							}*/
							console.log(OUTPUT);
							var JSONOP=JSON.stringify(OUTPUT);
//							console.log(JSONOP);
							$.ajax({
								url : "../SaveSchedule",
								data: {
									JSONData:JSONOP,
								},
								//data:calendar.getEvents(),
								type : "POST",
								success : function(data) {
									alert("新增課表成功");
								}
							});
						}
					},
				},
				
				//DEMO button
				DEMOButton : {
					text : 'DEMO',
					click : function() {
						for(var i in JSONDEMO){
							calendar.addEvent(JSONDEMO[i]);
						};
					},
				},
				
			},
			
			//點選課程並詢問課程是否要刪除(12/15)
			eventClick: function(info) {
    			// alert('Clicked on: ' + info.event);
    			if (confirm("是否要刪除原課程？") == true) {
    				info.event.remove();
    			}
  			},
  			
  			
			
		});
		calendar.render();
		
		/*var result = {
			id: 0,
			title: "",
			start: "",
			end: "",
		}
		
		function getResult(events){
			for(var)
		}*/
		
		// Convert all events objects into lighter objects ordered by time.
		function convertOutput(allEvents){
			let output = [];
			let category = {};
			//console.log(allEvents);
			for(var i in allEvents){
				var tempObject = {
					title:allEvents[i].title,
					start:moment(allEvents[i].start).format("YYYY-MM-DDTHH:mm:ss"),
					end:moment(allEvents[i].end).format("YYYY-MM-DDTHH:mm:ss"),
					id:allEvents[i].id,
					class:$("#showClass :selected").text()
				}
				let year_month = moment(allEvents[i].start).format("YYYY-MM")
				//console.log(year_month);
				//console.log(category[year_month]);
				if(!category[year_month]){
					category[year_month] = [];
				}
				category[year_month].push(tempObject);
			}
			console.log(category);
			for(var key in category){
				output = output.concat(category[key]);
			}
			//console.log(output);
			return output;
		}
		
		//等著測試的地方
		function getCourseList(data) {
			var dataO = JSON.parse(data);
			//console.log(typeof (dataO));
			$("#showCourses").remove();
			$(".fc-left:first:first").prepend($("<select>").attr("id","showCourses"));
			for ( var i in dataO) {
				var opt = $("<option>").val(dataO[i].courseId).text(
						dataO[i].courseName);
				$("#showCourses").append(opt);
				//
				var cHour = $("<input>").attr("type","hidden").attr("id",dataO[i].courseId).
										val(dataO[i].courseHour);
				$("#calendar").append(cHour);
			}
		}
		
		$("#showClass").change(function() {
			//跳確認詢問是否要洗掉
			
			//清掉calendar中既有的event
			calendar.events="";
			//把已經存在的課表拉進來
			
			//如果class有時數，取代預設時數
			
			
			var id = $("#showClass :selected").text();
			$.ajax({
				url : "../GetCourses?classPeriodId=" + id,
				type : "GET",
				success : function(data) {
					
					getCourseList(data);
					setCourseListContent(data);
					//呼叫外面的ajax
					//恩他不吃Array...只吃Object..
					var courseList = getCourseListContent();
					for ( var i in courseList) {
						calendar.addEvent(courseList[i]);
					}
					//顯示早中晚按鈕
					timeButton();
					// console.log(calendar.events);					
				}
			});
		});
		
	});
	};
</script>
<style>
html {
	font-family: 'Noto Sans TC', Arial,, Nunito, Helvetica Neue, Helvetica,
		sans-serif;
}

body {
	/*margin: 40px 10px;*/
	padding: 0;
	font-size: 14px;
}

.calendar-container {
	margin: 0 auto;
	width: 800px;
}

.container-fluid {
	padding: 0px;
}

.fc-content {
	color: white;
}

#calendar {
	width: 800px;
	margin: 0 auto;
}

#selectClass {
	margin: 0 auto;
}

/*直接在html覆蓋css的,對應上下個月的設定*/
#prev {
	visibility: hidden;
}

#next {
	visibility: visible;
}

/*隱藏旁邊選擇月與週功能(不能disable，會跑版)*/
.fc-month-button, .fc-basicWeek-button {
	visibility: hidden;
}

/*隱藏早中晚按鈕，1214晚*/
.fc-mornButton-button, .fc-afterButton-button, .fc-nightButton-button {
	visibility: hidden;
	margin: 0px 0px 0px 0px;
}
</style>
<script src='scr/js/jquery.min.js'></script>
<title>ClassCourseTest</title>

</head>
<body id=page-top>
	<div id="wrapper">
		<jsp:include page="/fragment/sidebarA.jsp" />
		<!-- Content Wrapper -->
		<div id="content-wrapper" class="d-flex flex-column">

			<!-- Main Content -->
			<div id="content">
				<jsp:include page="/fragment/topbar.jsp"></jsp:include>
				<div class="calendar-container">
					<div class="container-fluid">
						<!-- 要放東西的內容都放在這裡面 -->
						<p>
							<b>Choose a class: </b>
						</p>
						<select id="showClass"></select>
					</div>
					<div id='courseSeletorField'>
						<p>
							<b>Choose a course: </b>
						</p>
					</div>
				</div>
				<div id='calendar'></div>
			</div>
			<jsp:include page="/fragment/footer.jsp"></jsp:include>
		</div>
	</div>

	<jsp:include page="/fragment/scroll-to-top.html"></jsp:include>
	<jsp:include page="/fragment/logoutModel.jsp"></jsp:include>
	<jsp:include page="/fragment/jsResource.jsp"></jsp:include>

	<!-- <script src='Schedule/scr/js/fullcalendar.js'></script> -->
	<!--  <div id='classSelectorField'>
		<p>
			<b>Choose a class: </b>
		</p>
		<select id="showClass"></select>
	</div>
	<div id='courseSeletorField'>
		<p>
			<b>Choose a course: </b>
		</p>-->
	<!-- <select id="showCourses"></select> -->
	<!--</div>-->
	<div id='calendar'></div>
	<script>
	var courseListContent;
		$.ajax({
			url : "../GetClass",
			type : "GET",
			success : function(data) {
				getClassList(data);
			}
		});

		function getClassList(data) {
			//console.log(data);
			var dataO = JSON.parse(data);
			//console.log(typeof (dataO));
			$("#showClass").html("");
			$("#showClass").append($("<option>").text("--請選擇--"));
			for ( var i in dataO) {
				var opt = $("<option>").val(dataO[i].eduProgramTypeName).text(
						dataO[i].ClasPeriodId);
				$("#showClass").append(opt);
			}
		}

/*
		$("#showClass").change(function() {
			var id = $("#showClass :selected").text();
			$.ajax({
				url : "../../GetCourses?classPeriodId=" + id,
				type : "GET",
				success : function(data) {
					getCourseList(data);
					setCourseListContent(data);
				}
			});
		});
*/
		function setCourseListContent(data){
			courseListContent = data;
		}
		
		function getCourseListContent(){
			return JSON.parse(courseListContent);
		}
		
/*		function getCourseList(data) {
			var dataO = JSON.parse(data);
			//console.log(typeof (dataO));
			$("#showCourses").html("");
			for ( var i in dataO) {
				var opt = $("<option>").val(dataO[i].courseId).text(
						dataO[i].courseName + "(" + dataO[i].courseHour + ")");
				$("#showCourses").append(opt);
			}
		}
*/		
	    //避開Chrome Listener的問題(會大幅度拖慢效能..暫時解決？？？)
	    var passiveSupported = false;
	    // try {
	    //     var options = Object.defineProperty({}, "passive", {
	    //         get: function() {
	    //             passiveSupported = true;
	    //         }
	    //     });
	    //     window.addEventListener("touchmove", null, options);
	    //     window.addEventListener("wheel", null, options);
	    //     window.addEventListener("mousewheel", null, options);
	    //     window.addEventListener("touchstart", null, options);
	    //     window.addEventListener("some", null, options);
	    // // } catch (err) {}
	    // document.addEventListener("touchmove", passiveSupported ? { passive: true } : false);
	    // document.addEventListener("wheel", passiveSupported ? { passive: true } : false);
	    // document.addEventListener("mousewheel", passiveSupported ? { passive: true } : false);
	    // document.addEventListener("touchstart", passiveSupported ? { passive: true } : false);
	    // document.addEventListener("some", passiveSupported ? { passive: true } : false);

	    document.addEventListener("touchmove",{ passive: true });
	    document.addEventListener("wheel",{ passive: true });
	    document.addEventListener("mousewheel",{ passive: true });
	    document.addEventListener("touchstart",{ passive: true });
	    document.addEventListener("some",{ passive: true });
	    
	    //控制早中晚按鈕出現(1214晚)
	    function timeButton() {
	        document.getElementsByClassName("fc-mornButton-button")[0].style.visibility = "visible";
	        document.getElementsByClassName("fc-afterButton-button")[0].style.visibility = "visible";
	        document.getElementsByClassName("fc-nightButton-button")[0].style.visibility = "visible";
	    }


	    //按鈕點擊隱藏另外一個(更動js檔的6089)
	    function dpButton(Button) {
	        if (Button === "prev") {
	            document.getElementById("prev").style.visibility = "hidden";
	            document.getElementById("next").style.visibility = "visible";
	        } else if (Button === "next") {
	            document.getElementById("next").style.visibility = "hidden";
	            document.getElementById("prev").style.visibility = "visible";
	        }
	    }
	    
	</script>
</body>
</html>