package coursesystem.model;

import java.io.Serializable;
import java.util.Date;

public class ClassBean implements Serializable{


	private static final long serialVersionUID = 1L;
	
	String classPeriod ;
	String eduProgramTypeName;
	int periodNumber;
	Date startDate;
	Date endDate;
	String classroomId;
	
	
	public ClassBean() {
	}
	public ClassBean(String classPeriod, String eduProgramType, int periodNumber, Date startDate, Date endDate,
			String classroomId) {
		super();
		this.classPeriod = classPeriod;
		this.eduProgramTypeName = eduProgramType;
		this.periodNumber = periodNumber;
		this.startDate = startDate;
		this.endDate = endDate;
		this.classroomId = classroomId;
	}
	public String getClassPeriod() {
		return classPeriod;
	}
	public void setClassPeriod(String classPeriod) {
		this.classPeriod = classPeriod;
	}
	public String getEduProgramTypeName() {
		return eduProgramTypeName;
	}
	public void setEduProgramTypeName(String eduProgramType) {
		this.eduProgramTypeName = eduProgramType;
	}
	public int getPeriodNumber() {
		return periodNumber;
	}
	public void setPeriodNumber(int periodNumber) {
		this.periodNumber = periodNumber;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getClassroomId() {
		return classroomId;
	}
	public void setClassroomId(String classroomId) {
		this.classroomId = classroomId;
	}
	
	
}
