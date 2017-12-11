<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>GeoTracker - Users</title>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<link href="css/geotracker.css" rel="stylesheet" type="text/css">
	<script language="javascript" type="text/javascript" src="js/mm_functions.js"></script>
	<script language="javascript" type="text/javascript" src="js/users.js"></script>
</head>
<%@ page import="java.util.ArrayList, java.util.Iterator" %>
<%@ page import="api.UserAPI, models.User" %>
<%
	User user;
	ArrayList<User> users;
	
	UserAPI api = new UserAPI();
	users = api.getAllUsers();
//	users = null;
%>
<body leftmargin="0" topmargin="0" onLoad="MM_preloadImages('img/new.gif','img/new_up.gif','img/save.gif','img/save_up.gif','img/delete16_up.gif','img/delete32.gif','img/delete32_up.gif','img/edit16.gif','img/edit16_up.gif','img/edit32.gif','img/edit32_up.gif')">
	<table width="100%" border="0" cellpadding="0" cellspacing="0"
		bgcolor="#660033">
		<tr>
			<td colspan="2" nowrap><%@ include file="capcelera.jsp"%>
			</td>
		</tr>
		<tr>
			<td width="20%" valign="top" class="menu_panel">
				<img src="img/blanc.gif" width="10" height="26"> 
				<%@ include file="menu.jsp" %>
				<p>&nbsp;</p>
			</td>
			<td valign="top" bgcolor="#FFFFFF">
			
				<table id="title_frame" width="100%" border="0" align="center"
					cellpadding="2" cellspacing="1">
					<tr>
						<td class="screen_title">Users</td>
					</tr>
				</table> 
				<form action="UserServlet">
				<br/>
				<input type="hidden" id="action" name="action"/> 
				
				  <img src="img/blanc.gif" width="10" height="10">
				
				
				


				
					<table align='center' cellspacing=2 cellpadding=5 id="data_table" border=1 class="data_table">
<tr>
<th>Code</th>
<th>Name</th>
<th>Password</th>
</tr>
<%
			if (users != null) {
				Iterator<User> iterator = users.iterator();
				int row = 0;
				while (iterator.hasNext()) {
					user = (User) iterator.next();
					row = row + 1;
%>

<tr id="row">
<td class="data_col_code" id="codeRow<%=user.getId() %>"><%=user.getId() %></td>
<td class="data_col_code" id="nameRow<%=user.getName() %>"><%=user.getName() %></td>
<td class="data_col_code" id="passRow<%=user.getPassword() %>"><%=user.getPassword() %></td>
<td><img src="img/edit.png" width="20" height="20" onclick="edit_row(1)"></td>
<td><img src="img/save.png" width="20" height="20" onclick="save_row(1)"></td>
<td><img src="img/delete.png" width="20" height="20" onclick="delete_row(1)"></td>
</tr>

<% 		} 
	}
	%>
<tr>
<td><input type="text" id="new_code"></td>
<td><input type="text" id="new_name"></td>
<td><input type="text" id="new_pass"></td>
<td><img src="img/row.png" class="add" onclick="add_row();" width="20" height="20"></td>
</tr>
</table>
				</form>
			</td>
		</tr>
	</table>

</body>
</html>