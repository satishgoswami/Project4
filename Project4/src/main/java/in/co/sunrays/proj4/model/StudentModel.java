package in.co.sunrays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.sunrays.proj4.bean.CollegeBean;
import in.co.sunrays.proj4.bean.StudentBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.exception.DatabaseException;
import in.co.sunrays.proj4.exception.DuplicateRecordException;
import in.co.sunrays.proj4.util.JDBCDataSource;

/**
 * JDBC Implementation of Student Model
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */
public class StudentModel {
	private static Logger log = Logger.getLogger(StudentModel.class);

	/**
	 * Find next PK of Student
	 * 
	 * @throws DatabaseException
	 */
	public static long nextPK() throws DatabaseException {
		
		log.debug("StudentModel nextPK Started");
		long pk = 0;
		Connection conn = null;
		try {
			String query = "SELECT MAX(ID) FROM ST_STUDENT";
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getLong(1);
			}
			rs.close();

		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new DatabaseException("Exception : Exception in getting PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("StudentModel nextPK End");
		return pk + 1;
	}

	/**
	 * Add a Student
	 * 
	 * @param bean
	 * @throws DatabaseException
	 * 
	 */
	public static long add(StudentBean sbean) throws ApplicationException, DuplicateRecordException {
		Connection con=null;
		log.debug("StudentModel add Started");
		// get College Name
		CollegeModel cmodel = new CollegeModel();
		CollegeBean collegeBean = cmodel.findByPK(sbean.getCollegeId());
		sbean.setCollegeName(collegeBean.getName());
		Connection conn = null;
		StudentBean duplicateName = findByEmailId(sbean.getEmail());
		long pk = 0;
		PreparedStatement pstmt=null;
		
		if (duplicateName != null) {
			throw new DuplicateRecordException("Email already exists");
		}

		try {
			String query = "INSERT INTO ST_STUDENT VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?)";
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			conn.setAutoCommit(false); // Begin transaction
			pstmt = conn.prepareStatement(query);
			pstmt.setLong(1, pk);
			pstmt.setLong(2, sbean.getCollegeId());
			pstmt.setString(3, sbean.getCollegeName());
			pstmt.setString(4, sbean.getFirstName());
			pstmt.setString(5, sbean.getLastName());
			pstmt.setDate(6, new java.sql.Date(sbean.getDob().getTime()));
			pstmt.setString(7, sbean.getAddress());
			pstmt.setString(8, sbean.getMobileNo());
			pstmt.setString(9, sbean.getEmail());
			pstmt.setString(10, sbean.getCreatedBy());
			pstmt.setString(11, sbean.getModifiedBy());
			pstmt.setTimestamp(12, sbean.getCreateDatetime());
			pstmt.setTimestamp(13, sbean.getModifiedDatetime());
			int c = pstmt.executeUpdate();
			System.out.println("Rows Inserted" + c);
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in add Student");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("StudentModel add Ended.");
		return pk;
	}

	/**
	 * Delete a Student
	 * 
	 * @param bean
	 * @throws ApplicationException
	 */
	
	public static void delete(StudentBean sbean) throws ApplicationException {
		
		log.debug("StudentModel delete Started");
		Connection conn = null;
		PreparedStatement pstmt=null;
		
		try {
			String query = "DELETE FROM ST_STUDENT WHERE ID=?";
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			pstmt = conn.prepareStatement(query);
			pstmt.setLong(1, sbean.getId());
			int a = pstmt.executeUpdate();
			System.out.println("Rows deleted  " + a);
			conn.commit(); // End transaction
			pstmt.close();

		} catch (Exception e) {
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in delete Student");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("StudentModel delete Started");
	}

	/**
	 * Find User by Student
	 * 
	 * @param Email
	 *            : get parameter
	 * @return bean
	 * @throws DatabaseException
	 */

	public static StudentBean findByEmailId(String email) throws ApplicationException {
		
		log.debug("StudentModel findByEmail Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_STUDENT WHERE EMAIL=?");
		Connection conn = null;
		StudentBean sbean = null;
		PreparedStatement pstmt=null;
		try {
			conn = JDBCDataSource.getConnection();
			pstmt= conn.prepareStatement(sql.toString());
			pstmt.setString(1, email);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				sbean = new StudentBean();
				sbean.setId(rs.getLong(1));
				sbean.setCollegeId(rs.getLong(2));
				sbean.setCollegeName(rs.getString(3));
				sbean.setFirstName(rs.getString(4));
				sbean.setLastName(rs.getString(5));
				sbean.setDob(rs.getDate(6));
				sbean.setAddress(rs.getString(7));
				sbean.setMobileNo(rs.getString(8));
				sbean.setEmail(rs.getString(9));
				sbean.setCreatedBy(rs.getString(10));
				sbean.setModifiedBy(rs.getString(11));
				sbean.setCreateDatetime(rs.getTimestamp(12));
				sbean.setModifiedDatetime(rs.getTimestamp(13));
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting User by Email");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("StudentModel findByEmail End");
		return sbean;
	}

	/**
	 * Find Student by PK
	 * 
	 * @param pk
	 *            : get parameter
	 * @return bean
	 * @throws DatabaseException
	 */

	public static StudentBean findByPK(long pk) throws ApplicationException {
	
	//	log.debug("StudentModel findByPK Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_STUDENT WHERE ID=?");
		Connection conn = null;
		StudentBean sbean = null;
		PreparedStatement pstmt=null;
		try {
			conn = JDBCDataSource.getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			System.out.println("....................");
			while (rs.next()) {
				sbean = new StudentBean();
				sbean.setId(rs.getLong(1));
				sbean.setCollegeId(rs.getLong(2));
				sbean.setCollegeName(rs.getString(3));
				sbean.setFirstName(rs.getString(4));
				sbean.setLastName(rs.getString(5));
				sbean.setDob(rs.getDate(6));
				sbean.setAddress(rs.getString(7));
				sbean.setMobileNo(rs.getString(8));
				sbean.setEmail(rs.getString(9));
				sbean.setCreatedBy(rs.getString(10));
				sbean.setModifiedBy(rs.getString(11));
				sbean.setCreateDatetime(rs.getTimestamp(12));
				sbean.setModifiedDatetime(rs.getTimestamp(13));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("StudentModel findByPK End");
		return sbean;
	}

	/**
	 * Update a Student
	 * 
	 * @param bean
	 * @throws DatabaseException
	 */

	public static void update(StudentBean bean) throws ApplicationException, DuplicateRecordException {
		
		log.debug("StudentModel update Started");
		Connection conn = null;
		PreparedStatement pstmt=null;
		StudentBean beanExist = findByEmailId(bean.getEmail());

		
		
		// Check if updated Roll no already exist
		if (beanExist != null && beanExist.getId() != bean.getId()) {
			throw new DuplicateRecordException("Email Id is already exist");
		}

		// get College Name
		
		CollegeModel cModel = new CollegeModel();
		CollegeBean collegeBean = cModel.findByPK(bean.getCollegeId());
		bean.setCollegeName(collegeBean.getName());

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
		pstmt = conn.prepareStatement(
					"UPDATE ST_STUDENT SET COLLEGE_ID=?,COLLEGE_NAME=?,FIRST_NAME=?,LAST_NAME=?,DATE_OF_BIRTH=?,MOBILE_NO=?,ADDRESS=?,EMAIL=?,CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=? WHERE ID=?");

			pstmt.setLong(1, bean.getCollegeId());
			pstmt.setString(2, bean.getCollegeName());
			pstmt.setString(3, bean.getFirstName());
			pstmt.setString(4, bean.getLastName());
			pstmt.setDate(5, new java.sql.Date(bean.getDob().getTime()));
			pstmt.setString(6, bean.getMobileNo());
			pstmt.setString(7,bean.getAddress());
			pstmt.setString(8, bean.getEmail());
			pstmt.setString(9, bean.getCreatedBy());
			pstmt.setString(10, bean.getModifiedBy());
			pstmt.setTimestamp(11, bean.getCreateDatetime());
			pstmt.setTimestamp(12, bean.getModifiedDatetime());
			pstmt.setLong(13, bean.getId());
			pstmt.executeUpdate();
			System.out.println("record updated");
			conn.commit();
			
			pstmt.close();

		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception" + e);
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model update End");
	}
	/**
	 * Search Student with pagination
	 * 
	 * @return list : List of Students
	 * @
	 * 
	 * 
	 * 
	 * param bean
	 *            : Search Parameters
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * 
	 * @throws DatabaseException
	 */

	public static List search(StudentBean sbean, int pageNo, int pageSize) throws ApplicationException {
		log.debug("StudentModel search Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_STUDENT WHERE 1=1");
		Connection conn = null;
		ResultSet rs=null;
		if (sbean != null) {
			if (sbean.getId() > 0) {
				sql.append(" AND id = " + sbean.getId());
			}
			if (sbean.getFirstName() != null && sbean.getFirstName().trim().length() > 0) {
				sql.append(" AND FIRST_NAME like '" + sbean.getFirstName() + "%'");
			}
			if (sbean.getLastName() != null && sbean.getLastName().trim().length() > 0) {
				sql.append(" AND LAST_NAME like '" + sbean.getLastName() + "%'");
			}
			if (sbean.getDob() != null && sbean.getDob().getDate() > 0) {
				sql.append(" AND DOB = " + sbean.getDob());
			}
			if (sbean.getMobileNo() != null && sbean.getMobileNo().trim().length() > 0) {
				sql.append(" AND MOBILE_NO like '" + sbean.getMobileNo() + "%'");
			}
			if (sbean.getEmail() != null && sbean.getEmail().trim().length() > 0) {
				sql.append(" AND EMAIL like '" + sbean.getEmail() + "%'");
			}
			if (sbean.getCollegeName() != null && sbean.getCollegeName().trim().length() > 0) {
				sql.append(" AND COLLEGE_NAME = " + sbean.getCollegeName());
			}

		}
		System.out.println(sql);
		// if page size is greater than zero then apply pagination
		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;

			sql.append(" Limit " + pageNo + ", " + pageSize);
			// sql.append(" limit " + pageNo + "," + pageSize);
		}

		List<StudentBean> list = new ArrayList<StudentBean>();

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				sbean = new StudentBean();
				sbean.setId(rs.getLong(1));
				sbean.setCollegeId(rs.getLong(2));
				sbean.setCollegeName(rs.getString(3));
				sbean.setFirstName(rs.getString(4));
				sbean.setLastName(rs.getString(5));
				sbean.setDob(rs.getDate(6));
				sbean.setAddress(rs.getString(7));
				sbean.setMobileNo(rs.getString(8));
				sbean.setEmail(rs.getString(9));
				sbean.setCreatedBy(rs.getString(10));
				sbean.setModifiedBy(rs.getString(11));
				sbean.setCreateDatetime(rs.getTimestamp(12));
				sbean.setModifiedDatetime(rs.getTimestamp(13));
				list.add(sbean);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			e.printStackTrace();
			throw new ApplicationException("Exception : Exception in search Student");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("StudentModel search Ended.");
		return list;
	}

	/**
	 * Get List of Student with pagination
	 * 
	 * @return list : List of Student
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * @throws DatabaseException
	 */

	public static List list(int pageNo, int pageSize) throws ApplicationException {

		log.debug("Model list Started");
		List<StudentBean> list = new ArrayList<StudentBean>();
		Connection conn = null;
		StringBuffer sql = new StringBuffer("select * from ST_STUDENT");
		StudentBean sbean=null;
		// if page size is greater than zero then apply pagination
		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + "," + pageSize);
		}

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				sbean = new StudentBean();
				sbean.setId(rs.getLong(1));
				sbean.setCollegeId(rs.getLong(2));
				sbean.setCollegeName(rs.getString(3));
				sbean.setFirstName(rs.getString(4));
				sbean.setLastName(rs.getString(5));
				sbean.setDob(rs.getDate(6));
				sbean.setAddress(rs.getString(7));
				sbean.setMobileNo(rs.getString(8));
				sbean.setEmail(rs.getString(9));
				sbean.setCreatedBy(rs.getString(10));
				sbean.setModifiedBy(rs.getString(11));
				sbean.setCreateDatetime(rs.getTimestamp(12));
				sbean.setModifiedDatetime(rs.getTimestamp(13));
				list.add(sbean);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting list of Student");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("StudentModel list Ended");
		return list;

	}
    /**
	 * Get List of Student
	 * 
	 * @return list : List of Student
	 * @throws ApplicationException
	 */
	public List list() throws ApplicationException{
		return list(0,0);
	}
}


