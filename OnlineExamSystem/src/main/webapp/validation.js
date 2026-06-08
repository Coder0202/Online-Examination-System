document.getElementById("registerForm").addEventListener("submit", function(e) {

	let valid = true;

	// Clear all old error messages
	document.getElementById("nameError").innerHTML = "";
	document.getElementById("emailError").innerHTML = "";
	document.getElementById("passError").innerHTML = "";
	document.getElementById("courseError").innerHTML = "";
	document.getElementById("mobileError").innerHTML = "";

	// Get values
	let name = document.getElementById("name").value;
	let email = document.getElementById("email").value;
	let password = document.getElementById("password").value;
	let course = document.getElementById("course").value;
	let mobile = document.getElementById("mobile").value;

	// Name check
	if (name == "") {
		document.getElementById("nameError").innerHTML = "Enter Name";
		valid = false;
	}

	// Email check
	if (email == "") {
		document.getElementById("emailError").innerHTML = "Enter Email";
		valid = false;
	}

	// Password check
	if (password == "") {
		document.getElementById("passError").innerHTML = "Enter Password";
		valid = false;
	} else if (password.length < 6) {
		document.getElementById("passError").innerHTML = "Password minimum 6 characters";
		valid = false;
	}

	// Course check
	if (course == "") {
		document.getElementById("courseError").innerHTML = "Enter Course";
		valid = false;
	}

	// Mobile check
	if (mobile == "") {
		document.getElementById("mobileError").innerHTML = "Enter Mobile Number";
		valid = false;
	} else if (mobile.length != 10) {
		document.getElementById("mobileError").innerHTML = "Enter 10 Digit Mobile Number";
		valid = false;
	}

	// Stop form submit
	if (valid == false) {
		e.preventDefault();
	}

});