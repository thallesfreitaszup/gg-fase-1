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
		Dados dados = new Dados(chaveValor[1]);
		dados.setChave(chaveValor[0]);
		return dadosRepositorio.save(dados);
	}
	public Dados procurarArquivo(String string) {
		String []chaveValor = string.split(":");
		return dadosRepositorio.findByChave(chaveValor[0]);
	}
	public void deletarItemArquivo(String string) {
		Dados dados = dadosRepositorio.findByChave(string);
		this.dadosRepositorio.delete(dados);
		
	}
	public List<Dados> listarArquivo() {
		List<Dados> listadados = this.dadosRepositorio.findAll();
		if(listadados.size() == 0) {
			System.out.println("Lista vazia");
		}
		 for (Dados dados :listadados) {
			 System.out.println(dados);
			
		}
		 return listadados;
	}
}
