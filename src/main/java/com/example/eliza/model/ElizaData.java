package com.example.eliza.model;

import java.util.List;

public class ElizaData {
    private String dictionary_name;
    private List<ElizaEntry> entries;

    public ElizaData() {
    }

    public String getDictionary_name() {
        return dictionary_name;
    }

    public void setDictionary_name(String dictionary_name) {
        this.dictionary_name = dictionary_name;
    }

    public List<ElizaEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<ElizaEntry> entries) {
        this.entries = entries;
    }

    public List<ElizaEntry> getElizaEntries() {
        return entries;
    }
}