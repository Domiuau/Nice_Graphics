package br.senac.sp.api.domain.user;

import br.senac.sp.api.domain.analysis.AnalisysRepository;

import br.senac.sp.api.domain.analysis.Analysis;
import br.senac.sp.api.domain.analysis.AnalysisDTO;
import br.senac.sp.api.domain.context.Context;
import br.senac.sp.api.domain.context.ContextDTO;
import br.senac.sp.api.domain.data.Data;
import br.senac.sp.api.domain.data.DataDTO;
import br.senac.sp.api.domain.user.dto.RegisterUserDTO;
import br.senac.sp.api.domain.user.dto.LoginUserDTO;
import br.senac.sp.api.infra.security.services.TokenService;
import br.senac.sp.api.services.apicalls.APIConnector;
import br.senac.sp.api.services.apicalls.AvailableIA;
import br.senac.sp.api.services.apicalls.openai.IAModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;

@Service
public class UserService {

    @Autowired
    private AnalisysRepository analisysRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;


    public ResponseEntity<?> cadastrar(RegisterUserDTO cadastro) {

        String encryptedPassword = new BCryptPasswordEncoder().encode(cadastro.senha());
        User user = new User(cadastro.login(), encryptedPassword, cadastro.email());
        userRepository.save(user);

        return ResponseEntity.ok("usuario " + user.getUsername() + " foi registrado");

    }

    public ResponseEntity<?> login(LoginUserDTO login) {

        var userNamePassword = new UsernamePasswordAuthenticationToken(login.login(), login.senha());
        var auth = authenticationManager.authenticate(userNamePassword);

        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(token);
    }

    public ResponseEntity<?> testSaveContext(String text, AvailableIA availableIA, IAModel model) throws Exception {

        AnalysisDTO analysisDTO = availableIA.getApiConnector().getAnalysisOfText(text, model);
        Analysis analysis = new Analysis(analysisDTO);
        analisysRepository.save(analysis);

       return ResponseEntity.ok(analysisDTO);


    }

}
