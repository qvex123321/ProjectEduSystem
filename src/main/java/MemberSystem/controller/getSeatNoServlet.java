package MemberSystem.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import MemberSystem.service.MemberService;
import MemberSystem.service.impl.MemberServiceImpl;

@WebServlet("/getSeatNoServlet")
public class getSeatNoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		MemberService service = new MemberServiceImpl();

		String classPeriod;
		request.setCharacterEncoding("UTF-8");
		response.setContentType("UTF-8");
//		System.out.println("--------------------");
		classPeriod = request.getParameter("id");
//		System.out.println(request.getParameter("id"));//�Y�������p�X�r��A�L�X��r��=>OK
//		service.getSeatNo(classPeriod);
		int tag = service.getPeriodNumberTag(classPeriod);
//		System.out.println(tag);
		PrintWriter out = response.getWriter();
		out.print(tag);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
