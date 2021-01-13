<%@page import="in.co.sunrays.proj4.controller.*"%>
<%@page import="in.co.sunrays.proj4.util.*"%>
<%@page import="in.co.sunrays.proj4.bean.*"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>

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
	
	<form action="<%=ORSView.USER_CTL%>" method="post">
		<%@ include file="Header.jsp"%>
		<script type="text/javascript" src="../js/calendar.js"></script>

		<jsp:useBean id="bean" class="in.co.sunrays.proj4.bean.UserBean"
			scope="request"></jsp:useBean>

		<%
            List lt = (List) request.getAttribute("role_list");
        %>

		<center>
			<%
		if(bean.getId()>0 && bean!=null){
		%><h1>Update User</h1>
			<%} else{
			%><h1>Add User</h1>
			<% }%>



			<H2>
				<font color="red"> <%=ServletUtility.getErrorMessage(request)%>
				</font>
			</H2>

			<H2>
				<font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
				</font>
			</H2>



			<input type="hidden" name="id" value="<%=bean.getId()%>"> <input
				type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
			<input type="hidden" name="modifiedBy"
				value="<%=bean.getModifiedBy()%>"> <input type="hidden"
				name="createdDatetime"
				value="<%=DataUtility.getTimestamp(bean.getCreateDatetime())%>">
			<input type="hidden" name="modifiedDatetime"
				value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">


			<table>
				<tr>
					<th align="left">First Name<font color="red">*</th>
					<td><input type="text" name="firstName" placeholder="Enter First Name"
						value="<%=DataUtility.getStringData(bean.getFirstName())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("firstName", request)%></font></td>
				</tr>
				<tr>
					<th align="left">Last Name<font color="red">*</th>
					<td  ><input type="text"
						name="lastName" placeholder="Enter Last Name"
						value="<%=DataUtility.getStringData(bean.getLastName())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("lastName", request)%></font></td>
				</tr>
				<tr>
					<th align="left">LoginId<font color="red">*</th>
					<td ><input type="text" name="login" placeholder="Enter Login Id"
						value="<%=DataUtility.getStringData(bean.getLogin())%>"
						<%=(bean.getId() > 0) ? "readonly" : ""%>></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("login", request)%></font></td>
				</tr>
	<input type="hidden" value="<%=HTMLUtility.getList("roleName",String.valueOf(bean.getRoleId()),lt)%>">
     			
				<%if(bean.getId()>0 && bean!=null){ %>
     
     <input type="hidden" name="password" value="<%=DataUtility.getStringData(bean.getPassword()) %>">
     <input type="hidden" name="confirmPassword" value="<%=DataUtility.getStringData(bean.getConfirmPassword()) %>">
    
     
     <%}else{ %>
				<tr>
					<th align="left">Password<font color="red">*</th>
					<td  ><input type="password"
						name="password" placeholder="Enter Password"
						value="<%=DataUtility.getStringData(bean.getPassword())%>">
					</td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("password", request)%></font></td>
				</tr>
				<tr>
					<th align="left">Confirm Password<font
							color="red">*</th>
					<td ><input type="password" name="confirmPassword" placeholder="Enter Confirm Password"
						value="<%=DataUtility.getStringData(bean.getPassword())%>">
					</td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("confirmPassword",
                    request)%></font></td>
				</tr>
				
				<%} %>
				<tr>
					<th align="left">Mobile Number<font
							color="red">*</font></th>
					<td ><input type="text" name="mobile" placeholder="Enter Mobile Number"
						value="<%=DataUtility.getStringData(bean.getMobileNo())%>">
					</td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("mobile",
                    request)%></font></td>
				</tr>
				
<tr>
       <th align="left">Gender<span style="color: red">*</span></th>
       <td>						<%
                            HashMap map = new HashMap();
                            map.put("M", "Male");
                            map.put("F", "Female");

                            String htmlList = HTMLUtility.getList("gender", bean.getGender(),map);
                        %> <%=htmlList%>
			   </td>
        <td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("gender",request) %></font>
      </td>
      </tr> 
     				<tr>
					<th align="left">Role :<font color="red">*</font></th>
					<td  ><%=HTMLUtility.getList("roleId",String.valueOf(bean.getRoleId()), lt)%>
					</td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("role",request) %></font>
					</td>

				</tr>
				<tr>
				<th align="left">Date Of Birth (dd/mm/yyyy)<font
							color="red">*</font></th>
					<td><input type="text" name="dob" placeholder="Enter Date of Birth"
						readonly="readonly" id="dob"
						value="<%=DataUtility.getDateString(bean.getDob())%>"> </a></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("dob1", request)%></font></td>
				</tr>
				<tr>
					<th align="left">Address<span style="color: red">*</span></th>
					<td><input type="text" name="address"
						placeholder="Enter Address"
						value="<%= DataUtility.getStringData(bean.getAddress())%>">
				</td>
				<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("address", request) %></font>
					</td>
				</tr>
				<%
      if(bean.getId()>0){
      %>

				<tr>
					<th></th>
					<td><input type="submit" name="operation"
						value="<%=UserCtl.OP_UPDATE%>"> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; <input type="submit"
						name="operation" value="<%=UserCtl.OP_CANCEL%>"> <%}else{ %>
				<tr>
					<th></th>
					<td><input type="submit" name="operation"
						value="<%=UserCtl.OP_SAVE%>"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="submit"
						name="operation" value="<%=UserCtl.OP_RESET%>"></td>
				</tr>
				<%} %>
			</table>
	</form>
	</center>
	<%@ include file="Footer.jsp"%>
</body>
</html>