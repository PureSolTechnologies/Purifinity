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
purifinityServer.controller("projectListCtrl", projectListCtrl);

function projectListCtrl($scope, $http, $location, baseURL, authFactory) {
	var authId = '';
	var authToken = '';
	if (authFactory.authData) {
		authId = authFactory.authData.authId;
		authToken = authFactory.authData.authToken;
	}
	$http({
		method : 'GET',
		url : baseURL + '/purifinityserver/rest/analysisstore/projects',
		headers : {
			'auth-id' : authId,
			'auth-token' : authToken
		}
	})
			.success(
					function(data) {
						$scope.projects = data;
						localStorage
								.setItem(
										"/purifinityserver/rest/analysisstore/projects",
										JSON.stringify(data));
					})
			.error(
					function(data, status, error) {
						if (status == 401) {
							authFactory.message = data;
							authFactory.redirect = "/overview";
							$location.path("/login");
							return;
						}
						var data = localStorage
								.getItem("/purifinityserver/rest/analysisstore/projects");
						if (data) {
							$scope.warning = "Data was taken from local cache.";
							$scope.projects = JSON.parse(data);
						} else {
							$scope.error = error;
						}
					});
}