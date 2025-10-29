package br.com.fuctura.biblioteca.enums;

public enum Edicao {

    PRIMEIRA(0),
    SEGUNDA(1),
    TERCEIRA(2);

    private Integer codigo;

    Edicao(Integer codigo) {
        this.codigo = codigo;
    }
}
