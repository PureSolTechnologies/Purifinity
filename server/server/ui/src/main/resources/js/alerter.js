/*
 * This JavaScript file contains a alerter module which provides the central
 * functionality for alerts, warnings, infos and the hint of re-used cached
 * data.
 */
var alerterModule = angular.module("alerterModule", [ "purifinityServer" ]);
alerterModule.directive("clearAlerts", function(alerterFactory) {
	return function(scope, element, attrs) {
		if (scope.$last) {
			alerterFactory.clear();
		}
	};
});
alerterModule.factory("alerterFactory", function() {
	var alerter = {};
	alerter.alerts = [];
	/*
	 * Type: info, danger, success, warning
	 */
	alerter.addAlert = function(type, message) {
		alerter.alerts.push({
			type : type,
			message : message
		});
	}
	alerter.closeAlert = function(index) {
		alerter.alerts.splice(index, 1);
	};
	alerter.clear = function() {
		alerter.alerts = [];
	};
	return alerter;
});
alerterModule.controller("alerterCtrl", function($scope, alerterFactory) {
	$scope.alerts = alerterFactory.alerts;
	/*
	 * Type: info, danger, success, warning
	 */
	$scope.addAlert = function(type, message) {
		alerterFactory.addAlert(type, message);
	}
	$scope.closeAlert = function(index) {
		alerterFactory.closeAlert(index);
	};
});
