
var inputPassword = document.querySelector("#password");
var inputEmail = document.querySelector("#email");



inputPassword.addEventListener("blur", function(event) {
	if (validator.validatePassword(event.target.value)) {
		valid();
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

function validateEmail(input) {
	var str = input;
	var reg = /^([a-zA-Z0-9_-]+\.)*[a-zA-Z0-9_-]+@[a-z0-9_-]+(\.[a-z0-9_-]+)*\.[a-z]{2,4}$/;
	if (!str.match(reg)) {
		return false;
	}
	return true;
}
