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

purifinityServerModule.factory("httpRequests", ["$http", "$location", "alerterFactory",
    function($http, $location, alerterFactory) {
        return new HTTPRequests($http, $location, alerterFactory);
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

purifinityServerModule.controller("serverStatusCtrl", ["$scope",
    function($scope) {
        $scope.connection = "Not Connected.";
        $scope.error = undefined;
        if (!$scope.websocket) {
            var server = PurifinityConfiguration.server;
            $scope.websocket = new WebSocket("ws://" + server.host + ":" + server.port + "/purifinityserver/socket/status");
            $scope.websocket.onopen = function(event) {
                $scope.connection = "Connected.";
                $scope.$apply();
                $scope.websocket.send("sendStatus");
            }
            $scope.websocket.onclose = function(event) {
                $scope.connection = "Connection closed.";
                $scope.$apply();
            }
            $scope.websocket.onmessage = function(event) {
                $scope.status = JSON.parse(event.data);
                $scope.$apply();
            }
            $scope.websocket.onerror = function(event) {
                $scope.error = event;
                $scope.$apply();
            }
        }
        $scope.getUptimeString = function() {
            if (!$scope.status) {
                return "";
            }
            var milliseconds = $scope.status.uptime;
            if (milliseconds < 1000) {
                return milliseconds + "ms";
            }
            var seconds = Math.floor(milliseconds / 1000);
            milliseconds = milliseconds % 1000;
            if (seconds < 60) {
                return seconds + "s";
            }
            var minutes = Math.floor(seconds / 60);
            seconds = seconds % 60;
            if (minutes < 60) {
                return minutes + "min " + seconds + "s";
            }
            var hours = Math.floor(minutes / 60);
            minutes = minutes % 60;
            if (hours < 24) {
                return hours + "hr " + minutes + "min " + seconds + "s";
            }
            var days = Math.floor(hours / 24);
            hours = hours % 24;
            return days + "days " + hours + "hr " + minutes + "min " + seconds + "s";
        };
        $scope.getMemorySeverity = function() {
            if (!$scope.status || !$scope.status.usedMemory || !$scope.status.maxMemory) {
                return "";
            }
            var usage = $scope.status.usedMemory / $scope.status.maxMemory;
            if (usage < 0.75) {
                return "progress-bar-success";
            }
            if (usage < 0.9) {
                return "progress-bar-warning";
            }
            return "progress-bar-danger";
        };
        $scope.getCPUSeverity = function() {
            var usage = $scope.getCPUUsage();
            if (usage === "n/a") {
                return "";
            }
            if (usage < 0.75) {
                return "progress-bar-success";
            }
            if (usage < 0.9) {
                return "progress-bar-warning";
            }
            return "progress-bar-danger";
        };
        $scope.getCPUUsage = function() {
            if (!$scope.status || !$scope.status.averageLoad || ($scope.status.averageLoad < 0) || !$scope.status.availableCPUs) {
                return 0.0;
            }
            return $scope.status.averageLoad / $scope.status.availableCPUs;
        };
    }]);

purifinityServerModule.controller("jobStatesCtrl", ["$scope", "purifinityServerConnector",
    function($scope, purifinityServerConnector) {
        $scope.connection = "Not Connected.";
        $scope.error = undefined;
        if (!$scope.websocket) {
            var server = PurifinityConfiguration.server;
            $scope.websocket = new WebSocket("ws://" + server.host + ":" + server.port + "/purifinityserver/socket/jobs");
            $scope.websocket.onopen = function(event) {
                $scope.connection = "Connected.";
                $scope.$apply();
                $scope.websocket.send("sendJobStates");
            }
            $scope.websocket.onclose = function(event) {
                $scope.connection = "Connection closed.";
                $scope.$apply();
            }
            $scope.websocket.onmessage = function(event) {
                $scope.jobs = JSON.parse(event.data);
                $scope.$apply();
            }
            $scope.websocket.onerror = function(event) {
                $scope.error = event;
                $scope.$apply();
            }
        }
        $scope.abortRun = function(projectId, jobId) {
            purifinityServerConnector.put("/purifinityserver/rest/analysis/projects/" + projectId + "/abort/" + jobId, "",
                function(data, status) { },
                function(data, status, error) { }
                );
        };
    }]);
