package com.br.zup.juniors.urlLeitura;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

/**
 * Unit test for simple App.
 */
public class ServiceTest 
    
{
	List<String> lista = null;
	@BeforeEach
	public void setup() throws IOException, InterruptedException {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().
		uri(URI.create("https://www.reddit.com/r/programming/")).build();
		 lista = Arrays.asList(client.send(request,BodyHandlers.ofString()).body().split(","));
		 
		 
	}
   @Test
   public void pegaTitulosItensTest(){
	   assertEquals("",Service.pegaTitulosItens(lista));
   }
}
