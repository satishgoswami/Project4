package in.co.sunrays.proj4.test;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.sunrays.proj4.bean.CollegeBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.exception.DuplicateRecordException;
import in.co.sunrays.proj4.model.CollegeModel;
import in.co.sunrays.proj4.model.MarksheetModel;

/**
 * College Model Test classes
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 * 
 */
public class CollegeModelTest {
	public static CollegeModel model = new CollegeModel();

	public static void main(String[] args) throws Exception {
		
		//testList();
		//testsearchBean();
		//testsearch();
		//testfindByPk();
		//testFindbyName();
		//testUpdate();
		//testDelete();
		//testAdd();
		//System.out.println("record will inserted");
		//System.out.println("one recor will deleted");
		//System.out.println("record updated");
		System.out.println("find by name");

	}
	/**
	 * Tests add a College
	 * 
	 * @throws DuplicateRecordException
	 */
	public static void testAdd() throws DuplicateRecordException, ParseException {

		CollegeBean bean = new CollegeBean();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
		Date dd = new Date();
		Date d = sdf.parse("03/05/1992");
		Timestamp ts = new Timestamp(dd.getTime());
		bean.setName("iist");
		bean.setAddress("behind iim");
		bean.setState("mp");
		bean.setCity("indore");
		bean.setPhoneNo("94142258");
		bean.setModifiedBy("rihan");
		bean.setCreatedBy("akhay");
		bean.setCreateDatetime(ts);
		bean.setModifiedDatetime(ts);
		try {
			model.add(bean);
		} catch (DuplicateRecordException e) {

			e.printStackTrace();
		} catch (ApplicationException e) {

			e.printStackTrace();
		}

	}
	/**
	 * Tests delete a College
	 */
	public static void testDelete() {
		CollegeBean bean = new CollegeBean();
		bean.setId(2l);
		try {
			model.delete(bean);
		} catch (ApplicationException e) {

			e.printStackTrace();
		}

	}
	/**
	 * Tests update a College
	 */
	public static void testUpdate() throws ParseException{
		CollegeBean bean=new CollegeBean();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
		Date dd = new Date();
		Date d = sdf.parse("03/05/1996");
		Timestamp ts = new Timestamp(dd.getTime());
		try {
			bean.setId(3L);
			bean.setName("vashnav");
			bean.setAddress("dewas road");
			bean.setState("indore");
			bean.setCity("indore");
			bean.setPhoneNo("95325829");
			bean.setModifiedBy("salman");
			bean.setCreatedBy("sohil");
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
	 * Tests find a College by Name.
	 */
		public static void testFindbyName() throws ApplicationException{
			CollegeBean bean=new CollegeBean();
			bean=CollegeModel.findByName("sims");
			System.out.println(bean.getAddress());
			System.out.println(bean.getState());
		}
		public static void testfindByPk() throws SQLException {
			CollegeBean bean;
			try {
				bean = CollegeModel.findByPK(3L);
				System.out.println(bean.getAddress());
				System.out.println(bean.getName());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}
		/**
		 * Tests search a College by Name
		 */

		public static void testsearch() throws Exception {
			CollegeBean bean = new CollegeBean();
			bean.setAddress("dewas road");
			List<CollegeBean> list= new ArrayList<CollegeBean>();
			list=CollegeModel.search(bean, 1, 10);
			
			Iterator it= list.iterator();
			while(it.hasNext()){
			bean=(CollegeBean) it.next();
			System.out.println(bean.getName());
			System.out.println(bean.getAddress());
			System.out.println(bean.getCity());
			}
}
		public static void testsearchBean() throws Exception {
			CollegeBean bean= new CollegeBean();
			List<CollegeBean> list= new ArrayList<CollegeBean>();
			list=CollegeModel.search(bean);
			Iterator it =list .listIterator();
			while(it.hasNext()){
				bean=(CollegeBean) it.next();
				System.out.println(bean.getName());
				
			}
		}
		public static void testList() throws Exception
		 {
				CollegeBean bean =new  CollegeBean();
				List<CollegeBean> list = new ArrayList<CollegeBean>();
				
						list=CollegeModel.list(1, 2);
						Iterator it = list.iterator();
						while(it.hasNext()){
							bean=(CollegeBean) it.next();
							System.out.println(bean.getName());
							System.out.println(bean.getModifiedBy());
							
						}
				
		}}

