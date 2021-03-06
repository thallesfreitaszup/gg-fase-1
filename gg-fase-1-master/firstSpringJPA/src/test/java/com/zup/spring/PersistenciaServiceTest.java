package com.zup.spring;


import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.rules.ErrorCollector;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;
import com.zup.spring.model.Dados;
import com.zup.spring.model.DadosRepositorio;
import com.zup.spring.service.PersistenciaService;
@RunWith(SpringRunner.class)
public class PersistenciaServiceTest {
	 @Rule		
	    public ErrorCollector collector = new ErrorCollector();	
	private PersistenciaService persistenciaService;
	
	private Dados dados;

	List<Dados> listaDados = new ArrayList<Dados>();
	private DadosRepositorio dadosRepositorio;
	@BeforeEach
	public void setup(){
		this.persistenciaService = new PersistenciaService();
		dadosRepositorio = Mockito.mock(DadosRepositorio.class);
		
		persistenciaService.setDadosRepositorio(dadosRepositorio);
		dados = new Dados();
		dados.setId(1);
		dados.setValor("125");
		dados.setChave("chave");
		listaDados.add(dados);
		
	}
	//Esse teste busca verificar se dados são persistidos no banco de dados a partir da chamada
	//pela service
	
	@Test
	public void testPersistencia(){
		when(dadosRepositorio.save(dados)).thenReturn(dados);
	
		assertEquals(dados,this.persistenciaService.escreverArquivo("chave:valor"));
	}
	
	@Test
	public void testeLista() {
		when(dadosRepositorio.findAll()).thenReturn(listaDados);
		assertEquals(listaDados,this.persistenciaService.listarArquivo());
		
	}
	@Test
	public void testGet(){
		when(dadosRepositorio.findByChave("chave")).thenReturn(dados);
		assertEquals("125",this.persistenciaService.procurarArquivo("chave").getValor());
		
	}
	//Teste que busca deletar elemento pela chave e  mocka objeto repositorio
	// para reproduzir comportamento desejado e deletar item
	@Test
	public void testDelete(){
		when(this.dadosRepositorio.deleteByChave("chave")).thenReturn(1L);
		assertEquals(1,this.persistenciaService.deletarItemArquivo("chave"));
		
	}
	
	
	
}
