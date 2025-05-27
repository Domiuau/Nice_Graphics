package br.senac.sp.api.domain.data;

public record DataDTO(String field, double value) {

    public DataDTO(String field, double value) {
        this.field = field;
        this.value = value;
    }
    public DataDTO(Data data) {
        this(data.getField(), data.getValue());
    }
}
