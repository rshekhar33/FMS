$(function() {
	$("#roles").select2({
		placeholder : "Select an option"
	});
	loadDataFun();
});

function goToListUsers() {
	location.href = contextPath + "user/list";
}

function loadDataFun() {
	showLoaderRight(true);
	var userId = $("#userId").val();
	var deferreds = [];
	deferreds.push(loadUserData(userId));
	deferreds.push(loadRolesData());

	var doneCallbackFun = function(user) {
		if (user != null) {
			var userRoleIds = user.roles;
			if (!isEmpty(userId) && userRoleIds != null) {
				$("#roles").val(userRoleIds);
				$("#roles").trigger("change");
			}
		}
		showLoaderRight(false);

		return user;
	};

	return whenAllDone(deferreds, doneCallbackFun);
}

function loadUserData(userId) {
	if (!isEmpty(userId)) {
		$("#userName").prop("disabled", true);
		var url = "user/fetchData";
		var data = {
			userId : userId
		};
		var doneCallbackFun = function(responseObj) {
			var user = responseObj;
			if (user != null) {
				$("#userName").val(user.userName);
				$("#firstName").val(user.firstName);
				$("#middleName").val(user.middleName);
				$("#lastName").val(user.lastName);
				$("#emailId").val(user.emailId);
				$("#mobileNo").val(user.mobileNo);
			}

			return user;
		};

		return callAjaxPostFun(url, data, doneCallbackFun, errorFun1);
	} else {
		return $.when(null);
	}
}

function loadRolesData() {
	var url = "role/fetchActiveDetails";
	var doneCallbackFun = function(responseObj) {
		var roles = responseObj;

		var dropdownData = [];
		if (roles != null) {
			for (var i = 0; i < roles.length; i++) {
				var role = roles[i];
				dropdownData.push({
					id : role.roleId,
					text : role.roleName
				});
			}
		}
		$("#roles").select2({
			placeholder : "Select an option",
			data : dropdownData
		});

		return roles;
	};

	return callAjaxPostFun(url, null, doneCallbackFun, errorFun1);
}

function validateFun(dataObj) {
	$(".errMsgCls").html("");
	$(".errMsgCls").addClass("d-none");

	$(".validationField").removeClass("is-invalid");

	var errorObj = {};

	if (isEmpty(dataObj.userId)) {
		if (isEmpty(dataObj.userName)) {
			errorObj.userName = "Mandatory Field!";
		} else if (hasRestrictedChar2(dataObj.userName)) {
			errorObj.userName = "UserName can only have alphanumeric characters and special characters like '_ @ .'";
		}
	}
	if (isEmpty(dataObj.firstName)) {
		errorObj.firstName = "Mandatory Field!";
	} else if (!hasOnlyAlphabets(dataObj.firstName)) {
		errorObj.firstName = "First Name can only have alphabets!";
	}
	if (!isEmpty(dataObj.middleName) && !hasOnlyAlphabets(dataObj.middleName)) {
		errorObj.middleName = "Middle Name can only have alphabets!";
	}
	if (!isEmpty(dataObj.lastName) && !hasOnlyAlphabets(dataObj.lastName)) {
		errorObj.lastName = "Last Name can only have alphabets!";
	}
	if (isEmpty(dataObj.emailId)) {
		errorObj.emailId = "Mandatory Field!";
	} else if (isNotValidEmail(dataObj.emailId)) {
		errorObj.emailId = "Invalid Email!";
	}
	if (isEmpty(dataObj.mobileNo)) {
		errorObj.mobileNo = "Mandatory Field!";
	} else if (isNotNumber(dataObj.mobileNo)) {
		errorObj.mobileNo = "Must be a number!";
	} else if (dataObj.mobileNo.length != 10) {
		errorObj.mobileNo = "Must be 10 digit!";
	}
	if (isEmpty(dataObj.roles)) {
		errorObj.roles = "Mandatory Field!";
	}

	return showErrors(errorObj);
}

function submitFun(dataObj) {
	var url = "user/validateSave";
	var doneCallbackFun = function(responseObj) {
		if (responseObj.status == "success") {
			bootbox.alert({
				message : responseObj.msg,
				backdrop : true,
				callback : function() {
					showLoaderRight(true);
					goToListUsers();
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

	var userFormData = $("#userForm").serializeToJSON({});

	var isValid = validateFun(userFormData);

	if (isValid) {
		submitFun(userFormData);
	} else {
		showLoaderRight(false);
	}
}