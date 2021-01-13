<%@page import="in.co.sunrays.proj4.bean.SubjectBean"%>
<%@page import="in.co.sunrays.proj4.controller.SubjectListCtl"%>
<%@page import="java.util.Iterator"%>
<%@page import="in.co.sunrays.proj4.util.HTMLUtility"%>
<%@page import="java.util.List"%>
<%@page import="in.co.sunrays.proj4.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" />
<title>SubjectList Page</title>
<script src="<%=ORSView.APP_CONTEXT%>/js/jquery.min.js">
	
</script>
<script type="text/javascript"
	src="<%=ORSView.APP_CONTEXT%>/js/checkbox11.js"></script>

</head>
<body>
	<%@include file="Header.jsp"%>
	<jsp:useBean id="bean" class="in.co.sunrays.proj4.bean.SubjectBean"
		scope="request"></jsp:useBean>
	<jsp:useBean id="model" class="in.co.sunrays.proj4.model.Subjectmodel"
		scope="request"></jsp:useBean>
	<center>
		<h1>Subject List</h1>
		<form action="<%=ORSView.SUBJECT_LIST_CTL%>" method="post">

			<h2>
				<font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
			</h2>

			<h2>
				<font color="green"><%=ServletUtility.getSuccessMessage(request)%></font>
			</h2>

			<%
				List lc = (List) request.getAttribute("courselist");

				List ls = (List) request.getAttribute("subjectlist");
			%>

			<%
				List li = ServletUtility.getList(request);
				if (li == null || li.size() == 0) {
			%>
			<input type="submit" name="operation"
				value="<%=SubjectListCtl.OP_BACK%>">
			<%
				} else {
			%>

			<table width="100%">
				<tr align="center">
					<td><lable>Course Name</lable> <%=HTMLUtility.getList("courseId", String.valueOf(bean.getCourseId()), lc)%>
						<%-- <font color="red"><%=ServletUtility.getErrorMessage(request) %></font> --%>
						&nbsp;<lable>Subject Name</lable> <%=HTMLUtility.getList("subjectId", String.valueOf(bean.getId()), ls)%>
						<%-- <font color="red"><%=ServletUtility.getErrorMessage(request) %></font> --%>
						&nbsp;<input type="submit" name="operation"
						value="<%=SubjectListCtl.OP_SEARCH%>"> &nbsp;<input
						type="submit" name="operation"
						value="<%=SubjectListCtl.OP_RESET%>"></td>
				</tr>
			</table>

			<table width="100%" border="1">

				<tr>
					<th><input type="checkbox" id="select_all" name="ids">Select
						All</th>
					<th>SNO</th>
					<th>COURSENAME</th>
					<th>SUBJECTNAME</th>
					<th>DESCRIPTION</th>
					<th>EDIT</th>
				</tr>

				<tr>
					<%
						int pageNo = ServletUtility.getPageNo(request);
							int pageSize = ServletUtility.getPageSize(request);
							int index = ((pageNo - 1) * pageSize) + 1;

							List list = ServletUtility.getList(request);

							Iterator it = list.iterator();
							//SubjectBean bean;

							while (it.hasNext()) {
								bean = (SubjectBean) it.next();
					%>
					<td align="center"><input type="checkbox" class="checkbox"
						name="ids" value="<%=bean.getId()%>"></td>
					<td align="center"><%=index++%></td>
					<td align="center"><%=bean.getCourseName()%></td>
					<td align="center"><%=bean.getSubjectName()%></td>
					<td align="center"><%=bean.getDescription()%></td>
					<td align="center"><a href="SubjectCtl?id=<%=bean.getId()%>">Edit</a></td>

				</tr>
				<%
					}
				%>

			</table>

			<table width="100%">
				<tr>
					<td align="left"><input type="submit" name="operation"
						value="<%=SubjectListCtl.OP_PREVIOUS%>"
						<%=(pageNo == 1) ? "disabled" : ""%>></td>

					<%-- <%
   SubjectModel model = new SubjectModel();
   
 %> --%>
					<td align="center"><input type="submit" name="operation"
						value="<%=SubjectListCtl.OP_NEW%>"></td>
					<td align="center"><input type="submit" name="operation"
						value="<%=SubjectListCtl.OP_DELETE%>"></td>
					<td align="right"><input type="submit" name="operation"
						value="<%=SubjectListCtl.OP_NEXT%>"
						<%-- <%=(list.size()<pageSize || (model2.nextPk()-1==bean2.getId()))?"disabled":""%> --%>
     <%=(list.size() < pageSize || model.nextPk() - 1 == bean.getId()) ? "disabled" : ""%>>

					</td>
				</tr>
			</table>

			<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
				type="hidden" name="pageSize" value="<%=pageSize%>">
		</form>
	</center>
	<%} %>
	<%@include file="Footer.jsp"%>
</body>
<br>
<br>
<br>
<br>
</body>
</html>