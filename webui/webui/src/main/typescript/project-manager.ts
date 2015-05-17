/*
 * This JavaScript files contains Angular JS functionality to be added to an
 * application to handle projects for Purifinity.
 */
var projectManagerModule : angular.IModule  = angular.module("projectManagerModule", [ "purifinityServer" ]);
projectManagerModule.factory('projectManager', ['purifinityServerConnector', projectManager ]);
projectManagerModule.controller("projectListCtrl", projectListCtrl);
projectManagerModule.controller("projectsCtrl", projectsCtrl);	
projectManagerModule.controller("projectSettingsCtrl", projectSettingsCtrl);	
projectManagerModule.controller("createProjectModalInstanceCtrl", createProjectModalInstanceCtrl);
projectManagerModule.controller("editProjectModalInstanceCtrl", editProjectModalInstanceCtrl);

function projectManager(purifinityServerConnector) {
	var projectManager = {};
	projectManager.getProjects = function(success, error) {
		return purifinityServerConnector.get('/purifinityserver/rest/projectmanager/projects',
				success, error);
	};
	projectManager.getProject = function(projectId, success, error) {
		return purifinityServerConnector.get('/purifinityserver/rest/projectmanager/projects/' + projectId,
				success, error);
	};
	projectManager.getLastRun = function(projectId, success, error) {
		return purifinityServerConnector.get('/purifinityserver/rest/projectmanager/projects/' + projectId + '/lastrun',
				success, error);
	};
	projectManager.getRun = function(projectId, runId, success, error) {
		return purifinityServerConnector.get('/purifinityserver/rest/projectmanager/projects/' + projectId + '/runs/' + runId,
				success, error);
	};
	projectManager.createProject = function(identifier, projectSettings, success, error) {
		return purifinityServerConnector.put('/purifinityserver/rest/projectmanager/projects/' + identifier, projectSettings,
				success, error);
	};
	projectManager.triggerNewRun = function(identifier, success, error) {
		return purifinityServerConnector.put('/purifinityserver/rest/analysis/projects/' + identifier, "",
				success, error);
	};
	projectManager.editProject = function(id, name, success, error) {
	};
	projectManager.deleteProject = function(identifier, success, error) {
		return purifinityServerConnector.del('/purifinityserver/rest/projectmanager/projects/' + identifier,
				success, error);
	};
	projectManager.getRepositoryTypes = function(success, error) {
		return purifinityServerConnector.get('/purifinityserver/rest/repositories/types',
				success, error);
	};
	projectManager.readAllRunInformation = function(projectId, success, error) {
		return purifinityServerConnector.get('/purifinityserver/rest/projectmanager/projects/' + projectId + '/runs',
				success, error);
	};
	projectManager.getAnalysisFileTree = function(projectId, runId, success, error) {
		return purifinityServerConnector.get('/purifinityserver/rest/projectmanager/projects/' + projectId + '/runs/' + runId + '/filetree',
			function (data, status) {
				data.files = {};
				data.directories = {};
				var searchFileTree = function (tree) {
					var hashId = tree.hashId.algorithmName + ":" + tree.hashId.hash;
					if (tree.file) {
						data.files[hashId] = tree;
					} else {
						data.directories[hashId] = tree;
					}
					if (tree.children.length > 0) {
						tree.children.forEach(searchFileTree);
					}
				}
				searchFileTree(data);
				data.getFile = function(hashid) {
					return this.files[hashid];
				}
				data.getDirectory = function(hashid) {
					return this.directories[hashid];
				}
				success(data, status);
			}, 
			error
		);
	};
	return projectManager;
}

function projectListCtrl($scope, $location, projectManager) {
	$scope.projects = {};
	projectManager.getProjects(//
		function(data, status) {
			$scope.projects = data;
			if (!$scope.projects) {
				$scope.projects = {};
			}
		}, //
		function(data, status, error) {});
	$scope.triggerNewRun = function(id) {
		projectManager.triggerNewRun(id, 
			function (data, status) {},
			function (data, status, error) {}
		);
	}
}

function projectsCtrl($scope, $routeParams, baseURL) {
	$scope.projectId = $routeParams['project'];
	$scope.runId = $routeParams['run'];
	$scope.mode = $routeParams['mode'];
	$scope.isSelected = function(id) {
		return $scope.projectId == id;
	};
}

function projectSettingsCtrl($scope, $modal, $log, projectManager) {
	$scope.projects = {};
	projectManager.getProjects(//
		function(data, status) {
			$scope.projects = data;
			if (!$scope.projects) {
				$scope.projects = {};
			}
		}, //
		function(data, status, error) {});
	$scope.createItems = {
					id: "", 
					name: "",
					description: "",
					directoryIncludes: "",
					directoryExcludes: "",
					fileIncludes: "",
					fileExcludes: "",
					ignoreHidden: true,
					repositoryId: "",
					repositoryProperties: {}
					};
	$scope.openCreateProject = function () {
		var modalInstance = $modal.open({
			templateUrl: 'views/admin/dialogs/createProjectModalContent.html',
			controller: 'createProjectModalInstanceCtrl',
			resolve: {
				items: function () {
					return $scope.createItems;
				}
			}
	    });
	    
	    modalInstance.result.then(function (items) {
			$scope.createItems = items;
			projectManager.getProjects(//
				function(data, status) {$scope.projects = data}, //
				function(data, status, error) {});
		}, function () {
	      $log.info('Modal dismissed at: ' + new Date());
	    })
	};
	$scope.editItems = {
					id: "",
					name: ""};
	$scope.openEditProject = function (user) {
		$scope.editItems.id = user.id;
		$scope.editItems.name = user.name;
		var modalInstance = $modal.open({
			templateUrl: 'views/admin/dialogs/editProjectModalContent.html',
			controller: 'editProjectModalInstanceCtrl',
			resolve: {
				items: function () {
					return $scope.editItems;
				}
			}
	    });	    
	    modalInstance.result.then(function (items) {
			$scope.editItems = items;
			projectManager.getProjects(//
				function(data, status) {$scope.projects = data}, //
				function(data, status, error) {});
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

function createProjectModalInstanceCtrl($scope, $modalInstance, items, projectManager) {
	$scope.items = items;
	$scope.repositories = undefined;
	projectManager.getRepositoryTypes(
		function(data, status) {$scope.repositories = data}, //
		function(data, status, error) {});
	$scope.$watch('items.repositoryId', function(oldValue, newValue) {
		var key;
		for (key in $scope.repositories) {
			var repository = $scope.repositories[key];
			if (repository.id == items.repositoryId) {
				$scope.repositoryProperties = {};
				var name;
				for (name in repository.parameters) {
					$scope.repositoryProperties[name] = repository.parameters[name];
					$scope.repositoryProperties[name].uiName = name;
				}
			}
		}
	});
	$scope.ok = function () {
	    $modalInstance.close($scope.items);
		var items = $scope.items;
		var projectSettings = {
			"name": items.name,
			"description": items.description,
			"fileSearchConfiguration":{
				"locationIncludes": items.directoryIncludes.split(";"),
				"locationExcludes": items.directoryExcludes.split(";"),
				"fileIncludes": items.fileIncludes.split(";"),
				"fileExcludes": items.fileExcludes.split(";"),
				"ignoreHidden": items.ignoreHidden
			},
			"repository":{
				"repository.id": items.repositoryId
			}
		};
		var key;
		for (key in items.repositoryProperties) {
			projectSettings.repository[key] = items.repositoryProperties[key];
		}
		projectManager.createProject(items.id, projectSettings,
			function(data, status) {}, //
			function(data, status, error) {});
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
