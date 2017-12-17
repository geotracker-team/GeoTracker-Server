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



function edit(no) {
	//alert("Edit");
	document.getElementById("btnEdit"+no).style.display="none";
	document.getElementById("btnSave"+no).style.display="block";
	
	 var codeRow=document.getElementById("codeRow"+no);
	 var nameRow=document.getElementById("nameRow"+no);
	 var passwordRow=document.getElementById("passRow"+no);
	 var companyRow=document.getElementById("companyRow"+no);
	 var boolRow=document.getElementById("boolRow"+no);
	 var boolvalue = document.getElementById("bool"+no).checked;
	 
	 var code_data=codeRow.innerHTML;
	 
	 var name_data=nameRow.innerHTML;
	 
	 var pass_data=passwordRow.innerHTML;
	 
	 
	 
	 
	 
	 var company_data=companyRow.innerHTML;
	 var company_code=document.getElementById("company"+no).value;
	 
	 codeRow.innerHTML="<input type='hidden' id='codeSave' name='nameSave' value='"+code_data+"'>"+code_data;
	 nameRow.innerHTML="<input type='text' id='nameSave' name='nameSave' value='"+name_data+"' size='50' maxlength='255' >";
	 passwordRow.innerHTML="<input type='text' id='passSave' name='passSave' value='"+pass_data+"' size='50' maxlength='255' >";
	 if(boolvalue == true){
	 boolRow.innerHTML="<input type='checkbox' id='boolSave' name='boolSave' value='"+boolvalue+"' checked>";
	 }
	 else
	 {
		 boolRow.innerHTML="<input type='checkbox' id='boolSave' name='boolSave' value='"+boolvalue+"'>";
	 }
	 companySelect="<select id='companySave' name='companySave' style='width:200px'>" +
						"<option value='0' selected>(Select company)</option>";
	 var company = document.getElementById("company");
	 var max = company.options.length;
	 var i = 1;
	 while (i < max) {
		 if (company.options[i].value == company_code)
			 companySelect=companySelect + "<option value='" + company.options[i].value + "' selected='selected'>"+company.options[i].text+"</option>";
		 else
			 companySelect=companySelect + "<option value='" + company.options[i].value + "'>"+company.options[i].text+"</option>";
		 i++;
	 }
	 companySelect = companySelect + "</select>";
	 companyRow.innerHTML=companySelect;
	 
	 
	 return true;
}

function save(no) {
//	alert("Save:" + no);
	if (validateSave(no)) {
		document.getElementById("btnEdit"+no).style.display="block";
		document.getElementById("btnSave"+no).style.display="none";
		
		document.getElementById("code").value = document.getElementById("codeSave").value;
		document.getElementById("name").value = document.getElementById("nameSave").value;
		document.getElementById("company").value = document.getElementById("companySave").value;
		document.getElementById("pass").value = document.getElementById("passSave").value;
		document.getElementById("bool").checked = document.getElementById("boolSave").checked;
		
		document.getElementById("action").value = "save";
		document.getElementById("form_data").submit();
		return true;
	} else {
		return false;
	}
}



function deleteUser(id) {
//	alert("Delete");
	if (validateDelete()) {
		document.getElementById("action").value = "delete";
		document.getElementById("code").value = id;
		document.getElementById("form_data").submit();	
		return true;
	} else {
		return false;
	}
}

function validateAdd() {

//	alert("validateAdd");
	result = true;
	if (document.getElementById("name").value == "") {
		alert("Project name is required");
		result = false;
	} else {
		if (document.getElementById("company").value == 0) {
			alert("Company name is required");
			result = false;
		}
	}	
	return result;
}

function validateSave() {
//	alert("validateSave");
	result = true;
	if (document.getElementById("nameSave").value == "") {
		alert("Project name is required");
		result = false;
	} else {
		if (document.getElementById("companySave").value == 0) {
			alert("Company name is required");
			result = false;
		}
	}		
//	alert("validateSave:"+result);
	return result;
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