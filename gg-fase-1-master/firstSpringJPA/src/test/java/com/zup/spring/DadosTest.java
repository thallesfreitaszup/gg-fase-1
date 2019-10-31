package com.zup.spring;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import com.zup.spring.model.Dados;
@ExtendWith(SpringExtension.class)
public class DadosTest {
	Dados dados;
	@BeforeEach
	public void setup() {
		dados = new Dados();
		dados.setChave("chave");
		dados.setValor("123");
	}
	@Test
	public void testToString() {
		Assertions.assertEquals("",dados.toString());
		
	}
}
