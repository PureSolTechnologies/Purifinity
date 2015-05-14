/*
 * This JavaScript files contains Angular JS functionality to be added to an
 * application to handle preferences for Purifinity.
 */
var preferencesManagerModule = angular.module("preferencesManagerModule", [ "purifinityServer" ]);
preferencesManagerModule.factory('preferencesManager', [
    'purifinityServerConnector', preferencesManager ]);
preferencesManagerModule.controller('systemSettingsCtrl', systemSettingsCtrl);

function preferencesManager(purifinityServerConnector) {
    var preferencesManager = {};
    preferencesManager.getSystemParameters = function(success, error) {
	return purifinityServerConnector.get(
	    '/purifinityserver/rest/preferences/system',
	    success, 
	    error
	);
    };
    return preferencesManager;
}

function systemSettingsCtrl($scope, preferencesManager) {
    $scope.systemSettingsTree = {
	children: []
    };
    preferencesManager.getSystemParameters(
	function (data, status) {
	    for (var i = 0; i < data.length; i++) {
		var parameter = data[i];
	    }
	},
	function (data, status, error) {}
    );
}
