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
    