<%@page import="in.co.sunrays.proj4.controller.ChangePasswordCtl"%>
<%@page import="in.co.sunrays.proj4.util.DataUtility"%>
<%@page import="in.co.sunrays.proj4.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="icon" type="image/png" href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16*16"/>
<title>Change Password</title>
</head>

<body>
    <form action="<%=ORSView.CHANGE_PASSWORD_CTL%>" method="post">
        
        <%@ include file="Header.jsp"%>

        <jsp:useBean id="bean" class="in.co.sunrays.proj4.bean.UserBean"
            scope="request"></jsp:useBean>

        <center>
            <h1>Change Password</h1>


            <H2>
                <font color="red"> <%=ServletUtility.getErrorMessage(request)%>
                </font>
            </H2>
			<H2>
                <font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
                </font>
            </H2>
            
            <input type="hidden" name="id" value="<%=bean.getId()%>">
            <input type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
            <input type="hidden" name="modifiedBy" value="<%=bean.getModifiedBy()%>">
            <input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(bean.getCreateDatetime())%>">
            <input type="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">

         <table>
       <tr>
           <th align="left">Old password<span style="color:red">*</span></th>
           <td >
     <input type="password" name="oldPassword" value="<%=DataUtility.getStringData(request.getParameter("oldPassword"))%>" placeholder="Enter Old Password"> 
        </td>       
          <td style="position: fixed;"><font color ="red"><%=ServletUtility.getErrorMessage("oldPassword",request)%></font>
          </td>
       </tr>
       
   <tr>
           <th align="left">New password<span style="color:red">*</span></th>
           <td >
           <input type="password" name="newPassword" value="<%=DataUtility.getStringData(request.getParameter("newPassword"))%>" placeholder="Enter New Password">
            </td>        
              <td style="position: fixed;"><font color ="red"><%=ServletUtility.getErrorMessage("newPassword",request) %></font>
          </td>
       </tr>
           <tr>
           <th >Confirm password<span style="color:red">*</span></th>
           <td >
           <input type="password" name="confirmPassword" value="<%=DataUtility.getStringData(request.getParameter("confirmPassword"))%>" placeholder="Enter Confirm Password">
            </td>
           <td style="position: fixed;"><font color ="red"><%=ServletUtility.getErrorMessage("confirmPassword", request) %></font>
         
          </td>
       </tr>
       
       <tr>
           <th></th>
           <td><input type="submit" name ="operation" value="<%=ChangePasswordCtl.OP_SAVE %>">
            &nbsp;<input type="submit" name = "operation" value="<%=ChangePasswordCtl.OP_CHANGE_MY_PROFILE %>">  
           </td>
        </tr>

</table>
            
    </form>
    </center>
    <%@ include file="Footer.jsp"%>
</body>
</html>