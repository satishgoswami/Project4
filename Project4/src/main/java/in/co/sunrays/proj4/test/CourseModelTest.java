package in.co.sunrays.proj4.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import in.co.sunrays.proj4.bean.CourseBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.model.CourseModel;

public class CourseModelTest {

	public static CourseModel model = new CourseModel();

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		// testAdd();
		//testUpdate();
		 testDelete();
		// testFindByName();
		// testFindByPk();
		// testSearch();
		// testList();
		// System.out.println("record inserted");
		// System.out.println("one record delete");
		//System.out.println("data will update");
		// System.out.println("find by name");
	}

	public static void testAdd() throws Exception {

		CourseBean bean = new CourseBean();

		bean.setCourseName("BSc");
		bean.setDuration("3 years");
		bean.setDescription("bechlore of science");
		bean.setCreatedBy("satish");
		bean.setModifiedBy("satish");

		model.add(bean);
	}

	public static void testDelete() throws ApplicationException {
		CourseBean bean = new CourseBean();
		bean.setId(17l);
		model.delete(bean);

	}

	public static void testUpdate() throws Exception {

		CourseBean bean = new CourseBean();

		bean.setId(17l);
		bean.setCourseName("BTech1");
		bean.setDuration("4 years");
		bean.setDescription("Bachlor degree");
		bean.setCreatedBy("Ankit");
		bean.setModifiedBy("Ankit");

		model.update(bean);
	}
	public static void testFindByName() throws ApplicationException{
		CourseBean bean=new CourseBean();
		bean=model.findByName("Btech1");
		System.out.println(bean.getId());
		System.out.println(bean.getDescription());
	}
	public static void testFindByPk() throws Exception {

		CourseBean bean = new CourseBean();

		bean = model.findByPK(17l);

		System.out.println(bean.getId());
		System.out.println(bean.getCourseName());
		System.out.println(bean.getDescription());
}
public static void testSearch() throws Exception{
		
		CourseBean bean = new CourseBean();
		
		List<CourseBean> list = new ArrayList<CourseBean>();
		
		 list=model.search(bean,0,0);
		 
		 Iterator<CourseBean> it = list.iterator();
		 
		 while(it.hasNext()){
			      bean = it.next();
	 		   System.out.println(bean.getId());
				System.out.println(bean.getCourseName());
				System.out.println(bean.getDescription());
				System.out.println(bean.getDuration());
			 
	}
		 }
public static void testList() throws Exception{
	
	CourseBean bean = new CourseBean();
	
	List<CourseBean> list = new ArrayList<CourseBean>();
	
	 list=model.list(0,0);
	 
	 Iterator<CourseBean> it = list.iterator();
	 
	 while(it.hasNext()){
		      bean = it.next();
 		   System.out.println(bean.getId());
			System.out.println(bean.getCourseName());
			System.out.println(bean.getDescription());
			System.out.println(bean.getDuration());
		    System.out.println(bean.getCreatedBy());
	 }
}
		 }

		
		