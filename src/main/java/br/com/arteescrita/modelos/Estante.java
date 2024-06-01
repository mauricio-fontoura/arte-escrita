package br.com.arteescrita.modelos;





import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;



@JsonIgnoreProperties(ignoreUnknown=true)
@Entity
@Table(name="biblioteca_estantes")
public class Estante {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String titulo;
	
	private String autor;

	private Integer anoDeNascimento;

	private Integer anoDeFalecimento;

	private String traducao;

	private Integer baixados;
	
	
	public Estante() {
		
	}
	
	public Estante(Livros dados) {
		this.titulo = dados.titulo();
		this.autor = dados.autor().get(0).nome();
		this.anoDeNascimento = dados.autor().get(0).anoDeNascimento();
		this.anoDeFalecimento = dados.autor().get(0).anoDeFalecimento();
		this.traducao = dados.traducao().get(0);
		this.baixados = dados.baixados();
	}
	
	public void EstanteAutor() {
		this.autor = getAutor();
		this.anoDeNascimento = getAnoDeNascimento();
		this.anoDeFalecimento = getAnoDeFalecimento();
		this.titulo = getTitulo();
	}
	
	
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public Integer getAnoDeNascimento() {
		return anoDeNascimento;
	}

	public void setAnoDeNascimento(Integer anoDeNascimento) {
		this.anoDeNascimento = anoDeNascimento;
	}

	public Integer getAnoDeFalecimento() {
		return anoDeFalecimento;
	}

	public void setAnoDeFalecimento(Integer anoDeFalecimento) {
		this.anoDeFalecimento = anoDeFalecimento;
	}

	public String getTraducao() {
		return traducao;
	}

	public void setTraducao(String traducao) {
		this.traducao = traducao;
	}

	public Integer getBaixados() {
		return baixados;
	}

	public void setBaixados(Integer baixados) {
		this.baixados = baixados;
	}
	
	@Override
	public String toString() {
		return "Estante [ id=" + id + ", titulo=" + titulo + ", autor=" + autor
				+ ", anoDeNascimento=" + anoDeNascimento + ", anoDeFalecimento=" + anoDeFalecimento + ", traducao="
				+ traducao + ", baixados=" + baixados + "]";
	}

	
	
	
}
