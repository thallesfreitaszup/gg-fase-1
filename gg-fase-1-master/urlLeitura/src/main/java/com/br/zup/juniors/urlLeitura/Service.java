package com.br.zup.juniors.urlLeitura;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Service {
	//Metodo que retorna string com conteudo da página 
	public static String leDadosUrl(){
	
	HttpClient client = HttpClient.newHttpClient();
	HttpRequest request = HttpRequest.newBuilder().
			uri(URI.create("https://www.reddit.com/r/programming/")).build();
	 HttpResponse<String> response = null;
	try {
		response = client.send(request, BodyHandlers.ofString());
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 return response.body();	}
	//Método que itera sobre conteúdo da url e filtra os 20 itens que representam
	//titulos dos artigos do reddit
	public static HashSet<String> pegaTitulosItens(List<String> items) {
		// TODO Auto-generated method stub
		int contador = 0;
		HashSet<String> titulosArtigos = new HashSet<String>();
		for (String item : items) {
			
			if(titulosArtigos.size() < 20 && item.contains("title") && items.get(contador+1).contains("event") ) {
				item = item.substring(item.indexOf(':')+2,item.length()-1);
				titulosArtigos.add(item);

				
			}
			contador++;
		}
		return titulosArtigos;
	}
	//Metodo que ordena titulos dos artigos do reddit através do algoritmo insertion sort
	public static List<String> ordenaTitulos(List<String> titulosArtigos) {  
		    int i,j;
		    String key;   
		    for (i = 1; i < titulosArtigos.size(); i++) 
		    {  
		        key = titulosArtigos.get(i);  
		        j = i - 1;  
		  
		        /* Move elements of arr[0..i-1], that are  
		        greater than key, to one position ahead  
		        of their current position */
		        while (j >= 0 && titulosArtigos.get(j).compareTo(key)> 0 ) 
		        {  
		        	
		            titulosArtigos.set(j + 1,titulosArtigos.get(j));  
		            j = j - 1;  
		        }  
		        titulosArtigos.set(j + 1,key);  
		    }  
		    return titulosArtigos;
		}  	
	}
