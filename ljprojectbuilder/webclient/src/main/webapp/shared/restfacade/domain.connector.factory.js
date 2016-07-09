function domainConnectorFactory ($http, $location, restConnectorFactory) {
	var factory = {};
	
	factory.getDomainsByProject = function(projectId) {
		return $http.get('api/domain/query/domainsbyproject/' + projectId)
		.then(
			restConnectorFactory.handleResponseSuccess,
			restConnectorFactory.handleResponseError
		);
	};
	
	factory.getDomainAll = function($scope) {
		$http.get('api/domain/query/all')
		.then(function (response) {
			content = response.data;
			$scope.domainAll = content.result;		
		});
	};
		
	factory.loadDomain = function($scope, id) {
		$http.get('api/domain/query/' + id)
		.then(function (response) {
			content = response.data;
			$scope.domain = content.result;		
		});
	};
		
	factory.createDomain = function($scope, successPath, errorPath) {
		$http.put('api/domain/', $scope.domain)
		.then(function(response) {
			restConnectorFactory.handleResponse($scope, response, successPath,  errorPath);
		});
	};
		
	factory.updateDomain = function($scope, successPath, errorPath) {
		$http.post('api/domain/', $scope.domain)
		.then(function(response) {
			restConnectorFactory.handleResponse($scope, response, successPath, errorPath);
		});
	};
		
	factory.deleteDomain = function($scope, id) {
		$http.delete('api/domain/' + id)
		.then(function(response) {
			content = response.data;
			$scope.protocol = content.result;
			factory.getDomainAll($scope);
		});
	};
	
	factory.getTypes = function() {
		return $http.get('api/domain/query/types')
		.then(function(response) {
			return content = response.data;
		});
	};
		
	return factory;
}