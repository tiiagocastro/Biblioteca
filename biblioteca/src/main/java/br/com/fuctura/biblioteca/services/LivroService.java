package br.com.fuctura.biblioteca.services;

import br.com.fuctura.biblioteca.exceptions.ObjectNotFoundException;
import br.com.fuctura.biblioteca.models.Livro;
import br.com.fuctura.biblioteca.repositories.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private CategoriaService categoriaService;

    public Livro findById(Integer id) {
        return livroRepository.findById(id).orElseThrow(() ->
                new ObjectNotFoundException("Livro não encontrado com o ID: " + id));
    }

    public Livro findByTitulo(String titulo) {
        return livroRepository.findByTituloIgnoreCase(titulo).orElseThrow(() ->
                new ObjectNotFoundException("Não existe livro com esse título." + titulo));
    }


    public List<Livro> findAll(Integer id_cat) {
        categoriaService.findById(id_cat);
        return livroRepository.findAllByCategoriaId(id_cat);
    }

    public Livro save(Livro livro) {
        buscarPorTitulo(livro);
        return livroRepository.save(livro);
    }

    public Livro update(Livro livro) {
        findById(livro.getId());
        buscarPorTitulo(livro);
        return livroRepository.save(livro);
    }

    public void delete(Integer id) {
        Livro livro = findById(id);
        livroRepository.deleteById(id);
    }

    private void buscarPorTitulo(Livro livro) {
        Optional<Livro> liv = livroRepository.findByTituloIgnoreCase(livro.getTitulo());
        if (liv.isPresent() && !liv.get().getId().equals(livro.getId())) {
            throw new IllegalArgumentException("Já existe um livro com o título: "  + livro.getTitulo());
        }
    }

}
