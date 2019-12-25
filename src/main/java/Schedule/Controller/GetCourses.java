package Schedule.Controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Schedule.Service.ClassCourseService;
import Schedule.Service.IClassCourseService;
import Schedule.Service.IScheduleService;
import Schedule.Service.ScheduleService;

@WebServlet("/GetCourses")
public class GetCourses extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String classPeriodId = request.getParameter("classPeriodId");
		// 丟給Service處理
		IClassCourseService CCS = new ClassCourseService();
		String JS = null;
		try {
			JS = CCS.getCoursesJSON(classPeriodId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 將回傳的JSONString傳到AJAX
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(JS);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
