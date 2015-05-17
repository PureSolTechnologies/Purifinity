/*
 * This JavaScript files contains Angular JS functionality to be added to an
 * application to handle user accounts for Purifinity.
 */
var pluginManagerModule : angular.IModule = angular.module("pluginManagerModule", [ "purifinityServerModule" ]);
pluginManagerModule.factory('pluginManager', [
		'purifinityServerConnector', pluginManager ]);
pluginManagerModule.controller("pluginSettingsCtrl", pluginSettingsCtrl);
pluginManagerModule.controller("pluginActivationCtrl", pluginActivationCtrl);

function pluginManager(purifinityServerConnector) {
    return new PluginManager(purifinityServerConnector);
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
	$scope.analyzerActivation = {};
	$scope.evaluatorActivation = {};
	pluginManager.getAnalyzers(
		function(data, status) {
			$scope.analyzers = data;
			$scope.analyzers.forEach(function(analyzer) {
				pluginManager.isAnalyzerEnabled(analyzer.id, 
					function(data, status) {
						$scope.analyzerActivation[analyzer.id] = data;
					},
					function(data, status, error) {}
				);
			});
		}, 
		function(data, status, error) {}
	);
	pluginManager.getEvaluators(
		function(data, status) {
			$scope.evaluators = data;
			$scope.evaluators.forEach(function(evaluator) {
				pluginManager.isEvaluatorEnabled(evaluator.id, 
					function(data, status) {
						$scope.evaluatorActivation[evaluator.id] = data;
					},
					function(data, status, error) {}
				);
			});
		}, 
		function(data, status, error) {}
	);
	$scope.getAnalyzerButtonClass = function(pluginId) {
		if ($scope.analyzerActivation[pluginId]) {
			return "btn-success";
		} else {
			return "btn-danger";
		}
	}
	$scope.getAnalyzerButtonText = function(pluginId) {
		if ($scope.analyzerActivation[pluginId]) {
			return "enabled";
		} else {
			return "disabled";
		}
	}
	$scope.getEvaluatorButtonClass = function(pluginId) {
		if ($scope.evaluatorActivation[pluginId]) {
			return "btn-success";
		} else {
			return "btn-danger";
		}
	}
	$scope.getEvaluatorButtonText = function(pluginId) {
		if ($scope.evaluatorActivation[pluginId]) {
			return "enabled";
		} else {
			return "disabled";
		}
	}
	$scope.toggleAnalyzerActivation = function(pluginId) {
		$scope.analyzerActivation[pluginId] = !$scope.analyzerActivation[pluginId];
		if ($scope.analyzerActivation[pluginId]) {
			pluginManager.enableAnalyzer(pluginId, 
				function (data, status) {},
				function (data, status, error) {}
			);
		} else {
			pluginManager.disableAnalyzer(pluginId, 
				function (data, status) {},
				function (data, status, error) {}
			);
		}
	}
	$scope.toggleEvaluatorActivation = function(pluginId) {
		$scope.evaluatorActivation[pluginId] = !$scope.evaluatorActivation[pluginId];
		if ($scope.evaluatorActivation[pluginId]) {
			pluginManager.enableEvaluator(pluginId, 
				function (data, status) {},
				function (data, status, error) {}
			);
		} else {
			pluginManager.disableEvaluator(pluginId, 
				function (data, status) {},
				function (data, status, error) {}
			);
		}
	}
}
