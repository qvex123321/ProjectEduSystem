package MemberSystem.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import MemberSystem.service.impl.MemberServiceImpl;
import _util.GlobalService;
import _util.model.MembersBean;


@WebServlet("/UpdatePwd")
public class PwdUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		MembersBean mb = (MembersBean) session.getAttribute("LoginOK");
//		String msgOK ="密碼修改成功";
//		String errorMsg = "密碼修改失敗";
//		Map<String, String> errorMsg = (HashMap<String, String>) session.getAttribute("MsgMap");
		
		String oldPwd = request.getParameter("oldPwd");
		oldPwd = GlobalService.getMD5Endocing(GlobalService.encryptString(oldPwd));
		String newPwd = request.getParameter("newPwd");
		newPwd = GlobalService.getMD5Endocing(GlobalService.encryptString(newPwd));
		
		MemberServiceImpl msi = new MemberServiceImpl();
		int status = msi.updatePwd(mb.getAccountName(), oldPwd, newPwd);
		if(status==1) {
//			response.setCharacterEncoding("UTF-8");
//			response.getWriter().print(msgOK);
			mb.setPassword(request.getParameter("newPwd"));
			session.setAttribute("LoginOK", mb);
//			session.setAttribute("msgOK", msgOK);
//			response.sendRedirect("member/updatePwd.jsp");
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().println("<script>alert('密碼修改成功！');location.href='member/updatePwd.jsp';</script>");
		}else {
//			errorMsg.put("errorPwdUpdate", "密碼修改失敗");
//			request.setAttribute("errorMsg", errorMsg);
//			RequestDispatcher rd = request.getRequestDispatcher("member/updatePwd.jsp");
//			rd.forward(request, response);
			response.setContentType("text/html;charset=UTF-8");
			response.getWriter().println("<script>alert('舊密碼輸入錯誤！');location.href='member/updatePwd.jsp';</script>");
		}	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		doGet(request, response);
	}

}
