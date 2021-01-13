package in.co.sunrays.proj4.test;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.mail.internet.ParseException;

import in.co.sunrays.proj4.bean.RoleBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.exception.DuplicateRecordException;
import in.co.sunrays.proj4.model.RoleModel;

/**
 * Role Model Test classes
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 * 
 */
public class RoleModeTest {

	/**
	 * Msodel object to test
	 */

	public static RoleModel model = new RoleModel();
	public static RoleBean bean = new RoleBean();

	public static void main(String[] args) throws Exception {
		testList();
		//testSearch();
		//testUpdate();
		//testDelete();
		 //testAdd();
		// testFindByPK();
		// testFindByName();
		//System.out.println("record is inserted");
		//System.out.println("one record is deleted");
		//System.out.println("one record update");

	}

	/**
	 * Tests add a Role
	 * 
	 * @throws ParseException
	 * @throws DuplicateRecordException 
	 * @throws SQLException 
	 */
	public static void testAdd() throws ParseException, java.text.ParseException {
		try {
			RoleBean bean = new RoleBean();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
			Date dd = new Date();
			Date d = sdf.parse("02/06/1991");
			Timestamp ts = new Timestamp(dd.getTime());
			bean.setName("sorav");
			bean.setDescription("shrma");
			bean.setCreatedBy("ck");
			bean.setModifiedBy("post");
			bean.setCreateDatetime(ts);
			bean.setModifiedDatetime(ts);
			long pk = model.add(bean);
			RoleBean addedbean = model.findByPK(pk);
			if (addedbean == null) {
				System.out.println("test is fail");
			}

		} catch (ApplicationException e) {
			e.printStackTrace();
		} catch (DuplicateRecordException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Tests find a User by PK.
	 */
	public static void testFindByPK() {
		try {
			RoleBean bean = new RoleBean();
			long pk = 2L;
			bean = model.findByPK(pk);
			if (bean == null) {
				System.out.println("Test Find By PK fail");
			}
			System.out.println(bean.getId());
			System.out.println(bean.getName());
			System.out.println(bean.getDescription());
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Tests find a User by FindByName.
	 */
	public static void testFindByName() {
		try {
			RoleBean bean = new RoleBean();
			bean = model.findByName("danish");
			if (bean == null) {
				System.out.println("Test Find By PK fail");
			}
			System.out.println(bean.getId());
			System.out.println(bean.getName());
			System.out.println(bean.getDescription());
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Tests delete a Role
	 */
	public static void testDelete() {

		try {
			RoleBean bean = new RoleBean();
			long pk = 2L;
			bean.setId(pk);
			model.delete(bean);
			RoleBean deletedbean = model.findByPK(pk);
			if (deletedbean != null) {
				System.out.println("Test Delete fail");
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Tests update a Role
	 */
	public static void testUpdate() throws java.text.ParseException {
		  SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
	   try {
		   Date dd = new Date();
	   Date d = sdf.parse("07/03/1992");
			Timestamp ts = new Timestamp(dd.getTime());
	        RoleBean bean = model.findByPK(1l);
	        bean.setName("sachin");
	        bean.setDescription("tendulkar");
	        bean.setCreatedBy("achrekar");
	     bean.setModifiedBy("bcci");
	     bean.setCreateDatetime(ts);
	     bean.setModifiedDatetime(ts);
	     model.update(bean);
	    // RoleBean updatedbean = model.findByPK(6L);
        // if (!"12".equals(updatedbean.getName())) {
           //  System.out.println("Test Update fail");
         //}
     } catch (ApplicationException e) {
         e.printStackTrace();
     } catch (DuplicateRecordException e) {
         e.printStackTrace();
     }
 }
	/**
	 * Tests get Search
	 */
	 public static void testSearch() {

	        try {
	            RoleBean bean = new RoleBean();
	            List list = new ArrayList();
	            bean.setName("sachin");
	            list = model.search(bean, 0, 0);
	            if (list.size() < 0) {
	                System.out.println("Test Serach fail");
	            }
	            Iterator it = list.iterator();
	            while (it.hasNext()) {
	                bean = (RoleBean) it.next();
	                System.out.println(bean.getId());
	                System.out.println(bean.getName());
	                System.out.println(bean.getDescription());
	                System.out.println(bean.getCreateDatetime());
	                System.out.println(bean.getModifiedDatetime());
	            }

	        } catch (ApplicationException e) {
	            e.printStackTrace();
	        }

	    }
	 /**
		 * Tests get List Search
		 */
	 public static void testList(){
		 try {
	            RoleBean bean = new RoleBean();
	            List list = new ArrayList();
	            list = model.list(1, 10);
	            if (list.size() < 0) {
	                System.out.println("Test list fail");
	            }
	            Iterator it = list.iterator();
	            while (it.hasNext()) {
	                bean = (RoleBean) it.next();
	                System.out.println(bean.getId());
	                System.out.println(bean.getName());
	                System.out.println(bean.getDescription());
	            }

	        } catch (ApplicationException e) {
	            e.printStackTrace();
	        }
	    }
	
		 
	 }

	        
