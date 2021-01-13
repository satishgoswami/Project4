<%@page import="in.co.sunrays.proj4.bean.CourseBean"%>
<%@page import="in.co.sunrays.proj4.model.CourseModel"%>
<%@page import="java.util.Iterator"%>
<%@page import="in.co.sunrays.proj4.controller.CourseListCtl"%>
<%@page import="in.co.sunrays.proj4.util.HTMLUtility"%>
<%@page import="java.util.List"%>
<%@page import="in.co.sunrays.proj4.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<link rel="icon" type="image/png" href="<%=ORSView.APP_CONTEXT%>/img/logo.png" />
<title>CourseList View</title>
<script  src="<%=ORSView.APP_CONTEXT%>/js/jquery.min.js">
	
</script>	
<script type="text/javascript" src="<%=ORSView.APP_CONTEXT%>/js/checkbox11.js"></script>

</head>
<body>

<%-- <%CourseBean cBean=(CourseBean)request.getAttribute("cbean"); 
 System.out.println(cBean);%> --%>
<jsp:useBean id="cbean" class="in.co.sunrays.proj4.bean.CourseBean" scope="request"></jsp:useBean>
<%@include file="Header.jsp" %>

<center>
  <h1>Course List</h1>

        <h2>
        <font color="red"><%=ServletUtility.getErrorMessage(request) %></font>
      </h2>
  
      <h2>
        <font color="green"><%=ServletUtility.getSuccessMessage(request) %></font>
      </h2>
  <%-- <%System.out.println("id is   "+cBean.getId()); %> --%>
<form action="<%=ORSView.COURSE_LIST_CTL%>" method="post">

<% List l= ServletUtility.getList(request); 
List clist=(List)request.getAttribute("clist");
if(l==null || l.size()==0)
{%>
<input type="submit" name="operation" value="<%=CourseListCtl.OP_BACK%>">	
<%}
else{ %>
<table>
<tr>
    <td align="center"><label>Course Name</label> &nbsp;
    <%=HTMLUtility.getList("CourseName", String.valueOf((cbean!=null)?cbean.getId():0), clist) %>
    <%-- <input type="text" name="CourseName" placeholder="Enter Course Name" value="<%=ServletUtility.getParameter("CourseName", request) %>">
     --%>&nbsp;
<input type="submit" name="operation" value="<%=CourseListCtl.OP_SEARCH %>">
   <input type="submit" name="operation" value="<%=CourseListCtl.OP_RESET %>">
   </td>
   </tr> 
</table>

<table width="100%" border="1px">
<tr>
<th > <input type="checkbox" name="ids" id="select_all">Select All</th>
	<th>S.NO</th>
     <th>COURSE NAME</th>
     <th>DURATION</th>
     <th>DESCRIPTION</th>
     <th>EDIT</th>
</tr>
<%
 int pageNo=ServletUtility.getPageNo(request);
int pageSize=ServletUtility.getPageSize(request);
int index=(pageNo-1)*pageSize+1;

List list=ServletUtility.getList(request);

Iterator it=list.iterator();
CourseBean bean=null;
while(it.hasNext()){
	bean =(CourseBean) it.next();
	

%>
<tr>
<td align="center"><input type="checkbox" class="checkbox" name="ids" value="<%=bean.getId()%>">
</td>
<td align="center"><%=index++%></td>
   <td align="center"><%=bean.getCourseName()%></td>
   <td align="center"><%=bean.getDuration()%></td>
   <td align="center"><%=bean.getDescription()%></td>
   <td align="center"><a href="CourseCtl?id=<%=bean.getId()%>">Edit</td>

</tr>
<%} %>
</table>

<%
CourseModel model=new CourseModel(); %>
<table width="100%">
<tr>
<td align="left"><input type="submit" name="operation" value="<%=CourseListCtl.OP_PREVIOUS%>" 
<%=(pageNo>1)?"":"disabled" %>>
<td> <input type="submit" name="operation" value="<%=CourseListCtl.OP_NEW%>"></td> 
 <td><input type="submit" name="operation" value="<%=CourseListCtl.OP_DELETE%>" ></td>
<td align="right"><input type="submit" name="operation" value="<%=CourseListCtl.OP_NEXT%>" 
<%=(list.size()>pageSize || model.nextPk()-1!=bean.getId())?"":"disabled" %>> 
</td></tr>
</table>
<input type="hidden" name= "pageNo" value="<%=pageNo%>">
  <input type="hidden" name= "pagesize" value="<%=pageSize%>">
</form>

<%} %>

</form>
<%@include file ="Footer.jsp" %>

</body>
</html>