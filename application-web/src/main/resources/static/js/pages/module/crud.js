var CrudController = {
	elements: {},

	findElements: function () {
		this.elements.submitBtn = $("#submitBtn");
		this.elements.moduleId = $("#moduleId");
		this.elements.moduleName = $("#moduleName");
		this.elements.errMsgs = $(".errMsgCls");
		this.elements.validationFields = $(".validationField");
		this.elements.moduleForm = $("#moduleForm");

		return this;
	},
	addClickEvents: function () {
		var base = this;

		base.elements.submitBtn.click(function () {
			base.validateSubmitFun();
		});

		return base;
	},
	goToListModules: function () {
		location.href = contextPath + "module/list";
	},
	loadDataFun: function () {
		var base = this;

		Loader.showLoaderRight(true);
		var moduleId = base.elements.moduleId.val();
		if (!Validation.isEmpty(moduleId)) {
			var url = "module/fetchData";
			var data = {
				moduleId: moduleId
			};
			var doneCallbackFun = function (responseObj) {
				var module = responseObj;

				if (module != null) {
					base.elements.moduleName.val(module.moduleName);
				}
				Loader.showLoaderRight(false);

				return module;
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

		if (Validation.isEmpty(dataObj.moduleName)) {
			errorObj.moduleName = "Mandatory Field!";
		} else if (Validation.hasRestrictedChar3(dataObj.moduleName)) {
			errorObj.moduleName = "Module Name can only have alphanumeric characters, spaces and special characters like '_ @ .'";
		}

		return ErrorFn.showErrors(errorObj);
	},
	submitFun: function (dataObj) {
		var base = this;

		var url = "module/validateSave";
		var doneCallbackFun = function (responseObj) {
			if (responseObj.status == "success") {
				bootbox.alert({
					message: responseObj.msg,
					backdrop: true,
					callback: function () {
						Loader.showLoaderRight(true);
						base.goToListModules();
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

		var moduleFormData = this.elements.moduleForm.serializeToJSON({});
		var isValid = this.validateFun(moduleFormData);

		if (isValid) {
			this.submitFun(moduleFormData);
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