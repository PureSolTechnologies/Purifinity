/*
 * This JavaScript files contains Angular JS functionality to be added to an
 * application to handle preferences for Purifinity.
 */
var preferencesManagerModule = angular.module("preferencesManagerModule", [ "purifinityServer" ]);
preferencesManagerModule.factory('preferencesManager', [
		'purifinityServerConnector', preferencesManager ]);

function preferencesManager(purifinityServerConnector) {
	var preferencesManager = {};
	return preferencesManager;
}
