<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script language="javascript" type="text/javascript" src="js/search.js"></script>
<link href="css/geotracker.css" rel="stylesheet" type="text/css">
<title>Records</title>
</head>
<%@ page import="java.util.ArrayList, java.util.Iterator" %>
<%@ page import="api.RecordAPI, models.Record" %>
<%
	Record record;
	ArrayList<Record> records;
	
	RecordAPI api = new RecordAPI();
	records = api.getAllRecords();
%>
<body>

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
						<td class="screen_title">Record history</td>
					</tr>
				</table> 
				<form action="#">
				<br/>
				<input type="hidden" id="action" name="action"/> 
				
				  <img src="img/blanc.gif" width="10" height="10">
				
				 <label for="search" id="txt">Type for search(userid or projectid)</label>  <input type="text" name="search" placeholder="search" id="search" onkeyup="Search()"><br><br><br>
				

<table align='center' cellspacing=2 cellpadding=5 id="data_table" border=1 class="data_table">
<tr>
<th>ID</th>	
<th>Description</th>
<th>Date</th>
<th>Project</th>
<th>User</th>
<th>Latitude</th>
<th>Longitude</th>
</tr>
<%
			if (records != null) {
				Iterator<Record> iterator = records.iterator();
				while (iterator.hasNext()) {
					record = (Record) iterator.next();
%>

<tr>
<td><%=record.getId() %></td>
<td><%=record.getDescription() %></td>
<td><%=record.getDate() %></td>
<td><%=record.getIdProject() %></td>
<td><%=record.getIdUser() %></td>
<td><%=record.getLatitude() %></td>
<td><%=record.getLongitude() %></td>

</tr>

<% 		} 
	}
	%>

</table>
</form>
			</td>
		</tr>
	</table>
</body>
</html>