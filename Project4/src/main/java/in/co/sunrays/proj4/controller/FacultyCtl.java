package in.co.sunrays.proj4.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.sunrays.proj4.bean.BaseBean;
import in.co.sunrays.proj4.bean.FacultyBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.model.CollegeModel;
import in.co.sunrays.proj4.model.CourseModel;
import in.co.sunrays.proj4.model.FacultyModel;
import in.co.sunrays.proj4.model.Subjectmodel;
import in.co.sunrays.proj4.util.DataUtility;
import in.co.sunrays.proj4.util.DataValidator;
import in.co.sunrays.proj4.util.PropertyReader;
import in.co.sunrays.proj4.util.ServletUtility;

@WebServlet(name = "FacultyCtl", urlPatterns = { "/ctl/FacultyCtl" })
public class FacultyCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
private static Logger log=Logger.getLogger(FacultyCtl.class);

@Override
	protected void preload(HttpServletRequest request) {

		CollegeModel model1 = new CollegeModel();

		try {
			List list1 = model1.list();
			request.setAttribute("collegeList", list1);
		} catch (ApplicationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		CourseModel model2 = new CourseModel();

		try {
			List list2 = model2.list();
			request.setAttribute("courseList", list2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Subjectmodel model3 = new Subjectmodel();
           List list3= new ArrayList();
		try {
			list3 = model3.list();
			request.setAttribute("subjectList", list3);
					
			/*Iterator it = list3.iterator();
			SubjectBean sbean=null;
			while(it.hasNext()){
				sbean= (SubjectBean)it.next();
				
				System.out.println(sbean.getId()+" "+sbean.getCourseName()+" "+sbean.getSubjectName());
			}*/
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}


	protected boolean validate(HttpServletRequest request){
		
		System.out.println("inside validation");
		
		boolean pass= true;
		
		if(DataValidator.isNull(request.getParameter("fname"))){
			request.setAttribute("fname1",PropertyReader.getValue("error.require","First Name"));
		   pass=false;
		}
		else if(!DataValidator.isName1(request.getParameter("fname"))){
			request.setAttribute("fname1","Invalid First Name");
			pass= false;
		}
		
		if(DataValidator.isNull(request.getParameter("lname"))){
			request.setAttribute("lname1",PropertyReader.getValue("error.require","Last Name"));
		   pass=false;
		}
		else if(!DataValidator.isName1(request.getParameter("lname"))){
			request.setAttribute("lname1","Invalid Last Name");
			pass= false;
		}
		if(DataValidator.isNull(request.getParameter("gender"))){
			request.setAttribute("gender1",PropertyReader.getValue("error.require","Gender"));
		   pass=false;
		}
		if(DataValidator.isNull(request.getParameter("login"))){
			request.setAttribute("login1",PropertyReader.getValue("error.require","Emailid"));
		   pass=false;
		}
		else if(!DataValidator.isEmail(request.getParameter("login"))){
			request.setAttribute("login1","Invalid Emailid");
			pass= false;
		}
		
		if(DataValidator.isNull(request.getParameter("address"))){
			request.setAttribute("address1",PropertyReader.getValue("error.require","Address"));
		   pass=false;
		}
		else if(!DataValidator.isName(request.getParameter("address"))){
			request.setAttribute("address1","Invalid Address");
			pass= false;
		}
		
		if(DataValidator.isNull(request.getParameter("jod"))){
			request.setAttribute("jod",PropertyReader.getValue("error.require","Date Of Joining"));
		   pass=false;
		}
		if(DataValidator.isNull(request.getParameter("qual"))){
			request.setAttribute("qual1",PropertyReader.getValue("error.require","Qualification"));
		   pass=false;
		}
		if(DataValidator.isNull(request.getParameter("mobile"))){
			request.setAttribute("mobile1",PropertyReader.getValue("error.require","Mobile No"));
		   pass=false;
		}
		else if(!DataValidator.isMobileNo(request.getParameter("mobile"))){
			request.setAttribute("mobile1","Invalid Mobile No");
			pass= false;
		}
		if(DataValidator.isNull(request.getParameter("collegeid"))){
			request.setAttribute("collegeName1",PropertyReader.getValue("error.require","College Name"));
		   pass=false;
		}
		if(DataValidator.isNull(request.getParameter("courseid"))){
			request.setAttribute("courseName1",PropertyReader.getValue("error.require","Course Name"));
		   pass=false;
		}
		if(DataValidator.isNull(request.getParameter("subjectid"))){
			request.setAttribute("subjectName1",PropertyReader.getValue("error.require","Subject Name"));
		   pass=false;
		}
		System.out.println("pass="+pass);
		return pass;
	}
	
	protected BaseBean populateBean(HttpServletRequest request) {

		FacultyBean bean = new FacultyBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setFirstName(DataUtility.getString(request.getParameter("fname")));
		bean.setLastName(DataUtility.getString(request.getParameter("lname")));
		bean.setGender(DataUtility.getString(request.getParameter("gender")));
		bean.setLoginId(DataUtility.getString(request.getParameter("login")));
		bean.setAddress(DataUtility.getString(request.getParameter("address")));
		bean.setDateOfJoining(DataUtility.getDate(request.getParameter("jod")));
		bean.setQualification(DataUtility.getString(request.getParameter("qual")));
		bean.setMobileNo(DataUtility.getString(request.getParameter("mobile")));
		bean.setCollegeId(DataUtility.getInt(request.getParameter("collegeid")));
//		bean.setCollegeName(DataUtility.getString(request.getParameter("collegeName")));
		bean.setCourseId(DataUtility.getInt(request.getParameter("courseid")));
	//	bean.setCourseName(DataUtility.getString(request.getParameter("courseName")));
		bean.setSubjectId(DataUtility.getInt(request.getParameter("subjectid")));
            System.out.println(" subject id is"+request.getParameter("subjectid"));		
            System.out.println(" subject name is"+request.getParameter("subjectName"));
         //   bean.setSubjectName(DataUtility.getString(request.getParameter("subjectName")));
		populateDTO(bean, request);

		return bean;
	}
	/* 
	 * Contains view logic
	 * */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("FacultyCtl doGet Meyhod Started");

		System.out.println("inside do get");

		long id = DataUtility.getLong(request.getParameter("id"));
		String op = DataUtility.getString(request.getParameter("operation"));

		FacultyModel model = new FacultyModel();
		FacultyBean bean = null;
		if (id > 0) {

			try {
				bean = model.findByPk(id);
				ServletUtility.setBean(bean, request);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		ServletUtility.forward(getView(), request, response);
		log.debug("FacultyCtl doGet Method End");
	}
	/* 
	 * Contains submit logic
	 * */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("FacultyCtl doPost Method Started");

		System.out.println("inside do get");

		long id = DataUtility.getLong(request.getParameter("id"));
		String op = DataUtility.getString(request.getParameter("operation"));

		FacultyModel model = new FacultyModel();
		FacultyBean bean = null;
		
		if(OP_SAVE.equalsIgnoreCase(op)){
			bean = (FacultyBean)populateBean(request);
			try {
				model.add(bean);
				ServletUtility.setSuccessMessage("Faculty Added Successfullly", request);
				//ServletUtility.forward(getView(), request, response);		
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				ServletUtility.setErrorMessage("Faculty Already Exist", request);
				ServletUtility.setBean(bean, request);
			}
			
			ServletUtility.forward(getView(), request, response);
		}
		
		else if(op.equals("Update")){
			
			FacultyBean bean1 = (FacultyBean)populateBean(request);
			
			try {
				model.update(bean1);
				ServletUtility.setBean(bean1, request);
				ServletUtility.setSuccessMessage("Faculty Updated Successfullly", request);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			ServletUtility.forward(getView(), request, response);
			
		}
	
		else if(op.equals("Reset")){
			ServletUtility.redirect(ORSView.FACULTY_CTL, request, response);
			return;
		}
		else if(OP_CANCEL.equalsIgnoreCase(op)){
			ServletUtility.redirect(ORSView.FACULTY_LIST_CTL, request, response);
			log.debug("FacultyCtl doPost Method End");
		    return;
		}
		
		
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.FACULTY_VIEW;
	}
}
