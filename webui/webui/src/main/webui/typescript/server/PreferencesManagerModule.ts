/*
 * This JavaScript files contains Angular JS functionality to be added to an
 * application to handle preferences for Purifinity.
 */
var preferencesManagerModule: angular.IModule = angular.module("preferencesManagerModule", ["purifinityServerModule", "pluginManagerModule"]);

preferencesManagerModule.controller("systemSettingsCtrl", ["$scope", "preferencesManager",
    function($scope, preferencesManager) {
        $scope.systemSettings = {};
        preferencesManager.getSystemParameters(
            function(data, status) {
                var systemSettings: ConfigurationComponentData = new ConfigurationComponentData("Purifinity", PreferencesGroup.SYSTEM);
                for (var i = 0; i < data.length; i++) {
                    var parameter: ConfigurationParameter = ConfigurationParameter.fromJSON(PreferencesGroup.SYSTEM, new PreferencesGroupIdentifier(), data[i]);
                    ConfigurationComponentData.addConfigurationParameter(systemSettings.root, parameter);
                }
                $scope.systemSettings = systemSettings;
            },
            function(data, status, error) { }
            );
    }]);

preferencesManagerModule.controller("pluginSettingsCtrl", ["$scope", "preferencesManager", "pluginManager",
    function($scope, preferencesManager, pluginManager) {
        var pluginSettings: ConfigurationComponentData = new ConfigurationComponentData("Plug-ins", PreferencesGroup.PLUGIN_DEFAULT);
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
                $scope.analyzers.sort(
                    function(left, right) {
                        return Utilities.strcmp(left.name, right.name);
                    });
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
                $scope.evaluators.sort(
                    function(left, right) {
                        return Utilities.strcmp(left.name, right.name);
                    });
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
                $scope.repositories.sort(
                    function(left, right) {
                        return Utilities.strcmp(left.name, right.name);
                    });
                for (var i = 0; i < data.length; i++) {
                    var plugin = data[i];
                    var repositoryNode: ConfigurationComponentTree = new ConfigurationComponentTree(repositoriesNode, plugin.name);
                    repositoriesNode.addChild(repositoryNode);
                    addPluginConfiguration(preferencesManager, repositoryNode, plugin.id);
                }
            },
            function(data, status, error) { }
            );
        var addPluginConfiguration = function(preferencesManager: PreferencesManager, pluginNode: ConfigurationComponentTree, pluginId: string) {
            preferencesManager.getPluginDefaultParameters(pluginId,
                function(data, status) {
                    for (var i = 0; i < data.length; i++) {
                        var parameter: ConfigurationParameter = ConfigurationParameter.fromJSON(PreferencesGroup.PLUGIN_DEFAULT, new PreferencesGroupIdentifier(pluginId), data[i]);
                        ConfigurationComponentData.addConfigurationParameter(pluginNode, parameter);
                    }
                },
                function(data, status, error) { }
                );
        }
    }]);
