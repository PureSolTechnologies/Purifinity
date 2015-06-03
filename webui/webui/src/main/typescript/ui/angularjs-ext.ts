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
purifinityUI.controller("menuCtrl", menuCtrl);
purifinityUI.filter("defaultDate", defaultDateFilter);
purifinityUI.filter("metricValue", metricValueFilter);
purifinityUI.filter("version", versionFilter);
purifinityUI.filter("successfulMark", successfulMarkFilter);

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
        if ($location.path() === locationPath) {
            return "active";
        }
        if ((locationPath.length > 4) && ($location.path().indexOf(locationPath) === 0)) {
            return "active";
        }
        return "";
    };
}

function defaultDateFilter($filter) {
    return function(value) {
        return $filter("date")(value, "yyyy-MM-dd HH:mm:ss");
    };
}

function metricValueFilter($filter) {
    return function(value) {
        if (value === parseInt(value, 10)) {
            return value;
        } else {
            return $filter("number")(value, 2);
        }
    };
}

function versionFilter() {
    return function(version) {
        if ((typeof version.major === "number") && (typeof version.minor === "number") && (typeof version.patch === "number")) {
            var versionString = version.major + "." + version.minor + "." + version.patch;
            if (typeof version.preReleaseInformation === "string") {
                versionString += "-" + version.preReleaseInformation;
            }
            if (typeof version.buildMetadata === "string") {
                versionString += "+" + version.buildMetadata;
            }
            return versionString;
        }
        return version;
    };
}

function successfulMarkFilter($sce) {
    return function(successful) {
        var mark = "<div style='position:relative;'>" +
        "<img src='/images/icons/FatCow_Icons16x16/source_code.png' />";
        if (successful) {
            mark += "<img style='position:absolute;top:6px;left:6px;' src='/images/icons/FatCow_Icons16x16/bullet_valid.png' />";
        } else {
            mark += "<img style='position:absolute;top:6px;left:6px;' src='/images/icons/FatCow_Icons16x16/bullet_error.png' />";
        }
        mark += "</div>";
        return $sce.trustAsHtml(mark);
    };
}
