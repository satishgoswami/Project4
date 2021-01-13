package in.co.sunrays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.sunrays.proj4.bean.UserBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.exception.DatabaseException;
import in.co.sunrays.proj4.exception.DuplicateRecordException;
import in.co.sunrays.proj4.exception.RecordNotFoundException;
import in.co.sunrays.proj4.util.EmailBuilder;
import in.co.sunrays.proj4.util.EmailMessage;
import in.co.sunrays.proj4.util.EmailUtility;
import in.co.sunrays.proj4.util.JDBCDataSource;

/**
 * JDBC Implementation of UserModel
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */
public class UserModel {

	/** The log. */
	public static Logger log = Logger.getLogger(UserModel.class);
	
	/** The pk. */
	 long pk = 0;

	/**
	 * Find next PK of user
	 * 
	 * @throws DatabaseException
	 */

	public  long nextPK() throws DatabaseException {
		log.debug("UserModel nextPk started");
		String query = "SELECT MAX(ID) FROM ST_USER";
		Connection con=null;
		UserBean ubean=null;
		try {

			con = JDBCDataSource.getConnection();
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				pk = rs.getLong(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		log.debug("UserModel nextPk ended.");
		return pk + 1;
	}

	/**
	 * Add a User
	 * 
	 * @param ubean
	 * @throws ApplicationException,
	 *             DuplicateRecordException
	 * 
	 */

	public long add(UserBean ubean) throws ApplicationException, DuplicateRecordException {
		log.debug("UserModel add started");
		Connection con=null;
		
		UserBean existubean = findByLogin(ubean.getLogin());
		if (existubean != null) {
			throw new DuplicateRecordException("Login id already exists.");
		}

		try {
			String query = "INSERT INTO ST_USER VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

			con = JDBCDataSource.getConnection();
			pk = nextPK();
			con.setAutoCommit(false);
			PreparedStatement ps = con.prepareStatement(query);
			ps.setLong(1, pk);
			ps.setString(2, ubean.getFirstName());
			ps.setString(3, ubean.getLastName());
			ps.setString(4, ubean.getLogin());
			ps.setString(5, ubean.getPassword());
			ps.setString(6, ubean.getAddress());
			ps.setDate(7, new java.sql.Date(ubean.getDob().getTime()));
			ps.setString(8, ubean.getMobileNo());
			ps.setLong(9, ubean.getRoleId());
			ps.setString(10,ubean.getRoleName());
			ps.setInt(11, ubean.getUnSuccessfulLogin());
			ps.setString(12, ubean.getGender());
			ps.setTimestamp(13, ubean.getLastLogin());
			ps.setString(14, ubean.getLock());
			ps.setString(15, ubean.getRegisteredIP());
			ps.setString(16, ubean.getLastLoginIP());
			ps.setString(17, ubean.getCreatedBy());
			ps.setString(18, ubean.getModifiedBy());
			ps.setTimestamp(19, ubean.getCreateDatetime());
			ps.setTimestamp(20, ubean.getCreateDatetime());
			int c = ps.executeUpdate();
			con.commit();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(con);
		}
		log.debug("UserModel add ended.");
		return pk;
	}

	/**
	 * Delete a User
	 * 
	 * @param ubean
	 * @throws ApplicationException
	 */
	public void delete(UserBean ubean) throws ApplicationException {
		log.debug("UserModel delete started");
		Connection con=null;
		try {
			String query = "DELETE FROM ST_USER WHERE ID=?";
			con = JDBCDataSource.getConnection();
			PreparedStatement ps = con.prepareStatement(query);
			ps.setLong(1, ubean.getId());
			int a = ps.executeUpdate();
			System.out.println("Rows deleted " + a);
			ps.close();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(con);
		}
		log.debug("UserModel delete ended.");
	}

	/**
	 * Find User by Login
	 * 
	 * @param login
	 *            : get parameter
	 * @return ubean
	 * @throws ApplicationException
	 */
	public static UserBean findByLogin(String login) throws ApplicationException {

		log.debug("Model findByLogin Started");
		Connection con=null;
		UserBean ubean=null;
		StringBuffer sql = new StringBuffer("SELECT* FROM ST_USER WHERE LOGIN=?");
		// System.out.println("sql is" + sql);

		try {
			con = JDBCDataSource.getConnection();
			PreparedStatement ps = con.prepareStatement(sql.toString());
			ps.setString(1, login);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ubean = new UserBean();
				ubean.setId(rs.getLong(1));
				ubean.setFirstName(rs.getString(2));
				ubean.setLastName(rs.getString(3));
				ubean.setLogin(rs.getString(4));
				ubean.setPassword(rs.getString(5));
				ubean.setAddress(rs.getString(6));
				ubean.setDob(rs.getDate(7));
				ubean.setMobileNo(rs.getString(8));
				ubean.setRoleId(rs.getLong(9));
				ubean.setRoleName(rs.getString(10));
				ubean.setUnSuccessfulLogin(rs.getInt(11));
				ubean.setGender(rs.getString(12));
				ubean.setLastLogin(rs.getTimestamp(13));
				ubean.setLock(rs.getString(14));
				ubean.setRegisteredIP(rs.getString(15));
				ubean.setLastLoginIP(rs.getString(16));
				ubean.setCreatedBy(rs.getString(17));
				ubean.setModifiedBy(rs.getString(18));
				ubean.setCreateDatetime(rs.getTimestamp(19));
				ubean.setModifiedDatetime(rs.getTimestamp(20));			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(con);
		}
		log.debug("Model findByLogin End");
		return ubean;
	}

	/**
	 * Update a user
	 * 
	 * @param ubean
	 * @throws ApplicationException,
	 *             DuplicateRecordException
	 */
	public static void update(UserBean ubean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model update Started");
		Connection con=null;
		System.out.println(ubean.getDob());
		// UserBean existubean=findByLogin(ubean.getLogin());

		// if(existubean!=null && !(existubean.getId() == ubean.getId())){
		// throw new DuplicateRecordException("LoginId is already exist");

		String sql = ("UPDATE ST_USER SET FIRST_NAME=?,LAST_NAME=?,LOGIN=?,PASSWORD=?,ADDRESS=?,DOB=?,MOBILE_NO=?,ROLE_ID=?,ROLE_NAME=?,UNSUCCESSFUL_LOGIN=?,GENDER=?,LAST_LOGIN=? WHERE ID=?");

		try {
			con = JDBCDataSource.getConnection();
			con.setAutoCommit(false); // Begin transaction
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, ubean.getFirstName());
			ps.setString(2, ubean.getLastName());
			ps.setString(3, ubean.getLogin());
			ps.setString(4, ubean.getPassword());
			ps.setString(5, ubean.getAddress());
			ps.setDate(6, new java.sql.Date(ubean.getDob().getTime()));
			ps.setString(7, ubean.getMobileNo());
			ps.setLong(8, ubean.getRoleId());
			ps.setString(9, ubean.getRoleName());
			ps.setInt(10, ubean.getUnSuccessfulLogin());
			ps.setString(11, ubean.getGender());
			ps.setTimestamp(12, ubean.getLastLogin());
			ps.setLong(13, ubean.getId());
			ps.executeUpdate();
			con.commit(); // End transaction
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			try {
				con.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception in updating User ");
		} finally {
			JDBCDataSource.closeConnection(con);
		}
		log.debug("Model update Ended");
	}

	/**
	 * Find User by PK
	 * 
	 * : get parameter
	 * 
	 * @return ubean
	 * @throws DatabaseException
	 * @param pk
	 */

	public static UserBean findByPK(long pk) throws ApplicationException {
		log.debug("Model findByPK started");
		StringBuffer sb = new StringBuffer("select* from st_user where ID=?");
		Connection con=null;
		UserBean ubean=null;
		try {
			con = JDBCDataSource.getConnection();
			PreparedStatement ps = con.prepareStatement(sb.toString());
			ps.setLong(1, pk);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ubean = new UserBean();
				ubean.setId(rs.getLong(1));
				ubean.setFirstName(rs.getString(2));
				ubean.setLastName(rs.getString(3));
				ubean.setLogin(rs.getString(4));
				ubean.setPassword(rs.getString(5));
				ubean.setAddress(rs.getString(6));
				ubean.setDob(rs.getDate(7));
				ubean.setMobileNo(rs.getString(8));
				ubean.setRoleId(rs.getLong(9));
				ubean.setRoleName(rs.getString(10));
				ubean.setUnSuccessfulLogin(rs.getInt(11));
				ubean.setGender(rs.getString(12));
				ubean.setLastLogin(rs.getTimestamp(13));
				ubean.setLock(rs.getString(14));
				ubean.setRegisteredIP(rs.getString(15));
				ubean.setLastLoginIP(rs.getString(16));
				ubean.setCreatedBy(rs.getString(17));
				ubean.setModifiedBy(rs.getString(18));
				ubean.setCreateDatetime(rs.getTimestamp(19));
				ubean.setModifiedDatetime(rs.getTimestamp(20));			}
			
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCDataSource.closeConnection(con);
		}
		log.debug("Model findByPK End");
		return ubean;

	}

	/**
	 * Search User with pagination
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

	public List search(UserBean ubean, int pageno, int pagesize) throws ApplicationException {
		log.debug("Model search Started");
		Connection con=null;
		StringBuffer sql = new StringBuffer("SELECT* FROM ST_USER WHERE 1=1");

		if (ubean != null) {
			if (ubean.getId() > 0) {
				sql.append(" AND id = " + ubean.getId());
			}
			if (ubean.getFirstName() != null && ubean.getFirstName().trim().length() > 0) {
				sql.append(" AND FIRST_NAME like '" + ubean.getFirstName() + "%'");
			}
			if (ubean.getLastName() != null && ubean.getLastName().trim().length() > 0) {
				sql.append(" AND LAST_NAME like '" + ubean.getLastName() + "%'");
			}
			if (ubean.getLogin() != null && ubean.getLogin().trim().length() > 0) {
				sql.append(" AND LOGIN like '" + ubean.getLogin() + "%'");
			}
			if (ubean.getPassword() != null && ubean.getPassword().trim().length() > 0) {
				sql.append(" AND PASSWORD like '" + ubean.getPassword() + "%'");
			}
			if (ubean.getDob() != null && ubean.getDob().getDate() > 0) {
				sql.append(" AND DOB = " + ubean.getGender());
			}
			if (ubean.getMobileNo() != null && ubean.getMobileNo().trim().length() > 0) {
				sql.append(" AND MOBILE_NO = " + ubean.getMobileNo());
			}
			if (ubean.getRoleId() > 0) {
				sql.append(" AND ROLE_ID = " + ubean.getRoleId());
			}
			if (ubean.getRoleName() != null && ubean.getRoleName().trim().length() > 0) {
				sql.append(" AND ROLE_NAME like '" + ubean.getRoleName() + "%'");
			}

			if (ubean.getUnSuccessfulLogin() > 0) {
				sql.append(" AND UNSUCCESSFUL_LOGIN = " + ubean.getUnSuccessfulLogin());
			}
			if (ubean.getGender() != null && ubean.getGender().trim().length() > 0) {
				sql.append(" AND GENDER like '" + ubean.getGender() + "%'");
			}
			if (ubean.getLastLogin() != null && ubean.getLastLogin().getTime() > 0) {
				sql.append(" AND LAST_LOGIN = " + ubean.getLastLogin());
			}
			if (ubean.getRegisteredIP() != null && ubean.getRegisteredIP().trim().length() > 0) {
				sql.append(" AND REGISTERED_IP like '" + ubean.getRegisteredIP() + "%'");
			}
			if (ubean.getLastLoginIP() != null && ubean.getLastLoginIP().trim().length() > 0) {
				sql.append(" AND LAST_LOGIN_IP like '" + ubean.getLastLoginIP() + "%'");
			}

		}

		// if page size is greater than zero then apply pagination
		if (pagesize > 0) {
			// Calculate start record index
			pageno = (pageno - 1) * pagesize;

			sql.append(" Limit " + pageno + ", " + pagesize);
			// sql.append(" limit " + pageNo + "," + pageSize);
		}

		System.out.println(sql);
		List list = new ArrayList();

		try {
			con = JDBCDataSource.getConnection();
			PreparedStatement ps = con.prepareStatement(sql.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ubean = new UserBean();
				ubean.setId(rs.getLong(1));
				ubean.setFirstName(rs.getString(2));
				ubean.setLastName(rs.getString(3));
				ubean.setLogin(rs.getString(4));
				ubean.setPassword(rs.getString(5));
				ubean.setAddress(rs.getString(6));
				ubean.setDob(rs.getDate(7));
				ubean.setMobileNo(rs.getString(8));
				ubean.setRoleId(rs.getLong(9));
				ubean.setRoleName(rs.getString(10));
				ubean.setUnSuccessfulLogin(rs.getInt(11));
				ubean.setGender(rs.getString(12));
				ubean.setLastLogin(rs.getTimestamp(13));
				ubean.setLock(rs.getString(14));
				ubean.setRegisteredIP(rs.getString(15));
				ubean.setLastLoginIP(rs.getString(16));
				ubean.setCreatedBy(rs.getString(17));
				ubean.setModifiedBy(rs.getString(18));
				ubean.setCreateDatetime(rs.getTimestamp(19));
				ubean.setModifiedDatetime(rs.getTimestamp(20));

				list.add(ubean);
			}
			rs.close();
		} catch (Exception e) {
	         e.printStackTrace();
			log.error("Database Exception..", e);
			//throw new ApplicationException("Exception : Exception in search user");
		} finally {
			JDBCDataSource.closeConnection(con);
		}

		log.debug("Model search Ended");
		return list;
	} 

	/**
	 * Get List of User with pagination
	 * 
	 * @return list : List of users
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * @throws DatabaseException
	 */

	public List list(int pageNo, int pageSize) throws ApplicationException {

		log.debug("Model list Started");
		Connection con=null;
		UserBean ubean=null;
		List list = new ArrayList();
		StringBuffer sql = new StringBuffer("select * from ST_USER");

		// if page size is greater than zero then apply pagination
		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + "," + pageSize);
		}

