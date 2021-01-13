package in.co.sunrays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.sunrays.proj4.bean.CourseBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.exception.DatabaseException;
import in.co.sunrays.proj4.exception.DuplicateRecordException;
import in.co.sunrays.proj4.util.JDBCDataSource;

/**
 * JDBC Implementation of Course Model
 * 
 * @author Facade
 * @version 1.0 Facade
 */
public class CourseModel {
	private static Logger log = Logger.getLogger(CourseModel.class);

	/**
	 * Find next PK of Course
	 * 
	 * @return long
	 * @throws DatabaseException
	 */
	public static Integer nextPk() throws DatabaseException {
		 log.debug("Model nextPK Started");
		Connection conn = null;
		int pk = 0;
		try {

			String query = "SELECT MAX(ID)FROM COURSE";
			conn = JDBCDataSource.getConnection();
			PreparedStatement pst = conn.prepareStatement(query);

			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();

		} catch (Exception e) {

			throw new DatabaseException("Exception : Exception in getting PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
log.debug("Model nextPk Ended");
		return pk + 1;
	}

	/**
	 * Add a User
	 *
	 * @param bean
	 * @throws DatabaseException
	 *
	 */
	public static void add(CourseBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model  add Started");
		CourseBean duplicataRole = findByName(bean.getCourseName());
		// Check if create Role already exist
		if (duplicataRole != null) {
			throw new DuplicateRecordException("Course already exists");
		}
		Connection con = null;
		ResultSet rs = null;
		PreparedStatement pst = null;
		try {
			con = JDBCDataSource.getConnection();
			con.setAutoCommit(false);
			pst = con.prepareStatement("INSERT INTO COURSE VALUES(?,?,?,?,?,?,?,?)");

			pst.setLong(1, nextPk());
			pst.setString(2, bean.getCourseName());
			pst.setString(3, bean.getDescription());
			pst.setString(4, bean.getDuration());
			pst.setString(5, bean.getCreatedBy());
			pst.setString(6, bean.getModifiedBy());
			pst.setTimestamp(7, bean.getCreateDatetime());
			pst.setTimestamp(8, bean.getModifiedDatetime());
			pst.executeUpdate();
			pst.close();
			con.commit();
		} catch (Exception e) {
			throw new ApplicationException("exception in course model add" + e.getMessage());
		}
		JDBCDataSource.closeConnection(con);
		log.debug("CourseModel add ended");
	}
	/**
	 * Update a Course
	 * 
	 * @param bean
	 *            :bean
	 * @throws ApplicationException
	 * 
	 */
	public static void update(CourseBean bean) throws ApplicationException {
		log.debug("Model update Started");
		Connection con = null;
		PreparedStatement pst = null;

		try {
			con = JDBCDataSource.getConnection();
			pst = con.prepareStatement("UPDATE COURSE SET COURSENAME=?,DESCRIPTION=?,DURATION=?"
					+ ",CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?," + "MODIFIED_DATETIME=? WHERE ID=?");
			con.setAutoCommit(false);

			pst.setString(1, bean.getCourseName());
			pst.setString(2, bean.getDescription());
			pst.setString(3, bean.getDuration());
			pst.setString(4, bean.getCreatedBy());
			pst.setString(5, bean.getModifiedBy());
			pst.setTimestamp(6, bean.getCreateDatetime());
			pst.setTimestamp(7, bean.getModifiedDatetime());
			pst.setLong(8, bean.getId());
			pst.executeUpdate();
			pst.close();
			con.commit();
		} catch (Exception e) {
			throw new ApplicationException("exception in course model update...." + e.getMessage());
		}
		JDBCDataSource.closeConnection(con);
log.debug("Model update Ended");
	}

	/**
	 * Delete a Course
	 * 
	 * @param bean
	 *            :bean
	 * @throws ApplicationException
	 */
	public static void delete(CourseBean bean) throws ApplicationException {
		 log.debug("Model delete Started");
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			System.out.println("conn start");
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pst = conn.prepareStatement("DELETE FROM COURSE WHERE ID=?");
			pst.setLong(1, bean.getId());
			int i = pst.executeUpdate();

			conn.commit(); // End transaction
			pst.close();

		} catch (Exception e) {
			throw new ApplicationException("exception in course model delete ... " + e.getMessage());
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model delete Ended");
	}

	/**
	 * Find Course by PK
	 * 
	 * @param pk
	 *            : get parameter
	 * @return bean :bean
	 * @throws ApplicationException
	 */
	public static CourseBean findByPK(long pk) throws ApplicationException {
		 log.debug("Model findByPk Start");
		StringBuffer sql = new StringBuffer("SELECT * FROM COURSE WHERE ID=?");
		CourseBean bean = null;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pst = conn.prepareStatement(sql.toString());
			pst.setLong(1, pk);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				bean = new CourseBean();
				bean.setId(rs.getLong(1));
				bean.setCourseName(rs.getString(2));
				bean.setDescription(rs.getString(3));
				bean.setDuration(rs.getString(4));
				bean.setCreatedBy(rs.getString(5));
				bean.setModifiedBy(rs.getString(6));
				bean.setCreateDatetime(rs.getTimestamp(7));
				bean.setModifiedDatetime(rs.getTimestamp(8));
			}
			pst.close();
			rs.close();
		} catch (Exception e) {
			throw new ApplicationException("exception in course model findByPK..." + e.getMessage());
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByPk Ended");
		return bean;
	}

	/**
	 * Find Course by Course Name
	 * 
	 * @param Name
	 *            : get parameter
	 * @return bean
	 * @throws ApplicationException
	 */
	public static CourseBean findByName(String Name) throws ApplicationException {
		 log.debug("Model findByName Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM COURSE WHERE COURSENAME=?");
		CourseBean bean = null;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pst = conn.prepareStatement(sql.toString());
			pst.setString(1, Name);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				bean = new CourseBean();
				bean.setId(rs.getLong(1));
				bean.setCourseName(rs.getString(2));
				bean.setDescription(rs.getString(3));
				bean.setDuration(rs.getString(4));
				bean.setCreatedBy(rs.getString(5));
				bean.setModifiedBy(rs.getString(6));
				bean.setCreateDatetime(rs.getTimestamp(7));
				bean.setModifiedDatetime(rs.getTimestamp(8));
			}
			pst.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		 log.debug("Model findByName Ended");
		return bean;
	}

