/*
 * This JavaScript files contains Angular JS functionality to be added to an
 * application to handle user accounts for Purifinity.
 */
var pluginManagerModule = angular.module("pluginManagerModule", [ "purifinityServer" ]);
pluginManagerModule.factory('pluginManager', [
		'purifinityServerConnector', pluginManager ]);
pluginManagerModule.controller("pluginSettingsCtrl", pluginSettingsCtrl);
pluginManagerModule.controller("pluginActivationCtrl", pluginActivationCtrl);

function pluginManager(purifinityServerConnector) {
	var pluginManager = {};
	pluginManager.getAnalyzers = function(success, error) {
		return purifinityServerConnector.get('/purifinityserver/rest/analysis/analyzers',
				success, error);
	};
	pluginManager.getEvaluators = function(success, error) {
		return purifinityServerConnector.get('/purifinityserver/rest/evaluation/evaluators',
				success, error);
	};
	pluginManager.getRepositoryTypes = function(success, error) {
		return purifinityServerConnector.get('/purifinityserver/rest/repositories/types',
				success, error);
	};
	return pluginManager;
}

function pluginSettingsCtrl($scope, pluginManager) {
	$scope.analyzers = {};
	$scope.evaluators = {};
	$scope.repositories = {};
	pluginManager.getAnalyzers(
		function(data, status) {
			$scope.analyzers = data;
		}, 
		function(data, status, error) {}
	);
	pluginManager.getEvaluators(
		function(data, status) {
			$scope.evaluators = data;
		}, 
		function(data, status, error) {}
	);
	pluginManager.getRepositoryTypes(
		function(data, status) {
			$scope.repositories = data;
		}, 
		function(data, status, error) {}
	);
}

function pluginActivationCtrl($scope, pluginManager) {
	$scope.analyzers = {};
	$scope.evaluators = {};
	$scope.repositories = {};
	pluginManager.getAnalyzers(
		function(data, status) {
			$scope.analyzers = data;
		}, 
		function(data, status, error) {}
	);
	pluginManager.getEvaluators(
		function(data, status) {
			$scope.evaluators = data;
		}, 
		function(data, status, error) {}
	);
}
