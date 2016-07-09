var projectControllers = {};
projectControllers.loadProjectController = function($rootScope, $scope, $location, projectConnectorFactory, $translate, gotoProject) {

	$scope.projectAll = [];
	$scope.refresh = function() { projectConnectorFactory.getProjectAll($scope); };
	$scope.gotoUpdateProject = function(id) { gotoProject.update($location, id); };
	$scope.gotoCreateProject = function () { gotoProject.create($location); };
	$scope.deleteProject = function(id) {	projectConnectorFactory.deleteProject($scope, id);};
	$scope.setSelected = function (idSelected) { $scope.idSelected = idSelected; };
	
	init();
	function init() {
		//change title on view change
		$scope.$on('$routeChangeSuccess', function (scope, next, current) {
			$scope.title=next.title;
			$scope.subtitle=next.subtitle;
		});
		$scope.refresh();
	}
	
	$scope.doBack = function () {
		gotoProject.back($location);
	};
};

projectControllers.maintainProjectController = function ($rootScope, $scope, $routeParams, $location, projectConnectorFactory, $translate, gotoProject) {
	
	$scope.project = {};
	$scope.gotoAll = function() { gotoProject.all($location); };
	$scope.gotoUpdateProject = function() { gotoUpdateProject.update($location, id); }
	$scope.gotoCreateProject = function () { gotoProject.create($location); };
	init();
	
	function init() {
		$scope.$on('$routeChangeSuccess', function (scope, next, current) {
			$scope.title = next.title;
			$scope.mode = next.mode;
			if ($routeParams.id != undefined) {
				projectConnectorFactory.loadProject($scope, $routeParams.id);
			}
		});
	};
	
	function showDialog(dialogtitle, dialogtext, dialogid) {
		$rootScope.dialog.title = dialogtitle;
		$rootScope.dialog.text = dialogtext;
		document.getElementById(dialogid).style.display = 'block';
	};
	
	function showGenerationResultDialog(response) {
		if (response.responseCode == 'OK' || response.responseCode == 'EMPTY') {
			showDialog("project.dialog.success.title", response.message, 'successdialog');
		} else {
			showDialog("project.dialog.error.title", response.message, 'errordialog');
		}
	};
	
	function saveSuccess(response) {
		$scope.project = response;
		showDialog("project.dialog.success.title", "project.save.success", 'successdialog');
	};
	
	function saveError(response) {
		$rootScope.dialog.title = "project.dialog.error.title";
		showDialog("project.dialog.error.title", "project.save.error", 'errordialog');
	};
	
	$scope.doMaintain = function () {
		$rootScope.dialog = {};
		if ($scope.mode == 'update') {
			projectConnectorFactory.updateProject($scope.project).then(
					saveSuccess, 
					saveError
			);
		} else {
			projectConnectorFactory.createProject($scope.project).then(
					saveSuccess, 
					saveError
			);
		}
	};
	
	$scope.doGenerate = function () {
		$rootScope.dialog = {};
		projectConnectorFactory.generate($scope.project)
		.then(showGenerationResultDialog);
	};
	
	$scope.doRename = function () {
		$rootScope.dialog = {};
		projectConnectorFactory.rename($scope.project)
		.then(showGenerationResultDialog);
	};
	
	$scope.doRenamePackage = function () {
		$rootScope.dialog = {};
		projectConnectorFactory.renamePackage($scope.project)
		.then(showGenerationResultDialog);
	};
	
	$scope.doBack = function () {
		gotoProject.all($location);
	};
};