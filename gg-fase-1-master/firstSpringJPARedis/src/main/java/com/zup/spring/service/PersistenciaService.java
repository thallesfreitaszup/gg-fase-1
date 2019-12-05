package com.zup.spring.service;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.zup.spring.model.Dados;
import com.zup.spring.model.DadosRepositorio;
@Service
public class PersistenciaService {
	@Autowired
	private DadosRepositorio dadosRepositorio;
	private RedisTemplate<String,Object> template;
	private HashOperations<String, String, Dados> hashOperations;
	@Autowired
	public PersistenciaService(RedisTemplate<String,Object> template) {
		this.template = template;
		this.hashOperations = template.opsForHash();
	}
	public PersistenciaService() {
		
	}
	public DadosRepositorio getDadosRepositorio() {
		return dadosRepositorio;
	}
	
	public void setDadosRepositorio(DadosRepositorio dadosRepositorio) {
		this.dadosRepositorio = dadosRepositorio;
	}
	
	public Dados  escreverArquivo(String args) {
		String []chaveValor = args.split(":");
		Dados dados = new Dados(chaveValor[1],chaveValor[0]);
		
		  hashOperations.put("Dados",dados.getChave(),dados);
		  return this.dadosRepositorio.save(dados);
	}
	
	public Dados procurarArquivo(String string) {
		Dados dados;
		String []chaveValor = string.split(":");
		if(( dados = hashOperations.get("Dados",chaveValor[0])) != null) {
		 return dados;
		}
		return dadosRepositorio.findByChave(chaveValor[0]);
	}
	public Long deletarItemArquivo(String string) {
		
		long resultado =  this.dadosRepositorio.deleteByChave(string);
		return resultado;
	}
	public List<Dados> listarArquivo() {
		List<Dados> listadados = this.dadosRepositorio.findAll();
		if(listadados.isEmpty()) {
			System.out.println("Lista vazia");
		}
		 for (Dados dados :listadados) {
			 System.out.println(dados);
			
		}
		 return listadados;
	}
}
