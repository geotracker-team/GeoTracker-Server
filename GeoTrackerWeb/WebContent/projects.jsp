<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>GeoTracker - Projects</title>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<link href="css/geotracker.css" rel="stylesheet" type="text/css">
	<script language="javascript" type="text/javascript" src="js/mm_functions.js"></script>
	<script language="javascript" type="text/javascript" src="js/projects.js"></script>
</head>
<%@ page import="java.util.ArrayList, java.util.Iterator, java.util.HashMap" %>
<%@ page import="api.*, models.*" %>
<%
	if((session == null) || (session.getAttribute("username") == null)) {			
	    	response.sendRedirect("login.jsp");
	}	

	Project project;
	Company company;
	
	ProjectsAPI api = new ProjectsAPI();
	ArrayList<Project> projects = api.getAllProjects();
	
	CompanysAPI companysAPI = new CompanysAPI();
	ArrayList<Company> companys = companysAPI.getAllCompanies();
	
	HashMap<Integer, Company> companysMap = new HashMap<Integer, Company>();
	for (Company c: companys) {
		companysMap.put(new Integer(c.getId()), c);
	}	
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
				<form action="ProjectsServlet" method="post" id="form_data" name="form_data">
				<input type="hidden" id="action" name="action" value="" /> 
				<br/>
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
					company = (Company) companysMap.get(new Integer(project.getIdCompany()));
					
%>

					<tr class="data_row">
						<td class="data_col_code" id="codeRow<%=project.getId() %>"><%=project.getId() %></td>
						<td class="data_col_text" id="nameRow<%=project.getId() %>"><%=project.getName() %></td>
						<td class="data_col_text" id="companyRow<%=project.getId() %>">
							<input type="hidden" id="company<%=project.getId() %>" value="<%=project.getIdCompany() %>" />						
<%							if (company != null) 
								out.println(company.getName()); 
%>
						</td>
						<td align="center" nowrap="nowrap">
							<a  onClick="return edit(<%=project.getId() %>);" id="btnEdit<%=project.getId() %>" name="btnEdit<%=project.getId() %>"
								onMouseOut="MM_swapImgRestore()" style="display: block; cursor: pointer;"
								onMouseOver="MM_swapImage('btnEditImg<%=project.getId() %>','','img/edit24_up.gif',1)">
								<img src="img/edit24.gif" alt="Edit user" name="btnEditImg<%=project.getId() %>" id="btnEditImg<%=project.getId() %>" width="24" height="24" border="0"></a>
								
							<a onClick="return save(<%=project.getId() %>);" id="btnSave<%=project.getId() %>" name="btnSave<%=project.getId() %>"
								onMouseOut="MM_swapImgRestore()" style="display: none; cursor: pointer;"
								onMouseOver="MM_swapImage('btnSaveImg<%=project.getId() %>','','img/save24_up.gif',1)">
								<img src="img/save24.gif" alt="Save user data" name="btnSaveImg<%=project.getId() %>" id="btnSaveImg<%=project.getId() %>" width="24" height="24" border="0"></a>
						</td>
						<td align="center">
							<a  onClick="return deleteProject(<%=project.getId() %>);" 
								onMouseOut="MM_swapImgRestore()" style="cursor: pointer;"
								onMouseOver="MM_swapImage('btnDelete<%=project.getId() %>','','img/delete24_up.gif',1)">
								<img src="img/delete24.gif" alt="Delete user" id="btnDelete<%=project.getId() %>" name="btnDelete<%=project.getId() %>" width="24" height="24" border="0"></a>
						</td>
					</tr>
<% 		} 
	}
	%>
										
					<tr class="data_row">	
						<td class="data_col_code" id="codeAddRow">&nbsp;
							<input type="hidden" id="code" name="code" value="" size="10" maxlength="20" />
						</td>
						<td class="data_col_text" id="nameAddRow">
							<input type="text" id="name" name="name" value="" size="50" maxlength="255" />
						</td>
						<td class="data_col_text" id="companyAddRow">
							<select id="company" name="company" style="width:200px">
	                          <option value="0" selected>(Select company)</option>
<%							  	for (Company c: companys) {	%>
							  		<option value="<%=c.getId() %>"><%=c.getName() %></option>
<%							  } %>	                          
	                        </select>						
	                    </td>	
					  <td align="center"><a onClick="return add();" style="cursor: pointer;"
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