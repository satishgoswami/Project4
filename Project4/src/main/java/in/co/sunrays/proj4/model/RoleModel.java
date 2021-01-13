package in.co.sunrays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.sunrays.proj4.bean.RoleBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.exception.DatabaseException;
import in.co.sunrays.proj4.exception.DuplicateRecordException;
import in.co.sunrays.proj4.util.JDBCDataSource;
/**
 * JDBC Implementation of Role Model
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */
public class RoleModel {
	 private static Logger log = Logger.getLogger(RoleModel.class);

	/**
	 * Find next PK of Role
	 *
	 * @throws DatabaseException
	 */
	RoleBean rbean = null;
	int pk = 0;

	public Integer nextPk() throws DatabaseException {
		 log.debug("Model nextPK Started");
		Connection conn = null;
		try {

			String query = "SELECT MAX(ID)FROM ST_Role";
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
		 log.debug("Model nextPK Ended");
		return pk + 1;
	}

	/**
	 * Add a Role
	 *
	 * @param bean
	 * @throws DatabaseException
	 *
	 */

	public long add(RoleBean rbean) throws ApplicationException, DuplicateRecordException {
		 log.debug("Model add Started");
		Connection conn = null;
		int pk = 0;

		RoleBean duplicataRole = findByName(rbean.getName());
		// Check if create Role already exist
		if (duplicataRole != null) {
			throw new DuplicateRecordException("Role already exists");
		}

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPk();
			// Get auto-generated next primary key
			System.out.println(pk + " in ModelJDBC");
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pst = conn.prepareStatement("INSERT INTO ST_ROLE VALUES(?,?,?,?,?,?,?)");
			pst.setInt(1, pk);
			pst.setString(2, rbean.getName());
			pst.setString(3, rbean.getDescription());
			pst.setString(4, rbean.getCreatedBy());
			pst.setString(5, rbean.getModifiedBy());
			pst.setTimestamp(6, rbean.getCreateDatetime());
			pst.setTimestamp(7, rbean.getModifiedDatetime());
			pst.executeUpdate();
			conn.commit(); // End transaction
			pst.close();
		} catch (Exception e) {
			e.printStackTrace();
			 log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in add Role");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		 log.debug("Model add Ended");
		return pk;
	}

	/**
	 * Find Role by PK
	 *
	 * @param pk
	 *            : get parameter
	 * @return bean
	 * @throws DatabaseException
	 */

	public RoleBean findByPK(long pk) throws ApplicationException {
		 log.debug("Model findByPK Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_ROLE WHERE ID=?");
		RoleBean rbean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pst = conn.prepareStatement(sql.toString());
			pst.setLong(1, pk);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				rbean = new RoleBean();
				rbean.setId(rs.getLong(1));
				rbean.setName(rs.getString(2));
				rbean.setDescription(rs.getString(3));
				rbean.setCreatedBy(rs.getString(4));
				rbean.setModifiedBy(rs.getString(5));
				rbean.setCreateDatetime(rs.getTimestamp(6));
				rbean.setModifiedDatetime(rs.getTimestamp(7));
			}
			rs.close();
		} catch (Exception e) {
			 log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting User by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		 log.debug("Model findByPK Ended");
		return rbean;
	}

