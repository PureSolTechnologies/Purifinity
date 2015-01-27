/*
 * This JavaScript files contains Angular JS functionality to be added to an
 * application to handle Authentication and Authorization against Purifinity
 * servers.
 *
 * This function adds Authentication and Authorization functionality to an
 * AngularJS application.
 */
var authModule = angular.module("authService", [ "purifinityServer" ]);
authModule.factory('authFactory', [ '$http', '$location', 'baseURL',
		authFactory ]);
authModule.controller('authCtrl', authCtrl);

function authFactory($http, $location, baseURL) {
	var authFactory = {
		authData : undefined
	};
	/* Check local storage for authentication information. */
	var data = localStorage.getItem("purifinity-authentication");
	if (!data) {
		data = sessionStorage.getItem("purifinity-authentication");
	}
	if (data) {
		authFactory.authData = JSON.parse(data);
	}
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
					authenticated = true;
					if (remember) {
						localStorage.setItem("purifinity-authentication", JSON
								.stringify(data));
						sessionStorage.removeItem("purifinity-authentication");
					} else {
						localStorage.removeItem("purifinity-authentication");
						sessionStorage.setItem("purifinity-authentication",
								JSON.stringify(data));
					}
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
			localStorage.removeItem("purifinity-authentication");
		})//
		.error(function(data, status) {
			authFactory.authData = undefined;
			// Error handling
		});
	};
	authFactory.isLoggedIn = function() {
		if (authFactory.authData) {
			return authFactory.authData.authToken;
		} else {
			return false;
		}
	}
	return authFactory;
}

function authCtrl($scope, authFactory) {
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
