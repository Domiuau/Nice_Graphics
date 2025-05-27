package br.senac.sp.api.domain.context;

import br.senac.sp.api.domain.analysis.Analysis;
import br.senac.sp.api.domain.data.Data;
import br.senac.sp.api.domain.data.DataDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity(name = "context")
@Table(name = "context")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Context {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String description;
    private String type;
    private String numberRepresented;
    @OneToMany(mappedBy = "context", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Data> data;
    @ManyToOne
    @JoinColumn(name = "analysis_id")
    private Analysis analysis;

    public Context(String description, String type, String numberRepresented, List<DataDTO> dataDTO, Analysis analysis) {
        this.description = description;
        this.analysis = analysis;
        this.type = type;
        this.numberRepresented = numberRepresented;
        this.data = dataDTO.stream().map(data -> new Data(data.field(), data.value(), this)).toList();
    }




}
