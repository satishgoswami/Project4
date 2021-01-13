<%@page import="in.co.sunrays.proj4.controller.ForgetPasswordCtl"%>
<%@page import="in.co.sunrays.proj4.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Forget View</title>
<head>

<link rel="icon" type="image/png" href="<%=ORSView.APP_CONTEXT%>/img/logo.png" />
<title>Forget Password Page</title>

</head>
<body>
    <form action="<%=ORSView.FORGET_PASSWORD_CTL%>" method="post">

        <%@ include file="Header.jsp"%>

        <jsp:useBean id="bean" class="in.co.sunrays.proj4.bean.UserBean"
            scope="request"></jsp:useBean>

<center>
 <h1>Forget Password</h1>

<h3><font color="blue">Submit your email address and we'll send you password.</font></h3>

<h2>
<font color="green"><%=ServletUtility.getSuccessMessage(request)%></font>
</h2>

<h2>
<font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
</h2>

<input type="hidden" name ="id" value="<%=bean.getId()%>">

<table>
<tr>
    <th align="left" style="height:20px;font-size:10pt;">Email Id<span style="color: red" >*</span></th>                
 <td><input type= "text" name="login" placeholder="Enter EmailId"
 value="<%=ServletUtility.getParameter("login", request) %>" size="20" style="height:20px;font-size:10pt;">
 </td>

 &emsp;
 <td><input type="submit" name="operation" style="height:25px;font-size:12pt;" value="<%=ForgetPasswordCtl.OP_GO%>">
 <td><input type="submit" name="operation" style="height:25px;font-size:12pt;" value="<%=ForgetPasswordCtl.OP_RESET%>">
 <td style="position: fixed;"> <font color="red"><%=ServletUtility.getErrorMessage("login",request)%></font></td> 
 
 </tr>
 </table>
</center>
</form>
<%@include file ="Footer.jsp" %>
</body>
</html>