/* 
 * This JavaScript file contains general extensions for AngularJS which are needed
 * to enhance the functionality.
 *
 * This function adds new controller and such alike to an AngularJS application
 * to enhance the functionality.
 * 
 * @angularApplication is the AngularJS application which is to be enhanced.
 */
var purifinityUI = angular.module("purifinityUI", ["pluginManagerModule"]);
purifinityUI.controller("menuCtrl", menuCtrl);
purifinityUI.directive("evaluatorSelection", evaluatorSelection);

/**
 * This is a menu controller to have a chance to mark items as active in a
 * Bootstrap navigation bar.
 * 
 * @param $scope
 *            is injected
 * @param $route
 *            is injected
 * @param $routeParams
 *            is injected
 * @param $location
 *            is injected
 */
function menuCtrl($scope, $route, $routeParams, $location) {
	$scope.$route = $route;
	$scope.$location = $location;
	$scope.$routeParams = $routeParams;
	$scope.isActive = function(locationPath) {
		if ($location.path() == locationPath) {
			return "active";
		}
		if ((locationPath.length > 4) && ($location.path().indexOf(locationPath) == 0)) {
			return "active";
		}
		return "";
	}
}

function evaluatorSelection() {
	return {
		restrict: "E",
		link: function(scope, element, attrs) {
			attrs["ngBind"];
		},
		scope: {
			evaluatorSelection: '=ngModel'
		},
		controller : function($scope, pluginManager) {
			$scope.evaluators = {};
			pluginManager.getEvaluators(
				function(data, status) {
					$scope.evaluators = data;
					$scope.evaluators.sort(function(l, r) {return (l.name < r.name)? - 1 : ((l.name > r.name)? 1 : 0);});
				}, 
				function(data, status, error) {}
			);
		},
		templateUrl: "/js/evaluatorSelection.html"
	};
}