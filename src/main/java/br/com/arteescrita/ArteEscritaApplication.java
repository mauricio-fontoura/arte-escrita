package br.com.arteescrita;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.arteescrita.principal.Principal;

@SpringBootApplication
public class ArteEscritaApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(ArteEscritaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Principal principal = new Principal();
		principal.ExibirMenu();
	}

}
