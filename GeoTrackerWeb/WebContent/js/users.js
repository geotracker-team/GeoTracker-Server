function add() {
//	alert("add");
	if (validateAdd()) {
		document.getElementById("action").value = "add";
		document.getElementById("form_data").submit();	
		return true;
	} else {
		return false;
	}
}
function validateAdd()
{
	result = true;
	if (document.getElementById("new_name").value == "") {
		alert("User name is required");
		result = false;
	} else {
		if (document.getElementById("new_pass").value == "") {
			alert("User password is required");
			result = false;
		}
	}	
	return result;
}
function deleteProject(id) {
	alert("Delete");
	if (validateDelete()) {
		document.getElementById("action").value = "delete";
		document.getElementById("new_code").value = id;
		document.getElementById("form_data").submit();	
		//document.getElementById("row"+id+"").outerHTML="";
		return true;
	} else {
		return false;
	}
	
}

function validateDelete() {
	
	//	Pending validate there aren't records associated
	if (confirm("Are you sure to delete the project?")) {
		
		result = true;
	} else {
		result = false;
	}
	return result;
}

function edit_row(no)
{
 document.getElementById("edit_button"+no).style.display="none";
 document.getElementById("save_button"+no).style.display="block";
	
 var id=document.getElementById("id_row"+no);
 var name=document.getElementById("name_row"+no);
 var password=document.getElementById("pass_row"+no);
	
 var id_data=id.innerHTML;
 var name_data=name.innerHTML;
 var pass_data=password.innerHTML;
	
 id.innerHTML="<input type='text' id='id_text"+no+"' value='"+id_data+"'>";
 name.innerHTML="<input type='text' id='name_text"+no+"' value='"+name_data+"'>";
 password.innerHTML="<input type='text' id='pass_text"+no+"' value='"+pass_data+"'>";
}

function save_row(no)
{
 var code_val=document.getElementById("code_text"+no).value;
 var name_val=document.getElementById("name_text"+no).value;
 var pass_val=document.getElementById("pass_text"+no).value;

 document.getElementById("code_row"+no).innerHTML=code_val;
 document.getElementById("name_row"+no).innerHTML=name_val;
 document.getElementById("pass_row"+no).innerHTML=pass_val;

 document.getElementById("edit_button"+no).style.display="block";
 document.getElementById("save_button"+no).style.display="none";
}

function delete_row(no)
{
 document.getElementById("row"+no+"").outerHTML="";
}

function add_row()
{
 var new_code=document.getElementById("new_code").value;
 var new_name=document.getElementById("new_name").value;
 var new_pass=document.getElementById("new_pass").value;
	
 var table=document.getElementById("data_table");
 var table_len=(table.rows.length)-1;
 var row = table.insertRow(table_len).outerHTML="<tr id='row"+table_len+"'><td class='data_col_code' id='id_row"+table_len+"'>"+new_code+"</td><td class='data_col_code' id='name_row"+table_len+"'>"+new_name+"</td><td class='data_col_code' id='pass_row"+table_len+"'>"+new_pass+"</td><td class='data_col_code'><img src='img/edit32.gif' id='edit_button"+table_len+"'class='edit' onclick='edit_row("+table_len+")' width='16' height='16'></td><td class='data_col_code'><img src='img/delete32.gif' class='delete' onclick='delete_row("+table_len+")' width='16' height='16'></td></tr>";

 document.getElementById("new_code").value="";
 document.getElementById("new_name").value="";
 document.getElementById("new_pass").value="";
}