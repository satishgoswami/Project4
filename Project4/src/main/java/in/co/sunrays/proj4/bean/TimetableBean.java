package in.co.sunrays.proj4.bean;

import java.util.Date;
/**
 * Timetable JavaBean encapsulates Timetable attributes
 * 
 * @author Proxy
 * @version 1.0
 * Copyright (c) Proxy
 * 
 */
public class TimetableBean extends BaseBean {
	/** The course id. */
	private int courseId;
	
	/** The course name. */
	private String courseName;
	
	/** The subject id. */
	private int subjectId;
	
	/** The subject name. */
	private String subjectName;
	
	/** The semester. */
	private String semester;
	
	/** The exam time. */
	private String examTime;
	
	/** The exam date. */
	private Date examDate;
	
	/**
	 * Gets the course id.
	 *
	 * @return the course id
	 */
	public int getCourseId() {
		return courseId;
	}

	/**
	 * Sets the course id.
	 *
	 * @param courseId the new course id
	 */
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	/**
	 * Gets the course name.
	 *
	 * @return the course name
	 */
	public String getCourseName() {
		return courseName;
	}

	/**
	 * Sets the course name.
	 *
	 * @param courseName the new course name
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	/**
	 * Gets the subject id.
	 *
	 * @return the subject id
	 */
	public int getSubjectId() {
		return subjectId;
	}

	/**
	 * Sets the subject id.
	 *
	 * @param subjectId the new subject id
	 */
	public void setSubjectId(int subjectId) {
		this.subjectId = subjectId;
	}

	/**
	 * Gets the subject name.
	 *
	 * @return the subject name
	 */
	public String getSubjectName() {
		return subjectName;
	}

	/**
	 * Sets the subject name.
	 *
	 * @param subjectName the new subject name
	 */
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	/**
	 * Gets the semester.
	 *
	 * @return the semester
	 */
	public String getSemester() {
		return semester;
	}

	/**
	 * Sets the semester.
	 *
	 * @param semester the new semester
	 */
	public void setSemester(String semester) {
		this.semester = semester;
	}

	/**
	 * Gets the exam time.
	 *
	 * @return the exam time
	 */
	public String getExamTime() {
		return examTime;
	}

	/**
	 * Sets the exam time.
	 *
	 * @param examTime the new exam time
	 */
	public void setExamTime(String examTime) {
		this.examTime = examTime;
	}

	/**
	 * Gets the exam date.
	 *
	 * @return the exam date
	 */
	public Date getExamDate() {
		return examDate;
	}

	/**
	 * Sets the exam date.
	 *
	 * @param examDate the new exam date
	 */
	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}

	/**
	 * Gets the key.
	 *
	 * 
	 */
	public String getKey() {
		// TODO Auto-generated method stub
		return id+"";
	}

	/**
	 * Gets the Value.
	 *
	 * 
	 */
	public String getValue() {
		
		return courseName;
	}

	public int compareTo(BaseBean arg0) {
		
		return getValue().compareTo(getValue());
	}

	public String getValue1() {
		
		return null;
	}

	
	
}



