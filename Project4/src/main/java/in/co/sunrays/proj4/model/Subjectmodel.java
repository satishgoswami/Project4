package in.co.sunrays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.sunrays.proj4.bean.CourseBean;
import in.co.sunrays.proj4.bean.SubjectBean;
import in.co.sunrays.proj4.util.JDBCDataSource;

public class Subjectmodel {
	/**
	 * The Class SubjectModel.
	 */
	// public static class SubjectModel {

	/** The log. */
	 private static Logger log = Logger.getLogger(Subjectmodel.class);

	/**
	 * Next pk.
	 *
	 * @return the integer
	 * @throws SQLException
	 *             the SQL exception
	 */
	public static int nextPk() throws SQLException {

		 log.debug(" Model nextPk() Started");

		int pk = 0;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_subject");

			ResultSet rs = pstmt.executeQuery();
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
		log.debug(" Model nextPk() Started");
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
	public static long add(SubjectBean bean) throws Exception {

		 log.debug("Model add Started");
		int pk = 0;

		CourseModel cmodel = new CourseModel();
		CourseBean cbean = CourseModel.findByPK(bean.getCourseId());
		System.out.println("course name" + cbean.getCourseName());
		bean.setCourseName(cbean.getCourseName());

		SubjectBean beanExist = findByName(bean.getSubjectName());
		System.out.println("beanExist=" + beanExist);
		if (beanExist != null) {
			throw new Exception("subject name alredy exist");
		}

		Connection conn = null;

		try {
			pk = nextPk();
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("insert into st_subject values(?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setInt(2, bean.getCourseId());
			pstmt.setString(3, bean.getCourseName());
			pstmt.setString(4, bean.getSubjectName());
			pstmt.setString(5, bean.getDescription());
			pstmt.setString(6, bean.getCreatedBy());
			pstmt.setString(7, bean.getModifiedBy());
			pstmt.setTimestamp(8, bean.getCreateDatetime());
			pstmt.setTimestamp(9, bean.getModifiedDatetime());

			pstmt.executeUpdate();
			System.out.println("record added");
			conn.commit();
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
	 * Delete.
	 *
	 * @param bean
	 *            the bean
	 * @throws Exception
	 *             the exception
	 */
	public static void delete(SubjectBean bean) throws Exception {
		 log.debug(" Model delete Started");
		System.out.println("model delete  started");
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("delete from st_subject where id=?");
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
		log.debug("Model deleted End");
	}

	/**
	 * Update.
	 *
	 * @param bean
	 *            the bean
	 * @throws SQLException
	 *             the SQL exception
	 */
	public static void update(SubjectBean bean) throws SQLException {
		 log.debug("Model update Satarted");
		System.out.println("model update  started");
		Connection conn = null;

		try {

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(
					"update st_subject set course_id=?,course_name=?,subject_name=?,description=?,created_by=?,modified_by=?,created_datetime=?,modified_datetime=? where id=?");
			pstmt.setInt(1, bean.getCourseId());
			pstmt.setString(2, bean.getCourseName());
			pstmt.setString(3, bean.getSubjectName());
			pstmt.setString(4, bean.getDescription());
			pstmt.setString(5, bean.getCreatedBy());
			pstmt.setString(6, bean.getModifiedBy());
			pstmt.setTimestamp(7, bean.getCreateDatetime());
			pstmt.setTimestamp(8, bean.getModifiedDatetime());
			pstmt.setLong(9, bean.getId());

			pstmt.executeUpdate();
			System.out.println("record updated");
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
	 * Find by pk.
	 *
	 * @param pk
	 *            the pk
	 * @return the subject bean
	 * @throws SQLException
	 *             the SQL exception
	 */
	public static SubjectBean findByPk(long pk) throws SQLException {
		 log.debug("Model findByPk Started");
		System.out.println("model update  started");

		SubjectBean bean = new SubjectBean();

		StringBuffer sql = new StringBuffer("select * from st_subject where id=?");
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());

			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			conn.commit();
			while (rs.next()) {

				bean.setId(rs.getLong(1));
				bean.setCourseId(rs.getInt(2));
				bean.setCourseName(rs.getString(3));
				bean.setSubjectName(rs.getString(4));
				bean.setDescription(rs.getString(5));
				bean.setCreatedBy(rs.getString(6));
				bean.setModifiedBy(rs.getString(7));
				bean.setCreateDatetime(rs.getTimestamp(8));
				bean.setModifiedDatetime(rs.getTimestamp(9));

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
	 * Find by name.
	 *
	 * @param name
	 *            the name
	 * @return the subject bean
	 * @throws SQLException
	 *             the SQL exception
	 */
	public static SubjectBean findByName(String name) throws SQLException {
		 log.debug("Model findByName Stared");
		System.out.println("model update  started");

		SubjectBean bean = null;
		;

		StringBuffer sql = new StringBuffer("select * from st_subject where subject_name=?");
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());

			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			conn.commit();
			while (rs.next()) {
				bean = new SubjectBean();
				bean.setId(rs.getLong(1));
				bean.setCourseId(rs.getInt(2));
				bean.setCourseName(rs.getString(3));
				bean.setSubjectName(rs.getString(4));
				bean.setDescription(rs.getString(5));
				bean.setCreatedBy(rs.getString(6));
				bean.setModifiedBy(rs.getString(7));
				bean.setCreateDatetime(rs.getTimestamp(8));
				bean.setModifiedDatetime(rs.getTimestamp(9));

			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
		log.debug("Model findByName Stared");
		return bean;

	}

	/**
	 * Search.
	 *
	 * @param bean
	 *            the bean
	 * @return the list
	 */
	public List search(SubjectBean bean) {
		return search(bean);
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
	 * @throws SQLException
	 *             the SQL exception
	 */
	public List search(SubjectBean bean, int pageNo, int pageSize) throws SQLException {

		 log.debug(" Model search Started");

		List<SubjectBean> list = new ArrayList<SubjectBean>();

		StringBuffer sql = new StringBuffer("select * from st_subject where 1=1");

		Connection conn = null;

		if (bean != null) {

			if (bean.getId() > 0) {
				sql.append(" And ID =" + bean.getId());

			}
			if (bean.getCourseId() > 0) {
				sql.append(" And COURSE_ID =" + bean.getCourseId());

			}

			/*
			 * if(bean.getCourseName()!=null &&
			 * bean.getCourseName().length()>0){ sql.append(
			 * " AND COURSE_NAME LIKE '"+bean.getCourseName()+"%'"); }
			 * 
			 * if(bean.getSubjectName()!=null &&
			 * bean.getSubjectName().length()>0){ sql.append(
			 * " AND SUBJECT_NAME LIKE '"+bean.getSubjectName()+"%'"); }
			 * 
			 * if(bean.getDescription()!=null &&
			 * bean.getDescription().length()>0){ sql.append(
			 * " AND DESCRIPTION LIKE '"+bean.getDescription()+"%'"); }
			 */
		}
		if (pageSize > 0) {
			pageNo = ((pageNo - 1) * pageSize);
			sql.append(" LIMIT " + pageNo + " ," + pageSize);
		}

		System.out.println("sal" + sql);
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());

			ResultSet rs = pstmt.executeQuery();
			conn.commit();
			SubjectBean bean1 = null;
			while (rs.next()) {
				bean1 = new SubjectBean();

				bean1.setId(rs.getLong(1));
				bean1.setCourseId(rs.getInt(2));
				bean1.setCourseName(rs.getString(3));
				bean1.setSubjectName(rs.getString(4));
				bean1.setDescription(rs.getString(5));
				bean1.setCreatedBy(rs.getString(6));
				bean1.setModifiedBy(rs.getString(7));
				bean1.setCreateDatetime(rs.getTimestamp(8));
				bean1.setModifiedDatetime(rs.getTimestamp(9));

				list.add(bean1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
		log.debug(" Model search Ended");
		return list;
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
	public List list(int pageNo, int pageSize) throws Exception {
log.debug("Model list Started");
		System.out.println("inside list");
		Connection conn = null;
		List<SubjectBean> list = new ArrayList<SubjectBean>();

		StringBuffer sql = new StringBuffer("select * from st_subject");

		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" LIMIT " + pageNo + " ," + pageSize);
		}

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());

			ResultSet rs = pstmt.executeQuery();
			conn.commit();
			SubjectBean bean1 = null;
			while (rs.next()) {
				bean1 = new SubjectBean();

				bean1.setId(rs.getLong(1));
				bean1.setCourseId(rs.getInt(2));
				bean1.setCourseName(rs.getString(3));
				bean1.setSubjectName(rs.getString(4));
				bean1.setDescription(rs.getString(5));
				bean1.setCreatedBy(rs.getString(6));
				bean1.setModifiedBy(rs.getString(7));
				bean1.setCreateDatetime(rs.getTimestamp(8));
				bean1.setModifiedDatetime(rs.getTimestamp(9));

				list.add(bean1);
			}
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		} finally {
			conn.close();
		}
		log.debug("Model list Ended");
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

}
