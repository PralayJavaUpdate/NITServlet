

function validation(frm){
	//set vflag to "yes" indicating client-side form validations are done
	frm.vflag.value="yes";
	
	//set style to error message
	document.getElementById("nameErr").innerHTML="";
	document.getElementById("ageErr").innerHTML="";
	document.getElementById("nameErr").style="color:red";
	document.getElementById("ageErr").style="color:red";
	
	//read form data
	var name=frm.pname.value;
	var age=frm.page.value;
	
	//client-side validation
	if(name==""){
		document.getElementById("nameErr").inner="Person name is mandatory"
		frm.pname.focus();
		return false;
	}	
	else if(!isNaN(name)){
			document.getElementById("nameErr").innerHTML="Person name must be only character value"
			frm.page.focus();
			return false
		}//if

	
	else if(age==""){
		document.getElementById("ageErr").innerHTML="Person age is mandatory"
		frm.page.focus();
		return false;
	}
	else{
		if(isNaN(age)){
			document.getElementById("ageErr").innerHTML="Person age must be numeric value"
			frm.page.focus();
			return false
		}//if
	}//else
	return true;
}//function