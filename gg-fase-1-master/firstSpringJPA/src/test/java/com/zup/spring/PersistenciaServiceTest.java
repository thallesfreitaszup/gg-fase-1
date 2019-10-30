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
	
	@Test
	public void testPersistencia(){
		Dados dados1= new Dados();
		dados1.setChave("chave");
		dados1.setValor("valor");
		when(dadosRepositorio.save(dados1)).thenReturn(dados);
	
		assertEquals(dados,this.persistenciaService.escreverArquivo("chave:valor"));
		listaDados = this.persistenciaService.listarArquivo();
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
	@Test
	public void testDelete(){
		doNothing().when(this.dadosRepositorio).delete(dados);
		this.persistenciaService.deletarItemArquivo("chave");
		assertEquals(0,this.persistenciaService.listarArquivo().size());
		
	}
	
	
	
}
