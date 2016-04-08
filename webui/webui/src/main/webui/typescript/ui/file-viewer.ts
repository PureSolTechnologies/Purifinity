var fileViewerModule: angular.IModule = angular
    .module("fileViewerModule", ["fileStoreModule"]);



fileViewerModule.directive("sourceCode",
    function() {
        return {
            restrict: "E",
            scope: {
                sourceCodeData: "=ngModel"
            },
            controller: ['$scope', function($scope: any) {
            }],
            templateUrl: "directives/source-code.html"
        };
    });




fileViewerModule.directive("ustView",
    function ustView() {
        return {
            restrict: "E",
            replace: false,
            scope: {
                ustViewData: "=ngModel"
            },
            controller: ['$scope', function ustViewCtrl($scope) {
            }],
            link: function(scope: any, element, attrs) {
                element.append($("<div/>").text("Hallo").append(
                    $("<span/>").text(scope.ustViewData)));
            }
        };
    });
