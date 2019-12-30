package MemberSystem.controller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import MemberSystem.service.MemberService;
import MemberSystem.service.impl.MemberServiceImpl;
import _util.GlobalService;
import _util.model.MembersBean;

@WebServlet("/CsvProcessServlet")
@MultipartConfig
public class CsvProcessServlet_JDBC extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Part part = request.getPart("csvFile");
		MemberService service = new MemberServiceImpl();
		service.saveMemberByCSV(part);
		//存進資料庫
		response.sendRedirect(response.encodeRedirectURL("member/administrator/admin.jsp"));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
