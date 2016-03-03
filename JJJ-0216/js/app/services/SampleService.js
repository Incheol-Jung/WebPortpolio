/**
 * Created by sjc on 2016-03-03.
 */
app.service('SampleService', function($http) {

	this.getResultAll = function(storeid, ps, pn, callback) {
		var url = "http://183.99.40.77:8282/WebPortpolioApiSvr/TestRead";
		console.log('url: ', url);
		$http.get(url).then(callback);
	};
/*
	this.getResultsPage = function(storeid, ps, pn, callback) {
		var url = "http://192.168.1.92:9051/item?pn=" + pn + "&ps=" + ps + "&storeId=" + storeid;
		console.log('url: ', url);
		$http.get(url).then(callback);
	};
*/
});