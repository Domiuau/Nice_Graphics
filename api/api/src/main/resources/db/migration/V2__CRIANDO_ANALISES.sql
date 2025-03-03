create table analysis (
    id VARCHAR(36) PRIMARY KEY,
    analyzed_text VARCHAR(3000) not null,
    model_who_responded VARCHAR(30) not null,
    cost_in_tokens INT not null,
    summary VARCHAR(500) not null
);

create table context (
    id VARCHAR(36) PRIMARY KEY,
    description VARCHAR(200) not null,
    analysis_id VARCHAR(36) not null,
    FOREIGN KEY (analysis_id) REFERENCES analysis(id)
);

create table data (
    id VARCHAR(36) PRIMARY KEY,
    field VARCHAR(30) not null,
    value DOUBLE not null,
    context_id VARCHAR(36) not null,
    FOREIGN KEY (context_id) REFERENCES context(id)
);