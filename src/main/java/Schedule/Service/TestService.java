package Schedule.Service;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import Schedule.Dao.ITestDao;
import Schedule.Dao.TestDaoImpl;
import _util.model.StudentBean;
import _util.model.TeacherBean;

public class TestService {
	Connection conn;

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

	public void testTeacherBean() {
//		TestService ts = new TestService();
//		ts.initCon();
//		Connection conn = ts.conn;
//		ts.initCon();
		initCon();
		ITestDao dao = new TestDaoImpl(conn);
		TeacherBean tb = dao.getTeacherBean(1);
		System.out.println(tb.toString());
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

	public void testStudentBean() {
		initCon();
		ITestDao dao = new TestDaoImpl(conn);
		StudentBean tb = dao.getStudentBean(1);
		System.out.println(tb.toString());
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
