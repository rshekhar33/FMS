var SignUpController = {
	elements: {},

	findElements: function () {
		this.elements.userNameError = $("#userNameError");
		this.elements.firstNameError = $("#firstNameError");
		this.elements.middleNameError = $("#middleNameError");
		this.elements.lastNameError = $("#lastNameError");
		this.elements.passwordError = $("#passwordError");
		this.elements.passwordRepeatError = $("#passwordRepeatError");
		this.elements.emailIdError = $("#emailIdError");
		this.elements.mobileNoError = $("#mobileNoError");
		this.elements.userName = $("#userName");
		this.elements.firstName = $("#firstName");
		this.elements.middleName = $("#middleName");
		this.elements.lastName = $("#lastName");
		this.elements.password = $("#password");
		this.elements.passwordRepeat = $("#passwordRepeat");
		this.elements.emailId = $("#emailId");
		this.elements.mobileNo = $("#mobileNo");
		this.elements.registerBtn = $("#register");
		this.elements.regForm = $("#regForm");
		this.elements.successMsg = $("#successMsg");

		return this;
	},
	addClickEvents: function () {
		var base = this;

		base.elements.registerBtn.click(function () {
			base.validateSubmitFun();
		});

		return base;
	},
	validateFun: function () {
		var isValid = true;
		this.elements.userNameError.text("");
		this.elements.firstNameError.text("");
		this.elements.middleNameError.text("");
		this.elements.lastNameError.text("");
		this.elements.passwordError.text("");
		this.elements.passwordRepeatError.text("");
		this.elements.emailIdError.text("");
		this.elements.mobileNoError.text("");
		var userName = this.elements.userName.val();
		var firstName = this.elements.firstName.val();
		var middleName = this.elements.middleName.val();
		var lastName = this.elements.lastName.val();
		var password = this.elements.password.val();
		var passwordRepeat = this.elements.passwordRepeat.val();
		var emailId = this.elements.emailId.val();
		var mobileNo = this.elements.mobileNo.val();

		if (Validation.isEmpty(userName)) {
			isValid = false;
			this.elements.userNameError.text("Mandatory Field!");
		}
		if (Validation.isEmpty(firstName)) {
			isValid = false;
			this.elements.firstNameError.text("Mandatory Field!");
		}
		if (Validation.isEmpty(middleName)) {
			isValid = false;
			this.elements.middleNameError.text("Mandatory Field!");
		}
		if (Validation.isEmpty(lastName)) {
			isValid = false;
			this.elements.lastNameError.text("Mandatory Field!");
		}
		if (Validation.isEmpty(password)) {
			isValid = false;
			this.elements.passwordError.text("Mandatory Field!");
		}
		if (Validation.isEmpty(passwordRepeat)) {
			isValid = false;
			this.elements.passwordRepeatError.text("Mandatory Field!");
		}
		if (!Validation.isEmpty(password) && !Validation.isEmpty(passwordRepeat) && password != passwordRepeat) {
			isValid = false;
			this.elements.passwordRepeatError.text("Password Not Matching!");
		}
		if (Validation.isEmpty(emailId)) {
			isValid = false;
			this.elements.emailIdError.text("Mandatory Field!");
		} else if (Validation.isNotValidEmail(emailId)) {
			isValid = false;
			this.elements.emailIdError.text("Invalid Email!");
		}
		if (Validation.isEmpty(mobileNo)) {
			isValid = false;
			this.elements.mobileNoError.text("Mandatory Field!");
		} else if (Validation.isNotNumber(mobileNo)) {
			isValid = false;
			this.elements.mobileNoError.text("Must be a number!");
		} else if (mobileNo.length != 10) {
			isValid = false;
			this.elements.mobileNoError.text("Must be 10 digit!");
		}

		return isValid;
	},
	submitFun: function () {
		var base = this;

		$.post({
			url: contextPath + "validateSaveRegistration",
			data: base.elements.regForm.serialize(),
		}).then(function (responseObj) {
			if (responseObj.status == "success") {
				base.elements.successMsg.text("Registration Completed Succesfully.");
				base.elements.regForm[0].reset();
				setTimeout(function () {
					window.location.replace(contextPath);
				}, 1000);
			} else {
				base.elements.userNameError.text(responseObj.userNameError);
				base.elements.firstNameError.text(responseObj.firstNameError);
				base.elements.passwordError.text(responseObj.passwordError);
				base.elements.emailIdError.text(responseObj.userEmailError);
			}
		}).fail(function (jqXHR) {
			if (jqXHR.status == 403) {
				location.reload();
			}
		});
	},
	validateSubmitFun: function () {
		var isValid = this.validateFun();

		if (isValid) {
			this.submitFun();
		}
	},
	initialize: function () {
		this.findElements().addClickEvents();
	}
};

$(function () {
	SignUpController.initialize();
});