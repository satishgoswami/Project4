package in.co.sunrays.proj4.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import in.co.sunrays.proj4.bean.SubjectBean;
import in.co.sunrays.proj4.model.Subjectmodel;

public class SubjectModelTest {
	public static Subjectmodel model = new Subjectmodel();

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		//addTest();
		// testUpdate();
		//testDelete();
		// testfindByName();
		// testfindByPk();
		//testSearch();
		testList();
	}
	public static void addTest() throws Exception {

		SubjectBean bean = new SubjectBean();
bean.setCourseId(18);
		bean.setCourseName("BSc");
		bean.setSubjectName("bechlore of science");
		bean.setDescription("3 years");

		model.add(bean);
}
	public static void testUpdate() throws Exception {

		SubjectBean bean = new SubjectBean();

		bean.setId(2);
		bean.setCourseName("MCOM");
		bean.setSubjectName("Finance & account");
		bean.setDescription("for Finance data");

		model.update(bean);
	}
	public static void testDelete() throws Exception {

		SubjectBean bean = new SubjectBean();

		bean.setId(2);

		model.delete(bean);
	}
	public static void testfindByName() throws Exception {

		SubjectBean bean = new SubjectBean();

		bean = model.findByName("bechlore of science");

		System.out.println(bean.getId());
		System.out.println(bean.getCourseName());
	}
	public static void testfindByPk() throws Exception {

		SubjectBean bean = new SubjectBean();

		bean = model.findByPk(1);

		System.out.println(bean.getId());
		System.out.println(bean.getCourseName());
	}
	public static void testSearch() throws Exception {

		SubjectBean bean = new SubjectBean();

		List list = new ArrayList();

		list = model.search(bean, 0, 0);

		Iterator<SubjectBean> it = list.iterator();

		while (it.hasNext()) {
			bean = it.next();
			System.out.println(bean.getId());
			System.out.println(bean.getCourseName());
			System.out.println(bean.getSubjectName());
		}
	}
public static void testList() throws Exception{
		
		SubjectBean bean = new SubjectBean();
		
		List<SubjectBean> list = new ArrayList<SubjectBean>();
		
		 list=model.list(0,0);
		 
		 Iterator it = list.iterator();
		 
		 while(it.hasNext()){
			      bean = (SubjectBean)it.next();
	 		   System.out.println(bean.getId());
				System.out.println(bean.getCourseName());
				System.out.println(bean.getDescription());
			 }
	}

}
	
	
