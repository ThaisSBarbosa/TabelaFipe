package br.com.thais.TabelaFipe.services;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

public class ConverteDados implements IConverteDados{
	private ObjectMapper mapper = new ObjectMapper();

	@Override
	public <T> T obterDados(String json, Class<T> classe) {
		try {
			return mapper.readValue(json, classe);
		} catch (JsonProcessingException e) {
			throw new RuntimeException();
		}
	}

	@Override
	public <T> List<T> obterLista(String json, Class<T> classe) {
		try {
			CollectionType lista = mapper.getTypeFactory()
					.constructCollectionType(List.class, classe);
			return mapper.readValue(json, lista);
		} catch (JsonProcessingException e) {
			throw new RuntimeException();
		}
	}	
}
