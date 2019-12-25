package Schedule.Service;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import Schedule.Dao.ScheduleDAOImpl;
import _init.HibernateUtils;
import _util.model.ClassScheduleBean;
import _util.model.CourseBean;
import _util.model.TeacherBean;
import _util.model.TeacherScheduleBean;

public class ScheduleService implements IScheduleService {
//	ScheduleDAOImpl dao;
//	SessionFactory factory;

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

	public ScheduleService() {

//		dao = new ScheduleDAOImpl();
//		factory = HibernateUtils.getSessionFactory();
	}

	@Override
	public void testInsert() {
		initCon();
		ScheduleDAOImpl SI = new ScheduleDAOImpl(conn);
		SI.testInsert();
	}

	@Override
	public String getScheduleJSON(String MemberType, String Id) throws Exception {

		initCon();
		ScheduleDAOImpl SI = new ScheduleDAOImpl(conn);
		JSONArray Ja = new JSONArray();
		JSONArray monArray;
		JSONArray aftArray;
		JSONArray nigArray;
		if (MemberType == "Teacher") {
			TeacherScheduleBean ThisMonBean;
			TeacherScheduleBean NextMonBean;
			try {
				// 交易開始
//				tx = session.beginTransaction();
				// 拿這個月的Bean()
				ThisMonBean = SI.getTeacherScheduleBean(Integer.parseInt(Id), LocalDate.now().getYear(),
						LocalDate.now().getMonthValue());
//						new TeacherScheduleBean(1, 2, 2019, 9,
//								"[{'courseId':1, 'courseName':'中文'},{'courseId':1, 'courseName':'中文'},{'courseId':1, 'courseName':'中文'},{'courseId':1, 'courseName':'中文'},{'courseId':1, 'courseName':'中文'}]",
//								"[{'courseId':1, 'courseName':'中文'},{'courseId':1, 'courseName':'中文'},{'courseId':1, 'courseName':'中文'},{'courseId':1, 'courseName':'中文'},{'courseId':1, 'courseName':'中文'}]",
//								"[{'courseId':1, 'courseName':'中文'},{'courseId':1, 'courseName':'中文'},{'courseId':1, 'courseName':'中文'},{'courseId':1, 'courseName':'中文'},{'courseId':1, 'courseName':'中文'}]");
				NextMonBean = SI.getTeacherScheduleBean(Integer.parseInt(Id), LocalDate.now().getYear(),
						LocalDate.now().plusMonths(1).getMonthValue());
//						new TeacherScheduleBean(1, 3, 2020, 0,
//								"[{'courseId':3, 'courseName':'123'},{'courseId':1, 'courseName':'222'},{'courseId':1, 'courseName':'中文'},{'courseId':1, 'courseName':'中文'},{'courseId':1, 'courseName':'中文'}]",
//								"[{'courseId':4, 'courseName':'中文'},{'courseId':1, 'courseName':'中文'},{'courseId':1, 'courseName':'中文'},{'courseId':1, 'courseName':'中文'},{'courseId':1, 'courseName':'中文'}]",
//								"[{'courseId':5, 'courseName':'567'},{'courseId':1, 'courseName':'中文'},{'courseId':1, 'courseName':'中文'},{'courseId':1, 'courseName':'中文'},{'courseId':1, 'courseName':'中文'}]");
				monArray = new JSONArray(ThisMonBean.getDailyScheduleM());
				aftArray = new JSONArray(ThisMonBean.getDailyScheduleAN());
				nigArray = new JSONArray(ThisMonBean.getDailyScheduleN());

				buildJSON(ThisMonBean.getYear(), ThisMonBean.getMonth(), monArray, aftArray, nigArray, Ja);

				// 拿下個月的Bean
				monArray = new JSONArray(NextMonBean.getDailyScheduleM());
				aftArray = new JSONArray(NextMonBean.getDailyScheduleAN());
				nigArray = new JSONArray(NextMonBean.getDailyScheduleN());
				buildJSON(NextMonBean.getYear(), NextMonBean.getMonth(), monArray, aftArray, nigArray, Ja);
//				tx.commit();
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
		} else if (MemberType == "Student") {
			ClassScheduleBean ThisMonBean;
			ClassScheduleBean NextMonBean;
			try {
				int nowYear = LocalDate.now().getYear();
				int nowMonth = LocalDate.now().getMonthValue();
				// 交易開始
//				tx = session.beginTransaction();
				// 拿這個月的Bean()
//				Date today = new Date(System.currentTimeMillis());
//				System.out.println(today.getYear()+" , "+today.getMonth());
//				System.out.println(LocalDate.now().getYear()+" , "+LocalDate.now().getMonthValue());
//				System.out.println(Id);
				ThisMonBean = SI.getClassScheduleBean(Id, nowYear, nowMonth);
//						new ClassScheduleBean(1, 2, 2019, 9,
//								"[{'MorningCourseId':1, 'MorningCourseName':'中文', 'AfternoonCourseId':3, 'AfternoonCourseName':'English', 'NightCourseId':4, 'NightCourseName':'aaa'},{'MorningCourseId':5, 'MorningCourseName':'中文', 'AfternoonCourseId':10, 'AfternoonCourseName':'English', 'NightCourseId':4, 'NightCourseName':'aaa'}]");
//				NextMonBean = SI.getClassScheduleBean(Id, LocalDate.now().getYear(), LocalDate.now().getMonthValue());
				if (nowMonth == 12) {
					nowYear += 1;
					nowMonth = 1;
				} else {
					nowMonth += 1;
				}
				NextMonBean = SI.getClassScheduleBean(Id, nowYear, nowMonth);
//						new ClassScheduleBean(1, 3, 2020, 0,
//								"[{'MorningCourseId':1, 'MorningCourseName':'中文', 'AfternoonCourseId':3, 'AfternoonCourseName':'English', 'NightCourseId':4, 'NightCourseName':'aaa'},{'MorningCourseId':5, 'MorningCourseName':'中文', 'AfternoonCourseId':10, 'AfternoonCourseName':'English', 'NightCourseId':4, 'NightCourseName':'aaa'}]");
				monArray = new JSONArray(ThisMonBean.getDailyScheduleM());
				aftArray = new JSONArray(ThisMonBean.getDailyScheduleAN());
				nigArray = new JSONArray(ThisMonBean.getDailyScheduleN());

				buildJSON(ThisMonBean.getYear(), ThisMonBean.getMonth(), monArray, aftArray, nigArray, Ja);
//				System.out.println(Ja.toString());
//				// 拿下個月的Bean
				monArray = new JSONArray(NextMonBean.getDailyScheduleM());
				aftArray = new JSONArray(NextMonBean.getDailyScheduleAN());
				nigArray = new JSONArray(NextMonBean.getDailyScheduleN());
				buildJSON(NextMonBean.getYear(), NextMonBean.getMonth(), monArray, aftArray, nigArray, Ja);
//				tx.commit();
			} catch (Exception e) {
//				if (tx != null) {
//					tx.rollback();
//				}
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
		}
		return Ja.toString();
	}

	public String getCourseInfo(int courseId) {
//		Session session = factory.getCurrentSession();
//		Transaction tx = null;
		JSONObject JO = new JSONObject();
//		JO.put("courseId", 1);
//		JO.put("courseName", "English");
//		JO.put("briefInfo", "安安你好ㄇ");
//		JO.put("teacher", "panpan");
		initCon();
		ScheduleDAOImpl SI = new ScheduleDAOImpl(conn);
		CourseBean course;
		TeacherBean teacher;
		try {
			// 交易開始
//			tx = session.beginTransaction();
			// 拿course的Bean()，其中要抓teacher的名字，所以有抓teacher的bean
			course = SI.getCourseBean(courseId);
			teacher = SI.getTeacherBean(course.getTeacherId());

			String teacherName = teacher.getLastName() + teacher.getFirstName();
//			// JSON物件，直接傳送
			JO.put("courseID", course.getCourseId());
			JO.put("courseName", course.getCourseName());
			JO.put("briefInfo", course.getBriefInfo());
			JO.put("teacher", teacherName);
//			tx.commit();
		} catch (Exception e) {
//			if (tx != null) {
//				tx.rollback();
//			}
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
		System.out.println(JO.toString());
		return JO.toString();
	}

	@Override
	public void saveClassSchedule(String jsonString, String classPeriodId) {
//		String[] data = jsonString.split("&");
//		String jsonStr = data[0];
//		String classPeriodId = data[1];

//		List<ClassScheduleBean> csbs = parseJSON2(jsonString, classPeriodId);
		initCon();
		try {
			ScheduleDAOImpl dao = new ScheduleDAOImpl(conn);
			List<ClassScheduleBean> csbs = parseJSON(jsonString, classPeriodId);
//			System.out.println(csbs.size() + ", " + csbs.toString());
			for (ClassScheduleBean csb : csbs) {
//				System.out.println(csbs.size());
//				System.out.println(csb.toString());
				ClassScheduleBean old_csb = dao.getClassScheduleBean(csb.getClassPeriodId(), csb.getYear(),
						csb.getMonth());
				if (csb.getClassPeriodId().equals(old_csb.getClassPeriodId()) && old_csb.getYear() == csb.getYear()
						&& old_csb.getMonth() == csb.getMonth()) {
					dao.updateClassSchedule(csb);
					System.out.println("Successfully update!");
				}
				else {
					dao.insertClassSchedule(csb);
					System.out.println("Successfully insert!");
				}
			}
		} catch (Exception e) {
			System.out.println("Update Failed!");
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
	}

	private List<ClassScheduleBean> parseJSON(String jsonString, String classPeriodId) {
		List<ClassScheduleBean> csbs = new ArrayList<ClassScheduleBean>();
		JSONArray input_ja = new JSONArray(jsonString);
//		int count = 0;
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
		int prev_year = 0;
		int prev_month = 0;
		JSONArray morning_ja = new JSONArray();
		JSONArray afternoon_ja = new JSONArray();
		JSONArray night_ja = new JSONArray();

		for (int i = 0; i < input_ja.length(); i++) {
			LocalDateTime startDate = null;
			LocalDateTime endDate = null;
			JSONObject input_jo = input_ja.getJSONObject(i);
			String title = input_jo.getString("title");
			title = title.split("\\(")[0];
			try {
				startDate = LocalDateTime.parse(input_jo.getString("start"), dtf);
				endDate = LocalDateTime.parse(input_jo.getString("end"), dtf);
			} catch (JSONException e) {
				e.printStackTrace();
				new RuntimeException();
			}

			if (prev_month == 0) {
//				prev_year = startDate.getYear();
//				prev_month = startDate.getMonth();
				prev_year = startDate.getYear();
				prev_month = endDate.getMonthValue();
			}

			JSONObject output_jo = new JSONObject();
			output_jo.put("CourseId", input_jo.getInt("id"));
			output_jo.put("CourseName", title);
			output_jo.put("start", startDate);
			output_jo.put("end", endDate);

			if (prev_month == startDate.getMonthValue() && startDate.getHour() == 9) {
				morning_ja.put(output_jo);

			} else if (prev_month == startDate.getMonthValue() && startDate.getHour() == 14) {
				afternoon_ja.put(output_jo);

			} else if (prev_month == startDate.getMonthValue() && startDate.getHour() == 18) {
				night_ja.put(output_jo);
			}

			if (prev_month != startDate.getMonthValue() || i == input_ja.length() - 1) {
				ClassScheduleBean csb = new ClassScheduleBean(0, classPeriodId, prev_year, prev_month,
						morning_ja.toString(), afternoon_ja.toString(), night_ja.toString());
				csbs.add(csb);
				prev_month = 0;
				morning_ja = new JSONArray();
				afternoon_ja = new JSONArray();
				night_ja = new JSONArray();
				
				if (startDate.getHour() == 9) {
					morning_ja.put(output_jo);
				} else if (startDate.getHour() == 14) { 
					afternoon_ja.put(output_jo);
				} else if (startDate.getHour() == 18) {
					night_ja.put(output_jo);
				}
				
			}
		}
		return csbs;
	}

	private void buildJSON(int year, int month, JSONArray mornArray, JSONArray aftnArray, JSONArray nighArray,
			JSONArray ja) throws JSONException {
		// 處理JSON字串
		// 可以直接拿來用(共通)
		// 取得dailySchedule(本來為JSONString)
		for (int i = 0; i < mornArray.length(); i++) {
			JSONObject mornObject = (JSONObject) mornArray.get(i);
			// 早上區
			JSONObject morning_jo = new JSONObject();
			morning_jo.put("id", (int) mornObject.get("CourseId"));
			morning_jo.put("title", (String) mornObject.get("CourseName"));
			morning_jo.put("start", (String) mornObject.get("start"));
			morning_jo.put("end", (String) mornObject.get("end"));
			ja.put(morning_jo);
		}

		// 下午區
		for (int i = 0; i < aftnArray.length(); i++) {
			JSONObject aftnObject = (JSONObject) aftnArray.get(i);
			JSONObject afternoon_jo = new JSONObject();
			afternoon_jo.put("id", (int) aftnObject.get("CourseId"));
			afternoon_jo.put("title", (String) aftnObject.get("CourseName"));
			afternoon_jo.put("start", (String) aftnObject.get("start"));
			afternoon_jo.put("end", (String) aftnObject.get("end"));
			ja.put(afternoon_jo);
		}

		// 晚上區
		for (int i = 0; i < nighArray.length(); i++) {
			JSONObject nighObject = (JSONObject) nighArray.get(i);
			JSONObject night_jo = new JSONObject();
			night_jo.put("id", (int) nighObject.get("CourseId"));
			night_jo.put("title", (String) nighObject.get("CourseName"));
			night_jo.put("start", (String) nighObject.get("start"));
			night_jo.put("end", (String) nighObject.get("end"));
			ja.put(night_jo);
		}
	}

//	private List<ClassScheduleBean> parseJSON1(String jsonString, String classPeriodId) {
//		List<ClassScheduleBean> csbs = new ArrayList<ClassScheduleBean>();
//		JSONArray input_ja = new JSONArray(jsonString);
//		int count = 0;
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
//		int prev_year = 0;
//		int prev_month = 0;
//		JSONArray morning_ja = new JSONArray();
//		JSONArray afternoon_ja = new JSONArray();
//		JSONArray night_ja = new JSONArray();
//	
//		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//		LocalDateTime start_datetime = LocalDateTime.parse(input_ja.getJSONObject(0).getString("start"), dtf);
//		LocalDateTime end_datetime = LocalDateTime
//				.parse(input_ja.getJSONObject(input_ja.length() - 1).getString("start"), dtf);
//		int month_offset = 0;
//		if (start_datetime.getYear() != end_datetime.getYear()) {
//			int start_year = start_datetime.getYear();
//			int end_year = end_datetime.getYear();
//			while (start_year + 1 != end_year) {
//				start_year += 1;
//				month_offset += 12;
//			}
//			month_offset += (13 - start_datetime.getMonthValue()) + end_datetime.getMonthValue();
//		}
//	
//		int year_ptr = start_datetime.getYear();
//		int month_ptr = start_datetime.getMonthValue();
//		int ja_ptr = 0;
//		for (int i = 0; i < month_offset; i++) {
//			int date_ptr = 1;
//			int date_limit = start_datetime.with(TemporalAdjusters.lastDayOfMonth()).getDayOfMonth();
//	
//			while (date_ptr != date_limit + 1) {
//				JSONObject input_jo = input_ja.getJSONObject(ja_ptr);
//				System.out.println(year_ptr + "-" + month_ptr + "-" + date_ptr);
//				LocalDateTime jo_date = LocalDateTime.parse(input_jo.getString("start"), dtf);
//				boolean samedate = (jo_date.getYear() == year_ptr) && (jo_date.getMonthValue() == month_ptr)
//						&& (jo_date.getDayOfMonth() == date_ptr);
//				if (samedate && jo_date.getHour() == 9) {
//					JSONObject output_jo = new JSONObject();
//	
//					output_jo.put("CourseId", input_jo.getInt("Id"));
//					String title = input_jo.getString("title");
//					title = title.split("\\(")[0];
//					output_jo.put("CourseName", title);
//					morning_ja.put(output_jo);
//					ja_ptr += 1;
//				} else {
//					JSONObject output_jo = new JSONObject();
//	
//				}
//	
//				if (jo_date.getHour() == 14) {
//				}
//	
//				if (jo_date.getHour() == 18) {
//				}
//				date_ptr += 1;
//	
//			}
//			if (month_ptr == 12) {
//				year_ptr += 1;
//				month_ptr = 0;
//			}
//			start_datetime = start_datetime.plusMonths(1);
//			month_ptr += 1;
//		}
//		return csbs;
//	}
//
//	private List<ClassScheduleBean> parseJSON(String jsonString, String classPeriodId) {
//			List<ClassScheduleBean> csbs = new ArrayList<ClassScheduleBean>();
//			JSONArray input_ja = new JSONArray(jsonString);
//			int count = 0;
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
//			int prev_year = 0;
//			int prev_month = 0;
//			JSONArray morning_ja = new JSONArray();
//			JSONArray afternoon_ja = new JSONArray();
//			JSONArray night_ja = new JSONArray();
//	
//			for (int i = 0; i < input_ja.length(); i += 1) {
//				Date startDate = null;
//	//			Date endDate = null;
//				JSONObject input_jo = input_ja.getJSONObject(i);
//				String title = input_jo.getString("title");
//	
//				title = title.split("\\(")[0];
//	
//				try {
//					startDate = sdf.parse(input_jo.getString("start"));
//	
//	//				endDate = sdf.parse(input_jo.getString("end"));
//				} catch (JSONException e) {
//					e.printStackTrace();
//					new RuntimeException();
//				} catch (ParseException e) {
//					e.printStackTrace();
//					new RuntimeException();
//				}
//				LocalDate ld = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//	//			System.out.println(ld);
//				if (prev_month == 0) {
//	//				prev_year = startDate.getYear();
//	//				prev_month = startDate.getMonth();
//					prev_year = ld.getYear();
//					prev_month = ld.getMonthValue();
//				}
//	
//				count += 1;
//				JSONObject output_jo = new JSONObject();
//				output_jo.put("CourseId", input_jo.getInt("id"));
//				output_jo.put("CourseName", title);
//	//			System.out.println(input_jo.getInt("id")+", "+title);
//	
//				if (count == 1 && prev_month == ld.getMonthValue()) {
//					morning_ja.put(output_jo);
//	
//				} else if (count == 2 && prev_month == ld.getMonthValue()) {
//					afternoon_ja.put(output_jo);
//	
//				} else if (count == 3 && prev_month == ld.getMonthValue()) {
//					night_ja.put(output_jo);
//					count = 0;
//				}
//	//			System.out.println(prev_month+", "+ld.getMonthValue());
//				if (prev_month != ld.getMonthValue() || i == input_ja.length() - 1) {
//					ClassScheduleBean csb = new ClassScheduleBean(0, classPeriodId, prev_year, prev_month,
//							morning_ja.toString(), afternoon_ja.toString(), night_ja.toString());
//					csbs.add(csb);
//					prev_month = 0;
//					morning_ja = new JSONArray();
//					afternoon_ja = new JSONArray();
//					night_ja = new JSONArray();
//					morning_ja.put(output_jo);
//				}
//			}
//			return csbs;
//		}
//
//	private void buildJSON(int year, int month, JSONArray monArray, JSONArray aftArray, JSONArray nigArray,
//			JSONArray Ja) throws JSONException {
//		// 處理JSON字串
//		// 可以直接拿來用(共通)
//		// 取得dailySchedule(本來為JSONString)
//		for (int i = 0; i < monArray.length(); i++) {
//			// 拿到每天的課表
//			JSONObject dMDaily = (JSONObject) monArray.get(i);
//			JSONObject dADaily = (JSONObject) aftArray.get(i);
//			JSONObject dNDaily = (JSONObject) nigArray.get(i);
//			// System.out.println(dSDaily);
//			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
//
//			// 放入JSONObject中(一個時段一個)
//			// 早上區
//			JSONObject Morning = new JSONObject();
//			Calendar calendar = Calendar.getInstance();
//			Morning.put("id", (int) dMDaily.get("CourseId"));
//			Morning.put("title", (String) dMDaily.get("CourseName"));
////			calendar.set(year, month, i + 1, 9, 0, 0);
//			calendar.set(year, month - 1, i + 1, 9, 0, 0);
//			Morning.put("start", sdf.format(calendar.getTime()));
////			calendar.set(year, month, i + 1, 12, 0, 0);
//			calendar.set(year, month - 1, i + 1, 12, 0, 0);
//			Morning.put("end", sdf.format(calendar.getTime()));
//			Ja.put(Morning);
//			// 下午區
//			JSONObject Afternoon = new JSONObject();
//			Afternoon.put("id", (int) dADaily.get("CourseId"));
////			calendar.set(year, month, i + 1, 14, 0, 0);
//			calendar.set(year, month - 1, i + 1, 14, 0, 0);
//			Afternoon.put("start", sdf.format(calendar.getTime()));
////			calendar.set(year, month, i + 1, 17, 0, 0);
//			calendar.set(year, month - 1, i + 1, 17, 0, 0);
//			Afternoon.put("end", sdf.format(calendar.getTime()));
//			Afternoon.put("title", (String) dADaily.get("CourseName"));
//
//			Ja.put(Afternoon);
//			// 晚上區
//			JSONObject Night = new JSONObject();
//			Night.put("id", (int) dNDaily.get("CourseId"));
////			calendar.set(year, month, i + 1, 18, 0, 0);
//			calendar.set(year, month - 1, i + 1, 18, 0, 0);
//			Night.put("start", sdf.format(calendar.getTime()));
////			calendar.set(year, month, i + 1, 21, 0, 0);
//			calendar.set(year, month - 1, i + 1, 21, 0, 0);
//			Night.put("end", sdf.format(calendar.getTime()));
//			Night.put("title", (String) dNDaily.get("CourseName"));
//
//			Ja.put(Night);
//		}
//	}
}
