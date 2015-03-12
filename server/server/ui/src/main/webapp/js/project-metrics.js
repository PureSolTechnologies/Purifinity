var projectMetricsModule = angular.module("projectMetricsModule", ["pluginManagerModule"]);
projectMetricsModule.controller("fileSystemMetrics", fileSystemMetrics);

function fileSystemMetrics($scope) {
	$scope.selectedEvaluator = undefined;
	$scope.metricsTreeTable = {
		columns : [ "name", "col2", "col3" ],
		id : "id1",
		name : "root",
		children: [
			{
				id : "id1",
				name : "name1"
			},
			{
				id : "id2",
				name : "name2"
			},
			{
				id : "id3",
				name : "name3",
				children: [
					{
						id : "id1",
						name : "name1"
					},
					{
						id : "id2",
						name : "name2"
					},
					{
						id : "id3",
						name : "name3"
					}
				]
			}
		]
	};
}
