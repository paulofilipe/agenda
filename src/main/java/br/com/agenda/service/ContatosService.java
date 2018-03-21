package br.com.agenda.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import br.com.agenda.dao.ContatoDao;
import br.com.agenda.model.Contato;

@Service
public class ContatosService {
	
	@Autowired
	private ContatoDao contatoDao;

	public List<Contato> findAll() {
		return contatoDao.findAll();
	}

	public Contato findById(Long id) {
		Optional<Contato> optional = contatoDao.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		}
		
		throw new RuntimeException("Contato não encontrado");
	}

	public void save(Contato contato) {
		contatoBasicValidation(contato);
		contatoDao.save(contato);
	}

	public void deleteById(Long id) {
		Assert.notNull(id, "O campo id é necessário para realizar a exclusão de um contato");
		contatoDao.deleteById(id);
	}

	public void update(Contato contato) {
		contatoBasicValidation(contato);
		Assert.isTrue(contato.getId() > 0, "Contato inválido");
		contatoDao.save(contato);
	}
	
	private void contatoBasicValidation(Contato contato) {
		Assert.hasText(contato.getNome(), "O campo nome é obrigatório");
		Assert.notNull(contato.getTelefone(), "O campo telefone é obrigatório");
		Assert.isTrue(contato.getTelefone().toString().length() >= 10 && contato.getTelefone().toString().length() <12 , "telefone inválido");
	}

}
