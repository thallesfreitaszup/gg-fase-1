package com.zup.spring;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.zup.spring.model.Pessoa;
import com.zup.spring.repository.PessoaRepository;
import com.zup.spring.service.PessoaService;

class ServicePessoaTest {
	PessoaRepository pessoaRepository;
	PessoaService pessoaService;
	@BeforeEach
	public void setup() {
		
		pessoaRepository = Mockito.mock(PessoaRepository.class);
		pessoaService = new PessoaService();
		pessoaService.setPessoaRepository(pessoaRepository);
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
	//Testa quando serviço de salvar não é feito com sucesso
	@Test
	void salvarTestEmpty() {
		Pessoa pessoa = null;
		
		when(pessoaRepository.save(pessoa)).thenReturn(pessoa);
		assertEquals(null,pessoaService.salvar(pessoa));
		
		
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
	//Testa quando serviço de buscar não é feito com sucesso
	@Test
	void buscarVazioTest() {
		
		when(pessoaRepository.findByCpf("444444444444")).thenReturn(null);
		assertEquals(null,pessoaService.buscar("444444444444"));
	}
	//Testa se serviço de deletar é realizado com sucesso
	@Test
	void deletarTest() {
		
		when(pessoaRepository.deleteByCpf("03227403129")).thenReturn(1);
		assertEquals(1,pessoaService.deletar("03227403129"));
		
		
	}
	//Testa se serviço de deletar vazio é realizado com sucesso
	@Test
void deletarVazioTest() {
		
		when(pessoaRepository.deleteByCpf("444444444444")).thenReturn(0);
		assertEquals(0,pessoaService.deletar("444444444444"));
		
		
	}
	@Test
	void updateTest() {
		Pessoa pessoa = new Pessoa();
		pessoa.setCpf("03227403129");
		pessoa.setNome("marcos");
		when(pessoaRepository.updateByName(pessoa.getNome(),pessoa.getCpf())).thenReturn(1);
		assertEquals(1,pessoaService.alterar(pessoa));
		
	}
	@Test
	void updateVazioTest() {
		Pessoa pessoa = new Pessoa();
		pessoa.setCpf(null);
		pessoa.setNome(null);
		when(pessoaRepository.updateByName(null,null)).thenReturn(0);
		assertEquals(0,pessoaService.alterar(pessoa));
		
	}

}
