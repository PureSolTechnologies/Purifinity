/*
 * This JavaScript files contains Angular JS functionality to be added to an
 * application to handle the file store of Purifinity.
 */
var fileStoreModule : angular.IModule = angular.module("fileStoreModule", [ "purifinityServer" ]);
fileStoreModule.factory('fileStore', ['purifinityServerConnector', fileStore ]);

function fileStore(purifinityServerConnector) {
    var fileStore = {};
    fileStore.isAvailable = function(hashId, success, error) {
    return purifinityServerConnector.get('/purifinityserver/rest/filestore/files/' + hashId + '/available',
                         success, error);
    };
    fileStore.readSourceCode = function(hashId, success, error) {
    return purifinityServerConnector.get('/purifinityserver/rest/filestore/files/' + hashId + '/sourcecode',
                         success, error);
    };
    fileStore.loadAnalyses = function(hashId, success, error) {
    return purifinityServerConnector.get('/purifinityserver/rest/filestore/files/' + hashId + '/analyses',
                         success, error);
    };
    fileStore.wasAnalyzed = function(hashId, success, error) {
    return purifinityServerConnector.get('/purifinityserver/rest/filestore/files/' + hashId + '/analyzed',
                         success, error);
    };
    return fileStore;
}
