package com.br.zup.juniors.request;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;


public class Service {

	public static  HttpRequest criaRequest(String url,String tipo,StringBuilder dados) {
		HttpRequest request = null;
		if(tipo.equals("POST")) {

			request = HttpRequest.newBuilder()
					.uri(URI.create(url)).
					timeout(Duration.ofMinutes(1))
					.header("Content-Type","Application/json").
					POST(BodyPublishers.ofString(dados.toString())).build();
		}
		if(tipo.equals("DELETE")) {
			request = HttpRequest.newBuilder()
					.uri(URI.create(url)).
					timeout(Duration.ofMinutes(1))
					.header("Content-Type","Application/json").
					DELETE().build();

		}
		if(tipo.equals("BEARER")) {
			request = HttpRequest.
					newBuilder().uri(URI.create(url))
					.timeout(Duration.ofMinutes(1))
					.GET()
					.header("Content-Type","application/json")
					.header("Authorization","Bearer "+"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c")
					.build();
		}
		return request;
	}
	public static StringBuilder transformaMapString() {
		Map<String,Object> mapDados = new HashMap<String,Object>();
		mapDados.put("id",123);
		mapDados.put("field","testing");
		StringBuilder mapAsString = new StringBuilder("{");
		for (String key : mapDados.keySet()) {
			mapAsString.append(key + ":" + mapDados.get(key) + ",");
		}
		mapAsString.delete(mapAsString.length()-1, mapAsString.length()).append("}");
		return mapAsString;
	}
	public static void enviaPost() {
		// TODO Auto-generated method stub
		HttpRequest request = null;
		System.out.println("Requisição post");
		StringBuilder mapAsString = Service.transformaMapString();
		request = 	Service.criaRequest("http://httpbin.org/post","POST",mapAsString);
		HttpClient client = HttpClient.newHttpClient();
		try {

			HttpResponse<String> response = client.send(request,BodyHandlers.ofString());
			System.out.println(response.body());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void enviaDelete() {
		// TODO Auto-generated method stub


		System.out.println("Requisição delete");
		HttpClient client = HttpClient.newHttpClient();
			HttpRequest request = Service.criaRequest("http://httpbin.org/delete","DELETE",null);

			HttpResponse<String> response = null;
			try {
				response = client.send(request,BodyHandlers.ofString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(response.body());
	}
	public static void enviaBearer() {
		System.out.println("Requisição bearer");
		// TODO Auto-generated method stub
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = Service.criaRequest("http://httpbin.org/bearer","BEARER",null);
		try {
			HttpResponse<String> response = client.send(request,BodyHandlers.ofString());
			System.out.println("Resposta: "+response.body());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}