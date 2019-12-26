package coursesystem.dao;

import java.sql.Connection;
import java.util.List;

import _util.model.ClassBean;
import _util.model.CourseBean;
import _util.model.CourseListBean;
import _util.model.eduProgramSquenceBean;

public interface CourseDao {

	public int saveClass(ClassBean cb);

	List<CourseListBean> getCourseList(String eduProgramTypeId);

	public int saveCourse(CourseBean course);

	void setConnection(Connection conn);

	int saveProgramSeq(eduProgramSquenceBean psb);

	List<eduProgramSquenceBean> getProgram();

}