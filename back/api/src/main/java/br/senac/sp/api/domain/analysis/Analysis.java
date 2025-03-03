package br.senac.sp.api.domain.analysis;

import br.senac.sp.api.domain.context.Context;
import br.senac.sp.api.domain.context.ContextDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "analysis")
@Table(name = "analysis")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Analysis {

    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String summary;
    @OneToMany(mappedBy = "analysis", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Context> contexts;
    private String analyzedText;
    private int costInTokens;
    private String modelWhoResponded;

    public Analysis(AnalysisDTO analysisDTO) {
        this.summary = analysisDTO.textAnalysis().summary();
        this.contexts = analysisDTO.textAnalysis().contexts().stream().map(context -> new Context(context.description(), context.data(), this)).toList();
        this.analyzedText = analysisDTO.analyzedText();
        this.costInTokens = analysisDTO.costInTokens();
        this.modelWhoResponded = analysisDTO.modelWhoResponded();

    }
}
