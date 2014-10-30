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
purifinityServer.factory('userAdministratorFactory', [
		'purifinityServerConnector', userAdministratorFactory ]);
purifinityServer.controller("projectListCtrl", projectListCtrl);

function purifinityServerConnector($http, $location, baseURL, authFactory,
		alerterFactory) {
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
			.success(function(data, status) {
				localStorage.setItem(serviceURL, JSON.stringify(data));
				successCallback(data, status);
			})
			//
			.error(
					function(data, status, error) {
						if (status == 401) {
							alerterFactory.addAlert("info", data);
							authFactory.redirect = "/overview";
							$location.path("/login");
							return;
						}
						alerterFactory.addAlert("error", "HTTP Status: "
								+ status + "\n" + data);
						var data = localStorage.getItem(serviceURL);
						errorCallback(data, status, error);
					});
		}
	};
}

function userAdministratorFactory(purifinityServerConnector) {
	var userAdministratorFactory = {};
	userAdministratorFactory.getUsers = function(success, error) {
		return purifinityServerConnector.get('/accountmanager/rest/users',
				success, error);
	};
	userAdministratorFactory.getRoles = function(success, error) {
		return purifinityServerConnector.get('/accountmanager/rest/roles', //
		function(data, status) {
			setRoles(data)
		}, //
		function(data, status, error) {
			setError(error);
		});
	};
	return userAdministratorFactory;
}

function projectListCtrl($scope, $location, purifinityServerConnector,
		authFactory, alerterFactory) {
	purifinityServerConnector.get(
			'/purifinityserver/rest/analysisstore/projects', //
			function(data, status) {
				$scope.projects = data;
			}, // 
			function(data, status, error) {
				if (data) {
					alerterFactory.addAlert("warning",
							"Data was taken from local cache.");
					$scope.projects = JSON.parse(data);
				} else {
					alerterFactory.addAlert("danger", error);
				}
			});
}
