
var today = moment().format("YYYY-MM-DD");
$(document).ready(function() {
  $("#calendar").fullCalendar({
    header: {
      left: "prev,next /*today*/",
      center: "title",
      right: "month,basicWeek,/*basicDay*/"
    },
    defaultDate: today,
    navLinks: false, // can click day/week names to navigate views
    editable: false,
    eventLimit: false, // allow "more" link when too many events
    events: [
      {
        id: 9333,
        title: "All Day Event",
        start: "2019-12-01"
      },
      {
        id: 123,
        title: "Long Event",
        start: "2019-12-03",
        end: "2019-12-05"
      },
      {
        id: 1,
        title: "Repeating Event",
        start: "2019-12-09T16:00:00"
      },
      {
        id: 2,
        title: "Repeating Event",
        start: "2019-12-10T09:00:00"
      },
      {
        id: 77,
        title: "Conference",
        start: "2019-12-15",
        end: "2019-12-16"
      },
      {
        title: "Google",
        id: 999,
        start: "2019-12-28T09:00:00"
      }
    ]
  });
});

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

  if ($("#content_cal").remove() !== null) {
    //這個判斷式可以讓user直接點擊任一課程就會直接跳出該課程popUp
    $("#content_cal").remove();
  }
  popUp();
  setJson(id);
}

function popUp() {
  if (document.getElementById("content_cal") === null) {
    var pDiv = document.createElement("div"); //生成popUp之div
    pDiv.setAttribute("id", "content_cal"); //賦予上面生成的div標籤一個id
    var xButton = document.createElement("button"); //生成button
    xButton.innerText = "X"; //把按鈕的value設為X，以看起來有叉叉的效果
    xButton.setAttribute("onmouseup", "removePdiv()"); //定義點擊要觸發的function
    pDiv.append(xButton); //把按鈕塞進xDiv內
    xButton.setAttribute("id", "xButton"); //賦予按鈕顯示的X
    document.getElementById("calendar").append(pDiv); //把 pDiv 塞進 calendar div下
    positionHandler(); // 讓彈出視窗的位置在滑鼠點擊位置
  } else {
    $("#content_cal").remove();
  }
}

function removePdiv() {
  $("#content_cal").remove();
}

function positionHandler() {
  /*設定置中的函式*/
  var myTop = window.event.y; /*取得垂直中央位置*/
  var myLeft = window.event.x; /*取得水平中央位置*/
  $("#content_cal").offset({
    top: myTop,
    left: myLeft
  }); /*設定區塊於水平與垂直置中*/
}

function setJson(id) {
  var jsonDiv_1 = document.createElement("div");
  jsonDiv_1.setAttribute("id", "json1");
  document.getElementById("content_cal").append(jsonDiv_1);

  var jsonDiv_2 = document.createElement("div");
  jsonDiv_2.setAttribute("id", "json2");
  document.getElementById("content_cal").append(jsonDiv_2);

  var jsonDiv_3 = document.createElement("div");
  jsonDiv_3.setAttribute("id", "json3");
  document.getElementById("content_cal").append(jsonDiv_3);

  var x = document.getElementById("json1");
  var y = document.getElementById("json2");
  var z = document.getElementById("json3");

  var xhttp = new XMLHttpRequest();
  xhttp.onload = function() {
    if (this.readyState == 4 && this.status == 200) {
      rText = this.responseText;
      rJSON = JSON.parse(rText);
      x.innerText = "課程名稱：" + rJSON.courseName;
      y.innerText = "授課老師：" + rJSON.teacher;
      z.innerText = "課程簡介：" + rJSON.briefInfo;
    } else {
      x.innerText = "無法連上伺服器";
    }
  };
  xhttp.open("GET", "test.json", true); //中間的參數名稱表示要讀入的json字串
  xhttp.send();
}
