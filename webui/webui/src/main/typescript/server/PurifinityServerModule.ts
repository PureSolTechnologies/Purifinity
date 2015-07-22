/*
 * This JavaScript file contains the connector and connection logics for Purifinity
 * servers. The main purpose here is to encapsulate the HTTP logic for the requests itself
 * and also the handling of authentication.
 *
 * This function adds functionality to an AngularJS application which is needed
 * to handle Purifinity servers efficiently.
 */

var purifinityServerModule: angular.IModule = angular.module("purifinityServerModule", ["alerter"]);
/*
 * Add baseURL constant as it is read out of the configuration for later use in
 * modules and controllers.
 */
purifinityServerModule.constant("baseURL", "http://" + PurifinityConfiguration.server.host + ":" + PurifinityConfiguration.server.port)

purifinityServerModule.factory("purifinityServerConnector", ["authService", "httpRequests", "baseURL",
    function(authService, httpRequests, baseURL) {
        return new PurifinityServerConnector(authService, httpRequests, baseURL);
    }]);

purifinityServerModule.factory("authService", ["$location", "httpRequests", "baseURL", 
    function($location, httpRequests, baseURL) {
        return new AuthenticationService($location, httpRequests, baseURL);
    }]);

purifinityServerModule.factory("httpRequests", ["$http", "$location", "$window", "alerterFactory",
    function($http, $location, $window, alerterFactory) {
        return new HTTPRequests($http, $location, $window, alerterFactory);
    }]);

/**
 * This function is the controller for the login box and logout box.
 */
purifinityServerModule.controller("loginCtrl", ["$scope", "$route", "authService",
    function($scope, $route, authService) {
        $scope.message = authService.message;
        $scope.authId = undefined;
        $scope.email = undefined;
        $scope.password = undefined;
        $scope.login = function() {
            if ($scope.email.indexOf("@") <= -1) {
                var authSettings = PurifinityConfiguration.authentication;
                if (authSettings.defaultDomain) {
                    $scope.email = $scope.email + authSettings.defaultDomain;
                }
            }
            authService.login($scope.email, $scope.password);
        };
        $scope.logout = function() {
            authService.logout();
            $route.reload();
        };
        $scope.isLoggedIn = function() {
            return authService.isLoggedIn();
        };
    }]);
