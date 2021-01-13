package in.co.sunrays.proj4.bean;
/**
 * Marksheet JavaBean encapsulates Marksheet attributes
 *
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 *
 */


public class MarksheetBean extends BaseBean {
	 /**
     * Rollno of Student
     */
    private String rollNo;
    /**
     * ID of Student
     */
    private long studentId;
    /**
     * Name of Student
     */
    private String name;
    /**
     * Physics marks of Student
     */
    private int physics;
    /**
     * Chemistry marks of Student
     */
    private int chemistry;
    /**
     * Mathematics marks of Student
     */
    private int maths;


	/**
	 * Gets the rollno of Student.
	 *
	 * @return the rollno of Student
	 */
   
  

    public String getRollNo() {
		return rollNo;
	}
    /**
	 * Sets the rollno of Student.
	 *
	 * @param rollNo the new rollno of Student
	 */
	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}

	/**
	 * Gets the studentId of Student.
	 *
	 * @return the studentId of Student
	 */
	public long getStudentId() {
		return studentId;
	}

	/**
	 * Sets the studentId of Student.
	 *
	 * @param studentId the new studentId of Student
	 */
	public void setStudentId(long studentId) {
		this.studentId = studentId;
	}
	/**
	 * Gets the name of Student.
	 *
	 * @return the name of Student
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name of Student.
	 *
	 * @param name the new name of Student
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Gets the physics marks of Student.
	 *
	 * @return the physics marks of Student
	 */
	public int getPhysics() {
		return physics;
	}

	/**
	 * Sets the physics marks of Student.
	 *
	 * @param physics the new physics marks of Student
	 */
	public void setPhysics(int physics) {
		this.physics = physics;
	}

	/**
	 * Gets the chemistry marks of Student.
	 *
	 * @return the chemistry marks of Student
	 */
	public int getChemistry() {
		return chemistry;
	}

	/**
	 * Sets the chemistry marks of Student.
	 *
	 * @param chemistry the new chemistry marks of Student
	 */
	public void setChemistry(int chemistry) {
		this.chemistry = chemistry;
	}

	/**
	 * Gets the maths marks of Student.
	 *
	 * @return the maths marks of Student
	 */
	public int getMaths() {
		return maths;
	}
	/**
	 * Sets the maths marks of Student.
	 *
	 * @param maths the new maths marks of Student
	 */
	public void setMaths(int maths) {
		this.maths = maths;
	}

	  public String getKey() {
	        return id + "";
	    }
	
	public String getValue() {
        return rollNo;
    }

	public int compareTo(BaseBean arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getValue1() {
		// TODO Auto-generated method stub
		return null;
	}

}

	

