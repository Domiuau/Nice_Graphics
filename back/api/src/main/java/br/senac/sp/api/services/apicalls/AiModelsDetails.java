package br.senac.sp.api.services.apicalls;

public enum AiModelsDetails {
    DEEPSEEK_CHAT("deepseek-chat", 5000, "ROLE_USER"),
    DEEKSEEK_REASONER("deepseek-reasoner", 5000, "ROLE_PREMIUM_USER"),
    GPT_3_5_TURBO("gpt-3.5-turbo", 5000, "ROLE_USER"),
    GPT_4("gpt-4", 5000, "ROLE_PREMIUM_USER"),
    GEMINI_1_5_FLASH("gemini-1.5-flash", 5000, "ROLE_USER"),
    GEMINI_2_0_FLASH("gemini-2.0-flash", 5000, "ROLE_USER")
    ;

    private String name;
    private int charactersLimit;
    private  String usarRole;

    AiModelsDetails(String name, int charactersLimit, String usarRole) {
        this.name = name;
        this.charactersLimit = charactersLimit;
        this.usarRole = usarRole;
    }

    public String getName() {
        return name;
    }

    public int getCharactersLimit() {
        return charactersLimit;
    }

    public String getUsarRole() {
        return usarRole;
    }
}
