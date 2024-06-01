package br.com.arteescrita.repositorio;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.arteescrita.DTO.LivrosDTO;
import br.com.arteescrita.modelos.Estante;




public interface LivrosRepositorio extends JpaRepository<Estante, Long> {

	List<Estante> findByAutor(String age);

	List<Estante> findByTraducaoContainingIgnoreCase(String idioma);

}
