package com.uniguide.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

@Service
public class AiChatService {

    private static final String API_URL =
            "https://api.groq.com/openai/v1/chat/completions";

    private static final String MODEL = "openai/gpt-oss-20b";

    private static final String SYSTEM_PROMPT = """
            You are UniGuide AI, a friendly and helpful university guidance
            assistant for students.

            RESPONSE STYLE:
            - Give a direct answer first.
            - Keep answers concise, clear, and easy to read on a phone.
            - Use short headings to separate topics.
            - Use bullet points, with each bullet on its own line.
            - Use blank lines between sections.
            - Avoid long, dense paragraphs.
            - Avoid Markdown tables. Use headings and bullet lists instead.
            - Never output HTML tags such as <br>.
            - Use bold formatting sparingly for important terms.
            - For semester-wise curricula, use a heading for each semester
              followed by a short bullet list of subjects.
            - For long topics, provide a brief summary first and offer
              to explain more if needed.
            - Do not repeat the question unnecessarily.
            - Do not invent university fees, course details, eligibility
              rules, deadlines, or admission requirements.
            - If exact information is uncertain, clearly say so and advise
              the student to verify it with the university.
            - Be friendly, respectful, and practical.
            """;

    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public AiChatService() {
        this.httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(20))
                .build();

        this.objectMapper = new ObjectMapper();
    }

    public String getAiResponse(String message) {
        String apiKey = System.getenv("GROQ_API_KEY");

        if (apiKey == null || apiKey.isBlank()) {
            throw new IllegalStateException(
                    "GROQ_API_KEY environment variable is not set.");
        }

        try {
            var requestBody = objectMapper.createObjectNode();

            requestBody.put("model", MODEL);
            requestBody.put("temperature", 0.5);
            requestBody.put("max_completion_tokens", 1200);

            var messages = requestBody.putArray("messages");

            var systemMessage = messages.addObject();
            systemMessage.put("role", "system");
            systemMessage.put("content", SYSTEM_PROMPT);

            var userMessage = messages.addObject();
            userMessage.put("role", "user");
            userMessage.put("content", message);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(API_URL))
                    .timeout(Duration.ofSeconds(60))
                    .header("Authorization", "Bearer " + apiKey)
                    .header("Content-Type", "application/json; charset=UTF-8")
                    .POST(HttpRequest.BodyPublishers.ofString(
                            objectMapper.writeValueAsString(requestBody),
                            StandardCharsets.UTF_8))
                    .build();

            HttpResponse<String> response = httpClient.send(
                    request,
                    HttpResponse.BodyHandlers.ofString(StandardCharsets.UTF_8));

            if (response.statusCode() < 200
                    || response.statusCode() >= 300) {
                throw new IllegalStateException(
                        "Groq API returned HTTP "
                                + response.statusCode() + ": "
                                + response.body());
            }

            JsonNode root = objectMapper.readTree(response.body());

            JsonNode content = root.path("choices")
                    .path(0)
                    .path("message")
                    .path("content");

            if (content.isMissingNode() || content.isNull()
                    || content.asText().isBlank()) {
                return "Sorry, I couldn't generate a response. "
                        + "Please try asking again.";
            }

            return content.asText().trim();

        } catch (IOException e) {
            throw new IllegalStateException(
                    "Could not communicate with the Groq API.", e);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException(
                    "The Groq API request was interrupted.", e);
        }
    }
}