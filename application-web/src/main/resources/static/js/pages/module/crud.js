$(function() {
	loadDataFun();
});

function goToListModules() {
	location.href = contextPath + "module/list";
}

function loadDataFun() {
	showLoaderRight(true);
	var moduleId = $("#moduleId").val();
	if (!isEmpty(moduleId)) {
		var url = "module/fetchData";
		var data = {
			moduleId : moduleId
		};
		var doneCallbackFun = function(responseObj) {
			var module = responseObj;

			if (module != null) {
				$("#moduleName").val(module.moduleName);
			}
			showLoaderRight(false);

			return module;
		};

		return callAjaxPostFun(url, data, doneCallbackFun, errorFun1);
	} else {
		showLoaderRight(false);
		return $.when(null);
	}
}

function validateFun(dataObj) {
	$(".errMsgCls").html("");
	$(".errMsgCls").addClass("d-none");

	$(".validationField").removeClass("is-invalid");

	var errorObj = {};

	if (isEmpty(dataObj.moduleName)) {
		errorObj.moduleName = "Mandatory Field!";
	} else if (hasRestrictedChar3(dataObj.moduleName)) {
		errorObj.moduleName = "Module Name can only have alphanumeric characters, spaces and special characters like '_ @ .'";
	}

	return showErrors(errorObj);
}

function submitFun(dataObj) {
	var url = "module/validateSave";
	var doneCallbackFun = function(responseObj) {
		if (responseObj.status == "success") {
			bootbox.alert({
				message : responseObj.msg,
				backdrop : true,
				callback : function() {
					showLoaderRight(true);
					goToListModules();
				}
			});
		}
		showLoaderRight(false);

		return responseObj;
	};

	return callAjaxPostFun(url, dataObj, doneCallbackFun, errorFun2);
}

function validateSubmitFun() {
	showLoaderRight(true);

	var moduleFormData = $("#moduleForm").serializeToJSON({});

	var isValid = validateFun(moduleFormData);

	if (isValid) {
		submitFun(moduleFormData);
	} else {
		showLoaderRight(false);
	}
}