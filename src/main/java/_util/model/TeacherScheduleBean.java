package _util.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

public class TeacherScheduleBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private int tScheduleId;
	private int teacherId;
//	private int memberId;
	private int year;
	private int month;
	private String dailyScheduleM;
	private String dailyScheduleAN;
	private String dailyScheduleN;
	
	public TeacherScheduleBean() { }
	
//public TeacherScheduleBean(int tScheduleId, int year, int month, String dailyScheduleM, String dailyScheduleAN,
//			String dailyScheduleN) {
//		super();
//		this.tScheduleId = tScheduleId;
//		this.year = year;
//		this.month = month;
//		this.dailyScheduleM = dailyScheduleM;
//		this.dailyScheduleAN = dailyScheduleAN;
//		this.dailyScheduleN = dailyScheduleN;
//	}

//public TeacherScheduleBean(int tScheduleId, int memberId, int year, int month, String dailyScheduleM,
//			String dailyScheduleAN, String dailyScheduleN) {
//		super();
//		this.tScheduleId = tScheduleId;
//		this.memberId = memberId;
//		this.year = year;
//		this.month = month;
//		this.dailyScheduleM = dailyScheduleM;
//		this.dailyScheduleAN = dailyScheduleAN;
//		this.dailyScheduleN = dailyScheduleN;
//	}

	public TeacherScheduleBean(int tScheduleId, int teacherId, int year, int month, String dailyScheduleM, String dailyScheduleAN, String dailyScheduleN) {
		super();
		this.tScheduleId = tScheduleId;
		this.teacherId = teacherId;
		this.year = year;
		this.month = month;
		this.dailyScheduleM = dailyScheduleM;
		this.dailyScheduleAN = dailyScheduleAN;
		this.dailyScheduleN = dailyScheduleN;
	}

	public int gettScheduleId() {
		return tScheduleId;
	}

	public void settScheduleId(int pScheduleId) {
		this.tScheduleId = pScheduleId;
	}
	
	public int getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public String getDailyScheduleM() {
		return dailyScheduleM;
	}

	public void setDailyScheduleM(String dailyScheduleM) {
		this.dailyScheduleM = dailyScheduleM;
	}

	public String getDailyScheduleAN() {
		return dailyScheduleAN;
	}

	public void setDailyScheduleAN(String dailyScheduleAN) {
		this.dailyScheduleAN = dailyScheduleAN;
	}

	public String getDailyScheduleN() {
		return dailyScheduleN;
	}

	public void setDailyScheduleN(String dailyScheduleN) {
		this.dailyScheduleN = dailyScheduleN;
	}
	
}
