<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>GeoTracker - Users</title>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<link href="css/geotracker.css" rel="stylesheet" type="text/css">
	<script language="javascript" type="text/javascript" src="js/mm_functions.js"></script>
	<script language="javascript" type="text/javascript" src="js/users.js"></script>
</head>

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
				<form action="usersServlet"></form>
				<br/>
				<input type="hidden" id="action" name="action"/> 
				<table id="search_frame" border="0" align="center" cellpadding="5"
					cellspacing="0" class="search_frame" width="75%">
					<tr>
						<td>&nbsp;</td>
						<td class="seach_label" align="left">Code:</td>
						<td class="textcerca"><input type="text" id="code"
							name="code" size="10" maxlength="10" class="search_input" /></td>
						<td class="seach_label" align="left">Name:</td>
						<td><input type="text" id="name" name="name" size="50"
							maxlength="50" class="search_input" /></td>
						<td class="seach_label">Password:</td>
						<td><input type="text" id="name2" name="name2" size="15"
							maxlength="15" class="search_input" /></td>
						<td width="8%" align="center">
							<a  onclick="return add();"
								onMouseOut="MM_swapImgRestore()" 
								onMouseOver="MM_swapImage('btnNew','','img/new_up.gif',1)">
					  <img src="img/new.gif" alt="New user" name="btnNew" width="32" height="32" border="0"></a></td>
						<td width="8%" align="center">
							<a href="#" onclick="return save();" 
								onMouseOut="MM_swapImgRestore()" 
								onMouseOver="MM_swapImage('btnSave','','img/save_up.gif',1)"><img src="img/save.gif" alt="Save user data" name="btnSave" width="32" height="32" border="0"></a></td>
					</tr>
				</table> 
				  <img src="img/blanc.gif" width="10" height="10">

				<table width="75%" cellpadding="0" cellspacing="2" align="center"
					class="data_table" id="data_table">
					<tr>
						<th width="20%" class="data_header">Code</th>
						<th class="data_header">Name</th>
						<th width="20%" class="data_header">Password</th>
						<th width="8%" class="data_header">&nbsp;</th>
						<th width="8%" class="data_header">&nbsp;</th>
					</tr>
					<tr class="data_row">
						<td class="data_col_code">1</td>
						<td class="data_col_text">Joan Josep</td>
						<td class="data_col_text">jsp394</td>
						<td align="center">
							<a href="#" onclick="return edit();"
								onMouseOut="MM_swapImgRestore()" 
								onMouseOver="MM_swapImage('btnEdit1','','img/edit16_up.gif',1)"><img src="img/edit16.gif" alt="Edit user" name="btnEdit1" width="16" height="16" border="0"></a></td>
						<td align="center">
							<a href="#" onclick="return deleteRecord();" 
								onMouseOut="MM_swapImgRestore()" 
								onMouseOver="MM_swapImage('btnDelete','','img/delete16_up.gif',1)"><img src="img/delete16.gif" alt="Delete user" name="btnDelete" width="16" height="16" border="0"></a></td>
					</tr>
					<tr class="data_row">
						<td class="data_col_code">2</td>
						<td class="data_col_text">Xavier</td>
						<td class="data_col_text">xvr630</td>
						<td align="center"><a href="#" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('btnEdit2','','img/edit32_up.gif',1)"><img src="img/edit32.gif" alt="Edit user" name="btnEdit2" width="32" height="32" border="0"></a></td>
						<td align="center"><a href="#" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('btnDelete2','','img/delete32_up.gif',1)"><img src="img/delete32.gif" alt="Delete user" name="btnDelete2" width="32" height="32" border="0"></a></td>
					</tr>
					<tr class="data_row">
						<td class="data_col_code">3</td>
						<td class="data_col_text">David</td>
						<td class="data_col_text">dvd520</td>
						<td align="center"><a href="#" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('btnEdit3','','img/edit32_up.gif',1)"><img src="img/edit32.gif" alt="Edit user" name="btnEdit3" width="32" height="32" border="0"></a></td>
						<td align="center"><a href="#" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('btnDelete3','','img/delete3_up.gif',1)"><img src="img/delete3.gif" alt="Delete user" name="btnDelete3" width="32" height="32" border="0"></a></td>
					</tr>
					<tr class="data_row">
						<td class="data_col_code">4</td>
						<td class="data_col_text">Dejan</td>
						<td class="data_col_text">djn946</td>
						<td align="center"><a href="#" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('btnEdit4','','img/edit32_up.gif',1)"><img src="img/edit32.gif" alt="Edit user" name="btnEdit4" width="32" height="32" border="0"></a></td>
						<td align="center"><a href="#" onMouseOut="MM_swapImgRestore()" onMouseOver="MM_swapImage('btnDelete4','','img/delete3_up.gif',1)"><img src="img/delete3.gif" alt="Delete user" name="btnDelete4" width="32" height="32" border="0"></a></td>
					</tr>
				</table>
				</form>
			</td>
		</tr>
	</table>

</body>
</html>