package com.zup.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zup.spring.model.Pessoa;
import com.zup.spring.repository.PessoaRepository;

@Service
public class PessoaService {
	@Autowired
	PessoaRepository pessoaRepository;
	
	//Serviço para salvar pessoa no banco de dados
	public Pessoa salvar(Pessoa pessoa){
		return pessoaRepository.save(pessoa);
	}
	//Serviço para alterar pessoa no banco de dados
	public int alterar(Pessoa pessoa){
		return pessoaRepository.updateByName(pessoa.getNome(),pessoa.getCpf());
		
	}
	//Serviço para buscar pessoa no banco de dados
	public Pessoa buscar(String cpf){
		return pessoaRepository.findByCpf(cpf);
	}
	//Serviço para deletar pessoa no banco de dados
	public int deletar(String cpf ) {
		return pessoaRepository.deleteByCpf(cpf);
			
	}
}
