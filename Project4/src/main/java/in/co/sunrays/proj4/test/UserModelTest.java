package in.co.sunrays.proj4.test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.sunrays.proj4.bean.UserBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.exception.DuplicateRecordException;
import in.co.sunrays.proj4.exception.RecordNotFoundException;
import in.co.sunrays.proj4.model.UserModel;

public class UserModelTest {
	public static UserModel model = new UserModel();
	///public static UserBean bean = new UserBean();

	public static void main(String[] args) throws Exception {
		//testforgetPassword();
		
		//testchangePassword();
		//testRegisterUser();
		//testAuthenticate();
		// testList();
		// testGetRole();
		// testSearch();
		// testUpdate();
		// testFindByLogin();
		// testFindByPk();
		// testDelete();
		testAdd();
		// System.out.println("data inserted");
		// System.out.println("delete one record");
		//System.out.println("Hello");
		//System.out.println("register successfully");
	}

	public static void testAdd() throws Exception {

		 UserBean bean = new UserBean();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
		Date dd = new Date();
		Date d = sdf.parse("03/05/1992");
		Timestamp ts = new Timestamp(dd.getTime());
		bean.setFirstName("anmol");
		bean.setLastName("sharma");
		bean.setLogin("satendra@gmail.com.com");
		bean.setPassword("11111");
		bean.setAddress("indore");
		bean.setDob(d);
		bean.setMobileNo("942514585");
		bean.setRoleId(8);
		bean.setUnSuccessfulLogin(9);
		bean.setGender("male");
		bean.setLastLogin(new Timestamp(new Date().getTime()));
		bean.setLock("g");
		bean.setRegisteredIP("192.276.000");
		bean.setLastLoginIP("192.278.101");
		// bean.setCreatedBy();

		try {
			model.add(bean);
		} catch (DuplicateRecordException e) {

			e.printStackTrace();
		} catch (ApplicationException e) {

			e.printStackTrace();
		}
	}

	public static void testDelete() {
		UserBean bean = new UserBean();

		bean.setId(17l);
		try {
			model.delete(bean);
		} catch (ApplicationException e) {

			e.printStackTrace();
		}
	}

