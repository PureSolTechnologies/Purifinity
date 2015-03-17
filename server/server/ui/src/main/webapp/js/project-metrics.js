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
							$scope.metricsTreeTable.columns = [ "Name" ];
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
					applyMetricsToFileTree($scope.metricsTreeTable, $scope.metrics, $scope.metrics.parameters);
					$scope.metricsTreeTable.columns = [ "Name" ];
					$scope.metrics.parameters.forEach(function(parameter) {
						$scope.metricsTreeTable.columns.push(parameter.name);
					});
				}, 
				function(data, status, error) {}
			);
		}
	});
}

function convertFileTreeForMetrics(fileTree) {
	var treeTableData = {};
	treeTableData.name = fileTree.name;
	treeTableData.id = fileTree.hashId.algorithmName + ":" + fileTree.hashId.hash;
	treeTableData.columns = [];
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

function applyMetricsToFileTree(treeTableData, runMetrics, parameters) {
	var found = false;
	treeTableData.columns = [];
	if (treeTableData.children && (treeTableData.children.length > 0)) {
		var directoryMetrics = runMetrics.directoryMetrics[treeTableData.id];
		if (directoryMetrics) {
			found = true;
			parameters.forEach(function(parameter){
				var value = metric.values[parameter.name];
				if (value) {
					treeTableData.columns.push({name:value.value});
				} else {
					treeTableData.columns.push({name:"n/a"});
				}
			});			
		}
		treeTableData.children.forEach(function(child) {
			applyMetricsToFileTree(child, runMetrics, parameters);
		});
	} else {
		var fileMetrics = runMetrics.fileMetrics[treeTableData.id];
		if (fileMetrics) {
			fileMetrics.codeRangeMetrics.forEach(function(metric) {
				if (metric.codeRangeType == "FILE") {
					found = true;
					parameters.forEach(function(parameter){
						var value = metric.values[parameter.name];
						if (value) {
							treeTableData.columns.push({name:value.value});
						} else {
							treeTableData.columns.push({name:"n/a"});
						}
					});
				}
			});
		}
	}
	if (!found) {
		parameters.forEach(function(parameter){
			treeTableData.columns.push({name:""});
		});
	}
}

function strcmp(s1, s2) {
	return (s1 < s2)? - 1 : ((s1 > s2)? 1 : 0);
}
