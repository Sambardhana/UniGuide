package com.uniguide.controller;

import com.uniguide.dto.ChatRequest;
import com.uniguide.dto.ChatResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ai")
public class AiChatController {

    @PostMapping("/chat")
    public ChatResponse chat(@RequestBody ChatRequest request) {

        return new ChatResponse(
                "Hello! UniGuide AI backend is connected successfully."
        );
    }
}
