<%@page import="in.co.sunrays.proj4.util.HTMLUtility"%>
<%@page import="in.co.sunrays.proj4.util.DataUtility"%>
<%@page import="in.co.sunrays.proj4.model.RoleModel"%>
<%@page import="in.co.sunrays.proj4.model.UserModel"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="in.co.sunrays.proj4.controller.UserListCtl"%>
<%@page import="in.co.sunrays.proj4.util.ServletUtility"%>
<%@ page errorPage="ErrorView.jsp"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="icon" type="image/png" href="<%=ORSView.APP_CONTEXT%>/img/logo.png" />
<title>UserList View Page</title>
<script  src="<%=ORSView.APP_CONTEXT%>/js/jquery.min.js">
	
</script>	
<script type="text/javascript" src="<%=ORSView.APP_CONTEXT%>/js/checkbox11.js"></script>
 
</head>
<body>
<jsp:useBean id="bean" class="in.co.sunrays.proj4.bean.UserBean" scope="request"></jsp:useBean>

<%@include file="Header.jsp" %>

<center>

  <h1>User List</h1>

        <h2>
        <font color="red"><%=ServletUtility.getErrorMessage(request) %></font>
      </h2>
  
      <h2>
        <font color="green"><%=ServletUtility.getSuccessMessage(request) %></font>
      </h2>
  
<form action="<%=ORSView.USER_LIST_CTL%>" method="post">

		<%
            List lt = (List) request.getAttribute("role_list");
        %>



<% List l = ServletUtility.getList(request); 
if(l==null || l.size()==0){ System.out.println(l.size()+"------------llll");%>
<input type="submit" name="operation" value="<%=UserListCtl.OP_BACK%>">	
<%}else{ %>
      <table width="100%">
<tr>
    <td align="center"><label>First Name</label>
    <input type="text" name="firstName" placeholder="Enter First Name" value="<%=ServletUtility.getParameter("firstName", request) %>">
  &nbsp;<label>Role Name</label>
  <%=HTMLUtility.getList("rname", String.valueOf(bean.getRoleId()), lt) %>
  &nbsp;<label>Login Id</label>
  <input type="text" name="login" placeholder="Enter Login Id" value="<%=ServletUtility.getParameter("login", request) %>">
   <input type="submit" name="operation" value="<%=UserListCtl.OP_SEARCH %>">
   <input type="submit" name="operation" value="<%=UserListCtl.OP_RESET %>">
   </td>
   </tr> 
</table>

<table border="1" width="100%" align="center">
 <tr><th align="left"><input type="checkbox" name="ids" id = "select_all">Select All</th>
     <th>S.NO</th>
     <th>FIRST NAME</th>
     <th>LAST NAME</th>
     <th>ROLE NAME</th>
     <th>Address</th>
     <th>Mobile No</th>
     <th>GENDER</th>
     <th>DOB</th>
     <th>EMAIL</th>
     <th>EDIT</th>
</tr>


   <%
     int pageNo= ServletUtility.getPageNo(request);
     int pageSize= ServletUtility.getPageSize(request);
     int index= ((pageNo-1)*pageSize)+1;
     
    
     List list = ServletUtility.getList(request);
     
     Iterator it = list.iterator();
     
     //UserBean bean;
     while(it.hasNext()){
    	
    	 bean = (UserBean)it.next();
    	 //System.out.println("role is"+bean.getRoleId());
   %>
   <tr>
   <td align="center"><input type="checkbox" class="checkbox" name="ids" value="<%=bean.getId()%>"
   <%if(bean.getRoleId()==1) {%>
   <%="disabled"%><%} %>>
   </td>
   
   <td align="center"><%=index++%></td>
   <td align="center"><%=bean.getFirstName()%></td>
   <td align="center"><%=bean.getLastName()%></td>
   <td align="center"><%=bean.getRoleName()%></td>
   <td align="center"><%=bean.getAddress()%></td>
   <td align="center"><%=bean.getMobileNo()%></td>
   <td align="center"><%=bean.getGender()%></td>
   <td align="center"><%=bean.getDob()%></td>
   <td align="center"><%=bean.getLogin()%></td>
   <td><a href="UserCtl?id=<%=bean.getId()%>"
   <%if(bean.getRoleId()==RoleBean.ADMIN){ %>
   onclick="return false;"<%} %>>Edit</td>
   </tr>  
   <% }%>
    
  </table>
<%
UserModel model=new UserModel();
%>
<table width="100%">

<tr>
    <td ><input type="submit" name="operation" value="<%=UserListCtl.OP_PREVIOUS %>"
    <%=(pageNo>1)?"":"disabled"%>>
    </td>
     
    <td align="center"><input type="submit" name="operation" value="<%=UserListCtl.OP_NEW %>">
    </td>
    
    <td align="center"><input type="submit" name="operation" value="<%=UserListCtl.OP_DELETE %>">
    </td>
         
    <td align="right"><input type="submit" name="operation" value="<%=UserListCtl.OP_NEXT %>"
    <%=((list.size()<pageSize || model.nextPK()-1 == bean.getId())?"disabled":"") %>>
    
    </td>
</tr>

</table>
<input type="hidden" name= "pageNo" value="<%=pageNo%>">
  <input type="hidden" name= "pageSize" value="<%=pageSize%>">
</form>
<%} %>
  
<%@include file ="Footer.jsp" %>
</center>

</body>
</html>