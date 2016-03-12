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

configurationUIModule.directive("configurationParameter",
    function() {
        return {
            restrict: "E",
            scope: {
                parameter: "=ngModel"
            },
            controller: "configurationParameterCtrl",
            templateUrl: "directives/configuration-parameter.html"
        };
    });

configurationUIModule.controller("configurationParameterCtrl", ["$scope", "preferencesManager",
    function($scope, preferencesManager) {
        $scope.values = {};
        $scope.values.booleanInput = false;
        $scope.values.textInput = "";
        $scope.values.numberInput = 0;
        $scope.overriding = false;
        $scope.isDefault = function(): boolean {
            if ($scope.isLocked()) {
                return true;
            }
            if ($scope.isBoolean()) {
                return $scope.values.default === $scope.values.booleanInput;
            } else if ($scope.isText()) {
                return $scope.values.default === $scope.values.textInput;
            } else if ($scope.isNumber()) {
                return $scope.values.default === $scope.values.numberInput;
            } else {
                return $scope.values.default === $scope.values.textInput;
            }
        };
        $scope.setDefault = function() {
            if ($scope.isBoolean()) {
                $scope.values.booleanInput = $scope.values.default;
            } else if ($scope.isText()) {
                $scope.values.textInput = $scope.values.default;
            } else if ($scope.isNumber()) {
                $scope.values.numberInput = $scope.values.default;
            } else {
                $scope.values.textInput = $scope.values.default;
            }
        };
        $scope.wasChanged = function(): boolean {
            if ($scope.isLocked()) {
                return false;
            }
            if ($scope.isBoolean()) {
                return $scope.values.current !== $scope.values.booleanInput;
            } else if ($scope.isText()) {
                return $scope.values.current !== $scope.values.textInput;
            } else if ($scope.isNumber()) {
                return $scope.values.current !== $scope.values.numberInput;
            } else {
                return $scope.values.current !== $scope.values.textInput;
            }
        };
        $scope.commit = function() {
            if ($scope.isBoolean()) {
                preferencesManager.setParameter($scope.parameter, String($scope.values.booleanInput), function(data: any, status: number) {
                    $scope.values.current = $scope.values.booleanInput;
                }, function(data: any, status: number, error: string) {
                    });
            } else if ($scope.isText()) {
                preferencesManager.setParameter($scope.parameter, String($scope.values.textInput), function(data: any, status: number) {
                    $scope.values.current = $scope.values.textInput;
                }, function(data: any, status: number, error: string) {
                    });
            } else if ($scope.isNumber()) {
                preferencesManager.setParameter($scope.parameter, String($scope.values.numberInput), function(data: any, status: number) {
                    $scope.values.current = $scope.values.numberInput;
                }, function(data: any, status: number, error: string) {
                    });
            }
        };
        $scope.rollback = function() {
            if ($scope.isBoolean()) {
                $scope.values.booleanInput = $scope.values.current;
            } else if ($scope.isText()) {
                $scope.values.textInput = $scope.values.current;
            } else if ($scope.isNumber()) {
                $scope.values.numberInput = $scope.values.current;
            } else {
                $scope.values.textInput = $scope.values.current;
            }
        };
        $scope.refresh = function() {
            preferencesManager.getParameter($scope.parameter, function(data: any, status: number) {
                if ($scope.isBoolean()) {
                    if (data) {
                        $scope.values.booleanInput = (data === "true");
                    } else {
                        $scope.values.booleanInput = $scope.parameter.defaultValue;
                    }
                    $scope.values.current = $scope.values.booleanInput;
                    $scope.values.default = $scope.parameter.defaultValue;
                } else if ($scope.isText()) {
                    if (data) {
                        $scope.values.textInput = String(data);
                    } else {
                        $scope.values.textInput = $scope.parameter.defaultValue;
                    }
                    $scope.values.current = $scope.values.textInput;
                    $scope.values.default = String($scope.parameter.defaultValue);
                } else if ($scope.isNumber()) {
                    if (data) {
                        $scope.values.numberInput = Number(data);
                    } else {
                        $scope.values.numberInput = $scope.parameter.defaultValue;
                    }
                    $scope.values.current = $scope.values.numberInput;
                    $scope.values.default = Number($scope.parameter.defaultValue);
                } else {
                    if (data) {
                        $scope.values.textInput = data;
                    } else {
                        $scope.values.textInput = $scope.parameter.defaultValue;
                    }
                    $scope.values.current = data;
                    $scope.values.default = data;
                }
                if ($scope.values.current || (!$scope.isOverride())) {
                    $scope.overriding = true;
                }
            }, function(data: any, status: number, error: string) {
                });
        };
        $scope.isBoolean = function() {
            return $scope.parameter.valueRepresentation === "BOOLEAN";
        };
        $scope.isText = function() {
            return $scope.parameter.valueRepresentation === "STRING";
        };
        $scope.isNumber = function() {
            return ($scope.parameter.valueRepresentation === "DECIMAL") || ($scope.parameter.valueRepresentation === "INTEGER");
        };
        $scope.getBooleanText = function() {
            if ($scope.values.booleanInput) {
                return "enabled";
            }
            return "disabled";
        };
        $scope.getBooleanButtonClass = function() {
            if ($scope.values.booleanInput) {
                return "btn-success";
            }
            return "btn-danger";
        };
        $scope.toggleBoolean = function() {
            $scope.values.booleanInput = !$scope.values.booleanInput;
        };
        $scope.isOverride = function(): boolean {
            switch ($scope.parameter.preferencesGroup) {
                case PreferencesGroup.SYSTEM:
                case PreferencesGroup.PLUGIN_DEFAULT:
                case PreferencesGroup.USER_DEFAULT:
                    return false;
                case PreferencesGroup.PLUGIN_PROJECT:
                case PreferencesGroup.USER:
                    return true;
                default:
                    return false;
            }
        };
        $scope.isOverriding = function(): boolean {
            return $scope.overriding;
        };
        $scope.toggleOverriding = function() {
            if ($scope.overriding) {
                preferencesManager.deleteParameter($scope.parameter, function(data: any, status: number) {
                }, function(data: any, status: number, error: string) {
                    });
            }
            $scope.overriding = !$scope.overriding;
        };
        $scope.getOverrideButtonClass = function() {
            if ($scope.isOverriding()) {
                return "btn-success";
            } else {
                return "btn-default";
            }
        };
        $scope.isLocked = function(): boolean {
            if (!$scope.isOverride()) {
                return false;
            }
            return !$scope.isOverriding();
        };
        $scope.refresh();
    }]);
