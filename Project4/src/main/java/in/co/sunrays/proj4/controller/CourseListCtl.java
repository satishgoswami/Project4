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
import in.co.sunrays.proj4.bean.CourseBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.model.CollegeModel;
import in.co.sunrays.proj4.model.CourseModel;
import in.co.sunrays.proj4.util.DataUtility;
import in.co.sunrays.proj4.util.PropertyReader;
import in.co.sunrays.proj4.util.ServletUtility;


/**
 * Course List functionality Controller. Performs operation for list, search and
 * delete operations of Course
 *
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */
@WebServlet(name = "CourseListCtl", urlPatterns = { "/ctl/CourseListCtl" })

public class CourseListCtl extends BaseCtl {
	private static Logger log = Logger.getLogger(CourseListCtl.class);

@Override
protected void preload(HttpServletRequest request) {
CourseModel model=new CourseModel();
try {
List clist=	model.list();
request.setAttribute("clist", clist);
} catch (ApplicationException e) {
	e.printStackTrace();
}
	
}
	
	protected BaseBean populateBean(HttpServletRequest request) {

		CourseBean bean = new CourseBean();
		bean.setId(DataUtility.getLong(request.getParameter("CourseName")));
		System.out.println("id   "+bean.getId());
		/*populateDTO(bean, request);*/
		return bean;
	}

	/**
	 * Contains Display logics
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.debug("CourseListCtl doGet Start");
		System.out.println("inside CourseListCTL doGet");

		CourseModel model = new CourseModel();
		CourseBean bean = (CourseBean) populateBean(request);

		int pageNo = 1;
		int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));
		List list = new ArrayList();
		try {
			list = model.search(bean, pageNo, pageSize);
			System.out.println("-----------" + bean.getCourseName());

			if (list == null || list.size() == 0) {
				ServletUtility.setErrorMessage("No record found ", request);
			}
			ServletUtility.setList(list, request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.forward(getView(), request, response);
		} catch (ApplicationException e) {
			e.printStackTrace();
			ServletUtility.handleException(e, request, response);
			return;
		}
		log.debug("CourseListCtl doGet End");

	}

	/**
	 * Contains Submit logics
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.debug("UserListCtl doPost Start");

		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));

		pageNo = (pageNo == 0) ? 1 : pageNo;
		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;

		String op = DataUtility.getString(request.getParameter("operation"));

		// get the selected checkbox ids array for delete list
		String[] ids = request.getParameterValues("ids");

		if (OP_SEARCH.equalsIgnoreCase(op)) {
			pageNo = 1;
		}

		else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.COURSE_LIST_CTL, request, response);
			return;
		}

		else if (OP_PREVIOUS.equalsIgnoreCase(op)) {
			pageNo--;
		} else if (OP_NEW.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.COURSE_CTL, request, response);
			return;
		} else if (OP_NEXT.equalsIgnoreCase(op)) {
			pageNo++;
		}

		else if (OP_DELETE.equalsIgnoreCase(op)) {
			CourseModel model = new CourseModel();
			System.out.println("id delete...........");
			if (ids != null && ids.length > 0) {
				pageNo=1;
				for (String id : ids) {
					CourseBean deleteBean = new CourseBean();
					int id2=DataUtility.getInt(id);
					deleteBean.setId(id2);
					try {
						model.delete(deleteBean);
						ServletUtility.setSuccessMessage("Data Deleted Successfully", request);
					} 
					catch (ApplicationException e) {
						ServletUtility.handleException(e, request, response);
						e.printStackTrace();
					}

				}
			} else {
				ServletUtility.setErrorMessage("Select Atleast one record", request);
			}

		}
		CourseModel model = new CourseModel();

		CourseBean bean = (CourseBean) populateBean(request);
           System.out.println("id is........"+bean.getId());
		List list = new ArrayList();

		try {
			list = model.search(bean, pageNo, pageSize);

			if (list == null || list.size() == 0) {
				ServletUtility.setErrorMessage("No record found ", request);
			}
			request.setAttribute("cbean", bean);
			ServletUtility.setList(list, request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.forward(getView(), request, response);
			
		} catch (ApplicationException e) {
			ServletUtility.handleException(e, request, response);
			e.printStackTrace();
		}
log.debug("CourseListCtl doPost Method End");
	}

	@Override
	protected String getView() {
		return ORSView.COURSE_LIST_VIEW;
	}}