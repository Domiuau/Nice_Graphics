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

    @PostMapping("/analyze/gpt_3_5")
    public ResponseEntity<?> analyzeTextWithGpt3_5(@RequestBody TextForAnalyzeDTO textForAnalyzeDTO, @RequestHeader(required = false, name = "Authorization") String token) throws Exception {
        return userService.analyzeText(textForAnalyzeDTO.text(), OpenAiGPTModel.GPT_3_5_TURBO, token);
    }

    @PostMapping("/analyze/gpt_4")
    public ResponseEntity<?> analyzeTextWithGpt4(@RequestBody TextForAnalyzeDTO textForAnalyzeDTO, @RequestHeader(name = "Authorization") String token) throws Exception {
        return userService.analyzeText(textForAnalyzeDTO.text(), OpenAiGPTModel.GPT_4, token);
    }

    @PostMapping("/analyze/gemini_1_5_flash")
    public ResponseEntity<?> analyzeTextWithGemini1_5_flash(@RequestBody TextForAnalyzeDTO textForAnalyzeDTO, @RequestHeader(required = false, name = "Authorization") String token) throws Exception {
        return userService.analyzeText(textForAnalyzeDTO.text(), GeminiModel.GEMINI_1_5_FLASH, token);
    }

    @PostMapping("/analyze/gemini_2_0_flash")
    public ResponseEntity<?> analyzeTextWithGemini2_0_flash(@RequestBody TextForAnalyzeDTO textForAnalyzeDTO, @RequestHeader(name = "Authorization") String token) throws Exception {
        return userService.analyzeText(textForAnalyzeDTO.text(), GeminiModel.GEMINI_2_0_FLASH, token);
    }

    @PostMapping("/analyze/deepseek_chat")
    ResponseEntity<?> analyzeTextWithDeepseek_chat(@RequestBody TextForAnalyzeDTO textForAnalyzeDTO, @RequestHeader(required = false, name = "Authorization") String token) throws Exception {
        return userService.analyzeText(textForAnalyzeDTO.text(), DeepseekModel.DEEPSEEK_CHAT, token);
    }

    @PostMapping("/analyze/deepseek_reasoner")
    ResponseEntity<?> analyzeTextWithDeepseek_reasoner(@RequestBody TextForAnalyzeDTO textForAnalyzeDTO, @RequestHeader(name = "Authorization") String token) throws Exception {
        return userService.analyzeText(textForAnalyzeDTO.text(), DeepseekModel.DEEKSEEK_REASONER, token);
    }

    @GetMapping("/analyze/generations")
    public ResponseEntity<?> getGenerations(@RequestHeader(name = "Authorization") String token) {
        return userService.getGenerations(token);
    }

    @GetMapping("/models/details")
    public ResponseEntity<?> getModelsDetails() {
        return ResponseEntity.ok(new AiModelsDetailsReturnDTO());
    }



}
