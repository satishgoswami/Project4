<%@page import="in.co.sunrays.proj4.util.HTMLUtility"%>
<%@page import="in.co.sunrays.proj4.controller.RoleListCtl"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="in.co.sunrays.proj4.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" />
<title>Role list Page</title>
<script src="<%=ORSView.APP_CONTEXT%>/js/jquery.min.js">
	
</script>
<script type="text/javascript"
	src="<%=ORSView.APP_CONTEXT%>/js/checkbox11.js"></script>


</head>


<html>
<body>
<%@include file="Header.jsp"%>
<jsp:useBean id="bean" class="in.co.sunrays.proj4.bean.RoleBean" scope="request"></jsp:useBean>
<center>
<h1>Role list</h1>

 <h2>
<font color="red"><%=ServletUtility.getErrorMessage(request) %></font>
</h2>

<h2>
<font color="green"><%=ServletUtility.getSuccessMessage(request) %></font>
</h2>

<form action="<%=ORSView.ROLE_LIST_CTL %>" method="post">

<%
List li = ServletUtility.getList(request);
List rlist=(List)request.getAttribute("rlist");
if(li==null || li.size()==0){
%>
<input type="submit" name= "operation" value="<%=RoleListCtl.OP_BACK%>">
<%}else{ %>

<table width="100%" >
<tr align="center">
    <td>Name:
   <%=HTMLUtility.getList("rname",String.valueOf(bean.getId()), rlist) %>
   <%--  <input type="text" name = "name" placeholder="Enter Role Name" value="<%=ServletUtility.getParameter("name", request) %>">
    --%>&nbsp;<input type="submit" name="operation" value="<%=RoleListCtl.OP_SEARCH %>"> 
   &nbsp;<input type="submit" name="operation" value="<%=RoleListCtl.OP_RESET %>">
   </td>
 </tr>  
</table>

<table width="100%" border="1" >
  
  <tr >
      <th><input type="checkbox" id="select_all" name="ids">Select All</th>
      <th>SNO</th>
      <th>NAME</th>
      <th>DESCRIPTION</th>
      <th>EDIT</th>
</tr>

   


  <%
  int pageNo = ServletUtility.getPageNo(request);
  int pageSize = ServletUtility.getPageSize(request);
  int index= ((pageNo-1)*pageSize)+1;
  
  List list= ServletUtility.getList(request);
  
  Iterator<RoleBean> it =list.iterator();
  
  
  while(it.hasNext()){
	  RoleBean bean1;
	  bean1 = it.next();
	  
  %>
<tr>
    <td align="center"><input type="checkbox" class="checkbox" name="ids" value="<%=bean1.getId() %>"
    <%=(bean.getId()==1)?"disabled":"" %>></td>
    <td align="center"><%=index++%></td>
    <td align="center"><%=bean1.getName()%></td>
    <td align="center"><%=bean1.getDescription()%></td>
    <td align="center"><a href="RoleCtl?id=<%=bean1.getId()%>"
    >
  Edit</td>
</tr>
<%} %>
</table>

<table width="100%" >
<tr>
   <td align="left"><input type="submit" name = "operation" value="<%=RoleListCtl.OP_PREVIOUS %>" <%=(pageNo==1)?"disabled":"" %>></td>
   <td align="center"><input type="submit" name = "operation" value="<%=RoleListCtl.OP_NEW %>"></td>
   <td align="center"><input type="submit" name = "operation" value="<%=RoleListCtl.OP_DELETE%>"></td>
   <td align="right"><input type="submit" name = "operation" value="<%=RoleListCtl.OP_NEXT %>" <%=(list.size()<pageSize)?"disabled":"" %>>
  </td>
 </tr>  
</table>
</form>
</center>
<%} %>
<%@include file="Footer.jsp" %>
</body>
</html>