var configurationUIModule: angular.IModule = angular.module("configurationUIModule",
    ["preferencesManagerModule"]);

configurationUIModule.directive("configurationComponent",
    function configurationComponent() {
        return {
            restrict: "E",
            scope: {
                configurationTreeData: '=ngModel'
            },
            controller: "configurationComponentCtrl",
            templateUrl: "directives/configuration-component.html"
        };
    });

configurationUIModule.controller("configurationComponentCtrl",
    function($scope) {
        $scope.path = [];
        $scope.path.push($scope.configurationTreeData.root);
        $scope.currentFolder = $scope.configurationTreeData.root;
        $scope.chdir = function(dir: string) {
            if (dir == "..") {
                if ($scope.path.length > 1) {
                    $scope.path.pop();
                    $scope.currentFolder = $scope.path[$scope.path.length - 1];
                }
                return;
            }
            for (var key in $scope.currentFolder.children) {
                if ($scope.currentFolder.children[key].name == dir) {
                    var newFolder = $scope.currentFolder.children[key];
                    $scope.path.push(newFolder);
                    $scope.currentFolder = newFolder;
                    return;
                }
            }
        }
        $scope.setDir = function(dir: string) {
            while (($scope.path.length > 1)
                && ($scope.path[$scope.path.length - 1] !== dir)) {
                $scope.path.pop();
            }
            $scope.currentFolder = $scope.path[$scope.path.length - 1];
        }
        $scope.$watch('configurationTreeData', function(newValue, oldValue) {
            $scope.path = [];
            $scope.path.push($scope.configurationTreeData.root);
            $scope.currentFolder = $scope.configurationTreeData.root;
        });
    });

configurationUIModule.directive("configurationParameter",
    function() {
        return {
            restrict: "E",
            scope: {
                parameter: '=ngModel'
            },
            controller: "configurationParameterCtrl",
            templateUrl: "directives/configuration-parameter.html"
        };
    });

configurationUIModule.controller("configurationParameterCtrl",
    function($scope, preferencesManager) {
        $scope.values = {};
        $scope.values.current = "";
        $scope.values.input = $scope.values.current;
        $scope.isDefault = function(): boolean {
            return String($scope.parameter.defaultValue) === String($scope.values.input);
        }
        $scope.setDefault = function() {
            $scope.values.input = String($scope.parameter.defaultValue);
        }
        $scope.wasChanged = function(): boolean {
            return String($scope.values.current) !== String($scope.values.input);
        }
        $scope.commit = function() {
            preferencesManager.setSystemParameter($scope.parameter.propertyKey, String($scope.values.input), function(data: any, status: number) {
                $scope.values.current = String($scope.values.input);
            }, function(data: any, status: number, error: string) {
                });
        }
        $scope.rollback = function() {
            $scope.values.input = $scope.values.current;
        }
        $scope.refresh = function() {
            preferencesManager.getSystemParameter($scope.parameter.propertyKey, function(data: any, status: number) {
                $scope.values.input = data;
                $scope.values.current = $scope.values.input;
            }, function(data: any, status: number, error: string) {
                });
        }
        $scope.refresh();
    });