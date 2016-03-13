/*
 * This JavaScript files contains Angular JS functionality to be added to an
 * application to handle projects for Purifinity.
 */
var projectManagerModule: angular.IModule = angular.module("projectManagerModule", ["purifinityServerModule", "preferencesManagerModule"]);

projectManagerModule.controller("editProjectCtrl", ["$scope", "$location", "$rootScope", "$routeParams", "projectManager", "pluginManager", "preferencesManager",
    function($scope, $location, $rootScope, $routeParams, projectManager, pluginManager, preferencesManager) {
        $scope.projectId = $routeParams["projectId"];
        $scope.currentProject = {};
        $scope.items = {
            id: "",
            name: "",
            description: "",
            repositoryId: "",
            repositoryProperties: {},
            directoryIncludes: "",
            directoryExcludes: "",
            fileIncludes: "",
            fileExcludes: "",
            ignoreHidden: true
        };
        $scope.refresh = function() {
            projectManager.getProject($scope.projectId, function(data: Project, status) {
                $scope.currentProject = data;
                $scope.items.id = data.information.projectId;
                $scope.items.name = data.settings.name;
                $scope.items.description = data.settings.description;
                $scope.items.repositoryId = data.settings.repository["repository.id"];
                $scope.items.repositoryProperties = data.settings.repository;
                $scope.items.fileIncludes = "";
                data.settings.fileSearchConfiguration.fileIncludes.forEach(function(line: string, num: number) {
                    if (num > 0) {
                        $scope.items.fileIncludes += "\n";
                    }
                    $scope.items.fileIncludes += line;
                });
                $scope.items.fileExcludes = "";
                data.settings.fileSearchConfiguration.fileExcludes.forEach(function(line: string, num: number) {
                    if (num > 0) {
                        $scope.items.fileExcludes += "\n";
                    }
                    $scope.items.fileExcludes += line;
                });
                $scope.items.directoryIncludes = "";
                data.settings.fileSearchConfiguration.locationIncludes.forEach(function(line: string, num: number) {
                    if (num > 0) {
                        $scope.items.directoryIncludes += "\n";
                    }
                    $scope.items.directoryIncludes += line;
                });
                $scope.items.directoryExcludes = "";
                data.settings.fileSearchConfiguration.locationExcludes.forEach(function(line: string, num: number) {
                    if (num > 0) {
                        $scope.items.directoryExcludes += "\n";
                    }
                    $scope.items.directoryExcludes += line;
                });
                $scope.items.ignoreHidden = data.settings.fileSearchConfiguration.ignoreHidden;
            }, function(data, status, error) { });
        };
        $scope.ok = function() {
            $scope.currentProject.settings.name = $scope.items.name;
            $scope.currentProject.settings.description = $scope.items.description;
            $scope.currentProject.settings.repository["repository.id"] = $scope.items.repositoryId;
            $scope.currentProject.settings.repository = $scope.items.repositoryProperties;
            $scope.currentProject.settings.fileSearchConfiguration.fileIncludes = $scope.items.fileIncludes.split("\n");
            $scope.currentProject.settings.fileSearchConfiguration.fileExcludes = $scope.items.fileExcludes.split("\n");
            $scope.currentProject.settings.fileSearchConfiguration.locationIncludes = $scope.items.directoryIncludes.split("\n");
            $scope.currentProject.settings.fileSearchConfiguration.locationExcludes = $scope.items.directoryExcludes.split("\n");
            $scope.currentProject.settings.fileSearchConfiguration.ignoreHidden = $scope.items.ignoreHidden;
            projectManager.updateProjectSettings($scope.projectId, $scope.currentProject.settings, function(data: Project, status) {
            }, function(data, status, error) { });
        };
        $scope.cancel = function() {
            $scope.refresh();
        };
        $scope.repositories = undefined;
        projectManager.getRepositoryTypes(
            function(data, status) { $scope.repositories = data }, //
            function(data, status, error) { });
        $scope.$watch("items.repositoryId", function(newValue, oldValue) {
            for (var key in $scope.repositories) {
                var repository = $scope.repositories[key];
                if (repository.id === $scope.items.repositoryId) {
                    $scope.repositoryProperties = {};
                    for (var name in repository.parameters) {
                        $scope.repositoryProperties[name] = repository.parameters[name];
                        $scope.repositoryProperties[name].uiName = name;
                    }
                }
            }
        });
        $scope.refresh();
    }]);

projectManagerModule.controller("editProjectPluginSettingsCtrl", ["$scope", "$location", "$rootScope", "$routeParams", "pluginManager", "preferencesManager",
    function($scope, $location, $rootScope, $routeParams, pluginManager, preferencesManager) {
        $scope.projectId = $routeParams["projectId"];
        var pluginSettings: ConfigurationComponentData = new ConfigurationComponentData("Plug-ins", PreferencesGroup.PLUGIN_PROJECT);
        $scope.pluginSettings = pluginSettings;
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
                    addPluginConfiguration(preferencesManager, analyzerNode, plugin.id, $scope.projectId);
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
                    addPluginConfiguration(preferencesManager, evaluatorNode, plugin.id, $scope.projectId);
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
                    addPluginConfiguration(preferencesManager, repositoryNode, plugin.id, $scope.projectId);
                }
            },
            function(data, status, error) { }
            );
        var addPluginConfiguration = function(preferencesManager: PreferencesManager, pluginNode: ConfigurationComponentTree, pluginId: string, projectId: string) {
            preferencesManager.getPluginProjectParameters(pluginId, $scope.projectId,
                function(data, status) {
                    for (var i = 0; i < data.length; i++) {
                        var parameter: ConfigurationParameter = ConfigurationParameter.fromJSON(PreferencesGroup.PLUGIN_PROJECT, new PreferencesGroupIdentifier(pluginId, $scope.projectId), data[i]);
                        ConfigurationComponentData.addConfigurationParameter(pluginNode, parameter);
                    }
                },
                function(data, status, error) { }
                );
        }
    }]);
