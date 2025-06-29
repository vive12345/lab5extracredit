package com.example.eliza.model;

import java.util.List;

public class ElizaEntry {
    private List<String> key;
    private List<String> answer;
    private List<String> question;

    // Constructors
    public ElizaEntry() {
    }

    public ElizaEntry(List<String> key, List<String> answer, List<String> question) {
        this.key = key;
        this.answer = answer;
        this.question = question;
    }

    // Getters and Setters
    public List<String> getKey() {
        return key;
    }

    public void setKey(List<String> key) {
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