package com.zup.spring;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.zup.spring.service.FileService;


@SpringBootApplication
public class FirstSpringApplication implements CommandLineRunner {
	
	
	public static void main(String[] args) {
		SpringApplication.run(FirstSpringApplication.class, args);
	}
	@Bean
	public FileService getFileService(){
		
		return new FileService();
	}
	@Override
	public void run (String[] args) {
		String resultado;
		if(args.length != 0) {
			
			if(args[0].equals("set")) {
				getFileService().escreverArquivo(args[1]);
			}else if(args[0].equals("get")){
				if(( resultado = getFileService().procurarArquivo(args[1])) != null) {
					System.out.println(resultado);
				}else {
					System.out.println("Não encontrado");
				}
			}else if(args[0].equals("delete")) {
				getFileService().deletarItemArquivo(args[1]);
			}else if(args[0].equals("list")) {
				getFileService().listarArquivo();
			}else {
				System.out.println("Argumento inválido");
			}
			
		}else {
			
			System.out.println("Não foram passados argumentos");
		}
		
	}

}
