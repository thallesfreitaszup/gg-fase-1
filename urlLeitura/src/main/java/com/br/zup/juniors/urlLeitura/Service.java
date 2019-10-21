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

	public static HashSet<String> pegaTitulosItens(List<String> items) {
		// TODO Auto-generated method stub
		int contador = 0;
		HashSet<String> titulosArtigos = new HashSet<String>();
		for (String item : items) {
			
			if(titulosArtigos.size() < 20 && item.contains("title") && items.get(contador+1).contains("event") ) {
				item = item.substring(item.indexOf(":")+2,item.length()-1);
				titulosArtigos.add(item);

				
			}
			contador++;
		}
		return titulosArtigos;
	}

	public static ArrayList<String> ordenaTitulos(ArrayList<String> titulosArtigos) {
		for (int i = 0;i<titulosArtigos.size();i++) {
			for (int j = i+1;j<titulosArtigos.size();j++) {
				if(titulosArtigos.get(i).toLowerCase().compareTo(titulosArtigos.get(j).toLowerCase())>0) {
					String auxiliar = titulosArtigos.get(i);
					titulosArtigos.set(i,titulosArtigos.get(j));
					titulosArtigos.set(j,auxiliar);
				}
			}
			
		}
		return titulosArtigos;
	}
	}
