package coursesystem.model;

import java.io.Serializable;

public class CourseBean extends CourseListBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	int courseId;
	int courseListId ; 
	String classPeriodId; 
	String classroomId;
	int teachedId;
	int surveyId;
	
	
	public CourseBean() {
	}
	
	public CourseBean(CourseListBean clb ,int courseId, int courseListId, String classPeriodId, String classroomId, int surveyId, int teachedId) {
		super();
		this.courseListId = clb.getCourseListId();
		this.courseName = clb.getCourseName();
		this.briefInfo = clb.getBriefInfo();
		this.eduProgramTypeId = clb.getEduProgramTypeId();
		this.courseHour = clb.getCourseHour();
		this.courseId = courseId;
		this.courseListId = courseListId;
		this.classPeriodId = classPeriodId;
		this.classroomId = classroomId;
		this.surveyId = surveyId;
		this.teachedId = teachedId;
	}

	
	public CourseBean(CourseListBean clb) {
		super();
		this.courseListId = clb.getCourseListId();
		this.courseName = clb.getCourseName();
		this.briefInfo = clb.getBriefInfo();
		this.eduProgramTypeId = clb.getEduProgramTypeId();
		this.courseHour = clb.getCourseHour();
	}
		
		
		
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public int getCourseListId() {
		return courseListId;
	}
	public void setCourseListId(int courseListId) {
		this.courseListId = courseListId;
	}
	public String getClassPeriodId() {
		return classPeriodId;
	}
	public void setClassPeriodId(String classPeriodId) {
		this.classPeriodId = classPeriodId;
	}
	public String getClassroomId() {
		return classroomId;
	}
	public void setClassroomId(String classroomId) {
		this.classroomId = classroomId;
	}
	public int getTeachedId() {
		return teachedId;
	}
	public void setTeachedId(int teachedId) {
		this.teachedId = teachedId;
	}

	public int getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(int surveyId) {
		this.surveyId = surveyId;
	}

}
