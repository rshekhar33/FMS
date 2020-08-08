var ListController = {
	elements: {},
	modulesDataTable: null,

	findElements: function () {
		this.elements.modulesTable = $("#modulesTable");
		this.elements.linkId = $("#linkId");
		this.elements.linkForm = $("#linkForm");

		return this;
	},
	addClickEvents: function () {
		var base = this;

		base.elements.modulesTable.on("click", "input.actionActivate", function () {
			base.activationFun(this);
		});

		base.elements.modulesTable.on("click", "a.actionEdit", function () {
			base.editFun(this);
		});

		return base;
	},
	initDT: function () {
		this.modulesDataTable = this.elements.modulesTable.DataTable({
			ordering: true,
			info: true,
			autoWidth: false,
			paging: true,
			responsive: true,
			pagingType: "full_numbers",
			columns: [{
				title: "Module Name",
				data: "moduleName"
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
		var url = "module/fetchDetails";
		var doneCallbackFun = function (responseObj) {
			var modules = responseObj;
			var modulesData = [];
			for (var i = 0; i < modules.length; i++) {
				var module = modules[i];
				var isActiveStr = '';
				if (activationIsAllowed) {
					isActiveStr = '<div class="ui fitted toggle checkbox"><input type="checkbox" class="actionActivate" ';
					isActiveStr += (module.isActive == 1) ? 'checked="checked" ' : '';
					isActiveStr += 'data-module-id="' + module.moduleId + '" /><label></label></div>';
				}
				var isActionStr = '';
				if (updateIsAllowed) {
					isActionStr = '<a class="btn actionEdit" data-module-id="' + module.moduleId + '"><i class="fas fa-edit"></i></a>';
				}
				modulesData.push({
					moduleName: module.moduleName,
					isActive: isActiveStr,
					action: isActionStr
				});
			}
			base.modulesDataTable.clear().rows.add(modulesData).draw();
			Loader.showLoaderRight(false);

			return modules;
		};

		return AjaxFn.callPostFun(url, null, doneCallbackFun, ErrorFn.errorFun1);
	},
	activationFun: function (checkBoxObj) {
		var moduleId = $(checkBoxObj).data("moduleId");

		Loader.showLoaderRight(true);
		var isActive = 0;
		if (checkBoxObj.checked) {
			isActive = 1;
		}
		var url = "module/activation";
		var data = {
			moduleId: moduleId,
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
		var moduleId = $(anchorObj).data("moduleId");

		this.elements.linkId.val(moduleId);
		this.elements.linkForm.attr("action", contextPath + "module/update");
		this.elements.linkForm.submit();
	},
	initialize: function () {
		this.findElements().addClickEvents().initDT().loadDataFun();
	}
};

$(function () {
	ListController.initialize();
});