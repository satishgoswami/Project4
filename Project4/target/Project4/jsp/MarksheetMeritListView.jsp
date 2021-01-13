<%@page import="in.co.sunrays.proj4.controller.MarksheetMeritListCtl"%>
<%@page import="in.co.sunrays.proj4.bean.MarksheetBean"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="in.co.sunrays.proj4.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<html>
<head>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" />
<title>MarksheetMeritList Page</title>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="Header.jsp"%>

	<jsp:useBean id="bean" class="in.co.sunrays.proj4.bean.MarksheetBean"
		scope="request"></jsp:useBean>
	<center>
		<h1>MarksheetMeritList</h1>
		<h2>
			<font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
			</td>
		</h2>

		<form action="<%=ORSView.MARKSHEET_MERIT_LIST_CTL%>" method="post">

			<table border="1px" width="100%">

				<tr>

					<th>SNO</th>
					<th>Roll No</th>
					<th>Name</th>
					<th>Physics</th>
					<th>Chemistry</th>
					<th>Maths</th>
					<th>Total</th>
					<th>Percentage(%)</th>
				</tr>


				<%
					int pageNo = (int) ServletUtility.getPageNo(request);

					int pageSize = (int) ServletUtility.getPageSize(request);
					int index = ((pageNo - 1) * pageSize) + 1;

					//System.out.println(pageNo+pageSize);
					/* List<MarksheetBean> list = (List<MarksheetBean>)request.getAttribute("list"); */
					List list = ServletUtility.getList(request);
					Iterator it = list.iterator();
					while (it.hasNext()) {

						bean = new MarksheetBean();
						bean = (MarksheetBean) it.next();

						long id = bean.getId();

						String roll = bean.getRollNo();
						String name = bean.getName();

						System.out.println("name is----->" + name);

						int phy = bean.getPhysics();
						int che = bean.getChemistry();
						int mat = bean.getMaths();
				%>
				<tr>
					<td align="center"><%=index++%></td>
					<td align="center"><%=roll%></td>
					<td align="center"><%=name%></td>
					<td align="center"><%=bean.getPhysics()%></td>
					<td align="center"><%=bean.getChemistry()%></td>
					<td align="center"><%=bean.getMaths()%></td>
					<td align="center"><%=(bean.getPhysics() + bean.getChemistry() + bean.getMaths())%></td>
					<td align="center"><%=(bean.getPhysics() + bean.getChemistry() + bean.getMaths()) / 3%>%</td>
				</tr>
				<%
					}
				%>
			</table>
			<table>
				<tr>
					<td><input type="submit" name="operation"
						value="<%=MarksheetMeritListCtl.OP_BACK%>"></td>
				</tr>

			</table>

			<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
				type="hidden" name="pageSize" value="<%=pageSize%>">
		</form>
	</center>
	<%@include file="Footer.jsp"%>
</body>
</html>