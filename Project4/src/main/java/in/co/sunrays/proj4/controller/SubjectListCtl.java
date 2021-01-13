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
import in.co.sunrays.proj4.bean.SubjectBean;
import in.co.sunrays.proj4.model.CourseModel;
import in.co.sunrays.proj4.model.Subjectmodel;
import in.co.sunrays.proj4.util.DataUtility;
import in.co.sunrays.proj4.util.PropertyReader;
import in.co.sunrays.proj4.util.ServletUtility;

/**
 * Subject List functionality Controller. Performs operation for list, search and
 * delete operations of Subject
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */
@WebServlet(name = "SubjectListCtl", urlPatterns = { "/ctl/SubjectListCtl" })
public class SubjectListCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(SubjectListCtl.class);

	protected void preload(HttpServletRequest request) {

		System.out.println("preload of subject");

		CourseModel cmodel = new CourseModel();

		try {
			List clist = cmodel.list();
			request.setAttribute("courselist", clist);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Subjectmodel smodel = new Subjectmodel();

		try {
			List slist = smodel.list();
			request.setAttribute("subjectlist", slist);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected BaseBean populateBean(HttpServletRequest request) {

		SubjectBean bean = new SubjectBean();

		bean.setId(DataUtility.getLong(request.getParameter("subjectId")));
		bean.setCourseId(DataUtility.getInt(request.getParameter("courseId")));
		// bean.setCourseName(DataUtility.getString(request.getParameter("courseName")));
		// bean.setId(DataUtility.getInt(request.getParameter("subjectId")));
		// bean.setSubjectName(DataUtility.getString(request.getParameter("subjectName")));
		populateDTO(bean, request);
		return bean;
	}

	/**
	 * Contains display logic
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("SubjectListCtl doGet method Started");

		System.out.println("inside do get");

		int pageNo = 1;
		int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));

		List list = new ArrayList();

		Subjectmodel model = new Subjectmodel();
		SubjectBean bean = (SubjectBean) populateBean(request);
		try {
			list = model.search(bean, pageNo, pageSize);

			if (list == null || list.size() == 0) {
				ServletUtility.setErrorMessage("no record exist", request);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		ServletUtility.setList(list, request);
		ServletUtility.setPageNo(pageNo, request);
		ServletUtility.setPageSize(pageSize, request);
		ServletUtility.forward(getView(), request, response);
		log.debug("SubjectListCtl doGet method End");
	}


    /**
     * Contains Submit logics
 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("SubjectListCtl doPost method Started");

		System.out.println("inside do post");

		long id = DataUtility.getLong(request.getParameter("id"));
		String op = DataUtility.getString(request.getParameter("operation"));

		String ids[] = request.getParameterValues("ids");

		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));

		pageNo = (pageNo == 0) ? 1 : pageNo;
		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;

		if (OP_SEARCH.equalsIgnoreCase(op)) {
			pageNo = 1;
		} else if (OP_PREVIOUS.equalsIgnoreCase(op)) {
			if (pageNo > 1) {
				pageNo--;
			}
			/*
			 * else if(pageNo==1){ System.out.println("111"); }
			 */
			else {
				ServletUtility.setErrorMessage("No previous page", request);
				pageNo = 1;
			}
		}

		else if (OP_NEXT.equalsIgnoreCase(op)) {
			pageNo++;
		}

		else if (OP_NEW.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.SUBJECT_CTL, request, response);
			return;
		}

		else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.SUBJECT_LIST_CTL, request, response);
			return;
		} else if (OP_DELETE.equalsIgnoreCase(op)) {

			Subjectmodel mod = new Subjectmodel();
			SubjectBean dbean = new SubjectBean();

			if (ids != null && ids.length > 0) {

				for (String id2 : ids) {

					int idnew = DataUtility.getInt(id2);
					dbean.setId(idnew);
					try {
						mod.delete(dbean);
						ServletUtility.setSuccessMessage("Record Deleted Successfully", request);
						// ServletUtility.forward(getView(), request, response);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			} else {
				ServletUtility.setErrorMessage("Select Atleast One Record", request);
			}
		}

		List list = new ArrayList();
		Subjectmodel model = new Subjectmodel();
		SubjectBean bean = (SubjectBean) populateBean(request);

		try {
			// if(!OP_DELETE.equalsIgnoreCase(op)){

			list = model.search(bean, pageNo, pageSize);
			ServletUtility.setBean(bean, request);
			// }

			if (list == null || list.size() == 0 && !OP_DELETE.equalsIgnoreCase(op)) {
				ServletUtility.setErrorMessage("No Record Found", request);

				System.out.println("list in subjectctl..........." + list.size());
			}
			ServletUtility.setBean(bean, request);
			ServletUtility.setList(list, request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.forward(getView(), request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
		log.debug("SubjectListCtl doPost method End");
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.SUBJECT_LIST_VIEW;
	}

}


