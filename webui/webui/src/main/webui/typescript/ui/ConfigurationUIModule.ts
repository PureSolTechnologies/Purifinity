var configurationUIModule: angular.IModule = angular.module("configurationUIModule",
    ["preferencesManagerModule"]);

configurationUIModule.directive("configurationComponent",
    function configurationComponent() {
        return {
            restrict: "E",
            scope: {
                configurationTreeData: "=ngModel"
            },
            controller: "configurationComponentCtrl",
            templateUrl: "directives/configuration-component.html"
        };
    });

configurationUIModule.controller("configurationComponentCtrl", ["$scope",
    function($scope) {
        $scope.path = [];
        $scope.path.push($scope.configurationTreeData.root);
        $scope.currentFolder = $scope.configurationTreeData.root;
        $scope.chdir = function(dir: string) {
            if (dir === "..") {
                if ($scope.path.length > 1) {
                    $scope.path.pop();
                    $scope.currentFolder = $scope.path[$scope.path.length - 1];
                }
                return;
            }
            for (var key in $scope.currentFolder.children) {
                if ($scope.currentFolder.children[key].name === dir) {
                    var newFolder = $scope.currentFolder.children[key];
                    $scope.path.push(newFolder);
                    $scope.currentFolder = newFolder;
                    return;
                }
            }
        };
        $scope.setDir = function(dir: string) {
            while (($scope.path.length > 1)
                && ($scope.path[$scope.path.length - 1] !== dir)) {
                $scope.path.pop();
            }
            $scope.currentFolder = $scope.path[$scope.path.length - 1];
        };
        $scope.$watch("configurationTreeData", function(newValue, oldValue) {
            $scope.path = [];
            $scope.path.push($scope.configurationTreeData.root);
            $scope.currentFolder = $scope.configurationTreeData.root;
        });
    }]);
