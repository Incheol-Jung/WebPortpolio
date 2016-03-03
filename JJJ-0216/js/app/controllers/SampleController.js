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
		SampleService.getResultAll(function(result) {
			console.log('result: ', JSON.stringify(result));
			$scope.items = result.data.data.items;
			$scope.totalItems = result.data.data.totalCount;
		});
	};
/*
	function getResultsPage(pageNumber) {
		SampleService.getResultsPage(7332, $scope.itemsPerPage, pageNumber, function(result) {
			console.log('result: ', JSON.stringify(result));
			$scope.items = result.data.data.items;
			$scope.totalItems = result.data.data.totalCount;
		});
	};
*/
})