/*
 * This JavaScript file contains the connector and connection logics for Purifinity
 * servers. The main purpose here is to encapsulate the HTTP logic for the requests itself
 * and also the handling of authentication.
 *
 * This function adds functionality to an AngularJS application which is needed
 * to handle Purifinity servers efficiently.
 */

var purifinityServer = angular.module("purifinityServer", [ 'alerter' ]);
/*
 * Add baseURL constant as it is read out of the configuration for later use in
 * modules and controllers.
 */
purifinityServer.constant("baseURL", "http://" + server.host + ":" + server.port)
purifinityServer.constant("authAPI", "/purifinityserver/rest/auth");

purifinityServer.factory("authURLs", authURLs);
purifinityServer.factory("purifinityServerConnector", [ '$http', '$location', 'authService', 'httpRequests', 'baseURL', 'alerterFactory', purifinityServerConnector ]);
purifinityServer.factory('authService', [ '$http', '$location', 'httpRequests', 'authURLs', authService ]);
purifinityServer.factory('httpRequests', [ '$http', '$location', 'alerterFactory', httpRequests ]);

purifinityServer.controller('loginCtrl', loginCtrl);
		
function authURLs(baseURL, authAPI) {
	var urls = {};
	urls.login = baseURL + authAPI + "/login";
	urls.logout = baseURL + authAPI + "/logout";
	return urls;
}
		
function purifinityServerConnector($http, $location, authService, httpRequests, baseURL, alerterFactory) {
	var factory = {};
	factory.get = function(serviceURL, successCallback, errorCallback) {
		var authId = '';
		var authToken = '';
		if (authService.authData) {
			authId = authService.authData.authId;
			authToken = authService.authData.authToken;
		}
		return httpRequests.get(baseURL + serviceURL, authId, authToken, successCallback, errorCallback);
	};
	factory.put = function(serviceURL, data, successCallback, errorCallback) {
		var authId = '';
		var authToken = '';
		if (authService.authData) {
			authId = authService.authData.authId;
			authToken = authService.authData.authToken;
		}
		return httpRequests.put(baseURL + serviceURL, data, authId, authToken, successCallback, errorCallback);
	};
	factory.post = function(serviceURL, data, successCallback, errorCallback) {
		var authId = '';
		var authToken = '';
		if (authService.authData) {
			authId = authService.authData.authId;
			authToken = authService.authData.authToken;
		}
		return httpRequests.post(baseURL + serviceURL, data, authId, authToken, successCallback, errorCallback);
	};
	factory.del = function(serviceURL, errorCallback) {
		var authId = '';
		var authToken = '';
		if (authService.authData) {
			authId = authService.authData.authId;
			authToken = authService.authData.authToken;
		}
		return httpRequests.del(baseURL + serviceURL, authId, authToken, successCallback, errorCallback);
	};
	return factory;
}

function httpRequests($http, $location, alerterFactory) {
	var service = {};
	service.get = function(url, authId, authToken, successCallback, errorCallback) {
		return $http(
			{
				method : 'GET',
				url : url,
				headers : {
					'auth-id' : authId,
					'auth-token' : authToken
				}
			}
		)
		.success(
			function(data, status) {
				localStorage.setItem(url, JSON.stringify(data));
				successCallback(data, status);
			}
		)
		.error(
			function(data, status, error) {
				if (status == 401) {
					alerterFactory.addAlert("info", data);
					authService.redirect = $location;
					$location.path("/login");
					return;
				}
				alerterFactory.addAlert("error", "HTTP Status: " + status + "\n" + data);
				var data = localStorage.getItem(url);
				errorCallback(data, status, error);
			}
		);
	}
	service.post = function(url, data, authId, authToken, successCallback, errorCallback) {
		return $http(
			{
				method : 'POST',
				url : url,
				headers : {
					'auth-id' : authId,
					'auth-token' : authToken
				},
				data: data
			}
		)
		.success(
			function(data, status) {
				successCallback(data, status);
			}
		)
		.error(
			function(data, status, error) {
				if (status == 401) {
					alerterFactory.addAlert("info", data);
					authService.redirect = $location;
					$location.path("/login");
					return;
				}
				alerterFactory.addAlert("error", "HTTP Status: " + status + "\n" + data);
				errorCallback(data, status, error);
			}
		);
	}
	service.put = function(url, data, authId, authToken, successCallback, errorCallback) {
		return $http(
			{
				method : 'PUT',
				url : url,
				headers : {
					'auth-id' : authId,
					'auth-token' : authToken
				},
				data: data
			}
		)
		.success(
			function(data, status) {
				successCallback(data, status);
			}
		)
		.error(
			function(data, status, error) {
				if (status == 401) {
					alerterFactory.addAlert("info", data);
					authService.redirect = $location;
					$location.path("/login");
					return;
				}
				alerterFactory.addAlert("error", "HTTP Status: " + status + "\n" + data);
				errorCallback(data, status, error);
			}
		);
	}
	service.del = function(url, authId, authToken, successCallback, errorCallback) {
		return $http(
			{
				method : 'DELETE',
				url : url,
				headers : {
					'auth-id' : authId,
					'auth-token' : authToken
				}
			}
		)
		.success(
			function(data, status) {
				successCallback(data, status);
			}
		)
		.error(
			function(data, status, error) {
				if (status == 401) {
					alerterFactory.addAlert("info", data);
					authService.redirect = $location;
					$location.path("/login");
					return;
				}
				alerterFactory.addAlert("error", "HTTP Status: "
						+ status + "\n" + data);
				errorCallback(data, status, error);
			}
		);
	}
	return service;
}

