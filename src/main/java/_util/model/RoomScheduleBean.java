package _util.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

public class RoomScheduleBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private int rScheduleId;
	private String classroomId;
	private int year;
	private int month;
	private String dailyScheduleM;
	private String dailyScheduleAN;
	private String dailyScheduleN;
	
	public RoomScheduleBean() { }
	
	public RoomScheduleBean(int rScheduleId, String classroomId, int year, int month, String dailyScheduleM, String dailyScheduleAN, String dailyScheduleN) {
		super();
		this.rScheduleId = rScheduleId;
		this.classroomId = classroomId;
		this.year = year;
		this.month = month;
		this.dailyScheduleM = dailyScheduleM;
		this.dailyScheduleAN = dailyScheduleAN;
		this.dailyScheduleN = dailyScheduleN;
	}

	public int getrScheduleId() {
		return rScheduleId;
	}

	public void setrScheduleId(int rScheduleId) {
		this.rScheduleId = rScheduleId;
	}

	public String getClassroomId() {
		return classroomId;
	}

	public void setClassroomId(String classroomId) {
		this.classroomId = classroomId;
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

//	public String getDailySchedule() {
//		return dailySchedule;
//	}
//
//	public void setDailySchedule(String dailySchedule) {
//		this.dailySchedule = dailySchedule;
//	}
	
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
