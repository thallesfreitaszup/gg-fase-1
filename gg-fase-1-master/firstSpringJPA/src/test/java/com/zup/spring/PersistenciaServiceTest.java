package com.zup.spring;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.hamcrest.beans.SamePropertyValuesAs;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
	private PersistenciaService persistenciaService;
	
	private Dados dados;

	List<Dados> listaDados = new ArrayList<Dados>();
	private DadosRepositorio dadosRepositorio;
	@BeforeEach
	public void setup(){
		this.persistenciaService = Mockito.mock(PersistenciaService.class);
		dados = new Dados();
		dados.setId(1);
		dados.setValor("125");
		listaDados.add(dados);
	}
	
	@Test
	public void testPersistencia(){
		when(this.persistenciaService.escreverArquivo("set chave:125")).thenReturn(dados);
		assertEquals(dados,this.persistenciaService.escreverArquivo("set chave:125"));
		
	}
	
	@Test
	public void testeLista() {
		when( this.persistenciaService.listarArquivo()).thenReturn(listaDados);
		assertEquals(listaDados,this.persistenciaService.listarArquivo());
		
	}
	@Test
	public void testGet(){
		when(this.persistenciaService.procurarArquivo("chave")).thenReturn("125");
		assertEquals("125",this.persistenciaService.procurarArquivo("chave"));
		
	}
	@Test
	public void testDelete(){
		when(this.persistenciaService.deletarItemArquivo("chave")).thenReturn(0);
		assertEquals(0,this.persistenciaService.deletarItemArquivo("chave"));
		
	}
	
	
	
}
