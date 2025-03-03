package br.senac.sp.api.controller;

import br.senac.sp.api.domain.analysis.AnalysisDTO;
import br.senac.sp.api.domain.user.User;
import br.senac.sp.api.domain.user.UserService;
import br.senac.sp.api.domain.user.dto.RegisterUserDTO;
import br.senac.sp.api.domain.user.dto.LoginUserDTO;
import br.senac.sp.api.services.apicalls.APIConnector;
import br.senac.sp.api.services.apicalls.AvailableIA;
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
        APIConnector apiService = new OpenAiAPIService();

        return userService.testSaveContext("Na última competição esportiva realizada neste fim de semana, apenas a atleta Elina conseguiu pontuar, somando 10 pontos no total. Enquanto Gabriel, João, Maria e Pedro não conseguiram marcar pontos, Elina garantiu sua colocação na disputa. Os prêmios da competição foram distribuídos da seguinte forma: R$ 100 para o primeiro colocado, R$ 50 para o segundo e R$ 10 para o terceiro. Apesar do esforço dos demais competidores, apenas Elina chegou à zona de pontuação.",
                AvailableIA.OPENAI, OpenAiGPTModel.GPT_3_5_TURBO);
    }



}
