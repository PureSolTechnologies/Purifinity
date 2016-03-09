/* 
 * This JavaScript file contains general extensions for AngularJS which are needed
 * to enhance the functionality.
 *
 * This function adds new controller and such alike to an AngularJS application
 * to enhance the functionality.
 * 
 * @angularApplication is the AngularJS application which is to be enhanced.
 */
var purifinityUI: angular.IModule = angular.module("purifinityUI", ["ngSanitize"]);

purifinityUI.controller("menuCtrl", ["$scope", "$route", "$routeParams", "$location",
    function(
        $scope: any,
        $route: angular.route.IRouteService,
        $routeParams: angular.route.IRouteParamsService,
        $location: angular.ILocationService) {
        $scope.$route = $route;
        $scope.$location = $location;
        $scope.$routeParams = $routeParams;
        $scope.isActive = function(locationPath) {
            if ($location.path() === locationPath) {
                return "active";
            }
            if ((locationPath.length > 4) && ($location.path().indexOf(locationPath) === 0)) {
                return "active";
            }
            return "";
        };
    }]);

purifinityUI.directive('applicationTitle', function() {
    return {
        restrict: "E",
        link: function(scope: any, element, attrs) {
            attrs.$observe('text', function(value) {
                scope.text = value;
            })
        },
        scope: {
            text: '@'
        },
        controller: "applicationTitleCtrl",
        templateUrl: "directives/application-title.html"
    };
});

purifinityUI.controller('applicationTitleCtrl', ['$scope',
    function($scope) {
        $scope.bar = 'test';
    }]);
	