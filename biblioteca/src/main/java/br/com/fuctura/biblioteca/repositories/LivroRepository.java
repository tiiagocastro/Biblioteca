package br.com.fuctura.biblioteca.repositories;

import br.com.fuctura.biblioteca.models.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Integer> {

    Optional<Livro> findByTituloIgnoreCase(String titulo);

    List<Livro> findAllByCategoriaId(Integer id_cat);
}
