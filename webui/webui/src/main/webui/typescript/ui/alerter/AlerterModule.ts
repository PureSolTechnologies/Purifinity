/*
 * This JavaScript file contains a alerter module which provides the central
 * functionality for dangers, warnings, infos, and successes.
 */
var alerterModule: angular.IModule = angular.module("alerter", []);

/*
 * The clearAlerts directive provides a 'clear-alerts' directive for alerters
 * ng-repeat to be used to clear all alerts to not be shown again.
 */
alerterModule.directive("clearAlerts", ["alerterFactory", function(alerterFactory) {
    return function(scope, element, attrs) {
        if (scope.$last) {
            alerterFactory.clear();
        }
    };
}]);

/*
 * The alerterFactory is the singleton instance which saves the alerts to be
 * shown. This factory can be used by any controller, factory and module.
 */
alerterModule.factory("alerterFactory", function(): Alerter {
    return new Alerter();
});

alerterModule.directive("alerter", function() {
    return {
        restrict: "E",
        controller: "alerterCtrl",
        templateUrl: "directives/alerter.html"
    };
});

/*
 * The alerterCtrl is the control for the alert messages to be shown in UI.
 */
alerterModule.controller("alerterCtrl", ["$scope", "alerterFactory", function($scope, alerterFactory) {
    $scope.alerts = alerterFactory.alerts;
    /*
     * Type: info, danger, success, warning
     */
    $scope.addAlert = function(severity, message) {
        if (severity && message) {
            alerterFactory.addAlert(severity, message);
        }
    };
    $scope.closeAlert = function(index) {
        alerterFactory.closeAlert(index);
    };
    $scope.clearAlerts = function() {
        alerterFactory.clear();
    };
    $scope.hasAlerts = function() {
        return alerterFactory.hasAlerts();
    };
}]);
