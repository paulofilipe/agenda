package br.com.agenda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.agenda.model.Contato;
import br.com.agenda.service.ContatosService;
import br.com.agenda.util.HTTPResponse;

@RestController
@RequestMapping("/")
public class ContatosController {

	@Autowired
	private ContatosService contatoService;
	
	@RequestMapping(value = "contato", method = RequestMethod.GET)
	public @ResponseBody HTTPResponse getContatos() {		
		return HTTPResponse.sucess(contatoService.findAll());
	}
	
	@RequestMapping(value = "contato/{id}", method = RequestMethod.GET)
	public @ResponseBody HTTPResponse getContato(@PathVariable("id") Long id) {
		
		try {
			return HTTPResponse.sucess(contatoService.findById(id));			
		} catch (Exception e) {
			return HTTPResponse.error(String.format("Ocorreu um erro ao buscar comtato com o id %s", id), e.getMessage());
		}
	}
	
	@RequestMapping(value = "contato", method = RequestMethod.POST)
	public @ResponseBody HTTPResponse addContato(@RequestBody Contato contato) {
		
		try {
			contatoService.save(contato);
			return HTTPResponse.sucess("Contato adicionado com sucesso");
		} catch (Exception e) {
			return HTTPResponse.error(String.format("Ocorreu um erro ao adicionar Contato: $s", contato), e.getMessage());
		}
	}

	@RequestMapping(value = "contato", method = RequestMethod.PUT)
	public @ResponseBody HTTPResponse editContato(@RequestBody Contato contato) {
		
		try {
			contatoService.update(contato);
			return HTTPResponse.sucess("Contato alterado com sucesso");
		} catch (Exception e) {
			return HTTPResponse.error(String.format("Ocorreu um erro ao adicionar Contato: $s", contato), e.getMessage());
		}
	}
	
	@RequestMapping(value = "contato/{id}", method = RequestMethod.DELETE)
	public @ResponseBody HTTPResponse addContato(@PathVariable("id") Long id) {
		
		try {
			contatoService.deleteById(id);
			return HTTPResponse.sucess("Contato removido com sucesso");
		} catch (Exception e) {
			return HTTPResponse.error(String.format("Ocorreu um erro ao remover Contato com id $s", id), e.getMessage());
		}
	}
}
