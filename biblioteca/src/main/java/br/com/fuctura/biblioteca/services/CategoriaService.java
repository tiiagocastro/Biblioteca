package br.com.fuctura.biblioteca.services;

import br.com.fuctura.biblioteca.exceptions.ObjectNotFoundException;
import br.com.fuctura.biblioteca.models.Categoria;
import br.com.fuctura.biblioteca.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria findById(Integer id) {
        return categoriaRepository.findById(id).orElseThrow(() ->
                new ObjectNotFoundException("Categoria não encontrada com o ID: " + id));
    }

    public Categoria findByNome(String nome) {
        return categoriaRepository.findByNomeContainingIgnoreCase(nome).orElseThrow(() ->
                new ObjectNotFoundException("Não existe categoria com esse nome: " + nome));
    }

    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    public Categoria save(Categoria categoria) {
        buscarPorNome(categoria);
        return categoriaRepository.save(categoria);
    }

    public Categoria update(Categoria categoria) {
        findById(categoria.getId());
        buscarPorNome(categoria);
        return categoriaRepository.save(categoria);
    }

    public void delete(Integer id) {
        Categoria categoria = findById(id);
        if (!categoria.getLivros().isEmpty()) {
            throw new DataIntegrityViolationException("Categoria não pode ser deletada, pois possuem livros associados.");
        }
        categoriaRepository.deleteById(id);
    }

    private void buscarPorNome(Categoria categoria) {
        Optional<Categoria> cat = categoriaRepository.findByNomeIgnoreCase(categoria.getNome());
        if (cat.isPresent()) {
            if (cat.get().getId() != categoria.getId()) {
                throw new IllegalArgumentException("Categoria já existe com o nome: " + categoria.getNome());
            }
        }
    }

}
