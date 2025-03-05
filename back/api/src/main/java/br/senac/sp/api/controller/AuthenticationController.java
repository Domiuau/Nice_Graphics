package br.senac.sp.api.controller;

import br.senac.sp.api.domain.user.UserService;
import br.senac.sp.api.domain.user.dto.RegisterUserDTO;
import br.senac.sp.api.domain.user.dto.LoginUserDTO;
import br.senac.sp.api.services.apicalls.AvailableAI;
import br.senac.sp.api.services.apicalls.deepseek.DeepseekAPIService;
import br.senac.sp.api.services.apicalls.deepseek.DeepseekModel;
import br.senac.sp.api.services.apicalls.gemini.GeminiModel;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

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

        return userService.login(login);

    }



    @GetMapping("/testandoAPI")
    public ResponseEntity<?> testandoAPI() throws Exception {

        DeepseekAPIService deepseekAPIService = new DeepseekAPIService();
        System.out.println(deepseekAPIService.getAnalysisOfText("Qual é a capital do Brasil?", DeepseekModel.DEEPSEEK_CHAT));
        return ResponseEntity.ok().build();
    }


}
