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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Service {

	public static void enviaPost() {
		// TODO Auto-generated method stub
    	System.out.println("Requisição post");
    	Map<String,Object> mapDados = new HashMap<String,Object>();
    	mapDados.put("id",123);
    	mapDados.put("field","testing");
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = null;
		try {
			requestBody = objectMapper
			      .writerWithDefaultPrettyPrinter()
			      .writeValueAsString(mapDados);
		} catch (JsonProcessingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		

        HttpClient client = HttpClient.newHttpClient();
        try {
			HttpRequest request = HttpRequest.newBuilder()
					.uri(URI.create("http://httpbin.org/post")).
					timeout(Duration.ofMinutes(1))
					.header("Content-Type","Application/json").
					POST(BodyPublishers.ofString(requestBody)).build();
					
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
        try {
			HttpRequest request = HttpRequest.newBuilder()
					.uri(URI.create("http://httpbin.org/delete")).
					timeout(Duration.ofMinutes(1))
					.header("Content-Type","Application/json").
					DELETE().build();
					
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
	public static void enviaBearer() {
		System.out.println("Requisição bearer");
		// TODO Auto-generated method stub
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.
				newBuilder().uri(URI.create("http://httpbin.org/bearer"))
				.timeout(Duration.ofMinutes(1))
				.GET()
				.header("Content-Type","application/json")
				.header("Authorization","Bearer "+"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c")
				.build();
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
