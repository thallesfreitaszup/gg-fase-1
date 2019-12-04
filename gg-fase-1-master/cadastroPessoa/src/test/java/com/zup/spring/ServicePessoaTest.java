package com.zup.spring;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.zup.spring.model.Pessoa;
import com.zup.spring.repository.PessoaRepository;
import com.zup.spring.service.PessoaService;

class ServicePessoaTest {
	@Mock
	PessoaRepository pessoaRepository;
	@InjectMocks
	PessoaService pessoaService;
	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
		
	}
	
	@Test
	//Testa se serviço de salvar é realizado com sucesso
	void salvarTest() {
		Pessoa pessoa = new Pessoa();
		pessoa.setCpf("03227403129");
		pessoa.setNome("thalles");
		when(pessoaRepository.save(pessoa)).thenReturn(pessoa);
		assertEquals(pessoa,pessoaService.salvar(pessoa));
		
		
	}
	
	//Testa se serviço de buscar é realizado com sucesso
	@Test
	void buscarTest() {
		Pessoa pessoa = new Pessoa();
		pessoa.setCpf("03227403129");
		pessoa.setNome("thalles");
		when(pessoaRepository.findByCpf("03227403129")).thenReturn(pessoa);
		assertEquals(pessoa,pessoaService.buscar("03227403129"));	
	}
	
	//Testa se serviço de deletar é realizado com sucesso
	@Test
	void deletarTest() {
		
		when(pessoaRepository.deleteByCpf("03227403129")).thenReturn(1);
		assertEquals(1,pessoaService.deletar("03227403129"));
		
		
	}
	
	@Test
	void updateTest() {
		Pessoa pessoa = new Pessoa();
		pessoa.setCpf("03227403129");
		pessoa.setNome("marcos");
		when(pessoaRepository.updateByName(pessoa.getNome(),pessoa.getCpf())).thenReturn(1);
		assertEquals(1,pessoaService.alterar(pessoa));
		
	}
	
}
