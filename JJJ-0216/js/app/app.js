/**
 * Created by sjc on 2016-03-03.
 */
angular.module('Sample'	, []);

var app =
    angular.module('JJJPjt',
	    [
		    'Sample',
            'ngRoute'
	    ]
    ).config(['$routeProvider',
        function ($routeProvider) {
            $routeProvider.
            when('/sampleDetail/:testId', {
                templateUrl: 'sampleDetail.html',
                controller: 'SampleDetailController',
                action: '/sampleDetail/:testId'
            })
            .otherwise({ redirectTo: "/" });
        }]);