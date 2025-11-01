package br.com.fuctura.biblioteca.controllers;

import br.com.fuctura.biblioteca.dtos.LivroDto;
import br.com.fuctura.biblioteca.models.Livro;
import br.com.fuctura.biblioteca.services.LivroService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/livro")
public class LivroController {
    // @GetMapping("/{id}") = //Método para buscar categoria por id
    // @GetMapping = Método para listar todas as categorias
    // @PostMapping = Método para criar uma nova categoria
    // @PutMapping("/{id}") = Método para atualizar uma categoria existente
    // @DeleteMapping("/{id}") = Método para deletar uma categoria pelo ID


    @Autowired
    private LivroService livroService;

    @Autowired
    private ModelMapper modelMapper;


    @GetMapping("/{id}")
    public ResponseEntity<LivroDto> findById(@PathVariable Integer id) {
        Livro livro = livroService.findById(id);
        return ResponseEntity.ok().body(modelMapper.map(livro, LivroDto.class));
    }

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<LivroDto> findByTitulo(@PathVariable String titulo) {
        Livro livro = livroService.findByTitulo(titulo);
        return ResponseEntity.ok().body(new LivroDto(livro));
    }

    @GetMapping
    public ResponseEntity<List<LivroDto>> findAll(@RequestParam(value = "categoria", defaultValue = "0") Integer id_cat) {
        List<Livro> list = livroService.findAll(id_cat);
        return ResponseEntity.ok().body(list.stream().map(x -> new LivroDto(x)).collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<LivroDto> save(@RequestParam(value = "categoria", defaultValue = "0") Integer id_cat,
                                         @RequestBody LivroDto livroDto) {
        Livro livro = livroService.save(id_cat, livroDto);
        return ResponseEntity.ok().body(new LivroDto(livro));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LivroDto> update(@RequestParam(value = "categoria", defaultValue = "0") Integer id_cat,
                                           @PathVariable Integer id,
                                           @RequestBody LivroDto livrodto) {
        Livro livro = livroService.update(id_cat, id, livrodto);
        return ResponseEntity.ok().body(new LivroDto(livro));
        // metodo de consulta
        //localhost:8080/livro/1?categoria=3
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        livroService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
