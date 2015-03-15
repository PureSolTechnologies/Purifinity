var projectMetricsModule = angular.module("projectMetricsModule", ["projectManagerModule", "pluginManagerModule", "purifinityServer"]);
projectMetricsModule.controller("fileSystemMetrics", fileSystemMetrics);

function fileSystemMetrics($scope, $routeParams, projectManager, purifinityServerConnector) {
	$scope.selectedEvaluator = undefined;
	$scope.metricsTreeTable = {};
	$scope.metrics = {};
	$scope.project = {};
	$scope.run = {};
	projectManager.getProject($routeParams.projectId,
		function(data, status) {
			$scope.project = data;
			projectManager.getLastRun($routeParams.projectId,
				function(data, status) {
					$scope.run = data;
					projectManager.getAnalysisFileTree(
						$scope.project.information.projectId,
						$scope.run.runId,
						function(data, status) {
							$scope.metricsTreeTable = convertFileTreeForMetrics(data);
							$scope.metricsTreeTable.columns = [ "Name", "Metrics..." ];
						},
						function(data, status, error) {}
					);
				},
				function(data, status, error) {}
			);
		},
		function(data, status, error) {}
	);
	$scope.$watch('selectedEvaluator', function(newValue, oldValue) {
		if ($scope.project.information && $scope.run.runId && newValue) {
			purifinityServerConnector.get('/purifinityserver/rest/evaluatorstore/metrics/' + $scope.project.information.projectId + '/' + $scope.run.runId + '/' + newValue, 
				function(data, status) {
					$scope.metrics = data;
				}, 
				function(data, status, error) {}
			);
		}
	});
}

function convertFileTreeForMetrics(fileTree) {
	var treeTableData = {};
	treeTableData.name = fileTree.name;
	treeTableData.id = fileTree.hashId.algorithm + ":" + fileTree.hashId.hash;
	treeTableData.columns = [
		{
			name : "column",
			id : "id"
		}
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
			treeTableData.children.push(convertFileTreeForMetrics(child));
		});
		treeTableData.imageUrl = '/images/icons/FatCow_Icons16x16/folder.png';
	} else {
		treeTableData.imageUrl = '/images/icons/FatCow_Icons16x16/document_green.png';
	}
	return treeTableData;
}

function strcmp(s1, s2) {
	return (s1 < s2)? - 1 : ((s1 > s2)? 1 : 0);
}
