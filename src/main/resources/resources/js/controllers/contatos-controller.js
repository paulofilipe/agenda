angular.module('agenda-app').controller('ContatosController', function($scope, $http, recursoContato, cadastroDeContatos, $routeParams) {
	$scope.contatos = [];
	$scope.filtro = '';
	$scope.contato = {};

	$http.get('/contato/').success(function (response) {
		$scope.contatos = response.data;
	}).error(function (erro) {
		console.log(erro);
	});
	
	if ($routeParams.id){
		recursoContato.get({id : $routeParams.id}, function(response) {
			$scope.contato = response.data;
		}, function(erro) {
			console.log(erro);
			$scope.mensagem = 'Não foi possível obter o contato';
		});
	}
	
	$scope.save = function () {
		if ($scope.formulario.$valid){
			cadastroDeContatos.cadastrar($scope.contato)
			.then(function(dados) {
				$scope.mensagem = dados.mensagem;
				if(dados.inclusao) $scope.contato = {};
			})
			.catch(function(dados){
				$scope.mensagem = dados.mensagem;
			})
		}
	}

	$scope.remover = function (contato) {
		cadastroDeContatos.remover(contato)
		.then(function(dados){
			$scope.mensagem = dados.mensagem;
			var indice = $scope.contatos.indexOf(contato);
			$scope.contatos.splice(indice, 1);
		})
		.catch(function(dados){
			$scope.mensagem = dados.mensagem;
		});
	}
});