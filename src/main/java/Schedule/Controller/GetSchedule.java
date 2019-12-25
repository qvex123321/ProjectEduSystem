package Schedule.Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Schedule.Service.IScheduleService;
import Schedule.Service.ScheduleService;
import Schedule.Service.TestService;

/*要輸出JsonObject
 * {[
 * 	courseId:int,
 * 	start:
 * 	end:
 * 	title:
 * }
 */

@WebServlet("/Schedule")
public class GetSchedule extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String teacherId = request.getParameter("teacherId");
		String classPeriodId = request.getParameter("classPeriodId");
		// 丟給Service處理
//		TestService ts = new TestService();
		IScheduleService Ss = new ScheduleService();
//		ts.testTeacherBean();
//		ts.testStudentBean();
		String JS = null;
		try {
			if(teacherId!=null&&classPeriodId==null) {
				JS = Ss.getScheduleJSON("Teacher", teacherId);
			}else if(teacherId==null&&classPeriodId!=null) {
//				Ss.testInsert();
				JS = Ss.getScheduleJSON("Student", classPeriodId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//將回傳的JSONString傳到JSP
		request.setAttribute("JSONString", JS);
//		RequestDispatcher rd = request.getRequestDispatcher("/Schedule/Schedule.jsp");
		RequestDispatcher rd = request.getRequestDispatcher("/Schedule/calendar.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