	public static void testFindByPk() {
		long pk = 10;
		 UserBean bean = new UserBean();
		try {

			bean = model.findByPK(pk);

			if (bean == null) {
				System.out.println("Test Find By PK fail");
			}
			else{
				
			System.out.println(bean.getId()+"       "+bean.getFirstName());
			System.out.println(bean.getPassword()+ "  CF "+bean.getConfirmPassword());
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	public static void testFindByLogin() {
		UserBean bean = new UserBean();
		try {
			bean = model.findByLogin("ravi1234");
			if (bean == null) {
				System.out.println("Test Find By PK fail");
			}

			System.out.println(bean.getFirstName());

		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	public static void testUpdate() throws ParseException {
		 UserBean bean = new UserBean();
		try {
			bean= model.findByPK(4l);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
		
		Date d = new Date();
	   bean.setId(9);
	   bean.setFirstName("Deepak");
	   bean.setLastName("ughade");
	   bean.setLogin("ramkumar.com");
	   bean.setPassword("987");
	   bean.setDob(sdf.parse("10/11/1999"));
	   
	   try {
		model.update(bean);
	} catch (ApplicationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (DuplicateRecordException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	public static void testSearch() {
		try {
			UserBean bean = new UserBean();
			List list = new ArrayList();
			bean.setFirstName("rahul");
			list = model.search(bean, 0, 0);
			if (list.size() < 0) {
				System.out.println("Test Serach fail");
			}
			Iterator it = list.iterator();
			while (it.hasNext()) {
				bean = (UserBean) it.next();
				System.out.println(bean.getId());
				System.out.println(bean.getFirstName());
				System.out.println(bean.getLastName());
				System.out.println(bean.getLogin());
				System.out.println(bean.getPassword());
				System.out.println(bean.getDob());
				System.out.println(bean.getRoleId());
				System.out.println(bean.getUnSuccessfulLogin());
				System.out.println(bean.getGender());
				System.out.println(bean.getLastLogin());
				System.out.println(bean.getMobileNo());

			}

		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	public static void testGetRole() throws RecordNotFoundException {
		List list = new ArrayList();
		UserBean bean = new UserBean();
		bean.setRoleId(2L);
		list = model.getRoles(bean);
		System.out.println(list.size());
		if (list.size() < 0) {
			System.out.println("test get role fail");
		}
		Iterator it = list.iterator();
		while (it.hasNext()) {
			bean = (UserBean) it.next();
			System.out.println(bean.getId());
			System.out.println(bean.getFirstName());
			System.out.println(bean.getLastName());
			System.out.println(bean.getLogin());
			System.out.println(bean.getPassword());
			System.out.println(bean.getDob());
			System.out.println(bean.getRoleId());
			System.out.println(bean.getUnSuccessfulLogin());
			System.out.println(bean.getGender());
			System.out.println(bean.getLastLogin());
			System.out.println(bean.getMobileNo());
			System.out.println(bean.getLock());
			System.out.println(bean.getRegisteredIP());
			System.out.println(bean.getLastLoginIP());

		}
	}

	public static void testList() {

		try {
			UserBean bean = new UserBean();
			List list = new ArrayList();
			list = model.list(1, 10);
			if (list.size() < 0) {
				System.out.println("Test list fail");
			}
			Iterator it = list.listIterator();
			bean = (UserBean) it.next();
			System.out.println(bean.getId());
			System.out.println(bean.getFirstName());
			System.out.println(bean.getLastName());
			System.out.println(bean.getLogin());
			System.out.println(bean.getPassword());
			System.out.println(bean.getDob());
			System.out.println(bean.getRoleId());
			System.out.println(bean.getUnSuccessfulLogin());
			System.out.println(bean.getGender());
			System.out.println(bean.getLastLogin());
			System.out.println(bean.getMobileNo());
			System.out.println(bean.getLock());
			System.out.println(bean.getRegisteredIP());
			System.out.println(bean.getLastLoginIP());

		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	public static void testAuthenticate() {

		try {
			UserBean bean = new UserBean();
			bean.setLogin("deep@gmail.com");
			bean.setPassword("987");
			bean = model.authenticate(bean.getLogin(), bean.getPassword());
			if (bean != null) {
				System.out.println("Successfully login");

			} else {
				System.out.println("Invalid login Id & password");
			}

		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}

	 public static void testRegisterUser(){
		   UserBean bean = new UserBean();
		   try{
			   
			   SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			   
		   bean.setFirstName("ravi");
		   bean.setLastName("Goswami");
		   bean.setLogin("rathoreravi550@gmail.com");
	       bean.setPassword("1234 ");
	       bean.setConfirmPassword("1234");
	       bean.setDob(sdf.parse("11/20/2015"));
	       bean.setGender("Male");
	       bean.setRoleId(2);;
	       long pk = model.registerUser(bean);
	       System.out.println("Successfully register");
		   }
		   catch(Exception e){
			   e.printStackTrace();
		   }

}
	 public static void testchangePassword(){
		   try{
			   UserBean bean = model.findByLogin("satishgoswami0731@gmail.com");
			   
			      
			   
			       model.changePassword(10L,"Satish@1234","Satish@123412");
			       System.out.println("password has been change successfully");
		   }
		   catch(Exception e){
			   e.printStackTrace();
		   } 
	 }
	
	 public static void testforgetPassword(){
		   
		   try{
			   model.forgetPassword("satishgoswami0731@gmail.com");
			   System.out.println("forget password successfully work");
		   }
		   catch(Exception e){
			   e.printStackTrace();
		   }
	   }
	 }
