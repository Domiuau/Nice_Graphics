package br.senac.sp.api.controller;

import br.senac.sp.api.domain.user.UserService;
import br.senac.sp.api.domain.user.dto.RegisterUserDTO;
import br.senac.sp.api.domain.user.dto.LoginUserDTO;
import br.senac.sp.api.domain.user.dto.UpdateUserDTO;
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

    @PostMapping("/register") @CrossOrigin
    public ResponseEntity<?> registerUser(@RequestBody @Valid RegisterUserDTO cadastro) {

        return userService.cadastrar(cadastro);
    }

    @PostMapping("/login") @CrossOrigin
    public ResponseEntity<?> loginUser(@RequestBody @Valid LoginUserDTO login) {

        return userService.login(login);

    }

    @PutMapping("/update") @CrossOrigin
    public ResponseEntity<?> updateUser(@RequestBody UpdateUserDTO update, @RequestHeader(name = "Authorization") String token) {

        return userService.updateUser(update, token);
    }

    @DeleteMapping("/delete/data") @CrossOrigin
    public ResponseEntity<?> deleteUserData(@RequestHeader(name = "Authorization") String token) {

        return userService.deleteUserData(token);
    }

    @DeleteMapping("/disable") @CrossOrigin
    public ResponseEntity<?> disableUser(@RequestHeader(name = "Authorization") String token) {

        return userService.disableUser(token);
    }

    @GetMapping("/{token}") @CrossOrigin
    public ResponseEntity<?> validateToken(@PathVariable String token) {

        return userService.validateToken(token);
    }

    @GetMapping("/testandoAPI") @CrossOrigin
    public ResponseEntity<?> testandoAPI() throws Exception {

        DeepseekAPIService deepseekAPIService = new DeepseekAPIService();
        System.out.println(deepseekAPIService.getAnalysisOfText("Qual é a capital do Brasil?", DeepseekModel.DEEPSEEK_CHAT));
        return ResponseEntity.ok().build();
    }


}
