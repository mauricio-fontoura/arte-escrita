package br.com.arteescrita.principal;

import java.util.List;
import java.util.Scanner;

import br.com.arteescrita.modelos.Autor;
import br.com.arteescrita.modelos.Estante;
import br.com.arteescrita.modelos.Livros;
import br.com.arteescrita.repositorio.LivrosRepositorio;
import br.com.arteescrita.servico.ConsumoAPI;
import br.com.arteescrita.servico.ConverteDados;

public class Principal {
	Scanner leitura = new Scanner(System.in);
	ConsumoAPI consumo = new ConsumoAPI();
	ConverteDados converte = new ConverteDados();
	private final String URL_BASE = "https://gutendex.com/books/";
	private LivrosRepositorio repositorio;

	public Principal(LivrosRepositorio repositorio) {
		this.repositorio = repositorio;
	}

	public void ExibirMenu() {
		Integer opcao = -1;

		while (opcao != 0) {
			MostrarOpcao();
			opcao = leitura.nextInt();

			switch (opcao) {
			case 1: {
				BuscarLivrosWeb();
			}
				break;
			case 2: {
			}
				break;
			case 3: {
			}
				break;
			case 4: {
			}
				break;
			case 5: {
			}
				break;
			}

		}

	}

	public void MostrarOpcao() {
		System.out.println("""
				\n
				Escolha o número de sua opcão:
				1- buscar livro pelo título
				2- listar livros registrados
				3- listar autores registrados
				4- listar autores vivos em um determinado ano
				5- listar livros em um determinado idioma
				¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯¯
				0 - sair

				""");
	}

	// Consumo de API
	public Livros getDadosLivro() {

		Scanner escrever = new Scanner(System.in);
		System.out.println("\nInsira um livro de sua escolha");
		String json = consumo.ObterDados(URL_BASE + "?search=" + escrever.nextLine().replace(" ", "%20"));

		List<Livros> info = converte.ObterLista(json, Livros.class, "results");

		System.out.println("Titulo: " + info.get(0).titulo());
		System.out.println("Autor: " + info.get(0).autor().get(0).nome());
		 System.out.println("Tradução : " + info.get(0).traducao().toString().substring(1,3));
		System.out.println("Número de downloads: " + info.get(0).baixados());

		return info.get(0);
	}

	private void BuscarLivrosWeb() {
		Livros dados = getDadosLivro();
		Estante livro = new Estante(dados);
		repositorio.save(livro);
	}

}
