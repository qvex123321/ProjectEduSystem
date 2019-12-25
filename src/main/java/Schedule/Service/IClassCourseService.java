package Schedule.Service;

public interface IClassCourseService {

	void initCon();

	String getClassJSON();

//	String getCourseListJSON(String eduProgramTypeName);

	String getCoursesJSON(String classPeriodId);

}