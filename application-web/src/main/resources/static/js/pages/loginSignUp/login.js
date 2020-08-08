var LoginController = {
	elements: {},

	findElements: function () {
		this.elements.inputs = $(".logmod__form .input");
		this.elements.hidePass = $(".hide-password");
		this.elements.loginBtn = $("#login");
		this.elements.userNameError = $("#userNameError");
		this.elements.loginError = $("#loginError");
		this.elements.userName = $("#userName");
		this.elements.password = $("#password");
		this.elements.passwordEnc = $("#passwordEnc");
		this.elements.loginForm = $("#loginForm");

		return this;
	},
	addClickEvents: function () {
		var base = this;

		base.elements.hidePass.on("click", function (e) {
			var $this = $(this), $pwInput = $this.prev("input");

			if ($pwInput.attr("type") == "password") {
				$pwInput.attr("type", "text");
				$this.text("Hide");
			} else {
				$pwInput.attr("type", "password");
				$this.text("Show");
			}
		});

		base.elements.inputs.find("label").on("click", function (e) {
			var $this = $(this), $input = $this.next("input");

			$input.focus();
		});

		base.elements.loginBtn.click(function () {
			base.validateSubmitFun();
		});

		return base;
	},
	validateFun: function () {
		var isValid = true;
		this.elements.userNameError.text("");
		this.elements.loginError.text("");
		var userName = this.elements.userName.val();
		var password = this.elements.password.val();

		if (Validation.isEmpty(userName)) {
			isValid = false;
			this.elements.userNameError.text("Username cannot be left Empty!");
		}
		if (Validation.isEmpty(password)) {
			isValid = false;
			this.elements.loginError.text("Password cannot be left Empty!");
		}

		return isValid;
	},
	submitFun: function () {
		this.elements.password.prop("disabled", true);
		this.elements.passwordEnc.val(encryptString(this.elements.password.val()));
		this.elements.loginForm.submit();
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
	LoginController.initialize();
});