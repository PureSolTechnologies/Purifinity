var systemModule: angular.IModule = angular.module("systemModule", ["purifinityServerModule", "projectManagerModule"]);

systemModule.directive('loggedInUsers', function() {
    return {
        restrict: "E",
        scope: {},
        controller: "loggedInUsersCtrl",
        templateUrl: "directives/logged-in-users.html"
    };
});

systemModule.controller('loggedInUsersCtrl', function() { });

systemModule.directive('systemHealth', function() {
    return {
        restrict: "E",
        scope: {},
        controller: "systemHealthCtrl",
        templateUrl: "directives/system-health.html"
    };
});

systemModule.controller("systemHealthCtrl", ["$scope",
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

systemModule.directive('runningJobs', function() {
    return {
        restrict: "E",
        scope: {},
        controller: "runningJobsCtrl",
        templateUrl: "directives/running-jobs.html"
    };
});

systemModule.controller("runningJobsCtrl", ["$scope", "purifinityServerConnector",
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
    