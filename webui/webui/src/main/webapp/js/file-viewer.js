var fileViewerModule = angular.module("fileViewerModule", [ "fileStoreModule" ]);
fileViewerModule.controller("fileSummaryCtrl", ['$scope', '$routeParams', 'fileStore', fileSummaryCtrl]);

function fileSummaryCtrl($scope, $routeParams, fileStore) {
    $scope.analyses = {};

    fileStore.loadAnalyses($routeParams.hashId, function(data, status) {
	$scope.analyses = data;
    }, function(data, status, error) {
    });
}
