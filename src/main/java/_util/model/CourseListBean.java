package _util.model;

import java.io.Serializable;

public class CourseListBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private int courseListId;
	private String courseName;
	private int eduProgramTypeId;
	private String briefInfo;
	private int courseHour;
	
	public CourseListBean() {}

	public CourseListBean(int courseListId, String courseName, int eduProgramTypeId, String briefInfo, int courseHour) {
		super();
		this.courseListId = courseListId;
		this.courseName = courseName;
		this.eduProgramTypeId = eduProgramTypeId;
		this.briefInfo = briefInfo;
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

	public int getEduProgramTypeId() {
		return eduProgramTypeId;
	}

	public void setEduProgramTypeId(int eduProgramTypeId) {
		this.eduProgramTypeId = eduProgramTypeId;
	}

	public String getBriefInfo() {
		return briefInfo;
	}

	public void setBriefInfo(String briefInfo) {
		this.briefInfo = briefInfo;
	}

	public int getCourseHour() {
		return courseHour;
	}

	public void setCourseHour(int courseHour) {
		this.courseHour = courseHour;
	}
	
}
