package br.com.arteescrita.modelos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public record Autor(
		
		@JsonProperty("name")
		String nome,
		
		@JsonProperty("birth_year")
		Integer anoDeNascimento,
		
		@JsonProperty("death_year")
		Integer anoDeFalecimento) {
	
	
}
