package com.br.zup.juniors.urlLeitura;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
public class ServiceTest 

{
	List<String> lista = null;
	StringBuilder titulos = new StringBuilder();
	String linha  = null;
	List<String> listaString =  new ArrayList<String>(); 
	@BeforeEach
	public void setup() throws IOException, InterruptedException {
		System.out.println("request");
		HttpClient client = HttpClient.newHttpClient();
		BufferedReader bf = new BufferedReader(new FileReader("teste.txt"));
		while(( linha = bf.readLine())!= null) {
			titulos.append(linha);
		}
		listaString.add("Using spring boot");
		listaString.add("How to make a test file");
		
	}
	//Teste que verifica se os 20 itens foram armazenados
	@Test
	public void pegaTitulosItensTest(){
		
		assertEquals("[Using spring boot, How to make a test file]",Service.pegaTitulosItens(Arrays.asList(titulos.toString().split(","))).toString());
	}
	@Test
	public void ordenaTitulosItensTest(){
		Collections.sort(listaString);
		assertEquals(listaString,Service.ordenaTitulos(listaString));
	}
}
