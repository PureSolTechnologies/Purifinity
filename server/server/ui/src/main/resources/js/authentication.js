/*
 * This JavaScript files contains Angular JS functionality to be added to an
 * application to handle Authentication and Authorization against Purifinity
 * servers.
 */

/**
 * This function adds Authentication and Authorization functionality to an
 * AngularJS application.
 */
function addAuthFunctionality(angularApplication) {
	angularApplication.factory('authFactory',
			[ '$http', '$location', 'baseURL', authFactory ]);
	angularApplication.controller('loginCtrl', loginCtrl);
	angularApplication.controller('logoutCtrl', logoutCtrl);
}

function authFactory($http, $location, baseURL) {
	var authFactory = {
		authData : undefined
	};
	/* Check local storage for authentication information. */
	var data = localStorage.getItem("purifinity-authentication");
	if (data) {
		authFactory.authData = JSON.parse(data);
	}
	authFactory.login = function(username, password, remember) {
		var authenticated = false;
		return $http({
			method : "post",
			url : baseURL + "/purifinityserver/rest/auth/login",
			data : {
				username : username,
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
					} else {
						localStorage.removeItem("purifinity-authentication");
					}
					if (authFactory.redirect) {
						$location.path(authFactory.redirect);
					}
				})//
		.error(function(data, status) {
			authFactory.authData = undefined;
			// Error handling
		});
		return authenticated;
	};
	authFactory.logout = function() {
		return $http({
			method : "post",
			url : baseURL + "/purifinityserver/rest/auth/logout",
			data : {
				username : authFactory.authData.authId,
				token : authFactory.authData.authToken
			}
		});
	};
	return authFactory;
}

function loginCtrl($scope, authFactory) {
	$scope.message = authFactory.message;
	$scope.username = undefined;
	$scope.password = undefined;
	$scope.remember = undefined;
	$scope.login = function() {
		authFactory.login($scope.username, $scope.password, $scope.remember);
	};
}

function logoutCtrl($scope, $location, authFactory) {
	$scope.logout = function() {
		authFactory.logout();
	}
}
