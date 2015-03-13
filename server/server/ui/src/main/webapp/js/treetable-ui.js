var treeTableUIModule = angular.module("treeTableUIModule", [ "pluginManagerModule" ]);
treeTableUIModule.directive("treeTable", treeTable);

function treeTable() {
	return {
		restrict: "E",
		scope: {
			treeTableData: '=ngModel'
		},
		controller : treeTableCtrl,
		templateUrl: "/js/treeTable.html"
	};
}

function treeTableCtrl($scope) {
	$scope.path = [];
	$scope.path.push($scope.treeTableData);
	$scope.currentFolder = $scope.treeTableData;
	$scope.chdir = function(dir) {
		if  (dir == "..") {
			if ($scope.path.length > 1) {
				$scope.path.pop();
				$scope.currentFolder = $scope.path[$scope.path.length - 1];
			} 
			return;
		}
		for (key in $scope.currentFolder.children) {
			if ($scope.currentFolder.children[key].name == dir) {
				var newFolder = $scope.currentFolder.children[key];
				$scope.path.push(newFolder);
				$scope.currentFolder = newFolder;
				return;
			}
		}
	}
	$scope.setDir = function(dir) {
		while (($scope.path.length > 1) && ($scope.path[$scope.path.length - 1] !== dir)) {
			$scope.path.pop();
		}
		$scope.currentFolder = $scope.path[$scope.path.length - 1];
	}
	$scope.$watch('treeTableData', function(newValue, oldValue) {
		$scope.path = [];
		$scope.path.push($scope.treeTableData);
		$scope.currentFolder = $scope.treeTableData;		
	});
};
