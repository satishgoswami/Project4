package in.co.sunrays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.sunrays.proj4.bean.CourseBean;
import in.co.sunrays.proj4.bean.SubjectBean;
import in.co.sunrays.proj4.bean.TimetableBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.util.JDBCDataSource;
/**
* JDBC Implementation of TimeTable Model
* 
@author Facade
* @version 1.0
*Facade
*/
public class Timetablemodel {
	private static Logger log = Logger.getLogger(Timetablemodel.class);
	/**
	 * The Class TimetableModel.
	 */

	/**
	 * Next pk.
	 *
	 * @return the integer
	 * @throws Exception
	 *             the exception
	 */
	public  static Integer nextPk() throws Exception {
		log.debug("Model nextPk Started");
		int pk = 0;

		Connection conn = null;
		try {

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_timetable");
			ResultSet rs = pstmt.executeQuery();
			conn.commit();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.getMessage();
		} finally {
			conn.close();
		}
		log.debug("Model nextPk Ended");
		return pk = pk + 1;
	}

	/**
	 * Find by pk.
	 *
	 * @param pk
	 *            the pk
	 * @return the timetable bean
	 * @throws Exception
	 *             the exception
	 */
	public  static TimetableBean findByPk(long pk) throws Exception {
		log.debug("Model findByPk Started");
		Connection conn = null;

		TimetableBean bean = null;
		StringBuffer sql = new StringBuffer("select * from st_timetable where id =?");

		System.out.println("id is" + sql);
		System.out.println("pk is" + pk);
		try {

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);

			ResultSet rs = pstmt.executeQuery();
			conn.commit();
			while (rs.next()) {
				bean = new TimetableBean();
				bean.setId(rs.getLong(1));
				bean.setCourseId(rs.getInt(2));
				bean.setCourseName(rs.getString(3));
				bean.setSubjectId(rs.getInt(4));
				bean.setSubjectName(rs.getString(5));
				bean.setSemester(rs.getString(6));
				bean.setExamTime(rs.getString(7));
				bean.setExamDate(rs.getDate(8));
				bean.setCreatedBy(rs.getString(9));
				bean.setModifiedBy(rs.getString(10));
				bean.setCreateDatetime(rs.getTimestamp(11));
				bean.setModifiedDatetime(rs.getTimestamp(12));

			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
		log.debug("Model findByPk Ended");
		return bean;
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
	public  static long add(TimetableBean bean) throws Exception {
		log.debug("Model add Started");
		int pk = 0;

		Connection conn = null;

		CourseModel cmodel = new CourseModel();
		CourseBean cbean = cmodel.findByPK(bean.getCourseId());
		/*System.out.println("cbean value" + cbean);
		System.out.println("course name is" + cbean.getCourseName());*/
		bean.setCourseName(cbean.getCourseName());

		Subjectmodel smodel = new Subjectmodel();
		SubjectBean sbean = smodel.findByPk(bean.getSubjectId());
	//	System.out.println("subject name" + sbean.getSubjectName());
		bean.setSubjectName(sbean.getSubjectName());

		TimetableBean bean1 = findByCSS(bean.getCourseName(), bean.getSubjectName(), bean.getSemester());

		//System.out.println("bean1 value--------> " + bean1.getCourseId());
		
		TimetableBean bean2 = findByCSE(bean.getCourseName(), bean.getSemester(),
				new java.sql.Date(bean.getExamDate().getTime()));

		
		//System.out.println("bean2 value----------> " + bean2.getSubjectId());

		if (bean1 != null || bean2 != null) {
			throw new Exception("exam timetable already exist");
		}

		try {
			pk = nextPk();
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("insert into st_timetable values(?,?,?,?,?,?,?,?,?,?,?,?)");

			pstmt.setLong(1, pk);
			pstmt.setInt(2, bean.getCourseId());
			pstmt.setString(3, bean.getCourseName());
			pstmt.setInt(4, bean.getSubjectId());
			pstmt.setString(5, bean.getSubjectName());
			pstmt.setString(6, bean.getSemester());
			pstmt.setString(7, bean.getExamTime());
			pstmt.setDate(8, new java.sql.Date(bean.getExamDate().getTime()));
			pstmt.setString(9, bean.getCreatedBy());
			pstmt.setString(10, bean.getModifiedBy());
			pstmt.setTimestamp(11, bean.getCreateDatetime());
			pstmt.setTimestamp(12, bean.getModifiedDatetime());
			int i = pstmt.executeUpdate();
			conn.commit();
			System.out.println("record inserted" + i);
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
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
	 * @throws SQLException
	 *             the SQL exception
	 */
	public static void delete(TimetableBean bean) throws ApplicationException {
		log.debug("Model delete Started");
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("delete from st_timetable where id =?");
			pstmt.setLong(1, bean.getId());
			pstmt.executeUpdate();
			conn.commit();
			System.out.println("record deleted");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model delete Ended");
	}

	/**
	 * Update.
	 *
	 * @param bean
	 *            the bean
	 * @throws Exception
	 *             the exception
	 */
	public static void update(TimetableBean bean) throws Exception {
log.debug("Model update Started");
		Connection conn = null;

		CourseModel cmodel = new CourseModel();
		CourseBean cbean = cmodel.findByPK(bean.getCourseId());
		System.out.println("cbean value" + cbean);
		System.out.println("course name is" + cbean.getCourseName());
		bean.setCourseName(cbean.getCourseName());

		Subjectmodel smodel = new Subjectmodel();
		SubjectBean sbean = smodel.findByPk(bean.getSubjectId());
		System.out.println("subject name" + sbean.getSubjectName());
		bean.setSubjectName(sbean.getSubjectName());

		TimetableBean bean1 = findByCSS(bean.getCourseName(), bean.getSubjectName(), bean.getSemester());

		TimetableBean bean2 = findByCSE(bean.getCourseName(), bean.getSemester(),
				new java.sql.Date(bean.getExamDate().getTime()));

		System.out.println("bean1 value " + bean1);
		System.out.println("bean2 value " + bean2);

		if (bean1 != null || bean2 != null) {
			throw new Exception("exam timetable already exist");
		}

		try {

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(
					"update st_timetable set course_id=?,course_name=?,subject_id=?,subject_name=?,semester=?,exam_time=?,exam_date=?,created_by=?,modified_by=?,created_datetime=?,modified_datetime=? where id =?");

			pstmt.setInt(1, bean.getCourseId());
			pstmt.setString(2, bean.getCourseName());
			pstmt.setInt(3, bean.getSubjectId());
			pstmt.setString(4, bean.getSubjectName());
			pstmt.setString(5, bean.getSemester());
			pstmt.setString(6, bean.getExamTime());
			pstmt.setDate(7, new java.sql.Date(bean.getExamDate().getTime()));
			pstmt.setString(8, bean.getCreatedBy());
			pstmt.setString(9, bean.getModifiedBy());
			pstmt.setTimestamp(10, bean.getCreateDatetime());
			pstmt.setTimestamp(11, bean.getModifiedDatetime());
			pstmt.setLong(12, bean.getId());

			pstmt.executeUpdate();
			conn.commit();
			System.out.println("record updated");
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
		log.debug("Model update Ended");
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
	public static List search(TimetableBean bean, int pageNo, int pageSize) throws Exception {
log.debug("Model search Started");
		List list = new ArrayList();

		Connection conn = null;
		StringBuffer sql = new StringBuffer("select * from st_timetable where 1=1");

		if (bean != null) {

			/*
			 * if (bean.getId() > 0) {
			 * 
			 * sql.append(" And id= " + bean.getId()); }
			 */
			if (bean.getCourseId() > 0) {
				System.out.println(" course_id search ");
				sql.append(" And course_id= " + bean.getCourseId());
			}
			/*
			 * if (bean.getCourseName() != null && bean.getCourseName().length()
			 * > 0) { sql.append(" AND COURSE_NAME LIKE '" +
			 * bean.getCourseName() + "%'"); }
			 */
			if (bean.getSubjectId() > 0) {
				sql.append(" And subject_id= " + bean.getSubjectId());
			}
			/*
			 * if (bean.getSubjectName() != null &&
			 * bean.getSubjectName().length() > 0) { sql.append(
			 * " AND SUBJECT_NAME LIKE '" + bean.getSubjectName() + "%'"); }
			 */
			/*
			 * if (bean.getSemester() != null && bean.getSemester().length() >
			 * 0) { sql.append(" AND SEMESTER LIKE '" + bean.getSemester() +
			 * "%'"); }
			 * 
			 */
			/*
			 * if (bean.getExamTime() != null && bean.getExamTime().length() >
			 * 0) { sql.append(" AND EXAM_TIME = '" + bean.getExamTime() + "'");
			 * }
			 */

			if (bean.getExamDate() != null && bean.getExamDate().getTime() > 0) {

				System.out.println("date before for" + bean.getExamDate().getTime());

				Date d = new Date(bean.getExamDate().getTime());

				sql.append(" AND EXAM_DATE= '"+new java.sql.Date(bean.getExamDate().getTime())+"'");
			}

		}

		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" LIMIT " + pageNo + " ," + pageSize);
		}
		System.out.println("sql quert is" + sql);
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			TimetableBean bean1;
			while (rs.next()) {
				bean1 = new TimetableBean();

				bean1.setId(rs.getLong(1));
				bean1.setCourseId(rs.getInt(2));
				bean1.setCourseName(rs.getString(3));
				bean1.setSubjectId(rs.getInt(4));
				bean1.setSubjectName(rs.getString(5));
				bean1.setSemester(rs.getString(6));
				bean1.setExamTime(rs.getString(7));
				bean1.setExamDate(rs.getDate(8));
				bean1.setCreatedBy(rs.getString(9));
				bean1.setModifiedBy(rs.getString(10));
				bean1.setCreateDatetime(rs.getTimestamp(11));
				bean1.setModifiedDatetime(rs.getTimestamp(12));

				list.add(bean1);
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}
		log.debug("Model search Ended");
		return list;
	}

	/**
	 * Search.
	 *
	 * @param bean
	 *            the bean
	 * @return the list
	 * @throws Exception
	 *             the exception
	 */
	public static List search(TimetableBean bean) throws Exception {
		return search(bean, 0, 0);
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
		List list = new ArrayList();

		Connection conn = null;
		StringBuffer sql = new StringBuffer("select * from st_timetable where 1=1");

		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" LIMIT " + pageNo + " ," + pageSize);
		}
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			TimetableBean bean1;
			while (rs.next()) {
				bean1 = new TimetableBean();

				bean1.setId(rs.getLong(1));
				bean1.setCourseId(rs.getInt(2));
				bean1.setCourseName(rs.getString(3));
				bean1.setSubjectId(rs.getInt(4));
				bean1.setSubjectName(rs.getString(5));
				bean1.setSemester(rs.getString(6));
				bean1.setExamTime(rs.getString(7));
				bean1.setExamDate(rs.getDate(8));
				bean1.setCreatedBy(rs.getString(9));
				bean1.setModifiedBy(rs.getString(10));
				bean1.setCreateDatetime(rs.getTimestamp(11));
				bean1.setModifiedDatetime(rs.getTimestamp(12));

				list.add(bean1);
			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
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
	public static List list() throws Exception {
		return list(0, 0);
	}

	/**
	 * Find by course name.
	 *
	 * @param courseId
	 *            the course id
	 * @param examDate
	 *            the exam date
	 * @return the timetable bean
	 * @throws Exception
	 *             the exception
	 */
	public TimetableBean findByCourseName(long courseId, java.util.Date examDate) throws Exception {
		log.debug("Model findbyCoursename Started");
		Connection conn = null;
		TimetableBean bean1 = new TimetableBean();

		Date examdate1 = new Date(examDate.getTime());

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn
					.prepareStatement("select * from st_timetable where course_id=? AND exam_date=?");

			System.out.println("examdate" + examdate1 + "coutr" + courseId);

			pstmt.setLong(1, courseId);
			pstmt.setDate(2, new java.sql.Date(bean1.getExamDate().getTime()));

			ResultSet rs = pstmt.executeQuery();
			conn.commit();
			long id = nextPk();

			while (rs.next()) {
				System.out.println("rs");
				bean1 = new TimetableBean();
				bean1.setId(rs.getLong(1));
				bean1.setCourseId(rs.getInt(2));
				bean1.setCourseName(rs.getString(3));
				bean1.setSubjectId(rs.getInt(4));
				bean1.setSubjectName(rs.getString(5));
				bean1.setSemester(rs.getString(6));
				bean1.setExamTime(rs.getString(7));
				bean1.setExamDate(rs.getDate(8));
				bean1.setCreatedBy(rs.getString(9));
				bean1.setModifiedBy(rs.getString(10));
				bean1.setCreateDatetime(rs.getTimestamp(11));
				bean1.setModifiedDatetime(rs.getTimestamp(12));
			}

		} catch (Exception e) {
			conn.rollback();
			e.printStackTrace();
		} finally {
			conn.close();
		}
		log.debug("Model findbyCoursename Ended");
		return bean1;
	}

	/**
	 * Find by CSS.
	 *
	 * @param course
	 *            the course
	 * @param subject
	 *            the subject
	 * @param sem
	 *            the sem
	 * @return the timetable bean
	 * @throws Exception
	 *             the exception
	 */
	public static TimetableBean findByCSS(String cour,String sub,String sem) throws Exception {
		log.debug("Model CSS Started");
		Connection conn = null;
		TimetableBean bean1=null ;

		StringBuffer sql = new StringBuffer("select * from st_timetable where course_name=? AND subject_name=? AND semester=?");

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			//pstmt.setLong(1, pk);
			pstmt.setString(1, cour);
			pstmt.setString(2, sub);
			pstmt.setString(3, sem);

			ResultSet rs = pstmt.executeQuery();
			conn.commit();

			System.out.println("hhhhhhhhh");
			while (rs.next()) {

				System.out.println("while--------->");
				bean1 = new TimetableBean();

				bean1.setId(rs.getLong(1));
				bean1.setCourseId(rs.getInt(2));
				bean1.setCourseName(rs.getString(3));
				bean1.setSubjectId(rs.getInt(4));
				bean1.setSubjectName(rs.getString(5));
				bean1.setSemester(rs.getString(6));
				bean1.setExamTime(rs.getString(7));
				bean1.setExamDate(rs.getDate(8));
				bean1.setCreatedBy(rs.getString(9));
				bean1.setModifiedBy(rs.getString(10));
				bean1.setCreateDatetime(rs.getTimestamp(11));
				bean1.setModifiedDatetime(rs.getTimestamp(12));

			}
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		}
		log.debug("Model CSS Ended");
		//System.out.println("cour name-------->jjj"+bean1.getCourseName());
		return bean1;

		
		/*
		Connection conn = null;
		TimetableBean bean = null;

		StringBuffer sql = new StringBuffer(
				"select * from st_timetable where course_name=? AND subject_name=? AND semester=?");

		System.out.println("sql--------->"+sql);
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, course);
			pstmt.setString(2, subject);
			pstmt.setString(3, sem);

			ResultSet rs = pstmt.executeQuery();
			System.out.println("rs executed--------->"+rs);

			while (rs.next()) {
				System.out.println("inside rs-------->");
				bean = new TimetableBean();
				bean.setId(rs.getLong(1));
				bean.setCourseId(rs.getInt(2));
				bean.setCourseName(rs.getString(3));
				bean.setSubjectId(rs.getInt(4));
				bean.setSubjectName(rs.getString(5));
				bean.setSemester(rs.getString(6));
				bean.setExamTime(rs.getString(7));
				bean.setExamDate(rs.getDate(8));
				bean.setCreatedBy(rs.getString(9));
				bean.setModifiedBy(rs.getString(10));
				bean.setCreateDatetime(rs.getTimestamp(11));
				bean.setModifiedDatetime(rs.getTimestamp(12));

			}
			conn.commit();
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		}

		return bean;
*/
		
	}

	/**
	 * Find by CSE.
	 *
	 * @param course
	 *            the course
	 * @param sem
	 *            the sem
	 * @param examDate
	 *            the exam date
	 * @return the timetable bean
	 * @throws Exception
	 *             the exception
	 */
	public static TimetableBean findByCSE(String course, String sem, java.util.Date examDate) throws Exception {
		log.debug("Model CSE Started");
		Connection conn = null;
		TimetableBean bean1 = null;

		StringBuffer sql = new StringBuffer(
				"select * from st_timetable where course_name=? AND semester=? AND exam_date=?");

		Date examDate1 = new Date(examDate.getTime());

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, course);
			pstmt.setString(2, sem);
			pstmt.setDate(3, new java.sql.Date(examDate.getTime()));

			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {

				bean1 = new TimetableBean();

				bean1.setId(rs.getLong(1));
				bean1.setCourseId(rs.getInt(2));
				bean1.setCourseName(rs.getString(3));
				bean1.setSubjectId(rs.getInt(4));
				bean1.setSubjectName(rs.getString(5));
				bean1.setSemester(rs.getString(6));
				bean1.setExamTime(rs.getString(7));
				bean1.setExamDate(rs.getDate(8));
				bean1.setCreatedBy(rs.getString(9));
				bean1.setModifiedBy(rs.getString(10));
				bean1.setCreateDatetime(rs.getTimestamp(11));
				bean1.setModifiedDatetime(rs.getTimestamp(12));

			}
			conn.commit();
			rs.close();
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			conn.rollback();
		}
		log.debug("Model CSE Ended");
		return bean1;
	}

}
