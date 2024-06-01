package br.com.arteescrita.modelos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.OneToMany;

@JsonIgnoreProperties(ignoreUnknown=true)
public record Livros(
		
		@JsonProperty("title")
		String titulo,
		
		@JsonProperty("authors")
		List<Autor> autor,
		
		@JsonProperty("languages")
		List<String> traducao,
		
		@JsonProperty("download_count")
		Integer baixados
		
		
) {

}
