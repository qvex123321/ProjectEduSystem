package Schedule.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import _util.model.ClassBean;
import _util.model.CourseBean;
import _util.model.CourseListBean;

public class ClassCourseDaoImpl {
	Connection conn;
	
	public ClassCourseDaoImpl() {
	}
	
	public ClassCourseDaoImpl(Connection conn) {
		super();
		this.conn = conn;
	}

	public List<ClassBean> getClassBeanList(){
		List<ClassBean> Beans = new ArrayList<ClassBean>();
		try {
			String sqlstr = "SELECT * FROM Class WHERE CURRENT_TIMESTAMP < endDate ORDER BY classPeriodId DESC";
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
	
	public ClassBean getClassBean(String classPeriodId){
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
	
	public List<CourseListBean> getCourseListBeanList(String eduProgramTypeName) {
		//		CourseListBean Bean = new CourseListBean();
				List<CourseListBean> Beans = new ArrayList<CourseListBean>();
				try {
					String sqlstr = "SELECT courseListId, courseName, briefInfo, EPT.eduProgramTypeId, courseHour FROM CourseList CL INNER JOIN EduProgramType EPT ON CL.eduProgramTypeId = EPT.eduProgramTypeId WHERE EPT.eduProgramTypeId = 0 OR EPT.eduProgramTypeName = ?";
					PreparedStatement state = conn.prepareStatement(sqlstr);
					state.setString(1, eduProgramTypeName);
					ResultSet rs = state.executeQuery();
					while (rs.next()) {
						CourseListBean Bean = new CourseListBean();
						Bean.setCourseListId(rs.getInt("courseListId"));
						Bean.setCourseName(rs.getString("courseName"));
						Bean.setEduProgramTypeId(rs.getInt("eduProgramTypeId"));
						Bean.setBriefInfo(rs.getString("briefInfo"));
						Bean.setCourseHour(rs.getInt("courseHour"));
						Beans.add(Bean);
					}
					rs.close();
					return Beans;
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return null;
			}
	
	public List<CourseBean> getCourseBeanList(String classPeriodId) {
		List<CourseBean> Beans = new ArrayList<CourseBean>();
		try {
			String sqlstr = "SELECT courseId,classroomId,teacherId,c.courseListId,surveyId,classPeriodId,courseName,briefInfo,eduProgramTypeId,courseHour,remainingHour FROM Course c INNER JOIN CourseList cl ON c.courseListId = cl.courseListId WHERE classPeriodId=?";
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
				Bean.setRemainingHour(rs.getInt("remainingHour"));
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
