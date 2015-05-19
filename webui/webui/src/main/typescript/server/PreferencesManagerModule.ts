/*
 * This JavaScript files contains Angular JS functionality to be added to an
 * application to handle preferences for Purifinity.
 */
var preferencesManagerModule: angular.IModule = angular.module("preferencesManagerModule", ["purifinityServerModule", "pluginManagerModule"]);

preferencesManagerModule.factory('preferencesManager', [
    'purifinityServerConnector', function(purifinityServerConnector) {
        return new PreferencesManager(purifinityServerConnector);
    }]);

preferencesManagerModule.controller('systemSettingsCtrl', function($scope, preferencesManager) {
    $scope.systemSettings = {};
    preferencesManager.getSystemParameters(
        function(data, status) {
            var systemSettings: ConfigurationComponentData = new ConfigurationComponentData("Purifinity");
            for (var i = 0; i < data.length; i++) {
                var parameter: ConfigurationParameter = ConfigurationParameter.fromJSON(data[i]);
                systemSettings.addConfigurationParameter(parameter);
            }
            $scope.systemSettings = systemSettings;
        },
        function(data, status, error) { }
        );
});

preferencesManagerModule.controller('pluginSettingsCtrl', function($scope, preferencesManager, pluginManager) {
    var pluginSettings: ConfigurationComponentData = new ConfigurationComponentData("Plug-ins");
    $scope.pluginSettings = pluginSettings;
    $scope.analyzers = {};
    $scope.evaluators = {};
    $scope.repositories = {};
    var analyzersNode : ConfigurationComponentTree = new ConfigurationComponentTree(pluginSettings.root, "Analyzers");
    pluginSettings.root.addChild(analyzersNode);  
    var evaluatorsNode : ConfigurationComponentTree = new ConfigurationComponentTree(pluginSettings.root, "Evaluators");  
    pluginSettings.root.addChild(evaluatorsNode);  
    var repositoriesNode : ConfigurationComponentTree = new ConfigurationComponentTree(pluginSettings.root, "Repositories");  
    pluginSettings.root.addChild(repositoriesNode);  
    pluginManager.getAnalyzers(
        function(data, status) {
            $scope.analyzers = data;
            for (var i = 0; i < data.length; i++) {
                analyzersNode.addChild(new ConfigurationComponentTree(analyzersNode, data[i].name));
            }
        },
        function(data, status, error) { }
        );
    pluginManager.getEvaluators(
        function(data, status) {
            $scope.evaluators = data;
            for (var i = 0; i < data.length; i++) {
                evaluatorsNode.addChild(new ConfigurationComponentTree(evaluatorsNode, data[i].name));
            }
        },
        function(data, status, error) { }
        );
    pluginManager.getRepositoryTypes(
        function(data, status) {
            $scope.repositories = data;
            for (var i = 0; i < data.length; i++) {
                repositoriesNode.addChild(new ConfigurationComponentTree(repositoriesNode, data[i].name));
            }
        },
        function(data, status, error) { }
        );
});

