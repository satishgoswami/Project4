package in.co.sunrays.proj4.bean;
/**
 * The Class CourseBean.
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */
public class CourseBean extends BaseBean {
	

	/**
	 * Name of Course
	 */
	private String CourseName;
	/**
	 * Description of Course
	 */
	private String description;
	/**
	 * Duration of Course
	 */
	private String duration;
	
	
	/**
	 * accessor
	 * @return String
	 */
	public String getCourseName() {
		return CourseName;
	}

	/**
	 * Sets the course name.
	 *
	 * @param courseName the new course name
	 */
	public void setCourseName(String courseName) {
		CourseName = courseName;
	}
	/**
	 * Gets the description of Course.
	 *
	 * @return the description of Course
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * Sets the description of Course.
	 *
	 * @param description the new description of Course
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gets the duration of Course.
	 *
	 * @return the duration of Course
	 */
	public String getDuration() {
		return duration;
	}

	/**
	 * Sets the duration of Course.
	 *
	 * @param duration the new duration of Course
	 */
	public void setDuration(String duration) {
		this.duration = duration;
	}
	
	
	public String getKey() {
		return id+"";
	}
	
	
	public String getValue() {
		return CourseName;
	}
	public int compareTo(BaseBean o) {
		// TODO Auto-generated method stub
		return 0;
	}
	public String getValue1() {
		// TODO Auto-generated method stub
		return null;
	}

}



