package in.co.sunrays.proj4.controller;
import java.io.IOException;


import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.sunrays.proj4.model.MarksheetModel;
import in.co.sunrays.proj4.util.DataUtility;
import in.co.sunrays.proj4.util.PropertyReader;
import in.co.sunrays.proj4.util.ServletUtility;

/**
 * Marksheet Merit List functionality Controller. Performance operation of
 * Marksheet Merit List
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */

@WebServlet(name="MarksheetMeritListCtl",urlPatterns={"/ctl/MarksheetMeritListCtl"})
public class MarksheetMeritListCtl extends BaseCtl {
	private static final long serialVersionUID = 1L;
       
    private static Logger log = Logger.getLogger(MarksheetMeritListCtl.class);
    
    /**
	 * Contains display logic
	 */
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("inside do get");
		
		log.debug("MarksheetMeritListCtl doGet Method End");
	
		int pageNo=1;
		int pageSize= DataUtility.getInt(PropertyReader.getValue("page.size"));
		
		MarksheetModel model = new MarksheetModel();
		try{
			List list =null;
			list=model.getMeritList(1,10);
			
			if(list==null && list.size()>0){
				ServletUtility.setErrorMessage("No record Found", request);
			}
			
			request.setAttribute("list",list);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		ServletUtility.setPageNo(pageNo, request);
		ServletUtility.setPageSize(pageSize, request);
		ServletUtility.forward(getView(),request,response);
		log.debug("MarksheetMeritListCtl doGet Method End");
	}
    

/**
	 * Contains Submit logics
	 */

		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			log.debug("MarksheetMeritListCtl doPost Method Started");
			System.out.println("inside do post");
			
			String op = DataUtility.getString(request.getParameter("operation"));
			
			if(OP_BACK.equalsIgnoreCase(op)){
				ServletUtility.forward(ORSView.WELCOME_VIEW, request, response);
			}
			log.debug("MarksheetMeritListCtl dopost Method End");
	}

		@Override
		protected String getView() {
			// TODO Auto-generated method stub
			return ORSView.MARKSHEET_MERIT_LIST_VIEW;
		}}