package com.zup.spring.model;


import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DadosRepositorio extends CrudRepository<Dados,Integer> {
	public Dados findByValor(String valor);
	@Override
	public List<Dados> findAll();
}
