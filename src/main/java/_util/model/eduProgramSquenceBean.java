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


public class eduProgramSquenceBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private int eduProgramTypeId;
	private String eduProgramTypeName;
	private int eduProgramNumber;
	
	public eduProgramSquenceBean() { }

	public eduProgramSquenceBean(int eduProgramTypeId, String eduProgramTypeName, int eduProgramNumber) {
		super();
		this.eduProgramTypeId = eduProgramTypeId;
		this.eduProgramTypeName = eduProgramTypeName;
		this.eduProgramNumber = eduProgramNumber;
	}

	public int getEduProgramTypeId() {
		return eduProgramTypeId;
	}

	public void setEduProgramTypeId(int eduProgramTypeId) {
		this.eduProgramTypeId = eduProgramTypeId;
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
