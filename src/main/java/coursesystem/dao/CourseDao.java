package coursesystem.dao;

import java.sql.Connection;
import java.util.List;

import _util.model.eduProgramSquenceBean;
import coursesystem.model.ClassBean;
import coursesystem.model.CourseBean;
import coursesystem.model.CourseListBean;

public interface CourseDao {

	public int saveClass(ClassBean cb);

	List<CourseListBean> getCourseList(String eduProgramTypeId);

	public int saveCourse(CourseBean course);

	void setConnection(Connection conn);

	public List<eduProgramSquenceBean> getPropram();

	int saveProgramSeq(eduProgramSquenceBean psb);

}