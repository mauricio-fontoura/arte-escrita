package br.com.arteescrita.servico;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.type.CollectionType;




public class ConverteDados implements IConverteDados {
	
	ObjectMapper mapper = new ObjectMapper();

	@Override
	public <T> T ObterDados(String json, Class<T> classe) {
		
		try {
			return mapper.readValue(json, classe);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}


	@Override
	public <T> List<T> ObterLista(String json, Class<T> classe, String no) {
		CollectionType list = mapper.getTypeFactory()
				.constructCollectionType(List.class, classe);
		
		try {
			JsonNode raiz = mapper.readTree(json);
			JsonNode ramoRaiz = raiz.findValue(no);
			return mapper.readValue(ramoRaiz.toPrettyString(), list);
			
		}catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}
	

	
	

	public String ObterNo(String json, String no) {
		try {
			JsonNode raiz = mapper.readTree(json);
			JsonNode pegarRamo = raiz.findValue(no);
			
			return  pegarRamo.toString();
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}


	
	
}
