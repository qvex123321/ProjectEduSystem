package MemberSystem.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import _util.model.AdminStaffBean;
import _util.model.MembersBean;
import _util.model.StudentBean;
import _util.model.TeacherBean;

/*
 * 本程式在後端執行，它會
 * (1) 讀取使用者由前端網頁輸入資料，
 * (2) 進行必要的資料轉換，
 * (3) 檢查使用者輸入資料，
 * (4) 呼叫Business Service元件，進行Business Logic運算，
 * (5) 依照Business Logic運算結果來送回適當的畫面給前端的使用者。
 * 
 */
//
//啟動檔案上傳的功能：
//1. <form>標籤的 method屬性必須是"POST", 而且
//    enctype屬性必須是"multipart/form-data"
//    注意：enctype屬性的預設值為"application/x-www-form-urlencoded"
//2. 定義可以挑選上傳檔案的表單欄位：
//   <input type='file' name='user-defined_name' />
//

//MultipartConfig的屬性說明:
//location: 上傳之表單資料與檔案暫時存放在Server端之路徑，此路徑必須存在，否則Web Container將丟出例外。
//
//fileSizeThreshold: 上傳檔案的大小臨界值，超過此臨界值，上傳檔案會用存放在硬碟，
//                   否則存放在主記憶體。
//
//maxFileSize: 上傳單一檔案之長度限制，如果超過此數值，Web Container會丟出例外
//
//maxRequestSize: 上傳所有檔案之總長度限制，如果超過此數值，Web Container會丟出例外
@MultipartConfig(location = "", fileSizeThreshold = 5 * 1024 * 1024, maxFileSize = 1024 * 1024
		* 500, maxRequestSize = 1024 * 1024 * 500 * 5)

@WebServlet("/register.do")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//
	// 設定密碼欄位必須由大寫字母、小寫字母、數字與 !@#$%!^'" 等四組資料組合而成，且長度不能小於八個字元
	//
	private static final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%!^'\"]).{8,})";
	private Pattern pattern = null;
	private Matcher matcher = null;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8"); // 文字資料轉內碼
		// 準備存放錯誤訊息的Map物件
		Map<String, String> errorMsg = new HashMap<String, String>();
		// 準備存放註冊成功之訊息的Map物件
		Map<String, String> msgOK = new HashMap<String, String>();
		// 註冊成功後將用response.sendRedirect()導向新的畫面，所以需要
		// session物件來存放共用資料。
		HttpSession session = request.getSession();
		request.setAttribute("MsgMap", errorMsg); // 顯示錯誤訊息
		session.setAttribute("MsgOK", msgOK); // 顯示正常訊息

		Integer memberId = 0;
		String firstName = "";
		String lastName = "";
		String accountName = "";
		String password = "";
		String address = "";
		String email = "";
		String gender = "";
		String telePhone = "";
		String cellPhone = "";
		String strBirthDate = "";
		Date birthDate = null;
		Integer privilegeId = 0;

		String fileName = "";
		long sizeInBytes = 0;
		InputStream is = null;

		// 取出HTTP multipart request內所有的parts
		Collection<Part> parts = request.getParts();
