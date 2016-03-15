/*
 * This JavaScript files contains Angular JS functionality to be added to an
 * application to handle user accounts for Purifinity.
 */
var accountManagerModule: angular.IModule = angular.module("accountManagerModule", ["purifinityServerModule"]);

accountManagerModule.controller("usersViewCtrl", ["$scope", function usersViewCtrl($scope) {
    /*
     * Mode may be either users or roles.
     */
    $scope.mode = "users";
    $scope.isMode = function(mode) {
        return mode === $scope.mode ? "active" : "";
    }
    $scope.getUsersPartial = function() {
        return "/views/admin/partials/" + $scope.mode + ".html";
    }
    $scope.setMode = function(mode) {
        $scope.mode = mode;
    }
}]);
