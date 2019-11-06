package com.br.zup.juniors.request;


import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;

import org.junit.jupiter.api.Test;


public class ServiceTest {
	//Teste que verifica se requisição está sendo feita com sucesso para a url.
	@Test
	public void testCriaRequest() {
		HttpRequest request = null;
		request = HttpRequest.newBuilder()
				.uri(URI.create("http://httpbin.org/post")).
				timeout(Duration.ofMinutes(1))
				.header("Content-Type","Application/json").
				POST(BodyPublishers.ofString(Service.transformaMapString().toString())).build();
		assertEquals(request,Service.criaRequest("http://httpbin.org/post","POST",Service.transformaMapString()));
		
	}
	//Teste que verifica se transforma map em uma string com estrutura de json
	@Test
	public void testTransformaMapString() {
		StringBuilder map = new StringBuilder();
		map.append("{field:testing,id:123}");
		assertEquals(map.toString(),Service.transformaMapString().toString());
	}
	
	//Teste que verifica retorno de requisição post 
	@Test
	public void testEnviaPost() {
		String string1 = Service.enviaPost().replaceAll("(\r\n|\n)","");
		String string2 = "{\r\n" + 
				"  \"args\": {}, \r\n" + 
				"  \"data\": \"{field:testing,id:123}\", \r\n" + 
				"  \"files\": {}, \r\n" + 
				"  \"form\": {}, \r\n" + 
				"  \"headers\": {\r\n" + 
				"    \"Content-Length\": \"22\", \r\n" + 
				"    \"Content-Type\": \"Application/json\", \r\n" + 
				"    \"Host\": \"httpbin.org\", \r\n" + 
				"    \"User-Agent\": \"Java-http-client/11.0.5\"\r\n" + 
				"  }, \r\n" + 
				"  \"json\": null, \r\n" + 
				"  \"origin\": \"187.11.122.155, 187.11.122.155\", \r\n" + 
				"  \"url\": \"https://httpbin.org/post\"\r\n" + 
				"}";
				string2 = string2.replaceAll("(\r\n|\n)","");
		assertEquals(string1,string2);
	}
	//Teste que verifica se método envia requisição e se traz resposta de sucesso
	@Test
	public void testEnviaRequest() throws IOException, InterruptedException {
		HttpRequest request = null;
		request = HttpRequest.newBuilder()
				.uri(URI.create("http://httpbin.org/post")).
				timeout(Duration.ofMinutes(1))
				.header("Content-Type","Application/json").
				POST(BodyPublishers.ofString(Service.transformaMapString().toString())).build();
		HttpClient client = HttpClient.newHttpClient();
		String string1 = "{\r\n" + 
				"  \"args\": {}, \r\n" + 
				"  \"data\": \"{field:testing,id:123}\", \r\n" + 
				"  \"files\": {}, \r\n" + 
				"  \"form\": {}, \r\n" + 
				"  \"headers\": {\r\n" + 
				"    \"Content-Length\": \"22\", \r\n" + 
				"    \"Content-Type\": \"Application/json\", \r\n" + 
				"    \"Host\": \"httpbin.org\", \r\n" + 
				"    \"User-Agent\": \"Java-http-client/11.0.5\"\r\n" + 
				"  }, \r\n" + 
				"  \"json\": null, \r\n" + 
				"  \"origin\": \"187.11.122.155, 187.11.122.155\", \r\n" + 
				"  \"url\": \"https://httpbin.org/post\"\r\n" + 
				"}";
		assertEquals(string1.replaceAll("\r\n|\n",""),Service.enviaRequest(request).body().replaceAll("\n\r|\n",""));
	}
	//Teste que verifica se requisição delete é feita com sucesso
	@Test
	public void testEnviaDelete() {
		HttpRequest request  = null;
		String string1 = "{\r\n" + 
				"  \"args\": {}, \r\n" + 
				"  \"data\": \"\", \r\n" + 
				"  \"files\": {}, \r\n" + 
				"  \"form\": {}, \r\n" + 
				"  \"headers\": {\r\n" + 
				"    \"Content-Type\": \"Application/json\", \r\n" + 
				"    \"Host\": \"httpbin.org\", \r\n" + 
				"    \"User-Agent\": \"Java-http-client/11.0.5\"\r\n" + 
				"  }, \r\n" + 
				"  \"json\": null, \r\n" + 
				"  \"origin\": \"187.11.122.155, 187.11.122.155\", \r\n" + 
				"  \"url\": \"https://httpbin.org/delete\"\r\n" + 
				"}\r\n" + 
				"";
				string1 =  string1.replaceAll("(\r\n|\n)","");
		assertEquals(string1,Service.enviaDelete().replaceAll("(\r\n|\n)",""));
	}
	//Teste que verifica se autenticação bearer é feita com sucesso
	@Test
	public void testEnviaBearer() {
		String string1 = "{\r\n" + 
				"  \"authenticated\": true, \r\n" + 
				"  \"token\": \"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c\"\r\n" + 
				"}";
		string1=string1.replaceAll("\r\n|\n","");
		assertEquals(string1,Service.enviaBearer().replaceAll("\r\n|\n",""));
	}

}
