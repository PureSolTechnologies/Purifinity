/*
 * This JavaScript files contains Angular JS functionality to be added to an
 * application to handle user accounts for Purifinity.
 */
var accountManagerModule = angular.module("accountManager", [ "purifinityServer" ]);
purifinityServer.factory('userAdministratorFactory', [
		'purifinityServerConnector', userAdministratorFactory ]);
accountManagerModule.controller("usersViewCtrl", usersViewCtrl);
accountManagerModule.controller("userSettingsCtrl", userSettingsCtrl);
accountManagerModule.controller("addUserModalCtrl", addUserModalCtrl);
accountManagerModule.controller("addUserModalInstanceCtrl", addUserModalInstanceCtrl);
accountManagerModule.controller("roleSettingsCtrl", roleSettingsCtrl);

function userAdministratorFactory(purifinityServerConnector) {
	var userAdministratorFactory = {};
	userAdministratorFactory.getUsers = function(success, error) {
		return purifinityServerConnector.get('/accountmanager/rest/users',
				success, error);
	};
	userAdministratorFactory.getRoles = function(success, error) {
		return purifinityServerConnector.get('/accountmanager/rest/roles',
				success, error);
	};
	return userAdministratorFactory;
}

function usersViewCtrl($scope) {
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
}

 function userSettingsCtrl($scope, userAdministratorFactory) {
	$scope.users = undefined;
	userAdministratorFactory.getUsers(//
		function(data, status) {$scope.users = data}, //
		function(data, status, error) {});
}

 function addUserModalCtrl($scope, $modal, $log) {
	$scope.items = {
					name:"", 
					email:""};
	$scope.open = function (size) {
		var modalInstance = $modal.open({
			templateUrl: 'addUserModalContent.html',
			controller: 'addUserModalInstanceCtrl',
			size: size, 
			resolve: {
				items: function () {
					return $scope.items;
				}
			}
	    });
	    
	    modalInstance.result.then(function (items) {
	      $scope.items = items;
	    }, function () {
	      $log.info('Modal dismissed at: ' + new Date());
	    })
	};
}

function addUserModalInstanceCtrl($scope, $modalInstance, items) {
	  $scope.items = items;
	  $scope.ok = function () {
	    $modalInstance.close($scope.items);
	  };
	  $scope.cancel = function () {
	    $modalInstance.dismiss('cancel');
	  };
	$scope.disableOK = function() {
		if (!$scope.items.password) {
			return true;
		}
		if (!$scope.items.password2) {
			return true;
		}
		if ($scope.items.password != $scope.items.password2) {
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
}

function roleSettingsCtrl($scope, userAdministratorFactory) {
	$scope.roles = undefined;
	userAdministratorFactory.getRoles(//
		function(data, status) {$scope.roles = data}, //
		function(data, status, error) {});
}
