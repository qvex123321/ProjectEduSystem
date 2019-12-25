package coursesystem.model;

import java.io.Serializable;

public class CourseListBean implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	int courseListId ; 
	String courseName ; 
	String briefInfo; 
	int eduProgramTypeId; 
	int courseHour;

	
	public CourseListBean() {
	}
	
	
	public CourseListBean(int courseListId, String courseName, String briefInfo, int eduProgramTypeId, int courseHour) {
		super();
		this.courseListId = courseListId;
		this.courseName = courseName;
		this.briefInfo = briefInfo;
		this.eduProgramTypeId = eduProgramTypeId;
		this.courseHour = courseHour;
	}


	public int getCourseListId() {
		return courseListId;
	}
	public void setCourseListId(int courseListId) {
		this.courseListId = courseListId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getBriefInfo() {
		return briefInfo;
	}
	public void setBriefInfo(String briefInfo) {
		this.briefInfo = briefInfo;
	}
	public int getEduProgramTypeId() {
		return eduProgramTypeId;
	}
	public void setEduProgramTypeId(int eduProgramTypeId) {
		this.eduProgramTypeId = eduProgramTypeId;
	}
	public int getCourseHour() {
		return courseHour;
	}
	public void setCourseHour(int courseHour) {
		this.courseHour = courseHour;
	}

}
