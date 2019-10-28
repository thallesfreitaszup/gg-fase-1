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
		Dados dados = new Dados(args.split(":")[1]);
		
		
		return dadosRepositorio.save(dados);
	}
	public String procurarArquivo(String string) {
		
		return dadosRepositorio.findByValor(string).getValor();
	}
	public int deletarItemArquivo(String string) {
		Dados dados = new Dados(string);
		this.dadosRepositorio.delete(dados);
		return this.dadosRepositorio.findAll().size();
	}
	public List<Dados> listarArquivo() {
		List<Dados> Listadados = this.dadosRepositorio.findAll();
		
		 for (Dados dados :Listadados) {
			 System.out.println(dados);
			
		}
		 return Listadados;
	}
}
