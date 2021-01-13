package in.co.sunrays.proj4.bean;

public class CollegeBean extends BaseBean {
	 /**
     * Name of College
     */
    private String name;
    /**
     * Address of College
     */
    private String address;
    /**
     * State of College
     */
    private String state;
    /**
     * City of College
     */
    private String city;
    /**
     * Phoneno of College
     */
    private String phoneNo;
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
	 * Gets the address.
	 *
	 * @return the address
	 */
    public String getAddress() {
        return address;
    }

	/**
	 * Sets the address.
	 *
	 * @param address the new address
	 */
    public void setAddress(String address) {
        this.address = address;
    }

	/**
	 * Gets the state.
	 *
	 * @return the state
	 */
    public String getState() {
        return state;
    }
    /**
	 * Sets the state.
	 *
	 * @param state the new state
	 */
    public void setState(String state) {
        this.state = state;
    }
    /**
	 * Gets the city.
	 *
	 * @return the city
	 */
    public String getCity() {
        return city;
    }
    /**
	 * Sets the city.
	 *
	 * @param city the new city
	 */
    public void setCity(String city) {
        this.city = city;
    }
    /**
	 * Gets the phone no.
	 *
	 * @return the phone no
	 */
    public String getPhoneNo() {
        return phoneNo;
    }

	/**
	 * Sets the phone no.
	 *
	 * @param phoneNo the new phone no
	 */
    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    
    public String getKey() {
        return id + "";
    }

    
    public String getValue() {
        return name;
    }

	public int compareTo(BaseBean arg0) {
		// TODO Auto-generated method stub
		return getValue().compareTo(getValue());
	}

	public String getValue1() {
		// TODO Auto-generated method stub
		return null;
	}

}



