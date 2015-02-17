/*
 * This JavaScript files contains Angular JS functionality to be added to an
 * application to handle user accounts for Purifinity.
 */
var accountManagerModule = angular.module("accountManagerModule", [ "purifinityServer" ]);
accountManagerModule.factory('accountManager', [
		'purifinityServerConnector', accountManager ]);
accountManagerModule.controller("usersViewCtrl", usersViewCtrl);
accountManagerModule.controller("userSettingsCtrl", userSettingsCtrl);
accountManagerModule.controller("addUserModalCtrl", addUserModalCtrl);
accountManagerModule.controller("addUserModalInstanceCtrl", addUserModalInstanceCtrl);
accountManagerModule.controller("editUserModalInstanceCtrl", editUserModalInstanceCtrl);
accountManagerModule.controller("roleSettingsCtrl", roleSettingsCtrl);

function accountManager(purifinityServerConnector) {
	var accountManager = {};
	accountManager.getUsers = function(success, error) {
		return purifinityServerConnector.get('/accountmanager/rest/users',
				success, error);
	};
	accountManager.createAccount = function(email, name, password, roleId, success, error) {
		var data = {
			email: email,
			name: name,
			password: password,
			roleId: roleId
		};
		return purifinityServerConnector.put('/accountmanager/rest/users', data, 
				success, error);
	};
	accountManager.editAccount = function(email, name, roleId, success, error) {
		var data = {
			name: name,
			roleId: roleId
		};
		return purifinityServerConnector.post('/accountmanager/rest/users/' + email, data, 
				success, error);
	};
	accountManager.deleteAccount = function(email, success, error) {
		var data = {};
		return purifinityServerConnector.del('/accountmanager/rest/users/' + email,  
				success, error);
	};
	accountManager.getRoles = function(success, error) {
		return purifinityServerConnector.get('/accountmanager/rest/roles',
				success, error);
	};
	return accountManager;
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

 function userSettingsCtrl($scope, $modal, $log, accountManager) {
	$scope.users = undefined;
	accountManager.getUsers(//
		function(data, status) {$scope.users = data}, //
		function(data, status, error) {});
	$scope.items = {
					email: "",
					name: "", 
					roleId: ""};
	$scope.openEditUser = function (user) {
		$scope.items.email = user.email;
		$scope.items.name = user.name;
		$scope.items.roleId = user.role.id;
		var modalInstance = $modal.open({
			templateUrl: 'views/admin/dialogs/editUserModalContent.html',
			controller: 'editUserModalInstanceCtrl',
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
	$scope.deleteUser = function (email) {
		accountManager.deleteAccount(email, 
			function (data, status) {
				accountManager.getUsers(//
					function(data, status) {$scope.users = data}, //
					function(data, status, error) {});
			},
			function (data, status, error) {
			}
		);
	}
}

 function addUserModalCtrl($scope, $modal, $log) {
	$scope.items = {
					name:"", 
					email:"",
					password:"",
					roleId:""};
	$scope.open = function () {
		var modalInstance = $modal.open({
			templateUrl: 'views/admin/dialogs/addUserModalContent.html',
			controller: 'addUserModalInstanceCtrl',
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

function addUserModalInstanceCtrl($scope, $modalInstance, items, accountManager) {
	$scope.items = items;
	$scope.roles = undefined;
	accountManager.getRoles(//
		function(data, status) {$scope.roles = data}, //
		function(data, status, error) {});
	$scope.ok = function () {
	    $modalInstance.close($scope.items);
		accountManager.createAccount($scope.items.email, $scope.items.name, $scope.items.password, $scope.items.roleId, 
					function (data, status) {
			},
			function (data, status, error) {
			}
		);
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

function editUserModalInstanceCtrl($scope, $modalInstance, items, accountManager) {
	$scope.items = items;
	$scope.roles = undefined;
	accountManager.getRoles(//
		function(data, status) {$scope.roles = data}, //
		function(data, status, error) {});
	$scope.ok = function () {
	    $modalInstance.close($scope.items);
		accountManager.editAccount($scope.items.email, $scope.items.name, $scope.items.roleId, 
			function (data, status) {
			},
			function (data, status, error) {
			}
		);
	};
	$scope.cancel = function () {
	    $modalInstance.dismiss('cancel');
	};
}

function roleSettingsCtrl($scope, accountManager) {
	$scope.roles = undefined;
	accountManager.getRoles(//
		function(data, status) {$scope.roles = data}, //
		function(data, status, error) {});
}
