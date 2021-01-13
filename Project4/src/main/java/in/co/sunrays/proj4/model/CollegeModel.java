package in.co.sunrays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.sunrays.proj4.bean.CollegeBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.exception.DatabaseException;
import in.co.sunrays.proj4.exception.DuplicateRecordException;
import in.co.sunrays.proj4.util.JDBCDataSource;

/**
 * JDBC Implementation of CollegeModel
 *
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */

public class CollegeModel {
	 private static Logger log = Logger.getLogger(CollegeModel.class);

	/**
	 * Find next PK of College
	 *
	 * @throws DatabaseException
	 */
	public static Integer nextPK() throws DatabaseException {
		 log.debug("Model nextPK Started");
		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pst = conn.prepareStatement("SELECT MAX(ID) FROM ST_COLLEGE");
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();

		} catch (Exception e) {
			// log.error("Database Exception..", e);
			throw new DatabaseException("Exception : Exception in getting PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		 log.debug("Model nextPK End");
		return pk + 1;
	}

	/**
	 * Add a College
	 *
	 * @param bean
	 * @throws DatabaseException
	 *
	 */
	public static long add(CollegeBean cbean) throws ApplicationException, DuplicateRecordException {
		 log.debug("Model add Started");
		Connection conn = null;
		int pk = 0;

		CollegeBean duplicateCollegeName = findByName(cbean.getName());

		if (duplicateCollegeName != null) {
			throw new DuplicateRecordException("College Name already exists");
		}

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			// Get auto-generated next primary key
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pst = conn.prepareStatement("INSERT INTO ST_COLLEGE VALUES(?,?,?,?,?,?,?,?,?,?)");
			pst.setInt(1, pk);
			pst.setString(2, cbean.getName());
			pst.setString(3, cbean.getAddress());
			pst.setString(4, cbean.getState());
			pst.setString(5, cbean.getCity());
			pst.setString(6, cbean.getPhoneNo());
			pst.setString(7, cbean.getCreatedBy());
			pst.setString(8, cbean.getModifiedBy());
			pst.setTimestamp(9, cbean.getCreateDatetime());
			pst.setTimestamp(10, cbean.getModifiedDatetime());
			pst.executeUpdate();
			conn.commit(); // End transaction
			pst.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in add College");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		 log.debug("Model add End");
		return pk;
	}

	/**
	 * Delete a College
	 *
	 * @param bean
	 * @throws DatabaseException
	 */
	public  static void delete(CollegeBean cbean) throws ApplicationException {
		 log.debug("Model delete Started");
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pst = conn.prepareStatement("DELETE FROM ST_COLLEGE WHERE ID=?");
			pst.setLong(1, cbean.getId());
			pst.executeUpdate();
			conn.commit(); // End transaction
			pst.close();

		} catch (Exception e) {
			// log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in delete college");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		 log.debug("Model delete Started");
	}

	/**
	 * Find User by College
	 *
	 * @param login
	 *            : get parameter
	 * @return bean
	 * @throws DatabaseException
	 */
	public static CollegeBean findByName(String name) throws ApplicationException {
		 log.debug("Model findByName Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_COLLEGE WHERE NAME=?");
		CollegeBean cbean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pst = conn.prepareStatement(sql.toString());
			pst.setString(1, name);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				cbean = new CollegeBean();
				cbean.setId(rs.getLong(1));
				cbean.setName(rs.getString(2));
				cbean.setAddress(rs.getString(3));
				cbean.setState(rs.getString(4));
				cbean.setCity(rs.getString(5));
				cbean.setPhoneNo(rs.getString(6));
				cbean.setCreatedBy(rs.getString(7));
				cbean.setModifiedBy(rs.getString(8));
				cbean.setCreateDatetime(rs.getTimestamp(9));
				cbean.setModifiedDatetime(rs.getTimestamp(10));

			}
			rs.close();
		} catch (Exception e) {
			 log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting College by Name");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		 log.debug("Model findByName End");
		return cbean;
	}

	/**
	 * Find User by College
	 *
	 * @param pk
	 *            : get parameter
	 * @return bean
	 * @throws DatabaseException
	 */
	public  static CollegeBean findByPK(long pk) throws ApplicationException {
		 log.debug("Model findByPK Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_COLLEGE WHERE ID=?");
		CollegeBean cbean = null;
		Connection conn = null;
		try {

			conn = JDBCDataSource.getConnection();
			PreparedStatement pst = conn.prepareStatement(sql.toString());
			pst.setLong(1, pk);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				cbean = new CollegeBean();
				cbean.setId(rs.getLong(1));
				cbean.setName(rs.getString(2));
				cbean.setAddress(rs.getString(3));
				cbean.setState(rs.getString(4));
				cbean.setCity(rs.getString(5));
				cbean.setPhoneNo(rs.getString(6));
				cbean.setCreatedBy(rs.getString(7));
				cbean.setModifiedBy(rs.getString(8));
				cbean.setCreateDatetime(rs.getTimestamp(9));
				cbean.setModifiedDatetime(rs.getTimestamp(10));

			}
			rs.close();
		} catch (Exception e) {
			// log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting College by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		 log.debug("Model findByPK End");
		return cbean;
	}

