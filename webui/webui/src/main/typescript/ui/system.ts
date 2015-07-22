var systemModule: angular.IModule = angular.module("systemModule", []);

systemModule.directive('loggedInUsers', function() {
    return {
        restrict: "E",
        scope: {},
        controller: "loggedInUsersCtrl",
        templateUrl: "directives/logged-in-users.html"
    };
});

systemModule.controller('loggedInUsersCtrl', function() { });