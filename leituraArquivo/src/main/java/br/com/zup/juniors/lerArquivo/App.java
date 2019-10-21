package br.com.zup.juniors.lerArquivo;

import java.io.File;
import java.net.URISyntaxException;



/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws URISyntaxException
    {
    	System.out.println("Crie os arquivos na pasta:" +System.getProperty("user.home")+"\\arquivos");
    	File arquivo = new File(System.getProperty("user.home")+"/arquivos");
    	Pasta.lerArquivo(arquivo);
		
    }
}
