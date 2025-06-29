package com.example.eliza.model;

import java.util.List;

public class ElizaData {
    private List<ElizaEntry> elizaEntries;

    public ElizaData() {
    }

    public List<ElizaEntry> getElizaEntries() {
        return elizaEntries;
    }

    public void setElizaEntries(List<ElizaEntry> elizaEntries) {
        this.elizaEntries = elizaEntries;
    }
}