function authService($http, $location, httpRequests, authURLs, purifinityServerConnector) {
	var service = {};
	/* Initialize authData from storage. */
	service.authData = loadAuthData();
	service.redirect = "/";
	/* Login functionality */
	service.login = function(email, password, remember) {
		var authenticated = false;
		var data = {
				email : email,
				password : password
			};
		httpRequests.post(authURLs.login, data, '', '', 
			function(data, status) {
				service.authData = data;
				service.authData.email = email;
				authenticated = true;
				storeAuthData(service.authData, remember);
				if (service.redirect) {
					$location.path(service.redirect);
				} else {
					$location.path("/");
				}
			}, //
			function(data, status) {
				service.authData = undefined;
				authenticated = false;
				removeAuthData();
				// Error handling
			}
		);
		return authenticated;
	};
	/* Logout functionality */
	service.logout = function() {
		var data = {
				authId : service.authData.authId,
				token : service.authData.authToken
			};
		httpRequests.post(authURLs.logout, data, '', '', 
			function(data, status) {
				service.authData = undefined;
				removeAuthData();
			},
			function(data, status) {
				service.authData = undefined;
				removeAuthData();
				// Error handling
			}
		);
	};
	/* Check for login functionality */
	service.isLoggedIn = function() {
		if (service.authData) {
			return service.authData.authToken;
		} else {
			return false;
		}
	}
	return service;
}

/**
 * This function is the controller for the login box and logout box.
 */
function loginCtrl($scope, authService) {
	$scope.message = authService.message;
	$scope.authId = undefined;
	$scope.email = undefined;
	$scope.password = undefined;
	$scope.remember = undefined;
	$scope.login = function() {
		if ($scope.email.indexOf('@') <= -1) {
			if (authenticationSettings.defaultDomain) {
				$scope.email = $scope.email + authenticationSettings.defaultDomain;
			}
		}
		authService.login($scope.email, $scope.password, $scope.remember);
		$scope.password = undefined;
	};
	$scope.logout = function() {
		authService.logout();
	};
	$scope.isLoggedIn = function() {
		return authService.isLoggedIn();
	};
}

/**
 * This function reads authentication data from storage.
 */
function loadAuthData() {
	var data = localStorage.getItem("purifinity-authentication");
	if (!data) {
		data = sessionStorage.getItem("purifinity-authentication");
	}
	if (data && data != "undefined") {
		 return JSON.parse(data);
	}
	return undefined;
}

/**
 * This function write authentication data to storage.
 */
function storeAuthData(authData, remember) {
	if (remember) {
		localStorage.setItem("purifinity-authentication", JSON.stringify(authData));
		sessionStorage.removeItem("purifinity-authentication");
	} else {
		localStorage.removeItem("purifinity-authentication");
		sessionStorage.setItem("purifinity-authentication", JSON.stringify(authData));
	}
}

function removeAuthData() {
	localStorage.removeItem("purifinity-authentication");
	sessionStorage.removeItem("purifinity-authentication");
}
