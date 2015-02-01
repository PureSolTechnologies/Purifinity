/*
 * This JavaScript files contains Angular JS functionality to be added to an
 * application to handle user accounts for Purifinity.
 */
var projectManagerModule = angular.module("projectManager", [ "purifinityServer" ]);
projectManagerModule.controller("projectListCtrl", projectListCtrl);
projectManagerModule.controller("projectsCtrl", projectsCtrl);	

function projectListCtrl($scope, $location, purifinityServerConnector,
		alerterFactory) {
	purifinityServerConnector.get(
			'/purifinityserver/rest/analysisstore/projects', //
			function(data, status) {
				if (data) {
					$scope.projects = data;
				} else {
					$scope.projects = [];
				}
			}, // 
			function(data, status, error) {
				if (data) {
					alerterFactory.addAlert("warning",
							"Data was taken from local cache.");
					$scope.projects = JSON.parse(data);
				} else {
					alerterFactory.addAlert("danger", error);
				}
			});
}

function projectsCtrl($scope, $routeParams, baseURL) {
	$scope.projectId = $routeParams['project'];
	$scope.runId = $routeParams['run'];
	$scope.mode = $routeParams['mode'];
	$scope.isSelected = function(uuid) {
		return projectId == uuid;
	};
}