		try {
			con = JDBCDataSource.getConnection();
			PreparedStatement pstmt = con.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				ubean = new UserBean();
				ubean.setId(rs.getLong(1));
				ubean.setFirstName(rs.getString(2));
				ubean.setLastName(rs.getString(3));
				ubean.setLogin(rs.getString(4));
				ubean.setPassword(rs.getString(5));
				ubean.setAddress(rs.getString(6));
				ubean.setDob(rs.getDate(7));
				ubean.setMobileNo(rs.getString(8));
				ubean.setRoleId(rs.getLong(9));
				ubean.setRoleName(rs.getString(10));
				ubean.setUnSuccessfulLogin(rs.getInt(11));
				ubean.setGender(rs.getString(12));
				ubean.setLastLogin(rs.getTimestamp(13));
				ubean.setLock(rs.getString(14));
				ubean.setRegisteredIP(rs.getString(15));
				ubean.setLastLoginIP(rs.getString(16));
				ubean.setCreatedBy(rs.getString(17));
				ubean.setModifiedBy(rs.getString(18));
				ubean.setCreateDatetime(rs.getTimestamp(19));
				ubean.setModifiedDatetime(rs.getTimestamp(20));
				list.add(ubean);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting list of users");
		} finally {
			JDBCDataSource.closeConnection(con);
		}

