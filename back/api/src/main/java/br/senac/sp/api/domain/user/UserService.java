package br.senac.sp.api.domain.user;

import br.senac.sp.api.domain.analysis.AnalisysRepository;

import br.senac.sp.api.domain.analysis.Analysis;
import br.senac.sp.api.domain.analysis.AnalysisDTO;
import br.senac.sp.api.domain.user.dto.LoggedUserDTO;
import br.senac.sp.api.domain.user.dto.RegisterUserDTO;
import br.senac.sp.api.domain.user.dto.LoginUserDTO;
import br.senac.sp.api.infra.security.services.TokenService;
import br.senac.sp.api.services.apicalls.AvailableIA;
import br.senac.sp.api.services.apicalls.IAModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;

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

        var token = tokenService.generateToken(user);

        return ResponseEntity.ok(new LoggedUserDTO(user, token));

    }

    public ResponseEntity<?> login(LoginUserDTO login) {

        var userNamePassword = new UsernamePasswordAuthenticationToken(login.login(), login.senha());
        var auth = authenticationManager.authenticate(userNamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoggedUserDTO((User) auth.getPrincipal(), token));
    }

    public ResponseEntity<?> analyzeText(String text, AvailableIA availableIA, IAModel model) throws Exception {

        AnalysisDTO analysisDTO = availableIA.getApiConnector().getAnalysisOfText(text, model);
        Analysis analysis = new Analysis(analysisDTO);
        analisysRepository.save(analysis);

        return ResponseEntity.ok(analysisDTO);
    }

    public ResponseEntity<?> testSaveContext(String text, AvailableIA availableIA, IAModel model) throws Exception {

        AnalysisDTO analysisDTO = availableIA.getApiConnector().getAnalysisOfText(text, model);
        Analysis analysis = new Analysis(analysisDTO);
        analisysRepository.save(analysis);

        return ResponseEntity.ok(analysisDTO);


    }

}
