/*
 * This JavaScript files contains Angular JS functionality to be added to an
 * application to handle projects for Purifinity.
 */
var projectManagerModule: angular.IModule = angular.module("projectManagerModule", ["purifinityServerModule", "preferencesManagerModule"]);

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

projectManagerModule.controller("editProjectCtrl", function($scope, $location, $rootScope, $routeParams, projectManager, pluginManager, preferencesManager) {
    $scope.projectId = $routeParams['project'];
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
    var pluginSettings: ConfigurationComponentData = new ConfigurationComponentData("Plug-ins", PreferencesGroup.PLUGIN_PROJECT);
    $scope.pluginSettings = pluginSettings;
    projectManager.getRepositoryTypes(
        function(data, status) { $scope.repositories = data }, //
        function(data, status, error) { });
    $scope.analyzers = {};
    $scope.evaluators = {};
    $scope.repositories = {};
    var analyzersNode: ConfigurationComponentTree = new ConfigurationComponentTree(pluginSettings.root, "Analyzers");
    pluginSettings.root.addChild(analyzersNode);
    var evaluatorsNode: ConfigurationComponentTree = new ConfigurationComponentTree(pluginSettings.root, "Evaluators");
    pluginSettings.root.addChild(evaluatorsNode);
    var repositoriesNode: ConfigurationComponentTree = new ConfigurationComponentTree(pluginSettings.root, "Repositories");
    pluginSettings.root.addChild(repositoriesNode);
    pluginManager.getAnalyzers(
        function(data, status) {
            $scope.analyzers = data;
            for (var i = 0; i < data.length; i++) {
                var plugin = data[i];
                var analyzerNode: ConfigurationComponentTree = new ConfigurationComponentTree(analyzersNode, plugin.name);
                analyzersNode.addChild(analyzerNode);
                addPluginConfiguration(preferencesManager, analyzerNode, plugin.id);
            }
        },
        function(data, status, error) { }
        );
    pluginManager.getEvaluators(
        function(data, status) {
            $scope.evaluators = data;
            for (var i = 0; i < data.length; i++) {
                var plugin = data[i];
                var evaluatorNode: ConfigurationComponentTree = new ConfigurationComponentTree(evaluatorsNode, plugin.name);
                evaluatorsNode.addChild(evaluatorNode);
                addPluginConfiguration(preferencesManager, evaluatorNode, plugin.id);
            }
        },
        function(data, status, error) { }
        );
    pluginManager.getRepositoryTypes(
        function(data, status) {
            $scope.repositories = data;
            for (var i = 0; i < data.length; i++) {
                var plugin = data[i];
                var repositoryNode: ConfigurationComponentTree = new ConfigurationComponentTree(repositoriesNode, plugin.name);
                repositoriesNode.addChild(repositoryNode);
                addPluginConfiguration(preferencesManager, repositoryNode, plugin.id);
            }
        },
        function(data, status, error) { }
        );
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
    $scope.ok =    function() {
        $location.path("/projects");};
    $scope.cancel =    function() {
        $location.path("/projects");};
});
