package com.zup.spring;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import com.zup.spring.model.Dados;
@RunWith(SpringRunner.class)
public class DadosTest {
	Dados dados;
	@BeforeEach
	public void setup() {
		dados = new Dados();
		dados.setChave("chave");
		dados.setValor("123");
	}
	@Test
	public void toStringTest() {
		Assertions.assertEquals("",dados.toString());
		
	}
}
