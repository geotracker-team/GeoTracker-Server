<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>GeoTracker - Projects</title>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<link href="css/geotracker.css" rel="stylesheet" type="text/css">
	<script language="javascript" type="text/javascript" src="js/mm_functions.js"></script>
	<script language="javascript" type="text/javascript" src="js/users.js"></script>
</head>
<%@ page import="java.util.ArrayList, java.util.Iterator" %>
<%@ page import="api.API, models.Project" %>
<%
	Project project;
	ArrayList<Project> projects;
	
	API api = new API();
//	projects = api.getAllProjects(1);
	projects = null;
%>
<body leftmargin="0" topmargin="0" onLoad="MM_preloadImages('img/new.gif','img/new_up.gif','img/save.gif','img/save_up.gif','img/delete16_up.gif','img/delete32.gif','img/delete32_up.gif','img/edit24.gif','img/edit24_up.gif','img/edit32.gif','img/edit32_up.gif')">
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
						<td class="screen_title">Projects</td>
					</tr>
				</table> 
				<form action="projectsServlet"></form>
				<input type="hidden" id="action" name="action"/> 

				<table width="75%" cellpadding="0" cellspacing="2" align="center"
					class="data_table" id="data_table">
					<tr>
						<th width="20%" class="data_header">Code</th>
						<th class="data_header">Name</th>
						<th class="data_header">Company</th>
						<th width="8%" class="data_header">&nbsp;</th>
						<th width="8%" class="data_header">&nbsp;</th>
					</tr>
<%
			if (projects != null) {
				Iterator<Project> iterator = projects.iterator();
				while (iterator.hasNext()) {
					project = (Project) iterator.next();
%>
					<tr class="data_row">
						<td class="data_col_code"><%=project.getId() %></td>
						<td class="data_col_text"><%=project.getName() %></td>
						<td class="data_col_text"><%=project.getIdCompany() %></td>
						<td align="center">
							<a href="#" onClick="return edit();"
								onMouseOut="MM_swapImgRestore()" 
								onMouseOver="MM_swapImage('btnEdit1','','img/edit24_up.gif',1)"><img src="img/edit24.gif" alt="Edit user" name="btnEdit1" width="24" height="24" border="0"></a>
								&nbsp;
								<a href="#" onClick="return save();" 
								onMouseOut="MM_swapImgRestore()" style="visibility:hidden"
								onMouseOver="MM_swapImage('btnSave','','img/save24_up.gif',1)"><img src="img/save24.gif" alt="Save user data" name="btnSave" width="24" height="24" border="0"></a></td>
						<td align="center">
							<a href="#" onClick="return deleteRecord();" 
								onMouseOut="MM_swapImgRestore()" 
								onMouseOver="MM_swapImage('btnDelete','','img/delete24_up.gif',1)"><img src="img/delete24.gif" alt="Delete user" name="btnDelete" width="24" height="24" border="0"></a></td>
					</tr>
<% 		} 
	}
	%>					
					<tr class="data_row">
						<td class="data_col_code">2</td>
						<td class="data_col_text">Mushroom predictor </td>
						<td class="data_col_text">UdL</td>
						<td align="center">
							<a href="#" onMouseOut="MM_swapImgRestore()" 
								onMouseOver="MM_swapImage('btnEdit2','','img/edit24_up.gif',1)">
								<img src="img/edit24.gif" alt="Edit user" name="btnEdit2" width="24" height="24" border="0"></a>						</td>
						<td align="center">
							<a href="#" onMouseOut="MM_swapImgRestore()" 
								onMouseOver="MM_swapImage('btnDelete2','','img/delete24_up.gif',1)"><img src="img/delete24.gif" alt="Delete user" name="btnDelete2" width="24" height="24" border="0"></a></td>
					</tr>
					<tr class="data_row">
						<td class="data_col_code">3</td>
						<td class="data_col_text">Excursion tracker </td>
						<td class="data_col_text">UdL</td>
						<td align="center">
							<a href="#" onMouseOut="MM_swapImgRestore()" 
								onMouseOver="MM_swapImage('btnEdit3','','img/edit24_up.gif',1)"><img src="img/edit24.gif" alt="Edit user" name="btnEdit3" width="24" height="24" border="0"></a></td>
						<td align="center">
							<a href="#" onMouseOut="MM_swapImgRestore()" 
								onMouseOver="MM_swapImage('btnDelete3','','img/delete24_up.gif',1)"><img src="img/delete24.gif" alt="Delete user" name="btnDelete3" width="24" height="24" border="0"></a></td>
					</tr>
					<tr class="data_row">
						<td class="data_col_code">4</td>
						<td class="data_col_text">EPS project tracker </td>
						<td class="data_col_text">UdL</td>
						<td align="center">
							<a href="#" onMouseOut="MM_swapImgRestore()" 
								onMouseOver="MM_swapImage('btnEdit4','','img/edit24_up.gif',1)"><img src="img/edit24.gif" alt="Edit user" name="btnEdit4" width="24" height="24" border="0"></a></td>
						<td align="center">
							<a href="#" onMouseOut="MM_swapImgRestore()" 
								onMouseOver="MM_swapImage('btnDelete4','','img/delete24_up.gif',1)"><img src="img/delete24.gif" alt="Delete user" name="btnDelete4" width="24" height="24" border="0"></a></td>
					</tr>
					<tr class="data_row">
					  <td class="data_col_code"><span class="textcerca">
					    <input type="text" id="code"
							name="code" size="10" maxlength="10" class="search_input" />
					  </span></td>
					  <td class="data_col_text"><span class="textcerca">
					    <input type="text" id="name" name="name" size="40"
							maxlength="50" class="search_input" />
					  </span></td>
					  <td class="data_col_text"><span class="textcerca">
					    <select id="company" name="company" style="width:200px">
                          <option value="0" selected>(Select company)</option>
                          <option value="1">Company 1</option>
                          <option value="2">Company 2</option>
                          <option value="3">UdL</option>
                        </select>
					  </span></td>
					  <td align="center"><a href="#"  onClick="return add();"
								onMouseOut="MM_swapImgRestore()" 
								onMouseOver="MM_swapImage('btnNew','','img/new24_up.gif',1)"><img src="img/new24.gif" alt="New user" name="btnNew" width="24" height="24" border="0"></a></td>
					  <td align="center">&nbsp;</td>
				  </tr>
			  </table>
				</form>
			</td>
		</tr>
	</table>

</body>
</html>