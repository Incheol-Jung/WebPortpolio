/**
 * Created by sjc on 2016-03-03.
 */
app.service('SampleService', function($http) {

	this.getResultAll = function(callback) {
		var url = "http://183.99.40.77:8282/WebPortpolioApiSvr/TestRead";
		console.log('url: ', url);
		$http.get(url).then(callback);
	};

	this.getResultsPage = function(testId, callback) {
		var url = "http://183.99.40.77:8282/WebPortpolioApiSvr/TestRead/"+testId;
		console.log('url: ', url);
		$http.get(url).then(callback);
	};

});