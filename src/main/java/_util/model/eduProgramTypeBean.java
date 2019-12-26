package _util.model;

public class eduProgramTypeBean {

	private Integer eduProgramTypeId;
	private String eduProgramTypeName;
	private String eduProgramTypeFullName;
	
	public eduProgramTypeBean() {}

	public eduProgramTypeBean(Integer eduProgramTypeId, String eduProgramTypeName, String eduProgramTypeFullName) {
		this.eduProgramTypeId = eduProgramTypeId;
		this.eduProgramTypeName = eduProgramTypeName;
		this.eduProgramTypeFullName = eduProgramTypeFullName;
	}

	public Integer getEduProgramTypeId() {
		return eduProgramTypeId;
	}

	public void setEduProgramTypeId(Integer eduProgramTypeId) {
		this.eduProgramTypeId = eduProgramTypeId;
	}

	public String getEduProgramTypeName() {
		return eduProgramTypeName;
	}

	public void setEduProgramTypeName(String eduProgramTypeName) {
		this.eduProgramTypeName = eduProgramTypeName;
	}

	public String getEduProgramTypeFullName() {
		return eduProgramTypeFullName;
	}

	public void setEduProgramTypeFullName(String eduProgramTypeFullName) {
		this.eduProgramTypeFullName = eduProgramTypeFullName;
	}
	
	
}
