var CrudController = {
	elements: {},

	findElements: function () {
		this.elements.submitBtn = $("#submitBtn");
		this.elements.userId = $("#userId");
		this.elements.userName = $("#userName");
		this.elements.firstName = $("#firstName");
		this.elements.middleName = $("#middleName");
		this.elements.lastName = $("#lastName");
		this.elements.emailId = $("#emailId");
		this.elements.mobileNo = $("#mobileNo");
		this.elements.roles = $("#roles");
		this.elements.errMsgs = $(".errMsgCls");
		this.elements.validationFields = $(".validationField");
		this.elements.userForm = $("#userForm");

		return this;
	},
	addClickEvents: function () {
		var base = this;

		base.elements.submitBtn.click(function () {
			base.validateSubmitFun();
		});

		return base;
	},
	goToListUsers: function () {
		location.href = contextPath + "user/list";
	},
	loadDataFun: function () {
		var base = this;

		Loader.showLoaderRight(true);
		var userId = base.elements.userId.val();
		var deferreds = [];
		deferreds.push(base.loadUserData(userId));
		deferreds.push(base.loadRolesData());

		var doneCallbackFun = function (user) {
			if (user != null) {
				var userRoleIds = user.roles;
				if (!Validation.isEmpty(userId) && userRoleIds != null) {
					base.elements.roles.val(userRoleIds);
					base.elements.roles.trigger("change");
				}
			}
			Loader.showLoaderRight(false);

			return user;
		};

		return AjaxFn.whenAllDone(deferreds, doneCallbackFun);
	},
	loadUserData: function (userId) {
		var base = this;

		if (!Validation.isEmpty(userId)) {
			base.elements.userName.prop("disabled", true);
			var url = "user/fetchData";
			var data = {
				userId: userId
			};
			var doneCallbackFun = function (responseObj) {
				var user = responseObj;
				if (user != null) {
					base.elements.userName.val(user.userName);
					base.elements.firstName.val(user.firstName);
					base.elements.middleName.val(user.middleName);
					base.elements.lastName.val(user.lastName);
					base.elements.emailId.val(user.emailId);
					base.elements.mobileNo.val(user.mobileNo);
				}

				return user;
			};

			return AjaxFn.callPostFun(url, data, doneCallbackFun, ErrorFn.errorFun1);
		} else {
			return $.when(null);
		}
	},
	loadRolesData: function () {
		var base = this;

		var url = "role/fetchActiveDetails";
		var doneCallbackFun = function (responseObj) {
			var roles = responseObj;

			var dropdownData = [];
			if (roles != null) {
				for (var i = 0; i < roles.length; i++) {
					var role = roles[i];
					dropdownData.push({
						id: role.roleId,
						text: role.roleName
					});
				}
			}
			base.elements.roles.select2({
				placeholder: "Select an option",
				data: dropdownData
			});

			return roles;
		};

		return AjaxFn.callPostFun(url, null, doneCallbackFun, ErrorFn.errorFun1);
	},
	validateFun: function (dataObj) {
		this.elements.errMsgs.html("");
		this.elements.errMsgs.addClass("d-none");

		this.elements.validationFields.removeClass("is-invalid");

		var errorObj = {};

		if (Validation.isEmpty(dataObj.userId)) {
			if (Validation.isEmpty(dataObj.userName)) {
				errorObj.userName = "Mandatory Field!";
			} else if (Validation.hasRestrictedChar2(dataObj.userName)) {
				errorObj.userName = "UserName can only have alphanumeric characters and special characters like '_ @ .'";
			}
		}
		if (Validation.isEmpty(dataObj.firstName)) {
			errorObj.firstName = "Mandatory Field!";
		} else if (!Validation.hasOnlyAlphabets(dataObj.firstName)) {
			errorObj.firstName = "First Name can only have alphabets!";
		}
		if (!Validation.isEmpty(dataObj.middleName) && !Validation.hasOnlyAlphabets(dataObj.middleName)) {
			errorObj.middleName = "Middle Name can only have alphabets!";
		}
		if (!Validation.isEmpty(dataObj.lastName) && !Validation.hasOnlyAlphabets(dataObj.lastName)) {
			errorObj.lastName = "Last Name can only have alphabets!";
		}
		if (Validation.isEmpty(dataObj.emailId)) {
			errorObj.emailId = "Mandatory Field!";
		} else if (Validation.isNotValidEmail(dataObj.emailId)) {
			errorObj.emailId = "Invalid Email!";
		}
		if (Validation.isEmpty(dataObj.mobileNo)) {
			errorObj.mobileNo = "Mandatory Field!";
		} else if (Validation.isNotNumber(dataObj.mobileNo)) {
			errorObj.mobileNo = "Must be a number!";
		} else if (dataObj.mobileNo.length != 10) {
			errorObj.mobileNo = "Must be 10 digit!";
		}
		if (Validation.isEmpty(dataObj.roles)) {
			errorObj.roles = "Mandatory Field!";
		}

		return ErrorFn.showErrors(errorObj);
	},
	submitFun: function (dataObj) {
		var base = this;

		var url = "user/validateSave";
		var doneCallbackFun = function (responseObj) {
			if (responseObj.status == "success") {
				bootbox.alert({
					message: responseObj.msg,
					backdrop: true,
					callback: function () {
						Loader.showLoaderRight(true);
						base.goToListUsers();
					}
				});
			} else if (responseObj.status == "fail" && !Validation.isEmpty(responseObj.invalidData)) {
				ErrorFn.showErrors(responseObj.invalidData);
			}
			Loader.showLoaderRight(false);

			return responseObj;
		};

		return AjaxFn.callPostFun(url, dataObj, doneCallbackFun, ErrorFn.errorFun2);
	},
	validateSubmitFun: function () {
		Loader.showLoaderRight(true);

		var userFormData = this.elements.userForm.serializeToJSON({});
		var isValid = this.validateFun(userFormData);

		if (isValid) {
			this.submitFun(userFormData);
		} else {
			Loader.showLoaderRight(false);
		}
	},
	initialize: function () {
		this.findElements().addClickEvents();
		this.elements.roles.select2({
			placeholder: "Select an option"
		});
		this.loadDataFun();
	}
};

$(function () {
	CrudController.initialize();
});