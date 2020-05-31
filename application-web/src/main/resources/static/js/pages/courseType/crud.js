$(function() {
	loadDataFun();
});

function goToListCourseTypes() {
	location.href = contextPath + "courseType/list";
}

function loadDataFun() {
	showLoaderRight(true);
	var courseTypeId = $("#courseTypeId").val();
	if (!isEmpty(courseTypeId)) {
		var url = "courseType/fetchData";
		var data = {
			courseTypeId : courseTypeId
		};
		var doneCallbackFun = function(responseObj) {
			var courseType = responseObj;

			if (courseType != null) {
				$("#courseTypeCode").val(courseType.courseTypeCode);
				$("#courseTypeName").val(courseType.courseTypeName);
				$("#noOfDays").val(courseType.noOfDays);
			}
			showLoaderRight(false);

			return courseType;
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

	if (isEmpty(dataObj.courseTypeName)) {
		errorObj.courseTypeName = "Mandatory Field!";
	} else if (hasRestrictedChar3(dataObj.courseTypeName)) {
		errorObj.courseTypeName = "Course Type Name can only have alphanumeric characters, spaces and special characters like '_ @ .'";
	}
	if (!isEmpty(dataObj.noOfDays) && isNotNumber(dataObj.noOfDays)) {
		errorObj.noOfDays = "Must be a number!";
	}

	return showErrors(errorObj);
}

function submitFun(dataObj) {
	var url = "courseType/validateSave";
	var doneCallbackFun = function(responseObj) {
		if (responseObj.status == "success") {
			bootbox.alert({
				message : responseObj.msg,
				backdrop : true,
				callback : function() {
					showLoaderRight(true);
					goToListCourseTypes();
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

	var courseTypeFormData = $("#courseTypeForm").serializeToJSON({});

	var isValid = validateFun(courseTypeFormData);

	if (isValid) {
		submitFun(courseTypeFormData);
	} else {
		showLoaderRight(false);
	}
}