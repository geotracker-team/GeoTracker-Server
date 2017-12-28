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
	if ((document.getElementById("user").value == "") || (document.getElementById("user").value == "0")) {
		alert("User is required");
		result = false;
	}	
	return result;
}

function validateDelete() {
	
	//	Pending validate there aren't records associated
	if (confirm("Are you sure to delete the assignation?")) {
		
		result = true;
	} else {
		result = false;
	}
	return result;
}