package br.com.fuctura.biblioteca.controllers;

import br.com.fuctura.biblioteca.dtos.CategoriaDto;
import br.com.fuctura.biblioteca.models.Categoria;
import br.com.fuctura.biblioteca.services.CategoriaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    // @GetMapping("/{id}") = //Método para buscar categoria por id
    // @GetMapping = Método para listar todas as categorias
    // @PostMapping = Método para criar uma nova categoria
    // @PutMapping("/{id}") = Método para atualizar uma categoria existente
    // @DeleteMapping("/{id}") = Método para deletar uma categoria pelo ID

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDto> findById(@PathVariable Integer id) {
        Categoria categoria = categoriaService.findById(id);
        return ResponseEntity.ok().body(modelMapper.map(categoria, CategoriaDto.class));
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<CategoriaDto> findByNome(@PathVariable String nome) {
        Categoria categoria = categoriaService.findByNome(nome);
        return ResponseEntity.ok().body(modelMapper.map(categoria, CategoriaDto.class));
    }

    @GetMapping
    public ResponseEntity<List<CategoriaDto>> findAll() {
        List<Categoria> list = categoriaService.findAll();
        List<CategoriaDto> listDto = new ArrayList<>();
        for (Categoria categoria : list) {
            listDto.add(modelMapper.map(categoria, CategoriaDto.class));
        }
        return ResponseEntity.ok().body(listDto);
    }

    @PostMapping
    public ResponseEntity<CategoriaDto> save(@Valid @RequestBody CategoriaDto categoriaDto) {
        Categoria categoria = modelMapper.map(categoriaDto, Categoria.class);
        Categoria cat = categoriaService.save(categoria);
        return ResponseEntity.ok().body(modelMapper.map(cat, CategoriaDto.class));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDto> update(@PathVariable Integer id, @Valid @RequestBody CategoriaDto categoriaDto) {
        categoriaDto.setId(id);
        Categoria categoria = modelMapper.map(categoriaDto, Categoria.class);
        Categoria cat = categoriaService.update(categoria);
        return ResponseEntity.ok().body(modelMapper.map(cat, CategoriaDto.class));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        categoriaService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
