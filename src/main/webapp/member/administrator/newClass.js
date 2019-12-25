(function() {
  "use strict";
  window.addEventListener(
    "load",
    function() {
      // 取得form ，驗證表單
      var forms = document.getElementsByClassName("needs-validation");
      // Loop over them and prevent submission
      var validation = Array.prototype.filter.call(forms, function(form) {
        form.addEventListener(
          "submit",
          function(event) {
            // 驗證是否為空欄位
            if (form.checkValidity() === false) {
              event.preventDefault();
              event.stopPropagation();
              form.classList.add("was-validated");
            } 
//              else if (new Date() < $("#startDate").val() ||$("#endDate")< new Date() ) {
//              event.preventDefault();
//              event.stopPropagation();
//              alert("開始日期/結束日期不得早於今天");
//            } else if ($("#startDate").val() > $("#endDate").val()) {
//              event.preventDefault();
//              event.stopPropagation();
//              alert("開始日期不得晚於結束日期");
//            } 
            else {
              // 將資料存入JSON中
              let item = {};
              item["program"] = $("#program").val();
              item["period"] = $("#period").val();
              item["startDate"] = $("#startDate").val();
              item["endDate"] = $("#endDate").val();
              item["classRoom"] = $("#classRoom").val();
              $("#obj").val(JSON.stringify(item));
            }
          },
          false
        );
      });
    },
    false
  );
})();

// 載入頁面時要求選擇器資料
(function() {
  $.ajax({
    url: "http://localhost:8080/ProjectEduSystem/ProgramSeq",
    type: "GET",
    dataType: "json",
    error: function(){
    	alert("AAAAAA");
    },
    success: function(result) {
      let str = "<option selected>請選擇學程</option>";
      for (let i = 0; i < result.length; i++) {
        data = result[i].eduProgramTypeName;
        str += `<option value ="${data}" data-id="${i}">${data}</option>`;
      }
      $("#program").html(str);
      // 監聽變換事件
      $("#program").change(function(e) {
        // console.log(e.target.options[event.target.selectedIndex].dataset.id);
        // 使用選取到的option位置去找到物件中的number Value
        // 並塞到下個input中
        // 下期數字為撈到的數字去加一
        $("#period").val(
          result[e.target.selectedIndex - 1].eduProgramNumber + 1
        );
      });
    }
  });
})();
