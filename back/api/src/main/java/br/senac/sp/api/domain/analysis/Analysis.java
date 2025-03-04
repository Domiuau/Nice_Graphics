package br.senac.sp.api.domain.analysis;

import br.senac.sp.api.domain.context.Context;
import br.senac.sp.api.domain.context.ContextDTO;
import br.senac.sp.api.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
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
    private Date creation_date;
    private String analyzedBy;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Analysis(AnalysisDTO analysisDTO, User user) {
        this.summary = analysisDTO.textAnalysis().summary();
        this.contexts = analysisDTO.textAnalysis().contexts().stream().map(context -> new Context(context.description(), context.data(), this)).toList();
        this.analyzedText = analysisDTO.analyzedText();
        this.costInTokens = analysisDTO.costInTokens();
        this.modelWhoResponded = analysisDTO.modelWhoResponded();
        this.creation_date = analysisDTO.creationDate();
        this.analyzedBy = analysisDTO.analyzedBy();
        this.user = user;

    }
}
