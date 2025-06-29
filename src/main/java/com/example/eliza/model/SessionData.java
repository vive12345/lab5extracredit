// SessionData.java
package com.example.eliza.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

public class SessionData {
    private String userName;
    private List<ConversationTurn> conversation;
    private Map<String, Set<Integer>> usedAnswerIndices;
    private Map<String, Set<Integer>> usedQuestionIndices;
    private Set<Integer> usedDefaultAnswerIndices;
    private Set<Integer> usedDefaultQuestionIndices;

    public SessionData() {
        this.conversation = new ArrayList<>();
        this.usedAnswerIndices = new HashMap<>();
        this.usedQuestionIndices = new HashMap<>();
        this.usedDefaultAnswerIndices = new HashSet<>();
        this.usedDefaultQuestionIndices = new HashSet<>();
    }

    // Getters and Setters
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<ConversationTurn> getConversation() {
        return conversation;
    }

    public void setConversation(List<ConversationTurn> conversation) {
        this.conversation = conversation;
    }

    public Map<String, Set<Integer>> getUsedAnswerIndices() {
        return usedAnswerIndices;
    }

    public void setUsedAnswerIndices(Map<String, Set<Integer>> usedAnswerIndices) {
        this.usedAnswerIndices = usedAnswerIndices;
    }

    public Map<String, Set<Integer>> getUsedQuestionIndices() {
        return usedQuestionIndices;
    }

    public void setUsedQuestionIndices(Map<String, Set<Integer>> usedQuestionIndices) {
        this.usedQuestionIndices = usedQuestionIndices;
    }

    public Set<Integer> getUsedDefaultAnswerIndices() {
        return usedDefaultAnswerIndices;
    }

    public void setUsedDefaultAnswerIndices(Set<Integer> usedDefaultAnswerIndices) {
        this.usedDefaultAnswerIndices = usedDefaultAnswerIndices;
    }

    public Set<Integer> getUsedDefaultQuestionIndices() {
        return usedDefaultQuestionIndices;
    }

    public void setUsedDefaultQuestionIndices(Set<Integer> usedDefaultQuestionIndices) {
        this.usedDefaultQuestionIndices = usedDefaultQuestionIndices;
    }

    public void addConversationTurn(String userInput, String elizaResponse) {
        conversation.add(new ConversationTurn(userInput, elizaResponse));
    }
}