	/**
	 * Find User by Role
	 *
	 * @param name
	 *            : get parameter
	 * @return bean
	 * @throws DatabaseException
	 */
	public RoleBean findByName(String name) throws ApplicationException {
		 log.debug("Model findBy Name Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_ROLE WHERE NAME=?");
		RoleBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				rbean = new RoleBean();
				rbean.setId(rs.getLong(1));
				rbean.setName(rs.getString(2));
				rbean.setDescription(rs.getString(3));
				rbean.setCreatedBy(rs.getString(4));
				rbean.setModifiedBy(rs.getString(5));
				rbean.setCreateDatetime(rs.getTimestamp(6));
				rbean.setModifiedDatetime(rs.getTimestamp(7));
			}
			rs.close();
		} catch (Exception e) {
			 log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting User by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		 log.debug("Model findBy Name Ended");
		return rbean;
	}

	/**
	 * Delete a Role
	 *
	 * @param bean
	 * @throws DatabaseException
	 */
	public void delete(RoleBean bean) throws ApplicationException {
		 log.debug("Model delete Started");
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM ST_ROLE WHERE ID=?");
			pstmt.setLong(1, bean.getId());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();

		} catch (Exception e) {
			 log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in delete Role");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		 log.debug("Model delete Ended");
	}

	/**
	 * Update a Role
	 *
	 * @param bean
	 * @throws DatabaseException
	 */
	public void update(RoleBean bean) throws ApplicationException, DuplicateRecordException {
		 log.debug("Model update Started");
		Connection conn = null;

		// RoleBean duplicataRole = findByName(bean.getName());
		// Check if updated Role already exist
		// if (duplicataRole != null && duplicataRole.getId() != bean.getId()) {
		// throw new DuplicateRecordException("Role already exists");
		// }
		try {
			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pst = conn.prepareStatement(
					"UPDATE ST_ROLE SET NAME=?,DESCRIPTION=?,CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=? WHERE ID=?");
			pst.setString(1, bean.getName());
			pst.setString(2, bean.getDescription());
			pst.setString(3, bean.getCreatedBy());
			pst.setString(4, bean.getModifiedBy());
			pst.setTimestamp(5, bean.getCreateDatetime());
			pst.setTimestamp(6, bean.getModifiedDatetime());
			pst.setLong(7, bean.getId());
			pst.executeUpdate();
			conn.commit(); // End transaction
			pst.close();
		} catch (Exception e) {
			 log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception in updating Role ");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		 log.debug("Model update Ended");
	}

	/**
	 * Search Role
	 *
	 * @param bean
	 *            : Search Parameters
	 * @throws DatabaseException
	 */
	public List search(RoleBean bean) throws ApplicationException {
		return search(bean);
	}

	/**
	 * Search Role with pagination
	 *
	 * @return list : List of Roles
	 * @param bean
	 *            : Search Parameters
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 *
	 * @throws DatabaseException
	 */
	public List search(RoleBean rbean, int pageNo, int pageSize) throws ApplicationException {
		 log.debug("Model search Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_ROLE WHERE 1=1");

		if (rbean != null) {
			if (rbean.getId() > 0) {
				sql.append(" AND id = " + rbean.getId());
			}
			if (rbean.getName() != null && rbean.getName().length() > 0) {
				sql.append(" AND NAME like '" + rbean.getName() + "%'");
			}
			if (rbean.getDescription() != null && rbean.getDescription().length() > 0) {
				sql.append(" AND DESCRIPTION like '" + rbean.getDescription() + "%'");
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
				rbean = new RoleBean();
				rbean = new RoleBean();
				rbean.setId(rs.getLong(1));
				rbean.setName(rs.getString(2));
				rbean.setDescription(rs.getString(3));
				rbean.setCreatedBy(rs.getString(4));
				rbean.setModifiedBy(rs.getString(5));
				rbean.setCreateDatetime(rs.getTimestamp(6));
				rbean.setModifiedDatetime(rs.getTimestamp(7));
				list.add(rbean);
			}
			rs.close();
		} catch (Exception e) {
			 log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in search Role");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		 log.debug("Model search Ended");
		return list;
	}
	 /**
     * Get List of Role
     *
     * @return list : List of Role
     * @throws DatabaseException
     */

    public List list() throws ApplicationException {
        return list(0,0);
    }
    /**
     * Get List of Role with pagination
     *
     * @return list : List of Role
     * @param pageNo
     *            : Current Page No.
     * @param pageSize
     *            : Size of Page
     * @throws DatabaseException
     */
    public List list(int pageNo, int pageSize) throws ApplicationException {
        log.debug("Model list Started");
        ArrayList list = new ArrayList();
        StringBuffer sql = new StringBuffer("select * from ST_ROLE");
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
                RoleBean bean = new RoleBean();
                bean.setId(rs.getLong(1));
                bean.setName(rs.getString(2));
                bean.setDescription(rs.getString(3));
                bean.setCreatedBy(rs.getString(4));
                bean.setModifiedBy(rs.getString(5));
                bean.setCreateDatetime(rs.getTimestamp(6));
                bean.setModifiedDatetime(rs.getTimestamp(7));
                list.add(bean);
            }
            rs.close();
        } catch (Exception e) {
            log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in getting list of Role");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }

        log.debug("Model list Ended");
        return list;

    }
}

