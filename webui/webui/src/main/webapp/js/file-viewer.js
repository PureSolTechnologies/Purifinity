var fileViewerModule = angular.module("fileViewerModule", [ "fileStoreModule" ]);
fileViewerModule.controller("fileSummaryCtrl", ['$scope', '$routeParams', 'fileStore', fileSummaryCtrl]);
fileViewerModule.directive("sourceCode", sourceCode);

function fileSummaryCtrl($scope, $routeParams, fileStore) {
    $scope.analyses = {};
    $scope.sourceCode = {};
    fileStore.wasAnalyzed($routeParams.hashId, function(data, status) {
	if (data) {
	    fileStore.loadAnalyses($routeParams.hashId, function(data, status) {
		$scope.analyses = data;
	    }, function(data, status, error) {
	    });
	}
    }, function(data, status, error) {
    });
    fileStore.readSourceCode($routeParams.hashId, function(data, status) {
	$scope.sourceCode = data;
    }, function(data, status, error) {
    });
}

function sourceCode() {
    return {
	restrict: "E",
	scope: {
	    sourceCodeData: '=ngModel'
	},
	controller : sourceCodeCtrl,
	templateUrl: "js/sourceCode.html"
    };
}

function sourceCodeCtrl($scope) {
}
