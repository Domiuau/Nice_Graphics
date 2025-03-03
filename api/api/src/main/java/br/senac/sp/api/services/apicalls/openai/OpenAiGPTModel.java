package br.senac.sp.api.services.apicalls.openai;

public enum OpenAiGPTModel implements IAModel {
    GPT_3_5_TURBO("gpt-3.5-turbo"),
    GPT_4("gpt-4"),
    GPT_4_32K("gpt-4-32k");

    private final String model;

    OpenAiGPTModel(String model) {
        this.model = model;
    }

    @Override
    public String getModel() {
        return model;
    }
}
