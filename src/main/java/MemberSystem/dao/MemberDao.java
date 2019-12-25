package MemberSystem.dao;

import _util.model.AdminStaffBean;
import _util.model.MembersBean;
import _util.model.StudentBean;
import _util.model.TeacherBean;

public interface MemberDao {

	// 拿到插入後的MemberId
	Integer searchMemberId(String accountName);

	// 儲存MemberBean物件，將參數mb新增到Member表格內。
	int saveSingleMember(MembersBean mb);

	Integer getMemberId(String accountName);

	Boolean insertStudent(StudentBean mb, int memberId);

	Boolean insertTeacher(TeacherBean mb, int memberId);

	Boolean insertAdminStaff(AdminStaffBean mb, int memberId);

	// 儲存MemberBean物件，將參數mb新增到Member表格內。
	int saveMember(MembersBean mb);

	// 判斷參數id(會員帳號)是否已經被現有客戶使用，如果是，傳回true，表示此id不能使用，
	// 否則傳回false，表示此id可使用。
	boolean idExists(String id);

	// 由參數 id (會員帳號) 到Member表格中 取得某個會員的所有資料，傳回值為一個MemberBean物件，
	// 如果找不到對應的會員資料，傳回值為null。
	MembersBean queryMember(String id);

	// 檢查使用者在登入時輸入的帳號與密碼是否正確。如果正確，傳回該帳號所對應的MemberBean物件，
	// 否則傳回 null。
	MembersBean checkIdPassword(String userAcct, String password);

	MembersBean getIdentityBean(MembersBean mb, int privilegeId);

	int updateMember(MembersBean mb);

	Integer getPeriodNumberTag(String classPeriod);

	Integer getSeatNo(String classPeriod);

}