//		GlobalService.exploreParts(parts, request);
		// 由parts != null來判斷此上傳資料是否為HTTP multipart request
		if (parts != null) { // 如果這是一個上傳資料的表單
			for (Part p : parts) {
				String fldName = p.getName();
				String value = request.getParameter(fldName);

				// 讀取使用者輸入資料
				if (p.getContentType() == null) {
					if (fldName.equals("firstName")) {
						firstName = value;
					} else if (fldName.equals("lastName")) {
						lastName = value;
					} else if (fldName.equals("accountName")) {
						accountName = value;
					} else if (fldName.equals("password")) {
						password = value;
					} else if (fldName.equals("address")) {
						address = value;
					} else if (fldName.equals("email")) {
						email = value;
					} else if (fldName.equals("gender")) {
						gender = value;
					} else if (fldName.equals("telePhone")) {
						telePhone = value;
					} else if (fldName.equals("cellPhone")) {
						cellPhone = value;
					} else if (fldName.equals("birthDate_year") || fldName.equals("birthDate_month")) {
						strBirthDate += value + "-";
					} else if (fldName.equals("birthDate_day")) {
						strBirthDate += value;
					}

					else if (fldName.equals("privilegeId")) {
						// 判斷privilegeId 帶入資料表的權限數字
						if (value.equals("student")) {
							privilegeId = 1;
						} else if (value.equals("teacher")) {
							privilegeId = 2;
						} else if (value.equals("admin")) {
							privilegeId = 3;
						}
					}
				} else {
					// 取出圖片檔的檔名
					fileName = GlobalService.getFileName(p);
					// 調整圖片檔檔名的長度，需要檔名中的附檔名，所以調整主檔名以免檔名太長無法寫入表格
					fileName = GlobalService.adjustFileName(fileName, GlobalService.IMAGE_FILENAME_LENGTH);
					if (fileName != null && fileName.trim().length() > 0) {
						sizeInBytes = p.getSize();
						is = p.getInputStream();
					}
				}
			}
			// 轉換生日字串 => Date形式
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try {
				birthDate = sdf.parse(strBirthDate);
			} catch (Exception e) {
				e.printStackTrace();
			}

			// 檢查使用者輸入資料
			if (accountName == null || accountName.trim().length() == 0) {
				errorMsg.put("errorIdEmpty", "帳號欄必須輸入");
			}
			if (password == null || password.trim().length() == 0) {
				errorMsg.put("errorPasswordEmpty", "密碼欄必須輸入");
			}
			if (firstName == null || firstName.trim().length() == 0) {
				errorMsg.put("errorName", "姓名欄必須輸入");
			}
			if (email == null || email.trim().length() == 0) {
				errorMsg.put("errorEmail", "電子郵件欄必須輸入");
			}
			if (telePhone == null || telePhone.trim().length() == 0) {
				errorMsg.put("errorTel", "電話號碼欄必須輸入");
			}
			if (cellPhone == null || cellPhone.trim().length() == 0) {
				errorMsg.put("errorCell", "手機號碼欄必須輸入");
			}
		} else {
			errorMsg.put("errTitle", "此表單不是上傳檔案的表單");
		}

		// 如果有錯誤
		if (errorMsg.isEmpty()) {
			pattern = Pattern.compile(PASSWORD_PATTERN);
			matcher = pattern.matcher(password);
			if (!matcher.matches()) {
				errorMsg.put("passwordError", "密碼至少含有一個大寫字母、小寫字母、數字與!@#$%!^'\"等四組資料組合而成，且長度不能小於八個字元");
			}
		}
		// 如果有錯誤
		if (!errorMsg.isEmpty()) {
//			Set<String> set = errorMsg.keySet();
//			for (String s : set) {
//				System.out.println(s);
//			}
			// 導向原來輸入資料的畫面，這次會顯示錯誤訊息
			RequestDispatcher rd = request.getRequestDispatcher("administrator/register.jsp");
			rd.forward(request, response);
			return;
		}
		try {
			// 4. 產生MemberDao物件，以便進行Business Logic運算
			// MemberDaoImpl_Jdbc類別的功能：
			// 1.檢查帳號是否已經存在，已存在的帳號不能使用，回傳相關訊息通知使用者修改
			// 2.若無問題，儲存會員的資料
			MemberService service = new MemberServiceImpl();
			if (service.idExists(accountName)) {
				errorMsg.put("errorIdDup", "此帳號已存在，請換新帳號");
			} else {
				// 為了配合Hibernate的版本。
				// 要在此加密，不要在 dao.saveMember(mem)進行加密
				password = GlobalService.getMD5Endocing(GlobalService.encryptString(password));
				Timestamp ts = new java.sql.Timestamp(System.currentTimeMillis());
				Blob blob = null;
				if (is != null) {
					blob = GlobalService.fileToBlob(is, sizeInBytes);
				}
				// 將所有會員資料封裝到MemberBean(類別的)物件
				MembersBean mem = new MembersBean(memberId, firstName, lastName, accountName, password, address, email,
						gender, telePhone, cellPhone, birthDate, privilegeId, ts, blob, fileName, ts, 1);
				
				// 轉換身份權限
				if (privilegeId == 1) {
					mem = new StudentBean(mem, null, request.getParameter("classPeriodId") , Integer.parseInt(request.getParameter("seatNo")));
				} else if(privilegeId == 2) {
					mem = new TeacherBean(mem, null);
				} else {
					mem = new AdminStaffBean(mem, null);
				}
				
				
				// 呼叫MemberDao的saveMember方法
				int n = service.saveSingleMember(mem);
				if (n == 1) {
					msgOK.put("InsertOK", "會員新增成功");
					response.sendRedirect("administrator/register.jsp");
					return;
				} else {
					errorMsg.put("errorIdDup", "新增此筆資料有誤(RegisterServlet)");
				}
			}
			// 5.依照 Business Logic 運算結果來挑選適當的畫面
			if (!errorMsg.isEmpty()) {
				// 導向原來輸入資料的畫面，這次會顯示錯誤訊息
				RequestDispatcher rd = request.getRequestDispatcher("member/administrator/register.jsp");
				rd.forward(request, response);
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
			errorMsg.put("errorIdDup", e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("member/administrator/register.jsp");
			rd.forward(request, response);
		}
	}
}