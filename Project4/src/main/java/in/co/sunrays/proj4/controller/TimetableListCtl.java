package in.co.sunrays.proj4.controller;
/**
 * Timetable List functionality Controller. Performs operation for list, search and
 * delete operations of Subject
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.sunrays.proj4.bean.BaseBean;
import in.co.sunrays.proj4.bean.TimetableBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.model.CourseModel;
import in.co.sunrays.proj4.model.Subjectmodel;
import in.co.sunrays.proj4.model.Timetablemodel;
import in.co.sunrays.proj4.util.DataUtility;
import in.co.sunrays.proj4.util.PropertyReader;
import in.co.sunrays.proj4.util.ServletUtility;

@WebServlet(name = "TimetableListCtl", urlPatterns = { "/ctl/TimetableListCtl" })
public class TimetableListCtl extends BaseCtl{
	private static final long serialVersionUID = 1L;
private static Logger log=Logger.getLogger(TimetableListCtl.class);
	protected void preload(HttpServletRequest request) {

		CourseModel comodel= new CourseModel();
		
		try {
			List list1= comodel.list();
			request.setAttribute("courseList",list1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Subjectmodel somodel = new Subjectmodel();
		
		try {
			List list2 = somodel.list();
			request.setAttribute("subjectList",list2);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected BaseBean populateBean(HttpServletRequest request) {

		System.out.println("in populateBean");
		
		String op = DataUtility.getString(request.getParameter("operation"));
		
		TimetableBean bean = new TimetableBean();
		
		//bean.setId(DataUtility.getInt(request.getParameter("id")));
		System.out.println(" course name --------> "+(request.getParameter("courseid")));
		bean.setCourseId(DataUtility.getInt(request.getParameter("courseid")));
		//bean.setCourseName(DataUtility.getString(request.getParameter("courseName")));
		bean.setSubjectId(DataUtility.getInt(request.getParameter("subjectid")));
		//bean.setSubjectName(DataUtility.getString(request.getParameter("subjectName")));
		//bean.setSemester(DataUtility.getString(request.getParameter("sem")));
		//bean.setExamTime(DataUtility.getString(request.getParameter("examtime")));
		
		//if(!OP_DELETE.equalsIgnoreCase(op)){  
		System.out.println(" date           --"+request.getParameter("examdate"));
		
		bean.setExamDate(DataUtility.getDate(request.getParameter("examdate")));
		System.out.println("date popu "+bean.getExamDate());
		
		
		
		return bean;
	}

	/**
	 * Contains display logic
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("TimetableListCtl doGet method Started");
   
		  System.out.println("inside do get method");
		  
			int pageNo= 1;
			int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));
			Timetablemodel model = new Timetablemodel();
			
			TimetableBean bean = new TimetableBean();
			List list =new ArrayList();
			
			try {
				list = model.search(bean, pageNo, pageSize);
				if(list==null || list.size()==0){
					ServletUtility.setErrorMessage("no record found", request);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ServletUtility.setList(list, request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.forward(getView(), request, response);
			log.debug("TimetableListCtl doGet method End");
		
	}

	/**
	 * Contains Submit logics
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("TimetableListCtl doPost method Started");
		System.out.println("inside do post");
		
		long id =DataUtility.getLong(request.getParameter("id"));
		String op = DataUtility.getString(request.getParameter("operation"));
		
		String ids[] = request.getParameterValues("ids");
		
		int pageNo= DataUtility.getInt(request.getParameter("pageNo"));
		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));
		
		pageNo = (pageNo==0)?1:pageNo;
		pageSize = (pageSize==0)?DataUtility.getInt(PropertyReader.getValue("page.size")):pageSize;
		
		if(OP_SEARCH.equalsIgnoreCase(op))
		{
			pageNo=1;
		}
		
		else if(OP_PREVIOUS.equalsIgnoreCase(op)){
			if(pageNo>1){
				pageNo--;
			}
			else{
				ServletUtility.setErrorMessage("no previous page", request);
				pageNo=1;
			}
		}
		else if(OP_NEXT.equalsIgnoreCase(op)){
			pageNo++;
		}
		
		else if(OP_NEW.equalsIgnoreCase(op)){
			ServletUtility.redirect(ORSView.TIMETABLE_CTL, request, response);
			return;
		}
		else if(OP_RESET.equalsIgnoreCase(op)){
			ServletUtility.redirect(ORSView.TIMETABLE_LIST_CTL, request, response);
		    return;
		}
		
		else if(OP_BACK.equalsIgnoreCase(op)){
			ServletUtility.redirect(ORSView.TIMETABLE_LIST_CTL, request, response);
			return;
		}
		else if(OP_DELETE.equalsIgnoreCase(op)){
		
			Timetablemodel mod = new Timetablemodel();
			TimetableBean dbean = new TimetableBean();
			System.out.println("ids=="+ids);
			if(ids!=null && ids.length>0){
				
				for(String id2:ids){
					
					int idnew= DataUtility.getInt(id2);
					dbean.setId(idnew);
					try {
						mod.delete(dbean);
					} catch (ApplicationException e) {
						
						e.printStackTrace();
						ServletUtility.handleException(e, request, response);
					}
					
					}
				ServletUtility.setSuccessMessage("Record  deleted successfully", request);
			}
		}
			else{
				ServletUtility.setErrorMessage("Select Atleast One Record", request);
			}
			
			
		
		
		Timetablemodel model = new Timetablemodel();
		
		TimetableBean bean = (TimetableBean)populateBean(request);
		
		List list =new ArrayList();
		
		try {
			//if(!OP_DELETE.equalsIgnoreCase(op)){
			list = model.search(bean, pageNo, pageSize);
			System.out.println("list in ctl is"+list.size());
		
			
			//	}
			
			if(list==null || list.size()==0 && !OP_DELETE.equalsIgnoreCase(op)){
				System.out.println("list in TT..........."+list.size());
				ServletUtility.setErrorMessage("No Record found", request);
				ServletUtility.forward(getView(), request, response);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ServletUtility.setBean(bean, request);
		ServletUtility.setList(list, request);
		ServletUtility.setPageNo(pageNo, request);
		ServletUtility.setPageSize(pageSize, request);
		ServletUtility.forward(getView(), request, response);
		log.debug("TimetableListCtl doPost method End");
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.TIMETABLE_LIST_VIEW;
}
}