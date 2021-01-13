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
import in.co.sunrays.proj4.util.DataUtility;
import in.co.sunrays.proj4.util.PropertyReader;
import in.co.sunrays.proj4.util.ServletUtility;

@WebServlet(name = "FacultyListCtl", urlPatterns = { "/ctl/FacultyListCtl" })
public class FacultyListCtl  extends BaseCtl{
	private static final long serialVersionUID = 1L;
	private static Logger log=Logger.getLogger(FacultyListCtl.class);

	@Override
	protected void preload(HttpServletRequest request) {

		/*FacultyModel model = new FacultyModel();
		try {
			List flist = model.list(0, 0);
			request.setAttribute("flist", flist);

		} catch (Exception e) {
			e.printStackTrace();
		}*/
		CollegeModel cmodel = new CollegeModel();
		try {
			List clist = cmodel.list(0, 0);
			request.setAttribute("clist", clist);
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
		CourseModel ccmodel = new CourseModel();
		try {
			List cclist = ccmodel.list(0, 0);
			request.setAttribute("cclist", cclist);
		} catch (ApplicationException e) {
			e.printStackTrace();
		}

	}

	protected BaseBean populateBean(HttpServletRequest request) {

		FacultyBean bean = new FacultyBean();

		bean.setFirstName(DataUtility.getString(request.getParameter("fname")));

		bean.setCollegeId(DataUtility.getInt(request.getParameter("cname")));
		bean.setCourseId(DataUtility.getInt(request.getParameter("lname")));

		System.out.println(request.getParameter("lname")+"==============--------------");
		return bean;
	}

	/*
	 * Contain view logic
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("FacultListCtl doGet Method Started");

		System.out.println("inside doGet");

		int pageNo = 1;
		int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));

		List list = new ArrayList();
		FacultyModel model = new FacultyModel();
		FacultyBean bean = new FacultyBean();
		try {

			list = model.search(bean, pageNo, pageSize);
			if (list == null || list.size() == 0) {
				ServletUtility.setErrorMessage("no record found", request);
			}

			ServletUtility.setList(list, request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.forward(getView(), request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
log.debug("FacultyListCtl doGet Method Started");
	}

	/*
	 * contain submit logic
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("FacultyListCtl doPost Method Started");

		System.out.println("inside do post");

		long id = DataUtility.getInt(request.getParameter("id"));
		String op = DataUtility.getString(request.getParameter("operation"));

		String ids[] = request.getParameterValues("ids");

		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));

		pageNo = (pageNo == 0) ? 1 : pageNo;
		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;

		if (OP_SEARCH.equalsIgnoreCase(op)) {
			System.out.println("search called");
			pageNo = 1;
		}

		else if (OP_PREVIOUS.equalsIgnoreCase(op)) {

			if (pageNo > 1) {
				pageNo--;
			} else {
				pageNo = 1;
			}
		}

		else if (OP_NEXT.equalsIgnoreCase(op)) {
			pageNo++;
		}

		else if (OP_NEW.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.FACULTY_CTL, request, response);
			return;
		}

		else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.FACULTY_LIST_CTL, request, response);
			return;
		} else if (OP_BACK.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.FACULTY_LIST_CTL, request, response);
			return;
		} else if (OP_DELETE.equalsIgnoreCase(op)) {

			FacultyModel mod = new FacultyModel();
			FacultyBean dbean = new FacultyBean();

			if (ids != null && ids.length > 0) {

				for (String id2 : ids) {
					int idnew = DataUtility.getInt(id2);
					dbean.setId(idnew);
					try {
						mod.delete(dbean);
						ServletUtility.setSuccessMessage("Record Deleted Successfully", request);
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
		FacultyModel model = new FacultyModel();
		FacultyBean bean = null;
		try {
			bean = (FacultyBean) populateBean(request);
			list = model.search(bean, pageNo, pageSize);
			System.out.println(list.size()+"----------size-------");
			if (list == null || list.size() == 0 && !OP_DELETE.equalsIgnoreCase(op)) {
				ServletUtility.setErrorMessage("No Record Found", request);
			}

			ServletUtility.setBean(bean, request);
			ServletUtility.setList(list, request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			ServletUtility.forward(getView(), request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
log.debug("FacultyListCtl doPost Method End");
	}

	@Override
	protected String getView() {
		return ORSView.FACULTY_LIST_VIEW;
	}
}
