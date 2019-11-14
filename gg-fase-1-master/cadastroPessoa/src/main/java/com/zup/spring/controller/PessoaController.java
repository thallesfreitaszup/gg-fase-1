package com.zup.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zup.spring.model.Pessoa;
import com.zup.spring.service.PessoaService;

@RestController
@RequestMapping("/pessoa")
public class PessoaController {
	@Autowired
	PessoaService pessoaService;
	@PostMapping("")
	//Utilizo requestbody por seu suporte ao formato json e por automaticamente 
	// transformar string json em um objeto java
	//Após receber objeto pessoa ocorre chamada a classe service que 
	// oferece serviço de acesso a banco de dados
	public ResponseEntity<String> criar(@RequestBody Pessoa pessoa){

		Pessoa pessoaBD = pessoaService.salvar(pessoa);
		if(pessoaBD != null)
			return new ResponseEntity<>("Pessoa: "+pessoaBD,HttpStatus.CREATED);
		else
			return new ResponseEntity<>("Pessoa não criada",HttpStatus.BAD_REQUEST);

	}
	//Utilizo PathVariable para receber parametro da url 
	// e faço chamada da Service de acesso ao banco de dados com os dados da url
	@GetMapping("/{cpf}")
	public ResponseEntity<String> procurar(@PathVariable String cpf){

		Pessoa pessoaBD = pessoaService.buscar(cpf);
		if(pessoaBD != null) {

			return new ResponseEntity<>("Pessoa: "+pessoaBD,HttpStatus.OK);
		}else {
			return new ResponseEntity<>("Pessoa não encontrada",HttpStatus.BAD_REQUEST);
		}
	}
	//Utilizo put mapping para a atualização dos dados
	//Dados são enviados pelo body da requisição e transformados em objeto java 
	//pela tag requestbody
	//Após isso,faço chamada da Service para alterar os dados no banco de dados
	@PutMapping("")
	public ResponseEntity<String> atualizar(@RequestBody Pessoa pessoa){
		if(pessoaService.alterar(pessoa) == 1) {
			return  new ResponseEntity<>("Alterado com sucesso",HttpStatus.OK);
		}
		return  new ResponseEntity<>("Alterado sem sucesso",HttpStatus.BAD_REQUEST);
		
	}
	public PessoaService getPessoaService() {
		return pessoaService;
	}
	public void setPessoaService(PessoaService pessoaService) {
		this.pessoaService = pessoaService;
	}
	//Utilizo deleteMapping para mapear a requisição delete
	//Utilizo pathvariable para pegar o parametro da requisição
	//Utilizo a classe Service para deletar o dado no banco de dados
	@DeleteMapping("/{cpf}")
	public ResponseEntity<String> deletar(@PathVariable("cpf") String cpf){
		int deletado = pessoaService.deletar(cpf);
		if(deletado == 1) {
			return new ResponseEntity<>("Deletado com sucesso",HttpStatus.OK);
		}else {
			return new ResponseEntity<>("Usuário não deletado",HttpStatus.BAD_REQUEST);
		}
		
	}
}
