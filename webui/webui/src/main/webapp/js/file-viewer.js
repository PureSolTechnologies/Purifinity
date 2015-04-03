var fileViewerModule = angular.module("fileViewerModule", [ "fileStoreModule" ]);
fileViewerModule.controller("fileSummaryCtrl", ['$scope', '$routeParams', 'fileStore', fileSummaryCtrl]);
fileViewerModule.controller("fileAnalysisCtrl", ['$scope', '$routeParams', '$filter', 'fileStore', fileAnalysisCtrl]);
fileViewerModule.directive("sourceCode", sourceCode);
fileViewerModule.directive("ustView", ustView);

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

function fileAnalysisCtrl($scope, $routeParams, $filter, fileStore) {
    $scope.analyzerSelection = undefined;
    $scope.analyses = [];
    $scope.usts = {};
    $scope.selectedUST = undefined;
    fileStore.wasAnalyzed($routeParams.hashId, function(data, status) {
	if (data) {
	    fileStore.loadAnalyses($routeParams.hashId, function(data, status) {
		$scope.analyses = data;
		$scope.analyses.forEach(function(analysis) {
		    var id = analysis.analysisInformation.analyzerId + $filter('version')(analysis.analysisInformation.analyzerVersion);
		    analysis.analyzableCodeRanges.forEach(function(codeRange) {
			if (codeRange.type === "FILE") {
			    $scope.usts[id] = codeRange.ust;
			}
		    });
		    
		});
	    }, function(data, status, error) {
	    });
	}
    }, function(data, status, error) {
    });
    $scope.$watch('analyzerSelection', function(newValue, oldValue) {
	$scope.selectedUST = $scope.usts[newValue];
    });
}

function ustView() {
    return {
	restrict: "E",
	replace: false,
	scope: {
	    ustViewData: '=ngModel'
	},
	controller : ustViewCtrl,
	link: function(scope, element, attrs) {
	    element.append(
		$('<div/>').text("Hallo").append(
		    $('<span/>')
			.text(scope.ustViewData)
		)
	    );
	}
    };
}

function ustViewCtrl($scope) {
}
