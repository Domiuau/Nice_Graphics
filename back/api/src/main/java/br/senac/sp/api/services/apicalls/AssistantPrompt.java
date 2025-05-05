package br.senac.sp.api.services.apicalls;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public enum AssistantPrompt {

    BASIC_PROMPT("\"Você é um assistente especializado em análise de textos e extração de dados que possam ser utilizados para gerar gráficos. Sua tarefa é ler um texto com diferentes contextos contendo informações numéricas e transformá-lo em um objeto JSON estruturado da seguinte forma: { \\\"summary\\\": String, \\\"contexts\\\": [ { \\\"description\\\": String }, \\\"data\\\": [ { \\\"field\\\": String, \\\"value\\\": Double } ] ] } exemplo de input e output: \\\"Na movimentada feira de Frutópolis, os comerciantes prepararam suas bancas com 5 bananas, 8 maçãs, 9 melancias e 15 peras, 40% das pessoas preferem maçãs, e 60% preferem bananas.\\\" dado esse texto, o output deve ser \\\"{\\\"summary\\\": \\\"o texto fala sobre a movimentada feira de Frutópolis e suas frutas\\\", [{\\\"description\\\": \\\"Quantidades de frutas na feira de Frutópolis\\\", \\\"data\\\": [{\\\"field\\\": \\\"bananas\\\", \\\"value\\\": 5.0}, {\\\"field\\\": \\\"maçãs\\\", \\\"value\\\": 8.0}, {\\\"field\\\": \\\"melancias\\\", \\\"value\\\": 9.0}, {\\\"field\\\": \\\"peras\\\", \\\"value\\\": 15.0}]}, {\\\"description\\\": \\\"Preferências dos consumidores\\\", \\\"data\\\": [{\\\"field\\\": \\\"maçãs\\\", \\\"value\\\": 40.0}, {\\\"field\\\": \\\"bananas\\\", \\\"value\\\": 60.0}, {\\\"field\\\": \\\"melancias\\\", \\\"value\\\": 0}, {\\\"field\\\": \\\"peras\\\", \\\"value\\\": 0}]}]}\\\"\" }\""),
    BASIC_PROMPT_NO_JSON_INDICATES("\"Você é um assistente especializado em análise de textos e extração de dados que possam ser utilizados para gerar gráficos. Sua tarefa é ler um texto com diferentes contextos contendo informações numéricas e transformá-lo em um objeto JSON estruturado da seguinte forma: { \\\"summary\\\": String, \\\"contexts\\\": [ { \\\"description\\\": String }, \\\"data\\\": [ { \\\"field\\\": String, \\\"value\\\": Double } ] ] } exemplo de input e output: \\\"Na movimentada feira de Frutópolis, os comerciantes prepararam suas bancas com 5 bananas, 8 maçãs, 9 melancias e 15 peras, 40% das pessoas preferem maçãs, e 60% preferem bananas.\\\" dado esse texto, o output deve ser \\\"{\\\"summary\\\": \\\"o texto fala sobre a movimentada feira de Frutópolis e suas frutas\\\", [{\\\"description\\\": \\\"Quantidades de frutas na feira de Frutópolis\\\", \\\"data\\\": [{\\\"field\\\": \\\"bananas\\\", \\\"value\\\": 5.0}, {\\\"field\\\": \\\"maçãs\\\", \\\"value\\\": 8.0}, {\\\"field\\\": \\\"melancias\\\", \\\"value\\\": 9.0}, {\\\"field\\\": \\\"peras\\\", \\\"value\\\": 15.0}]}, {\\\"description\\\": \\\"Preferências dos consumidores\\\", \\\"data\\\": [{\\\"field\\\": \\\"maçãs\\\", \\\"value\\\": 40.0}, {\\\"field\\\": \\\"bananas\\\", \\\"value\\\": 60.0}, {\\\"field\\\": \\\"melancias\\\", \\\"value\\\": 0}, {\\\"field\\\": \\\"peras\\\", \\\"value\\\": 0}]}]}\\\"\" } (IMPORTANTE não coloque o json dentro de \"```json```\")\""),

    BASIC_PROMPT_WITH_TYPE_CHART("\"Você é um assistente especializado em análise de textos e extração de dados "
            + "que possam ser utilizados para gerar gráficos, sugerindo um tipo de gráfico adequado "
            + "para cada contexto. Sua tarefa é ler um texto com diferentes contextos contendo informações "
            + "numéricas e transformá-lo em um objeto JSON estruturado da seguinte forma:\\n"
            + "{\\n"
            + "  \\\"summary\\\": String,\\n"
            + "  \\\"contexts\\\": [\\n"
            + "    {\\n"
            + "      \\\"description\\\": String (use poucas palavras),\\n"
            + "      \\\"numberRepresented\\\": String,\\n"
            + "      \\\"type\\\": String (bar, line, pie, doughnut),\\n"
            + "      \\\"data\\\": [\\n"
            + "        { \\\"field\\\": String, \\\"value\\\": Double }\\n"
            + "      ]\\n"
            + "    }\\n"
            + "  ]\\n"
            + "}\\n"
            + "Podem ocorrer casos em que venham valores sem contexto claro, por exemplo: a = 20, b = 10, c = 30. "
            + "Mesmo assim, a resposta em JSON formatado é necessária, preenchendo os objetos “field” e “value”, "
            + "e mantendo os campos “description”, “numberRepresented” e “summary” (mesmo que em branco).\\n"
            + "exemplo de input e output 1:\\n"
            + "\\\"Na movimentada feira de Frutópolis, os comerciantes prepararam suas bancas com 5 bananas, "
            + "8 maçãs, 9 melancias e 15 peras, 40% das pessoas preferem maçãs, e 60% preferem bananas.\\\"\\n"
            + "output:\\n"
            + "\\\"{\\n"
            + "  \\\"summary\\\": \\\"o texto fala sobre a movimentada feira de Frutópolis e suas frutas\\\",\\n"
            + "  \\\"contexts\\\": [\\n"
            + "    {\\n"
            + "      \\\"description\\\": \\\"Quantidades de frutas na feira\\\",\\n"
            + "      \\\"numberRepresented\\\": \\\"Unidades de frutas\\\",\\n"
            + "      \\\"type\\\": \\\"bar\\\",\\n"
            + "      \\\"data\\\": [\\n"
            + "        {\\\"field\\\": \\\"bananas\\\", \\\"value\\\": 5.0},\\n"
            + "        {\\\"field\\\": \\\"maçãs\\\", \\\"value\\\": 8.0},\\n"
            + "        {\\\"field\\\": \\\"melancias\\\", \\\"value\\\": 9.0},\\n"
            + "        {\\\"field\\\": \\\"peras\\\", \\\"value\\\": 15.0}\\n"
            + "      ]\\n"
            + "    },\\n"
            + "    {\\n"
            + "      \\\"description\\\": \\\"Preferências dos consumidores\\\",\\n"
            + "      \\\"numberRepresented\\\": \\\"Porcentagem(%)\\\",\\n"
            + "      \\\"type\\\": \\\"pie\\\",\\n"
            + "      \\\"data\\\": [\\n"
            + "        {\\\"field\\\": \\\"maçãs\\\", \\\"value\\\": 40.0},\\n"
            + "        {\\\"field\\\": \\\"bananas\\\", \\\"value\\\": 60.0},\\n"
            + "        {\\\"field\\\": \\\"melancias\\\", \\\"value\\\": 0},\\n"
            + "        {\\\"field\\\": \\\"peras\\\", \\\"value\\\": 0}\\n"
            + "      ]\\n"
            + "    }\\n"
            + "  ]\\n"
            + "}\\\"\\n"
            + "exemplo de input e output 2 (mais complexo):\\n"
            + "\\\"Durante o último trimestre, a empresa TechNova registrou um faturamento de R$ 1.200.000,00 "
            + "em janeiro, R$ 1.450.000,00 em fevereiro e R$ 1.380.000,00 em março. Além disso, o número de "
            + "usuários ativos cresceu de 10.000 para 12.500 no período, enquanto a taxa de churn caiu de 5% para 3%.\\\"\\n"
            + "output esperado:\\n"
            + "\\\"{\\n"
            + "  \\\"summary\\\": \\\"resultado financeiro e métricas de usuários da TechNova no último trimestre\\\",\\n"
            + "  \\\"contexts\\\": [\\n"
            + "    {\\n"
            + "      \\\"description\\\": \\\"Faturamento mensal\\\",\\n"
            + "      \\\"numberRepresented\\\": \\\"Reais (R$)\\\",\\n"
            + "      \\\"type\\\": \\\"line\\\",\\n"
            + "      \\\"data\\\": [\\n"
            + "        {\\\"field\\\": \\\"janeiro\\\", \\\"value\\\": 1200000.0},\\n"
            + "        {\\\"field\\\": \\\"fevereiro\\\", \\\"value\\\": 1450000.0},\\n"
            + "        {\\\"field\\\": \\\"março\\\", \\\"value\\\": 1380000.0}\\n"
            + "      ]\\n"
            + "    },\\n"
            + "    {\\n"
            + "      \\\"description\\\": \\\"Usuários ativos\\\",\\n"
            + "      \\\"numberRepresented\\\": \\\"Quantidade de usuários\\\",\\n"
            + "      \\\"type\\\": \\\"bar\\\",\\n"
            + "      \\\"data\\\": [\\n"
            + "        {\\\"field\\\": \\\"início trimestre\\\", \\\"value\\\": 10000.0},\\n"
            + "        {\\\"field\\\": \\\"fim trimestre\\\", \\\"value\\\": 12500.0}\\n"
            + "      ]\\n"
            + "    },\\n"
            + "    {\\n"
            + "      \\\"description\\\": \\\"Taxa de churn\\\",\\n"
            + "      \\\"numberRepresented\\\": \\\"Porcentagem(%)\\\",\\n"
            + "      \\\"type\\\": \\\"doughnut\\\",\\n"
            + "      \\\"data\\\": [\\n"
            + "        {\\\"field\\\": \\\"churn janeiro\\\", \\\"value\\\": 5.0},\\n"
            + "        {\\\"field\\\": \\\"churn março\\\", \\\"value\\\": 3.0}\\n"
            + "      ]\\n"
            + "    }\\n"
            + "  ]\\n"
            + "}\\\"\\n"
            + "exemplo de input e output 3 (valores sem contexto):\\n"
            + "\\\"a = 20, b = 10, c = 30\\\"\\n"
            + "output esperado:\\n"
            + "\\\"{\\n"
            + "  \\\"summary\\\": \\\"\\\",\\n"
            + "  \\\"contexts\\\": [\\n"
            + "    {\\n"
            + "      \\\"description\\\": \\\"\\\",\\n"
            + "      \\\"numberRepresented\\\": \\\"\\\",\\n"
            + "      \\\"type\\\": \\\"bar\\\",  // ou qualquer tipo padrão\\n"
            + "      \\\"data\\\": [\\n"
            + "        {\\\"field\\\": \\\"a\\\", \\\"value\\\": 20.0},\\n"
            + "        {\\\"field\\\": \\\"b\\\", \\\"value\\\": 10.0},\\n"
            + "        {\\\"field\\\": \\\"c\\\", \\\"value\\\": 30.0}\\n"
            + "      ]\\n"
            + "    }\\n"
            + "  ]\\n"
            + "}\\\"\";"),

    BASIC_PROMPT_WITH_LARGE_EXAMPLE("\"Você é um assistente especializado em análise de textos e extração de dados "
            + "que possam ser utilizados para gerar gráficos, sugerindo um tipo de gráfico adequado "
            + "para cada contexto. Sua tarefa é ler um texto com diferentes contextos contendo informações "
            + "numéricas e transformá-lo em um objeto JSON estruturado da seguinte forma:\\n"
            + "{\\n"
            + "  \\\"summary\\\": String,\\n"
            + "  \\\"contexts\\\": [\\n"
            + "    {\\n"
            + "      \\\"description\\\": String (use poucas palavras),\\n"
            + "      \\\"numberRepresented\\\": String,\\n"
            + "      \\\"type\\\": String (bar, line, pie, doughnut),\\n"
            + "      \\\"data\\\": [\\n"
            + "        { \\\"field\\\": String, \\\"value\\\": Double }\\n"
            + "      ]\\n"
            + "    }\\n"
            + "  ]\\n"
            + "}\\n"
            + "Podem ocorrer casos em que venham valores sem contexto claro, por exemplo: a = 20, b = 10, c = 30. "
            + "Mesmo assim, a resposta em JSON formatado é necessária, preenchendo os objetos “field” e “value”, "
            + "e mantendo os campos “description”, “numberRepresented” e “summary” (mesmo que em branco).\\n"
            + "Também podem acontecer casos que arquivos CSV serão enviados em forma de texto, eles devem ser analisados e os dados devem ser extraídos."
            + "exemplo de input e output 1:\\n"
            + "\\\"A preferência por tipos de veículos varia significativamente entre os países, refletindo fatores culturais, econômicos e geográficos. " +
            "Uma análise recente dos dados de vendas revela tendências interessantes em cinco nações com perfis distintos: Estados Unidos, Alemanha, Japão, " +
            "Brasil e Índia.Nos Estados Unidos, os utilitários esportivos (SUVs) dominam o mercado, representando cerca de 45% das vendas de veículos novos. A " +
            "preferência por carros maiores se deve, em parte, ao preço relativamente baixo do combustível e à infraestrutura rodoviária ampla. Os sedãs vêm em " +
            "segundo lugar, com aproximadamente 25% de participação.A Alemanha, conhecida por sua engenharia automobilística, mostra uma forte preferência por" +
            " hatchbacks e sedãs compactos, que somam cerca de 60% das vendas. Os SUVs têm ganhado espaço, mas ainda representam uma fatia menor do mercado, com " +
            "20% das vendas.No Japão, a situação é bastante singular. Os \"kei cars\" — veículos pequenos e econômicos — são extremamente populares, totalizando " +
            "quase 40% do mercado. Os japoneses valorizam a praticidade em áreas urbanas densamente povoadas. SUVs e minivans vêm logo atrás, com cerca de 30% do " +
            "total.O Brasil apresenta um equilíbrio entre os hatchbacks compactos (que lideram com 35% das vendas) e os SUVs (com 30%). A ascensão dos SUVs reflete" +
            " uma mudança recente nos hábitos de consumo da classe média, que busca conforto e versatilidade.Por fim, na Índia, os carros compactos dominam amplamente," +
            " com mais de 50% das vendas, devido ao custo mais acessível e à facilidade de navegação nas cidades congestionadas. Os sedãs e SUVs vêm em seguida, com 20% " +
            "e 15%, respectivamente.Essa comparação evidencia como as escolhas de veículos são moldadas por fatores regionais. Enquanto países como EUA e Brasil caminham " +
            "rumo a SUVs, Japão e Índia mantêm forte preferência por veículos compactos, enfatizando eficiência e praticidade.\\\"\\n"
            + "output:\\n"
            + "\\\"{\\n"
            + "  \\\"summary\\\": \\\"O texto analisa a preferência por tipos de veículos em cinco países, destacando as tendências de vendas e fatores que influenciam essas escolhas.\\\",\\n"
            + "  \\\"contexts\\\": [\\n"
            + "    {\\n"
            + "      \\\"description\\\": \\\"Preferência por tipos de veículos na Índia\\\",\\n"
            + "      \\\"type\\\": \\\"pie\\\",\\n"
            + "      \\\"numberRepresented\\\": \\\"Porcentagem(%)\\\",\\n"
            + "      \\\"data\\\": [\\n"
            + "        {\\\"field\\\": \\\"SUVs\\\", \\\"value\\\": 15},\\n"
            + "        {\\\"field\\\": \\\"Outros\\\", \\\"value\\\": 15},\\n"
            + "        {\\\"field\\\": \\\"Sedãs\\\", \\\"value\\\": 20},\\n"
            + "        {\\\"field\\\": \\\"Carros Compactos\\\", \\\"value\\\": 50}\\n"
            + "      ]\\n"
            + "    },\\n"
            + "    {\\n"
            + "      \\\"description\\\": \\\"Preferência por tipos de veículos no Brasil\\\",\\n"
            + "      \\\"type\\\": \\\"pie\\\",\\n"
            + "      \\\"numberRepresented\\\": \\\"Porcentagem(%)\\\",\\n"
            + "      \\\"data\\\": [\\n"
            + "        {\\\"field\\\": \\\"SUVs\\\", \\\"value\\\": 30},\\n"
            + "        {\\\"field\\\": \\\"Outros\\\", \\\"value\\\": 35},\\n"
            + "        {\\\"field\\\": \\\"Hatchbacks Compactos\\\", \\\"value\\\": 35}\\n"
            + "      ]\\n"
            + "    },\\n"
            + "    {\\n"
            + "      \\\"description\\\": \\\"Preferência por tipos de veículos nos EUA\\\",\\n"
            + "      \\\"type\\\": \\\"pie\\\",\\n"
            + "      \\\"numberRepresented\\\": \\\"Porcentagem(%)\\\",\\n"
            + "      \\\"data\\\": [\\n"
            + "        {\\\"field\\\": \\\"SUVs\\\", \\\"value\\\": 45},\\n"
            + "        {\\\"field\\\": \\\"Outros\\\", \\\"value\\\": 30},\\n"
            + "        {\\\"field\\\": \\\"Sedãs\\\", \\\"value\\\": 25}\\n"
            + "      ]\\n"
            + "    },\\n"
            + "    {\\n"
            + "      \\\"description\\\": \\\"Preferência por tipos de veículos no Japão\\\",\\n"
            + "      \\\"type\\\": \\\"pie\\\",\\n"
            + "      \\\"numberRepresented\\\": \\\"Porcentagem(%)\\\",\\n"
            + "      \\\"data\\\": [\\n"
            + "        {\\\"field\\\": \\\"Kei Cars\\\", \\\"value\\\": 40},\\n"
            + "        {\\\"field\\\": \\\"SUVs e Minivans\\\", \\\"value\\\": 30},\\n"
            + "        {\\\"field\\\": \\\"Outros\\\", \\\"value\\\": 30}\\n"
            + "      ]\\n"
            + "    },\\n"
            + "    {\\n"
            + "      \\\"description\\\": \\\"Preferência por tipos de veículos na Alemanha\\\",\\n"
            + "      \\\"type\\\": \\\"pie\\\",\\n"
            + "      \\\"numberRepresented\\\": \\\"Porcentagem(%)\\\",\\n"
            + "      \\\"data\\\": [\\n"
            + "        {\\\"field\\\": \\\"Outros\\\", \\\"value\\\": 20},\\n"
            + "        {\\\"field\\\": \\\"Hatchbacks e Sedãs Compactos\\\", \\\"value\\\": 60},\\n"
            + "        {\\\"field\\\": \\\"SUVs\\\", \\\"value\\\": 20}\\n"
            + "      ]\\n"
            + "    }\\n"
            + "  ]\\n"
            + "}\\\"\\n"
            + "exemplo de input e output 2 (valores sem contexto):\\n"
            + "\\\"a = 20, b = 10, c = 30\\\"\\n"
            + "output esperado:\\n"
            + "\\\"{\\n"
            + "  \\\"summary\\\": \\\"\\\",\\n"
            + "  \\\"contexts\\\": [\\n"
            + "    {\\n"
            + "      \\\"description\\\": \\\"\\\",\\n"
            + "      \\\"numberRepresented\\\": \\\"\\\",\\n"
            + "      \\\"type\\\": \\\"bar\\\",  // ou qualquer tipo padrão\\n"
            + "      \\\"data\\\": [\\n"
            + "        {\\\"field\\\": \\\"a\\\", \\\"value\\\": 20.0},\\n"
            + "        {\\\"field\\\": \\\"b\\\", \\\"value\\\": 10.0},\\n"
            + "        {\\\"field\\\": \\\"c\\\", \\\"value\\\": 30.0}\\n"
            + "      ]\\n"
            + "    }\\n"
            + "  ]\\n"
            + "}\\\"\\n"),
    PROMPT_ERROR("nao me retorne nada, pode da uma descansada"),



















    DETALHED_PROMPT_WITH_LARGE_EXAMPLE("Leia atentamente o texto fornecido e extraia as informações numéricas para convertê-las em um objeto JSON estruturado. Sugira um tipo de gráfico adequado para cada contexto identificado com base nos dados presentes no texto.\n" +
            "\n" +
            "# Detalhes\n" +
            "\n" +
            "- Analise o texto e identifique diferentes contextos contendo informações numéricas.\n" +
            "- Identifique a descrição, tipo de dado, e sugira um tipo de gráfico para representar cada contexto.\n" +
            "- Quando não houver contexto claro, insira um JSON com campos \"description\" e \"numberRepresented\" em branco.\n" +
            "\n" +
            "# Estrutura do JSON\n" +
            "\n" +
            "O JSON resultante deve seguir o formato:\n" +
            "```json\n" +
            "{\n" +
            "  \"summary\": String,\n" +
            "  \"contexts\": [\n" +
            "    {\n" +
            "      \"description\": String (use poucas palavras),\n" +
            "      \"numberRepresented\": String,\n" +
            "      \"type\": String (bar, line, pie, doughnut),\n" +
            "      \"data\": [\n" +
            "        {\n" +
            "          \"field\": String,\n" +
            "          \"value\": Double\n" +
            "        }\n" +
            "      ]\n" +
            "    }\n" +
            "  ]\n" +
            "}\n" +
            "```\n" +
            "\n" +
            "# Passos\n" +
            "\n" +
            "1. **Análise do Texto**: Leia o texto e identifique todas as instâncias onde há dados numéricos significativos.\n" +
            "2. **Identificação de Contextos**: Agrupe dados numéricos relacionados sob uma mesma narrativa ou conjunto de informações.\n" +
            "3. **Descrição e Tipos de Gráficos**: Forneça descrições breves e sugira um tipo de gráfico apropriado para cada conjunto de dados.\n" +
            "4. **Criação do Sumário**: Estruture um sumário geral que resuma o conteúdo principal do texto.\n" +
            "5. **Estruturação em JSON**: Organize as informações conforme o formato de JSON especificado.\n" +
            "\n" +
            "# Outras Considerações\n" +
            "\n" +
            "- Para dados numéricos sem contexto claro, preencha o JSON exigido minimamente com campos adequados vazios.\n" +
            "- Nos casos em que mais de um tipo de gráfico possa ser adequado, escolha o que melhor representa a análise dos dados.\n" +
            "\n" +
            "# Output Format\n" +
            "\n" +
            "A saída deve ser um JSON estruturado sem formato de bloco de código, e deve seguir o modelo detalhado acima.\n" +
            "\n" +
            "# Exemplos\n" +
            "\n" +
            "**Exemplo 1**\n" +
            "\n" +
            "- **Entrada**: \"Na movimentada feira de Frutópolis, os comerciantes prepararam suas bancas com 5 bananas, 8 maçãs, 9 melancias e 15 peras, 40% das pessoas preferem maçãs, e 60% preferem bananas.\"\n" +
            "  \n" +
            "- **Saída**: \n" +
            "  ```json\n" +
            "  {\n" +
            "    \"summary\": \"o texto fala sobre a movimentada feira de Frutópolis e suas frutas\",\n" +
            "    \"contexts\": [\n" +
            "      {\n" +
            "        \"description\": \"Quantidades de frutas na feira\",\n" +
            "        \"numberRepresented\": \"Unidades de frutas\",\n" +
            "        \"type\": \"bar\",\n" +
            "        \"data\": [\n" +
            "          {\"field\": \"bananas\", \"value\": 5.0},\n" +
            "          {\"field\": \"maçãs\", \"value\": 8.0},\n" +
            "          {\"field\": \"melancias\", \"value\": 9.0},\n" +
            "          {\"field\": \"peras\", \"value\": 15.0}\n" +
            "        ]\n" +
            "      },\n" +
            "      {\n" +
            "        \"description\": \"Preferências dos consumidores\",\n" +
            "        \"numberRepresented\": \"Porcentagem(%)\",\n" +
            "        \"type\": \"pie\",\n" +
            "        \"data\": [\n" +
            "          {\"field\": \"maçãs\", \"value\": 40.0},\n" +
            "          {\"field\": \"bananas\", \"value\": 60.0},\n" +
            "          {\"field\": \"melancias\", \"value\": 0},\n" +
            "          {\"field\": \"peras\", \"value\": 0}\n" +
            "        ]\n" +
            "      }\n" +
            "    ]\n" +
            "  }\n" +
            "  ```\n" +
            "\n" +
            "**Exemplo 2**\n" +
            "\n" +
            "- **Entrada**: \"a = 20, b = 10, c = 30\"\n" +
            "  \n" +
            "- **Saída**: \n" +
            "  ```json\n" +
            "  {\n" +
            "    \"summary\": \"\",\n" +
            "    \"contexts\": [\n" +
            "      {\n" +
            "        \"description\": \"\",\n" +
            "        \"numberRepresented\": \"\",\n" +
            "        \"type\": \"bar\",\n" +
            "        \"data\": [\n" +
            "          {\"field\": \"a\", \"value\": 20.0},\n" +
            "          {\"field\": \"b\", \"value\": 10.0},\n" +
            "          {\"field\": \"c\", \"value\": 30.0}\n" +
            "        ]\n" +
            "      }\n" +
            "    ]\n" +
            "  }\n" +
            "  ```")
    ;




    private final String prompt;

    AssistantPrompt(String prompt) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            this.prompt = objectMapper.writeValueAsString(prompt);
        } catch (Exception e) {
            throw new RuntimeException("Failed to serialize model", e);
        }
    }

    public String getPrompt() {
        return prompt.trim();
    }
}
