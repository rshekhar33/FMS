var CrudController = {
	elements: {},

	findElements: function () {
		this.elements.submitBtn = $("#submitBtn");
		this.elements.userId = $("#userId");
		this.elements.userName = $("#userName");
		this.elements.modules = $("#modules");
		this.elements.errMsgs = $(".errMsgCls");
		this.elements.validationFields = $(".validationField");
		this.elements.userSkillsetForm = $("#userSkillsetForm");

		return this;
	},
	addClickEvents: function () {
		var base = this;

		base.elements.submitBtn.click(function () {
			base.validateSubmitFun();
		});

		return base;
	},
	goToListUserSkillsets: function () {
		location.href = contextPath + "userSkillset/list";
	},
	loadDataFun: function () {
		var base = this;

		Loader.showLoaderRight(true);
		var userId = base.elements.userId.val();
		var deferreds = [];
		deferreds.push(base.loadUserSkillsetData(userId));
		deferreds.push(base.loadModulesData());

		var doneCallbackFun = function (user) {
			if (user != null) {
				var userModuleIds = user.modules;
				if (!Validation.isEmpty(userId) && userModuleIds != null) {
					base.elements.modules.val(userModuleIds);
					base.elements.modules.bootstrapDualListbox('refresh', true);
				}
			}
			Loader.showLoaderRight(false);

			return user;
		};

		return AjaxFn.whenAllDone(deferreds, doneCallbackFun);
	},
	loadUserSkillsetData: function (userId) {
		var base = this;

		if (!Validation.isEmpty(userId)) {
			base.elements.userName.prop("disabled", true);
			var url = "userSkillset/fetchData";
			var data = {
				userId: userId
			};
			var doneCallbackFun = function (responseObj) {
				var user = responseObj;
				if (user != null) {
					base.elements.userName.val(user.userName);
				}

				return user;
			};

			return AjaxFn.callPostFun(url, data, doneCallbackFun, ErrorFn.errorFun1);
		} else {
			return $.when(null);
		}
	},
	loadModulesData: function () {
		var base = this;

		var url = "module/fetchActiveDetails";
		var doneCallbackFun = function (responseObj) {
			var modules = responseObj;

			var dropdownData = [];
			if (modules != null) {
				dropdownData = $.map(modules, function (module) {
					return $('<option>', {
						value: module.moduleId,
						text: module.moduleName
					});
				});
			}
			base.elements.modules.html(dropdownData);
			base.elements.modules.bootstrapDualListbox('refresh', true);

			return modules;
		};

		return AjaxFn.callPostFun(url, null, doneCallbackFun, ErrorFn.errorFun1);
	},
	validateFun: function (dataObj) {
		this.elements.errMsgs.html("");
		this.elements.errMsgs.addClass("d-none");

		this.elements.validationFields.removeClass("is-invalid");

		var errorObj = {};

		if (Validation.isEmpty(dataObj.userId) && Validation.isEmpty(dataObj.userName)) {
			errorObj.userName = "Mandatory Field!";
		}

		return ErrorFn.showErrors(errorObj);
	},
	submitFun: function (dataObj) {
		var base = this;

		var url = "userSkillset/validateSave";
		var doneCallbackFun = function (responseObj) {
			if (responseObj.status == "success") {
				bootbox.alert({
					message: responseObj.msg,
					backdrop: true,
					callback: function () {
						Loader.showLoaderRight(true);
						base.goToListUserSkillsets();
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

		var userSkillsetFormData = this.elements.userSkillsetForm.serializeToJSON({});
		var isValid = this.validateFun(userSkillsetFormData);

		if (isValid) {
			this.submitFun(userSkillsetFormData);
		} else {
			Loader.showLoaderRight(false);
		}
	},
	initialize: function () {
		this.findElements().addClickEvents();
		this.elements.modules.bootstrapDualListbox({
			nonSelectedListLabel: 'Non-selected',
			selectedListLabel: 'Selected',
			preserveSelectionOnMove: 'moved',
			moveOnSelect: false
		});
		this.loadDataFun();
	}
};

$(function () {
	CrudController.initialize();
});