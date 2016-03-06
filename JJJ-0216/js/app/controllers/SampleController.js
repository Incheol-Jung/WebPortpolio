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
.controller('SampleDetailController', function ($scope, $location, SampleService, $routeParams, $route) {
    console.log($location);

    console.log($location.search().sampleId);
    console.log("dddddddddddddddddd === >" + $routeParams.testId);
    $scope.items = [];
    console.log('Call SampleDetailController!!!');
    getResultsPage($routeParams.testId);      //id 3번으로 고정해서 가져오고 있음.
    $scope.$on(
        "$routeChangeSuccess",
        function handleRouteChangeEvent(event) {
            var current = $route.current;
            console.log(
                "ROUTE CHANGE: [ %s ] .. [ Path: %s ]",
                current.originalPath,
                $location.path()
            );
            //$location.path(current.action);
            // If the current route doesn't contain an action, then it will,
            // in all likelihood, be redirected to another route that does
            // contain a valid action (configured in $routeProvider).
            // --
            // NOTE: This is not a native part of routing - this is because
            // my route object contains an "action" key in the configuration
            // in all cases in which a valid route is matched.
            if (!current.action) {
                console.warn("Route does not contain an action.");
            }
            // Store our current action for output.
            $scope.routeAction = current.action;
        }
    );

    

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