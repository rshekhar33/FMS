$(function() {
	/* Code to include token in all Ajax headers */
	var token = $("meta[name='_csrf']").attr("content");
	var header = $("meta[name='_csrf_header']").attr("content");
	$(document).ajaxSend(function(e, xhr) {
		xhr.setRequestHeader(header, token);
	});

	$.ajaxPrefilter(function(options, originalOptions) {
		if (options.contentType == "application/json" && originalOptions.data != null && typeof originalOptions.data === "object") {
			options.data = JSON.stringify(originalOptions.data);
		}
	});
});

// Error message functions
function showErrorMsg(selector, errorMsg) {
	$(selector + "Error").html("<i class='far fa-times-circle'></i> " + errorMsg);
	$(selector + "Error").removeClass("d-none");
	$(selector).addClass("is-invalid");
}

function showErrors(errorObj) {
	var isValid = true;
	for ( var key in errorObj) {
		if (errorObj.hasOwnProperty(key) && key != "status") {
			var errorMsg = errorObj[key];
			if (!isEmpty(errorMsg)) {
				isValid = false;
				showErrorMsg("#" + key, errorMsg);
			}
		}
	}

	return isValid;
}

// Ajax function
function callAjaxPostFun(url, data, doneCallbackFun, failCallbackFun) {
	return $.post({
		url : contextPath + url,
		contentType : "application/json",
		data : data
	}).then(doneCallbackFun).fail(failCallbackFun);
}

function whenAllDone(deferreds, doneCallbackFun) {
	return $.when.apply($, deferreds).then(doneCallbackFun);
}

// Ajax error function
function errorFun1(jqXHR) {
	if (jqXHR.status == 403) {
		location.reload();
	}
}

function errorFun2(jqXHR) {
	if (jqXHR.status == 403) {
		location.reload();
	} else {
		var errorObj = jqXHR.responseJSON;
		if (errorObj.status == "fail") {
			showErrors(errorObj);
		}
		showLoaderRight(false);
	}
}

// Validation functions
function isEmpty(fieldVar) {
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
}

function isNotNumber(numberVar) {
	var regex = /^\d+$/;
	return !regex.test(numberVar);
}

function isNotValidEmail(emailVar) {
	var regex = /^(([^<>()\[\]\.,;:\s@\"]+(\.[^<>()\[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i;
	return !regex.test(emailVar);
}

function hasOnlyAlphabets(fieldVar) {
	var regex = /^[a-zA-Z]+$/;
	return regex.test(fieldVar);
}

function hasSpecialChar(fieldVar) {
	var regex = /[*|\":<>[\]{}`\\()';@&$]/;
	return regex.test(fieldVar);
}

function hasRestrictedChar1(fieldVar) {
	var regex = /\W/;
	return regex.test(fieldVar);
}

function hasRestrictedChar2(fieldVar) {
	var regex = /^[\w.@]+$/;
	return !regex.test(fieldVar);
}

function hasRestrictedChar3(fieldVar) {
	var regex = /^[\w.@ ]+$/;
	return !regex.test(fieldVar);
}

function showLoader(condition) {
	if (condition) {
		$("#loaderContainer").removeClass("d-none");
	} else {
		$("#loaderContainer").addClass("d-none");
	}
}

function showLoaderRight(condition) {
	if (condition) {
		$("#loaderRightContainer").removeClass("d-none");
	} else {
		$("#loaderRightContainer").addClass("d-none");
	}
}