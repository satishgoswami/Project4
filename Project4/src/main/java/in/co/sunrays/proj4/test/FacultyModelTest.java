package in.co.sunrays.proj4.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.sunrays.proj4.bean.FacultyBean;
import in.co.sunrays.proj4.model.FacultyModel;

public class FacultyModelTest {
	public static FacultyModel model = new FacultyModel();

	public static void main(String[] args) throws Exception {
		//testList();
		//testSearch();
		//testfindByEmail();
		//testfindByPk();
		//testAdd();
		//testUpdate();
		//testDelete();
		//System.out.println("one record inserted");
		//System.out.println(" record will updated");
		//System.out.println("one record will deleted");
		
	}
	public static void testAdd() throws ParseException {
		FacultyBean bean = new FacultyBean();

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");

		Date d = new Date();
		
		bean.setFirstName("mahesh");
		bean.setLastName("jain");
		bean.setLoginId("mahesh@gmail.com");
		bean.setCollegeId(3);
		bean.setCourseId(18);
		bean.setSubjectId(3);
        bean.setDateOfJoining(sdf.parse("10/11/1999"));
		try {
			model.add(bean);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void testUpdate() throws Exception {
		FacultyBean bean = new FacultyBean();
	
		
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		
		bean.setId(1);
		bean.setFirstName("rahul");
		bean.setLastName("tiwari");
		bean.setLoginId("rahul@gmail.com");
		bean.setCollegeId(1);
		bean.setCourseId(17);
		bean.setSubjectId(1);

		bean.setQualification("PhD");
		bean.setMobileNo("987665444");
		bean.setDateOfJoining(sdf.parse("20-08-1988"));
		
		model.update(bean);
		
	}

	public static void testDelete() throws Exception {
		FacultyBean bean = new FacultyBean();

		bean.setId(2);
		
		model.delete(bean);
	}

	public static void testfindByPk() throws Exception {
		
		FacultyBean bean = new FacultyBean();
 
		bean= model.findByPk(1);
		
		System.out.println(bean.getFirstName());
		System.out.println(bean.getLastName());
		
	}
public static void testfindByEmail(){
		
		FacultyBean bean = new FacultyBean();
		
		try {
			bean= model.findByEmail("rahul@gmail.com");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(bean.getFirstName());
		System.out.println(bean.getLastName());
	}
public static void testSearch() throws Exception{
	
	List list = new ArrayList();
	
	FacultyBean bean=new FacultyBean();
	

	list = model.search(bean,0,0);
	
	bean.setId(1);
	Iterator<FacultyBean> it = list.iterator();
	while(it.hasNext()){
		bean= it.next();
		
		System.out.println(bean.getFirstName());
		System.out.println(bean.getLastName());
		System.out.println(bean.getGender());
		System.out.println(bean.getDateOfJoining());
		
	}
}
public static void testList() throws Exception{
	
	List list = new ArrayList();
	
	FacultyBean bean=new FacultyBean();
	

	list = model.list(0,0);
	
	bean.setId(1);
	Iterator<FacultyBean> it = list.iterator();
	while(it.hasNext()){
		bean= it.next();
		
		System.out.println(bean.getFirstName());
		System.out.println(bean.getLastName());
		System.out.println(bean.getGender());
		System.out.println(bean.getDateOfJoining());
		
	}
}
}

