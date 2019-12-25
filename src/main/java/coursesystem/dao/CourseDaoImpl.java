package coursesystem.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import _util.model.eduProgramSquenceBean;
import coursesystem.model.ClassBean;
import coursesystem.model.CourseBean;
import coursesystem.model.CourseListBean;

public class CourseDaoImpl implements CourseDao {

	private Connection conn;

	public CourseDaoImpl() {
	}
	
	public CourseDaoImpl(Connection conn) {
		super();
		this.conn = conn;
	}

	@Override
	public int saveClass(ClassBean cb) {
		String sql = "insert into class "
				+ " (classPeriodId, startDate, endDate, classroomId, eduProgramTypeName, eduProgramNumber)"
				+ " values (?, ?, ?, ?, ?, ?)";
		int n = 0;
		try (PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setString(1, cb.getClassPeriod());
			ps.setDate(2, new Date(cb.getStartDate().getTime()));
			ps.setDate(3, new Date(cb.getEndDate().getTime()));
			ps.setString(4, cb.getClassroomId());
			ps.setString(5, cb.getEduProgramTypeName());
			ps.setInt(6, cb.getPeriodNumber());
			n = ps.executeUpdate();
		} catch (SQLIntegrityConstraintViolationException e) {
			throw new RuntimeException("(班級期別重複)");
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("CourseDao類別#saveClass()發生例外: " + ex.getMessage());
		}
		return n;
	}

	@Override
	public int saveCourse(CourseBean course) {
		String sql = "insert into Course " + " (classroomId, teacherId, courseListId, surveyId, classPeriodId)"
				+ " values (?, ?, ?, ?, ?)";
		int n = 0;
		try (PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setString(1, course.getClassroomId());
			ps.setInt(2, course.getTeachedId());
			ps.setInt(3, course.getCourseListId());
			ps.setInt(4, course.getSurveyId());
			ps.setString(5, course.getClassPeriodId());
			n = ps.executeUpdate();
		}

		catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("CourseDao類別#saveCourse()發生例外: " + ex.getMessage());
		}
		return n;
	}
	@Override
	public int saveProgramSeq(eduProgramSquenceBean psb) {
		String sql = "insert into EduProgramSequence (eduProgramTypeId, eduProgramTypeName, eduProgramNumber)"
				+ " values (?, ?, ?)";
		int n = 0;
		try (PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setInt(1, psb.getEduProgramTypeId());
			ps.setString(2, psb.getEduProgramTypeName());
			ps.setInt(3, psb.getEduProgramNumber());
			n = ps.executeUpdate();
		}
		catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("CourseDao類別#saveCourse()發生例外: " + ex.getMessage());
		}
		return n;
	}

	@Override
	public List<CourseListBean> getCourseList(String eduProgramTypeName) {
		List<CourseListBean> beans = new ArrayList<>();
		String sql = "select * from courseList where eduProgramTypeId = (select EduProgramTypeid from EduProgramType where EduProgramTypeName = ?)";
		try (PreparedStatement state = conn.prepareStatement(sql);) {
			state.setString(1, eduProgramTypeName);
			ResultSet rs = state.executeQuery();
			while (rs.next()) {
				CourseListBean bean = new CourseListBean();
				bean.setCourseListId(rs.getInt("courseListId"));
				bean.setCourseName(rs.getString("courseName"));
				bean.setBriefInfo(rs.getString("briefInfo"));
				bean.setEduProgramTypeId(rs.getInt("eduProgramTypeId"));
				bean.setCourseHour(rs.getInt("courseHour"));
				beans.add(bean);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			throw new RuntimeException("CourseDao類別#getCourseList()發生例外: " + ex.getMessage());
		}
		return beans;
	}

	@Override
	public List<eduProgramSquenceBean> getPropram() {
		List<eduProgramSquenceBean> list = new ArrayList<>();
		String sql = "SELECT EduProgramTypeName, MAX(EduProgramNumber) AS EduProgramNumber FROM EduProgramSequence GROUP BY eduProgramTypeName";
		try (PreparedStatement state = conn.prepareStatement(sql);) {
			try (ResultSet rs = state.executeQuery();) {
				while (rs.next()) {
					eduProgramSquenceBean bean = new eduProgramSquenceBean();
					bean.setEduProgramTypeName(rs.getString("eduProgramTypeName"));
					bean.setEduProgramNumber(rs.getInt("eduProgramNumber"));
					list.add(bean);
				}
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new RuntimeException("#getPropram Error" + ex.getMessage());
		}
		return list;
	}

	@Override
	public void setConnection(Connection conn) {
		this.conn = conn;
	}
}
