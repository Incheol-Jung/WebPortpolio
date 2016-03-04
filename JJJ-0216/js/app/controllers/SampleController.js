/**
 * Created by sjc on 2016-03-03.
 */
angular.module('Sample')
.controller('SampleController', function($scope, SampleService) {
	$scope.items = [];
	console.log('Call SampleController!!!');

	getResultAll();

	/*
	$scope.sampleDetail = function(sampleId){
		console.log('sampleDetail');
		console.log(sampleId);
	}
	*/

	$scope.sampleDelete = function(sampleId){
		console.log('sampleDelete');
		console.log(sampleId);
		deleteSample(sampleId);
	}

	function getResultAll() {
		console.log('getResultAll');
		SampleService.getResultAll(function(result) {
			//console.log('result: ', JSON.stringify(result));
			$scope.items = result.data.data;
			//$scope.totalItems = result.data.data.totalCount;
		});
	};

	function deleteSample(sampleId){
		var requestMapper = {};
		requestMapper.sampleId = sampleId;
		SampleService.deleteSample(requestMapper, function(result) {
			getResultAll();
		});
	}
})
.controller('SampleDetailController', function($scope, $location, SampleService) {
		console.log($location);

	console.log($location.search().sampleId);
	$scope.items = [];
	console.log('Call SampleDetailController!!!');
	getResultsPage(3);      //id 3번으로 고정해서 가져오고 있음.

	function getResultsPage(testId) {
		SampleService.getResultsPage(testId, function(result) {
			//console.log('result: ', JSON.stringify(result));
			$scope.items = result.data.data;
			//$scope.totalItems = result.data.data.totalCount;
		});
	};
})
.controller('SampleInsertController', function($scope, SampleService) {
	console.log($scope);
	$scope.items = [];
	console.log('Call SampleInsertController!!!');

	$scope.pagination = {
		current: 1
	};

	$scope.save = function(){
		insertSample();
	}

	$scope.pageChanged = function(newPage) {
		getResultsPage(newPage);
	};

	function insertSample(){
		var requestMapper = {};
		requestMapper.name = $scope.sample.name;
		requestMapper.age = $scope.sample.age;
		requestMapper.active = $scope.sample.active;
		console.log(requestMapper);

		SampleService.insertSamplePage(requestMapper, function(result) {
			$scope.sample.success = result.data.success;
		});
	}
})