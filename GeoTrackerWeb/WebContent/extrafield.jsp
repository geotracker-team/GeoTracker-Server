<%@ page contentType="text/html; charset=iso-8859-1" language="java" import="java.sql.*" errorPage="" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>GeoTracker - Projects detail</title>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
	<link href="css/geotracker.css" rel="stylesheet" type="text/css" />
	<script language="javascript" type="text/javascript" src="js/mm_functions.js" ></script>
	<script language="javascript" type="text/javascript" src="js/project_detail.js" ></script>
</head>

<%@ page import="java.util.ArrayList, java.util.Iterator, java.util.HashMap" %>
<%@ page import="api.*, models.*" %>
<%	
	int idRecord;
	String RecordName;
	ExtraField eField;
	
	//	Get parameter id of the project
	if (request.getParameter("id") != null)
		idRecord = Integer.parseInt(request.getParameter("id"));
	else 
		idRecord = 0;
	
	
	RecordAPI recordsApi = new RecordAPI();
	Record record = recordsApi.getRecordById(idRecord);
	if (record != null)
		RecordName = record.getDescription();
	else
		RecordName = "";
	
	//	Get list of users assignets
	ExtraFieldAPI api = new ExtraFieldAPI();
	ArrayList<ExtraField> efields = api.getAllExtraFieldsById(idRecord);
	
%>
<body onload="MM_preloadImages('img/edit24_up.gif','img/save24_up.gif','img/delete24_up.gif')">
	<table id="title_frame" width="100%" border="0" align="center"
		cellpadding="2" cellspacing="1">
		<tr>
			<td class="screen_title">Record details</td>
		</tr>
	</table> 
	<form action="AssignedServlet" method="post" id="form_data" name="form_data">
		<input type="hidden" id="action" name="action" value="" /> 
		<br/>
		<table width="75%" border="0" align="center">
			<tr>
				<td>
					<table width="100%" cellpadding="10" cellspacing="0" class="search_frame">
						<tr >
							<td width="10%" nowrap="nowrap" class="seach_label">Record name:</td>
							<td nowrap="nowrap" class="search_input"><b><%=RecordName %></b></td>
					  </tr>
				  </table>
				</td>
			</tr>
			<tr><td>&nbsp;</td></tr>
			<tr>
				<td>
				<table align='center' cellspacing="2" cellpadding="5" id="data_table" border="0" class="data_table" width="100%">
					<tr>
						<th>Type</th>
						<th>Title</th>
						<th>Value</th>
					</tr>		
<%
			if (efields != null) {
				Iterator<ExtraField> iterator = efields.iterator();
				while (iterator.hasNext()) {
					eField = (ExtraField) iterator.next();
%>
					<tr>
						<td><%=eField.getType() %></td>
						<td><% if (eField.getTitle() != null) 
								out.println(eField.getTitle());	%>						
						</td>
						<td><%=eField.getValue() %></td>
					</tr>
<% 		} 
	}
	%>					
					<tr>
				  </tr>
				  
				</table>
				</td>
			</tr>
	  </table>
		
	
		<p>&nbsp;</p>
	</form>
</body>
</html>
