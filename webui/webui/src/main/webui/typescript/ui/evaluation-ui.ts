var evaluationUIModule: angular.IModule = angular.module("evaluationUIModule", ["pluginManagerModule"]);

evaluationUIModule.directive("evaluatorSelection",
    function evaluatorSelection() {
        return {
            restrict: "E",
            scope: {
                evaluatorSelection: "=ngModel"
            },
            controller: ['$scope', 'pluginManager', function($scope: any, pluginManager: PluginManager) {
                $scope.evaluators = {};
                pluginManager.getEvaluators(
                    function(data, status) {
                        $scope.evaluators = data;
                        $scope.evaluators.sort(function(l, r) { return (l.name < r.name) ? - 1 : ((l.name > r.name) ? 1 : 0); });
                    },
                    function(data, status, error) { }
                    );
            }],
            templateUrl: "directives/evaluator-selection.html"
        };
    });
