package br.com.fuctura.biblioteca.dtos;

import br.com.fuctura.biblioteca.enums.Edicao;
import br.com.fuctura.biblioteca.models.Categoria;
import br.com.fuctura.biblioteca.models.Livro;

public class LivroDto {
    private Integer id;
    private String titulo;
    private String autor;
    private String sinopse;
    private Edicao edicao;
    private Categoria categoria;

    public LivroDto() {
    }

    public LivroDto(Integer id, String titulo, String autor, String sinopse, Edicao edicao, Categoria categoria) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.sinopse = sinopse;
        this.edicao = edicao;
        this.categoria = categoria;
    }

    public LivroDto(Livro livro) {
        this.id = livro.getId();
        this.titulo = livro.getTitulo();
        this.autor = livro.getAutor();
        this.sinopse = livro.getSinopse();
        this.edicao = livro.getEdicao();
        this.categoria = livro.getCategoria();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getSinopse() {
        return sinopse;
    }

    public void setSinopse(String sinopse) {
        this.sinopse = sinopse;
    }

    public Edicao getEdicao() {
        return edicao;
    }

    public void setEdicao(Edicao edicao) {
        this.edicao = edicao;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
