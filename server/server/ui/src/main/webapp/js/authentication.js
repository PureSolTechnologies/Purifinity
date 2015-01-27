/*
 * This JavaScript files contains Angular JS functionality to be added to an
 * application to handle Authentication and Authorization against Purifinity
 * servers.
 *
 * This function adds Authentication and Authorization functionality to an
 * AngularJS application.
 */
var authModule = angular.module("authenticationModule", [ "purifinityServer" ]);
authModule.factory('authFactory', [ '$http', '$location', 'baseURL',
		authFactory ]);
authModule.controller('loginCtrl', loginCtrl);

function authFactory($http, $location, baseURL) {
	var authFactory = {};
	/* Initialize authData from storage. */
	authFactory.authData = loadAuthData();
	/* Login functionality */
	authFactory.login = function(email, password, remember) {
		var authenticated = false;
		return $http({
			method : "post",
			url : baseURL + "/purifinityserver/rest/auth/login",
			data : {
				email : email,
				password : password
			}
		})//
		.success(
				function(data, status) {
					authFactory.authData = data;
					authFactory.authData.email = email;
					authenticated = true;
					storeAuthData(authFactory.authData, remember);
					if (authFactory.redirect) {
						$location.path(authFactory.redirect);
					} else {
						$location.path("#");
					}
				})//
		.error(function(data, status) {
			authFactory.authData = undefined;
			// Error handling
		});
		return authenticated;
	};
	/* Logout functionality */
	authFactory.logout = function() {
		var test = "Test";
		$http({
			method : "post",
			url : baseURL + "/purifinityserver/rest/auth/logout",
			data : {
				authId : authFactory.authData.authId,
				token : authFactory.authData.authToken
			}
		})//
		.success(function(data, status) {
			authFactory.authData = data;
			removeAuthData();
		})//
		.error(function(data, status) {
			authFactory.authData = undefined;
			// Error handling
		});
	};
	/* Check for login functionality */
	authFactory.isLoggedIn = function() {
		if (authFactory.authData) {
			return authFactory.authData.authToken;
		} else {
			return false;
		}
	}
	return authFactory;
}

/**
 * This function is the controller for the login box and logout box.
 */
function loginCtrl($scope, authFactory) {
	$scope.message = authFactory.message;
	$scope.authId = undefined;
	$scope.email = undefined;
	$scope.password = undefined;
	$scope.remember = undefined;
	$scope.login = function() {
		authFactory.login($scope.email, $scope.password, $scope.remember);
		$scope.password = undefined;
	};
	$scope.logout = function() {
		authFactory.logout();
	};
	$scope.isLoggedIn = function() {
		return authFactory.isLoggedIn();
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
	if (data) {
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