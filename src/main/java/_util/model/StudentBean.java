package _util.model;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Timestamp;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;


public class StudentBean extends MembersBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer studentId;
//	private Integer memberId;
	private String classPeriodId;
	private Integer seatNo;

	public StudentBean() {
	}

	public StudentBean(MembersBean member, Integer studentId, String classPeriodId, Integer seatNo) {
		this.memberId = member.memberId;
		this.firstName = member.firstName;
		this.lastName = member.lastName;
		this.accountName = member.accountName;
		this.password = member.password;
		this.address = member.address;
		this.email = member.email;
		this.gender = member.gender;
		this.telePhone = member.telePhone;
		this.cellPhone = member.cellPhone;
		this.birthDate = member.birthDate;
		this.privilegeId = member.privilegeId;
		this.registeredTime = member.registeredTime;
		this.memberImage = member.memberImage;
		this.imageFileName = member.imageFileName;
		this.modifiedTime = member.modifiedTime;
		this.activeStatus = member.activeStatus;
		setMemberId(memberId);
		setClassPeriodId(classPeriodId);
		setSeatNo(seatNo);
	}
	
	public StudentBean(Integer memberId, String firstName, String lastName, String accountName, String password,
			String address, String email, String gender, String telePhone, String cellPhone, Date birthDate,
			Integer privilegeId, Timestamp registeredTime, Blob memberImage, String imageFileName,
			Timestamp modifiedTime, Integer activeStatus, Integer studentId, String classPeriodId, Integer seatNo) {
		super();
		this.memberId = memberId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.accountName = accountName;
		this.password = password;
		this.address = address;
		this.email = email;
		this.gender = gender;
		this.telePhone = telePhone;
		this.cellPhone = cellPhone;
		this.birthDate = birthDate;
		this.privilegeId = privilegeId;
		this.registeredTime = registeredTime;
		this.memberImage = memberImage;
		this.imageFileName = imageFileName;
		this.modifiedTime = modifiedTime;
		this.activeStatus = activeStatus;
		setMemberId(memberId);
		setClassPeriodId(classPeriodId);
		setSeatNo(seatNo);
	}
	
	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public String getClassPeriodId() {
		return classPeriodId;
	}

	public void setClassPeriodId(String classPeriodId) {
		this.classPeriodId = classPeriodId;
	}

	public Integer getSeatNo() {
		return seatNo;
	}

	public void setSeatNo(Integer seatNo) {
		this.seatNo = seatNo;
	}

	@Override
	public String toString() {
		return "StudentBean [studentId=" + studentId + ", classPeriodId=" + classPeriodId + ", seatNo=" + seatNo
				+ ", memberId=" + memberId + ", firstName=" + firstName + ", lastName=" + lastName + ", accountName="
				+ accountName + ", password=" + password + ", address=" + address + ", email=" + email + ", gender="
				+ gender + ", telePhone=" + telePhone + ", cellPhone=" + cellPhone + ", birthDate=" + birthDate
				+ ", privilegeId=" + privilegeId + ", registeredTime=" + registeredTime + ", memberImage=" + memberImage
				+ ", imageFileName=" + imageFileName + ", modifiedTime=" + modifiedTime + ", activeStatus="
				+ activeStatus + "]";
	}
	
}
