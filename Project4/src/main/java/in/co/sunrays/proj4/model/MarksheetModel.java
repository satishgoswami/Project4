package in.co.sunrays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.sunrays.proj4.bean.MarksheetBean;
import in.co.sunrays.proj4.bean.StudentBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.exception.DatabaseException;
import in.co.sunrays.proj4.exception.DuplicateRecordException;
import in.co.sunrays.proj4.util.JDBCDataSource;

/**
 * JDBC Implementation of Marksheet Model
 *
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */

public  class MarksheetModel {
	private static Logger log = Logger.getLogger(MarksheetModel.class);

	public  static Integer nextPK() throws DatabaseException {
		 log.debug("Model nextPK Started");
		Connection conn = null;
		int pk = 0;
		try {
			conn = JDBCDataSource.getConnection();
			System.out.println("Connection Succesfully Establish");

			PreparedStatement pst = conn.prepareStatement("select max(ID) from ST_MARKSHEET");

			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();

		} catch (Exception e) {
			 log.error(e);
			throw new DatabaseException("Exception in Marksheet getting PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model nextPK Ended");
		return pk + 1;
	}

	/**
	 * Adds a Marksheet
	 *
	 * @param bean
	 * @throws DatabaseException
	 *
	 */
	public  static long add(MarksheetBean mbean) throws ApplicationException, DuplicateRecordException {

		 log.debug("Model add Started");

		Connection conn = null;

		// get Student Name
		StudentModel sModel = new StudentModel();
		System.out.println(mbean.getId());
		StudentBean studentbean = sModel.findByPK(mbean.getStudentId());
		mbean.setName(studentbean.getFirstName() + " " + studentbean.getLastName());

		MarksheetBean duplicateMarksheet = findByRollNo(mbean.getRollNo());
		int pk = 0;

		if (duplicateMarksheet != null) {
			throw new DuplicateRecordException("Roll Number already exists");
		}

		try {
			conn = JDBCDataSource.getConnection();

			// Get auto-generated next primary key
			pk = nextPK();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pst = conn.prepareStatement("INSERT INTO ST_MARKSHEET VALUES(?,?,?,?,?,?,?,?,?,?,?)");
			pst.setInt(1, pk);
			pst.setString(2, mbean.getRollNo());
			pst.setLong(3, mbean.getStudentId());
			pst.setString(4, mbean.getName());
			pst.setInt(5, mbean.getPhysics());
			pst.setInt(6, mbean.getChemistry());
			pst.setInt(7, mbean.getMaths());
			pst.setString(8, mbean.getCreatedBy());
			pst.setString(9, mbean.getModifiedBy());
			pst.setTimestamp(10, mbean.getCreateDatetime());
			pst.setTimestamp(11, mbean.getModifiedDatetime());
			pst.executeUpdate();
			conn.commit(); // End transaction
			pst.close();
		} catch (Exception e) {
			 log.error(e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception in add marksheet");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		 log.debug("Model add Ended");
		return pk;
	}

	/**
	 * Deletes a Marksheet
	 *
	 * @param bean
	 * @throws DatabaseException
	 */
	public static void delete(MarksheetBean mbean) throws ApplicationException {

		 log.debug("Model delete Started");

		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pst = conn.prepareStatement("DELETE FROM ST_MARKSHEET WHERE ID=?");
			pst.setLong(1, mbean.getId());
			System.out.println("Deleted MarkSheet");
			pst.executeUpdate();
			conn.commit(); // End transaction
			pst.close();

		} catch (Exception e) {
			// log.error(e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				// log.error(ex);
				throw new ApplicationException("Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception in delete marksheet");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		 log.debug("Model delete Ended");
	}

	/**
	 * Finds Marksheet by Roll No
	 *
	 * @param rollNo
	 *            : get parameter
	 * @return bean
	 * @throws DuplicateRecordException
	 */
	public  static MarksheetBean findByRollNo(String rollNo) throws ApplicationException {
		 log.debug("Model findByRollNo Started");

		StringBuffer sql = new StringBuffer("SELECT * FROM ST_MARKSHEET WHERE ROLL_NO=?");
		MarksheetBean mbean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pst = conn.prepareStatement(sql.toString());
			pst.setString(1, rollNo);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				mbean = new MarksheetBean();
				mbean.setId(rs.getLong(1));
				mbean.setRollNo(rs.getString(2));
				mbean.setStudentId(rs.getLong(3));
				mbean.setName(rs.getString(4));
				mbean.setPhysics(rs.getInt(5));
				mbean.setChemistry(rs.getInt(6));
				mbean.setMaths(rs.getInt(7));
				mbean.setCreatedBy(rs.getString(8));
				mbean.setModifiedBy(rs.getString(9));
				mbean.setCreateDatetime(rs.getTimestamp(10));
				mbean.setModifiedDatetime(rs.getTimestamp(11));
			}
			rs.close();
		} catch (Exception e) {
			 log.error(e);
			throw new ApplicationException("Exception in getting marksheet by roll no");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		 log.debug("Model findByRollNo Ended");
		return mbean;
	}

	/**
	 * Finds Marksheet by PK
	 *
	 * @param pk
	 *            : get parameter
	 * @return bean
	 * @throws DatabaseException
	 */
	public static MarksheetBean findByPK(long pk) throws ApplicationException {
		 log.debug("Model findByPK Started");

		StringBuffer sql = new StringBuffer("SELECT * FROM ST_MARKSHEET WHERE ID=?");
		MarksheetBean mbean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pst = conn.prepareStatement(sql.toString());
			pst.setLong(1, pk);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				mbean = new MarksheetBean();
				mbean.setId(rs.getLong(1));
				mbean.setRollNo(rs.getString(2));
				mbean.setStudentId(rs.getLong(3));
				mbean.setName(rs.getString(4));
				mbean.setPhysics(rs.getInt(5));
				mbean.setChemistry(rs.getInt(6));
				mbean.setMaths(rs.getInt(7));
				mbean.setCreatedBy(rs.getString(8));
				mbean.setModifiedBy(rs.getString(9));
				mbean.setCreateDatetime(rs.getTimestamp(10));
				mbean.setModifiedDatetime(rs.getTimestamp(11));

			}
			rs.close();
		} catch (Exception e) {
			// log.error(e);
			throw new ApplicationException("Exception in getting marksheet by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		 log.debug("Model findByPK Ended");
		return mbean;
	}

	/**
	 * Updates a Marksheet
	 *
	 * @param bean
	 * @throws DatabaseException
	 */
	public  static void update(MarksheetBean mbean) throws ApplicationException, DuplicateRecordException {

		 log.debug("Model update Started");

		Connection conn = null;
		// MarksheetBean beanExist = findByRollNo(mbean.getRollNo());

		// Check if updated Roll no already exist
		// if (beanExist != null && beanExist.getId() != mbean.getId()) {
		// throw new DuplicateRecordException("Roll No is already exist");
		// }

		// get Student Name
		StudentModel sModel = new StudentModel();
		StudentBean studentbean = sModel.findByPK(mbean.getStudentId());
		mbean.setName(studentbean.getFirstName() + " " + studentbean.getLastName());

		try {
			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE ST_MARKSHEET SET ROLL_NO=?,STUDENT_ID=?,NAME=?,PHYSICS=?,CHEMISTRY=?,MATHS=?,CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=? WHERE ID=?");
			pstmt.setString(1, mbean.getRollNo());
			pstmt.setLong(2, mbean.getStudentId());
			pstmt.setString(3, mbean.getName());
			pstmt.setInt(4, mbean.getPhysics());
			pstmt.setInt(5, mbean.getChemistry());
			pstmt.setInt(6, mbean.getMaths());
			pstmt.setString(7, mbean.getCreatedBy());
			pstmt.setString(8, mbean.getModifiedBy());
			pstmt.setTimestamp(9, mbean.getCreateDatetime());
			pstmt.setTimestamp(10, mbean.getModifiedDatetime());
			pstmt.setLong(11, mbean.getId());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			 log.error(e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Update rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception in updating Marksheet ");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		 log.debug("Model update Ended");

	}

	/**
	 * Searches Marksheet
	 *
	 * @param bean
	 *            : Search Parameters
	 * @throws DatabaseException
	 */
	public static List search(MarksheetBean mbean) throws ApplicationException {
		return search(mbean);
	}

	/**
	 * Searches Marksheet with pagination
	 *
	 * @return list : List of Marksheets
	 * @param bean
	 *            : Search Parameters
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 *
	 * @throws DatabaseException
	 */
	public static List search(MarksheetBean mbean, int pageNo, int pageSize) throws ApplicationException {

		 log.debug("Model search Started");

		StringBuffer sql = new StringBuffer("select * from ST_MARKSHEET where true");

		if (mbean != null) {
			System.out.println("service" + mbean.getName());
			if (mbean.getId() > 0) {
				sql.append(" AND id = " + mbean.getId());
			}
			if(mbean.getStudentId()>0)
			{
				sql.append(" AND student_id = " + mbean.getStudentId());
			}
			if (mbean.getRollNo() != null && mbean.getRollNo().length() > 0) {
				sql.append(" AND roll_no like '" + mbean.getRollNo() + "%'");
			}
			if (mbean.getName() != null && mbean.getName().length() > 0) {
				sql.append(" AND name like '" + mbean.getName() + "%'");
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
				mbean = new MarksheetBean();
				mbean.setId(rs.getLong(1));
				mbean.setRollNo(rs.getString(2));
				mbean.setStudentId(rs.getLong(3));
				mbean.setName(rs.getString(4));
				mbean.setPhysics(rs.getInt(5));
				mbean.setChemistry(rs.getInt(6));
				mbean.setMaths(rs.getInt(7));
				mbean.setCreatedBy(rs.getString(8));
				mbean.setModifiedBy(rs.getString(9));
				mbean.setCreateDatetime(rs.getTimestamp(10));
				mbean.setModifiedDatetime(rs.getTimestamp(11));
				list.add(mbean);
			}
			rs.close();
		} catch (Exception e) {
			// log.error(e);
			throw new ApplicationException("Update rollback exception " + e.getMessage());
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		 log.debug("Model search Ended");
		return list;
	}

	/**
	 * Gets List of Marksheet
	 *
	 * @return list : List of Marksheets
	 * @throws DatabaseException
	 */

	public static List list() throws ApplicationException {
		return list(0, 0);
	}

	/**
	 * get List of Marksheet with pagination
	 *
	 * @return list : List of Marksheets
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * @throws DatabaseException
	 */

	public static List list(int pageNo, int pageSize) throws ApplicationException {

		 log.debug("Model list Started");

		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer("select * from ST_MARKSHEET");
		// if page size is greater than zero then apply pagination
		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + "," + pageSize);
		}

		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				MarksheetBean bean = new MarksheetBean();
				bean.setId(rs.getLong(1));
				bean.setRollNo(rs.getString(2));
				bean.setStudentId(rs.getLong(3));
				bean.setName(rs.getString(4));
				bean.setPhysics(rs.getInt(5));
				bean.setChemistry(rs.getInt(6));
				bean.setMaths(rs.getInt(7));
				bean.setCreatedBy(rs.getString(8));
				bean.setModifiedBy(rs.getString(9));
				bean.setCreateDatetime(rs.getTimestamp(10));
				bean.setModifiedDatetime(rs.getTimestamp(11));
				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			 log.error(e);
			throw new ApplicationException("Exception in getting list of Marksheet");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		 log.debug("Model list Ended");
		return list;

	}

	/**
	 * get Merit List of Marksheet with pagination
	 *
	 * @return list : List of Marksheets
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * @throws DatabaseException
	 */

	public  static List getMeritList(int pageNo, int pageSize) throws ApplicationException {
		 log.debug("Model MeritList Started");
		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer(
				("SELECT ID,ROLL_NO,STUDENT_ID,NAME,PHYSICS,CHEMISTRY,MATHS,CREATED_BY,MODIFIED_BY,CREATED_DATETIME,MODIFIED_DATETIME"
						+ ",(PHYSICS +CHEMISTRY +MATHS) as total from ST_MARKSHEET WHERE NOT (PHYSICS<33 OR MATHS<33 OR CHEMISTRY<33) ORDER BY"
						+ " total DESC"));
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
				MarksheetBean bean = new MarksheetBean();
				bean.setId(rs.getLong(1));
				bean.setRollNo(rs.getString(2));
				
				bean.setStudentId(rs.getLong(3));
				
				bean.setName(rs.getString(4));
				
				bean.setPhysics(rs.getInt(5));
				
				bean.setChemistry(rs.getInt(6));
				
				bean.setMaths(rs.getInt(7));
				
				bean.setCreatedBy(rs.getString(8));
				bean.setModifiedBy(rs.getString(9));
				bean.setCreateDatetime(rs.getTimestamp(10));
				bean.setModifiedDatetime(rs.getTimestamp(11));
				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			 log.error(e);
			//throw new ApplicationException("Exception in getting merit list of Marksheet");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		 log.debug("Model MeritList Ended");
		return list;
	}

}
