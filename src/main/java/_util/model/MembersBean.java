package _util.model;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

public class MembersBean implements Serializable {
	private static final long serialVersionUID = 1L;
	Integer memberId;
	String firstName;
	String lastName;
	String accountName;
	String password;
	String address;
	String email;
	String gender;
	String telePhone;
	String cellPhone;
	Date birthDate;
	Integer privilegeId;
	Timestamp registeredTime;
	Blob memberImage;
	String imageFileName;
	Timestamp modifiedTime;
	Integer activeStatus;

	public MembersBean() {}

	public MembersBean(Integer memberId, String firstName, String lastName, String accountName, String password,
			String address, String email, String gender, String telePhone, String cellPhone, Date birthDate,
			Integer privilegeId, Timestamp registeredTime, Blob memberImage, String imageFileName,
			Timestamp modifiedTime, Integer activeStatus) {
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
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getTelePhone() {
		return telePhone;
	}

	public void setTelePhone(String telePhone) {
		this.telePhone = telePhone;
	}

	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String celePhone) {
		this.cellPhone = celePhone;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Integer getPrivilegeId() {
		return privilegeId;
	}

	public void setPrivilegeId(Integer privilegeId) {
		this.privilegeId = privilegeId;
	}

	public Timestamp getRegisteredTime() {
		return registeredTime;
	}

	public void setRegisteredTime(Timestamp registeredTime) {
		this.registeredTime = registeredTime;
	}

	public Blob getMemberImage() {
		return memberImage;
	}

	public void setMemberImage(Blob memberImage) {
		this.memberImage = memberImage;
	}

	public String getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}

	public Timestamp getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(Timestamp modifiedTime) {
		this.modifiedTime = modifiedTime;
	}

	public Integer getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(Integer activeStatus) {
		this.activeStatus = activeStatus;
	}

}
