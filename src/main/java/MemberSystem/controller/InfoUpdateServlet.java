package MemberSystem.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import MemberSystem.service.MemberService;
import MemberSystem.service.impl.MemberServiceImpl;
import _util.GlobalService;
import _util.model.MembersBean;

@MultipartConfig(location = "", fileSizeThreshold = 5 * 1024 * 1024, maxFileSize = 1024 * 1024
		* 500, maxRequestSize = 1024 * 1024 * 500 * 5)

@WebServlet("/info")
public class InfoUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		// 準備存放錯誤訊息的Map物件
		Map<String, String> errorMsg = new HashMap<String, String>();
		// 準備存放新增成功之訊息的Map物件
		Map<String, String> msgOK = new HashMap<String, String>();
		HttpSession session = request.getSession();
		request.setAttribute("MsgMap", errorMsg); // 顯示錯誤訊息
		session.setAttribute("MsgOK", msgOK); // 顯示正常訊息


		// 取得原先MemberBean 物件
		MembersBean mb = (MembersBean) session.getAttribute("LoginOK");

		String fileName = "";
		long sizeInBytes = 0;
		InputStream is = null;

		Collection<Part> parts = request.getParts();
		if (parts != null) {
			for (Part p : parts) {
				if (p.getContentType() == null) {
					// 設定姓氏
					String lastName = request.getParameter("lastName");
					if (lastName == null || lastName.trim().length() == 0) {
						errorMsg.put("errorName", "姓氏欄必須輸入");
					} else {
						mb.setLastName(lastName);
					}
					// 設定名字

					String firstName = request.getParameter("firstName");
					if (firstName == null || firstName.trim().length() == 0) {
						errorMsg.put("errorName", "名字欄必須輸入");
					} else {
						mb.setFirstName(firstName);
					}
					// 設定Email
					String email = request.getParameter("email");
					if (email == null || email.trim().length() == 0) {
						errorMsg.put("errorMail", "Email欄必須輸入");
					} else {
						mb.setEmail(email);
					}

					// 設定電話
					String telePhone = request.getParameter("telePhone");
					if (telePhone == null || telePhone.trim().length() == 0) {
						errorMsg.put("errorPhone", "電話欄必須輸入");
					} else {
						mb.setTelePhone(telePhone);
					}
					// 設定手機
					String cellPhone = request.getParameter("cellPhone");
					if (cellPhone == null || cellPhone.trim().length() == 0) {
						errorMsg.put("errorCell", "手機欄必須輸入");
					} else {
						mb.setCellPhone(cellPhone);
					}
					// 設定地址
					mb.setAddress(request.getParameter("address"));
					mb.setModifiedTime(new java.sql.Timestamp(System.currentTimeMillis()));
				} else {
					// 取出圖片檔的檔名
					fileName = GlobalService.getFileName(p);
					// 調整圖片檔檔名的長度，需要檔名中的附檔名，所以調整主檔名以免檔名太長無法寫入表格
					fileName = GlobalService.adjustFileName(fileName, GlobalService.IMAGE_FILENAME_LENGTH);
					if (fileName != null && fileName.trim().length() > 0) {
						sizeInBytes = p.getSize();
						is = p.getInputStream();
					}
					Blob blob = null;
					if (is != null) {
						try {
							blob = GlobalService.fileToBlob(is, sizeInBytes);
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
					mb.setImageFileName(fileName);
					mb.setMemberImage(blob);

				}
			}

		}

		if (!errorMsg.isEmpty()) {
//			Set<String> set = errorMsg.keySet();
//			for (String s : set) {
//				System.out.println(s);
//			}
			// 導向原來輸入資料的畫面，顯示錯誤訊息
			RequestDispatcher rd = request.getRequestDispatcher("member/userInfo.jsp");
			rd.forward(request, response);
			return;
		}
		try {
			MemberService service = new MemberServiceImpl();
			int status = service.updateMember(mb);
			if (status == 1) {
				// 修改成功
				msgOK.put("updateOK", "資料修改成功");
				session.setAttribute("LoginOK", mb);
				response.sendRedirect("member/userInfo.jsp");
			} else {
				errorMsg.put("errorUpdate", "修改此筆資料有誤(RegisterServlet)");
				RequestDispatcher rd = request.getRequestDispatcher("member/userInfo.jsp");
				rd.forward(request, response);
			}

		} catch (Exception e) {
			e.printStackTrace();
			errorMsg.put("errorUpdate", e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("member/userInfo.jsp");
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
