var fileViewerModule = angular.module("fileViewerModule", [ "fileStoreModule" ]);
fileViewerModule.controller("fileSummaryCtrl", ['$scope', '$routeParams', 'fileStore', fileSummaryCtrl]);

function fileSummaryCtrl($scope, $routeParams, fileStore) {
    $scope.analyses = {};
    $scope.sourceCode = {};

    fileStore.loadAnalyses($routeParams.hashId, function(data, status) {
	$scope.analyses = data;
    }, function(data, status, error) {
    });
    fileStore.readSourceCode($routeParams.hashId, function(data, status) {
	$scope.sourceCode = data;
    }, function(data, status, error) {
    });
}
