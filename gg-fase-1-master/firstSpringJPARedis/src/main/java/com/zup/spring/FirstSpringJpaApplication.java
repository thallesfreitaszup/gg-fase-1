package com.zup.spring;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import com.zup.spring.model.Dados;
import com.zup.spring.service.PersistenciaService;

@SpringBootApplication
public class FirstSpringJpaApplication implements CommandLineRunner {
	@Autowired
	PersistenciaService persistenciaService;
	public static void main(String[] args) {
		SpringApplication.run(FirstSpringJpaApplication.class, args);
	}
	@Bean
	JedisConnectionFactory jedisConnectionFactory() {
	    JedisConnectionFactory jedisConFactory
	      = new JedisConnectionFactory();
	    
	    return jedisConFactory;
	}
	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
	    RedisTemplate<String, Object> template = new RedisTemplate<>();
	    template.setConnectionFactory(jedisConnectionFactory());
	    return template;
	}
	@Override
	@Transactional
	public void run(String... args) throws Exception {
		Dados resultado;
		
			if(args.length != 0) {

				if(args[0].equals("set")) {
					System.out.println(persistenciaService.escreverArquivo(args[1]));
				}else if(args[0].equals("get")){
					if(( resultado = persistenciaService.procurarArquivo(args[1])) != null) {
						System.out.println(resultado.getValor());
						
					}else {
						System.out.println("Não encontrado");
					}
				}else if(args[0].equals("delete")) {
					persistenciaService.deletarItemArquivo(args[1]);
				}else if(args[0].equals("list")) {
					persistenciaService.listarArquivo();
				}else {
					System.out.println("Argumento inválido");
				}

			}else {

				System.out.println("Não foram passados argumentos");
			}
			
	}



}
