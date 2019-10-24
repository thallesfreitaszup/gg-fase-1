package com.zup.spring.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.springframework.boot.ApplicationArguments;

public class FileService {
	

	public void escreverArquivo(String item) {
		// TODO Auto-generated method stub
		String linha = null;
		BufferedWriter buffWriter;
		BufferedReader buffReader;
		StringBuilder conteudoArquivo = new StringBuilder();
		int itemEncontrado = 0;
		String[] arrayItem = item.split(":");
		File arquivo = this.abreArquivo();
		try {
			buffReader = new BufferedReader(new FileReader(arquivo));
			while((linha = buffReader.readLine())!= null) {
				if(linha.contains(arrayItem[0])) {
					linha = item;
					itemEncontrado = 1;
				}
				conteudoArquivo.append(linha+"\r\n");
			}
			buffReader.close();
			if(itemEncontrado == 0) {
				conteudoArquivo.append(item+"\r\n");
			}
			System.out.println(conteudoArquivo);
			buffWriter = new BufferedWriter( new FileWriter(arquivo));
			buffWriter.write(conteudoArquivo.toString());
			buffWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private File abreArquivo() {
		File arquivo = new File("dados.txt");
		if(!arquivo.exists()) {
			try {
				arquivo.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return arquivo;
	}

	public String procurarArquivo(String item) {
		File arquivo = this.abreArquivo();
		String linha = null;
		
		try {
			BufferedReader buffReader = new BufferedReader(new FileReader(arquivo));
			while(( linha = buffReader.readLine()) != null) {
				if(linha.contains(item)) {
					buffReader.close();
					return linha;
				}
			}
			buffReader.close();	
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	public  void deletarItemArquivo(String item) {
		// TODO Auto-generated method stub
		File arquivo = this.abreArquivo();
		String linha = null;
		StringBuilder  conteudoArquivo = new StringBuilder();
		try {
			BufferedReader buffReader = new BufferedReader(new FileReader(arquivo));
			while(( linha = buffReader.readLine()) != null) {
				String []arrayLinha= linha.split(":");
				if(!arrayLinha[0].equals(item)) {
					conteudoArquivo.append(linha+"\r\n");
				}
			}
			buffReader.close();
			BufferedWriter buffWriter = new BufferedWriter( new FileWriter(arquivo));
			buffWriter.write(conteudoArquivo.toString());
			buffWriter.close();
		}catch(IOException e) {
			e.getStackTrace();
		}
	}
	public  void listarArquivo() {
		String linha = null;
		String conteudoArquivo=null;
		// TODO Auto-generated method stub
		File arquivo = this.abreArquivo();
		BufferedReader buffReader;
		try {
			buffReader = new BufferedReader(new FileReader(arquivo));
			while(( linha = buffReader.readLine()) != null) {
					String[] arrKeysValue = linha.split(":");
					System.out.println(arrKeysValue[0]);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
