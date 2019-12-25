package _util.model;

import java.io.Serializable;

public class ClassScheduleBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private int cScheduleId;
	private String classPeriodId;
	private int year;
	private int month;
	private String dailyScheduleM;
	private String dailyScheduleAN;
	private String dailyScheduleN;
	
	public ClassScheduleBean() { }
	
	public ClassScheduleBean(int cScheduleId, String classPeriodId, int year, int month, String dailyScheduleM, String dailyScheduleAN, String dailyScheduleN) {
		super();
		this.cScheduleId = cScheduleId;
		this.classPeriodId = classPeriodId;
		this.year = year;
		this.month = month;
		this.dailyScheduleM = dailyScheduleM;
		this.dailyScheduleAN = dailyScheduleAN;
		this.dailyScheduleN = dailyScheduleN;
	}

	public int getcScheduleId() {
		return cScheduleId;
	}

	public void setcScheduleId(int cScheduleId) {
		this.cScheduleId = cScheduleId;
	}

	public String getClassPeriodId() {
		return classPeriodId;
	}

	public void setClassPeriodId(String classPeriodId) {
		this.classPeriodId = classPeriodId;
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

	@Override
	public String toString() {
		return "ClassScheduleBean [cScheduleId=" + cScheduleId + ", classPeriodId=" + classPeriodId + ", year=" + year
				+ ", month=" + month + ", dailyScheduleM=" + dailyScheduleM + ", dailyScheduleAN=" + dailyScheduleAN
				+ ", dailyScheduleN=" + dailyScheduleN + "]";
	}
}
