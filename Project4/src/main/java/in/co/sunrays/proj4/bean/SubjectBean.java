
package in.co.sunrays.proj4.bean;

/**
 * Subject JavaBean encapsulates Subject attributes.
 *
 * @author Proxy
 * @version 1.0 Copyright (c) Proxy
 */
public class SubjectBean extends BaseBean {
	/** The course id. */
	private int courseId;
	
	/** The course name. */
	private String courseName;
	
	/** The subject name. */
	private String subjectName;
	
	/** The description. */
	private String description;

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
	 * Gets the description.
	 *
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Sets the description.
	 *
	 * @param description the new description
	 */
	public void setDescription(String description) {
		this.description = description;
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
		
		return subjectName;
	}

	public int compareTo(BaseBean arg0) {
		
		return 0;
	}

	public String getValue1() {
		
		return null;
	}

	}



