package Schedule.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import _util.model.AdminStaffBean;
import _util.model.ClassScheduleBean;
import _util.model.CourseBean;
import _util.model.StudentBean;
import _util.model.TeacherBean;
import _util.model.TeacherScheduleBean;

public class ScheduleDAOImpl {
	Connection conn;

	public ScheduleDAOImpl() {
	}

	public ScheduleDAOImpl(Connection conn) {
		this.conn = conn;
//		initCon();
//		try {
//			Context ctx=new InitialContext();
//			Connection conn=(DataSource)ctx.lookup( );
//		} catch (NamingException e) {
//			e.printStackTrace();
//		}
	}

	// 拿ScheduleBean
	public TeacherScheduleBean getTeacherScheduleBean(int teacherId, int year, int month) {
		TeacherScheduleBean Bean = new TeacherScheduleBean();
		try {
			String sqlstr = "SELECT * FROM TeacherSchedule WHERE teacherId=? AND year=? AND month=?";
			PreparedStatement state = conn.prepareStatement(sqlstr);
			state.setInt(1, teacherId);
			state.setInt(2, year);
			state.setInt(3, month);
			ResultSet rs = state.executeQuery();

			while (rs.next()) {
			Bean.settScheduleId(rs.getInt("pSchedule"));
			Bean.setTeacherId(rs.getInt("teacherId"));
			Bean.setYear(rs.getInt("year"));
			Bean.setMonth(rs.getInt("month"));
			Bean.setDailyScheduleM(rs.getString("dailyScheduleM"));
			Bean.setDailyScheduleAN(rs.getString("dailyScheduleAN"));
			Bean.setDailyScheduleN(rs.getString("dailyScheduleN"));
			}
			rs.close();
			return Bean;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public ClassScheduleBean getClassScheduleBean(String classPeriodId, int year, int month) {
		ClassScheduleBean Bean = new ClassScheduleBean();
		try {
			String sqlstr = "SELECT * FROM ClassSchedule WHERE classPeriodId=? AND year=? AND month=?";
			PreparedStatement state = conn.prepareStatement(sqlstr);
			state.setString(1, classPeriodId);
			state.setInt(2, year);
			state.setInt(3, month);
//			String sqlstr = "SELECT * FROM ClassSchedule";
//			PreparedStatement state = conn.prepareStatement(sqlstr);
			System.out.println(sqlstr + ", " + classPeriodId + ", " + year + ", " + month);
			ResultSet rs = state.executeQuery();
			while (rs.next()) {
//				System.out.println(rs.getInt(1));
				Bean.setcScheduleId(rs.getInt("cScheduleId"));
				Bean.setClassPeriodId(rs.getString("classPeriodId"));
				Bean.setYear(rs.getInt("year"));
				Bean.setMonth(rs.getInt("month"));
				Bean.setDailyScheduleM(rs.getString("dailyScheduleM"));
				Bean.setDailyScheduleAN(rs.getString("dailyScheduleAN"));
				Bean.setDailyScheduleN(rs.getString("dailyScheduleN"));
//				System.out.println(Bean.toString());
			}
			rs.close();
			return Bean;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public void testInsert() {
		String sqlstr = "INSERT INTO AdminStaff (memberId) VALUES (?)";
		PreparedStatement state;
		try {
			state = conn.prepareStatement(sqlstr);
			state.setInt(1, 8);
			state.execute();
			System.out.println("YEs");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 拿courseBean
	public CourseBean getCourseBean(int courseId) {
		CourseBean Bean = new CourseBean();
		try {
//			System.out.println(courseId);
//			System.out.println(conn);
			String sqlstr = "SELECT * FROM Course c INNER JOIN CourseList cl ON c.courseListId = cl.courseListId WHERE courseId = ?";
			PreparedStatement state = conn.prepareStatement(sqlstr);
			state.setInt(1, courseId);
			ResultSet rs = state.executeQuery();
			while (rs.next()) {
				Bean.setCourseListId(rs.getInt("courseListId"));
				Bean.setCourseName(rs.getString("courseName"));
				Bean.setEduProgramTypeId(rs.getInt("eduProgramTypeId"));
				Bean.setBriefInfo(rs.getString("briefInfo"));
				Bean.setCourseHour(rs.getInt("courseHour"));
				Bean.setCourseListId(rs.getInt("courseListId"));
				Bean.setCourseName(rs.getString("courseName"));
				Bean.setCourseId(rs.getInt("courseId"));
				Bean.setClassroomId(rs.getString("classroomId"));
				Bean.setTeacherId(rs.getInt("teacherId"));
			}
			rs.close();
			return Bean;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public TeacherBean getTeacherBean(int teacherId) {
		TeacherBean Bean = new TeacherBean();
		try {
			String sqlstr = "SELECT * FROM Members AS m JOIN Teacher AS t ON m.memberId=t.memberId WHERE t.teacherId = ?";
			PreparedStatement state = conn.prepareStatement(sqlstr);
			state.setInt(1, teacherId);
			ResultSet rs = state.executeQuery();
			while (rs.next()) {
			Bean.setMemberId(rs.getInt("memberId"));
			Bean.setFirstName(rs.getString("firstName"));
			Bean.setLastName(rs.getString("lastName"));
			Bean.setAccountName(rs.getString("accountName"));
			Bean.setPassword(rs.getString("password"));
			Bean.setEmail(rs.getString("email"));
			Bean.setAddress(rs.getString("address"));
			Bean.setGender(rs.getString("gender"));
			Bean.setBirthDate(rs.getDate("birthDate"));
			Bean.setTelePhone(rs.getString("telePhone"));
			Bean.setCellPhone(rs.getString("cellPhone"));
			Bean.setPrivilegeId(rs.getInt("privilegeId"));
			Bean.setRegisteredTime(rs.getTimestamp("registeredTime"));
			Bean.setMemberImage(rs.getBlob("memberImage"));
			Bean.setImageFileName(rs.getString("imageFileName"));
			Bean.setModifiedTime(rs.getTimestamp("modifiedTime"));
			Bean.setActiveStatus(rs.getInt("activeStatus"));
			Bean.setTeacherId(rs.getInt("teacherId"));
			}
			rs.close();
			return Bean;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public StudentBean getStudentBean(int StudentId) {
		StudentBean Bean = new StudentBean();
		try {
			String sqlstr = "SELECT * FROM Members AS m JOIN Student AS s ON m.memberId=s.memberId WHERE s.studentId = ?";
			PreparedStatement state = conn.prepareStatement(sqlstr);
			state.setInt(1, StudentId);
			ResultSet rs = state.executeQuery();

			while (rs.next()) {
			Bean.setMemberId(rs.getInt("memberId"));
			Bean.setFirstName(rs.getString("firstName"));
			Bean.setLastName(rs.getString("lastName"));
			Bean.setAccountName(rs.getString("accountName"));
			Bean.setPassword(rs.getString("password"));
			Bean.setEmail(rs.getString("email"));
			Bean.setAddress(rs.getString("address"));
			Bean.setGender(rs.getString("gender"));
			Bean.setBirthDate(rs.getDate("birthDate"));
			Bean.setTelePhone(rs.getString("telePhone"));
			Bean.setCellPhone(rs.getString("cellPhone"));
			Bean.setPrivilegeId(rs.getInt("privilegeId"));
			Bean.setRegisteredTime(rs.getTimestamp("registeredTime"));
			Bean.setMemberImage(rs.getBlob("memberImage"));
			Bean.setImageFileName(rs.getString("imageFileName"));
			Bean.setModifiedTime(rs.getTimestamp("modifiedTime"));
			Bean.setActiveStatus(rs.getInt("activeStatus"));

			Bean.setStudentId(rs.getInt("studentID"));
			Bean.setClassPeriodId(rs.getString("classPeriodId"));
			Bean.setSeatNo(rs.getInt("seatNo"));
			}
			rs.close();
			return Bean;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public AdminStaffBean getAdminStaffBean(int adminId) {
		AdminStaffBean Bean = new AdminStaffBean();
		try {
			String sqlstr = "SELECT * FROM Members AS m JOIN AdminStaff AS a ON m.memberId=a.memberId WHERE a.adminId = ?";
			PreparedStatement state = conn.prepareStatement(sqlstr);
			state.setInt(1, adminId);
			ResultSet rs = state.executeQuery();
			while (rs.next()) {
			Bean.setMemberId(rs.getInt("memberId"));
			Bean.setFirstName(rs.getString("firstName"));
			Bean.setLastName(rs.getString("lastName"));
			Bean.setAccountName(rs.getString("accountName"));
			Bean.setPassword(rs.getString("password"));
			Bean.setEmail(rs.getString("email"));
			Bean.setAddress(rs.getString("address"));
			Bean.setGender(rs.getString("gender"));
			Bean.setBirthDate(rs.getDate("birthDate"));
			Bean.setTelePhone(rs.getString("telePhone"));
			Bean.setCellPhone(rs.getString("cellPhone"));
			Bean.setPrivilegeId(rs.getInt("privilegeId"));
			Bean.setRegisteredTime(rs.getTimestamp("registeredTime"));
			Bean.setMemberImage(rs.getBlob("memberImage"));
			Bean.setImageFileName(rs.getString("imageFileName"));
			Bean.setModifiedTime(rs.getTimestamp("modifiedTime"));
			Bean.setActiveStatus(rs.getInt("activeStatus"));

			Bean.setAdminId(rs.getInt("adminId"));
			}
			rs.close();
			return Bean;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean insertClassSchedule(ClassScheduleBean Bean) {
		try {
			String sqlstr = "INSERT ClassSchedule (classPeriodId, year, month, dailyScheduleM, dailyScheduleAN, dailyScheduleN) VALUES (?, ?, ?, ?, ?, ?)";
//			String sqlstr = "SELECT * FROM Members AS m JOIN AdminStaff AS a ON m.memberId=a.memberId WHERE a.adminId = ?";
			PreparedStatement state = conn.prepareStatement(sqlstr);
			System.out.println(Bean.getYear()+", "+ Bean.getMonth());
			state.setString(1, Bean.getClassPeriodId());
			state.setInt(2, Bean.getYear());
			state.setInt(3, Bean.getMonth());
			state.setString(4, Bean.getDailyScheduleM());
			state.setString(5, Bean.getDailyScheduleAN());
			state.setString(6, Bean.getDailyScheduleN());
			state.executeUpdate();
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean updateClassSchedule(ClassScheduleBean Bean) {
		try {
			String sqlstr = "UPDATE ClassSchedule SET dailyScheduleM = ?, dailyScheduleAN = ?, dailyScheduleN = ? WHERE classPeriodId = ? AND year = ? AND month = ?";
			PreparedStatement state = conn.prepareStatement(sqlstr);
//			System.out.println(Bean.getYear()+", "+ Bean.getMonth());
			state.setString(1, Bean.getDailyScheduleM());
			state.setString(2, Bean.getDailyScheduleAN());
			state.setString(3, Bean.getDailyScheduleN());
			state.setString(4, Bean.getClassPeriodId());
			state.setInt(5, Bean.getYear());
			state.setInt(6, Bean.getMonth());
			state.executeUpdate();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}