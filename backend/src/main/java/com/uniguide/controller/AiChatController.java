package com.uniguide.controller;

import com.uniguide.service.AiChatService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ai")
public class AiChatController {

    private final AiChatService aiChatService;

    public AiChatController(AiChatService aiChatService) {
        this.aiChatService = aiChatService;
    }

    @PostMapping("/chat")
    public ChatResponse chat(@RequestBody ChatRequest request) {

        String message = "";

        if (request.messages() != null && !request.messages().isEmpty()) {
            message = request.messages()
                    .get(request.messages().size() - 1)
                    .content();
        }

        String reply = aiChatService.getAiResponse(message);

        return new ChatResponse(reply);
    }

    public record ChatRequest(
            String role,
            List<ChatMessage> messages
    ) {}

    public record ChatMessage(
            String role,
            String content
    ) {}

    public record ChatResponse(
            String reply
    ) {}
}