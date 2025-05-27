package br.senac.sp.api.domain.data;

import br.senac.sp.api.domain.context.Context;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "data")
@Table(name = "data")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Data {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String field;
    private double value;
    @ManyToOne
    @JoinColumn(name = "context_id")
    private Context context;

    public Data(String field, double value, Context context) {
        this.field = field;
        this.value = value;
        this.context = context;
    }

}
