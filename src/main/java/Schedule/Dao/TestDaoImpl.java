package Schedule.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import _util.model.AdminStaffBean;
import _util.model.ClassBean;
import _util.model.ClassScheduleBean;
import _util.model.CourseBean;
import _util.model.CourseListBean;
import _util.model.MembersBean;
import _util.model.RoomScheduleBean;
import _util.model.StudentBean;
import _util.model.TeacherBean;
import _util.model.TeacherScheduleBean;

public class TestDaoImpl implements ITestDao {
	Connection conn;

	public TestDaoImpl() {
	}

	public TestDaoImpl(Connection conn) {
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
	@Override
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

	@Override
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
				System.out.println(rs.getInt(1));
				Bean.setcScheduleId(rs.getInt("cScheduleId"));
				Bean.setClassPeriodId(rs.getString("classPeriodId"));
				Bean.setYear(rs.getInt("year"));
				Bean.setMonth(rs.getInt("month"));
				Bean.setDailyScheduleM(rs.getString("dailyScheduleM"));
				Bean.setDailyScheduleAN(rs.getString("dailyScheduleAN"));
				Bean.setDailyScheduleN(rs.getString("dailyScheduleN"));
				System.out.println(Bean.toString());
			}
			rs.close();
			return Bean;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public RoomScheduleBean getRoomScheduleBean(String classroomId, int year, int month) {
		RoomScheduleBean Bean = new RoomScheduleBean();
		try {
			String sqlstr = "SELECT * FROM RoomSchedule WHERE classroomId = ?  AND year=? AND month=?";
			PreparedStatement state = conn.prepareStatement(sqlstr);
			state.setString(1, classroomId);
			state.setInt(2, year);
			state.setInt(3, month);
			ResultSet rs = state.executeQuery();
			while (rs.next()) {
				Bean.setrScheduleId(rs.getInt("rScheduleId"));
				Bean.setClassroomId(rs.getString("classroomId"));
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

	@Override
	public List<RoomScheduleBean> getRoomScheduleBean(String classroomId) {
		List<RoomScheduleBean> Beans = new ArrayList<RoomScheduleBean>();
		try {
			String sqlstr = "SELECT * FROM RoomSchedule WHERE classroomId = ?";
			PreparedStatement state = conn.prepareStatement(sqlstr);
			state.setString(1, classroomId);
			ResultSet rs = state.executeQuery();
			while (rs.next()) {
				RoomScheduleBean Bean = new RoomScheduleBean();
				Bean.setrScheduleId(rs.getInt("rScheduleId"));
				Bean.setClassroomId(rs.getString("classroomId"));
				Bean.setYear(rs.getInt("year"));
				Bean.setMonth(rs.getInt("month"));
				Bean.setDailyScheduleM(rs.getString("dailyScheduleM"));
				Bean.setDailyScheduleAN(rs.getString("dailyScheduleAN"));
				Bean.setDailyScheduleN(rs.getString("dailyScheduleN"));
				Beans.add(Bean);
			}
			rs.close();
			return Beans;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
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

	@Override
	public List<CourseListBean> getCourseListBeanList(int eduProgramTypeId) {
		// CourseListBean Bean = new CourseListBean();
		List<CourseListBean> Beans = new ArrayList<CourseListBean>();
		try {
			String sqlstr = "select * from CourseList where eduProgramTypeId=?";
			PreparedStatement state = conn.prepareStatement(sqlstr);
			state.setInt(1, eduProgramTypeId);
			ResultSet rs = state.executeQuery();
			while (rs.next()) {
				CourseListBean Bean = new CourseListBean();
				Bean.setCourseListId(rs.getInt("courseListId"));
				Bean.setCourseName(rs.getString("courseName"));
				Bean.setEduProgramTypeId(rs.getInt("eduProgramTypeId"));
				Bean.setBriefInfo(rs.getString("briefInfo"));
				Bean.setCourseHour(rs.getInt("courseHour"));
				Bean.setCourseListId(rs.getInt("courseListId"));
				Bean.setCourseName(rs.getString("courseName"));
				Beans.add(Bean);
			}
			rs.close();
			return Beans;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	// 拿courseBean
	@Override
	public CourseBean getCourseBean(int courseId) {
		CourseBean Bean = new CourseBean();
		try {
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
	@Override
	public List<CourseBean> getCourseBeanList(String classPeriodId) {
		List<CourseBean> Beans = new ArrayList<CourseBean>();
		try {
			String sqlstr = "SELECT courseId,classroomId,teacherId,c.courseListId,surveyId,classPeriodId,courseName,briefInfo,eduProgramTypeId,courseHour FROM Course c INNER JOIN CourseList cl ON c.courseListId = cl.courseListId WHERE classPeriodId=?";
			PreparedStatement state = conn.prepareStatement(sqlstr);
			state.setString(1, classPeriodId);
			ResultSet rs = state.executeQuery();
			while (rs.next()) {
				CourseBean Bean = new CourseBean();
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
			return Beans;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
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

	@Override
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

	@Override
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

	@Override
	public List<String> getTeacherSpecialies(int teacherId) {
		List<String> specialties = new ArrayList<String>();
		try {
			String sqlstr = "SELECT t.memberId, sts.specialtyName FROM teacher t JOIN (SELECT teacherId, specialtyName FROM Specialty s LEFT JOIN Teacher_Specialty ts ON s.specialtyId = ts.specialtyId WHERE teacherId = ?) AS sts ON t.teacherId = sts.teacherId";
			PreparedStatement state = conn.prepareStatement(sqlstr);
			state.setInt(1, teacherId);
			ResultSet rs = state.executeQuery();
			while (rs.next()) {
				specialties.add(rs.getString("adminId"));
			}
			rs.close();
			return specialties;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public MembersBean getMembersBean(String accountName, String password) {
		StudentBean Bean = new StudentBean();
		try {
			String sqlstr = "SELECT * FROM Members where accountName=? AND password=?";
			PreparedStatement state = conn.prepareStatement(sqlstr);
			state.setString(1, accountName);
			state.setString(2, password);
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
			}
			rs.close();
			return Bean;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public ClassBean getClassBean(String classPeriodId) {
		try {
			ClassBean Bean = new ClassBean();
			String sqlstr = "SELECT * FROM Class WHERE classPeriodId = ?";
			PreparedStatement state = conn.prepareStatement(sqlstr);
			state.setString(1, classPeriodId);
			ResultSet rs = state.executeQuery();
			while (rs.next()) {
				Bean.setClassPeriodId(rs.getString("classPeriodId"));
				Bean.setClassroomId(rs.getString("classroomId"));
				Bean.setStartDate(rs.getDate("startDate"));
				Bean.setEndDate(rs.getDate("endDate"));
				Bean.setEduProgramTypeName(rs.getString("eduProgramTypeName"));
				Bean.setEduProgramNumber(rs.getInt("eduProgramNumber"));
			}
			rs.close();
			return Bean;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<ClassBean> getClassBeanList() {
		List<ClassBean> Beans = new ArrayList<ClassBean>();
		try {
			String sqlstr = "SELECT * FROM Class WHERE CURRENT_TIMESTAMP > startDate AND CURRENT_TIMESTAMP < endDate";
			PreparedStatement state = conn.prepareStatement(sqlstr);
			ResultSet rs = state.executeQuery();
			while (rs.next()) {
				ClassBean Bean = new ClassBean();
				Bean.setClassPeriodId(rs.getString("classPeriodId"));
				Bean.setClassroomId(rs.getString("classroomId"));
				Bean.setStartDate(rs.getDate("startDate"));
				Bean.setEndDate(rs.getDate("endDate"));
				Bean.setEduProgramTypeName(rs.getString("eduProgramTypeName"));
				Bean.setEduProgramNumber(rs.getInt("eduProgramNumber"));
				Beans.add(Bean);
			}
			rs.close();
			return Beans;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}