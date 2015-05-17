/*
 * This JavaScript files contains Angular JS functionality to be added to an
 * application to handle preferences for Purifinity.
 */
var preferencesManagerModule: angular.IModule = angular.module("preferencesManagerModule", ["purifinityServerModule"]);
preferencesManagerModule.factory('preferencesManager', [
    'purifinityServerConnector', preferencesManager]);
preferencesManagerModule.controller('systemSettingsCtrl', systemSettingsCtrl);

function preferencesManager(purifinityServerConnector) {
    return new PreferencesManager(purifinityServerConnector);
}

function systemSettingsCtrl($scope, preferencesManager) {
    $scope.systemSettingsTree = {
        children: []
    };
    preferencesManager.getSystemParameters(
        function(data, status) {
            for (var i = 0; i < data.length; i++) {
                var parameter = data[i];
            }
        },
        function(data, status, error) { }
        );
}
