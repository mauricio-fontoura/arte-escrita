package br.com.arteescrita;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.arteescrita.principal.Principal;
import br.com.arteescrita.repositorio.LivrosRepositorio;



@SpringBootApplication
public class ArteEscritaApplication implements CommandLineRunner{

	
	private LivrosRepositorio repositorio;
	
	public ArteEscritaApplication(LivrosRepositorio repositorio) {
		this.repositorio = repositorio;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ArteEscritaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Principal principal = new Principal(repositorio);
		principal.ExibirMenu();
	}

}
