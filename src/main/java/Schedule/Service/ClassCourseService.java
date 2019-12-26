package Schedule.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.json.JSONArray;
import org.json.JSONObject;

import Schedule.Dao.ClassCourseDaoImpl;
import Schedule.Dao.ITestDao;
import Schedule.Dao.TestDaoImpl;
import _util.model.ClassBean;
import _util.model.CourseBean;
import _util.model.CourseListBean;
import _util.model.StudentBean;
import _util.model.TeacherBean;

public class ClassCourseService implements IClassCourseService {
	Connection conn;

	@Override
	public void initCon() {
		try {
			Context context = new InitialContext();
			DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/EduSystemDB");
			conn = ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getClassJSON() {
		initCon();
		JSONArray ja = null;
		try {
			ClassCourseDaoImpl cci = new ClassCourseDaoImpl(conn);
			List<ClassBean> classBeans = cci.getClassBeanList();
			ja = new JSONArray();
			for (ClassBean cb : classBeans) {
				JSONObject jo = new JSONObject();
				jo.put("eduProgramTypeName", cb.getEduProgramTypeName());
				jo.put("ClasPeriodId", cb.getClassPeriodId());
				ja.put(jo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return ja.toString();
	}

	@Override
	public String getCoursesJSON(String classPeriodId) {
		initCon();
		JSONArray ja = null;
		try {
			ClassCourseDaoImpl cci = new ClassCourseDaoImpl(conn);
			List<CourseBean> courseBeans = cci.getCourseBeanList(classPeriodId);
			ja = new JSONArray();
			for (CourseBean cb : courseBeans) {
				JSONObject jo = new JSONObject();
				jo.put("courseId", cb.getCourseId());
				jo.put("courseName", cb.getCourseName());
				jo.put("courseHour", cb.getCourseHour());
				jo.put("remainingHour", cb.getRemainingHour());
				ja.put(jo);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return ja.toString();
	}
}
