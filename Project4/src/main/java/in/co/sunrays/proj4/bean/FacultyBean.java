package in.co.sunrays.proj4.bean;

import java.util.Date;

/**
 * Faculty JavaBean encapsulates Faculty attributes
 * 
 * @author Proxy
 * @version 1.0
 * Copyright (c) Proxy
 * 
 */
public class FacultyBean extends BaseBean{
	/** The first name. */
	private String firstName;
	
	/** The last name. */
	private String lastName;
	
	/** The gender. */
	private String gender;
	
	/** The login id. */
	private String loginId;
	
	/** The Address. */
	private String address;

	/** The date of joining. */
	private Date dateOfJoining;
	
	/** The qualification. */
	private String qualification;
	
	/** The mobile no. */
	private String mobileNo;
	
	/** The college id. */
	private int collegeId;
	
	/** The college name. */
	private String collegeName;
	
	/** The course id. */
	private int courseId;
	
	/** The course name. */
	private String courseName;
	
	/** The subject id. */
	private int subjectId;
	
	/** The subject name. */
	private String subjectName;

	/**
	 * Gets the first name.
	 *
	 * @return the first name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the first name.
	 *
	 * @param firstName the new first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Gets the last name.
	 *
	 * @return the last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the last name.
	 *
	 * @param lastName the new last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Gets the gender.
	 *
	 * @return the gender
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * Sets the gender.
	 *
	 * @param gender the new gender
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * Gets the login id.
	 *
	 * @return the login id
	 */
	public String getLoginId() {
		return loginId;
	}

	/**
	 * Sets the login id.
	 *
	 * @param loginId the new login id
	 */
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	/**
	 * Gets the Address.
	 *
	 * @return the Address
	 */

	public String getAddress(){
		return address;
	}
	
	/**
	 * Sets the Address.
	 *
	 * @param Address the new Address
	 */
	public void setAddress(String address){
		this.address=address;
	}
	/**
	 * Gets the date of joining.
	 *
	 * @return the date of joining
	 */
	public Date getDateOfJoining() {
		return dateOfJoining;
	}

	/**
	 * Sets the date of joining.
	 *
	 * @param dateOfJoining the new date of joining
	 */
	public void setDateOfJoining(Date dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	/**
	 * Gets the qualification.
	 *
	 * @return the qualification
	 */
	public String getQualification() {
		return qualification;
	}

	/**
	 * Sets the qualification.
	 *
	 * @param qualification the new qualification
	 */
	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	/**
	 * Gets the mobile no.
	 *
	 * @return the mobile no
	 */
	public String getMobileNo() {
		return mobileNo;
	}

	/**
	 * Sets the mobile no.
	 *
	 * @param mobileNo the new mobile no
	 */
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	/**
	 * Gets the college id.
	 *
	 * @return the college id
	 */
	public int getCollegeId() {
		return collegeId;
	}

	/**
	 * Sets the college id.
	 *
	 * @param collegeId the new college id
	 */
	public void setCollegeId(int collegeId) {
		this.collegeId = collegeId;
	}

	/**
	 * Gets the college name.
	 *
	 * @return the college name
	 */
	public String getCollegeName() {
		return collegeName;
	}

	/**
	 * Sets the college name.
	 *
	 * @param collegeName the new college name
	 */
	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

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
	 * Gets the key.
	 *
	 * 
	 */
	public String getKey() {
		// TODO Auto-generated method stub
		return id+"";
	}

	/**
	 * Gets the value.
	 *
	 * 
	 */
	public String getValue() {
		// TODO Auto-generated method stub
		return firstName+" "+lastName;
		
	}

	public int compareTo(BaseBean o) {
		
		return 0;
	}

	public String getValue1() {
		// TODO Auto-generated method stub
		return null;
	}
}



