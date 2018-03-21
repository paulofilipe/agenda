package br.com.agenda.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.agenda.model.Contato;

public interface ContatoDao extends JpaRepository<Contato, Long> {

}
