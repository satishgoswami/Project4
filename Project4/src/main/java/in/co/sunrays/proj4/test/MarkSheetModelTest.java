package in.co.sunrays.proj4.test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.sunrays.proj4.bean.MarksheetBean;
import in.co.sunrays.proj4.bean.StudentBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.exception.DuplicateRecordException;
import in.co.sunrays.proj4.model.MarksheetModel;
/**
* Marksheet Model Test classes
*
* @author SunilOS
* @version 1.0
* @Copyright (c) SunilOS
*
*/
public class MarkSheetModelTest {

	/**
	 * Model object to test
	 */

	public static MarksheetModel model = new MarksheetModel();

	public static void main(String[] args) throws Exception {
	//	testList();
		testMeritlist();
		//testSearch();
		// testFindByPK();
		// testFindByRollNo();
		// testUpdate();
		// testDelete();
		// testAdd();
		// System.out.println("record will inserted");
		// System.out.println("record will delete");
		// System.out.println("record will updated");
		// System.out.println("get roll list");
		// System.out.println("find by pk");
		//System.out.println("find meritlist");
		//System.out.println("fund list");

	}

	/**
	 * Tests add a Marksheet
	 */
	public static void testAdd() throws ParseException {
		MarksheetBean bean = new MarksheetBean();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
		Date dd = new Date();
		Date d = sdf.parse("03/05/1992");
		Timestamp ts = new Timestamp(dd.getTime());

		bean.setRollNo("0837ec");
		bean.setStudentId(3L);
		bean.setName("manish");
		bean.setPhysics(75);
		bean.setChemistry(74);
		bean.setMaths(90);
		bean.setCreatedBy("rakesh");
		bean.setModifiedBy("mahesh");
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
	 * Tests delete a Marksheet
	 */
	public static void testDelete() {
		MarksheetBean bean = new MarksheetBean();
		bean.setId(2l);
		try {
			model.delete(bean);
		} catch (ApplicationException e) {

			e.printStackTrace();
		}
	}

	/**
	 * Tests update a Marksheet
	 */
	public static void testUpdate() throws ParseException {
		MarksheetBean bean = new MarksheetBean();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
		Date dd = new Date();
		Date d = sdf.parse("03/05/1996");
		Timestamp ts = new Timestamp(dd.getTime());
		try {
			bean.setId(1L);
			bean.setRollNo("0912cs");
			bean.setStudentId(2L);
			bean.setName("bhanu");
			bean.setPhysics(66);
			bean.setChemistry(50);
			bean.setMaths(85);
			bean.setCreatedBy("mohan");
			bean.setModifiedBy("sunil");
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
	 * Tests find a marksheet by Roll No.
	 */

	public static void testFindByRollNo() {
		// MarksheetBean bean = new MarksheetBean();

		try {
			MarksheetBean bean = model.findByRollNo("0912cs");
			if (bean == null) {
				System.out.println("Test Find By RollNo fail");
			}
			System.out.println(bean.getId());
			System.out.println(bean.getRollNo());
			System.out.println(bean.getName());
			System.out.println(bean.getPhysics());
			System.out.println(bean.getChemistry());
			System.out.println(bean.getMaths());
			System.out.println(bean.getModifiedBy());
			System.out.println(bean.getCreatedBy());
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Tests find a marksheet by PK.
	 */
	public static void testFindByPK() {
		try {
			MarksheetBean bean = new MarksheetBean();
			long pk = 1L;
			bean = model.findByPK(pk);
			if (bean == null) {
				System.out.println("Test Find By PK fail");
			}
			// System.out.println(bean.getId());
			System.out.println(bean.getRollNo());
			System.out.println(bean.getName());
			System.out.println(bean.getPhysics());
			System.out.println(bean.getChemistry());
			System.out.println(bean.getMaths());
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}
	/**
	 * Tests search a Marksheets
	 */
	public static void testSearch() {
		try {
			MarksheetBean bean = new MarksheetBean();
			List list = new ArrayList();
			bean.setRollNo("0912cs");
			list = model.search(bean, 1, 10);
			if (list.size() < 0) {
				System.out.println("Test Search fail");
			}
			Iterator it = list.iterator();
			while (it.hasNext()) {
				bean = (MarksheetBean) it.next();
				System.out.println(bean.getId());
				System.out.println(bean.getRollNo());
				System.out.println(bean.getName());
				System.out.println(bean.getPhysics());
				System.out.println(bean.getChemistry());
				System.out.println(bean.getMaths());
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}
	/**
	 * Tests get the meritlist of Marksheets
	 */
	public static void testMeritlist() {
		try {
			MarksheetBean bean = new MarksheetBean();
			List list = new ArrayList();
			list = model.getMeritList(0, 0);
			if (list.size() < 0) {
				System.out.println("Test List fail");
			}
			Iterator it = list.iterator();
			while (it.hasNext()) {
				bean = (MarksheetBean) it.next();
				System.out.println(bean.getId());
				System.out.println(bean.getRollNo());
				System.out.println(bean.getName());
				System.out.println(bean.getPhysics());
				System.out.println(bean.getChemistry());
				System.out.println(bean.getMaths());
				System.out.println(bean.getCreatedBy());
				System.out.println(bean.getCreateDatetime());
				System.out.println(bean.getModifiedBy());
				System.out.println(bean.getModifiedDatetime());
			
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Tests get the list of Marksheets
	 */
	public static void testList() {
		try {
			MarksheetBean bean = new MarksheetBean();
			List list = new ArrayList();
			list = model.list(1, 6);
			if (list.size() < 0) {
				System.out.println("Test List fail");
			}
			Iterator it = list.iterator();
			while (it.hasNext()) {
				bean = (MarksheetBean) it.next();
				System.out.println(bean.getId());
				System.out.println(bean.getRollNo());
				System.out.println(bean.getName());
				System.out.println(bean.getPhysics());
				System.out.println(bean.getChemistry());
				System.out.println(bean.getMaths());
				System.out.println(bean.getCreatedBy());
				System.out.println(bean.getCreateDatetime());
				System.out.println(bean.getModifiedBy());
				System.out.println(bean.getModifiedDatetime());
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
	}
}
