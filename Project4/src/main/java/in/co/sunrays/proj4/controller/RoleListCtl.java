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
import in.co.sunrays.proj4.bean.RoleBean;
import in.co.sunrays.proj4.model.RoleModel;
import in.co.sunrays.proj4.util.DataUtility;
import in.co.sunrays.proj4.util.PropertyReader;
import in.co.sunrays.proj4.util.ServletUtility;

/**
 * Role List functionality Controller. Performs operation for list, search
 * operations of Role
 * 
 * @author SunilOS
 * @version 1.0
 * @Copyright (c) SunilOS
 */

@WebServlet(name="RoleListCtl",urlPatterns={"/ctl/RoleListCtl"})
public class RoleListCtl extends BaseCtl {
private static final long serialVersionUID = 1L;
private static Logger log=Logger.getLogger(RoleListCtl.class);
protected void preload(HttpServletRequest request){
	RoleModel model=new RoleModel();
	try {
		List rlist=model.list();
		request.setAttribute("rlist", rlist);
	} catch (Exception e) {
		e.printStackTrace();
	}
	
}
    
	
   
	protected BaseBean populateBean(HttpServletRequest request){
		
		RoleBean bean = new RoleBean();
		bean.setId(DataUtility.getLong(request.getParameter("rname")));
		System.out.println("id  is  "+bean.getId());
		return bean;
	}
	
	/**
	 * Contains display logic
	 */
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		log.debug("RoleListCtl doGet Method Started");
		System.out.println("inside do get");
		
		int pageNo=1;
		int pageSize= DataUtility.getInt(PropertyReader.getValue("page.size"));
		
		RoleModel model = new RoleModel();
		RoleBean bean = (RoleBean)populateBean(request);
		
		List<RoleBean> list = new ArrayList<RoleBean>();
		
		try{
			list = model.search(bean, pageNo, pageSize);
			
			if(list.size()==0 || list==null){
				ServletUtility.setErrorMessage("No record exist", request);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		ServletUtility.setList(list, request);
		ServletUtility.setPageNo(pageNo, request);
		ServletUtility.setPageSize(pageSize, request);
		ServletUtility.forward(getView(), request, response);
		log.debug("RoleListCtl doGet Method End");
	}

	/**
	 * Contains Submit logics
	 */
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
		log.debug("RoleListCtl doPost Method Started");
		System.out.println("inside do post");
		
		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
		int pageSize= DataUtility.getInt(request.getParameter("pageSize"));
		
		
		pageNo = (pageNo==0)?1:pageNo;
		pageSize= (pageSize==0)?DataUtility.getInt
				    (PropertyReader.getValue("page.size")):pageSize;

	    String op = DataUtility.getString(request.getParameter("operation"));
		
	    String ids[]=request.getParameterValues("ids");
	    
	    if(OP_SEARCH.equalsIgnoreCase(op)){
			pageNo=1;
		}
		else if(OP_PREVIOUS.equalsIgnoreCase(op)){
			
			if(pageNo>1){
				pageNo--;
			}
			else{
				pageNo=1;
			}
		}
		else if(OP_NEXT.equalsIgnoreCase(op)){
			pageNo++;
		}
		
		else if(OP_NEW.equalsIgnoreCase(op)){
			ServletUtility.redirect(ORSView.ROLE_CTL, request, response);
			return;
		}
		
		else if(OP_RESET.equalsIgnoreCase(op)){
			ServletUtility.redirect(ORSView.ROLE_LIST_CTL, request, response);
			return;
		}
		else if(OP_DELETE.equalsIgnoreCase(op)){
			RoleModel mod = new RoleModel();
			RoleBean dbean = new RoleBean();
			
			if(ids!=null && ids.length>0){
				
				for(String id2:ids){
				int idnew = DataUtility.getInt(id2);
				dbean.setId(idnew);
				try {
					mod.delete(dbean);
					ServletUtility.setSuccessMessage("Record Deleted Successfully", request);
					//ServletUtility.forward(getView(), request, response);
				} catch(Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				}
			}
			else{
				ServletUtility.setErrorMessage("Select Atleast One Record", request);
			}
		}
		
		RoleModel model = new RoleModel();
		RoleBean bean = (RoleBean)populateBean(request);
		
		List<RoleBean> list= new ArrayList<RoleBean>();
		try{
			
			list = model.search(bean, pageNo, pageSize);
			if(list==null || list.size()==0 && !OP_DELETE.equalsIgnoreCase(op)){
				ServletUtility.setErrorMessage("No Record Found", request);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		ServletUtility.setBean(bean, request);
		ServletUtility.setList(list, request);
		ServletUtility.setPageNo(pageNo, request);
		ServletUtility.setPageSize(pageSize, request);
		ServletUtility.forward(getView(), request, response);
		log.debug("RoleListCtl doPost Method End");
	}


	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.ROLE_LIST_VIEW;
	}

}

