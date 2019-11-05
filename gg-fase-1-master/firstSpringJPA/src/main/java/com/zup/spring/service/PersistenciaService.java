package com.zup.spring.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zup.spring.model.Dados;
import com.zup.spring.model.DadosRepositorio;
@Service
public class PersistenciaService {
	@Autowired
	private DadosRepositorio dadosRepositorio;
	public DadosRepositorio getDadosRepositorio() {
		return dadosRepositorio;
	}
	public void setDadosRepositorio(DadosRepositorio dadosRepositorio) {
		this.dadosRepositorio = dadosRepositorio;
	}
	public Dados  escreverArquivo(String args) {
		String []chaveValor = args.split(":");
		Dados dados = new Dados(chaveValor[1],chaveValor[0]);
		
		return dadosRepositorio.save(dados);
	}
	public Dados procurarArquivo(String string) {
		String []chaveValor = string.split(":");
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
