angular.module('myServices', ['ngResource'])
.factory('recursoContato', function($resource) {
	return $resource('/contato/:id', null, {
		update: {
			method : 'PUT'
		}
	});
})
.factory('cadastroDeContatos', function(recursoContato, $q, $rootScope) {
	var service = {};

	service.cadastrar = function(contato) {
		return $q(function(resolve, reject){
			if (contato.id) {
				recursoContato.update(contato, function(){
					resolve({
						mensagem : 'Contato alterado com sucesso',
						inclusao : false
					});
				}, function(erro) {
					console.log(erro);
					reject({mensagem:'Não foi possivel alterar o contato'});
				});
			}else {
				recursoContato.save(contato, function(){
					contato = {};
					resolve({
						mensagem : 'Contato cadastrado com sucesso',
						inclusao : true});
				}, function(erro) {
					console.log(erro);
					reject({mensagem:'Não foi possivel cadastrar o contato'});
				});
			}
		});
	};
	
	service.remover = function (contato) {
		return $q(function(resolve, reject){
			recursoContato.delete({id:contato.id}, contato, function(){
				resolve({
					mensagem : 'Contato excluido com sucesso',
					inclusao : false
				});
			}, function(erro){
				console.log(erro);
				reject({mensagem:'Não foi possivel remover o contato ' + contato.nome});
			});
		});
	};

	return service;
});