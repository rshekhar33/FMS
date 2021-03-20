$(function () {
	CommonFn.initialize();
});

var CommonFn = {
	initialize: function () {
		/* Code to include token in all Ajax headers */
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
		$(document).ajaxSend(function (_e, xhr) {
			xhr.setRequestHeader(header, token);
		});

		$.ajaxPrefilter(function (options, originalOptions) {
			if (options.contentType == "application/json" && originalOptions.data != null && typeof originalOptions.data === "object") {
				options.data = JSON.stringify(originalOptions.data);
			}
		});
	}
};

// Error message functions
var ErrorFn = {
	showErrorMsg: function (selector, errorMsg) {
		$(selector + "Error").html("<i class='far fa-times-circle'></i> " + errorMsg);
		$(selector + "Error").removeClass("d-none");
		$(selector).addClass("is-invalid");
	},
	showErrors: function (errorObj) {
		var isValid = true;
		for (var key in errorObj) {
			var errorMsg = errorObj[key];
			if (!Validation.isEmpty(errorMsg)) {
				isValid = false;
				ErrorFn.showErrorMsg("#" + key, errorMsg);
			}
		}

		return isValid;
	},
	errorFun1: function (jqXHR) {
		if (jqXHR.status == 403) {
			location.reload();
		}
	},
	errorFun2: function (jqXHR) {
		if (jqXHR.status == 403) {
			location.reload();
		} else {
			var errorObj = jqXHR.responseJSON;
			if (errorObj.status == "fail" && !Validation.isEmpty(errorObj.invalidData)) {
				ErrorFn.showErrors(errorObj.invalidData);
			}
			Loader.showLoaderRight(false);
		}
	}
};

// Ajax function
var AjaxFn = {
	callPostFun: function (url, data, doneCallbackFun, failCallbackFun) {
		return $.post({
			url: contextPath + url,
			contentType: "application/json",
			data: data
		}).then(doneCallbackFun).fail(failCallbackFun);
	},
	whenAllDone: function (deferreds, doneCallbackFun) {
		return $.when.apply($, deferreds).then(doneCallbackFun);
	}
};

// Validation functions
var Validation = {
	isEmpty: function (fieldVar) {
		if (typeof fieldVar === 'undefined') {
			return true;
		} else if ((typeof fieldVar === 'string' || fieldVar instanceof String) && fieldVar.trim() == "") {
			return true;
		} else if (fieldVar.length == 0) {
			return true;
		} else if (fieldVar) {
			return false;
		}

		return true;
	},
	isNotNumber: function (numberVar) {
		var regex = /^\d+$/;
		return !regex.test(numberVar);
	},
	isNotValidEmail: function (emailVar) {
		var regex = /^(([^<>()\[\]\.,;:\s@\"]+(\.[^<>()\[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i;
		return !regex.test(emailVar);
	},
	hasOnlyAlphabets: function (fieldVar) {
		var regex = /^[a-zA-Z]+$/;
		return regex.test(fieldVar);
	},
	hasSpecialChar: function (fieldVar) {
		var regex = /[*|\":<>[\]{}`\\()';@&$]/;
		return regex.test(fieldVar);
	},
	hasRestrictedChar1: function (fieldVar) {
		var regex = /\W/;
		return regex.test(fieldVar);
	},
	hasRestrictedChar2: function (fieldVar) {
		var regex = /^[\w.@]+$/;
		return !regex.test(fieldVar);
	},
	hasRestrictedChar3: function (fieldVar) {
		var regex = /^[\w.@ ]+$/;
		return !regex.test(fieldVar);
	}
};

var Loader = {
	loaderContainer: $("#loaderContainer"),
	loaderRightContainer: $("#loaderRightContainer"),
	showLoader: function (condition) {
		if (condition) {
			this.loaderContainer.removeClass("d-none");
		} else {
			this.loaderContainer.addClass("d-none");
		}
	},
	showLoaderRight: function (condition) {
		if (condition) {
			this.loaderRightContainer.removeClass("d-none");
		} else {
			this.loaderRightContainer.addClass("d-none");
		}
	}
};