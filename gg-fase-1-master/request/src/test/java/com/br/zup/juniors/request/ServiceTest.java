package com.br.zup.juniors.request;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;

import org.junit.Test;

public class ServiceTest {

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

	@Test
	public void testTransformaMapString() {
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
		HttpResponse<String> response = client.send(request,BodyHandlers.ofString());
		assertEquals(response.body(),Service.enviaRequest(request).body());
	}

	@Test
	public void testEnviaDelete() {
		HttpRequest request = null;
		request = HttpRequest.newBuilder()
				.uri(URI.create("http://httpbin.org/delete")).
				timeout(Duration.ofMinutes(1))
				.header("Content-Type","Application/json").
				DELETE().build();
		assertEquals(Service.enviaRequest(request).body(),Service.enviaDelete());
	}

	@Test
	public void testEnviaBearer() {
		HttpRequest request = null;
		request = HttpRequest.newBuilder()
				.uri(URI.create("http://httpbin.org/bearer")).
				timeout(Duration.ofMinutes(1))
				.header("Content-Type","Application/json").
				header("Authorization","Bearer "+"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c")
				.build();
		assertEquals(Service.enviaRequest(request).body(),Service.enviaBearer());
		
	}

}
