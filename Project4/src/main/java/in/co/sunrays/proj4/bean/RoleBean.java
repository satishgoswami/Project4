package in.co.sunrays.proj4.bean;

/**
 * Role JavaBean encapsulates Role attributes
 *
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 *
 */

public class RoleBean extends BaseBean {
	 /**
     * Predefined Role constants
     */
	  public static final int ADMIN = 1;
	  /** The Constant STUDENT. */
	    public static final int STUDENT = 2;

		/** The Constant COLLEGE. */
	    public static final int COLLEGE = 4;
	    /** The Constant KIOSK. */
	    public static final int KIOSK = 5;

		/** The Constant FACULTY. */
	    public static final int FACULTY=3;
	    
	    /**
	     * Role Name
	     */

	    private String name;

	    /**
	     * Role Description
	     */
	    private String description;
	    /**
		 * Gets the name.
		 *
		 * @return the name
		 */

	    public String getName() {
	        return name;
	    }
	    /**
		 * Sets the name.
		 *
		 * @param name the new name
		 */
	    public void setName(String name) {
	        this.name = name;
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

	    public String getKey() {
	        return id + "";
	    }

	    public String getValue() {
	        return name;
	    }

		public int compareTo(BaseBean arg0) {
			
			return 0;
		}

		public String getValue1() {
			
			return null;
		}

	}




