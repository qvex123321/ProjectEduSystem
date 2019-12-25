package Schedule.Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Schedule.Service.IScheduleService;
import Schedule.Service.ScheduleService;

/*要輸出JsonObject
 * {
 * 	courseId:int,
 * 	courseName:String,
 * 	BriefInfo:String,
 * 	Teacher:String
 * }
 */

@WebServlet("/CourseInfo")
public class GetCourseInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String courseId = request.getParameter("courseId");
		// 丟給Service處理
		IScheduleService Ss = new ScheduleService();
		String JS = null;
		try {
			JS = Ss.getCourseInfo(Integer.parseInt(courseId));
				
		} catch (Exception e) {
			e.printStackTrace();
		}
		//將回傳的JSONString傳到AJAX
		response.setCharacterEncoding("UTF-8");
		response.getWriter().print(JS);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
