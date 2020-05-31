var usersDataTable = $("#usersTable").DataTable({
	ordering : true,
	info : true,
	autoWidth : false,
	paging : true,
	responsive : true,
	pagingType : "full_numbers",
	columns : [ {
		title : "UserName",
		data : "userName"
	}, {
		title : "Name",
		data : "fullName"
	}, {
		title : "Email ID",
		data : "emailId"
	}, {
		title : "Mobile No",
		data : "mobileNo"
	}, {
		title : "Role",
		data : "roleName"
	}, {
		title : "Active",
		data : "isActive",
		orderable : false,
		searchable : false
	}, {
		title : "Action",
		data : "action",
		orderable : false,
		searchable : false
	} ]
});

$(function() {
	loadData();
});

function loadData() {
	showLoaderRight(true);
	var url = "user/fetchDetails";
	var doneCallbackFun = function(responseObj) {
		var users = responseObj;
		var usersData = [];
		for (var i = 0; i < users.length; i++) {
			var user = users[i];
			var isActiveStr = '';
			if (activationIsAllowed) {
				isActiveStr = '<div class="ui fitted toggle checkbox"><input type="checkbox" ';
				isActiveStr += (user.isActive == 1) ? 'checked="checked" ' : '';
				isActiveStr += 'onclick="activationFun(\'' + user.userId + '\',this)" /><label></label></div>';
			}
			var isActionStr = '';
			if (updateIsAllowed) {
				isActionStr = '<a class="btn" onclick="editFun(\'' + user.userId + '\')"><i class="fas fa-edit"></i></a>';
			}
			usersData.push({
				userName : user.userName,
				fullName : user.fullName,
				emailId : user.emailId,
				mobileNo : user.mobileNo,
				roleName : user.roleName,
				isActive : isActiveStr,
				action : isActionStr
			});
		}
		usersDataTable.clear().rows.add(usersData).draw();
		showLoaderRight(false);

		return users;
	};

	return callAjaxPostFun(url, null, doneCallbackFun, errorFun1);
}

function editFun(userId) {
	$("#linkId").val(userId);
	$("#linkForm").attr("action", contextPath + "user/update");
	$("#linkForm").submit();

	return false;
}

function activationFun(userId, checkBoxObj) {
	showLoaderRight(true);
	var isActive = 0;
	if (checkBoxObj.checked) {
		isActive = 1;
	}
	var url = "user/activation";
	var data = {
		userId : userId,
		isActive : isActive
	};
	var doneCallbackFun = function(responseObj) {
		if (responseObj.status == "success") {
			bootbox.alert({
				message : responseObj.msg,
				backdrop : true
			});
		}
		showLoaderRight(false);

		return responseObj;
	};

	return callAjaxPostFun(url, data, doneCallbackFun, errorFun1);
}