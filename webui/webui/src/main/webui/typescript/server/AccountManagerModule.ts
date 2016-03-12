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

accountManagerModule.controller("userSettingsCtrl", ["$scope", "$modal", "$log", "accountManager",
    function($scope, $modal, $log, accountManager) {
        $scope.users = undefined;
        accountManager.getUsers(//
            function(data, status) { $scope.users = data }, //
            function(data, status, error) { });
        $scope.addItems = {
            name: "",
            email: "",
            password: "",
            roleId: ""
        };
        $scope.openAddUser = function() {
            var modalInstance = $modal.open({
                templateUrl: "views/admin/dialogs/addUserModalContent.html",
                controller: "addUserModalInstanceCtrl",
                resolve: {
                    items: function() {
                        return $scope.addItems;
                    }
                }
            });

            modalInstance.result.then(function(items) {
                $scope.addItems = items;
                accountManager.getUsers(//
                    function(data, status) { $scope.users = data }, //
                    function(data, status, error) { });
            }, function() {
                    $log.info("Modal dismissed at: " + new Date());
                })
        };
        $scope.editItems = {
            email: "",
            name: "",
            roleId: ""
        };
        $scope.openEditUser = function(user) {
            $scope.editItems.email = user.email;
            $scope.editItems.name = user.name;
            $scope.editItems.roleId = user.role.id;
            var modalInstance = $modal.open({
                templateUrl: "views/admin/dialogs/editUserModalContent.html",
                controller: "editUserModalInstanceCtrl",
                resolve: {
                    items: function() {
                        return $scope.editItems;
                    }
                }
            });
            modalInstance.result.then(function(items) {
                $scope.editItems = items;
                accountManager.getUsers(//
                    function(data, status) { $scope.users = data }, //
                    function(data, status, error) { });
            }, function() {
                    $log.info("Modal dismissed at: " + new Date());
                });
        };
        $scope.deleteUser = function(email) {
            accountManager.deleteAccount(email,
                function(data, status) {
                    accountManager.getUsers(//
                        function(data, status) { $scope.users = data }, //
                        function(data, status, error) { });
                },
                function(data, status, error) {
                }
                );
        }
    }]);

accountManagerModule.controller("addUserModalInstanceCtrl", ["$scope", "$modalInstance", "items", "accountManager",
    function($scope, $modalInstance, items, accountManager) {
        $scope.items = items;
        $scope.roles = undefined;
        accountManager.getRoles(//
            function(data, status) { $scope.roles = data }, //
            function(data, status, error) { });
        $scope.ok = function() {
            $modalInstance.close($scope.items);
            accountManager.createAccount($scope.items.email, $scope.items.name, $scope.items.password, $scope.items.roleId,
                function(data, status) {
                },
                function(data, status, error) {
                }
                );
        };
        $scope.cancel = function() {
            $modalInstance.dismiss("cancel");
        };
        $scope.disableOK = function() {
            if (!$scope.items.password) {
                return true;
            }
            if (!$scope.items.password2) {
                return true;
            }
            if ($scope.items.password !== $scope.items.password2) {
                return true;
            }
            if (!$scope.items.email) {
                return true;
            }
            if (!$scope.items.name) {
                return true;
            }
            return false;
        }
    }]);

accountManagerModule.controller("editUserModalInstanceCtrl", ["$scope", "$modalInstance", "items", "accountManager",
    function editUserModalInstanceCtrl($scope, $modalInstance, items, accountManager) {
        $scope.items = items;
        $scope.roles = undefined;
        accountManager.getRoles(//
            function(data, status) { $scope.roles = data }, //
            function(data, status, error) { });
        $scope.ok = function() {
            $modalInstance.close($scope.items);
            accountManager.editAccount($scope.items.email, $scope.items.name, $scope.items.roleId,
                function(data, status) {
                },
                function(data, status, error) {
                }
                );
        };
        $scope.cancel = function() {
            $modalInstance.dismiss("cancel");
        };
    }]);

accountManagerModule.controller("roleSettingsCtrl", ["$scope", "accountManager",
    function roleSettingsCtrl($scope, accountManager) {
        $scope.roles = undefined;
        accountManager.getRoles(//
            function(data, status) { $scope.roles = data }, //
            function(data, status, error) { });
    }]);
