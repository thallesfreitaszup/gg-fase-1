package com.br.zup.juniors.urlLeitura;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
public class ServiceTest 

{

	
	//Teste que verifica se os 20 itens foram armazenados
	@Test
	public void pegaTitulosItensTest(){
		StringBuilder titulos = new StringBuilder();
		String linha  = null;
		try(BufferedReader bf = new BufferedReader(new FileReader("teste.txt"))){			
			while(( linha = bf.readLine())!= null) {
				titulos.append(linha);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			System.out.println(e1.getMessage());
		}
			
		
		assertEquals("[Using spring boot, How to make a test file]",Service.pegaTitulosItens(Arrays.asList(titulos.toString().split(","))).toString());
	}
	//Teste que verifica se itens foram ordenados
	@Test
	
	public void ordenaTitulosItensTest(){
		List<String> listaString =  new ArrayList<String>(); 
		List<String> listaCompara =  new ArrayList<String>(); 
		listaString.add("Using spring boot");
		listaString.add("How to make a test file");
		listaCompara.add("How to make a test file");
		listaCompara.add("Using spring boot");
		assertEquals(listaCompara,Service.ordenaTitulos(listaString));
	}
	
	
}
