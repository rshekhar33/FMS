var CrudController = {
	elements: {},

	findElements: function () {
		this.elements.submitBtn = $("#submitBtn");
		this.elements.roleId = $("#roleId");
		this.elements.roleName = $("#roleName");
		this.elements.errMsgs = $(".errMsgCls");
		this.elements.validationFields = $(".validationField");
		this.elements.roleForm = $("#roleForm");

		return this;
	},
	addClickEvents: function () {
		var base = this;

		base.elements.submitBtn.click(function () {
			base.validateSubmitFun();
		});

		return base;
	},
	goToListRoles: function () {
		location.href = contextPath + "role/list";
	},
	loadDataFun: function () {
		var base = this;

		Loader.showLoaderRight(true);
		var roleId = base.elements.roleId.val();
		if (!Validation.isEmpty(roleId)) {
			var url = "role/fetchData";
			var data = {
				roleId: roleId
			};
			var doneCallbackFun = function (responseObj) {
				var role = responseObj;

				if (role != null) {
					base.elements.roleName.val(role.roleName);
				}
				Loader.showLoaderRight(false);

				return role;
			};

			return AjaxFn.callPostFun(url, data, doneCallbackFun, ErrorFn.errorFun1);
		} else {
			Loader.showLoaderRight(false);
			return $.when(null);
		}
	},
	validateFun: function (dataObj) {
		this.elements.errMsgs.html("");
		this.elements.errMsgs.addClass("d-none");

		this.elements.validationFields.removeClass("is-invalid");

		var errorObj = {};

		if (Validation.isEmpty(dataObj.roleName)) {
			errorObj.roleName = "Mandatory Field!";
		} else if (Validation.hasRestrictedChar3(dataObj.roleName)) {
			errorObj.roleName = "Role Name can only have alphanumeric characters, spaces and special characters like '_ @ .'";
		}

		return ErrorFn.showErrors(errorObj);
	},
	submitFun: function (dataObj) {
		var base = this;

		var url = "role/validateSave";
		var doneCallbackFun = function (responseObj) {
			if (responseObj.status == "success") {
				bootbox.alert({
					message: responseObj.msg,
					backdrop: true,
					callback: function () {
						Loader.showLoaderRight(true);
						base.goToListRoles();
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

		var roleFormData = this.elements.roleForm.serializeToJSON({});
		var isValid = this.validateFun(roleFormData);

		if (isValid) {
			this.submitFun(roleFormData);
		} else {
			Loader.showLoaderRight(false);
		}
	},
	initialize: function () {
		this.findElements().addClickEvents().loadDataFun();
	}
};

$(function () {
	CrudController.initialize();
});