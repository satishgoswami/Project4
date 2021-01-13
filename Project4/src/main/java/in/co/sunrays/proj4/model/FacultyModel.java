package in.co.sunrays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.sunrays.proj4.bean.CollegeBean;
import in.co.sunrays.proj4.bean.CourseBean;
import in.co.sunrays.proj4.bean.FacultyBean;
import in.co.sunrays.proj4.bean.SubjectBean;
import in.co.sunrays.proj4.exception.DuplicateRecordException;
import in.co.sunrays.proj4.util.JDBCDataSource;

/**
 * JDBC Implementation of Faculty Model
 * 
 * @author Facade
 * @version 1.0 Facade
 */
public class FacultyModel {

	/** The log. */
	 private static Logger log = Logger.getLogger(FacultyModel.class);

	/**
	 * Next pk.
	 *
	 * @return the integer
	 * @throws SQLException
	 *             the SQL exception
	 */
	public static Integer nextPk() throws SQLException {
log.debug("Model nextPk Started");
		int pk = 0;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_faculty");
			ResultSet rs = pstmt.executeQuery();
			conn.commit();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
		log.debug("Model nextPk Ended");
		return pk = pk + 1;
	}

	/**
	 * Adds the.
	 *
	 * @param bean
	 *            the bean
	 * @return the long
	 * @throws Exception
	 *             the exception
	 */
	public static long add(FacultyBean bean) throws Exception {
log.debug("Model add Started");
		int pk = 0;

		Connection conn = null;

		CollegeModel collegemodel = new CollegeModel();
		CollegeBean collegebean = collegemodel.findByPK(bean.getCollegeId());
		System.out.println(" name of colege" + collegebean.getName());
		bean.setCollegeName(collegebean.getName());

		CourseModel coursemodel = new CourseModel();
		CourseBean coursebean = coursemodel.findByPK(bean.getCourseId());
		System.out.println(" name of course" + coursebean.getCourseName());
		bean.setCourseName(coursebean.getCourseName());

		Subjectmodel subjectmodel = new Subjectmodel();
		SubjectBean subjectbean = subjectmodel.findByPk(bean.getSubjectId());
		System.out.println("name of subject" + subjectbean.getSubjectName());
		bean.setSubjectName(subjectbean.getSubjectName());

		FacultyBean beanExist = findByEmail(bean.getLoginId());

		if (beanExist != null) {
			throw new DuplicateRecordException("Faculty name already exist");
		}

		try {
			pk = nextPk();
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn
					.prepareStatement("insert into st_faculty values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setLong(1, pk);
			pstmt.setString(2, bean.getFirstName());
			pstmt.setString(3, bean.getLastName());
			pstmt.setString(4, bean.getGender());
			pstmt.setString(5, bean.getLoginId());
			pstmt.setString(6, bean.getAddress());
			pstmt.setDate(7, new java.sql.Date(bean.getDateOfJoining().getTime()));
			pstmt.setString(8, bean.getQualification());
			pstmt.setString(9, bean.getMobileNo());
			pstmt.setInt(10, bean.getCollegeId());
			pstmt.setString(11, bean.getCollegeName());
			pstmt.setInt(12, bean.getCourseId());
			pstmt.setString(13, bean.getCourseName());
			pstmt.setInt(14, bean.getSubjectId());
			pstmt.setString(15, bean.getSubjectName());
			pstmt.setString(16, bean.getCreatedBy());
			pstmt.setString(17, bean.getModifiedBy());
			pstmt.setTimestamp(18, bean.getCreateDatetime());
			pstmt.setTimestamp(19, bean.getModifiedDatetime());

			pstmt.executeUpdate();
			System.out.println("record inserted");
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
		log.debug("Model add Ended");
		return pk;
	}

	/**
	 * Update.
	 *
	 * @param bean
	 *            the bean
	 * @throws Exception
	 *             the exception
	 */
	public void update(FacultyBean bean) throws Exception {

		 log.debug("Model update Started");
		System.out.println("update model called");
		Connection conn = null;

		CollegeModel collegemodel = new CollegeModel();
		CollegeBean collegebean = collegemodel.findByPK(bean.getCollegeId());
		System.out.println(" name of colege" + collegebean.getName());
		bean.setCollegeName(collegebean.getName());

		CourseModel coursemodel = new CourseModel();
		CourseBean coursebean = coursemodel.findByPK(bean.getCourseId());
		System.out.println(" name of course" + coursebean.getCourseName());
		bean.setCourseName(coursebean.getCourseName());

		Subjectmodel subjectmodel = new Subjectmodel();
		SubjectBean subjectbean = subjectmodel.findByPk(bean.getSubjectId());
		System.out.println("name of subject" + subjectbean.getSubjectName());
		bean.setSubjectName(subjectbean.getSubjectName());

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(
					"update st_faculty set first_name=?,last_name=?,gender=?,login_id=?,address=?,date_of_joining=?,qualification=?,mobile_no=?,college_id=?,college_name=?,course_id=?,course_name=?,subject_id=?,subject_name=?,created_by=?,modified_by=?,created_datetime=?,modified_datetime=? where id=?");

			pstmt.setString(1, bean.getFirstName());
			pstmt.setString(2, bean.getLastName());
			pstmt.setString(3, bean.getGender());
			pstmt.setString(4, bean.getLoginId());
			pstmt.setString(5, bean.getAddress());
			pstmt.setDate(6, new java.sql.Date(bean.getDateOfJoining().getTime()));
			pstmt.setString(7, bean.getQualification());
			pstmt.setString(8, bean.getMobileNo());
			pstmt.setInt(9, bean.getCollegeId());
			pstmt.setString(10, bean.getCollegeName());
			pstmt.setInt(11, bean.getCourseId());
			pstmt.setString(12, bean.getCourseName());
			pstmt.setInt(13, bean.getSubjectId());
			pstmt.setString(14, bean.getSubjectName());
			pstmt.setString(15, bean.getCreatedBy());
			pstmt.setString(16, bean.getModifiedBy());
			pstmt.setTimestamp(17, bean.getCreateDatetime());
			pstmt.setTimestamp(18, bean.getModifiedDatetime());
			pstmt.setLong(19, bean.getId());

			int i = pstmt.executeUpdate();
			System.out.println("record updated" + i);
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
		 log.debug("Model update Ended");
	}

	/**
	 * Delete.
	 *
	 * @param bean
	 *            the bean
	 * @throws Exception
	 *             the exception
	 */
	public void delete(FacultyBean bean) throws Exception {
		 log.debug("Model  delete Started ");
		System.out.println("delete model called");
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("delete from st_faculty where id=?");

			pstmt.setLong(1, bean.getId());

			pstmt.executeUpdate();
			System.out.println("record deleted");
			conn.commit();
			pstmt.close();

		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
		log.debug("Model  delete Ended ");
	}

	/**
	 * Find by pk.
	 *
	 * @param pk
	 *            the pk
	 * @return the faculty bean
	 * @throws Exception
	 *             the exception
	 */
	public FacultyBean findByPk(long pk) throws Exception {

		 log.debug(" Model findByPk Started");
		System.out.println("findByPk model called");
		Connection conn = null;
		FacultyBean bean = null;
		StringBuffer sql = new StringBuffer("select * from st_faculty where id=?");
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());

			pstmt.setLong(1, pk);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new FacultyBean();

				bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setGender(rs.getString(4));
				bean.setLoginId(rs.getString(5));
				bean.setAddress(rs.getString(6));
				bean.setDateOfJoining(rs.getDate(7));
				bean.setQualification(rs.getString(8));
				bean.setMobileNo(rs.getString(9));
				bean.setCollegeId(rs.getInt(10));
				bean.setCollegeName(rs.getString(11));
				bean.setCourseId(rs.getInt(12));
				bean.setCourseName(rs.getString(13));
				bean.setSubjectId(rs.getInt(14));
				bean.setSubjectName(rs.getString(15));
				bean.setCreatedBy(rs.getString(16));
				bean.setModifiedBy(rs.getString(17));
				bean.setCreateDatetime(rs.getTimestamp(18));
				bean.setModifiedDatetime(rs.getTimestamp(19));
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
log.debug("Model findByPk Ended");
		return bean;
	}

	/**
	 * Find by email.
	 *
	 * @param email
	 *            the email
	 * @return the faculty bean
	 * @throws Exception
	 *             the exception
	 */
	public static FacultyBean findByEmail(String email) throws Exception {
		 log.debug(" Model findByEmail Strarted");
		System.out.println("findByemail model called");
		Connection conn = null;
		FacultyBean bean = null;
		StringBuffer sql = new StringBuffer("select * from st_faculty where login_id=?");
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());

			pstmt.setString(1, email);

			ResultSet rs = pstmt.executeQuery();
			conn.commit();
			while (rs.next()) {
				bean = new FacultyBean();

				bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setGender(rs.getString(4));
				bean.setLoginId(rs.getString(5));
				bean.setAddress(rs.getString(6));
				bean.setDateOfJoining(rs.getDate(7));
				bean.setQualification(rs.getString(8));
				bean.setMobileNo(rs.getString(9));
				bean.setCollegeId(rs.getInt(10));
				bean.setCollegeName(rs.getString(11));
				bean.setCourseId(rs.getInt(12));
				bean.setCourseName(rs.getString(13));
				bean.setSubjectId(rs.getInt(14));
				bean.setSubjectName(rs.getString(15));
				bean.setCreatedBy(rs.getString(16));
				bean.setModifiedBy(rs.getString(17));
				bean.setCreateDatetime(rs.getTimestamp(18));
				bean.setModifiedDatetime(rs.getTimestamp(19));
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
log.debug("Model findByemail Ended");
		return bean;

	}

	/**
	 * Search.
	 *
	 * @param bean
	 *            the bean
	 * @param pageNo
	 *            the page no
	 * @param pageSize
	 *            the page size
	 * @return the list
	 * @throws Exception
	 *             the exception
	 */
	public static List search(FacultyBean bean, int pageNo, int pageSize) throws Exception {

		 log.debug("Model search Started");
		System.out.println("findByPk model called");
		Connection conn = null;
		System.out.println(" search list " + bean.getCollegeName());

		StringBuffer sql = new StringBuffer("select * from st_faculty where 1=1");

		System.out.println("login id=" + bean.getLoginId());
		if (bean != null) {

			if (bean.getId() > 0) {
				sql.append(" And id = " + bean.getId());
			}

			if (bean.getFirstName() != null && bean.getFirstName().length() > 0) {
				sql.append(" AND FIRST_NAME LIKE '" + bean.getFirstName() + "%'");
			}
			if (bean.getLastName() != null && bean.getLastName().length() > 0) {
				sql.append(" AND LAST_NAME LIKE '" + bean.getLastName() + "%'");
			}
			if (bean.getGender() != null && bean.getGender().length() > 0) {
				sql.append(" AND GENDER LIKE '" + bean.getGender() + "%'");
			}
			if (bean.getLoginId() != null && bean.getLoginId().length() > 0) {
				sql.append(" AND LOGIN_ID LIKE '" + bean.getLoginId() + "%'");
			}

			if (bean.getDateOfJoining() != null && bean.getDateOfJoining().getDate() > 0) {
				sql.append(" AND DATE_OF_JOINING = " + bean.getDateOfJoining());
			}
			if (bean.getQualification() != null && bean.getQualification().length() > 0) {
				sql.append(" AND QUALIFICATION LIKE '" + bean.getQualification() + "%'");
			}
			if (bean.getMobileNo() != null && bean.getMobileNo().length() > 0) {
				sql.append(" AND MOBILE_NO LIKE '" + bean.getMobileNo() + "%'");
			}
			if (bean.getCollegeId() > 0) {
				sql.append(" AND COLLEGE_ID ="  + bean.getCollegeId() );
			}
			if (bean.getCollegeName() != null && bean.getCollegeName().length() > 0) {
				sql.append(" AND COLLEGE_NAME LIKE '" + bean.getCollegeName() + "%'");
			}
			if (bean.getCourseId() > 0) {
				sql.append(" AND COURSE_ID =" +bean.getCourseId() );
			}
			if (bean.getCourseName() != null && bean.getCourseName().length() > 0) {
				sql.append(" AND COURSE_NAME LIKE '" + bean.getCourseName() + "%'");
			}
			if (bean.getSubjectId() > 0) {
				sql.append(" AND SUBJECT_ID = '" + bean.getSubjectId() + "%'");
			}
			if (bean.getSubjectName() != null && bean.getSubjectName().length() > 0) {
				sql.append(" AND SUBJECT_NAME LIKE '" + bean.getSubjectName() + "%'");
			}

		}
		System.out.println("sql query is" + sql);
		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" LIMIT " + pageNo + " ," + pageSize);
		}
		List list = new ArrayList();

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());

			ResultSet rs = pstmt.executeQuery();
			conn.commit();
			while (rs.next()) {
				FacultyBean bean1 = new FacultyBean();

				bean1.setId(rs.getLong(1));
				bean1.setFirstName(rs.getString(2));
				bean1.setLastName(rs.getString(3));
				bean1.setGender(rs.getString(4));
				bean1.setLoginId(rs.getString(5));
				bean1.setAddress(rs.getString(6));
				bean1.setDateOfJoining(rs.getDate(7));
				bean1.setQualification(rs.getString(8));
				bean1.setMobileNo(rs.getString(9));
				bean1.setCollegeId(rs.getInt(10));
				bean1.setCollegeName(rs.getString(11));
				bean1.setCourseId(rs.getInt(12));
				bean1.setCourseName(rs.getString(13));
				bean1.setSubjectId(rs.getInt(14));
				bean1.setSubjectName(rs.getString(15));
				bean1.setCreatedBy(rs.getString(16));
				bean1.setModifiedBy(rs.getString(17));
				bean1.setCreateDatetime(rs.getTimestamp(18));
				bean1.setModifiedDatetime(rs.getTimestamp(19));
				list.add(bean1);
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
log.debug("model search Ended");
		return list;
	}

	/**
	 * List.
	 *
	 * @return the list
	 * @throws Exception
	 *             the exception
	 */
	public List list() throws Exception {
		return list(0, 0);
	}

	/**
	 * List.
	 *
	 * @param pageNo
	 *            the page no
	 * @param pageSize
	 *            the page size
	 * @return the list
	 * @throws Exception
	 *             the exception
	 */
	public static List list(int pageNo, int pageSize) throws Exception {
		 log.debug("Model list Started");
		System.out.println("list model called");
		Connection conn = null;

		StringBuffer sql = new StringBuffer("select * from st_faculty");

		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" LIMIT " + pageNo + " ," + pageSize);
		}
		List list = new ArrayList();

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());

			ResultSet rs = pstmt.executeQuery();
			conn.commit();
			while (rs.next()) {
				FacultyBean bean1 = new FacultyBean();

				bean1.setId(rs.getLong(1));
				bean1.setFirstName(rs.getString(2));
				bean1.setLastName(rs.getString(3));
				bean1.setGender(rs.getString(4));
				bean1.setLoginId(rs.getString(5));
				bean1.setAddress(rs.getString(6));
				bean1.setDateOfJoining(rs.getDate(7));
				bean1.setQualification(rs.getString(8));
				bean1.setMobileNo(rs.getString(9));
				bean1.setCollegeId(rs.getInt(10));
				bean1.setCollegeName(rs.getString(11));
				bean1.setCourseId(rs.getInt(12));
				bean1.setCourseName(rs.getString(13));
				bean1.setSubjectId(rs.getInt(14));
				bean1.setSubjectName(rs.getString(15));
				bean1.setCreatedBy(rs.getString(16));
				bean1.setModifiedBy(rs.getString(17));
				bean1.setCreateDatetime(rs.getTimestamp(18));
				bean1.setModifiedDatetime(rs.getTimestamp(19));
				list.add(bean1);
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
log.debug("Model list Ended");
		return list;

	}
}
