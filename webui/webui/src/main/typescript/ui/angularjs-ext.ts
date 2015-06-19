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

purifinityUI.controller("menuCtrl", ["$scope", "$route", "$routeParams", "$location", function($scope, $route, $routeParams, $location) {
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

purifinityUI.filter("defaultDate", ["$filter", function($filter) {
    return function(value) {
        return $filter("date")(value, "yyyy-MM-dd HH:mm:ss");
    };
}]);

purifinityUI.filter("metricValue", ["$filter", function($filter) {
    return function(value) {
        if (value === parseInt(value, 10)) {
            return value;
        } else {
            return $filter("number")(value, 2);
        }
    };
}]);

purifinityUI.filter("version", function() {
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
});

purifinityUI.filter("successfulMark", ["$sce", function($sce) {
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
}]);

purifinityUI.filter("fsSize", ["$filter", function($filter) {
    return function(size: number): string {
        var magnitude = 0;
        var result = size;
        while ((result > 1024) && (magnitude < 4)) {
            result /= 1024;
            magnitude++;
        }
        switch (magnitude) {
            case 1:
                return String($filter("number")(result, 2)) + "kB";
            case 2:
                return String($filter("number")(result, 2)) + "MB";
            case 3:
                return String($filter("number")(result, 2)) + "GB";
            case 4:
                return String($filter("number")(result, 2)) + "TB";
            default:
                return String(size) + " B";
        }
    }
}]);

/**
 * We assume for that filter a duration with millisecond accuracy.
 */
purifinityUI.filter("duration", function() {
    return function(duration: number): string {
        if (duration < 0) {
            return "not finished/aborted";
        }
        var result: string = String(duration % 1000) + "ms";
        duration = Math.floor(duration / 1000);
        if (duration <= 0) {
            return result;
        }
        result = String(duration % 60) + "s " + result;
        duration = Math.floor(duration / 60);
        if (duration <= 0) {
            return result;
        }
        result = String(duration % 60) + "min " + result;
        duration = Math.floor(duration / 60);
        if (duration <= 0) {
            return result;
        }
        return String(duration) + "hr " + result;
    }
});

purifinityUI.filter("hashId", function() {
    return function(hashId: HashId): string {
        return hashId.algorithmName + ":" + hashId.hash;
    }
});
