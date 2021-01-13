package in.co.sunrays.proj4.test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.sunrays.proj4.bean.StudentBean;
import in.co.sunrays.proj4.bean.UserBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.exception.DuplicateRecordException;
import in.co.sunrays.proj4.model.StudentModel;

/**
 * Student Model Test classes
 *
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 *
 */
public class StudentModelTest {
	/**
	 * Model object to test
	 */
	public static StudentModel model = new StudentModel();

	/**
	 * Main method to call test methods.
	 *
	 * @param args
	 * @throws ParseException
	 */
	public static void main(String[] args) throws Exception {
		//testList();
		//testSearch();
		//testFindByLogin();
		//testFindByPk();
		//testupdate();
		// testDelete();
		 testAdd();
		// System.out.println("record will inserted");
		// System.out.println("one record will delete");
		//System.out.println("data will update");
		//System.out.println("get it findbypk");
		//System.out.println("get findbylogin");
		//System.out.println("get search list");
		//System.out.println("get find list");

	}
	/**
	 * Tests add a Student
	 *
	 * @throws ParseException
	 * @throws DuplicateRecordException
	 * @throws ApplicationException
	 */
	public static void testAdd() throws DuplicateRecordException, ApplicationException, ParseException {

		StudentBean bean = new StudentBean();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
		Date dd = new Date();
		Date d = sdf.parse("03/04/1992");
		Timestamp ts = new Timestamp(dd.getTime());
		// bean.setId(1l);
		bean.setCollegeId(3l);
		//bean.setCollegeName("");
		bean.setFirstName("bhanu");
		bean.setLastName("Ahmed");
		//bean.setAddress("indore");
		bean.setDob(d);
		bean.setAddress("indore");
		bean.setMobileNo("9482552200");
		bean.setEmail("satish@");
		bean.setCreatedBy("Admin");
		bean.setModifiedBy("User");
		bean.setCreateDatetime(ts);
		bean.setModifiedDatetime(ts);

		long c = StudentModel.add(bean);
		System.out.println(c);
	}

	/**
	 * Tests delete a Student
	 */
	public static void testDelete() {

		try {
			StudentBean bean = new StudentBean();
			long pk = 1L;
			bean.setId(pk);
			model.delete(bean);

		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Tests update a Student
	 * @throws ApplicationException 
	 * @throws ParseException 
	 * @throws DuplicateRecordException 
	 */
	public static void testupdate() throws ParseException {
		StudentBean bean = new StudentBean();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
		Date dd = new Date();
		Date d = sdf.parse("03/05/1990");
		Timestamp ts = new Timestamp(dd.getTime());
		try {
			bean.setId(4l);
			bean.setCollegeId(3l);
			bean.setCollegeName("Achropolish");
			bean.setFirstName("Danish");
			bean.setLastName("ahemad");
			bean.setDob(d);
			bean.setMobileNo("9755121216");
			bean.setEmail("Danish@gmail.com");
			bean.setCreatedBy("rahul");
			bean.setModifiedBy("ramesh");
			bean.setCreateDatetime(ts);
			bean.setModifiedDatetime(ts);
			model.update(bean);
		} catch (ApplicationException e) {
			e.printStackTrace();
		} catch (DuplicateRecordException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Tests find a Student by PK.
	 */
	public static void testFindByPk(){
		long pk = 4l;
		 StudentBean bean=new StudentBean();
		try {

			bean = model.findByPK(pk);

			if (bean == null) {
				System.out.println("Test Find By PK fail");
			}
			System.out.println(bean.getFirstName());
			System.out.println(bean.getLastName());

		} catch (ApplicationException e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * Tests find a Student by Emailid.
	 */
	public static void testFindByEmailId() {
		try {
			StudentBean sbean = new StudentBean();
			sbean = StudentModel.findByEmailId("hm@gmail.com");
			
			if (sbean != null) {
				System.out.println("Test Find By EmailId fail");
			}
			System.out.println(sbean.getId());
			System.out.println(sbean.getFirstName());
			System.out.println(sbean.getLastName());
			System.out.println(sbean.getDob());
			System.out.println(sbean.getMobileNo());
			System.out.println(sbean.getEmail());
			System.out.println(sbean.getCollegeId());
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Tests get Search
	 */
	public static void testSearch() {
		try{
		StudentBean bean=new StudentBean();
		List list = new ArrayList();
		bean.setFirstName("Danish");
		list = model.search(bean, 0, 0);
		if (list.size() < 0) {
			System.out.println("Test Serach fail");
		}
		Iterator it = list.iterator();
		while (it.hasNext()) {
			bean=(StudentBean)it.next();
			System.out.println(bean.getId());
			System.out.println(bean.getFirstName());
			System.out.println(bean.getLastName());
			System.out.println(bean.getEmail());
			System.out.println(bean.getMobileNo());
		}

	

} catch (ApplicationException e) {
	e.printStackTrace();
}
	}

	/**
	 * Tests get List.
	 */
	public static void testList(){
		try {
			StudentBean bean=new StudentBean();
			List list = new ArrayList();
			list = model.list(1, 10);
			if (list.size() < 0) {
				System.out.println("Test list fail");
			}
			Iterator it = list.listIterator();
			bean = (StudentBean) it.next();
			System.out.println(bean.getId());
			System.out.println(bean.getFirstName());
			System.out.println(bean.getLastName());
			System.out.println(bean.getMobileNo());
			System.out.println(bean.getEmail());
		
	}
	catch (ApplicationException e) {
		e.printStackTrace();
	}
}}
