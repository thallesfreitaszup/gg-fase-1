package com.zup.spring;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.zup.spring.model.Dados;

class DadosTest {
	Dados dados;
	Dados dados1;
	@BeforeEach
	public void setup(){
		dados = new Dados();
		dados.setChave("chave");
		dados.setValor("123");
		dados1 = new Dados();
		dados1.setChave("chave");
		dados1.setValor("123");
	}
	
	@Test
	void testToString() {
		assertEquals("Dados [id=0, chave=chave, valor=123]",dados.toString());
	}
	@Test
	void testEquals() {
		assertTrue(dados.equals(dados1));
	}

}
