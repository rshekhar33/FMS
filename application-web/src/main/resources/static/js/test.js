function testFun() {
	var deferreds = [];
	for (var i = 0; i < 10; i++) {
		deferreds.push(loadDataFun1(i));
	}
	var callbackFun = function() {
		console.log("inside callbackFun");
		console.log(arguments);
	};

	whenAllDone(deferreds, callbackFun);
	chainCalls.chain3().then(function(data) {
		console.log("inside chain3 done");
		console.log(data);
	}).fail(function(data) {
		console.log("inside chain3 fail");
		console.log(data);
	});
}

function testFun2() {
	var deferreds = [];
	deferreds.push(loadDataFun1(10));
	deferreds.push(loadDataFun2(10));
	var callbackFun = function() {
		console.log("inside callbackFun of deferreds");
		console.log(arguments);
	};

	whenAllDone(deferreds, callbackFun);

	$.when(loadDataFun1(11), loadDataFun2(12)).then(function(resp1, resp2) {
		console.log("inside then of when");
		console.log(resp1);
		console.log(resp2);
	}).fail(function(err) {
		console.log("inside when-then-fail ", err);
	});
}

function loadDataFun1(aVar) {
	console.log("inside loadDataFun1 ", aVar);
	var url = "courseType/fetchData";
	var data = {
		courseTypeId : 1
	};
	var doneCallbackFun = function(responseObj) {
		console.log("inside successFun of loadDataFun1 ", aVar);
		var courseType = responseObj;

		courseType.aVar = aVar;
		
		return courseType;
	};

	return callAjaxPostFun(url, data, doneCallbackFun, errorFun1).then(function(resp1) {
		console.log("inside thenFun of loadDataFun1 ", aVar);
		return resp1;
	});
}

function loadDataFun2(aVar) {
	console.log("inside loadDataFun2 ", aVar);
	var url = "courseType/fetchDetails";
	var doneCallbackFun = function(responseObj) {
		console.log("inside successFun of loadDataFun2 ", aVar);
		a.b;
		return responseObj;
	};

	return callAjaxPostFun(url, null, doneCallbackFun, errorFun1).then(function(resp2) {
		console.log("inside thenFun of loadDataFun2 ", aVar);
		return resp2;
	}).catch(function(error) {
		console.log("Inside then-fail of loadDataFun2 ", aVar);
		console.log("Inside then-fail of loadDataFun2 error ", error);
		return null;
	});
}

var chainCalls = {
	chain1 : function() {
		return loadDataFun1(10).then(function(data) {
			console.log("inside chain1 promise");
			return data.courseType;
		});
	},
	chain2 : function() {
		return this.chain1().then(function(courseType) {
			console.log("inside chain2 promise");
			return courseType.courseTypeCode;
		});
	},
	chain3 : function() {
		return this.chain2().then(function(courseTypeCode) {
			console.log("inside chain3 promise");
			return courseTypeCode + " " + courseTypeCode + " " + courseTypeCode;
		});
	}
};