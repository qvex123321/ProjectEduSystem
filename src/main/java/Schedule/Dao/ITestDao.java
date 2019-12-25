package Schedule.Dao;

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

public interface ITestDao {

	// 拿ScheduleBean
	TeacherScheduleBean getTeacherScheduleBean(int teacherId, int year, int month);

	ClassScheduleBean getClassScheduleBean(String classPeriodId, int year, int month);

	RoomScheduleBean getRoomScheduleBean(String classroomId, int year, int month);

	List<RoomScheduleBean> getRoomScheduleBean(String classroomId);

	void testInsert();

	List<CourseListBean> getCourseListBeanList(int eduProgramTypeId);

	// 拿courseBean
	CourseBean getCourseBean(int courseId);

	TeacherBean getTeacherBean(int teacherId);

	StudentBean getStudentBean(int StudentId);

	AdminStaffBean getAdminStaffBean(int adminId);

	List<String> getTeacherSpecialies(int teacherId);

	MembersBean getMembersBean(String accountName, String password);

	ClassBean getClassBean(String classPeriodId);

	List<ClassBean> getClassBeanList();

	List<CourseBean> getCourseBeanList(String classPeriodId);

}