	/**
	 * Update a College
	 *
	 * @param bean
	 * @throws DatabaseException
	 */
	public  static void update(CollegeBean bean) throws ApplicationException, DuplicateRecordException {
		 log.debug("Model update Started");
		Connection conn = null;

		// CollegeBean beanExist = findByName(bean.getName());

		// Check if updated College already exist
		// if (beanExist != null && beanExist.getId() != bean.getId()) {

		// throw new DuplicateRecordException("College is already exist");
		// }

		try {

			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pst = conn.prepareStatement(
					"UPDATE ST_COLLEGE SET NAME=?,ADDRESS=?,STATE=?,CITY=?,PHONE_NO=?,CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=? WHERE ID=?");
			pst.setString(1, bean.getName());
			pst.setString(2, bean.getAddress());
			pst.setString(3, bean.getState());
			pst.setString(4, bean.getCity());
			pst.setString(5, bean.getPhoneNo());
			pst.setString(6, bean.getCreatedBy());
			pst.setString(7, bean.getModifiedBy());
			pst.setTimestamp(8, bean.getCreateDatetime());
			pst.setTimestamp(9, bean.getModifiedDatetime());
			pst.setLong(10, bean.getId());
			pst.executeUpdate();
			conn.commit(); // End transaction
			pst.close();
		} catch (Exception e) {
			e.printStackTrace();
			 log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception in updating College ");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		 log.debug("Model update End");
	}

	/**
	 * Search College with pagination
	 *
	 * @return list : List of Users
	 * @param bean
	 *            : Search Parameters
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 *
	 * @throws DatabaseException
	 */
	public static List search(CollegeBean cbean, int pageNo, int pageSize) throws ApplicationException {
		 log.debug("Model search Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_COLLEGE WHERE 1=1");

		if (cbean != null) {
			if (cbean.getId() > 0) {
				sql.append(" AND id = " + cbean.getId());
			}
			if (cbean.getName() != null && cbean.getName().length() > 0) {
				sql.append(" AND NAME like '" + cbean.getName() + "%'");
			}
			if (cbean.getAddress() != null && cbean.getAddress().length() > 0) {
				sql.append(" AND ADDRESS like '" + cbean.getAddress() + "%'");
			}
			if (cbean.getState() != null && cbean.getState().length() > 0) {
				sql.append(" AND STATE like '" + cbean.getState() + "%'");
			}
			if (cbean.getCity() != null && cbean.getCity().length() > 0) {
				sql.append(" AND CITY like '" + cbean.getCity() + "%'");
			}
			if (cbean.getPhoneNo() != null && cbean.getPhoneNo().length() > 0) {
				sql.append(" AND PHONE_NO = " + cbean.getPhoneNo());
			}

		}

		// if page size is greater than zero then apply pagination
		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;

			sql.append(" Limit " + pageNo + ", " + pageSize);
			// sql.append(" limit " + pageNo + "," + pageSize);
		}

		ArrayList list = new ArrayList();
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pst = conn.prepareStatement(sql.toString());
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				cbean = new CollegeBean();
				cbean.setId(rs.getLong(1));
				cbean.setName(rs.getString(2));
				cbean.setAddress(rs.getString(3));
				cbean.setState(rs.getString(4));
				cbean.setCity(rs.getString(5));
				cbean.setPhoneNo(rs.getString(6));
				cbean.setCreatedBy(rs.getString(7));
				cbean.setModifiedBy(rs.getString(8));
				cbean.setCreateDatetime(rs.getTimestamp(9));
				cbean.setModifiedDatetime(rs.getTimestamp(10));
				list.add(cbean);
			}
			rs.close();
		} catch (Exception e) {
			 log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in search college");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		 log.debug("Model search End");
		return list;
	}

	/**
	 * Search College
	 *
	 * @param bean
	 *            : Search Parameters
	 * @throws DatabaseException
	 */
	public static List search(CollegeBean bean) throws ApplicationException {
		return search(bean, 0, 0);
	}

	/**
	 * Get List of College
	 *
	 * @return list : List of College
	 * @throws DatabaseException
	 */
	public static List list() throws ApplicationException {
		return list(0,0);
	}

	/**
	 * Get List of College with pagination
	 *
	 * @return list : List of College
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * @throws DatabaseException
	 */
	public static List list(int pageNo, int pageSize) throws ApplicationException {
		 log.debug("Model list Started");
		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer("select * from ST_COLLEGE");
		// if page size is greater than zero then apply pagination
		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + "," + pageSize);
		}

		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pst = conn.prepareStatement(sql.toString());
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				CollegeBean cbean = new CollegeBean();
				cbean.setId(rs.getLong(1));
				cbean.setName(rs.getString(2));
				cbean.setAddress(rs.getString(3));
				cbean.setState(rs.getString(4));
				cbean.setCity(rs.getString(5));
				cbean.setPhoneNo(rs.getString(6));
				cbean.setCreatedBy(rs.getString(7));
				cbean.setModifiedBy(rs.getString(8));
				cbean.setCreateDatetime(rs.getTimestamp(9));
				cbean.setModifiedDatetime(rs.getTimestamp(10));
				list.add(cbean);
			}
			rs.close();
		} catch (Exception e) {
			 log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting list of users");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		 log.debug("Model list End");
		return list;

	}
}
