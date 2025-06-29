// ElizaService.java
package com.example.eliza.service;

import com.example.eliza.model.ElizaData;
import com.example.eliza.model.ElizaEntry;
import com.example.eliza.model.SessionData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.*;

@Service
public class ElizaService {

    private ElizaData elizaData;
    private final List<String> defaultAnswers = Arrays.asList(
            "I see, tell me more about that.",
            "That's interesting, can you elaborate?",
            "How does that make you feel?");

    private final List<String> defaultQuestions = Arrays.asList(
            "What else is on your mind?",
            "Is there something specific you'd like to discuss?",
            "What would you like to talk about next?");

    private final List<String> greetingTemplates = Arrays.asList(
            "%s, how is your day going?",
            "%s, is something troubling you?",
            "%s, you seem happy, why is that?");

    @PostConstruct
    public void loadElizaData() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            ClassPathResource resource = new ClassPathResource("eliza.json");
            elizaData = mapper.readValue(resource.getInputStream(), ElizaData.class);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load Eliza data", e);
        }
    }

    public String generateGreeting(String userName) {
        Random random = new Random();
        String template = greetingTemplates.get(random.nextInt(greetingTemplates.size()));
        return String.format(template, userName);
    }

    public String generateResponse(String userInput, SessionData sessionData) {
        String input = userInput.toLowerCase();

        // Try to find a matching key
        for (ElizaEntry entry : elizaData.getElizaEntries()) {
            if (input.contains(entry.getKey().toLowerCase())) {
                return generateResponseForKey(entry, sessionData);
            }
        }

        // No key matched, use default response
        return generateDefaultResponse(sessionData);
    }

    private String generateResponseForKey(ElizaEntry entry, SessionData sessionData) {
        String key = entry.getKey();

        // Get available answer indices
        Set<Integer> usedAnswers = sessionData.getUsedAnswerIndices().getOrDefault(key, new HashSet<>());
        List<Integer> availableAnswerIndices = new ArrayList<>();
        for (int i = 0; i < entry.getAnswer().size(); i++) {
            if (!usedAnswers.contains(i)) {
                availableAnswerIndices.add(i);
            }
        }

        // If all answers used, reset
        if (availableAnswerIndices.isEmpty()) {
            usedAnswers.clear();
            sessionData.getUsedAnswerIndices().put(key, usedAnswers);
            for (int i = 0; i < entry.getAnswer().size(); i++) {
                availableAnswerIndices.add(i);
            }
        }

        // Get available question indices
        Set<Integer> usedQuestions = sessionData.getUsedQuestionIndices().getOrDefault(key, new HashSet<>());
        List<Integer> availableQuestionIndices = new ArrayList<>();
        for (int i = 0; i < entry.getQuestion().size(); i++) {
            if (!usedQuestions.contains(i)) {
                availableQuestionIndices.add(i);
            }
        }

        // If all questions used, reset
        if (availableQuestionIndices.isEmpty()) {
            usedQuestions.clear();
            sessionData.getUsedQuestionIndices().put(key, usedQuestions);
            for (int i = 0; i < entry.getQuestion().size(); i++) {
                availableQuestionIndices.add(i);
            }
        }

        // Randomly select answer and question
        Random random = new Random();
        int answerIndex = availableAnswerIndices.get(random.nextInt(availableAnswerIndices.size()));
        int questionIndex = availableQuestionIndices.get(random.nextInt(availableQuestionIndices.size()));

        // Mark as used
        usedAnswers.add(answerIndex);
        usedQuestions.add(questionIndex);
        sessionData.getUsedAnswerIndices().put(key, usedAnswers);
        sessionData.getUsedQuestionIndices().put(key, usedQuestions);

        String answer = entry.getAnswer().get(answerIndex);
        String question = entry.getQuestion().get(questionIndex);

        return answer + " " + question;
    }

    private String generateDefaultResponse(SessionData sessionData) {
        // Get available default answer indices
        List<Integer> availableAnswerIndices = new ArrayList<>();
        for (int i = 0; i < defaultAnswers.size(); i++) {
            if (!sessionData.getUsedDefaultAnswerIndices().contains(i)) {
                availableAnswerIndices.add(i);
            }
        }

        // If all answers used, reset
        if (availableAnswerIndices.isEmpty()) {
            sessionData.getUsedDefaultAnswerIndices().clear();
            for (int i = 0; i < defaultAnswers.size(); i++) {
                availableAnswerIndices.add(i);
            }
        }

        // Get available default question indices
        List<Integer> availableQuestionIndices = new ArrayList<>();
        for (int i = 0; i < defaultQuestions.size(); i++) {
            if (!sessionData.getUsedDefaultQuestionIndices().contains(i)) {
                availableQuestionIndices.add(i);
            }
        }

        // If all questions used, reset
        if (availableQuestionIndices.isEmpty()) {
            sessionData.getUsedDefaultQuestionIndices().clear();
            for (int i = 0; i < defaultQuestions.size(); i++) {
                availableQuestionIndices.add(i);
            }
        }

        // Randomly select answer and question
        Random random = new Random();
        int answerIndex = availableAnswerIndices.get(random.nextInt(availableAnswerIndices.size()));
        int questionIndex = availableQuestionIndices.get(random.nextInt(availableQuestionIndices.size()));

        // Mark as used
        sessionData.getUsedDefaultAnswerIndices().add(answerIndex);
        sessionData.getUsedDefaultQuestionIndices().add(questionIndex);

        String answer = defaultAnswers.get(answerIndex);
        String question = defaultQuestions.get(questionIndex);

        return answer + " " + question;
    }
}