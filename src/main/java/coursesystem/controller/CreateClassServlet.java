package coursesystem.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import coursesystem.service.CourseServiceImpl;

@WebServlet("/CreateClass")
public class CreateClassServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		// 準備存放新增成功之訊息的Map物件
		Map<String, String> msgOK = new HashMap<String, String>();
		// 準備存放錯誤訊息的Map物件
		Map<String, String> errorMsg = new HashMap<String, String>();
		session.setAttribute("msgOK", msgOK); // 顯示正常訊息
		request.setAttribute("errorMsg", errorMsg); // 顯示錯誤訊息
		
		
		String params = request.getParameter("obj");
		CourseServiceImpl service = new CourseServiceImpl();
	
		// 新建班級 // 課程
		try {
			service.saveClass(params);
			msgOK.put("createClassOK", "班級新增成功");
			response.sendRedirect("administrator/newClass.jsp");
			return;
			
		} catch (Exception e) {
			errorMsg.put("errorClass", "班級新增失敗"+e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("administrator/newClass.jsp");
			rd.forward(request, response);
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
