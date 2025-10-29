package br.com.fuctura.biblioteca.controllers;

import br.com.fuctura.biblioteca.dtos.LivroDto;
import br.com.fuctura.biblioteca.models.Livro;
import br.com.fuctura.biblioteca.services.LivroService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @GetMapping("/{titulo}")
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
    public ResponseEntity<LivroDto> save(@Valid @RequestBody LivroDto livroDto) {
        Livro livro = modelMapper.map(livroDto, Livro.class);
        Livro liv = livroService.save(livro);
        return ResponseEntity.ok().body(modelMapper.map(liv, LivroDto.class));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LivroDto> update(@PathVariable Integer id, @Valid @RequestBody LivroDto livroDto) {
        livroDto.setId(id);
        Livro livro = modelMapper.map(livroDto, Livro.class);
        Livro livAtualizado = livroService.update(livro);
        return ResponseEntity.ok().body(modelMapper.map(livAtualizado, LivroDto.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        livroService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
