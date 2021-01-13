package in.co.sunrays.proj4.controller;
/**
 * * Timetable functionality Controller. Performs operation for add, update and get
 * Timetable
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.sunrays.proj4.bean.BaseBean;
import in.co.sunrays.proj4.bean.TimetableBean;
import in.co.sunrays.proj4.model.CourseModel;
import in.co.sunrays.proj4.model.Subjectmodel;
import in.co.sunrays.proj4.model.Timetablemodel;
import in.co.sunrays.proj4.util.DataUtility;
import in.co.sunrays.proj4.util.DataValidator;
import in.co.sunrays.proj4.util.PropertyReader;
import in.co.sunrays.proj4.util.ServletUtility;

@WebServlet(name = "TimetableCtl", urlPatterns = { "/ctl/TimetableCtl" })
public class TimetableCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
	private static Logger log=Logger.getLogger(TimetableCtl.class);

	protected void preload(HttpServletRequest request) {

		System.out.println("in preload");

		CourseModel cmodel = new CourseModel();
		try {
			List list1 = cmodel.list();
			request.setAttribute("courseList", list1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Subjectmodel smodel = new Subjectmodel();
		try {
			List list2 = smodel.list();
			request.setAttribute("subjectList", list2);
		} catch (Exception e) {
			// TODO Auto-generated catch blockR
			e.printStackTrace();
		}

	}

	protected boolean validate(HttpServletRequest request) {
		
		boolean pass=true;
		
		if(DataValidator.isNull(request.getParameter("courseid"))){
			request.setAttribute("courseName1",PropertyReader.getValue("error.require","Course Name"));
		   pass= false;
		}
		if(DataValidator.isNull(request.getParameter("subjectid"))){
			request.setAttribute("subjectName1",PropertyReader.getValue("error.require","Subject Name"));
		   pass= false;
		}
		if(DataValidator.isNull(request.getParameter("sem"))){
			request.setAttribute("sem1",PropertyReader.getValue("error.require","Semester"));
		   pass= false;
		}
		
		if(DataValidator.isNull(request.getParameter("examtime"))){
			request.setAttribute("examtime1",PropertyReader.getValue("error.require","Examtime"));
		   pass= false;
		}
		if(DataValidator.isNull(request.getParameter("examdate"))){
			request.setAttribute("examdate1",PropertyReader.getValue("error.require","Examdate"));
		   pass= false;
		}
		System.out.println("VALUE OF PASS IS"+pass);
		return pass;
	}
	/* (non-Javadoc)
	 * @see in.co.controller.BaseCtl#populateBean(javax.servlet.http.HttpServletRequest)
	 */
	protected BaseBean populateBean(HttpServletRequest request) {

		System.out.println("in populateBean");
		
		TimetableBean bean = new TimetableBean();
		
		bean.setId(DataUtility.getInt(request.getParameter("id")));
		
		bean.setCourseId(DataUtility.getInt(request.getParameter("courseid")));
		
		
		//bean.setCourseName(DataUtility.getString(request.getParameter("courseName")));
		
		System.out.println("Course name is"+request.getParameter("courseid"));
		bean.setSubjectId(DataUtility.getInt(request.getParameter("subjectid")));
		//bean.setSubjectName(DataUtility.getString(request.getParameter("subjectName")));
		
		System.out.println("subject name is"+request.getParameter("courseid"));
		
		bean.setSemester(DataUtility.getString(request.getParameter("sem")));
		bean.setExamTime(DataUtility.getString(request.getParameter("examtime")));
		
		bean.setExamDate(DataUtility.getDate(request.getParameter("examdate")));
		populateDTO(bean, request);
		
		return bean;
	}
	

	/**
	 * Contains display logic
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("TimetableCtl doGet method Started");

		long id =DataUtility.getLong(request.getParameter("id"));
		
		Timetablemodel model = new Timetablemodel();
		TimetableBean bean;
		if(id>0){
			try {
				bean=model.findByPk(id);
				ServletUtility.setBean(bean, request);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		ServletUtility.forward(getView(), request, response);
		log.debug("TimetableCtl doGet method End");
	}

	/**
	 * Contains Submit logics
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("TimetableCtl doPost method Started");

		long id =DataUtility.getLong(request.getParameter("id"));
		String op =DataUtility.getString(request.getParameter("operation"));
		
		Timetablemodel model = new Timetablemodel();
		if(OP_SAVE.equalsIgnoreCase(op)){
			TimetableBean bean1 = (TimetableBean)populateBean(request);
			try {
				id= model.add(bean1);
				ServletUtility.setSuccessMessage("Timetable Added Successfullly", request);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				ServletUtility.setErrorMessage("Timetable Already Exist", request);
				ServletUtility.setBean(bean1, request);
			}
			
			ServletUtility.forward(getView(), request, response);
		}
		
		else if(op.equals("Update")){
			TimetableBean bean2 = (TimetableBean)populateBean(request);
			if(id>0){
				
				try {
					model.update(bean2);
					ServletUtility.setSuccessMessage("Timetable Updated Successfullly", request);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					ServletUtility.setErrorMessage("Timetable Already Exist", request);
				}
			}
			ServletUtility.setBean(bean2, request);
			ServletUtility.forward(getView(), request, response);
		}
		
		else if(op.equals("Reset")){
			ServletUtility.redirect(ORSView.TIMETABLE_CTL, request, response);
			return;
		}
		else if(OP_CANCEL.equalsIgnoreCase(op)){
			ServletUtility.redirect(ORSView.TIMETABLE_LIST_CTL, request, response);
		    return;
		}
		log.debug("TimetableCtl doPost method End");
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.TIMETABLE_VIEW;
	}

}


