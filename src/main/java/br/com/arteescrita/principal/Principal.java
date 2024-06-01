package br.com.arteescrita.principal;

import java.util.ArrayList;
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
			case 2: {ObterLivrosRegistrados();
			}
				break;
			case 3: {ListarAutoresRegistrados();}
				break;
			case 4: {ListarAutoresVivos();}
				break;
			case 5: {ListarLivrosTraducao();}
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
	public Livros ObterDadosLivro() {

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
		Livros dados = ObterDadosLivro();
		Estante livro = new Estante(dados);
		repositorio.save(livro);
	}
	
	private void ObterLivrosRegistrados() {
		List<Estante> estante = new ArrayList<>();
		estante = repositorio.findAll();
		
		for (Integer i = 0; i < repositorio.count(); i++) {
			String titulo = estante.get(i).getTitulo();
			String autor = estante.get(i).getAutor();
			String idioma = estante.get(i).getTraducao();
			Integer baixados = estante.get(i).getAnoDeFalecimento();
			
			System.out.println("------------------ LIVRO ----------------");
			System.out.println("Titulo: " + titulo);
			System.out.println("Autor: " + autor);
			System.out.println("Idioma: " + idioma);
			System.out.println("Número de downloads: " + baixados);
			System.out.println("-----------------------------------------\n");
		}
		
	
	}

	private void ListarAutoresRegistrados() {
		List<Estante> estante = new ArrayList<>();
		estante = repositorio.findAll();
		
		for (Integer i = 0; i < repositorio.count(); i++) {
			List<String> obras = new ArrayList<>();
			String livros = estante.get(i).getTitulo();
			Integer anoDeNascimento = estante.get(i).getAnoDeNascimento();
			Integer anoDeFalecimento = estante.get(i).getAnoDeFalecimento();
			String autor = estante.get(i).getAutor();
			
			obras.add(livros);
			System.out.println("--------------- ESCRITOR ---------------");
			System.out.println("Autor: " + autor);
			System.out.println("Ano de Nascimento: " + anoDeNascimento);
			System.out.println("Ano de Falecimento: " + anoDeFalecimento);
			System.out.println("Livros: " + obras);
			System.out.println("----------------------------------------\n");
		}
		
	}

	private void ListarAutoresVivos() {
		List<Estante> estante = new ArrayList<>();
		estante = repositorio.findByAutor("Machado de Assis");
		System.out.println(estante);
	}

	private void ListarLivrosTraducao() {
		Scanner leitura = new Scanner(System.in);
		List<Estante> pt = new ArrayList<>();
		System.out.println("""
				Insira um idioma para realizar a busca
				pt - Português
				en - Inglês
				fr - Francês
				es - Espanhol
				""");
		String opcao = leitura.nextLine();
		
		pt = repositorio.findByTraducaoContainingIgnoreCase(opcao);
		
		if (pt.isEmpty()) {
			System.out.println("Deu ruim");
		} else {
			System.out.println(pt);
		}
		
		
	}

}
