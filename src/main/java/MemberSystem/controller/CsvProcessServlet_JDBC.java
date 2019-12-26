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
//		java.util.Date birthDate;
//		Integer privilegeId;
//		InputStream is2 = part.getInputStream();
////		InputStreamReader isr = new InputStreamReader(is2, StandardCharsets.UTF_8);
//		BufferedReader br = new BufferedReader(new InputStreamReader(is2, StandardCharsets.UTF_8));
//		OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("C:/Users/User/Downloads/test.csv"),"UTF-8");
//		int data;
//		while ((data = br.read()) != -1) {
//			osw.write(data);
//		}
//		System.out.println("writeIN!");
//		osw.flush();
//		osw.close();
//		br.close();
////		isr.close();
//		is2.close();
//		
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//
//		MemberServiceImpl service = new MemberServiceImpl();
//		try {
//			// 檔案讀取路徑C:\Users\User\Desktop\eduSystem
//			InputStreamReader isr = new InputStreamReader(new FileInputStream("C:/Users/User/Downloads/test.csv"));
//			BufferedReader reader = new BufferedReader(isr);
//			String line = null;
//			while ((line = reader.readLine()) != null) {
//				String item[] = line.split(",");
//				//new Bean，先存進Bean，再透過Dao將Bean存進DB
//				MembersBean mem = new MembersBean();
////				mem.setMemberId(null);
//				String pwd = GlobalService.getMD5Endocing(GlobalService.encryptString(item[3].trim()));
//				mem.setFirstName(item[0].trim());
//				mem.setLastName(item[1].trim());
//				mem.setAccountName(item[2].trim());
//				mem.setPassword(pwd);
////				mem.setPassword(item[3].trim());
//				mem.setAddress(item[4].trim());
//				mem.setEmail(item[5].trim());
//				mem.setGender(item[6].trim());
//				System.out.println(item[6].trim());
//				mem.setTelePhone(item[7].trim());
//				System.out.println(item[8].trim());
//				mem.setCellPhone(item[8].trim());
//				System.out.println(item[9].trim());
//				try {
//					birthDate = sdf.parse(item[9].trim());
//					System.out.println(birthDate);
//					mem.setBirthDate(birthDate);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//				privilegeId = Integer.parseInt(item[10].trim());
//				mem.setPrivilegeId(privilegeId);
//				Timestamp ts = new java.sql.Timestamp(System.currentTimeMillis());
//				mem.setRegisteredTime(ts);
//				mem.setMemberImage(null);
//				mem.setImageFileName(null);
//				privilegeId = Integer.parseInt(item[11].trim());
//				mem.setActiveStatus(privilegeId);
//				service.saveMember(mem);
//			}
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
		//存進資料庫
		response.sendRedirect(response.encodeRedirectURL("administrator/admin.jsp"));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