	/**
	 * Search Course with pagination
	 * 
	 * @return list : List of Course
	 * @param bean
	 *            : Search Parameters
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * 
	 * @throws ApplicationException
	 */
	public static List search(CourseBean bean, int pageNo, int pageSize) throws ApplicationException {
log.debug("Model search Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM COURSE WHERE 1=1");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" AND id = " + bean.getId());
			}
			if (bean.getCourseName() != null && bean.getCourseName().length() > 0) {
				sql.append(" AND COURSENAME like '" + bean.getCourseName() + "%'");
			}
			if (bean.getDuration() != null && bean.getDuration().length() > 0) {
				sql.append(" AND DURATION like '" + bean.getDuration() + "%'");
			}

		}

		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;

			sql.append(" Limit " + pageNo + ", " + pageSize);
			// sql.append(" limit " + pageNo + "," + pageSize);
		}

		List<CourseBean> list = new ArrayList<CourseBean>();
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new CourseBean();
				bean.setId(rs.getLong(1));
				bean.setCourseName(rs.getString(2));
				bean.setDescription(rs.getString(3));
				bean.setDuration(rs.getString(4));
				bean.setCreatedBy(rs.getString(5));
				bean.setModifiedBy(rs.getString(6));
				bean.setCreateDatetime(rs.getTimestamp(7));
				bean.setModifiedDatetime(rs.getTimestamp(8));
				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			throw new ApplicationException("exception in course model search..." + e.getMessage());
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model search Ended");
		return list;
	}

	/**
	 * Search Course
	 * 
	 * @param bean
	 *            : Search Parameters
	 * @throws ApplicationException
	 */
	public static List search(CourseBean bean) throws ApplicationException {
		return search(bean, 0, 0);
	}

	/**
	 * Get List of Course with pagination
	 * 
	 * @return list : List of Course
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * @throws ApplicationException
	 */

	public static List list(int pageNo, int pageSize) throws ApplicationException {
		log.debug("Model list Started");
		List<CourseBean> list = new ArrayList<CourseBean>();
		Connection con = null;
		StringBuffer sql = new StringBuffer("SELECT * FROM COURSE WHERE true");

		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" Limit " + pageNo + "," + pageSize);
		}
		System.out.println("FINAL SQL OF COURSE list :" + sql);
		PreparedStatement pst = null;
		CourseBean bean;
		try {
			con = JDBCDataSource.getConnection();
			pst = con.prepareStatement(sql.toString());
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {

				bean = new CourseBean();
				bean.setId(rs.getLong(1));
				bean.setCourseName(rs.getString(2));
				bean.setDescription(rs.getString(3));
				bean.setDuration(rs.getString(4));
				bean.setCreatedBy(rs.getString(5));
				bean.setModifiedBy(rs.getString(6));
				bean.setCreateDatetime(rs.getTimestamp(7));
				bean.setModifiedDatetime(rs.getTimestamp(8));
				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			throw new ApplicationException("exception in course model list..." + e.getMessage());
		} finally {
			JDBCDataSource.closeConnection(con);
		}
		log.debug("Model list Ended");
		return list;
	}

	/**
	 * Get List of Course
	 * 
	 * @return list : List of Course
	 * @throws ApplicationException
	 */
	public static List list() throws ApplicationException {
		return list(0, 0);
	}

}
