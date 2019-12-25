package _util.model;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

public class ClassroomBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private String classroomId;
	private int capacity;
	private String roomType;
//	private CourseBean course;
	
	public ClassroomBean() { }
	
	public ClassroomBean(String classroomId, int capacity, String roomType) {
		super();
		this.classroomId = classroomId;
		this.capacity = capacity;
		this.roomType = roomType;
	}

	public String getClassroomId() {
		return classroomId;
	}

	public void setClassroomId(String classroomId) {
		this.classroomId = classroomId;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	
//	public CourseBean getCourse() {
//		return course;
//	}
//
//	public void setCourse(CourseBean course) {
//		this.course = course;
//	}
	
}
