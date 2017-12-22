<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>GeoTracker - Users</title>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<link href="css/geotracker.css" rel="stylesheet" type="text/css">
	<script language="javascript" type="text/javascript" src="js/mm_functions.js"></script>
	<script language="javascript" type="text/javascript" src="js/users.js"></script>
</head>
<%@ page import="java.util.ArrayList, java.util.Iterator, java.util.HashMap" %>
<%@ page import="api.*, models.*" %>
<%
	User user;
	ArrayList<User> users;
	Company company;
	UserAPI api = new UserAPI();
	users = api.getAllUsers();
	CompanysAPI companysAPI = new CompanysAPI();
	ArrayList<Company> companys = companysAPI.getAllCompanies();
	
	HashMap<Integer, Company> companysMap = new HashMap<Integer, Company>();
	for (Company c: companys) {
		companysMap.put(new Integer(c.getId()), c);
	}	
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
				<form action="UserServlet" id="form_data">
				<br/>
				<input type="hidden" id="action" name="action"/> 
				<br>
				
<table align="center" cellspacing="2" cellpadding="5" id="data_table" class="data_table" width="75%">
<tr>

<th>Code</th>
<th>Name</th>
<th>Password</th>
<th>Type (isManager)</th>
<th>Company</th>
          
</tr>
<%
			if (users != null) {
				Iterator<User> iterator = users.iterator();
				while (iterator.hasNext()) {
					user = (User) iterator.next();
					company = (Company) companysMap.get(new Integer(user.getIdCompany()));
%>

<tr class="data_row">
						<td class="data_col_code" id="codeRow<%=user.getId() %>"><%=user.getId() %></td>
						<td class="data_col_code" id="nameRow<%=user.getId() %>"><%=user.getName() %></td>
						<td class="data_col_code" id="passRow<%=user.getId() %>"><%=user.getPassword() %></td>
						<td class="data_col_code" id="boolRow<%=user.getId() %>">
						<% if(user.IsManager() == true){ %>
						<input type="checkbox" id="bool<%=user.getId() %>"  checked disabled>
						<% } %>
						<% if(user.IsManager() == false) { %>
						<input type="checkbox" id="bool<%=user.getId() %>"  disabled>
						<% } %>
						</td>
						<td class="data_col_code" id="companyRow<%=user.getId() %>">
							<input type="hidden" id="company<%=user.getId() %>" value="<%=user.getIdCompany() %>" />						
<%							if (company != null) 
								out.println(company.getName()); 
%>
						</td>
						
						<td align="center" nowrap="nowrap" class="data_col_code">
							<a  onClick="return edit(<%=user.getId() %>);" id="btnEdit<%=user.getId() %>" name="btnEdit<%=user.getId() %>"
								onMouseOut="MM_swapImgRestore()" style="display: block; cursor: pointer;"
								onMouseOver="MM_swapImage('btnEditImg<%=user.getId() %>','','img/edit24_up.gif',1)">
								<img src="img/edit24.gif" alt="Edit user" name="btnEditImg<%=user.getId() %>" id="btnEditImg<%=user.getId() %>" width="24" height="24" border="0"></a>
								
							<a onClick="return save(<%=user.getId() %>);" id="btnSave<%=user.getId() %>" name="btnSave<%=user.getId() %>"
								onMouseOut="MM_swapImgRestore()" style="display: none; cursor: pointer;"
								onMouseOver="MM_swapImage('btnSaveImg<%=user.getId() %>','','img/save24_up.gif',1)">
								<img src="img/save24.gif" alt="Save user data" name="btnSaveImg<%=user.getId() %>" id="btnSaveImg<%=user.getId() %>" width="24" height="24" border="0"></a>
						</td>
						<td align="center" class="data_col_code">
							<a  onClick="return deleteUser(<%=user.getId() %>);" 
								onMouseOut="MM_swapImgRestore()" style="cursor: pointer;"
								onMouseOver="MM_swapImage('btnDelete<%=user.getId() %>','','img/delete24_up.gif',1)">
								<img src="img/delete24.gif" alt="Delete user" id="btnDelete<%=user.getId() %>" name="btnDelete<%=user.getId() %>" width="24" height="24" border="0"></a>
						</td>
					</tr>

<% 		} 
	}
	%>
<tr class="data_row">	
						<td class="data_col_code" id="codeAddRow">&nbsp;
							<input type="hidden" id="code" name="code" value="" size="10" maxlength="20" />
						</td>
						<td class="data_col_code" id="nameAddRow">
							<input type="text" id="name" name="name" value="" size="50" maxlength="255" />
						</td>
						<td class="data_col_code" id="passwordAddRow">
							<input type="text" id="pass" name="pass" value="" size="50" maxlength="255" />
						</td>
						<td class="data_col_code" id="boolAddRow">
							<input type="checkbox" id="bool" name="bool" />
						</td>
						<td class="data_col_code" id="companyAddRow">
							<select id="company" name="company" style="width:200px">
	                          <option value="0" selected>(Select company)</option>
<%							  	for (Company c: companys) {	%>
							  		<option value="<%=c.getId() %>"><%=c.getName() %></option>
<%							  } %>	                          
	                        </select>						
	                    </td>	
	                    
					  <td align="center" class="data_col_code"><a onClick="return add();" style="cursor: pointer;"
								onMouseOut="MM_swapImgRestore()" 
								onMouseOver="MM_swapImage('btnNew','','img/new24_up.gif',1)"><img src="img/new24.gif" alt="New user" name="btnNew" width="24" height="24" border="0"></a></td>
					  <td align="center" class="data_col_code">&nbsp;</td>
				  </tr>
			  </table>
			  </form>
			</td>
		</tr>
	</table>

</body>
</html>
