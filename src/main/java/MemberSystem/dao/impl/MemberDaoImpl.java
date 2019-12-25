package MemberSystem.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import MemberSystem.dao.MemberDao;
import _util.model.AdminStaffBean;
import _util.model.MembersBean;
import _util.model.StudentBean;
import _util.model.TeacherBean;

public class MemberDaoImpl implements MemberDao {
	private Connection conn = null;
	
	public MemberDaoImpl() {
		
	}

	public MemberDaoImpl(Connection conn) {
		super();
		this.conn = conn;
	}
	@Override
	// 拿到插入後的MemberId
	public Integer searchMemberId(String accountName) {
		Integer memberId = 0;
		try {
			String sql = "SELECT * FROM Members WHERE accountName = ?";
			PreparedStatement state;
			state = conn.prepareStatement(sql);
			state.setString(1, accountName);
			ResultSet rs = state.executeQuery();
			if (rs.next()) {
				memberId = rs.getInt("memberId");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return memberId;
	}
	@Override
	// 儲存MemberBean物件，將參數mb新增到Member表格內。
	public int saveSingleMember(MembersBean mb) {
		int n = 0;

		String sql = "INSERT INTO Members (firstName,lastName,accountName,password,address,email,gender,telePhone"
				+ ",cellPhone,birthDate,privilegeId,registeredTime,memberImage,imageFileName,modifiedTime,activeStatus)"
				+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement state = conn.prepareStatement(sql);
			state.setString(1, mb.getFirstName());
			state.setString(2, mb.getLastName());
			state.setString(3, mb.getAccountName());
			state.setString(4, mb.getPassword());
			state.setString(5, mb.getAddress());
			state.setString(6, mb.getEmail());
			state.setString(7, mb.getGender());
			state.setString(8, mb.getTelePhone());
			state.setString(9, mb.getCellPhone());
			state.setDate(10, new Date(mb.getBirthDate().getTime()));
			state.setInt(11, mb.getPrivilegeId());
			state.setTimestamp(12, mb.getRegisteredTime());
			state.setBlob(13, mb.getMemberImage());
			state.setString(14, mb.getImageFileName());
			state.setTimestamp(15, mb.getModifiedTime());
			state.setInt(16, mb.getActiveStatus());
			n = state.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return n;
	}
	
	@Override
	public Integer getMemberId(String accountName) {
		Integer memberId = 0;
		String sql = "SELECT * FROM Members WHERE accountName = ?";
		try{
			PreparedStatement state = conn.prepareStatement(sql);
			state.setString(1, accountName);
			ResultSet rs = state.executeQuery();
			if (rs.next()) {
				memberId=rs.getInt("memberId");
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("MemberDaoImpl #checkIDPassword()發生SQL例外: " + ex.getMessage());
		}
		return memberId;
	}
	@Override
	public Boolean insertStudent(StudentBean mb, int memberId) {
		System.out.println("進入到insertStudetn");
		System.out.println("#insertStudent: seatNo :" +mb.getSeatNo());
		String sql = "INSERT INTO Student (classPeriodId,seatNo,memberId) VALUES (?,?,?)";
		try {
			PreparedStatement state = conn.prepareStatement(sql);
			state.setString(1, mb.getClassPeriodId());
			state.setInt(2, mb.getSeatNo());
			state.setInt(3, memberId);
			return state.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public Boolean insertTeacher(TeacherBean mb, int memberId) {
		String sql = "INSERT INTO Teacher (memberId) VALUES (?)";
		try {
			PreparedStatement state = conn.prepareStatement(sql);
			state.setInt(1, memberId);
			return state.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public Boolean insertAdminStaff(AdminStaffBean mb, int memberId) {
		String sql = "INSERT INTO AdminStaff (memberId)" + " VALUES (?)";
		try {
			PreparedStatement state = conn.prepareStatement(sql);
			state.setInt(1, memberId);
			return state.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	// 儲存MemberBean物件，將參數mb新增到Member表格內。
	@Override
	public int saveMember(MembersBean mb) {
		String sql = "insert into Members " 
				+ " (firstName, lastName, accountName, password, address, email, "
				+ " gender, telephone, cellphone, birthDate, privilegeId, registeredTime, "
				+ " memberImage, imageFileName, activeStatus)" 
				+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		int n = 0;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
//			System.out.println(mb.getAccountName());
			ps.setString(1, mb.getFirstName());
			ps.setString(2, mb.getLastName());
			ps.setString(3, mb.getAccountName());
			ps.setString(4, mb.getPassword());
			ps.setString(5, mb.getAddress());
			ps.setString(6, mb.getEmail());
			ps.setString(7, mb.getGender());
			ps.setString(8, mb.getTelePhone());
			ps.setString(9, mb.getCellPhone());
			ps.setDate(10, new Date(mb.getBirthDate().getTime()));
			ps.setInt(11, mb.getPrivilegeId());
			ps.setTimestamp(12, mb.getRegisteredTime());
			ps.setBlob(13, mb.getMemberImage());
			ps.setString(14, mb.getImageFileName());
			ps.setInt(15, mb.getActiveStatus());
			n = ps.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("MemberDaoImpl_Jdbc類別#saveMember()發生例外: " 
										+ ex.getMessage());
		}
		return n;
	}

	// 判斷參數id(會員帳號)是否已經被現有客戶使用，如果是，傳回true，表示此id不能使用，
	// 否則傳回false，表示此id可使用。
	@Override
	public boolean idExists(String id) {
		boolean exist = false;
		String sql = "SELECT * FROM Members WHERE accountName = ?";
		try {
			PreparedStatement state = conn.prepareStatement(sql);
			state.setString(1, id);
			try (ResultSet rs = state.executeQuery();) {
				if (rs.next()) {
					exist = true;
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException(ex.getMessage());
		}
		return exist;
	}

	// 由參數 id (會員帳號) 到Member表格中 取得某個會員的所有資料，傳回值為一個MemberBean物件，
	// 如果找不到對應的會員資料，傳回值為null。
	@Override
	public MembersBean queryMember(String id) {
		MembersBean mb = null;
		String sql = "SELECT * FROM Members WHERE memberId = ? ";
		try {
			PreparedStatement state = conn.prepareStatement(sql);
			state.setString(1, id);
			try (ResultSet rs = state.executeQuery();) {
				if (rs.next()) {
					mb = new MembersBean();
					mb.setMemberId(rs.getInt("memberId"));
					mb.setFirstName(rs.getString("firstName"));
					mb.setLastName(rs.getString("lastName"));
					mb.setAccountName(rs.getString("accountName"));
					mb.setPassword(rs.getString("password"));
					mb.setAddress(rs.getString("address"));
					mb.setEmail(rs.getString("email"));
					mb.setGender(rs.getString("gender"));
					mb.setTelePhone(rs.getString("telePhone"));
					mb.setCellPhone(rs.getString("telePhone"));
					mb.setBirthDate(rs.getDate("birthDate"));
					mb.setPrivilegeId(rs.getInt("privilegeId"));
					mb.setRegisteredTime(rs.getTimestamp("registeredTime"));
					mb.setMemberImage(rs.getBlob("memberImage"));
					mb.setImageFileName(rs.getString("imageFileName"));
					mb.setModifiedTime(rs.getTimestamp("modifiedTime"));
					mb.setActiveStatus(rs.getInt("activeStatus"));
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("MemberDaoImpl #checkIDPassword()發生SQL例外: " + ex.getMessage());
		}
		return mb;
	}

	// 檢查使用者在登入時輸入的帳號與密碼是否正確。如果正確，傳回該帳號所對應的MemberBean物件，
	// 否則傳回 null。
	@Override
	public MembersBean checkIdPassword(String accountName, String password) {
		MembersBean mb = null;
		String sql = "SELECT * FROM Members m WHERE accountName = ? and password = ?";
		try {
			PreparedStatement state = conn.prepareStatement(sql);
			state.setString(1, accountName);
			state.setString(2, password);
			try (ResultSet rs = state.executeQuery();) {
				if (rs.next()) {
					mb = new MembersBean();
					mb.setMemberId(rs.getInt("memberId"));
					mb.setFirstName(rs.getString("firstName"));
					mb.setLastName(rs.getString("lastName"));
					mb.setAccountName(rs.getString("accountName"));
					mb.setPassword(rs.getString("password"));
					mb.setAddress(rs.getString("address"));
					mb.setEmail(rs.getString("email"));
					mb.setGender(rs.getString("gender"));
					mb.setTelePhone(rs.getString("telePhone"));
					mb.setCellPhone(rs.getString("telePhone"));
					mb.setBirthDate(rs.getDate("birthDate"));
					mb.setPrivilegeId(rs.getInt("privilegeId"));
					mb.setRegisteredTime(rs.getTimestamp("registeredTime"));
					mb.setMemberImage(rs.getBlob("memberImage"));
					mb.setImageFileName(rs.getString("imageFileName"));
					mb.setModifiedTime(rs.getTimestamp("modifiedTime"));
					mb.setActiveStatus(rs.getInt("activeStatus"));
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("MemberDaoImpl #checkIDPassword()發生SQL例外: " + ex.getMessage());
		}
		
		return mb;
	}

	@Override
	public MembersBean getIdentityBean(MembersBean mb, int privilegeId) {
		String sql = "";
		switch (privilegeId) {
		case 1:
			sql = "Select * FROM Student WHERE memberId = ?";
			break;
		case 2:
			sql = "Select * FROM teacher WHERE MemberId = ?";
			break;
		case 3:
			sql = "Select * FROM adminStaff WHERE MemberId = ?";
			break;
		default:
			return mb;
		}
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, mb.getMemberId());
			try (ResultSet rs = ps.executeQuery();) {
				if (rs.next()) {
					if(privilegeId==1){
					mb = new StudentBean(mb, rs.getInt("studentId"), rs.getString("classPeriodId"), rs.getInt("seatNo"));}
				}else if(privilegeId==2) {
//					mb = new TeacherBean();
				}else {
//					mb = new AdminStaffBean();
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("MemberDaoImpl發生例外:" + ex.getMessage());
		}
		return mb;
	}

	@Override
	public int updateMember(MembersBean mb) {
		int status = 0;
		String sql = "UPDATE Members " 
				+ " SET firstName= ?, lastName = ?, address=?, email =?, "
				+ " telephone= ?, cellphone =? ,  "
				+ " memberImage=?, imageFileName=?, modifiedTime =? WHERE memberId = ?" ;
		
		try {
			PreparedStatement state = conn.prepareStatement(sql);
			state.setString(1, mb.getFirstName());
			state.setString(2, mb.getLastName());
			state.setString(3, mb.getAddress());
			state.setString(4, mb.getEmail());
			state.setString(5, mb.getTelePhone());
			state.setString(6, mb.getCellPhone());
			state.setBlob(7, mb.getMemberImage());
			state.setString(8, mb.getImageFileName());
			state.setTimestamp(9, mb.getModifiedTime());
			state.setInt(10, mb.getMemberId());
			status = state.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("MemberDaoImpl #update()發生SQL例外: " + ex.getMessage());
		}
		return status;
	}
	
	@Override
	public Integer getSeatNo(String classPeriod) {
		String sqlStr="select seatNo from Student where classPeriodId=?";
		int latestSeatNo=0;;
		List<Integer> list = new ArrayList<Integer>();
		try {
			PreparedStatement stmt = conn.prepareStatement(sqlStr);
//			System.out.println(classPeriod);
			stmt.setString(1, classPeriod);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				list.add(rs.getInt("seatNo"));
			}
//			System.out.println(Collections.max(list));
			try {
				latestSeatNo=Collections.max(list)+1;
			}catch(Exception e) {
				latestSeatNo=1;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return latestSeatNo;
	}
	
	@Override
	public Integer getPeriodNumberTag(String classPeriod) {
		Integer latestSeatNo = getSeatNo(classPeriod);
		return latestSeatNo;
	}
	

}
