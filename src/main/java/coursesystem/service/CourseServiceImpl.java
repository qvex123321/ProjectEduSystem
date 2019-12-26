package coursesystem.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.json.JSONArray;
import org.json.JSONObject;

import _util.model.ClassBean;
import _util.model.CourseBean;
import _util.model.CourseListBean;
import _util.model.eduProgramSquenceBean;
import coursesystem.dao.CourseDao;
import coursesystem.dao.CourseDaoImpl;

public class CourseServiceImpl implements CourseService {
	private CourseDao dao;
//	private DataSource ds;
	Connection conn;

	public CourseServiceImpl() {
	
	}
	
	public void getConn() {
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
	public int saveClass(String params) {
		// 資料處理
		// 新增課程會同時新增資料入三個Table Class/ Course / EduProgramSequence
		// 將傳入的JSON字串轉為物件

		ClassBean cb = formatClassBean(params);
		////////////////////////////////////////////////////////////////
		
		// 取得連線，開始交易
		getConn();
		dao = new CourseDaoImpl(conn);
		int n = 0;
		// 交易開始
		try {
			conn.setAutoCommit(false);
			dao.saveClass(cb); // 儲存班級

			// 取得此學程應該開的課程
			List<CourseListBean> courseList = dao.getCourseList(cb.getEduProgramTypeName());
			// 新增班級課程至資料表
			for (CourseListBean clb : courseList) {
				CourseBean course = new CourseBean(clb);
				course.setClassroomId(cb.getClassroomId());
				course.setTeacherId(0);
				course.setClassPeriodId(cb.getClassPeriodId());
				course.setSurveyId(-1);
				n += dao.saveCourse(course);
			}
			
			// 新增開班期數至EduProgramSequence
			eduProgramSquenceBean programSeq = new eduProgramSquenceBean();
			
			try {
				programSeq.setEduProgramTypeId(courseList.get(0).getEduProgramTypeId());
				programSeq.setEduProgramTypeName(cb.getEduProgramTypeName());
				programSeq.setEduProgramNumber(cb.getEduProgramNumber());
				dao.saveProgramSeq(programSeq);	
			} catch (Exception e) {
				throw new RuntimeException("(尚未建立學程開課列表)");
			}
		} catch (Exception e) {
			try {
				conn.rollback();
				System.out.println("交易失敗");

			} catch (SQLException e1) {
				e1.printStackTrace();
				throw new RuntimeException(e.getMessage());
			}
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				conn.setAutoCommit(true);
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException(e.getMessage());
			}
		}
		return n;
	}

	@Override
	public String getProgram() {
		JSONArray jsonAry = new JSONArray();
		getConn();
		try {
			dao = new CourseDaoImpl(conn);
			List<eduProgramSquenceBean> list = dao.getProgram();
			
			for (eduProgramSquenceBean b : list) {
				JSONObject json = new JSONObject();
				json.put("eduProgramTypeName", b.getEduProgramTypeName());
				json.put("eduProgramNumber", b.getEduProgramNumber());
				jsonAry.put(json);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return jsonAry.toString();
	}
	
	public ClassBean formatClassBean(String params) {
		JSONObject obj = new JSONObject(params);
		String program = obj.getString("program");
		int period = Integer.parseInt(obj.getString("period"));
		String classRoom = obj.getString("classRoom") != null ? obj.getString("classRoom") : "201";
		// 處理日期部分
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate = null;
		Date endDate = null;
		try {
			startDate = sdf.parse(obj.getString("startDate"));
			startDate.setDate(startDate.getDate());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("開始時間有誤:" + e.getMessage());
		}
		try {
			endDate = sdf.parse(obj.getString("endDate"));
			endDate.setDate(endDate.getDate());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("結束時間有誤:" + e.getMessage());
		}

		ClassBean cb = new ClassBean(program + period, startDate, endDate, classRoom, program, period);
		return cb;
	}

}
