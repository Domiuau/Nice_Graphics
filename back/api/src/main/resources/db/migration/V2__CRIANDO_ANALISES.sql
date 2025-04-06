create table analysis (
    id VARCHAR(36) PRIMARY KEY,
    analyzed_text TEXT not null,
    model_who_responded VARCHAR(30) not null,
    cost_in_tokens INT not null,
    creation_date TIMESTAMP,
    analyzed_by VARCHAR(20) not null,
    summary VARCHAR(500) not null,
    user_id BIGINT not null,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

create table context (
    id VARCHAR(36) PRIMARY KEY,
    description VARCHAR(200) not null,
    type VARCHAR(20) not null,
    number_represented VARCHAR(50) not null,
    analysis_id VARCHAR(36) not null,
    FOREIGN KEY (analysis_id) REFERENCES analysis(id)
);

create table data (
    id VARCHAR(36) PRIMARY KEY,
    field VARCHAR(80) not null,
    value DOUBLE not null,
    context_id VARCHAR(36) not null,
    FOREIGN KEY (context_id) REFERENCES context(id)
);