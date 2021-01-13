package in.co.sunrays.proj4.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.sunrays.proj4.util.ServletUtility;

/**
 * @author '
 *
 */

@WebServlet(name="ErrorCtl", urlPatterns="/ErrorCtl")
public class ErrorCtl extends BaseCtl {
private static final long serialVersionUID = 1L;
private static Logger log = Logger.getLogger(ErrorCtl.class);

/* 
 * contains view logic
 * */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
log.debug("ErrorCtl doGet Method Started");
		ServletUtility.forward(getView(), request, response);
		log.debug("ErrorCtl doGet Method Ended");	
	}
	/* 
	 * contains submit logic
	 * */
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("ErrorCtl dopost Method Started");
		ServletUtility.forward(getView(), request, response);
		log.debug("ErrorCtl doPost Method End");
	}

	@Override
	protected String getView() {
		// TODO Auto-generated method stub
		return ORSView.ERROR_VIEW;
	}

}


