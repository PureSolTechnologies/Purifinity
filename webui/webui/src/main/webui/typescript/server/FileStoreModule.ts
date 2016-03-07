/*
 * This JavaScript files contains Angular JS functionality to be added to an
 * application to handle the file store of Purifinity.
 */
var fileStoreModule: angular.IModule = angular.module("fileStoreModule", ["purifinityServerModule"]);

fileStoreModule.factory("fileStore", ["purifinityServerConnector",
    function(purifinityServerConnector) {
    return new FileStore(purifinityServerConnector);
}]);
