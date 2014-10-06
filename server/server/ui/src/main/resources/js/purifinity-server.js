/*
 * This JavaScript file contains connectors and connection logic for Purifinity
 * servers.
 *
 * This function adds functionality to an AngularJS application which is needed
 * to handle Purifinity servers efficiently.
 */

var purifinityServer = angular.module("purifinityServer", []);
/*
 * Add baseURL constant as it is read out of the configuration for later use in
 * modules and controllers.
 */
purifinityServer.constant("baseURL", "http://" + server.host + ":"
		+ server.port)
purifinityServer
		.factory("purifinityServerConnector", purifinityServerConnector);
purifinityServer.controller("projectListCtrl", projectListCtrl);

function purifinityServerConnector($http, $location, baseURL, authFactory) {
	return {
		get : function(serviceURL, successCallback, errorCallback) {
			var authId = '';
			var authToken = '';
			if (authFactory.authData) {
				authId = authFactory.authData.authId;
				authToken = authFactory.authData.authToken;
			}
			return $http({
				method : 'GET',
				url : baseURL + serviceURL,
				headers : {
					'auth-id' : authId,
					'auth-token' : authToken
				}
			})
			//
			.success(function(data) {
				localStorage.setItem(serviceURL, JSON.stringify(data));
				successCallback(data);
			})
			//
			.error(function(data, status, error) {
				if (status == 401) {
					authFactory.message = data;
					authFactory.redirect = "/overview";
					$location.path("/login");
					return;
				}
				var data = localStorage.getItem(serviceURL);
				errorCallback(data, status, error);
			});
		}
	};
}

function projectListCtrl($scope, $location, purifinityServerConnector,
		authFactory) {
	purifinityServerConnector.get(
			'/purifinityserver/rest/analysisstore/projects', //
			function(data) {
				$scope.projects = data;
			}, // 
			function(data, status, error) {
				if (data) {
					$scope.warning = "Data was taken from local cache.";
					$scope.projects = JSON.parse(data);
				} else {
					$scope.error = error;
				}
			});
}
