// ElizaController.java
package com.example.eliza.controller;

import com.example.eliza.model.SessionData;
import com.example.eliza.service.ElizaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class ElizaController {

    private static final String SESSION_DATA_KEY = "sessionData";

    @Autowired
    private ElizaService elizaService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @PostMapping("/start")
    public String startConversation(@RequestParam("name") String name,
            HttpSession session,
            Model model) {
        // Create new session data
        SessionData sessionData = new SessionData();
        sessionData.setUserName(name);
        session.setAttribute(SESSION_DATA_KEY, sessionData);

        // Generate greeting
        String greeting = elizaService.generateGreeting(name);

        // Add to model for display
        model.addAttribute("userName", name);
        model.addAttribute("greeting", greeting);
        model.addAttribute("sessionData", sessionData);

        return "chat";
    }

    @PostMapping("/chat")
    public String chat(@RequestParam("userInput") String userInput,
            HttpSession session,
            Model model) {
        // Get session data
        SessionData sessionData = (SessionData) session.getAttribute(SESSION_DATA_KEY);

        if (sessionData == null) {
            // Session expired, redirect to start
            return "redirect:/";
        }

        // Generate Eliza's response
        String elizaResponse = elizaService.generateResponse(userInput, sessionData);

        // Add to conversation history
        sessionData.addConversationTurn(userInput, elizaResponse);

        // Update session
        session.setAttribute(SESSION_DATA_KEY, sessionData);

        // Add to model for display
        model.addAttribute("userName", sessionData.getUserName());
        model.addAttribute("sessionData", sessionData);
        model.addAttribute("latestUserInput", userInput);
        model.addAttribute("latestElizaResponse", elizaResponse);

        return "chat";
    }
}