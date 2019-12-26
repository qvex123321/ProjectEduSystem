package _util.model;

import java.io.Serializable;

public class CourseBean extends CourseListBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private int courseId;
	private String classroomId;
	private int teacherId;
	private int surveyId;
	private String classPeriodId;
	private Integer remainingHour;

	public CourseBean() {
		super();
	}

	public CourseBean(int courseListId, String courseName, int eduProgramTypeId, String briefInfo, int courseHour,
			int courseId, String classroomId, int teacherId, String classPeriodId, int surveyId,
			Integer remainingHour) {
		super(courseListId, courseName, eduProgramTypeId, briefInfo, courseHour);
		this.courseId = courseId;
		this.classroomId = classroomId;
		this.teacherId = teacherId;
		this.surveyId = surveyId;
		this.classPeriodId = classPeriodId;
		this.setRemainingHour(remainingHour);
	}

//	public CourseBean(int courseId, int classroomId, int teacherId, String classPeriodId) {
//		super();
//		this.courseId = courseId;
//		this.classroomId = classroomId;
//		this.teacherId = teacherId;
//		this.classPeriodId = classPeriodId;
//	}

	public CourseBean(CourseListBean clb) {
		super(clb.getCourseListId(), clb.getCourseName(), clb.getEduProgramTypeId(), clb.getBriefInfo(),
				clb.getCourseHour());
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public String getClassroomId() {
		return classroomId;
	}

	public void setClassroomId(String classroomId) {
		this.classroomId = classroomId;
	}

	public int getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}

	public String getClassPeriodId() {
		return classPeriodId;
	}

	public void setClassPeriodId(String classPeriodId) {
		this.classPeriodId = classPeriodId;
	}

	public Integer getRemainingHour() {
		return remainingHour;
	}

	public void setRemainingHour(Integer remainingHour) {
		this.remainingHour = remainingHour;
	}

	public int getSurveyId() {
		return surveyId;
	}

	public void setSurveyId(int surveyId) {
		this.surveyId = surveyId;
	}

}
