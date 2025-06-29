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