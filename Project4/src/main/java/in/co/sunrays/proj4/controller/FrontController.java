package in.co.sunrays.proj4.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import in.co.sunrays.proj4.util.ServletUtility;

//TODO: Auto-generated Javadoc
/**
* Main Controller performs session checking and logging operations before
* calling any application controller. It prevents any user to access
* application without login.
* 
* 
* @author SunilOS
* @version 1.0
* @Copyright (c) SunilOS
*/
@WebFilter(urlPatterns = {  "/ctl/*","/doc/*", })
public class FrontController implements Filter {

private static Logger log=Logger.getLogger(FrontController.class);
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		log.debug("FrontController doFillter Method Started");
		

		System.out.println("frnt ctl started");
		
		HttpServletRequest request1 = (HttpServletRequest) request;
		HttpServletResponse response1 = (HttpServletResponse) response;

		HttpSession session = request1.getSession();

		if (session.getAttribute("user") == null) {

			System.out.println("inside if of frnt ctl");
			request1.setAttribute("message", "Your session has been expired please login again");
			// ServletUtility.setErrorMessage("ur session has been expired",
			// request1);

			String str = request1.getRequestURI();
			session.setAttribute("URI", str);

			ServletUtility.forward(ORSView.LOGIN_VIEW, request1, response1);
			return;
		} else {
			System.out.println("inside else of frnt ctl ");
			chain.doFilter(request1, response1);
		}
		log.debug("FrontController doFillter Method End");
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

	
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
