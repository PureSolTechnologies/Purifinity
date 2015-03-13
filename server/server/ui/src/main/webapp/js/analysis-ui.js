var analysisUIModule = angular.module("analysisUIModule", [ "projectManagerModule" ]);
analysisUIModule.controller("analysisBrowserCtrl", analysisBrowserCtrl);
analysisUIModule.controller("runListCtrl", runListCtrl);

function analysisBrowserCtrl($scope, $routeParams, projectManager) {
	$scope.project = undefined;
	$scope.run = undefined;
	$scope.path = [];
	projectManager.getProject($routeParams.projectId,
		function(data, status) {
			$scope.project = data;
			projectManager.getLastRun($routeParams.projectId,
				function(data, status) {
					$scope.run = data;
					$scope.fileTree = {};
					projectManager.getAnalysisFileTree(
						$scope.project.information.projectId,
						$scope.run.runId,
						function(data, status) {
							$scope.fileTree = data;
							$scope.currentDirectory = $scope.fileTree;
							$scope.path.push($scope.currentDirectory);
						},
						function(data, status, error) {}
					);
				},
				function(data, status, error) {}
			);
		},
		function(data, status, error) {}
	);
	$scope.chdir = function(dir) {
		if  (dir == "..") {
			if ($scope.path.length > 1) {
				$scope.path.pop();
				$scope.currentDirectory = $scope.path[$scope.path.length - 1];
			} 
			return;
		}
		for (key in $scope.currentDirectory.children) {
			if ($scope.currentDirectory.children[key].name == dir) {
				var newDirectory = $scope.currentDirectory.children[key];
				$scope.path.push(newDirectory);
				$scope.currentDirectory = newDirectory;
				return;
			}
		}
	}
	$scope.setDir = function(dir) {
		while (($scope.path.length > 1) && ($scope.path[$scope.path.length - 1] !== dir)) {
			$scope.path.pop();
		}
		$scope.currentDirectory = $scope.path[$scope.path.length - 1];
	}
};

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
