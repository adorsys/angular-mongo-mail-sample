var sampleApp = angular.module('sampleApp', []);

sampleApp.controller('sampleAppController', function($scope, $http) {

	var loadContacts = function() {
		$http({
			method : 'GET',
			url : "rest/contact"
		}).success(function(contacts, status, headers, config) {
			$scope.contacts = contacts;
		});
	};
	loadContacts();

	$scope.addContact = function() {
		var newContact = $scope.newContact;
		var valid = $scope.newContactForm.$valid;
		if (valid) {
			$http({
				method : 'PUT',
				headers : {
					'Content-Type' : "application/json"
				},
				url : "rest/contact",
				data : newContact
			}).success(function(response, status, headers, config) {
				$scope.newContact = {};
				loadContacts();
			});
		}
	}
});