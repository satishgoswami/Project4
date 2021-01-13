package in.co.sunrays.proj4.bean;

import java.sql.Timestamp;
import java.util.Date;
/**
 * User JavaBean encapsulates User attributes.
 *
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */
public class UserBean extends BaseBean {

	/** First Name of User. */
	private String firstName;
	/** Last Name of User. */
	private String lastName;
	/** Login of User. */
	private String login;
	/** password of User. */
	private String password;
	/** Confirm password of User. */
	private String confirmPassword;
	/** DOB of User. */
	private Date dob;
	/** address of User. */
	private String address;
	/** Mobile No of User. */
	private String mobileNo;
	/** role id of User. */
	private long roleId;
	/** Number of unsuccessful login attempt. */
	private int unSuccessfulLogin;
	/** gender of User. */
	private String gender;
	/** Last login timestamp. */
	private Timestamp lastLogin;
	/** User Lock. */
	private String lock;
	/**
     * IP Address of User from where User was registred.
     */

	private String registeredIP;
	/** IP Address of User of his last login. */
	private String lastLoginIP;

	/** Number of unsuccessful login attempt. */
	private String roleName;

	
	/**
	 * Gets the number of unsuccessful login attempt.
	 *
	 * @return the number of unsuccessful login attempt
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * Sets the number of unsuccessful login attempt.
	 *
	 * @param roleName the new number of unsuccessful login attempt
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	/**
	 * Gets the first Name of User.
	 *
	 * @return the first Name of User
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the first Name of User.
	 *
	 * @param firstName the new first Name of User
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Gets the last Name of User.
	 *
	 * @return the last Name of User
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the last Name of User.
	 *
	 * @param lastName the new last Name of User
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Gets the login of User.
	 *
	 * @return the login of User
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Sets the login of User.
	 *
	 * @param login the new login of User
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * Gets the password of User.
	 *
	 * @return the password of User
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password of User.
	 *
	 * @param password the new password of User
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * Gets the confirm password of User.
	 *
	 * @return the confirm password of User
	 */
	public String getConfirmPassword() {
		return confirmPassword;
	}

	/**
	 * Sets the confirm password of User.
	 *
	 * @param confirmPassword the new confirm password of User
	 */
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	/**
	 * Gets the dOB of User.
	 *
	 * @return the dOB of User
	 */
	public Date getDob() {
		return dob;
	}

	/**
	 * Sets the dOB of User.
	 *
	 * @param dob the new dOB of User
	 */
	public void setDob(Date dob) {
		this.dob = dob;
	}

	/**
	 * Gets the mobile No of User.
	 *
	 * @return the mobile No of User
	 */
	public String getMobileNo() {
		return mobileNo;
	}

	/**
	 * Sets the mobile No of User.
	 *
	 * @param mobileNo the new mobile No of User
	 */
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	/**
	 * Gets the role id of User.
	 *
	 * @return the role id of User
	 */
	public long getRoleId() {
		return roleId;
	}

	/**
	 * Sets the role id of User.
	 *
	 * @param roleId the new role id of User
	 */
	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	/**
	 * Gets the number of unsuccessful login attempt.
	 *
	 * @return the number of unsuccessful login attempt
	 */
	public int getUnSuccessfulLogin() {
		return unSuccessfulLogin;
	}

	/**
	 * Sets the un successfull login.
	 *
	 * @param unSuccessfulLogin the new un successfull login
	 */
	public void setUnSuccessfullLogin(int unSuccessfulLogin) {
		this.unSuccessfulLogin = unSuccessfulLogin;
	}

	/**
	 * Sets the number of unsuccessful login attempt.
	 *
	 * @param unSuccessfulLogin the new number of unsuccessful login attempt
	 */
	public void setUnSuccessfulLogin(int unSuccessfulLogin) {
		this.unSuccessfulLogin = unSuccessfulLogin;
	}

	/**
	 * Gets the gender of User.
	 *
	 * @return the gender of User
	 */
	public String getGender() {
		return gender;
	}

	/**
	 * Sets the gender of User.
	 *
	 * @param gender the new gender of User
	 */
	public void setGender(String gender) {
		this.gender = gender;
	}

	/**
	 * Gets the last login timestamp.
	 *
	 * @return the last login timestamp
	 */
	public Timestamp getLastLogin() {
		return lastLogin;
	}

	/**
	 * Sets the last login timestamp.
	 *
	 * @param lastLogin the new last login timestamp
	 */
	public void setLastLogin(Timestamp lastLogin) {
		this.lastLogin = lastLogin;
	}

	/**
	 * Gets the user Lock.
	 *
	 * @return the user Lock
	 */
	public String getLock() {
		return lock;
	}

	/**
	 * Sets the user Lock.
	 *
	 * @param lock the new user Lock
	 */
	public void setLock(String lock) {
		this.lock = lock;
	}

	/**
	 * Gets the iP Address of User from where User was registred.
	 *
	 * @return the iP Address of User from where User was registred
	 */
	public String getRegisteredIP() {
		return registeredIP;
	}

	/**
	 * Sets the iP Address of User from where User was registred.
	 *
	 * @param registeredIP the new iP Address of User from where User was registred
	 */
	public void setRegisteredIP(String registeredIP) {
		this.registeredIP = registeredIP;
	}

	/**
	 * Gets the iP Address of User of his last login.
	 *
	 * @return the iP Address of User of his last login
	 */
	public String getLastLoginIP() {
		return lastLoginIP;
	}

	/**
	 * Sets the iP Address of User of his last login.
	 *
	 * @param lastLoginIP the new iP Address of User of his last login
	 */
	public void setLastLoginIP(String lastLoginIP) {
		this.lastLoginIP = lastLoginIP;
	}



	/**
	 * Gets the address of User.
	 *
	 * @return the address of User
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * Sets the address of User.
	 *
	 * @param address the new address of User
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/* (non-Javadoc)
	 * @see in.co.rays.project4.bean.DropdownListBean#getKey()
	 */
	public String getKey() {
		return id+"";
	}

	/* (non-Javadoc)
	 * @see in.co.rays.project4.bean.DropdownListBean#getvalue()
	 */
	public String getvalue() {
		return firstName+ ""+lastName;
	}

	public String getValue() {
		
		return null;
	}

	public String getValue1() {
		
		return null;
	}

	public int compareTo(BaseBean arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

}

	