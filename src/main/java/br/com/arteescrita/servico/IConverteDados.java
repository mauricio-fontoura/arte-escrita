package br.com.arteescrita.servico;

import java.util.List;



public interface IConverteDados {
	<T> T ObterDados (String json, Class<T> classe);
	<T> List<T> ObterLista (String json, Class<T> classe,String no);

}
