// ElizaEntry.java
package com.example.eliza.model;

import java.util.List;

public class ElizaEntry {
    private String key;
    private List<String> answer;
    private List<String> question;
    
    // Constructors
    public ElizaEntry() {}
    
    public ElizaEntry(String key, List<String> answer, List<String> question) {
        this.key = key;
        this.answer = answer;
        this.question = question;
    }
    
    // Getters and Setters
    public String getKey() {
        return key;
    }
    
    public void setKey(String key) {
        this.key = key;
    }
    
    public List<String> getAnswer() {
        return answer;
    }
    
    public void setAnswer(List<String> answer) {
        this.answer = answer;
    }
    
    public List<String> getQuestion() {
        return question;
    }
    
    public void setQuestion(List<String> question) {
        this.question = question;
    }
}

// ElizaData.java
package com.example.eliza.model;

import java.util.List;

public class ElizaData {
    private List<ElizaEntry> elizaEntries;
    
    public ElizaData() {}
    
    public List<ElizaEntry> getElizaEntries() {
        return elizaEntries;
    }
    
    public void setElizaEntries(List<ElizaEntry> elizaEntries) {
        this.elizaEntries = elizaEntries;
    }
}

// ConversationTurn.java
package com.example.eliza.model;

public class ConversationTurn {
    private String userInput;
    private String elizaResponse;
    
    public ConversationTurn(String userInput, String elizaResponse) {
        this.userInput = userInput;
        this.elizaResponse = elizaResponse;
    }
    
    public String getUserInput() {
        return userInput;
    }
    
    public void setUserInput(String userInput) {
        this.userInput = userInput;
    }
    
    public String getElizaResponse() {
        return elizaResponse;
    }
    
    public void setElizaResponse(String elizaResponse) {
        this.elizaResponse = elizaResponse;
    }
}