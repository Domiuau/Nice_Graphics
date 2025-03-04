package br.senac.sp.api.controller;

import br.senac.sp.api.domain.analysis.TextForAnalyzeDTO;
import br.senac.sp.api.domain.user.UserService;
import br.senac.sp.api.services.apicalls.AvailableIA;
import br.senac.sp.api.services.apicalls.IAModel;
import br.senac.sp.api.services.apicalls.gemini.GeminiModel;
import br.senac.sp.api.services.apicalls.openai.OpenAiGPTModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/analyze/gpt_3_5")
    public ResponseEntity<?> analyzeTextWithGpt3_5(@RequestBody TextForAnalyzeDTO textForAnalyzeDTO) throws Exception {
        return userService.analyzeText(textForAnalyzeDTO.text(), AvailableIA.OPENAI, OpenAiGPTModel.GPT_3_5_TURBO);
    }

    @PostMapping("/analyze/gpt_4")
    public ResponseEntity<?> analyzeTextWithGpt4(@RequestBody TextForAnalyzeDTO textForAnalyzeDTO) throws Exception {
        return userService.analyzeText(textForAnalyzeDTO.text(), AvailableIA.OPENAI, OpenAiGPTModel.GPT_4);
    }

    @PostMapping("/analyze/gemini_1_5_flash")
    public ResponseEntity<?> analyzeTextWithGemini1_5_flash(@RequestBody TextForAnalyzeDTO textForAnalyzeDTO) throws Exception {
        return userService.analyzeText(textForAnalyzeDTO.text(), AvailableIA.GEMINI, GeminiModel.GEMINI_1_5_FLASH);
    }

    @PostMapping("/analyze/gemini_2_0_flash")
    public ResponseEntity<?> analyzeTextWithGemini2_0_flash(@RequestBody TextForAnalyzeDTO textForAnalyzeDTO) throws Exception {
        return userService.analyzeText(textForAnalyzeDTO.text(), AvailableIA.GEMINI, GeminiModel.GEMINI_2_0_FLASH);
    }

}
