<%@page import="in.co.sunrays.proj4.controller.StudentCtl"%>
<%@page import="in.co.sunrays.proj4.util.HTMLUtility"%>
<%@page import="in.co.sunrays.proj4.util.DataUtility"%>
<%@page import="in.co.sunrays.proj4.util.ServletUtility"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" />
<title>User View Page</title>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
	$(function() {
		$("#dob").datepicker({
			changeMonth : true,
			changeYear : true,
			yearRange:'1980:2002',
			dateFormat: 'dd-mm-yy'
		});
	});
</script>
</head>
<body>
<%@include file ="Header.jsp" %>
<jsp:useBean id="bean" class="in.co.sunrays.proj4.bean.StudentBean" scope="request"></jsp:useBean>
<center>
  

<form action="<%=ORSView.STUDENT_CTL %>" method="post">

<%
List l = (List)request.getAttribute("collegeList");
%>

<%
System.out.println("id in jsp"+bean.getId());

if(bean!=null && bean.getId()>0){
%>
<h1>Update Student</h1>
<%}else{%>

<h1>Add Student</h1>
<%} %>

<h2>
<font color="green"><%=ServletUtility.getSuccessMessage(request)%></font>
</h2>

<h2>
<font color="red"><%=ServletUtility.getErrorMessage(request) %></font>
</h2>


<input type="hidden" name = "id" value="<%=bean.getId()%>">
<input type="hidden" name = "createdBy" value="<%=bean.getCreatedBy()%>">
<input type="hidden" name = "modifiedBy" value="<%=bean.getModifiedBy()%>">
<input type="hidden" name = "createDateTime" value="<%=DataUtility.getTimestamp(bean.getCreateDatetime())%>">
<input type="hidden" name = "modifiedDateTime" value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">
<input type="hidden" name = "collegeName" value="<%=HTMLUtility.getList("collegeName",String.valueOf(bean.getCollegeId()),l)%>" >




<table >
  
  <tr>
      <th align="left">College<span style="color: red">*</span></th>
      <td><%=HTMLUtility.getList("collegeId",String.valueOf(bean.getCollegeId()),l) %>
      </td>
      <td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("collegeId",request) %></font>
      </td>
 </tr>
      
   <tr>
      <th align="left">First Name<span style="color: red">*</span></th>
      <td><input type="text" name = "firstName" value="<%=DataUtility.getStringData(bean.getFirstName()) %>" placeholder="Enter First Name">
      </td>
      <td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("firstName",request) %></font>
      </td>
 </tr>
 
 <tr>
      <th align="left">Last Name<span style="color: red">*</span></th>
      <td><input type="text" name = "lastName" value="<%=DataUtility.getStringData(bean.getLastName()) %>" placeholder="Enter last Name">
      </td>
      <td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("lastName",request) %></font>
      </td>
 </tr>
      
 <tr>
      <th align="left">DOB<span style="color: red">*</span></th>
      <td><input type="text" name = "dob" id="dob" <%-- <% if(bean!=null && bean.getId()>0){
    	  %> readonly="readonly" <%} %> --%>readonly="readonly"
      value="<%=DataUtility.getDateString(bean.getDob()) %>" placeholder="Enter DOB">
      </td>
      <td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("dob",request) %></font>
      </td>
 </tr>
 
 <tr>
      <th align="left">Mobile<span style="color: red">*</span></th>
      <td><input type="text" name = "mobile" value="<%=DataUtility.getStringData(bean.getMobileNo()) %>" placeholder="Enter Mobile No">
      </td>
      <td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("mobile",request) %></font>
      </td>
 </tr>
 
 <tr>
     <th align="left">Address<span style="color: red">*</span></th>
     <td><input type="text" name="address" placeholder="Enter Address"
      value="<%=DataUtility.getStringData(bean.getAddress()) %>">
     <td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("address", request) %></font>
     </td>   
 </tr>
 
 <tr>
      <th align="left">Email<span style="color: red">*</span></th>
      <td><input type="text" name = "email" value="<%=DataUtility.getStringData(bean.getEmail()) %>" placeholder="Enter Emailid">
      </td>
      <td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("email",request) %></font>
      </td>
 </tr>
      



<tr>
   <th></th>
   <td>
<%
if(bean.getId()>0 && bean!=null){
%> 
   <input type="submit" name ="operation" value="Update">
 &nbsp;&emsp; &emsp; &nbsp; <input type="submit" name ="operation" value="<%=StudentCtl.OP_CANCEL %>">
 
 <%}else{ %>
  <input type="submit" name ="operation" value="<%=StudentCtl.OP_SAVE %>">
  
 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="submit" name ="operation" value="<%=StudentCtl.OP_RESET%>">
   
<%} %>
</td>
</tr>
</table>
</form>
</center>
</body>
<%@include file="Footer.jsp"%>
<body>

</body>
</html>