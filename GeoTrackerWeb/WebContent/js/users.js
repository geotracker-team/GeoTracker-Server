function edit_row(no)
{
 document.getElementById("edit_button"+no).style.display="none";
 document.getElementById("save_button"+no).style.display="block";
	
 var code=document.getElementById("code_row"+no);
 var name=document.getElementById("name_row"+no);
 var pass=document.getElementById("pass_row"+no);
	
 var code_data=code.innerHTML;
 var name_data=name.innerHTML;
 var pass_data=pass.innerHTML;
	
 code.innerHTML="<input type='text' id='code_text"+no+"' value='"+code_data+"'>";
 name.innerHTML="<input type='text' id='name_text"+no+"' value='"+name_data+"'>";
 pass.innerHTML="<input type='text' id='pass_text"+no+"' value='"+pass_data+"'>";
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
 var row = table.insertRow(table_len).outerHTML="<tr id='row"+table_len+"'><td id='code_row"+table_len+"'>"+new_code+"</td><td id='name_row"+table_len+"'>"+new_name+"</td><td id='pass_row"+table_len+"'>"+new_pass+"</td><td><img src='img/edit.png' id='edit_button"+table_len+"'class='edit' onclick='edit_row("+table_len+")' width='20' height='20'> <img src='img/save.png' id='save_button"+table_len+"' class='save' onclick='save_row("+table_len+")' width='20' height='20'> <img src='img/delete.png' class='delete' onclick='delete_row("+table_len+")' width='20' height='20'></td></tr>";

 document.getElementById("new_code").value="";
 document.getElementById("new_name").value="";
 document.getElementById("new_pass").value="";
}