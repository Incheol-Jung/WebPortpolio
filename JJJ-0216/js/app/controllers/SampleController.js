/**
 * Created by sjc on 2016-03-03.
 */
angular.module('Sample').controller('SampleController', function($scope, SampleService) {
	$scope.items = [];
	$scope.totalItems = 0;
	$scope.itemsPerPage = 5; // this should match however many results your API puts on one page
	console.log('Call SampleController!!!');
	//getResultsPage(1);

	getResultAll();

	$scope.pagination = {
		current: 1
	};

	$scope.pageChanged = function(newPage) {
		getResultsPage(newPage);
	};

	function getResultAll() {
		console.log('getResultAll');
		SampleService.getResultAll(function(result) {
			console.log('result: ', JSON.stringify(result));
			$scope.items = result.data.data;
			//$scope.totalItems = result.data.data.totalCount;
		});
	};

})
	.controller('SampleDetailController', function($scope, SampleService) {
		console.log($scope);
		$scope.items = [];
		console.log('Call SampleDetailController!!!');
		getResultsPage(3);

		$scope.pagination = {
			current: 1
		};

		$scope.pageChanged = function(newPage) {
			getResultsPage(newPage);
		};

		function getResultsPage(testId) {
			SampleService.getResultsPage(testId, function(result) {
				console.log('result: ', JSON.stringify(result));
				$scope.items = result.data.data;
				//$scope.totalItems = result.data.data.totalCount;
			});
		};

	})