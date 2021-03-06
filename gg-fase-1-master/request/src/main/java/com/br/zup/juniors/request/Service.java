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
	public static String enviaPost() {
		// TODO Auto-generated method stub
		HttpRequest request = null;
		System.out.println("Requisição post");
		StringBuilder mapAsString = Service.transformaMapString();
		request = 	Service.criaRequest("http://httpbin.org/post","POST",mapAsString);
		HttpResponse<String> response = null;
		response = Service.enviaRequest(request);
		return response.body();
	}
	public static  HttpResponse<String> enviaRequest(HttpRequest request) {
		HttpResponse<String> response = null;
		HttpClient client = HttpClient.newHttpClient();
		try {

			response = client.send(request,BodyHandlers.ofString());
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
		return response;
	}
	public static String enviaDelete() {
		// TODO Auto-generated method stub

		
		System.out.println("Requisição delete");
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = Service.criaRequest("http://httpbin.org/delete","DELETE",null);

		HttpResponse<String> response = null;
		response = Service.enviaRequest(request);
		return response.body();
	}
	public static String enviaBearer() {
		System.out.println("Requisição bearer");
		// ODO Auto-generated method stub
		
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = Service.criaRequest("http://httpbin.org/bearer","BEARER",null);
		HttpResponse<String> response = Service.enviaRequest(request);
			return response.body();

	}
}