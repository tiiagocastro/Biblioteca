package br.com.fuctura.biblioteca.exceptions;

public class FieldMessage {
    private String defaultMessaage;
    private String field;

    public FieldMessage(String defaultMessaage, String field) {
        this.defaultMessaage = defaultMessaage;
        this.field = field;
    }

    public String getDefaultMessaage() {
        return defaultMessaage;
    }

    public void setDefaultMessaage(String defaultMessaage) {
        this.defaultMessaage = defaultMessaage;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

}
