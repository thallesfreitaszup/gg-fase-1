package com.zup.spring;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.zup.spring.controller.PessoaController;
import com.zup.spring.model.Pessoa;
import com.zup.spring.service.PessoaService;

class PessoaControllerTest {
	@InjectMocks
	PessoaController pessoaController; 
	@Mock
	PessoaService pessoaService;
	//Para cada teste estabelecer um contexto anterior ao teste
	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
		
		
	}
	//Testa se pessoa é criada e se código http de criação é retornado
	@Test
	void testCriar() {
		Pessoa pessoa = new Pessoa();
		pessoa.setCpf("03227403129");
		pessoa.setNome("thalles");
		when(pessoaService.salvar(pessoa)).thenReturn(pessoa);
		assertEquals(new ResponseEntity<String>("Pessoa: "+pessoa,HttpStatus.CREATED),pessoaController.criar(pessoa));
	}
	//Testa quando pessoa não é criada e se código de erro é retornado
	@Test
	void testCriarVazio() {
		Pessoa pessoa = null;
		when(pessoaService.salvar(pessoa)).thenReturn(pessoa);
		assertEquals(new ResponseEntity<String>("Pessoa não criada",HttpStatus.BAD_REQUEST),pessoaController.criar(pessoa));
	}
	//Testa se pessoa é buscada no banco de dados e se retorna status http de requisição bem sucedida
	@Test
	void testProcurar() {
		Pessoa pessoa = new Pessoa();
		pessoa.setCpf("03227403129");
		pessoa.setNome("thalles");
		when(pessoaService.buscar("03227403129")).thenReturn(pessoa);
		assertEquals(new ResponseEntity<String>("Pessoa: "+pessoa,HttpStatus.OK),pessoaController.procurar("03227403129"));
	}
	//Testa quando pessoa não é encontrada no banco e se retorna status de requisição mal sucedida
	@Test
	void testProcurarVazio() {
		
		when(pessoaService.buscar("444444444")).thenReturn(null);
		assertEquals(new ResponseEntity<String>("Pessoa não encontrada",HttpStatus.BAD_REQUEST),pessoaController.procurar("444444444"));
	}
	//Testa se usuário é alterado e se retorna status de requisição bem sucedida
	@Test
	void testAtualizar() {
		Pessoa pessoa = new Pessoa();
		pessoa.setCpf("03227403129");
		pessoa.setNome("marcos");
		when(pessoaService.alterar(pessoa)).thenReturn(1);
		assertEquals(new ResponseEntity<String>("Alterado com sucesso",HttpStatus.OK),pessoaController.atualizar(pessoa));
	}
	//Testa quando atualizar não é bem sucedido e status de requisição mal sucedida
	@Test
	void testAtualizarVazio() {
		Pessoa pessoa = new Pessoa();
		pessoa.setCpf(null);
		pessoa.setNome(null);
		when(pessoaService.alterar(pessoa)).thenReturn(0);
		assertEquals(new ResponseEntity<String>("Alterado sem sucesso",HttpStatus.BAD_REQUEST),pessoaController.atualizar(pessoa));
	}
	//Testa quando deletar é bem sucedido e retorna status de requisição bem sucedida
	@Test
	void testDeletar() {
	
		when(pessoaService.deletar("03227403129")).thenReturn(1);
		assertEquals(new ResponseEntity<String>("Deletado com sucesso",HttpStatus.OK),pessoaController.deletar("03227403129"));
	}
	@Test
	//Testa quando deletar não é bem sucedido e se retorna status de requisição mal sucedida
	void testDeletarVazio() {
		when(pessoaService.deletar(null)).thenReturn(0);
		assertEquals(new ResponseEntity<String>("Usuário não deletado",HttpStatus.BAD_REQUEST),pessoaController.deletar("03227403129"));
	}
}