		log.debug("Model list End");
		return list;

	}

	/**
	 * Authenticate.
	 *
	 * @param login the login
	 * @param pass the pass
	 * @return the user bean
	 * @throws ApplicationException the application exception
	 */
	public UserBean authenticate(String login,String pass) throws ApplicationException {

		log.debug("Model authenticate Started");
		Connection con=null;
		UserBean ubean=null;
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_USER WHERE LOGIN = ? AND PASSWORD=?");

		try {
			con = JDBCDataSource.getConnection();
			PreparedStatement ps = con.prepareStatement(sql.toString());
			ps.setString(1, login);
			ps.setString(2, pass);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ubean = new UserBean();
				ubean.setId(rs.getLong(1));
				ubean.setFirstName(rs.getString(2));
				ubean.setLastName(rs.getString(3));
				ubean.setLogin(rs.getString(4));
				ubean.setPassword(rs.getString(5));
				ubean.setAddress(rs.getString(6));
				ubean.setDob(rs.getDate(7));
				ubean.setMobileNo(rs.getString(8));
				ubean.setRoleId(rs.getLong(9));
				ubean.setRoleName(rs.getString(10));
				ubean.setUnSuccessfulLogin(rs.getInt(11));
				ubean.setGender(rs.getString(12));
				ubean.setLastLogin(rs.getTimestamp(13));
				ubean.setLock(rs.getString(14));
				ubean.setRegisteredIP(rs.getString(15));
				ubean.setLastLoginIP(rs.getString(16));
				ubean.setCreatedBy(rs.getString(17));
				ubean.setModifiedBy(rs.getString(18));
				ubean.setCreateDatetime(rs.getTimestamp(19));
				ubean.setModifiedDatetime(rs.getTimestamp(20));

			}
		} catch (Exception e) {
			log.error("Database Exception..", e);
			e.printStackTrace();
			throw new ApplicationException("Exception : Exception in get roles");

		} finally {
			JDBCDataSource.closeConnection(con);
		}

		log.debug("Model authenticate End");
		return ubean;
	}
	
	/**
	 * Register user.
	 *
	 * @param ubean the ubean
	 * @return the long
	 * @throws ApplicationException the application exception
	 * @throws DuplicateRecordException the duplicate record exception
	 */
	public long registerUser(UserBean ubean) throws ApplicationException, DuplicateRecordException {

		log.debug("Model RegisterUser Started");
		long pk = add(ubean);

		HashMap<String, String> map = new HashMap<String, String>();

		map.put("login", ubean.getLogin());
		map.put("password", ubean.getPassword());

		String message = EmailBuilder.getUserRegistrationMessage(map);
		EmailMessage msg = new EmailMessage();

		msg.setTo(ubean.getLogin());
		msg.setSubject("Registration is successful for ORS Project SunilOS");
		msg.setMessage(message);
		msg.setMessageType(EmailMessage.HTML_MSG);

		EmailUtility.sendMail(msg);
		return pk;
	}
	/**
	 * Get User Roles
	 * 
	 * @return List : User Role List
o	 * @param bean
	 * @throws RecordNotFoundException
	 * 
	 */
	public static List getRoles(UserBean bean) throws RecordNotFoundException
	{
		log.debug("Roles started");
		List<UserBean> list = null;
		Connection con=null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		UserBean ubean=null;
		long Role = bean.getRoleId();
		
		try {
			con = JDBCDataSource.getConnection();
			ps = con.prepareStatement("SELECT * FROM ST_USER WHERE ROLE_ID=?");
			ps.setLong(1, Role);
			rs = ps.executeQuery();
			list = new ArrayList<UserBean>();
			while(rs.next())
			{
				log.debug("Roles result set started");
				System.out.println("count");
				ubean = new UserBean();
				ubean.setId(rs.getLong(1));
				ubean.setFirstName(rs.getString(2));
				ubean.setLastName(rs.getString(3));
				ubean.setLogin(rs.getString(4));
				ubean.setPassword(rs.getString(5));
				ubean.setAddress(rs.getString(6));
				ubean.setDob(rs.getDate(7));
				ubean.setMobileNo(rs.getString(8));
				ubean.setRoleId(rs.getLong(9));
				ubean.setRoleName(rs.getString(10));
				ubean.setUnSuccessfulLogin(rs.getInt(11));
				ubean.setGender(rs.getString(12));
				ubean.setLastLogin(rs.getTimestamp(13));
				ubean.setLock(rs.getString(14));
				ubean.setRegisteredIP(rs.getString(15));
				ubean.setLastLoginIP(rs.getString(16));
				ubean.setCreatedBy(rs.getString(17));
				ubean.setModifiedBy(rs.getString(18));
				ubean.setCreateDatetime(rs.getTimestamp(19));
				ubean.setModifiedDatetime(rs.getTimestamp(20));

			}
		} catch (Exception e) {
			log.debug("Roles catch block");
			e.printStackTrace();
		}	
		return list;
	}

	/**
	 * Gets the role id.
	 *
	 * @return the role id
	 */
	public long getRoleId() {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * @param id
	 * @param oldPassword
	 * @param newPassword
	 * @return
	 * @throws RecordNotFoundException
	 * @throws ApplicationException
	 */
	public static boolean changePassword(long id, String oldPassword, String newPassword) throws ApplicationException,  RecordNotFoundException 
	{
		log.debug("changePassword started");
		Connection con = null;
		PreparedStatement ps=null;
		boolean flag=false;
		
		UserBean ub = new UserBean();
		
		ub = findByPK(id);
		System.out.println(ub.getFirstName());
		
		if(ub!=null && ub.getPassword().equals(oldPassword) )
		{	System.out.println("password matched");
			
		ub.setPassword(newPassword);
		try{
		update(ub);
		}
		 catch (DuplicateRecordException e) {
				e.printStackTrace();
			}
		
		flag=true;
		}
		else {
			throw new RecordNotFoundException("Login not exist");
		}
		/*	HashMap<String, String> map = new HashMap<String, String>();
			map.put("login", ub.getLogin());
	        map.put("password", ub.getPassword());
	        map.put("firstName", ub.getFirstName());
	        map.put("lastName", ub.getLastName());
	        
	       String message = EmailBuilder.getChangePasswordMessage(map);
			
	       EmailMessage msg = new EmailMessage();
	       msg.setTo(ub.getLogin());
	       msg.setSubject("SUNARYS ORS Password has been changed Successfully.");
	       msg.setMessage(message);
	       msg.setMessageType(EmailMessage.HTML_MSG); 
	       
	       EmailUtility.sendMail(msg);
		*/
		return flag;
	}
	
	/**
	 * Send the password of User to his Email
	 * 
	 * @return boolean : true if success otherwise false
	 * @param login
	 *            : User Login
	 * @throws ApplicationException
	 * @throws RecordNotFoundException
	 *             : if user not found
	 */
	public static boolean forgetPassword(String login) throws ApplicationException, RecordNotFoundException 
	{
		UserBean bean = new UserBean();
		boolean flag=false;
		bean = findByLogin(login);
		if(bean==null)
		{
		  throw new RecordNotFoundException("Login Id Not Found");	
		}else{
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("login", bean.getLogin());
		map.put("password", bean.getPassword());
		map.put("firstName", bean.getFirstName());
		map.put("lastName", bean.getLastName());
		String message = EmailBuilder.getForgetPasswordMessage(map);
		
		EmailMessage msg = new EmailMessage();
		msg.setTo(login);
		msg.setSubject("Password Reset");
		msg.setMessage(message);
		msg.setMessageType(EmailMessage.HTML_MSG);
		
		EmailUtility.sendMail(msg);
		flag=true;
		}
		return flag;
	}

}
