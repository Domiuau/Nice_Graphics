package br.senac.sp.api.controller;

import br.senac.sp.api.domain.analysis.TextForAnalyzeDTO;
import br.senac.sp.api.domain.user.UserService;
import br.senac.sp.api.services.apicalls.AvailableAI;
import br.senac.sp.api.services.apicalls.deepseek.DeepseekModel;
import br.senac.sp.api.services.apicalls.dto.AiModelsDetailsReturnDTO;
import br.senac.sp.api.services.apicalls.gemini.GeminiModel;
import br.senac.sp.api.services.apicalls.openai.OpenAiGPTModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    private AiModelsDetailsReturnDTO aiModelsDetailsReturnDTO = new AiModelsDetailsReturnDTO();

    @PostMapping("/analyze/gpt-3.5-turbo") @CrossOrigin
    public ResponseEntity<?> analyzeTextWithGpt3_5(@RequestBody TextForAnalyzeDTO textForAnalyzeDTO, @RequestHeader(required = false, name = "Authorization") String token) throws Exception {
        return userService.analyzeText(textForAnalyzeDTO.text(), OpenAiGPTModel.GPT_3_5_TURBO, token);
    }

    @PostMapping("/analyze/gpt-4") @CrossOrigin
    public ResponseEntity<?> analyzeTextWithGpt4(@RequestBody TextForAnalyzeDTO textForAnalyzeDTO, @RequestHeader(name = "Authorization") String token) throws Exception {
        return userService.analyzeText(textForAnalyzeDTO.text(), OpenAiGPTModel.GPT_4, token);
    }

    @PostMapping("/analyze/gpt-4-turbo") @CrossOrigin
    public ResponseEntity<?> analyzeTextWithGpt4Turbo(@RequestBody TextForAnalyzeDTO textForAnalyzeDTO, @RequestHeader(name = "Authorization") String token) throws Exception {
        return userService.analyzeText(textForAnalyzeDTO.text(), OpenAiGPTModel.GPT_4_TURBO, token);
    }

    @PostMapping("/analyze/gpt-4o") @CrossOrigin
    public ResponseEntity<?> analyzeTextWithGpt4o(@RequestBody TextForAnalyzeDTO textForAnalyzeDTO, @RequestHeader(name = "Authorization") String token) throws Exception {
        return userService.analyzeText(textForAnalyzeDTO.text(), OpenAiGPTModel.GPT_4o, token);
    }

    @PostMapping("/analyze/gpt-4o-mini") @CrossOrigin
    public ResponseEntity<?> analyzeTextWithGpt4oMini(@RequestBody TextForAnalyzeDTO textForAnalyzeDTO, @RequestHeader(required = false, name = "Authorization") String token) throws Exception {
        return userService.analyzeText(textForAnalyzeDTO.text(), OpenAiGPTModel.GPT_4o_mini, token);
    }



    @PostMapping("/analyze/gemini-1.5-flash") @CrossOrigin
    public ResponseEntity<?> analyzeTextWithGemini1_5_flash(@RequestBody TextForAnalyzeDTO textForAnalyzeDTO, @RequestHeader(required = false, name = "Authorization") String token) throws Exception {
        return userService.analyzeText(textForAnalyzeDTO.text(), GeminiModel.GEMINI_1_5_FLASH, token);
    }

    @PostMapping("/analyze/gemini-1.5-pro") @CrossOrigin
    public ResponseEntity<?> analyzeTextWithGemini1_5_pro(@RequestBody TextForAnalyzeDTO textForAnalyzeDTO, @RequestHeader(name = "Authorization") String token) throws Exception {
        return userService.analyzeText(textForAnalyzeDTO.text(), GeminiModel.GEMINI_1_5_PRO, token);
    }

    @PostMapping("/analyze/gemini-2.0-flash") @CrossOrigin
    public ResponseEntity<?> analyzeTextWithGemini2_0_flash(@RequestBody TextForAnalyzeDTO textForAnalyzeDTO, @RequestHeader(name = "Authorization") String token) throws Exception {
        return userService.analyzeText(textForAnalyzeDTO.text(), GeminiModel.GEMINI_2_0_FLASH, token);
    }



    @PostMapping("/analyze/deepseek-chat") @CrossOrigin
    ResponseEntity<?> analyzeTextWithDeepseek_chat(@RequestBody TextForAnalyzeDTO textForAnalyzeDTO, @RequestHeader(required = false, name = "Authorization") String token) throws Exception {
        return userService.analyzeText(textForAnalyzeDTO.text(), DeepseekModel.DEEPSEEK_CHAT, token);
    }

    @PostMapping("/analyze/deepseek-reasoner") @CrossOrigin
    ResponseEntity<?> analyzeTextWithDeepseek_reasoner(@RequestBody TextForAnalyzeDTO textForAnalyzeDTO, @RequestHeader(name = "Authorization") String token) throws Exception {
        return userService.analyzeText(textForAnalyzeDTO.text(), DeepseekModel.DEEKSEEK_REASONER, token);
    }

    @GetMapping("/analyze/generations") @CrossOrigin
    public ResponseEntity<?> getGenerations(@RequestHeader(name = "Authorization") String token) {
        return userService.getGenerations(token);
    }

    @GetMapping("/analyze/generations/previews") @CrossOrigin
    public ResponseEntity<?> getGenerationsPreviews(@RequestHeader(name = "Authorization") String token) {
        return userService.getGenerationsPreviews(token);
    }

    @GetMapping("/analyze/generation/{id}") @CrossOrigin
    public ResponseEntity<?> getGenerationsPreviews(@RequestHeader(name = "Authorization") String token, @PathVariable String id) {
        return userService.getContextDetailsById(id, token);
    }

    @GetMapping("/models/details") @CrossOrigin
    public ResponseEntity<?> getModelsDetails() {
        return ResponseEntity.ok(aiModelsDetailsReturnDTO);
    }



}
