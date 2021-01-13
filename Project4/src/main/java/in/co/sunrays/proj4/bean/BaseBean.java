package in.co.sunrays.proj4.bean;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * The Class BaseBean.
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 * 
 */
public abstract class BaseBean implements Serializable, DropdownListBean, Comparable<BaseBean> {
	/** The id. */
	protected long id;

	/** The created by. */

	protected String createdBy;

	/** The modified by. */
	protected String modifiedBy;

	/** The created date time. */
	protected Timestamp createDatetime;
	/** The modified date time. */
	protected Timestamp modifiedDatetime;
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the new id
	 */

	public void setId(long id) {
		this.id = id;
	}
	/**
	 * Gets the created by.
	 *
	 * @return the created by
	 */

	public String getCreatedBy() {
		return createdBy;
	}
	/**
	 * Sets the created by.
	 *
	 * @param createdBy
	 *            the new created by
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * Gets the modified by.
	 *
	 * @return the modified by
	 */
	public String getModifiedBy() {
		return modifiedBy;
	}

	/**
	 * Sets the modified by.
	 *
	 * @param modifiedBy
	 *            the new modified by
	 */
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	/**
	 * Gets the created date time.
	 *
	 * @return the created date time
	 */
	public Timestamp getCreateDatetime() {
		return createDatetime;
	}

	/**
	 * Sets the created date time.
	 *
	 * @param createdDateTime
	 *            the new created date time
	 */
	public void setCreateDatetime(Timestamp createDatetime) {
		this.createDatetime = createDatetime;
	}
	/**
	 * Gets the modified date time.
	 *
	 * @return the modified date time
	 */
	public Timestamp getModifiedDatetime() {
		return modifiedDatetime;
	}

	/**
	 * Sets the modified date time.
	 *
	 * @param modifiedDateTime
	 *            the new modified date time
	 */
	public void setModifiedDatetime(Timestamp modifiedDatetime) {
		this.modifiedDatetime = modifiedDatetime;
	}

}
