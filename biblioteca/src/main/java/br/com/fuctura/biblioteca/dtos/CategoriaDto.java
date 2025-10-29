package br.com.fuctura.biblioteca.dtos;

import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;

public class CategoriaDto {

    private Integer id;


    @NotNull
    @Length(min = 3, max = 20, message = "O campo Nome deve conter entre 3 e 20 caracteres.")
    private String nome;

    @NotNull
    @Length(min = 10, max = 50, message = "O campo Descrição deve conter entre 10 e 50 caracteres.")
    private String descricao;

    public CategoriaDto() {
    }

    public CategoriaDto(Integer id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
