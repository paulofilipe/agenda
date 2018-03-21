angular.module('agenda-app',['ngRoute','myServices'])
.config(function($routeProvider, $locationProvider) {

	$locationProvider.html5Mode(true);

	$routeProvider.when('/', {
		templateUrl: 'partials/principal.html',
		controller: 'ContatosController'
	});

	$routeProvider.when('/contatos/add', {
		templateUrl: 'partials/contato.html',
		controller: 'ContatosController'
	});
	
	$routeProvider.when('/contatos/edit/:id', {
		templateUrl: 'partials/contato.html',
		controller: 'ContatosController'
	})

	$routeProvider.otherwise({redirectTo: '/'});
});