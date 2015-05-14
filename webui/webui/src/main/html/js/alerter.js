/*
 * This JavaScript file contains a alerter module which provides the central
 * functionality for dangers, warnings, infos, and successes.
 */
var alerterModule = angular.module("alerter", [ "purifinityServer" ]);
/*
 * The clearAlerts directive provides a 'clear-alerts' directive for alerters
 * ng-repeat to be used to clear all alerts to not be shown again.
 */
alerterModule.directive("clearAlerts", clearAlerts);
/*
 * The alerterFactory is the singleton instance which saves the alerts to be
 * shown. This factory can be used by any controller, factory and module.
 */
alerterModule.factory("alerterFactory", alerterFactory);
/*
 * The alerterCtrl is the control for the alert messages to be shown in UI.
 */
alerterModule.controller("alerterCtrl", alerterCtrl);

function clearAlerts(alerterFactory) {
	return function(scope, element, attrs) {
		if (scope.$last) {
			alerterFactory.clear();
		}
	};
}

function alerterFactory() {
	var alerter = {};
	alerter.alerts = [];
	/*
	 * Type: info, danger, success, warning
	 */
	alerter.addAlert = function(type, message) {
		if (type && message) {
			alerter.alerts.push({
				type : type,
				message : message
			});
		}
	}
	alerter.closeAlert = function(index) {
		alerter.alerts.splice(index, 1);
	};
	alerter.clear = function() {
		alerter.alerts.splice(0, alerter.alerts.length)
	};
	alerter.hasAlerts = function() {
		if (alerter.alerts == undefined) {
			return false;
		}
		return alerter.alerts.length > 0;
	}
	return alerter;
}

function alerterCtrl($scope, alerterFactory) {
	$scope.alerts = alerterFactory.alerts;
	/*
	 * Type: info, danger, success, warning
	 */
	$scope.addAlert = function(type, message) {
		if (type && message) {
			alerterFactory.addAlert(type, message);
		}
	}
	$scope.closeAlert = function(index) {
		alerterFactory.closeAlert(index);
	};
	$scope.clearAlerts = function() {
		alerterFactory.clear();
	};
	$scope.hasAlerts = function() {
		return alerterFactory.hasAlerts();
	}
}