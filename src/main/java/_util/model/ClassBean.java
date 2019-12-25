package _util.model;

import java.io.Serializable;
import java.util.Date;

public class ClassBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private String classPeriodId;
	private Date startDate;
	private Date endDate;
	private String classroomId;
	private String eduProgramTypeName;
	private int eduProgramNumber;
	
	public ClassBean() { }
	
	public ClassBean(String classPeriodId, Date startDate, Date endDate, String classroomId, String eduProgramTypeName,
			int eduProgramNumber) {
		super();
		this.classPeriodId = classPeriodId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.classroomId = classroomId;
		this.eduProgramTypeName = eduProgramTypeName;
		this.eduProgramNumber = eduProgramNumber;
	}
	
	public String getClassPeriodId() {
		return classPeriodId;
	}

	public void setClassPeriodId(String classPeriodId) {
		this.classPeriodId = classPeriodId;
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

	public String getEduProgramTypeName() {
		return eduProgramTypeName;
	}

	public void setEduProgramTypeName(String eduProgramTypeName) {
		this.eduProgramTypeName = eduProgramTypeName;
	}

	public int getEduProgramNumber() {
		return eduProgramNumber;
	}

	public void setEduProgramNumber(int eduProgramNumber) {
		this.eduProgramNumber = eduProgramNumber;
	}
}
