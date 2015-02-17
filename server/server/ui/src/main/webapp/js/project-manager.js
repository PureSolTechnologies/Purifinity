/*
 * This JavaScript files contains Angular JS functionality to be added to an
 * application to handle user accounts for Purifinity.
 */
var projectManagerModule = angular.module("projectManagerModule", [ "purifinityServer" ]);
accountManagerModule.factory('projectManager', [
		'purifinityServerConnector', projectManager ]);
projectManagerModule.controller("projectListCtrl", projectListCtrl);
projectManagerModule.controller("projectsCtrl", projectsCtrl);	
projectManagerModule.controller("projectSettingsCtrl", projectSettingsCtrl);	
projectManagerModule.controller("createProjectModalCtrl", createProjectModalCtrl);
projectManagerModule.controller("createProjectModalInstanceCtrl", createProjectModalInstanceCtrl);
projectManagerModule.controller("editProjectModalInstanceCtrl", editProjectModalInstanceCtrl);

function projectManager(purifinityServerConnector) {
	var projectManager = {};
	projectManager.getProjects = function(success, error) {
		return purifinityServerConnector.get('/purifinityserver/rest/analysisstore/projects',
				success, error);
	};
	projectManager.createProject = function(id, name, success, error) {
	};
	projectManager.editProject = function(id, name, success, error) {
	};
	projectManager.deleteProject = function(id, success, error) {
	};
	return projectManager;
}

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

  function projectSettingsCtrl($scope, $modal, $log, projectManager) {
	$scope.projects = undefined;
	projectManager.getProjects(//
		function(data, status) {$scope.projects = data}, //
		function(data, status, error) {});
	$scope.items = {
					id: "",
					name: ""};
	$scope.openEditProject = function (user) {
		$scope.items.id = user.id;
		$scope.items.name = user.name;
		var modalInstance = $modal.open({
			templateUrl: 'views/admin/dialogs/editProjectModalContent.html',
			controller: 'editProjectModalInstanceCtrl',
			resolve: {
				items: function () {
					return $scope.items;
				}
			}
	    });	    
	    modalInstance.result.then(function (items) {
	      $scope.items = items;
	    }, function () {
	      $log.info('Modal dismissed at: ' + new Date());
	    })
	};
	$scope.deleteProject = function (id) {
		projectManager.deleteProject(id, 
			function (data, status) {
				projectManager.getProjects(//
					function(data, status) {$scope.projects = data}, //
					function(data, status, error) {});
			},
			function (data, status, error) {
			}
		);
	}
}

function createProjectModalCtrl($scope, $modal, $log) {
	$scope.items = {
					id: "", 
					name: ""};
	$scope.open = function () {
		var modalInstance = $modal.open({
			templateUrl: 'views/admin/dialogs/createProjectModalContent.html',
			controller: 'createProjectModalInstanceCtrl',
			resolve: {
				items: function () {
					return $scope.items;
				}
			}
	    });
	    
	    modalInstance.result.then(function (items) {
	      $scope.items = items;
	    }, function () {
	      $log.info('Modal dismissed at: ' + new Date());
	    })
	};
}

function createProjectModalInstanceCtrl($scope, $modalInstance, items, projectManager) {
	$scope.items = items;
	$scope.roles = undefined;
	$scope.ok = function () {
	    $modalInstance.close($scope.items);
	};
	$scope.cancel = function () {
	    $modalInstance.dismiss('cancel');
	};
}

function editProjectModalInstanceCtrl($scope, $modalInstance, items, projectManager) {
	$scope.items = items;
	$scope.roles = undefined;
	$scope.ok = function () {
	    $modalInstance.close($scope.items);
	};
	$scope.cancel = function () {
	    $modalInstance.dismiss('cancel');
	};
}
