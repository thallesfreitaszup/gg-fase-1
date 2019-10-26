package com.br.zup.juniors.urlLeitura;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class App 
{
    public static void main( String[] args ) throws IOException, InterruptedException
    {
    	
    	
    	String resposta = Service.leDadosUrl();
    	
    	 List<String> items = Arrays.asList(resposta.split(","));
    	 HashSet<String> titulosArtigos = Service.pegaTitulosItens(items);
    	 List<String> arrayArtigos = new ArrayList<String>(titulosArtigos);
    	 List<String> titulosOrdenados =  Service.ordenaTitulos(arrayArtigos);
    	 System.out.println("Primeiros 20 artigos do reddit:");
			for (String titulo : titulosOrdenados) {
				System.out.println(titulo);
			}
    }

}
