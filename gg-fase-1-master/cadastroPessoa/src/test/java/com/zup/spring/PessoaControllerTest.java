package com.zup.spring;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.zup.spring.controller.PessoaController;
import com.zup.spring.model.Pessoa;
import com.zup.spring.service.PessoaService;

class PessoaControllerTest {
	PessoaController pessoaController; 
	PessoaService pessoaService;
	@BeforeEach
	public void setup() {
		pessoaController = new PessoaController();
		pessoaService = new PessoaService();
		pessoaService = Mockito.mock(PessoaService.class);
		pessoaController.setPessoaService(pessoaService);
	}
	@Test
	void testCriar() {
		Pessoa pessoa = new Pessoa();
		pessoa.setCpf("03227403129");
		pessoa.setNome("thalles");
		when(pessoaService.salvar(pessoa)).thenReturn(pessoa);
		assertEquals(new ResponseEntity<String>("Pessoa: "+pessoa,HttpStatus.CREATED),pessoaController.criar(pessoa));
	}
	@Test
	void testCriarVazio() {
		Pessoa pessoa = null;
		when(pessoaService.salvar(pessoa)).thenReturn(pessoa);
		assertEquals(new ResponseEntity<String>("Pessoa não criada",HttpStatus.BAD_REQUEST),pessoaController.criar(pessoa));
	}

	@Test
	void testProcurar() {
		Pessoa pessoa = new Pessoa();
		pessoa.setCpf("03227403129");
		pessoa.setNome("thalles");
		when(pessoaService.buscar("03227403129")).thenReturn(pessoa);
		assertEquals(new ResponseEntity<String>("Pessoa: "+pessoa,HttpStatus.OK),pessoaController.procurar("03227403129"));
	}
	@Test
	void testProcurarVazio() {
		
		when(pessoaService.buscar("444444444")).thenReturn(null);
		assertEquals(new ResponseEntity<String>("Pessoa não encontrada",HttpStatus.BAD_REQUEST),pessoaController.procurar("444444444"));
	}

	@Test
	void testAtualizar() {
		Pessoa pessoa = new Pessoa();
		pessoa.setCpf("03227403129");
		pessoa.setNome("marcos");
		when(pessoaService.alterar(pessoa)).thenReturn(1);
		assertEquals(new ResponseEntity<String>("Alterado com sucesso",HttpStatus.OK),pessoaController.atualizar(pessoa));
	}
	@Test
	void testAtualizarVazio() {
		Pessoa pessoa = new Pessoa();
		pessoa.setCpf(null);
		pessoa.setNome(null);
		when(pessoaService.alterar(pessoa)).thenReturn(0);
		assertEquals(new ResponseEntity<String>("Alterado sem sucesso",HttpStatus.BAD_REQUEST),pessoaController.atualizar(pessoa));
	}

	@Test
	void testDeletar() {
	
		when(pessoaService.deletar("03227403129")).thenReturn(1);
		assertEquals(new ResponseEntity<String>("Deletado com sucesso",HttpStatus.OK),pessoaController.deletar("03227403129"));
	}
	@Test
	void testDeletarVazio() {
	
		when(pessoaService.deletar(null)).thenReturn(0);
		assertEquals(new ResponseEntity<String>("Usuário não deletado",HttpStatus.BAD_REQUEST),pessoaController.deletar("03227403129"));
	}
}
