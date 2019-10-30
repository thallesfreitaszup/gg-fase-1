package com.zup.spring.model;


import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DadosRepositorio extends CrudRepository<Dados,Integer> {
	@Override
	public List<Dados> findAll();
	public Dados findByChave(String chave);
}
