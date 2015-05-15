var configurationUIModule = angular.module("configurationUIModule",
		[ "preferencesManagerModule" ]);
configurationUIModule.directive("configurationComponent",
		configurationComponent);

function configurationComponent() {
	return {
		restrict : "E",
		scope : {
			configurationTreeData : '=ngModel'
		},
		controller : configurationComponentCtrl,
		templateUrl : "directives/configuration-component.html"
	};
}

function configurationComponentCtrl($scope) {
	$scope.path = [];
	$scope.path.push($scope.configurationTreeData);
	$scope.currentFolder = $scope.configurationTreeData;
	$scope.chdir = function(dir) {
		if (dir == "..") {
			if ($scope.path.length > 1) {
				$scope.path.pop();
				$scope.currentFolder = $scope.path[$scope.path.length - 1];
			}
			return;
		}
		for (key in $scope.currentFolder.children) {
			if ($scope.currentFolder.children[key].content == dir) {
				var newFolder = $scope.currentFolder.children[key];
				$scope.path.push(newFolder);
				$scope.currentFolder = newFolder;
				return;
			}
		}
	}
	$scope.setDir = function(dir) {
		while (($scope.path.length > 1)
				&& ($scope.path[$scope.path.length - 1] !== dir)) {
			$scope.path.pop();
		}
		$scope.currentFolder = $scope.path[$scope.path.length - 1];
	}
	$scope.$watch('configurationTreeData', function(newValue, oldValue) {
		$scope.path = [];
		$scope.path.push($scope.configurationTreeData);
		$scope.currentFolder = $scope.configurationTreeData;
	});
};
