package _util.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

public class SpecialtyBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private int specialtyId;
	private String specialtyName;
	
	public SpecialtyBean() {}
	
	public SpecialtyBean(int specialtyId, String specialtyName) {
		super();
		this.specialtyId = specialtyId;
		this.specialtyName = specialtyName;
	}
	public int getSpecialtyId() {
		return specialtyId;
	}
	public void setSpecialtyId(int specialtyId) {
		this.specialtyId = specialtyId;
	}
	public String getSpecialtyName() {
		return specialtyName;
	}
	public void setSpecialtyName(String specialtyName) {
		this.specialtyName = specialtyName;
	}
	
}
