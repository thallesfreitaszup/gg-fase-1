package br.com.zup.juniors.lerArquivo;

import java.io.File;

public class Pasta {

	
	public static void lerArquivo(File arquivo) {
		
		// TODO Auto-generated method stub
				
			for(File arquivoLeitura: arquivo.listFiles()) { //Pega lista de arquivos
				if(arquivoLeitura.isDirectory()) { // verifica se arquivo é diretorio 
					lerArquivo(arquivoLeitura); // chama diretório recursivamente
				}else {
					System.out.println(arquivoLeitura.getName());  
				}
			}
		
	}

}
