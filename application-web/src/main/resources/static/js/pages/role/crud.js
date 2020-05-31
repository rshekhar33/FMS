$(function() {
	loadDataFun();
});

function goToListRoles() {
	location.href = contextPath + "role/list";
}

function loadDataFun() {
	showLoaderRight(true);
	var roleId = $("#roleId").val();
	if (!isEmpty(roleId)) {
		var url = "role/fetchData";
		var data = {
			roleId : roleId
		};
		var doneCallbackFun = function(responseObj) {
			var role = responseObj;

			if (role != null) {
				$("#roleName").val(role.roleName);
			}
			showLoaderRight(false);

			return role;
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

	if (isEmpty(dataObj.roleName)) {
		errorObj.roleName = "Mandatory Field!";
	} else if (hasRestrictedChar3(dataObj.roleName)) {
		errorObj.roleName = "Role Name can only have alphanumeric characters, spaces and special characters like '_ @ .'";
	}

	return showErrors(errorObj);
}

function submitFun(dataObj) {
	var url = "role/validateSave";
	var doneCallbackFun = function(responseObj) {
		if (responseObj.status == "success") {
			bootbox.alert({
				message : responseObj.msg,
				backdrop : true,
				callback : function() {
					showLoaderRight(true);
					goToListRoles();
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

	var roleFormData = $("#roleForm").serializeToJSON({});

	var isValid = validateFun(roleFormData);

	if (isValid) {
		submitFun(roleFormData);
	} else {
		showLoaderRight(false);
	}
}