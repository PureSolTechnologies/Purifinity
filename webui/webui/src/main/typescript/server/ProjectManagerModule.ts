/*
 * This JavaScript files contains Angular JS functionality to be added to an
 * application to handle projects for Purifinity.
 */
var projectManagerModule: angular.IModule = angular.module("projectManagerModule", ["purifinityServerModule"]);

projectManagerModule.factory('projectManager', ['purifinityServerConnector',
    function(purifinityServerConnector) {
        return new ProjectManager(purifinityServerConnector);
    }]);

projectManagerModule.controller("projectListCtrl", function($scope, projectManager) {
    $scope.projects = {};
    projectManager.getProjects(//
        function(data, status) {
            $scope.projects = data;
            if (!$scope.projects) {
                $scope.projects = {};
            }
        }, //
        function(data, status, error) { });
    $scope.triggerNewRun = function(id) {
        projectManager.triggerNewRun(id,
            function(data, status) { },
            function(data, status, error) { }
            );
    }
});
projectManagerModule.controller("projectsCtrl", function($scope, $routeParams, baseURL) {
    $scope.projectId = $routeParams['project'];
    $scope.runId = $routeParams['run'];
    $scope.mode = $routeParams['mode'];
    $scope.isSelected = function(id) {
        return $scope.projectId == id;
    };
});
projectManagerModule.controller("projectSettingsCtrl", function($scope, $modal, $log, projectManager) {
    $scope.projects = {};
    projectManager.getProjects(//
        function(data, status) {
            $scope.projects = data;
            if (!$scope.projects) {
                $scope.projects = {};
            }
        }, //
        function(data, status, error) { });
    $scope.deleteProject = function(id) {
        projectManager.deleteProject(id,
            function(data, status) {
                projectManager.getProjects(//
                    function(data, status) { $scope.projects = data }, //
                    function(data, status, error) { });
            },
            function(data, status, error) {
            }
            );
    }
});
projectManagerModule.controller("createProjectCtrl", function($scope, $location, $rootScope, projectManager) {
    $scope.items = {
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
    $scope.repositories = undefined;
    projectManager.getRepositoryTypes(
        function(data, status) { $scope.repositories = data }, //
        function(data, status, error) { });
    $scope.$watch('items.repositoryId', function(oldValue, newValue) {
        var key;
        for (key in $scope.repositories) {
            var repository = $scope.repositories[key];
            if (repository.id == $scope.items.repositoryId) {
                $scope.repositoryProperties = {};
                var name;
                for (name in repository.parameters) {
                    $scope.repositoryProperties[name] = repository.parameters[name];
                    $scope.repositoryProperties[name].uiName = name;
                }
            }
        }
    });
    $scope.ok = function() {
        var items = $scope.items;
        var projectSettings = {
            "name": items.name,
            "description": items.description,
            "fileSearchConfiguration": {
                "locationIncludes": items.directoryIncludes.split(";"),
                "locationExcludes": items.directoryExcludes.split(";"),
                "fileIncludes": items.fileIncludes.split(";"),
                "fileExcludes": items.fileExcludes.split(";"),
                "ignoreHidden": items.ignoreHidden
            },
            "repository": {
                "repository.id": items.repositoryId
            }
        };
        for (var key in items.repositoryProperties) {
            projectSettings.repository[key] = items.repositoryProperties[key];
        }
        projectManager.createProject(items.id, projectSettings,
            function(data, status) { }, //
            function(data, status, error) { });
        $location.path("/projects");
    };
    $scope.cancel = function() {
        $location.path("/projects");
    };
});

projectManagerModule.controller("editProjectCtrl", function($scope, $location, $rootScope, projectManager) {
    $scope.items = {};
    $scope.ok = function() {
        $location.path("/projects");
    };
    $scope.cancel = function() {
        $location.path("/projects");
    };
});
