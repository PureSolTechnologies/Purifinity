var analysisUIModule = angular.module("analysisUIModule", [ "projectManagerModule" ]);
analysisUIModule.controller("analysisBrowserCtrl", analysisBrowserCtrl);
analysisUIModule.controller("runListCtrl", runListCtrl);

function analysisBrowserCtrl($scope, $routeParams, projectManager) {
    $scope.project = undefined;
    $scope.run = undefined;
    $scope.analysisFileTree = [];
    projectManager.getProject($routeParams.projectId, function(data, status) {
	$scope.project = data;
	projectManager.getLastRun($routeParams.projectId, function(data, status) {
	    $scope.run = data;
	    projectManager.getAnalysisFileTree(
		$scope.project.information.projectId,
		$scope.run.runId,
		function(data, status) {
		    $scope.analysisFileTree = convertAnalysisFileTree(data);
		    $scope.analysisFileTree.columnHeaders = [ 
			{name: "Name", tooltip: "Name of file or folder"}, 
			{name: "Size", tooltip: "Size of file or size of folder without sub folders."}, 
			{name: "Size Recursive", tooltip: "Size of file or size of folder including sub folders."}, 
			{name: "Analyses", tooltip: "Successful analyses."} 
		    ];
		},
		function(data, status, error) {}
	    );
	}, function(data, status, error) {
	});
    }, function(data, status, error) {
    });
};


function convertAnalysisFileTree(fileTree) {
    var treeTableData = {};
    treeTableData.content = fileTree.name;
    treeTableData.id = fileTree.hashId.algorithmName + ":" + fileTree.hashId.hash;
    var analyses = "";
    fileTree.analyzedCodes.forEach(function(analysis) {
	if (analyses.length > 0) {
	    analyses += ", ";
	}
	analyses += analysis.languageName + " " + analysis.languageVersion;
    });
    treeTableData.columns = [
	{content: fileTree.size},
	{content: fileTree.sizeRecursive},
	{content: analyses, link: "/file.html#/summary/" + treeTableData.id}
    ];
    if (fileTree.children.length > 0) {
	treeTableData.children = [];
	fileTree.children.sort(function(l, r) {
	    if ((l.children) && (l.children.length > 0)) {
		if ((r.children) && (r.children.length > 0)) {
		    return strcmp(l.name.toUpperCase(), r.name.toUpperCase());
		}
		return -1;
	    } else {
		if ((r.children) && (r.children.length > 0)) {
		    return 1;
		}
		return strcmp(l.name.toUpperCase(), r.name.toUpperCase());
	    }
	});
	fileTree.children.forEach(function(child) {
	    treeTableData.children.push(convertAnalysisFileTree(child));
	});
	treeTableData.imageUrl = 'images/icons/FatCow_Icons16x16/folder.png';
    } else {
	treeTableData.imageUrl = 'images/icons/FatCow_Icons16x16/document_green.png';
    }
    return treeTableData;
}

function strcmp(s1, s2) {
	return (s1 < s2)? - 1 : ((s1 > s2)? 1 : 0);
}

function runListCtrl($scope, $routeParams, projectManager) {
	$scope.project = undefined;
$scope.runs = undefined;
	projectManager.getProject($routeParams.projectId,
		function(data, status) {
			$scope.project = data;
		},
		function(data, status, error) {
		}
);
	projectManager.readAllRunInformation($routeParams.projectId,
		function(data, status) {
			$scope.runs = data;
	},
		function(data, status, error) {
		}
	);
};
