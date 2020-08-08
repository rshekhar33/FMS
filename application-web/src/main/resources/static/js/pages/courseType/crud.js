var CrudController = {
	elements: {},

	findElements: function () {
		this.elements.submitBtn = $("#submitBtn");
		this.elements.courseTypeId = $("#courseTypeId");
		this.elements.courseTypeCode = $("#courseTypeCode");
		this.elements.courseTypeName = $("#courseTypeName");
		this.elements.noOfDays = $("#noOfDays");
		this.elements.errMsgs = $(".errMsgCls");
		this.elements.validationFields = $(".validationField");
		this.elements.courseTypeForm = $("#courseTypeForm");

		return this;
	},
	addClickEvents: function () {
		var base = this;

		base.elements.submitBtn.click(function () {
			base.validateSubmitFun();
		});

		return base;
	},
	goToListCourseTypes: function () {
		location.href = contextPath + "courseType/list";
	},
	loadDataFun: function () {
		var base = this;

		Loader.showLoaderRight(true);
		var courseTypeId = base.elements.courseTypeId.val();
		if (!Validation.isEmpty(courseTypeId)) {
			var url = "courseType/fetchData";
			var data = {
				courseTypeId: courseTypeId
			};
			var doneCallbackFun = function (responseObj) {
				var courseType = responseObj;

				if (courseType != null) {
					base.elements.courseTypeCode.val(courseType.courseTypeCode);
					base.elements.courseTypeName.val(courseType.courseTypeName);
					base.elements.noOfDays.val(courseType.noOfDays);
				}
				Loader.showLoaderRight(false);

				return courseType;
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

		if (Validation.isEmpty(dataObj.courseTypeName)) {
			errorObj.courseTypeName = "Mandatory Field!";
		} else if (Validation.hasRestrictedChar3(dataObj.courseTypeName)) {
			errorObj.courseTypeName = "Course Type Name can only have alphanumeric characters, spaces and special characters like '_ @ .'";
		}
		if (!Validation.isEmpty(dataObj.noOfDays) && Validation.isNotNumber(dataObj.noOfDays)) {
			errorObj.noOfDays = "Must be a number!";
		}

		return ErrorFn.showErrors(errorObj);
	},
	submitFun: function (dataObj) {
		var base = this;

		var url = "courseType/validateSave";
		var doneCallbackFun = function (responseObj) {
			if (responseObj.status == "success") {
				bootbox.alert({
					message: responseObj.msg,
					backdrop: true,
					callback: function () {
						Loader.showLoaderRight(true);
						base.goToListCourseTypes();
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

		var courseTypeFormData = this.elements.courseTypeForm.serializeToJSON({});
		var isValid = this.validateFun(courseTypeFormData);

		if (isValid) {
			this.submitFun(courseTypeFormData);
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