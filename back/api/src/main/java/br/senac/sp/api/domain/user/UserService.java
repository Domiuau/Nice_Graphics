package br.senac.sp.api.domain.user;

import br.senac.sp.api.domain.analysis.*;

import br.senac.sp.api.domain.user.dto.*;
import br.senac.sp.api.infra.errors.exceptions.ActionNotAllowedException;
import br.senac.sp.api.infra.errors.exceptions.CharacterLimitReachedException;
import br.senac.sp.api.infra.errors.exceptions.InvalidLoginException;
import br.senac.sp.api.infra.errors.exceptions.InvalidOrExpiredTokenException;
import br.senac.sp.api.infra.security.services.TokenService;
import br.senac.sp.api.services.apicalls.AvailableAI;
import br.senac.sp.api.services.apicalls.AIModel;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

        try {
            var userNamePassword = new UsernamePasswordAuthenticationToken(login.login(), login.senha());
            var auth = authenticationManager.authenticate(userNamePassword);
            var token = tokenService.generateToken((User) auth.getPrincipal());

            return ResponseEntity.ok(new LoggedUserDTO((User) auth.getPrincipal(), token));
        } catch (AuthenticationException e) {
            throw new InvalidLoginException("Login ou senha inválidos");
        }
    }

    @Transactional
    public ResponseEntity<?> updateUser(UpdateUserDTO update, String token) {
        String username = tokenService.validateToken(token.replace("Bearer ", ""));
        User user = (User) userRepository.findByUsername(username);
        if (user == null) throw new InvalidOrExpiredTokenException("Não foi possível carregar o usuário pois o token é inválido ou expirou");

        user.setUsername(update.name());
        user.setEmail(update.email());

        if(!update.password().isEmpty()) {
            String encryptedPassword = new BCryptPasswordEncoder().encode(update.password());
            user.setPassword(encryptedPassword);
        }

        var newToken = tokenService.generateToken(user);

        return ResponseEntity.ok(new LoggedUserDTO(user, newToken));

    }

    @Transactional
    public ResponseEntity<?> analyzeText(String text, AIModel model, String token) throws Exception {

        if (model.getCharactersLimit() < text.length()) throw new CharacterLimitReachedException("Tamanho do texto: " + text.length() + " - Limite do modelo " + model.getModelName() + ": " + model.getCharactersLimit());

        AnalysisDTO analysisDTO = model.getAI().getApiConnector().getAnalysisOfText(text, model);

        if (token != null) {
            String username = tokenService.validateToken(token.replace("Bearer ", ""));
            User user = (User) userRepository.findByUsername(username);
            if (user != null) {
                Analysis analysis = new Analysis(analysisDTO, user);
                user.getAnalyses().add(analysis);
            }


        }

        return ResponseEntity.ok(analysisDTO);
    }

    public ResponseEntity<?> getGenerations(String token) {
        String username = tokenService.validateToken(token.replace("Bearer ", ""));
        User user = (User) userRepository.findByUsername(username);
        if (user == null) throw new InvalidOrExpiredTokenException("Não foi possível carregar as gerações pois o token é inválido ou expirou");
        return ResponseEntity.ok(new UserAnalyzesDTO(user.getAnalyses().stream().map(analysis ->
                new AnalysisReturnDTO(analysis)).toList()));
    }

    public ResponseEntity<?> getGenerationsPreviews(String token) {
        String username = tokenService.validateToken(token.replace("Bearer ", ""));
        User user = (User) userRepository.findByUsername(username);
        if (user == null) throw new InvalidOrExpiredTokenException("Não foi possível carregar as gerações pois o token é inválido ou expirou");
        return ResponseEntity.ok(new AnalysisPreviewsListDTO(user.getAnalyses().stream().map(analysis ->
                new AnalysisPreviewDTO(analysis, analysis.getContexts().size())).toList()));
    }

    public ResponseEntity<?> getContextDetailsById(String id, String token) {
        String username = tokenService.validateToken(token.replace("Bearer ", ""));
        User user = (User) userRepository.findByUsername(username);
        if (user == null) throw new InvalidOrExpiredTokenException("Não foi possível carregar os detalhes pois o token é inválido ou expirou");
        Analysis analysis = analisysRepository.findById(id).orElseThrow(() -> new ConstraintViolationException("Análise não encontrada", null));
        if (!user.getId().equals(analysis.getUser().getId())) throw new ActionNotAllowedException("A análise especificada não pertence ao usuário logado");
        return ResponseEntity.ok(new AnalysisReturnDTO(analysis));

    }

    public ResponseEntity<?> testSaveContext(String text, AvailableAI availableAI, AIModel model) throws Exception {



        return ResponseEntity.ok("nada em teste no momento");


    }

    public ResponseEntity<?> validateToken(String token) {
        String username = tokenService.validateToken(token.replace("Bearer ", ""));
        User user = (User) userRepository.findByUsername(username);

        return user == null ?
                ResponseEntity.badRequest().body("Token inválido") :
                ResponseEntity.ok(new LoggedUserDTO(user, token));

    }
}
