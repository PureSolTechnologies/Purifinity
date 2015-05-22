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
        $scope.values.current = {};
        $scope.values.booleanInput = false;
        $scope.values.textInput = "";
        $scope.values.numberInput = 0;
        $scope.isDefault = function(): boolean {
            if ($scope.isBoolean()) {
                return Boolean($scope.parameter.defaultValue) === $scope.values.booleanInput;
            } else if ($scope.isText()) {
                return String($scope.parameter.defaultValue) === $scope.values.textInput;
            } else if ($scope.isNumber()) {
                return Number($scope.parameter.defaultValue) === $scope.values.numberInput;
            } else {
                return String($scope.parameter.defaultValue) === String($scope.values.input);
            }
        }
        $scope.setDefault = function() {
            if ($scope.isBoolean()) {
                $scope.values.booleanInput = Boolean($scope.parameter.defaultValue);
            } else if ($scope.isText()) {
                $scope.values.textInput = String($scope.parameter.defaultValue);
            } else if ($scope.isNumber()) {
                $scope.values.numberInput = Number($scope.parameter.defaultValue);
            } else {
                $scope.values.input = String($scope.parameter.defaultValue);
            }
        }
        $scope.wasChanged = function(): boolean {
            if ($scope.isBoolean()) {
                return Boolean($scope.values.current) !== $scope.values.booleanInput;
            } else if ($scope.isText()) {
                return String($scope.values.current) !== $scope.values.textInput;
            } else if ($scope.isNumber()) {
                return Number($scope.values.current) !== $scope.values.numberInput;
            } else {
                return String($scope.values.current) !== String($scope.values.input);
            }
        }
        $scope.commit = function() {
            if ($scope.isBoolean()) {
                preferencesManager.setSystemParameter($scope.parameter.propertyKey, String($scope.values.booleanInput), function(data: any, status: number) {
                    $scope.values.current = $scope.values.booleanInput;
                }, function(data: any, status: number, error: string) {
                    });
            } else if ($scope.isText()) {
                preferencesManager.setSystemParameter($scope.parameter.propertyKey, String($scope.values.textInput), function(data: any, status: number) {
                    $scope.values.current = $scope.values.textInput;
                }, function(data: any, status: number, error: string) {
                    });
            } else if ($scope.isNumber()) {
                preferencesManager.setSystemParameter($scope.parameter.propertyKey, String($scope.values.numberInput), function(data: any, status: number) {
                    $scope.values.current = $scope.values.numberInput;
                }, function(data: any, status: number, error: string) {
                    });
            }
        }
        $scope.rollback = function() {
            if ($scope.isBoolean()) {
                $scope.values.booleanInput = Boolean($scope.values.current);
            } else if ($scope.isText()) {
                $scope.values.textInput = String($scope.values.current);
            } else if ($scope.isNumber()) {
                $scope.values.numberInput = Number($scope.values.current);
            } else {
                $scope.values.input = $scope.values.current;
            }
        }
        $scope.refresh = function() {
            preferencesManager.getSystemParameter($scope.parameter.propertyKey, function(data: any, status: number) {
                if ($scope.isBoolean()) {
                    $scope.values.booleanInput = Boolean(data);
                    $scope.values.current =  Boolean(data);
                } else if ($scope.isText()) {
                    $scope.values.booleanInput = String(data);
                    $scope.values.current =  String(data);
                } else if ($scope.isNumber()) {
                    $scope.values.booleanInput = Number(data);
                    $scope.values.current =  Number(data);
                } else {
                    $scope.values.textInput = data;
                    $scope.values.current =  data;
                }
            }, function(data: any, status: number, error: string) {
                });
         }
        $scope.isBoolean = function() {
            return $scope.parameter.valueRepresentation === "BOOLEAN";
        }
        $scope.isText = function() {
            return $scope.parameter.valueRepresentation === "STRING";
        }
        $scope.isNumber = function() {
            return ($scope.parameter.valueRepresentation === "DECIMAL") || ($scope.parameter.valueRepresentation === "INTEGER");
        }
         $scope.refresh();
     });