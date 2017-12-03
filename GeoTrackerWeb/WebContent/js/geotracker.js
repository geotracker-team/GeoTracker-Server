
function confirmarBorrar() {
	text = "Esteu segur d'esborrar el registre?";
	if (confirm(text) == true)
		return true;
	else
		return false;
	}
	
	
function tornar() {
	window.history.back();
}	