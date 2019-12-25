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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;


public class AdminStaffBean extends MembersBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private int adminId;
//	private int memberId;
	
	public AdminStaffBean() {
		super();
	}
	
	public AdminStaffBean(MembersBean member, Integer adminId) {
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
		setAdminId(adminId);
	}
	
	public AdminStaffBean(Integer memberId, String firstName, String lastName, String accountName, String password,
			String address, String email, String gender, String telePhone, String cellPhone, Date birthDate,
			Integer privilegeId, Timestamp registeredTime, Blob memberImage, String imageFileName,
			Timestamp modifiedTime, Integer activeStatus,int adminId) {
		super(memberId, firstName, lastName, accountName, password, address, email, gender, telePhone, cellPhone, birthDate,
				privilegeId, registeredTime, memberImage, imageFileName, modifiedTime, activeStatus);
		this.adminId = adminId;
	}
	public int getAdminId() {
		return adminId;
	}
	public void setAdminId(int adminId) {
		this.adminId = adminId;
	}
	
}
