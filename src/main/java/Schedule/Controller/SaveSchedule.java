package Schedule.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import Schedule.Service.ClassCourseService;
import Schedule.Service.IClassCourseService;
import Schedule.Service.IScheduleService;
import Schedule.Service.ScheduleService;

@WebServlet("/SaveSchedule")
public class SaveSchedule extends HttpServlet{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		String test = request.getParameter("eventArray");
//		System.out.println(test);
//		String[] jdata = request.getParameter("eventArray").split("&");
//		String jsonData = jdata[0];
//		String classPeriodId = jdata[1].substring(jdata.length-3);
//		System.out.println(classPeriodId);
		String jsonData = request.getParameter("JSONData");
//		System.out.println(jsonData);
//		JSONArray ja = new JSONArray();
		JSONObject jo = new JSONArray(jsonData).getJSONObject(1);
		
		String classPeriodId = jo.getString("class");
//		System.out.println(classPeriodId);
//		String classPeriodId = request.getParameter("classPeriodId");
		// 丟給Service處理
		IScheduleService SS = new ScheduleService();
//		String JS = null;
		try {
			SS.saveClassSchedule(jsonData, classPeriodId);
//			JS = CCS.getClassJSON();

		} catch (Exception e) {
			e.printStackTrace();
		}
		// 將回傳的JSONString傳到AJAX
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print("Successfully save schedules");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
