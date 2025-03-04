package br.senac.sp.api.controller;

import br.senac.sp.api.domain.analysis.AnalysisDTO;
import br.senac.sp.api.domain.user.User;
import br.senac.sp.api.domain.user.UserService;
import br.senac.sp.api.domain.user.dto.RegisterUserDTO;
import br.senac.sp.api.domain.user.dto.LoginUserDTO;
import br.senac.sp.api.services.apicalls.APIConnector;
import br.senac.sp.api.services.apicalls.AvailableIA;
import br.senac.sp.api.services.apicalls.IAModel;
import br.senac.sp.api.services.apicalls.gemini.GeminiAPIService;
import br.senac.sp.api.services.apicalls.gemini.GeminiModel;
import br.senac.sp.api.services.apicalls.openai.OpenAiAPIService;
import br.senac.sp.api.services.apicalls.openai.OpenAiGPTModel;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/cadastrar")
    public ResponseEntity<?> registerUser(@RequestBody @Valid RegisterUserDTO cadastro) {

        return userService.cadastrar(cadastro);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody @Valid LoginUserDTO login) {
        Long user = new User("asd", "asd", "asd").getId();

        return userService.login(login);

    }

    @GetMapping("/testandoAPI")
    public ResponseEntity<?> testandoAPI() throws Exception {

        return userService.testSaveContext("Nos últimos anos, o mercado de tecnologia apresentou um crescimento significativo, com um aumento de 27,3% no número de startups ativas desde 2021. De acordo com pesquisas recentes, 68% dos consumidores preferem realizar compras online, enquanto apenas 32% ainda optam por lojas físicas.\n" +
                        "\n" +
                        "No setor de educação, a adoção de plataformas digitais cresceu 45% no último semestre, refletindo uma mudança na forma como os alunos acessam conteúdo. Além disso, 78% dos estudantes afirmam que o aprendizado online é mais flexível e eficiente do que os métodos tradicionais.\n" +
                        "\n" +
                        "Na área de saúde, estudos indicam que o uso de inteligência artificial para diagnósticos aumentou 54,2% desde 2020. Entre os médicos entrevistados, 82% acreditam que essas tecnologias podem melhorar a precisão das avaliações clínicas.\n" +
                        "\n" +
                        "Em contrapartida, a preocupação com a segurança digital cresceu 37% entre os usuários de internet, com 59% relatando já terem enfrentado tentativas de golpe ou vazamento de dados.\n" +
                        "\n" +
                        "Com tantas mudanças no cenário atual, especialistas preveem que o avanço da tecnologia continuará impactando diversos setores, levando a um aumento médio de 22% na automação de processos até 2030.",
                AvailableIA.GEMINI, GeminiModel.GEMINI_2_0_FLASH);
    }



}
