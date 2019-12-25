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
<link href='Schedule/scr/css/fullcalendar.css' rel='stylesheet' />
<link href='Schedule/scr/css/fullcalendar.print.css' rel='stylesheet'
	media='print' />
<link href="calendar/scr/css/cal.css" rel="stylesheet">

<script src='scr/js/core.js'></script>
<script src='scr/js/interaction.js'></script>
<script src='scr/js/daygrid.js'></script>
<script src='scr/js/timegrid.js'></script>
<script src='scr/js/jquery.min.js'></script>
<script src='scr/js/moment.min.js'></script>
<script>
	//使用moment.js拿到現在時間，塞進去
	var today = moment().format('YYYY-MM-DD');
	var flag = true;

	//calender區
	document.addEventListener('DOMContentLoaded', function() {
		var calendarEl = document.getElementById('calendar');
		var eventStartTime, eventEndTime, eventId;

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

			//在選擇日期時的動作(1214)
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
					}
					
					flag=true;

				}
				if (flag) {
					calendar.addEvent({
						title : title,
						start : arg.startStr + "T" + eventStartTime,
						end : arg.startStr + "T" + eventEndTime,
						id : eventid,
					});
					flag = false;
				}

				calendar.unselect()
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
						//這個是課程的Id
						
						;
					},

				},

				afterButton : {
					text : '中',
					click : function() {
						eventStartTime = "14:00:00";
						eventEndTime = "17:00:00";
						//這個是課程的Id
						
						;
					},
				},

				nightButton : {
					text : '晚',
					click : function() {
						eventStartTime = "18:00:00";
						eventEndTime = "21:00:00";
						//這個是課程的Id
						
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
						dataO[i].courseName + "(" + dataO[i].courseHour + ")");
				$("#showCourses").append(opt);
			}
		}
		
		$("#showClass").change(function() {
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
		
		//
		
	});
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
	</div>

	<jsp:include page="/fragment/scroll-to-top.html"></jsp:include>
	<jsp:include page="/fragment/logoutModel.jsp"></jsp:include>
	<jsp:include page="/fragment/jsResource.jsp"></jsp:include>

	<script src='Schedule/scr/js/moment.min.js'></script>
	<script src='Schedule/scr/js/fullcalendar.js'></script>
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
	    
	    
	    var JSONDEMO=[
	    	{
				title:'Java(54)',
				id:1,
				start:'2019-12-01T09:00:00',
				end:'2019-12-01T12:00:00'
			},{
				title:'Java(54)',
				id:1,
				start:'2019-12-01T14:00:00',
				end:'2019-12-01T17:00:00'
			},{
				title:'Java(54)',
				id:1,
				start:'2019-12-01T18:00:00',
				end:'2019-12-01T21:00:00'
			},
			//---------
			{
				title:'Java(54)',
				id:1,
				start:'2019-12-02T09:00:00',
				end:'2019-12-02T12:00:00'
			},{
				title:'Java(54)',
				id:1,
				start:'2019-12-02T14:00:00',
				end:'2019-12-02T17:00:00'
			},{
				title:'Java(54)',
				id:1,
				start:'2019-12-02T18:00:00',
				end:'2019-12-02T21:00:00'
			},
			//---------
			{
				title:'Java(54)',
				id:1,
				start:'2019-12-03T09:00:00',
				end:'2019-12-03T12:00:00'
			},{
				title:'Java(54)',
				id:1,
				start:'2019-12-03T14:00:00',
				end:'2019-12-03T17:00:00'
			},{
				title:'Java(54)',
				id:1,
				start:'2019-12-03T18:00:00',
				end:'2019-12-03T21:00:00'
			},
			//---------
			{
				title:'Java(54)',
				id:1,
				start:'2019-12-04T09:00:00',
				end:'2019-12-04T12:00:00'
			},{
				title:'Java(54)',
				id:1,
				start:'2019-12-04T14:00:00',
				end:'2019-12-04T17:00:00'
			},{
				title:'Java(54)',
				id:1,
				start:'2019-12-04T18:00:00',
				end:'2019-12-04T21:00:00'
			},
			//---------
			{
				title:'None(200)',
				id:-1,
				start:'2019-12-05T09:00:00',
				end:'2019-12-05T12:00:00'
			},{
				title:'None(200)',
				id:-1,
				start:'2019-12-05T14:00:00',
				end:'2019-12-05T17:00:00'
			},{
				title:'None(200)',
				id:-1,
				start:'2019-12-05T18:00:00',
				end:'2019-12-05T21:00:00'
			},
			//===============================
			{
				title:'Java(54)',
				id:1,
				start:'2019-12-06T09:00:00',
				end:'2019-12-06T12:00:00'
			},{
				title:'Java(54)',
				id:1,
				start:'2019-12-06T14:00:00',
				end:'2019-12-06T17:00:00'
			},{
				title:'Java(54)',
				id:1,
				start:'2019-12-06T18:00:00',
				end:'2019-12-06T21:00:00'
			},
			//---------
			{
				title:'Java(54)',
				id:1,
				start:'2019-12-07T09:00:00',
				end:'2019-12-07T12:00:00'
			},{
				title:'Java(54)',
				id:1,
				start:'2019-12-07T14:00:00',
				end:'2019-12-07T17:00:00'
			},{
				title:'Java(54)',
				id:1,
				start:'2019-12-07T18:00:00',
				end:'2019-12-07T21:00:00'
			},
			//---------
			{
				title:'Java(54)',
				id:1,
				start:'2019-12-08T09:00:00',
				end:'2019-12-08T12:00:00'
			},{
				title:'Java(54)',
				id:1,
				start:'2019-12-08T14:00:00',
				end:'2019-12-08T17:00:00'
			},{
				title:'Java(54)',
				id:1,
				start:'2019-12-08T18:00:00',
				end:'2019-12-08T21:00:00'
			},
			//---------
			{
				title:'Java(54)',
				id:1,
				start:'2019-12-09T09:00:00',
				end:'2019-12-04912:00:00'
			},{
				title:'Java(54)',
				id:1,
				start:'2019-12-09T14:00:00',
				end:'2019-12-09T17:00:00'
			},{
				title:'Java(54)',
				id:1,
				start:'2019-12-09T18:00:00',
				end:'2019-12-09T21:00:00'
			},
			//---------
			{
				title:'Java(54)',
				id:1,
				start:'2019-12-10T09:00:00',
				end:'2019-12-10T12:00:00'
			},{
				title:'Java(54)',
				id:1,
				start:'2019-12-10T14:00:00',
				end:'2019-12-10T17:00:00'
			},{
				title:'Java(54)',
				id:1,
				start:'2019-12-10T18:00:00',
				end:'2019-12-10T21:00:00'
			},
			//===============================20
			{
				title:'Java(54)',
				id:1,
				start:'2019-12-11T09:00:00',
				end:'2019-12-11T12:00:00'
			},{
				title:'Java(54)',
				id:1,
				start:'2019-12-11T14:00:00',
				end:'2019-12-11T17:00:00'
			},{
				title:'Java(54)',
				id:1,
				start:'2019-12-11T18:00:00',
				end:'2019-12-11T21:00:00'
			},
			//---------
			{
				title:'Java(54)',
				id:1,
				start:'2019-12-12T09:00:00',
				end:'2019-12-12T12:00:00'
			},{
				title:'Java(54)',
				id:1,
				start:'2019-12-12T14:00:00',
				end:'2019-12-12T17:00:00'
			},{
				title:'Java(54)',
				id:1,
				start:'2019-12-12T18:00:00',
				end:'2019-12-12T21:00:00'
			},
			//---------
			{
				title:'Java(54)',
				id:1,
				start:'2019-12-13T09:00:00',
				end:'2019-12-13T12:00:00'
			},{
				title:'Java(54)',
				id:1,
				start:'2019-12-13T14:00:00',
				end:'2019-12-13T17:00:00'
			},{
				title:'Java(54)',
				id:1,
				start:'2019-12-13T18:00:00',
				end:'2019-12-13T21:00:00'
			},
			//---------
			{
				title:'Java(54)',
				id:1,
				start:'2019-12-14T09:00:00',
				end:'2019-12-14T12:00:00'
			},{
				title:'Java(54)',
				id:1,
				start:'2019-12-14T14:00:00',
				end:'2019-12-14T17:00:00'
			},{
				title:'Java(54)',
				id:1,
				start:'2019-12-14T18:00:00',
				end:'2019-12-14T21:00:00'
			},
			//---------
			{
				title:'Java(54)',
				id:1,
				start:'2019-12-15T09:00:00',
				end:'2019-12-15T12:00:00'
			},{
				title:'Java(54)',
				id:1,
				start:'2019-12-15T14:00:00',
				end:'2019-12-15T17:00:00'
			},{
				title:'Java(54)',
				id:1,
				start:'2019-12-15T18:00:00',
				end:'2019-12-15T21:00:00'
			},
			//===============================
			{
				title:'Java(54)',
				id:1,
				start:'2019-12-16T09:00:00',
				end:'2019-12-16T12:00:00'
			},{
				title:'Java(54)',
				id:1,
				start:'2019-12-16T14:00:00',
				end:'2019-12-16T17:00:00'
			},{
				title:'Java(54)',
				id:1,
				start:'2019-12-16T18:00:00',
				end:'2019-12-16T21:00:00'
			},
			//---------
			{
				title:'Java(54)',
				id:1,
				start:'2019-12-17T09:00:00',
				end:'2019-12-17T12:00:00'
			},{
				title:'Java(54)',
				id:1,
				start:'2019-12-17T14:00:00',
				end:'2019-12-17T17:00:00'
			},{
				title:'Java(54)',
				id:1,
				start:'2019-12-17T18:00:00',
				end:'2019-12-17T21:00:00'
			},
			//---------
			{
				title:'Java(54)',
				id:1,
				start:'2019-12-18T09:00:00',
				end:'2019-12-18T12:00:00'
			},{
				title:'Java(54)',
				id:1,
				start:'2019-12-18T14:00:00',
				end:'2019-12-18T17:00:00'
			},{
				title:'Java(54)',
				id:1,
				start:'2019-12-18T18:00:00',
				end:'2019-12-18T21:00:00'
			},
			//---------
			{
				title:'Lab(200)',
				id:0,
				start:'2019-12-19T09:00:00',
				end:'2019-12-19T12:00:00'
			},{
				title:'Lab(200)',
				id:0,
				start:'2019-12-19T14:00:00',
				end:'2019-12-19T17:00:00'
			},{
				title:'Lab(200)',
				id:0,
				start:'2019-12-19T18:00:00',
				end:'2019-12-19T21:00:00'
			},
			//---------
			{
				title:'SQL(30)',
				id:2,
				start:'2019-12-20T09:00:00',
				end:'2019-12-20T12:00:00'
			},{
				title:'SQL(30)',
				id:2,
				start:'2019-12-20T14:00:00',
				end:'2019-12-20T17:00:00'
			},{
				title:'SQL(30)',
				id:2,
				start:'2019-12-20T18:00:00',
				end:'2019-12-20T21:00:00'
			},
			//===============================10
			{
				title:'SQL(30)',
				id:2,
				start:'2019-12-21T09:00:00',
				end:'2019-12-21T12:00:00'
			},{
				title:'SQL(30)',
				id:2,
				start:'2019-12-21T14:00:00',
				end:'2019-12-21T17:00:00'
			},{
				title:'SQL(30)',
				id:2,
				start:'2019-12-21T18:00:00',
				end:'2019-12-21T21:00:00'
			},
			//---------
			{
				title:'SQL(30)',
				id:2,
				start:'2019-12-22T09:00:00',
				end:'2019-12-22T12:00:00'
			},{
				title:'SQL(30)',
				id:2,
				start:'2019-12-22T14:00:00',
				end:'2019-12-22T17:00:00'
			},{
				title:'SQL(30)',
				id:2,
				start:'2019-12-22T18:00:00',
				end:'2019-12-22T21:00:00'
			},
			//---------
			{
				title:'SQL(30)',
				id:2,
				start:'2019-12-23T09:00:00',
				end:'2019-12-23T12:00:00'
			},{
				title:'Lab(200)',
				id:0,
				start:'2019-12-23T14:00:00',
				end:'2019-12-23T17:00:00'
			},{
				title:'Lab(200)',
				id:0,
				start:'2019-12-23T18:00:00',
				end:'2019-12-23T21:00:00'
			},
			//---------
			{
				title:'JDBC(18)',
				id:5,
				start:'2019-12-24T09:00:00',
				end:'2019-12-24T12:00:00'
			},{
				title:'JDBC(18)',
				id:5,
				start:'2019-12-24T14:00:00',
				end:'2019-12-24T17:00:00'
			},{
				title:'JDBC(18)',
				id:5,
				start:'2019-12-24T18:00:00',
				end:'2019-12-24T21:00:00'
			},
			//---------
			{
				title:'JDBC(18)',
				id:5,
				start:'2019-12-25T09:00:00',
				end:'2019-12-25T12:00:00'
			},{
				title:'JDBC(18)',
				id:5,
				start:'2019-12-25T14:00:00',
				end:'2019-12-25T17:00:00'
			},{
				title:'JDBC(18)',
				id:5,
				start:'2019-12-25T18:00:00',
				end:'2019-12-25T21:00:00'
			},
			//===============================
			{
				title:'JavaScript(18)',
				id:6,
				start:'2019-12-26T09:00:00',
				end:'2019-12-26T12:00:00'
			},{
				title:'JavaScript(18)',
				id:6,
				start:'2019-12-26T14:00:00',
				end:'2019-12-26T17:00:00'
			},{
				title:'JavaScript(18)',
				id:6,
				start:'2019-12-26T18:00:00',
				end:'2019-12-26T21:00:00'
			},
			//---------
			{
				title:'JavaScript(18)',
				id:6,
				start:'2019-12-27T09:00:00',
				end:'2019-12-27T12:00:00'
			},{
				title:'JavaScript(18)',
				id:6,
				start:'2019-12-27T14:00:00',
				end:'2019-12-27T17:00:00'
			},{
				title:'JavaScript(18)',
				id:6,
				start:'2019-12-27T18:00:00',
				end:'2019-12-27T21:00:00'
			},
			//---------
			{
				title:'SQL實作(24)',
				id:3,
				start:'2019-12-28T09:00:00',
				end:'2019-12-28T12:00:00'
			},{
				title:'SQL實作(24)',
				id:3,
				start:'2019-12-28T14:00:00',
				end:'2019-12-28T17:00:00'
			},{
				title:'SQL實作(24)',
				id:3,
				start:'2019-12-28T18:00:00',
				end:'2019-12-28T21:00:00'
			},
			//---------
			{
				title:'Lab(200)',
				id:0,
				start:'2019-12-29T09:00:00',
				end:'2019-12-29T12:00:00'
			},{
				title:'Lab(200)',
				id:0,
				start:'2019-12-29T14:00:00',
				end:'2019-12-29T17:00:00'
			},{
				title:'None(200)',
				id:-1,
				start:'2019-12-29T18:00:00',
				end:'2019-12-29T21:00:00'
			},
			//---------
			{
				title:'SQL實作(24)',
				id:3,
				start:'2019-12-30T09:00:00',
				end:'2019-12-30T12:00:00'
			},{
				title:'SQL實作(24)',
				id:3,
				start:'2019-12-30T14:00:00',
				end:'2019-12-30T17:00:00'
			},{
				title:'SQL實作(24)',
				id:3,
				start:'2019-12-30T18:00:00',
				end:'2019-12-30T21:00:00'
			},
			//===============================10
			{
				title:'None(200)',
				id:-1,
				start:'2019-12-31T09:00:00',
				end:'2019-12-31T12:00:00'
			},{
				title:'None(200)',
				id:-1,
				start:'2019-12-31T14:00:00',
				end:'2019-12-31T17:00:00'
			},{
				title:'None(200)',
				id:-1,
				start:'2019-12-31T18:00:00',
				end:'2019-12-31T21:00:00'
			},{
			title:'Java(54)',
			id:1,
			start:'2020-01-01T09:00:00',
			end:'2020-01-01T12:00:00'
		},{
			title:'Java(54)',
			id:1,
			start:'2020-01-01T14:00:00',
			end:'2020-01-01T17:00:00'
		},{
			title:'Java(54)',
			id:1,
			start:'2020-01-01T18:00:00',
			end:'2020-01-01T21:00:00'
		},
		//---------
		{
			title:'Java(54)',
			id:1,
			start:'2020-01-02T09:00:00',
			end:'2020-01-02T12:00:00'
		},{
			title:'Java(54)',
			id:1,
			start:'2020-01-02T14:00:00',
			end:'2020-01-02T17:00:00'
		},{
			title:'Java(54)',
			id:1,
			start:'2020-01-02T18:00:00',
			end:'2020-01-02T21:00:00'
		},
		//---------
		{
			title:'Java(54)',
			id:1,
			start:'2020-01-03T09:00:00',
			end:'2020-01-03T12:00:00'
		},{
			title:'Java(54)',
			id:1,
			start:'2020-01-03T14:00:00',
			end:'2020-01-03T17:00:00'
		},{
			title:'Java(54)',
			id:1,
			start:'2020-01-03T18:00:00',
			end:'2020-01-03T21:00:00'
		},
		//---------
		{
			title:'Java(54)',
			id:1,
			start:'2020-01-04T09:00:00',
			end:'2020-01-04T12:00:00'
		},{
			title:'Java(54)',
			id:1,
			start:'2020-01-04T14:00:00',
			end:'2020-01-04T17:00:00'
		},{
			title:'Java(54)',
			id:1,
			start:'2020-01-04T18:00:00',
			end:'2020-01-04T21:00:00'
		},
		//---------
		{
			title:'None(200)',
			id:-1,
			start:'2020-01-05T09:00:00',
			end:'2020-01-05T12:00:00'
		},{
			title:'None(200)',
			id:-1,
			start:'2020-01-05T14:00:00',
			end:'2020-01-05T17:00:00'
		},{
			title:'None(200)',
			id:-1,
			start:'2020-01-05T18:00:00',
			end:'2020-01-05T21:00:00'
		},
		//===============================
		{
			title:'Java(54)',
			id:1,
			start:'2020-01-06T09:00:00',
			end:'2020-01-06T12:00:00'
		},{
			title:'Java(54)',
			id:1,
			start:'2020-01-06T14:00:00',
			end:'2020-01-06T17:00:00'
		},{
			title:'Java(54)',
			id:1,
			start:'2020-01-06T18:00:00',
			end:'2020-01-06T21:00:00'
		},
		//---------
		{
			title:'Java(54)',
			id:1,
			start:'2020-01-07T09:00:00',
			end:'2020-01-07T12:00:00'
		},{
			title:'Java(54)',
			id:1,
			start:'2020-01-07T14:00:00',
			end:'2020-01-07T17:00:00'
		},{
			title:'Java(54)',
			id:1,
			start:'2020-01-07T18:00:00',
			end:'2020-01-07T21:00:00'
		},
		//---------
		{
			title:'Java(54)',
			id:1,
			start:'2020-01-08T09:00:00',
			end:'2020-01-08T12:00:00'
		},{
			title:'Java(54)',
			id:1,
			start:'2020-01-08T14:00:00',
			end:'2020-01-08T17:00:00'
		},{
			title:'Java(54)',
			id:1,
			start:'2020-01-08T18:00:00',
			end:'2020-01-08T21:00:00'
		},
		//---------
		{
			title:'Java(54)',
			id:1,
			start:'2020-01-09T09:00:00',
			end:'2020-01-04912:00:00'
		},{
			title:'Java(54)',
			id:1,
			start:'2020-01-09T14:00:00',
			end:'2020-01-09T17:00:00'
		},{
			title:'Java(54)',
			id:1,
			start:'2020-01-09T18:00:00',
			end:'2020-01-09T21:00:00'
		},
		//---------
		{
			title:'Java(54)',
			id:1,
			start:'2020-01-10T09:00:00',
			end:'2020-01-10T12:00:00'
		},{
			title:'Java(54)',
			id:1,
			start:'2020-01-10T14:00:00',
			end:'2020-01-10T17:00:00'
		},{
			title:'Java(54)',
			id:1,
			start:'2020-01-10T18:00:00',
			end:'2020-01-10T21:00:00'
		},
		//===============================20
		{
			title:'Java(54)',
			id:1,
			start:'2020-01-11T09:00:00',
			end:'2020-01-11T12:00:00'
		},{
			title:'Java(54)',
			id:1,
			start:'2020-01-11T14:00:00',
			end:'2020-01-11T17:00:00'
		},{
			title:'Java(54)',
			id:1,
			start:'2020-01-11T18:00:00',
			end:'2020-01-11T21:00:00'
		},
		//---------
		{
			title:'Java(54)',
			id:1,
			start:'2020-01-12T09:00:00',
			end:'2020-01-12T12:00:00'
		},{
			title:'Java(54)',
			id:1,
			start:'2020-01-12T14:00:00',
			end:'2020-01-12T17:00:00'
		},{
			title:'Java(54)',
			id:1,
			start:'2020-01-12T18:00:00',
			end:'2020-01-12T21:00:00'
		},
		//---------
		{
			title:'Java(54)',
			id:1,
			start:'2020-01-13T09:00:00',
			end:'2020-01-13T12:00:00'
		},{
			title:'Java(54)',
			id:1,
			start:'2020-01-13T14:00:00',
			end:'2020-01-13T17:00:00'
		},{
			title:'Java(54)',
			id:1,
			start:'2020-01-13T18:00:00',
			end:'2020-01-13T21:00:00'
		},
		//---------
		{
			title:'Java(54)',
			id:1,
			start:'2020-01-14T09:00:00',
			end:'2020-01-14T12:00:00'
		},{
			title:'Java(54)',
			id:1,
			start:'2020-01-14T14:00:00',
			end:'2020-01-14T17:00:00'
		},{
			title:'Java(54)',
			id:1,
			start:'2020-01-14T18:00:00',
			end:'2020-01-14T21:00:00'
		},
		//---------
		{
			title:'Java(54)',
			id:1,
			start:'2020-01-15T09:00:00',
			end:'2020-01-15T12:00:00'
		},{
			title:'Java(54)',
			id:1,
			start:'2020-01-15T14:00:00',
			end:'2020-01-15T17:00:00'
		},{
			title:'Java(54)',
			id:1,
			start:'2020-01-15T18:00:00',
			end:'2020-01-15T21:00:00'
		},
		//===============================
		{
			title:'Java(54)',
			id:1,
			start:'2020-01-16T09:00:00',
			end:'2020-01-16T12:00:00'
		},{
			title:'Java(54)',
			id:1,
			start:'2020-01-16T14:00:00',
			end:'2020-01-16T17:00:00'
		},{
			title:'Java(54)',
			id:1,
			start:'2020-01-16T18:00:00',
			end:'2020-01-16T21:00:00'
		},
		//---------
		{
			title:'Java(54)',
			id:1,
			start:'2020-01-17T09:00:00',
			end:'2020-01-17T12:00:00'
		},{
			title:'Java(54)',
			id:1,
			start:'2020-01-17T14:00:00',
			end:'2020-01-17T17:00:00'
		},{
			title:'Java(54)',
			id:1,
			start:'2020-01-17T18:00:00',
			end:'2020-01-17T21:00:00'
		},
		//---------
		{
			title:'Java(54)',
			id:1,
			start:'2020-01-18T09:00:00',
			end:'2020-01-18T12:00:00'
		},{
			title:'Java(54)',
			id:1,
			start:'2020-01-18T14:00:00',
			end:'2020-01-18T17:00:00'
		},{
			title:'Java(54)',
			id:1,
			start:'2020-01-18T18:00:00',
			end:'2020-01-18T21:00:00'
		},
		//---------
		{
			title:'Lab(200)',
			id:0,
			start:'2020-01-19T09:00:00',
			end:'2020-01-19T12:00:00'
		},{
			title:'Lab(200)',
			id:0,
			start:'2020-01-19T14:00:00',
			end:'2020-01-19T17:00:00'
		},{
			title:'Lab(200)',
			id:0,
			start:'2020-01-19T18:00:00',
			end:'2020-01-19T21:00:00'
		},
		//---------
		{
			title:'SQL(30)',
			id:2,
			start:'2020-01-20T09:00:00',
			end:'2020-01-20T12:00:00'
		},{
			title:'SQL(30)',
			id:2,
			start:'2020-01-20T14:00:00',
			end:'2020-01-20T17:00:00'
		},{
			title:'SQL(30)',
			id:2,
			start:'2020-01-20T18:00:00',
			end:'2020-01-20T21:00:00'
		},
		//===============================10
		{
			title:'SQL(30)',
			id:2,
			start:'2020-01-21T09:00:00',
			end:'2020-01-21T12:00:00'
		},{
			title:'SQL(30)',
			id:2,
			start:'2020-01-21T14:00:00',
			end:'2020-01-21T17:00:00'
		},{
			title:'SQL(30)',
			id:2,
			start:'2020-01-21T18:00:00',
			end:'2020-01-21T21:00:00'
		},
		//---------
		{
			title:'SQL(30)',
			id:2,
			start:'2020-01-22T09:00:00',
			end:'2020-01-22T12:00:00'
		},{
			title:'SQL(30)',
			id:2,
			start:'2020-01-22T14:00:00',
			end:'2020-01-22T17:00:00'
		},{
			title:'SQL(30)',
			id:2,
			start:'2020-01-22T18:00:00',
			end:'2020-01-22T21:00:00'
		},
		//---------
		{
			title:'SQL(30)',
			id:2,
			start:'2020-01-23T09:00:00',
			end:'2020-01-23T12:00:00'
		},{
			title:'Lab(200)',
			id:0,
			start:'2020-01-23T14:00:00',
			end:'2020-01-23T17:00:00'
		},{
			title:'Lab(200)',
			id:0,
			start:'2020-01-23T18:00:00',
			end:'2020-01-23T21:00:00'
		},
		//---------
		{
			title:'JDBC(18)',
			id:5,
			start:'2020-01-24T09:00:00',
			end:'2020-01-24T12:00:00'
		},{
			title:'JDBC(18)',
			id:5,
			start:'2020-01-24T14:00:00',
			end:'2020-01-24T17:00:00'
		},{
			title:'JDBC(18)',
			id:5,
			start:'2020-01-24T18:00:00',
			end:'2020-01-24T21:00:00'
		},
		//---------
		{
			title:'JDBC(18)',
			id:5,
			start:'2020-01-25T09:00:00',
			end:'2020-01-25T12:00:00'
		},{
			title:'JDBC(18)',
			id:5,
			start:'2020-01-25T14:00:00',
			end:'2020-01-25T17:00:00'
		},{
			title:'JDBC(18)',
			id:5,
			start:'2020-01-25T18:00:00',
			end:'2020-01-25T21:00:00'
		},
		//===============================
		{
			title:'JavaScript(18)',
			id:6,
			start:'2020-01-26T09:00:00',
			end:'2020-01-26T12:00:00'
		},{
			title:'JavaScript(18)',
			id:6,
			start:'2020-01-26T14:00:00',
			end:'2020-01-26T17:00:00'
		},{
			title:'JavaScript(18)',
			id:6,
			start:'2020-01-26T18:00:00',
			end:'2020-01-26T21:00:00'
		},
		//---------
		{
			title:'JavaScript(18)',
			id:6,
			start:'2020-01-27T09:00:00',
			end:'2020-01-27T12:00:00'
		},{
			title:'JavaScript(18)',
			id:6,
			start:'2020-01-27T14:00:00',
			end:'2020-01-27T17:00:00'
		},{
			title:'JavaScript(18)',
			id:6,
			start:'2020-01-27T18:00:00',
			end:'2020-01-27T21:00:00'
		},
		//---------
		{
			title:'SQL實作(24)',
			id:3,
			start:'2020-01-28T09:00:00',
			end:'2020-01-28T12:00:00'
		},{
			title:'SQL實作(24)',
			id:3,
			start:'2020-01-28T14:00:00',
			end:'2020-01-28T17:00:00'
		},{
			title:'SQL實作(24)',
			id:3,
			start:'2020-01-28T18:00:00',
			end:'2020-01-28T21:00:00'
		},
		//---------
		{
			title:'Lab(200)',
			id:0,
			start:'2020-01-29T09:00:00',
			end:'2020-01-29T12:00:00'
		},{
			title:'Lab(200)',
			id:0,
			start:'2020-01-29T14:00:00',
			end:'2020-01-29T17:00:00'
		},{
			title:'None(200)',
			id:-1,
			start:'2020-01-29T18:00:00',
			end:'2020-01-29T21:00:00'
		},
		//---------
		{
			title:'SQL實作(24)',
			id:3,
			start:'2020-01-30T09:00:00',
			end:'2020-01-30T12:00:00'
		},{
			title:'SQL實作(24)',
			id:3,
			start:'2020-01-30T14:00:00',
			end:'2020-01-30T17:00:00'
		},{
			title:'SQL實作(24)',
			id:3,
			start:'2020-01-30T18:00:00',
			end:'2020-01-30T21:00:00'
		},
		//===============================10
		{
			title:'None(200)',
			id:-1,
			start:'2020-01-31T09:00:00',
			end:'2020-01-31T12:00:00'
		},{
			title:'None(200)',
			id:-1,
			start:'2020-01-31T14:00:00',
			end:'2020-01-31T17:00:00'
		},{
			title:'None(200)',
			id:-1,
			start:'2020-01-31T18:00:00',
			end:'2020-01-31T21:00:00'
		}
		
		];
	    
	</script>
</body>
</html>