package br.com.arteescrita.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.arteescrita.modelos.Livros;

public interface Repositorio extends JpaRepository<Livros,Long>{
	
}
