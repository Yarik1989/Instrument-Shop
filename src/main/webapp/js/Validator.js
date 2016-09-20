var inputFirstName = document.querySelector("#firstName");
var inputLastName = document.querySelector("#lastName");
var inputPassword = document.querySelector("#password");
var inputRepeatPassword = document.querySelector("#repeatPassword");
var inputEmail = document.querySelector("#email");

inputFirstName.addEventListener("blur", function(event) {
	if (validator.validateName(event.target.value)) {
		valid();
	} else {
		invalid();
	}

});
inputLastName.addEventListener("blur", function(event) {
	if (validator.validateName(event.target.value)) {
		valid();
	} else {
		invalid();
	}

});
inputPassword.addEventListener("blur", function(event) {
	if (validator.validatePassword(event.target.value)) {
		valid();
	} else {
		invalid();
	}

});
inputRepeatPassword.addEventListener("blur", function(event) {
	if (validator.validatePassword(event.target.value)) {
		if (inputPassword.value == inputRepeatPassword.value) {
			valid();
		} else {
			invalid();
		}
	} else {
		invalid();
	}
});
inputEmail.addEventListener("blur", function(event) {
	if (validator.validateEmail(event.target.value)) {
		valid();
	} else {
		invalid();
	}
});

var validator = {
	validateEmail : validateEmail,
	validateName : validateName,
	validatePassword : validatePassword

}

function valid() {
	event.target.style.border = "2px double green";
	event.target.classList.remove("inputError");
	event.target.classList.add("inputSuccess");
}
function invalid() {
	event.target.style.border = "2px double red";
	event.target.classList.remove("inputSuccess");
	event.target.classList.add("inputError");

}
function validatePassword(input) {
	var password = input;
	var regForPassword =/[a-zA-Z0-9]{3,30}/;
	if (!password.match(regForPassword)) {
		return false;
	}
	return true;
}
function validateName(input) {
	var name = input;
	var regForLogin = /[a-zA-Z]{3,30}/;
	if (name.length < 3) {
		return false
	}
	if (!name.match(regForLogin)) {
		return false;
	}
	return true
}
function validateEmail(input) {
	var str = input;
	var reg = /^([a-zA-Z0-9_-]+\.)*[a-zA-Z0-9_-]+@[a-z0-9_-]+(\.[a-z0-9_-]+)*\.[a-z]{2,4}$/;
	if (!str.match(reg)) {
		return false;
	}
	return true;
}
