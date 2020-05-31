function validateFun() {
	var isValid = true;
	$("#userNameError").text("");
	$("#firstNameError").text("");
	$("#middleNameError").text("");
	$("#lastNameError").text("");
	$("#passwordError").text("");
	$("#passwordRepeatError").text("");
	$("#emailIdError").text("");
	$("#mobileNoError").text("");
	var userName = $("#userName").val();
	var firstName = $("#firstName").val();
	var middleName = $("#middleName").val();
	var lastName = $("#lastName").val();
	var password = $("#password").val();
	var passwordRepeat = $("#passwordRepeat").val();
	var emailId = $("#emailId").val();
	var mobileNo = $("#mobileNo").val();

	if (isEmpty(userName)) {
		isValid = false;
		$("#userNameError").text("Mandatory Field!");
	}
	if (isEmpty(firstName)) {
		isValid = false;
		$("#firstNameError").text("Mandatory Field!");
	}
	if (isEmpty(middleName)) {
		isValid = false;
		$("#middleNameError").text("Mandatory Field!");
	}
	if (isEmpty(lastName)) {
		isValid = false;
		$("#lastNameError").text("Mandatory Field!");
	}
	if (isEmpty(password)) {
		isValid = false;
		$("#passwordError").text("Mandatory Field!");
	}
	if (isEmpty(passwordRepeat)) {
		isValid = false;
		$("#passwordRepeatError").text("Mandatory Field!");
	}
	if (!isEmpty(password) && !isEmpty(passwordRepeat) && password != passwordRepeat) {
		isValid = false;
		$("#passwordRepeatError").text("Password Not Matching!");
	}
	if (isEmpty(emailId)) {
		isValid = false;
		$("#emailIdError").text("Mandatory Field!");
	} else if (isNotValidEmail(emailId)) {
		isValid = false;
		$("#emailIdError").text("Invalid Email!");
	}
	if (isEmpty(mobileNo)) {
		isValid = false;
		$("#mobileNoError").text("Mandatory Field!");
	} else if (isNotNumber(mobileNo)) {
		isValid = false;
		$("#mobileNoError").text("Must be a number!");
	} else if (mobileNo.length != 10) {
		isValid = false;
		$("#mobileNoError").text("Must be 10 digit!");
	}

	return isValid;
}

function submitFun() {
	$.post({
		url : contextPath + "validateSaveRegistration",
		data : $("#regForm").serialize(),
		success : function(data) {
			var responseObj = data;
			if (responseObj.status == "success") {
				$("#successMsg").text("Registration Completed Succesfully.");
				$("#regForm")[0].reset();
				setTimeout(function() {
					window.location.replace(contextPath);
				}, 1000);
			} else {
				$("#userNameError").text(responseObj.userNameError);
				$("#firstNameError").text(responseObj.firstNameError);
				$("#passwordError").text(responseObj.passwordError);
				$("#emailIdError").text(responseObj.userEmailError);
			}
		},
		error : function(jqXHR) {
			if (jqXHR.status == 403) {
				location.reload();
			}
		}
	});
}

function validateSubmitFun() {
	var isValid = validateFun();

	if (isValid) {
		submitFun();
	}
}