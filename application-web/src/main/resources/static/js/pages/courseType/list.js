var ListController = {
	elements: {},
	courseTypesDataTable: null,

	findElements: function () {
		this.elements.courseTypesTable = $("#courseTypesTable");
		this.elements.linkId = $("#linkId");
		this.elements.linkForm = $("#linkForm");

		return this;
	},
	addClickEvents: function () {
		var base = this;

		base.elements.courseTypesTable.on("click", "input.actionActivate", function () {
			base.activationFun(this);
		});

		base.elements.courseTypesTable.on("click", "a.actionEdit", function () {
			base.editFun(this);
		});

		return base;
	},
	initDT: function () {
		this.courseTypesDataTable = this.elements.courseTypesTable.DataTable({
			ordering: true,
			info: true,
			autoWidth: false,
			paging: true,
			responsive: true,
			pagingType: "full_numbers",
			columns: [{
				title: "Course Type Code",
				data: "courseTypeCode"
			}, {
				title: "Course Type Name",
				data: "courseTypeName"
			}, {
				title: "No. Of Days",
				data: "noOfDays"
			}, {
				title: "Active",
				data: "isActive",
				orderable: false,
				searchable: false
			}, {
				title: "Action",
				data: "action",
				orderable: false,
				searchable: false
			}]
		});

		return this;
	},
	loadDataFun: function () {
		var base = this;

		Loader.showLoaderRight(true);
		var url = "courseType/fetchDetails";
		var doneCallbackFun = function (responseObj) {
			var courseTypes = responseObj;
			var courseTypesData = [];
			for (var i = 0; i < courseTypes.length; i++) {
				var courseType = courseTypes[i];
				var isActiveStr = '';
				if (activationIsAllowed) {
					isActiveStr = '<div class="ui fitted toggle checkbox"><input type="checkbox" class="actionActivate" ';
					isActiveStr += (courseType.isActive == 1) ? 'checked="checked" ' : '';
					isActiveStr += 'data-course-type-id="' + courseType.courseTypeId + '" /><label></label></div>';
				}
				var isActionStr = '';
				if (updateIsAllowed) {
					isActionStr = '<a class="btn actionEdit" data-course-type-id="' + courseType.courseTypeId + '"><i class="fas fa-edit"></i></a>';
				}
				courseTypesData.push({
					courseTypeCode: courseType.courseTypeCode,
					courseTypeName: courseType.courseTypeName,
					noOfDays: courseType.noOfDays,
					isActive: isActiveStr,
					action: isActionStr
				});
			}
			base.courseTypesDataTable.clear().rows.add(courseTypesData).draw();
			Loader.showLoaderRight(false);

			return courseTypes;
		};

		return AjaxFn.callPostFun(url, null, doneCallbackFun, ErrorFn.errorFun1);
	},
	activationFun: function (checkBoxObj) {
		var courseTypeId = $(checkBoxObj).data("courseTypeId");

		Loader.showLoaderRight(true);
		var isActive = 0;
		if (checkBoxObj.checked) {
			isActive = 1;
		}
		var url = "courseType/activation";
		var data = {
			courseTypeId: courseTypeId,
			isActive: isActive
		};
		var doneCallbackFun = function (responseObj) {
			if (responseObj.status == "success") {
				bootbox.alert({
					message: responseObj.msg,
					backdrop: true
				});
			}
			Loader.showLoaderRight(false);

			return responseObj;
		};

		return AjaxFn.callPostFun(url, data, doneCallbackFun, ErrorFn.errorFun1);
	},
	editFun: function (anchorObj) {
		var courseTypeId = $(anchorObj).data("courseTypeId");

		this.elements.linkId.val(courseTypeId);
		this.elements.linkForm.attr("action", contextPath + "courseType/update");
		this.elements.linkForm.submit();
	},
	initialize: function () {
		this.findElements().addClickEvents().initDT().loadDataFun();
	}
};

$(function () {
	ListController.initialize();
});