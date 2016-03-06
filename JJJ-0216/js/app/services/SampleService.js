/**
 * Created by sjc on 2016-03-03.
 */
app.service('SampleService', function($http) {
	var apiUrl = "http://183.99.40.77:8282/WebPortpolioApiSvr";
	//var apiUrl = "http://localhost:10001/WebPortpolioApiSvr";
	this.getResultAll = function(callback) {
		var url = apiUrl + "/TestRead";
		console.log('url: ', url);
		$http.get(url).then(callback);
	};

	this.getResultsPage = function(testId, callback) {
		var url = apiUrl+"/TestRead/"+testId;
		console.log('url: ', url);
		$http.get(url).then(callback);
	};

	this.insertSamplePage = function(requestMapper, callback) {
		var url = apiUrl+"/TestCreate/"+requestMapper.name+"/"+requestMapper.age+"/"+requestMapper.active;
		console.log('url: ', url);
		$http.get(url).then(callback);
	};

	this.deleteSample = function(requestMapper, callback) {
		var url = apiUrl+"/TestDelete/"+requestMapper.sampleId;
		console.log('url: ', url);
		$http.get(url).then(callback);
	};



});