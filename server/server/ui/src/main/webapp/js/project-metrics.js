var projectMetricsModule = angular.module("projectMetricsModule", ["projectManagerModule", "pluginManagerModule", "purifinityServer"]);
projectMetricsModule.controller("fileSystemMetrics", fileSystemMetrics);
projectMetricsModule.controller("treeMapCtrl", treeMapCtrl);

function fileSystemMetrics($scope, $routeParams, projectManager, purifinityServerConnector) {
	$scope.selectedEvaluator = undefined;
	$scope.codeRangeType = {selected : undefined};
	$scope.fileTree = undefined;
	$scope.metricsTreeTable = {};
	$scope.metrics = {};
	$scope.project = {};
	$scope.run = {};
	$scope.codeRangeTypes = [];
	$scope.barData = [];
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
							$scope.fileTree = data;
							$scope.metricsTreeTable = convertFileTreeForMetrics(data);
							$scope.metricsTreeTable.columnHeaders = [ {name: "Name", tooltip: "Name of file or folder"} ];
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
					$scope.barData = [];
					var types = new Set();
					types.add('DIRECTORY');
					types.add('FILE');
					for (var hashid in data.fileMetrics) {
						var fileResults = data.fileMetrics[hashid];
						fileResults.codeRangeMetrics.forEach(function (metrics) {
							types.add(metrics.codeRangeType);
						});							
					}
					$scope.codeRangeTypes = [];
					for (var type of types) {
						$scope.codeRangeTypes.push({name:type});
					}
					$scope.codeRangeTypes.sort();
					$scope.selectedCodeRangeType = [];
					$scope.metrics = data;
					applyMetricsToFileTree($scope.metricsTreeTable, $scope.metrics, $scope.metrics.parameters);
					$scope.metricsTreeTable.columnHeaders = [ {name: "Name", tooltip: "Name of file or folder"} ];
					$scope.metrics.parameters.forEach(function(parameter) {
						var name = parameter.name;
						if (parameter.unit) {
							name += " [" + parameter.unit +"]";
						}
						$scope.metricsTreeTable.columnHeaders.push( {name: name, tooltip: parameter.description} );
					});
				}, 
				function(data, status, error) {}
			);
		}
	}, true);
	$scope.$watch('codeRangeType.selected', function(newValue, oldValue) {
		$scope.barData = [];
		if (newValue == 'DIRECTORY') {
			for (var hashid in $scope.metrics.directoryMetrics) {
				var metric = $scope.metrics.directoryMetrics[hashid];
				for (var valueName in metric.values) {
					var value = metric.values[valueName];
					if (!$scope.barData[value.parameter.name]) {
						$scope.barData[value.parameter.name] = [];
					}
					$scope.barData[value.parameter.name].push({name:value.parameter.name,value: value.value});
				}
			}
		} else {
			for (var hashid in $scope.metrics.fileMetrics) {
				var codeRangeMetrics = $scope.metrics.fileMetrics[hashid].codeRangeMetrics;
				if (codeRangeMetrics) {
					codeRangeMetrics.forEach(function (metric) {
						if (metric.codeRangeType == $scope.codeRangeType.selected) {
							for (var valueName in metric.values) {
								var value = metric.values[valueName];
								if (!$scope.barData[value.parameter.name]) {
									$scope.barData[value.parameter.name] = [];
								}
								$scope.barData[value.parameter.name].push({name:metric.codeRangeName,value: value.value});
							}
						}
					});
				}
			}
		}
	}, true);
	$scope.showClick  = function(item) {
		alert(item);
	};
}

function convertFileTreeForMetrics(fileTree) {
	var treeTableData = {};
	treeTableData.content = fileTree.name;
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
				var value = directoryMetrics.values[parameter.name];
				if (value) {
					treeTableData.columns.push({content:value.value});
				} else {
					treeTableData.columns.push({content:"n/a"});
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
							treeTableData.columns.push({content:value.value});
						} else {
							treeTableData.columns.push({content:"n/a"});
						}
					});
				}
			});
		}
	}
	if (!found) {
		parameters.forEach(function(parameter){
			treeTableData.columns.push({content:""});
		});
	}
}

function strcmp(s1, s2) {
	return (s1 < s2)? - 1 : ((s1 > s2)? 1 : 0);
}

function treeMapCtrl($scope) {
	// Treemap test!
	$scope.mapData = 
		{
			"name": "flare",
			"children": [
				{
					"name": "analytics",
					"children": [
						{
							"name": "cluster",
							"children": [
								{"name": "AgglomerativeCluster", "size": 3938},
								{"name": "CommunityStructure", "size": 3812},
								{"name": "MergeEdge", "size": 743}
							]
						},
						{
							"name": "graph",
		     				"children": [
								{"name": "BetweennessCentrality", "size": 3534},
								{"name": "LinkDistance", "size": 5731}
							]
						}
					]
				}
			]
		};
}